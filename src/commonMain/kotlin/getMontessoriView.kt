import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.bitmap.context2d
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korma.geom.vector.LineCap
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.roundRect


enum class MontiShapes {NOUN,  ARTICLE,  ADVERB, VERB, ADJECTIVE,  PREPOSITION, PRONOUN, CONJUNCTION, INTERJECTION, EMOJI,  PUNCTUATION, ERROR}

fun Stage.getMontessoriView(symbolNmSET: SymbolNmSET): Container {
    val SG = symbolNmSET.SG
    val GC = symbolNmSET.GC
    val SYM= symbolNmSET.SYM

    val margin = Dimens.symbolMargin
    val superGroupLinePosition = 0.4
    var shapeStroke       = COLORS.stroke(SG)
    var shapeFill         = COLORS.fill(SG)
    var symbolStrokeColor = COLORS.stroke(SG)

    if (symbolNmSET.isLanguage()) {
        shapeStroke = COLORS.lang(GC)
        shapeFill   = COLORS.lang(GC)
        symbolStrokeColor = if (GC == "PREPOSITIONS") Colors.WHITE else Colors.BLACK
    } else if (SG == "CONCEPTS" && GC == "COLORSGROUP" && SYM != "Colors") { //Todo:  Not very maintainable, best would be Symbol Cartouche side FILL only?
        symbolStrokeColor = symbolNmSET.fColor
        shapeStroke       = symbolNmSET.fColor
    }

    with( symbolNmSET ) {
        val scaledHeight     = (symbolHeight).toDouble()/bitmapSize
        val scaledThickness  = shapeStrokeThickness/aspectRatio

        return container {
            val mShape =  image(NativeImage(shapeWidth, shapeHeight).context2d {
                lineWidth = scaledThickness
                lineCap   = LineCap.ROUND

                when (symShape) {
                    MontiShapes.ARTICLE -> // 1 8 L 5 1 L 9 8 Z
                        stroke(shapeStroke) {scale(bitmapSize);moveTo(0.05, 0.8);lineTo(0.5, 0.1);lineTo(0.95,0.8);close()}
                    MontiShapes.ADVERB -> // 1 8 L 5 1 L 9 8 Z
                        stroke(shapeStroke) { scale(bitmapSize);circle(0.5, 0.5, 0.45) }
                    MontiShapes.ADJECTIVE ->  // M 1 7 L 5 1 L 9 7 Z
                        stroke(shapeStroke) {
                            scale(bitmapSize); moveTo(0.05, 0.7);lineTo(0.5, 0.1);lineTo(
                            0.95,
                            0.7
                        );close()
                        }
                    MontiShapes.PRONOUN ->  //  M 1 9 L 4 1 L 7 9 Z
                    {
                        stroke(shapeStroke) {
                            scale(bitmapSize);moveTo(0.05, 0.8);lineTo(0.4, 0.05);lineTo(
                            0.75,
                            0.8
                        );close()
                        }
                    }
                    MontiShapes.INTERJECTION -> {
                        stroke(shapeStroke) {scale(bitmapSize);moveTo(0.05, 0.8);lineTo(0.35, 0.15);lineTo(0.65,0.8);close();}
                        fill(RGBA(shapeFill));stroke(shapeStroke) { circle(0.35, 0.2, 0.15) }
                    }
                    MontiShapes.CONJUNCTION ->
                        stroke(shapeStroke) { scale(bitmapSize);roundRect(0.05, 0.3, 0.9, 0.4, 0.05, 0.05) }

                    MontiShapes.PUNCTUATION -> //TODO - testing only, likely not used! 04/20
                        stroke(shapeStroke) { scale(bitmapSize);roundRect(0.05, 0.3, 0.9, 0.4, 0.05, 0.05)
                    }

                    MontiShapes.PREPOSITION->
                        stroke(shapeStroke) {
                            scale(bitmapSize);moveTo(0.071, 0.357)
                            cubicTo(0.143, 0.214, 0.286,0.071, 0.500, 0.071);cubicTo(0.714, 0.071, 0.857, 0.214, 0.929, 0.357)
                            cubicTo(0.786, 0.286, 0.643,0.214, 0.500, 0.214);cubicTo(0.357, 0.214, 0.214, 0.286, 0.071, 0.357)
                            close()
                        }

                    // todo - size of circle?
                    MontiShapes.VERB ->
                        stroke(shapeStroke) {
                            scale(bitmapSize)
                            circle(0.5, 0.5, 0.45)
                        }

                    // NOUN = DEFAULT
                    else -> {
                        stroke(shapeStroke) {
                            scale(bitmapSize);roundRect(scaledThickness, scaledThickness, 1.0-scaledThickness*2, scaledHeight-scaledThickness, 0.05, 0.05)
                        }
                        fill(RGBA(shapeFill))
                        //fill( fColor )  //TODO:  Best would be to only fill RIGHT part of the Cartouche, or perhaps to use the color for Stroke?   This causes entire Cartouch to take on the color.

                        lineWidth = 0.5 * scaledThickness
                        // superGroup and symbol Divider
                        stroke(shapeStroke) {moveTo(superGroupLinePosition, scaledThickness);lineTo(superGroupLinePosition, scaledHeight);}
                    }
                }
                if (symShape != MontiShapes.NOUN)  fill(RGBA(shapeFill))

            })

            when (symShape){
                MontiShapes.NOUN -> {
                    val paddingN = ((symbolWidth+margin)* (1-superGroupLinePosition) -
                            symbolHeight * symbolSize.w / symbolSize.h) / 2 + shapeStrokeThickness * symbolHeight

                    image(getNativeFromSymbol(symbolHeight, endSymbol, symbolStrokeColor,
                        strokeSymbolThickness)).alignRightToRightOf(mShape, paddingN).centerYOn(mShape)

                    val superGrpSymbol   = symbolPaths.getSymbolByKeys(symbolNmSET.groupKey)
                    val sGroupSymbolSize = getWidthHeightFromViewBoxGK(superGrpSymbol)

                    val paddingG = ((symbolWidth+margin)* (superGroupLinePosition) -
                            symbolHeight * Dimens.superGroupKeyHeight * sGroupSymbolSize.w / sGroupSymbolSize.h) / 2 +
                            shapeStrokeThickness * symbolHeight * Dimens.superGroupKeyHeight

                    image(getNativeFromSymbol((symbolHeight*Dimens.superGroupKeyHeight).toInt(), superGrpSymbol, symbolStrokeColor,
                        strokeSymbolThickness)).alignLeftToLeftOf(mShape, paddingG).centerYOn(mShape)
                }

                MontiShapes.ARTICLE -> {
                    image(getNativeFromSymbol(symbolHeight, endSymbol, symbolStrokeColor,
                        strokeSymbolThickness)).alignTopToTopOf(mShape).alignLeftToLeftOf(mShape, Dimens.articlePadding)
                }
                else -> {
                    image(getNativeFromSymbol(symbolHeight, endSymbol, symbolStrokeColor,
                        strokeSymbolThickness)).centerOn(mShape)
                }
            }
        }
    } //with symbolNmSET()

}//End_of_Function( printMontiSymbol() )


