import com.soywiz.kds.ListReader
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.bitmap.context2d
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.toSvgPathString
import com.soywiz.korio.lang.printStackTrace
import com.soywiz.korio.util.StrReader
import com.soywiz.korio.util.isDigit
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.vector.*
import kotlin.math.*


//============================================SUPPORT FUNCTIONS==============================================================

private fun xformPointX(x: Double, y: Double, t: DoubleArray) = x*t[0] + y*t[2] + t[4]
private fun xformPointY(x: Double, y: Double, t: DoubleArray) = x*t[1] + y*t[3] + t[5]
private fun xformVecX(  x: Double, y: Double, t: DoubleArray) = x*t[0] + y*t[2]
private fun xformVecY(  x: Double, y: Double, t: DoubleArray): Double = x*t[1] + y*t[3]

private val t = DoubleArray(6)

private fun vmag(x: Double, y: Double): Double {
    return sqrt(x * x + y * y)
}

private fun vecrat(ux: Double, uy: Double, vx: Double, vy: Double): Double {
    return (ux * vx + uy * vy) / (vmag(ux, uy) * vmag(vx, vy))
}

private fun vecang(ux: Double, uy: Double, vx: Double, vy: Double): Double {
    var r = vecrat(ux, uy, vx, vy)
    if (r < -1.0) r = -1.0
    if (r > 1.0) r = 1.0
    return (if (ux * vy < uy * vx) -1.0 else 1.0) * acos(r)
}

private fun sqr(v: Double) = v * v
val bounds = Rectangle()

interface  PathToken
data class PathTokenNumber(val value: Double) : PathToken
data class PathTokenCmd(val id: Char) : PathToken

private fun tokenizePath(str: String): List<PathToken> {
    val sr = StrReader(str)
    fun StrReader.skipSeparators() {skipWhile { it == ',' || it == ' ' || it == '\t' || it == '\n' || it == '\r' } }

    fun StrReader.readNumber(): Double {
        skipSeparators()
        var first = true
        val str = readWhile {
            if (first) { first = false;it.isDigit() || it == '-' || it == '+'
            } else {it.isDigit() || it == '.' }
        }
        return if (str.isEmpty()) 0.0 else try {
            str.toDouble()
        } catch (e: Throwable) {
            e.printStackTrace()
            0.0
        }
    }

    val out = arrayListOf<PathToken>()
    while (sr.hasMore) {
        sr.skipSeparators()
        val c = sr.peekChar()
        out += if (c in '0'..'9' || c == '-' || c == '+') {
            PathTokenNumber(sr.readNumber())
        } else {
            PathTokenCmd(sr.readChar())
        }
    }

    return out

}//End_of_Function( tokenizePath() )


//===========================================================================================================================

fun convertSymbolSVGToNativeImageListG(symbolHeightOnScreen:Int, symbol: Symbol,
                                      strokeColor: RGBA=Colors.LIGHTSKYBLUE,   strokeWidth:Double = 0.5):List<NativeImage> {
    val actualWidth  = symbol.vB.x1
    val actualHeight = symbol.vB.y1
    val pathList     = symbol.pAr

    val bitmapWidth  = (symbolHeightOnScreen * actualWidth / actualHeight).toInt()
    val bitmaps      = mutableListOf<NativeImage>()

    for (item in pathList) {

        val mVector = drawPath(bitmapWidth, symbolHeightOnScreen, actualWidth, actualHeight, strokeColor, item, strokeWidth)

        bitmaps.add(mVector)
    }

    return bitmaps

}//End_of_Function( convertSymbolSVGToNativeImageList )


//=======================================================================================================================================
/*

(ONLY) FUNCTION CALLED FROM MAIN

 */
fun Stage.renderSymbol(symbolHeightOnScreen:Int, symbol: Symbol, strokeColor: RGBA=Colors.LIGHTSKYBLUE,   strokeWidth:Double = 0.5):Container {
    val bitmaps = convertSymbolSVGToNativeImageListG(symbolHeightOnScreen, symbol, strokeColor, strokeWidth)
    return container{
        bitmaps.forEach {
            image(it)
        }
    }
}

//===============================================================================================================================


