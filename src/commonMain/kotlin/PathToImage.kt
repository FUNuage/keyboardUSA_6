import com.soywiz.kds.ListReader
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.bitmap.context2d
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.format.SVG
import com.soywiz.korim.vector.format.SVG.Companion.tokenizePath
import com.soywiz.korio.serialization.xml.Xml
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.sin
import com.soywiz.korma.geom.vector.*
import kotlin.math.*


//NOTES:
//state.path.toSvgPathString() ->  classKey.keysClasses()   at two points
//t replaced with tDblAr
//is This Class to be called per Path?  Take the entire Symbol as a Parameter?

class PathToImage(var classKey: ClassKeys, var symbol: Symbol )  {
    val bounds    = Rectangle()

    private val tDblAr = DoubleArray(6)
    private fun sqr(v: Double) = v * v
    private fun vmag(x: Double, y: Double) = sqrt(x * x + y * y)
    private fun vecrat(ux: Double, uy: Double, vx: Double, vy: Double): Double {
        return (ux * vx + uy * vy) / (vmag(ux, uy) * vmag(vx, vy))
    }
    private fun vecang(ux: Double, uy: Double, vx: Double, vy: Double): Double {
        var r = vecrat(ux, uy, vx, vy)
        if (r < -1.0) r = -1.0
        if (r > 1.0)  r = 1.0
        return (if (ux * vy < uy * vx) -1.0 else 1.0) * acos(r)
    }

    private fun xformPointX(x: Double, y: Double, tDblAr: DoubleArray) = x*tDblAr[0] + y*tDblAr[2] + tDblAr[4]
    private fun xformPointY(x: Double, y: Double, tDblAr: DoubleArray) = x*tDblAr[1] + y*tDblAr[3] + tDblAr[5]
    private fun xformVecX(x: Double, y: Double, tDblAr: DoubleArray) = x*tDblAr[0] + y*tDblAr[2]
    private fun xformVecY(x: Double, y: Double, tDblAr: DoubleArray): Double = x*tDblAr[1] + y*tDblAr[3]

