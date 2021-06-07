//import Symbol
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
//import utils.drawPath


data class WHD(var w:Double, var h:Double)

fun Stage.renderRawSymbol(symbolHeightOnScreen:Int, symbol:Symbol, strokeSymbolColor: RGBA = Colors.LIGHTGRAY, strokeSymbolThickness:Double = 0.5) :Image =
    image(getNativeFromSymbol(symbolHeightOnScreen, symbol, strokeSymbolColor, strokeSymbolThickness))


fun Stage.renderKey(symbolHeightOnScreen:Int, symbol:Symbol,
                    extraWidth:Int = 16, rx:Int = 8,
                    fillFrameColor:RGBA = Colors.TRANSPARENT_BLACK,
                    strokeFrameColor: RGBA = Colors.LIGHTGRAY,
                    strokeSymbolColor: RGBA = Colors.LIGHTGRAY,
                    strokeFrameThickness:Double = 4.0,
                    strokeSymbolThickness:Double = 0.5) : Container {
    val symbolSize = getWidthHeightFromViewBoxGK(symbol)

    val aspectRatio = if (symbolSize.w > symbolSize.h) symbolSize.w / symbolSize.h else 1.0
    val keyWidth = (symbolHeightOnScreen * aspectRatio+extraWidth).toInt()

    return container{
        val rectFrame = roundRect(keyWidth, symbolHeightOnScreen, rx,
            fill = fillFrameColor,
            stroke = strokeFrameColor,
            strokeThickness =strokeFrameThickness)
        image(getNativeFromSymbol(symbolHeightOnScreen, symbol, strokeSymbolColor,
            strokeSymbolThickness)).centerOn(rectFrame)
    }

}//End_of_Function( Stage.renderKey() )


fun getNativeFromSymbol(symbolHeightOnScreen:Int, symbol:Symbol,strokeColor: RGBA = Colors.BLACK, strokeWidth:Double = 0.5): NativeImage {
    val actualWidth = getWidthHeightFromViewBoxGK(symbol).w
    val actualHeight = getWidthHeightFromViewBoxGK(symbol).h
    val bitmapWidth = (symbolHeightOnScreen*actualWidth/actualHeight).toInt()

    //fun drawPath(w: Int, h:Int, vPWd:Double, vPHt:Double, strokeColor: RGBA, path: Xml, strokeWidth:Double = 0.5, xtranslation:Double = 1.0): NativeImage = NativeImage(w, h).context2d {
    //GK PATCH#1!!
    var fullPath = ""
    for (subPath in symbol.pAr) fullPath += subPath

    return drawPath(bitmapWidth, symbolHeightOnScreen, actualWidth, actualHeight, strokeColor, fullPath, strokeWidth)
}

fun getWidthHeightFromViewBox(viewBox:String): WHD {
    val vB = viewBox.split(' ')  //eg viewBox="-0.75 -0.75 1.5 21.5"
    return if (vB.size == 4) {
        val w = vB[2].toDoubleOrNull()?:0.0
        val h = vB[3].toDoubleOrNull()?:0.0
        WHD(w, h)
    } else WHD(0.0, 0.0)
}


fun getWidthHeightFromViewBoxGK(symbol:Symbol): WHD {
    return WHD(symbol.vB.x1, symbol.vB.y1)
}