fun drawPath(w: Int, h:Int, viewPortWidth:Double, viewPortHeight:Double, strokeColor: RGBA, path: String, strokeWidth:Double = 0.5, xtranslation:Double = 1.0): NativeImage = NativeImage(w, h).context2d {
    //println(">drawPathG(),  path = $path")
    val scale = if (viewPortHeight >viewPortWidth) viewPortHeight else viewPortWidth
    lineWidth = strokeWidth/scale
    val mSize =  if (w >h) w else h
    lineCap = LineCap.ROUND

    val tokens = tokenizePath(path)
    val tokAr  = ListReader(tokens)

    //println("inGK::  tokAr = {$tokAr}")

    stroke(strokeColor) {
        scale(mSize)
        val warningProcessor: ((message: String) -> Unit)? = null
        // ===== BEGIN PATH =======

        fun dumpTokens() = run { for ((n, token) in tokens.withIndex()) warningProcessor?.invoke("- $n: $token") }
        fun isNextNumber(): Boolean = if (tokAr.hasMore) tokAr.peek() is PathTokenNumber else false

        fun readNumber(): Double {
            while (tokAr.hasMore) {
                val token = tokAr.read()
                if (token is PathTokenNumber) {
                    return token.value
                }
                warningProcessor?.invoke("Invalid path (expected number but found $token) at ${tokAr.position - 1}")
                dumpTokens()
            }
            return 0.0
        }
        fun readAndScaleNumber(xs:Double =0.0, scale: Double): Double {
            while (tokAr.hasMore) {
                val token = tokAr.read()
                if (token is PathTokenNumber) {
                    //         println("TOKEN VALUE = ${token.value}")
                    return (token.value + xs) / scale
                }
                warningProcessor?.invoke("Invalid path (expected number but found $token) at ${tokAr.position - 1}")
                dumpTokens()
            }
            return 0.0
        }

        fun n(xs:Double= 0.0): Double = readAndScaleNumber(xs=xs, scale=scale)
        fun nX(relative: Boolean, xs:Double=0.0): Double =
            if (relative) lastX + readAndScaleNumber(xs=0.0, scale=scale) else readAndScaleNumber(xs=xs, scale = scale)

        fun nY(relative: Boolean): Double =
            if (relative) lastY +  readAndScaleNumber(xs=0.0, scale=scale) else  readAndScaleNumber(xs=0.0, scale=scale)

        fun readNextTokenCmd(): Char? {
            while (tokAr.hasMore) {
                val token = tokAr.read()
                if (token is PathTokenCmd) {
                    //    println("TOKEN ID = ${token.id}")
                    return token.id
                }
                warningProcessor?.invoke("Invalid path (expected command but found $token) at ${tokAr.position - 1}")
                dumpTokens()
            }
            return null
        }

        beginPath()
        moveTo(0.0, 0.0) // Supports relative positioning as first command
        var lastCX = 0.0;var lastCY = 0.0
        var lastCmd = '-'

        while (tokAr.hasMore) {
            val cmd = readNextTokenCmd() ?: break
            val relative = cmd in 'a'..'z' // lower case

            //println("cmd = $cmd")
            //println("relative = $relative")
            var lastCurve = when (lastCmd) {
                'S', 'C', 'T', 'Q', 's', 'c', 't', 'q' -> true
                else -> false
            }

            when (cmd) {
                'M', 'm' -> {
                    rMoveTo(n(xtranslation), n(), relative)
                    while (isNextNumber()) rLineTo(n(xtranslation), n(), relative)
                }
                'L', 'l' -> while (isNextNumber()) {
                    rLineTo(n(xtranslation), n(), relative)
                }
                'H', 'h' -> while (isNextNumber()) rLineToH(n(xtranslation), relative)
                'V', 'v' -> while (isNextNumber()) rLineToV(n(), relative)
                'Q', 'q' -> while (isNextNumber()) {
                    val cx = nX(relative);val cy = nY(relative);val x2 = nX(relative);val y2 = nY(relative)
                    lastCX = cx;lastCY = cy
                    quadTo(cx, cy, x2, y2)
                }
                'C', 'c' -> while (isNextNumber()) {
                    val x1 = nX(relative, xtranslation)
                    val y1 = nY(relative)
                    val x2 = nX(relative, xtranslation)
                    val y2 = nY(relative)
                    val x  = nX(relative, xtranslation)
                    val y  = nY(relative)
                    lastCX = x2;lastCY = y2
                    cubicTo(x1 , y1, x2 , y2, x , y)
                }
                'S', 's' -> {
                    while (isNextNumber()) {
                        val x2 = nX(relative, xtranslation)
                        val y2 = nY(relative)
                        val x = nX(relative, xtranslation)
                        val y = nY(relative)
                        val x1 = if (lastCurve) lastX * 2 - lastCX else lastX
                        val y1 = if (lastCurve) lastY * 2 - lastCY else lastY

                        lastCX = x2; lastCY = y2

                        cubicTo(x1 , y1, x2 , y2, x , y)

                        lastCurve = true
                    }
                }
                'T', 't' -> {
                    //var n = 0
                    while (isNextNumber()) {
                        val x2 = nX(relative,xtranslation)
                        val y2 = nY(relative)
                        val cx = if (lastCurve) lastX * 2 - lastCX else lastX
                        val cy = if (lastCurve) lastY * 2 - lastCY else lastY
                        lastCX = cx;lastCY = cy
                        quadTo(cx , cy, x2 , y2)
                        //n++
                        lastCurve = true
                    }
                }
                'A', 'a' -> {
                    // Ported from nanosvg (https://github.com/memononen/nanosvg/blob/25241c5a8f8451d41ab1b02ab2d865b01600d949/src/nanosvg.h#L2067)
                    // Ported from canvg (https://code.google.com/p/canvg/)
                    var rx = readAndScaleNumber(xs = 0.0, scale=scale).absoluteValue                // y radius
                    var ry = readAndScaleNumber(xs = 0.0, scale = scale).absoluteValue                // x radius
                    val rotx = readNumber() / 180.0 * PI        // x rotation angle
                    val fa = if ((readNumber().absoluteValue) > 1e-6) 1 else 0    // Large arc
                    val fs = if ((readNumber().absoluteValue) > 1e-6) 1 else 0    // Sweep direction
                    val x1 = lastX     // start point
                    val y1 = lastY     // end point
                    val x2 = nX(relative)
                    val y2 = nY(relative)

                    var dx = x1 - x2;var dy = y1 - y2

                    val d = hypot(dx, dy)
                    if (d < 1e-6f || rx < 1e-6f || ry < 1e-6f) {
                        // The arc degenerates to a line
                        lineTo(x2, y2)
                    } else {
                        val sinrx = sin(rotx);val cosrx = cos(rotx)

                        val x1p = cosrx * dx / 2.0f + sinrx * dy / 2.0f
                        val y1p = -sinrx * dx / 2.0f + cosrx * dy / 2.0f
                        var d = sqr(x1p) / sqr(rx) + sqr(y1p) / sqr(ry)
                        if (d > 1) {d = sqr(d);rx *= d; ry *= d }
                        // 2) Compute cx', cy'
                        var s = 0.0
                        var sa = sqr(rx) * sqr(ry) - sqr(rx) * sqr(y1p) - sqr(ry) * sqr(x1p)
                        val sb = sqr(rx) * sqr(y1p) + sqr(ry) * sqr(x1p)
                        if (sa < 0.0) sa = 0.0
                        if (sb > 0.0) s  = sqrt(sa / sb)
                        if (fa == fs) s  = -s
                        val cxp = s *  rx * y1p / ry
                        val cyp = s * -ry * x1p / rx

                        // 3) Compute cx,cy from cx',cy'
                        val cx = (x1 + x2) / 2.0 + cosrx * cxp - sinrx * cyp
                        val cy = (y1 + y2) / 2.0 + sinrx * cxp + cosrx * cyp

                        // 4) Calculate theta1, and delta theta.
                        val ux = (x1p - cxp) / rx
                        val uy = (y1p - cyp) / ry
                        val vx = (-x1p - cxp) / rx
                        val vy = (-y1p - cyp) / ry
                        val a1 = vecang(1.0, 0.0, ux, uy)    // Initial angle
                        var da = vecang(ux, uy, vx, vy)        // Delta angle

                        if (fs == 0 && da > 0)  da -= 2 * PI
                        else if (fs == 1 && da < 0) da += 2 * PI

                        // Approximate the arc using cubic spline segments.
                        t[0] = cosrx;  t[1] = sinrx
                        t[2] = -sinrx; t[3] = cosrx
                        t[4] = cx;     t[5] = cy

                        val ndivs = (abs(da) / (PI * 0.5) + 1.0).toInt()
                        val hda = (da / ndivs.toDouble()) / 2.0
                        var kappa = abs(4.0f / 3.0f * (1.0f - cos(hda)) / sin(hda))
                        if (da < 0.0f) kappa = -kappa

                        var ptanx = 0.0;var ptany = 0.0
                        var px = 0.0; var py = 0.0

                        for (i in 0..ndivs) {
                            val a = a1 + da * (i.toDouble() / ndivs.toDouble())
                            dx = cos(a); dy = sin(a)
                            val x = xformPointX(dx * rx, dy * ry, t) // position
                            val y = xformPointY(dx * rx, dy * ry, t) // position
                            val tanx = xformVecX(-dy * rx * kappa, dx * ry * kappa, t) // tangent
                            val tany = xformVecY(-dy * rx * kappa, dx * ry * kappa, t) // tangent

                            if (i > 0) {cubicTo(px + ptanx,py + ptany,x - tanx,y - tany, x, y)}
                            px = x;py = y;ptanx = tanx;ptany = tany
                        }

                        lastX = x2;lastY = y2
                    }
                }
                'Z', 'z' -> close()
                else -> TODO("Unsupported command '$cmd' : Parsed: '${state.path.toSvgPathString()}', Original: '$path'")
            }
            lastCmd = cmd
        }
        warningProcessor?.invoke("Parsed SVG Path: '${state.path.toSvgPathString()}'")
        warningProcessor?.invoke("Original SVG Path: '$path'")
        warningProcessor?.invoke("Points: ${state.path.getPoints()}")
        getBounds(bounds)
    }

    close()

} //End_of_Function( drawPath() )