    fun drawPath(w: Int, h:Int, vPWd:Double, vPHt:Double, strokeColor: RGBA, path: Xml, strokeWidth:Double = 0.5, xtranslation:Double = 1.0): NativeImage = NativeImage(w, h).context2d {
        val scale = if (vPHt >vPWd) vPHt else vPWd

        lineWidth = strokeWidth/scale
        val mSize =  if (w >h) w else h
        lineCap   = LineCap.ROUND

        val d = path.str("d")
        val tokens = tokenizePath(d)
        val tl = ListReader(tokens)
        stroke(strokeColor) {
            scale(mSize)
            val warningProcessor: ((message: String) -> Unit)? = null
            // ===== BEGIN PATH =======

            fun dumpTokens() = run { for ((n, token) in tokens.withIndex()) warningProcessor?.invoke("- $n: $token") }
            fun isNextNumber(): Boolean = if (tl.hasMore) tl.peek() is SVG.PathTokenNumber else false

            fun readNumber(): Double {
                while (tl.hasMore) {
                    val token = tl.read()
                    if (token is SVG.PathTokenNumber) {
                        //       println("TOKEN VALUE = ${token.value}")
                        return token.value
                    }
                    warningProcessor?.invoke("Invalid path (expected number but found $token) at ${tl.position - 1}")
                    dumpTokens()
                }
                return 0.0
            }
            fun readAndScaleNumber(xs:Double =0.0, scale: Double): Double {
                while (tl.hasMore) {
                    val token = tl.read()
                    if (token is SVG.PathTokenNumber) {
                        //         println("TOKEN VALUE = ${token.value}")
                        return (token.value + xs) / scale
                    }
                    warningProcessor?.invoke("Invalid path (expected number but found $token) at ${tl.position - 1}")
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
                while (tl.hasMore) {
                    val token = tl.read()
                    if (token is SVG.PathTokenCmd) {return token.id }
                    warningProcessor?.invoke("Invalid path (expected command but found $token) at ${tl.position - 1}")
                    dumpTokens()
                }
                return null
            }

            beginPath()
            moveTo(0.0, 0.0) // Supports relative positioning as first command
            var lastCX = 0.0;var lastCY = 0.0;var lastCmd = '-'

            while (tl.hasMore) {
                val cmd = readNextTokenCmd() ?: break
                val relative = cmd in 'a'..'z' // lower case

                //println("cmd = $cmd, relative = $relative")
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
                        val cx = nX(relative)
                        val cy = nY(relative)
                        val x2 = nX(relative)
                        val y2 = nY(relative)
                        lastCX = cx;lastCY = cy
                        quadTo(cx , cy, x2 , y2)
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
                            val x2 = nX(relative, xtranslation);val y2 = nY(relative)
                            val x = nX(relative, xtranslation);val y = nY(relative)
                            val x1 = if (lastCurve) lastX * 2 - lastCX else lastX
                            val y1 = if (lastCurve) lastY * 2 - lastCY else lastY
                            lastCX = x2; lastCY = y2

                            cubicTo(x1 , y1, x2 , y2, x , y)
                            lastCurve = true
                        }
                    }
                    'T', 't' -> {
                        while (isNextNumber()) {
                            val x2 = nX(relative,xtranslation)
                            val y2 = nY(relative)
                            val cx = if (lastCurve) lastX * 2 - lastCX else lastX
                            val cy = if (lastCurve) lastY * 2 - lastCY else lastY
                            lastCX = cx;lastCY = cy
                            quadTo(cx , cy, x2 , y2)
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
                        val x1 = lastX;val y1 = lastY        // start point;  end point
                        val x2 = nX(relative);val y2 = nY(relative)
                        var dx = x1 - x2;var dy = y1 - y2

                        val d = hypot(dx, dy)
                        if (d < 1e-6f || rx < 1e-6f || ry < 1e-6f) {
                            // The arc degenerates to a line
                            lineTo(x2, y2)
                        } else {
                            val sinrx = sin(rotx);  val cosrx = cos(rotx)

                            val x1p = cosrx * dx / 2.0f + sinrx * dy / 2.0f
                            val y1p = -sinrx * dx / 2.0f + cosrx * dy / 2.0f
                            var d = sqr(x1p) / sqr(rx) + sqr(y1p) / sqr(ry)
                            if (d > 1) {d = sqr(d);rx *= d;ry *= d }
                            // 2) Compute cx', cy'
                            var s = 0.0
                            var sa = sqr(rx) * sqr(ry) - sqr(rx) * sqr(y1p) - sqr(ry) * sqr(x1p)
                            val sb = sqr(rx) * sqr(y1p) + sqr(ry) * sqr(x1p)
                            if (sa < 0.0) sa = 0.0
                            if (sb > 0.0)  s = sqrt(sa / sb)
                            if (fa == fs) s = -s
                            val cxp = s * rx * y1p / ry
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

                            if (fs == 0 && da > 0)      da -= 2 * PI
                            else if (fs == 1 && da < 0) da += 2 * PI

                            // Approximate the arc using cubic spline segments.
                            tDblAr[0] = cosrx;  tDblAr[1] = sinrx;tDblAr[2] = -sinrx; tDblAr[3] = cosrx;tDblAr[4] = cx; tDblAr[5] = cy

                            val ndivs = (abs(da) / (PI * 0.5) + 1.0).toInt()
                            val hda = (da / ndivs.toDouble()) / 2.0
                            var kappa = abs(4.0f / 3.0f * (1.0f - cos(hda)) / sin(hda))
                            if (da < 0.0f) kappa = -kappa

                            var ptanx = 0.0;var ptany = 0.0
                            var px = 0.0; var py = 0.0

                            for (i in 0..ndivs) {
                                val a = a1 + da * (i.toDouble() / ndivs.toDouble())
                                dx = cos(a); dy = sin(a)
                                val x    = xformPointX(dx * rx, dy * ry, tDblAr) // position
                                val y    = xformPointY(dx * rx, dy * ry, tDblAr) // position
                                val tanx = xformVecX(-dy * rx * kappa, dx * ry * kappa, tDblAr) // tangent
                                val tany = xformVecY(-dy * rx * kappa, dx * ry * kappa, tDblAr) // tangent

                                if (i > 0) {cubicTo(px + ptanx,py + ptany, x - tanx, y - tany, x, y)}
                                px = x;py = y;ptanx = tanx;ptany = tany
                            }
                            lastX = x2;lastY = y2;
                        }
                    }
                    'Z', 'z' -> close()
                    else -> TODO("Unsupported command '$cmd' : Parsed: '${classKey.keysClasses()}', Original: '$d'")
                }
                lastCmd = cmd
            }
            warningProcessor?.invoke("Parsed SVG Path: '${classKey.keysClasses()}'")
            warningProcessor?.invoke("Original SVG Path: '$d'")
            warningProcessor?.invoke("Points: ${state.path.getPoints()}")
            getBounds(bounds)
        }
        close()
    } //End_of_Function( drawPath() )

} //END_OF_CLASS( PathToImage )