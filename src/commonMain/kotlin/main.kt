import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.*
import com.soywiz.korge.input.draggable
import com.soywiz.korge.input.onClick
import com.soywiz.korge.input.onMouseDrag
import com.soywiz.korge.input.onOver
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.bitmap.context2d
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.Colors.BLACK
import com.soywiz.korim.font.BitmapFont
import com.soywiz.korim.font.readBitmapFont
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.vector.LineCap
import com.soywiz.korma.geom.vector.roundRect
import com.soywiz.korui.layout.MathEx.max
import kotlin.math.roundToInt
import kotlin.properties.Delegates

var uiFont: BitmapFont by Delegates.notNull()


//Keyboard MODES
//enum class KeyboardMODES {ADULT_DEMONSTRATE, ADULT_EXPLORE, ADULT_USE_MONTESSORI, CHILD_TEMPLATED, FULL_MONTESSORI, CARTOUCHE, PLAIN_BLISS}


//The "Symbols Library", the source of all information about a Symbol, including (as is) realtime
//Statistical information as will be required for Symbol Set Selections
val symbolPaths    = SymbolPaths()

var gLSentenceBufferOnPANEL = SentenceBufferOnPANEL( Document() )

//These are the Counters for each Panel
private var gColN       = 0;private var gRowN            = 0  //Group Codes
private var sColN       = 0;private var sRowN            = 0  //Symbol Keys
private var gLKeyColN   = 0;private var gLKeyRowN        = 0

private var gLsentencePanelHt= 0
var gLnSentences= 0;


private const val rowOStoSentencePanel =  6   //Offset in Rows to the Sentence Panel
private const val xSentencePanelOS     = 20   //Pixels (Minimum Left Margin)
private const val pixOStoFirstSymbol   = 50

const val XCOMP     =  45
const val YCOMP     = -50

//These are variable, dynamic with each row/col
private var xComp   = XCOMP
private var yComp   = YCOMP

//KEYBOARD SENTENCE CONSTANTS
private const val StndKeyW        = 93   //StndKeyW+10
private const val StndKeyH        = 83
private const val LangKeyW        = 95

private const val MaxCOL           = 18
private const val BegSCOL          = 13  //Begin Symbols Panel ColN
private const val BegKeyCOL        = 1
private const val BegKeyROW        = 1

var KeyXLoc = 0.0;var KeyYLoc      = 0.0   //     var ySLoc = 0.0

private const val WIDTH            = 1920
private const val HEIGHT           = 1360

private const val keyWidthMargin   = 16
private const val keyDim           = 80 // square box size
private const val verbDiameter     = 110 //####
private const val gLkeyRad         = 8

const val gLMaxNSymbolsPerSentence = 11
var gLActiveSentence               = ActiveSentence()
var gLSwapSet: Set<Int>            = setOf(0, 0)

var hooverLine : Text?             = null

//Set the Dimensions for the various kB Frame & Panels  (OUT FRAME ONLY),  GROUP, SYMBOL, SENTENCE
private const val gLOutWd = WIDTH  - 5.0;
private const val gLOutHt = HEIGHT - 5.0;
private const val gLGrpWd = gLOutWd * 0.64;
private const val gLGrpHt = StndKeyH * 7.4 + 10
private const val gLSymWd = gLOutWd - gLGrpWd - 11
private const val gLSymHt = gLGrpHt
private const val gLSenWd = gLOutWd - 18
private const val gLSenHt = HEIGHT - gLGrpHt - 12

private const val glActiveYLoc = HEIGHT * 0.511



class SymbolNmSET(symbolNm: String = "NULL.NULL.NULL") {
	val eleAr   = symbolNm.split(".")
	val SG      = eleAr[0].trim()
	val GC      = eleAr[1].trim()
	val SYM     = eleAr[2].trim()

	var punct   = setPunctuation(SYM)
	var cmmnd   = setCommand(SYM)

	var curXLoc = 0.0;var curYLoc = 0.0   //used in ActiveSentence

	fun isLanguage() = (SG == "LANGUAGE") || (SG == "GROUPS" && GC == "LANGUAGE")
	fun isNoun()     = !isLanguage()
	fun isNotNULL()  = SG != "NULL"

	val fullKey  = "$SG.$GC.$SYM"  //TODO!!  FROM REC  REPLACE WITH KeyForm OR replace keyForm() ???
	val groupKey = "GROUPS.$SG.$GC"  //CAREFUL WITH THIS!!!  - FROM REC

	var symShape = MontiShapes.ERROR

	var fColor           = BLACK;
	var sColor           = BLACK;
	val isStopper        = symbolPaths.isStopper(fullKey)
	var endSymbol   	 = symbolPaths.getSymbolByKeys(fullKey)


	//Montisorri pre-sets, defaults
	val symbolMargin     = Dimens.symbolMargin
	var symbolHeight     = keyDim
	val symbolSize       = getWidthHeightFromViewBoxGK(endSymbol)
	var aspectRatio      = if (symbolSize.w > symbolSize.h) symbolSize.w / symbolSize.h else 1.0
	var symbolWidth      = if ( isNoun() ) ((symbolHeight* aspectRatio) * (1 + Dimens.superGroupWidth)).toInt() else (symbolHeight * aspectRatio).toInt()

	val margin           = Dimens.symbolMargin
	var bitmapSize       = if (symbolHeight > symbolWidth) symbolHeight + margin else symbolWidth + margin
	var shapeWidth       = symbolWidth  + margin
	var shapeHeight      = symbolHeight + margin

	var shapeStrokeThickness  = 0.05
	var strokeSymbolThickness = 0.5


	fun setPunctuation(SYM: String) : PUNCTUATION {
		return when( SYM ) {
			"Space"          -> PUNCTUATION.SPACE
			"Comma"          -> PUNCTUATION.COMMA
			"Ellipse"        -> PUNCTUATION.ELLIPSE
			"ExclamationMark"-> PUNCTUATION.EOS
			"QuestionMark"   -> PUNCTUATION.EOS
			"Period"         -> PUNCTUATION.EOS
			//Note:: Following invisible characters but appended as placeholder to determine the symbol flow.
			"EndOfParagraph" -> PUNCTUATION.EOA  //A)bsatz == Paragraph
			"EndOfPage"      -> PUNCTUATION.EOP   //
			"EndOfDocument"  -> PUNCTUATION.EOD   //LIST (future: Save?) document to console in LongForm (readable in:  document.addSymbol(PERSON().PETS().Cat())   format)
			else             -> PUNCTUATION.ERR
		}
	}

	fun setCommand(SYM: String) : COMMANDS {
		return when( SYM ) {
			"Backspace"      -> COMMANDS.BS
			"ListToConsole1" -> COMMANDS.LTC1
			"ListToConsole2" -> COMMANDS.LTC2
			"ReadFile1"      -> COMMANDS.RF1     //Currently Simulates reading of a file...
			"ReadFile2"      -> COMMANDS.RF2     //Currently Simulates reading of a file...
			"AcceptKey"      -> COMMANDS.ACCEPT  //ACCEPT ACTIVE_SENTENCE INTO DOCUMENT_BUFFER
			"NewBuffer"      -> COMMANDS.NEWBUF  //TEMP, Explicit Buffer Control - for development only!
			"UpArrowSentence"-> COMMANDS.SEN_UP  //Up Arrow - Sentence
			"DnArrowSentence"-> COMMANDS.SEN_DN  //Down Arrow - Sentence
			"ClearAll"       -> COMMANDS.CLRALL  //Clear all -
			else             -> COMMANDS.ERR
		}
	}

	fun setMontessoriSymbol() {
			if( isLanguage() ) {
				fColor  = COLORS.lang(GC)
				sColor  = BLACK

				//NOUN,  ARTICLE,  ADVERB, VERB, ADJECTIVE,  PREPOSITION, PRONOUN, CONJUNCTION, INTERJECTION, EMOJI,  PUNCTUATION

				when (GC) {
					"PRONOUNS"     -> {
 					    shapeHeight = (symbolHeight*Dimens.pronounSize).toInt()
						shapeWidth  = (symbolHeight*Dimens.pronounSize*0.8).toInt()
						bitmapSize  = (symbolHeight * Dimens.pronounSize).toInt()
						symShape    = MontiShapes.PRONOUN
					}
					"ARTICLES"     -> {
						shapeHeight = (symbolHeight*Dimens.articleSize).toInt()
						shapeWidth  = (symbolWidth*Dimens.articleSize).toInt()
						bitmapSize  = (symbolHeight * Dimens.articleSize).toInt()
						symShape    = MontiShapes.ARTICLE}
					"ADVERBS"      -> {
						shapeHeight = (symbolHeight*Dimens.adverbSize).toInt()
						shapeWidth  = (symbolHeight*Dimens.adverbSize).toInt()
						bitmapSize  = (symbolHeight * Dimens.adverbSize).toInt()
						symShape = MontiShapes.ADVERB}
					"VERBS"        -> {symbolHeight = verbDiameter;symShape = MontiShapes.VERB}
					"ADJECTIVES"   -> {
						shapeHeight = (symbolHeight*Dimens.adjectiveSize).toInt()
						shapeWidth  = (symbolHeight*Dimens.adjectiveSize).toInt()
						bitmapSize  = (symbolHeight * Dimens.adjectiveSize).toInt()
						symShape    = MontiShapes.ADJECTIVE}
					"PREPOSITIONS" -> {symbolHeight = 52;symShape = MontiShapes.PREPOSITION}
					"CONJUNCTIONS" -> {symbolHeight = keyDim - 15;symShape = MontiShapes.CONJUNCTION}
					"INTERJECTIONS"-> {
						shapeHeight = (symbolHeight * Dimens.interjectionSize).toInt()
						shapeWidth =  (symbolHeight * Dimens.interjectionSize*0.7).toInt()
						bitmapSize =  (symbolHeight * Dimens.interjectionSize).toInt()
						symShape = MontiShapes.INTERJECTION}
					"EMOJIS"       -> {symbolHeight = 80;symShape = MontiShapes.EMOJI}
					"PUNCTUATION"  -> {symbolHeight = 80;symShape = MontiShapes.PUNCTUATION}
				}//when
			} else { //CARTOUCHE!
				val superGroupNAME = SG
				symShape = MontiShapes.NOUN

				sColor = COLORS.stroke(superGroupNAME);
//				fColor = if (endSymbol.hD.fillColor == BLACK) COLORS.fill(superGroupNAME) else endSymbol.hD.fillColor
				//todo:  used now in getMontessori, not ideal, see note there...  what value is index when not found, protection needed?   Putting into Hd seems best way to deal with maintenance.
				if (endSymbol.hD.fillColor==BLACK) fColor = COLORS.fill( superGroupNAME )
				else {val ndxN = COLORS.COL.indexOf(SYM);fColor = COLORS.appColors[ndxN]}
 				symbolHeight = keyDim + 5
			}

		val scaledHeight = (symbolHeight).toDouble()/bitmapSize
		val scaledThickness = shapeStrokeThickness/aspectRatio

	} //End_of_Function( setSymbol() )

	fun docForm()             = "$SG().$GC().$SYM()"
	fun keyLong()             = "$SG.$GC. <<$SYM>>\t\t  punct: ${punct},  cmmnd: ${cmmnd}"
	fun shortForm()           = "$SYM"
	fun peekForm()            = "$SYM,  (${SG.toLowerCase()}.${GC.toLowerCase()})"
	override fun toString()   = "symbolNmSET  SG: $SG, GC: $GC, SYM: $SYM, Punct: ${punct}, Cmmnd: ${cmmnd}"
	fun docFormat()           = "\t\tdocument.addSymbol(${docForm()})"

	fun isPunct()             = (punct != PUNCTUATION.ERR &&  punct != PUNCTUATION.ELLIPSE && punct != PUNCTUATION.SPACE)
	fun isCmmnd()             = (cmmnd != COMMANDS.ERR)
	fun isSymbol()            = (!isCmmnd() && !isPunct())
	fun isClickable()         = (!SYM.contains("BLANK"))
	fun isPrintable()         = (isSymbol() || (isPunct() && arrayOf(PUNCTUATION.SPACE, PUNCTUATION.COMMA,  PUNCTUATION.ELLIPSE).contains(punct)))

	init {
		setMontessoriSymbol()
	}
}//END_OF_CLASS( SymbolNmSET )


fun keyName(symClass: Symbol) : String {  //From class name, Avoids using "reflection" feature
	var strName  = symClass.toString().replace("SymbolPaths$", "").trim()
	var eleAr    = strName.split("$")
	try {strName = "${eleAr[0]}.${eleAr[1]}.${eleAr[2].split("@")[0]}"} catch (e: IndexOutOfBoundsException) {return ""}
	return strName
}

fun frame(width: Double, height: Double) : NativeImage {  //Special
	return NativeImage(width.toInt(), height.toInt()).context2d {
		lineWidth = 4.0
		lineCap   = LineCap.ROUND
		stroke(COLORS.kbFrame()) {
			roundRect(10, 10, (width-16).toInt(), (height-16).toInt(), 30)
		}
	}
}//End of Function(Frame )

fun activeFrame(width: Double, height: Double) : NativeImage {  //Special
	return NativeImage(width.toInt(), height.toInt()).context2d {
		lineWidth = 2.0
		lineCap   = LineCap.ROUND
		val ndx   = COLORS.COL.indexOf("Red")
		stroke(COLORS.appColors[ndx]) {
			roundRect(10, 10, (width-16).toInt(), (height-16).toInt(), 30)
		}
	}
}//End of Function(Frame )

//Used to position the GROUP Keys
data class ColNRowN(var colN: Int, var rowN: Int)
class ColRow() {
	private var nW = 1;	private var nA = 1;	private var nP = 1;	private var nS = 1;	private var nC = 1;	private var nL = 0;
	fun get(fCh: Char): ColNRowN {
		return when (fCh) {
			'W' -> ColNRowN(1, ++nW)
			'A' -> ColNRowN(3, ++nA)
			'P' -> ColNRowN(5, ++nP)
			'S' -> ColNRowN(7, ++nS)
			'C' -> ColNRowN(9, ++nC)
			'L' -> ColNRowN(++nL, 1)
			else -> ColNRowN(0, 0)
		}
	}
} //END_OF_CLASS( ColNRowN() )

/***************************
 *
 * There are Multiple Buffers involved with storing, loading, saving Symbol combinations (sentences), how do they fit?
 *
 * 1.  ActiveSentence   Edits made here, set sentence Termination type, default? to period.  Saved as punctuation at end, but displayed as a underline...
 *
 * 2.  The keysALL symbol Array in  Document.symbols is the MAIN Store, it does not store such as BS and Scroll Commands.
 *
 * 3.  Upon ACCEPT command some checks to be made, however if all is acceptable the Symbols of the ActiveSentenced are appended to KeysALL.  Then the Document is rebuilt.
 *
 * 4.  SentenceBufferOnPANEL  is yet another view of the symbol store.  Is is built from Document, and keeps a SIMPLE view of sentences to support Scrolling.  It only handles/is responsible for
 *     Accepted Sentences that have been successfully Appended to Document.
 *
 *
 * All these classes are found in the Document Source File.  The Display of the buffers are handled in Main.kt
 *
 *
 * These features generally Supported (user view)
 *
 *     Hoover / peek on all symbols - active & inactive *
 *     Drag & drop (simple exchanges) can be made in the activeSENTENCE area
 *
 */



fun symbolCountReport() {
	var nEleAll = 0;var nNouns = 0;
	println(">symbolCountReport(); >GROUP Counts, Total symbols report:")
	for (SG in symbolPaths.superGroups()) {
		val superGroupNAME   = SG.toString()
		val groupSymbolKeyAr = symbolPaths.groupKeysBySG(superGroupNAME)
		for (groupSymbolKey in groupSymbolKeyAr) {
			val groupCd = groupSymbolKey.split(".")[2]
			val nEle    = symbolPaths.symbolKeysBySGGC(superGroupNAME, groupCd).size
			if (groupSymbolKey.contains("LANGUAGE")) {
				println("\t\t${groupSymbolKey.replace("GROUPS.LANGUAGE.", "")}, \t\tCount: $nEle") //eg GROUPS.SCENES.TRANSPORT_WATER, GROUPS.LANGUAGE.EMOJIS
			} else 	nNouns += nEle
			nEleAll += nEle
		}
	}
	println("\t\tNOUNS, \t\tCount $nNouns")
	println("===============================================")
	println("Total number words: ${nEleAll - 6}\n\n")  //Not exact, SG keys being included? subtracting 6 reconciles total.

}


fun symbolCountReport2() {
	var nEleAll = 0;var nNouns = 0;
	println(">symbolCountReport2(); GROUP Counts, Total symbols report:")
	for (SG in symbolPaths.superGroups()) {
		val superGroupNAME   = SG.toString()

		if ( superGroupNAME == "LANGUAGE" || superGroupNAME == "CONCEPTS") {//TODO:  TEMPORARY EXCLUSION
			continue
		}

		val groupSymbolKeyAr = symbolPaths.groupKeysBySG(superGroupNAME)
		for (groupSymbolKey in groupSymbolKeyAr) {
			val groupCd = groupSymbolKey.split(".")[2]
			println("\tsuperGroupNAME: $superGroupNAME, \tgroupCODE: $groupCd")
			val symbolKeyAr = symbolPaths.symbolKeysBySGGC(superGroupNAME, groupCd)
			for (symbolKeys in symbolKeyAr) {
				val symbolRec  = symbolPaths.getSymbolByKeys(symbolKeys)
				val headerType = symbolRec.hD.type

				println("\t\t$headerType   (symbolKeys = $symbolKeys)")
			}
		}
		println()
	}



}



//=====================================================================================================================

suspend fun main() = Korge(width = WIDTH, height = HEIGHT, bgcolor = Colors["#111111"]) {  //height was 1080,  Sceptre monitor 1360 =^ 7 sentences, 1080 =^ 4 sentences
	uiFont                  = resourcesVfs["fonts/uifont.fnt"].readBitmapFont()
	val document            = Document()
	gLSentenceBufferOnPANEL = SentenceBufferOnPANEL(document)
	val nSymbols            = symbolPaths.nSymbols()
	val sentencePANELwidth  = WIDTH - 10  //Todo - this is an estimation

	var cursorDefaultRange  = 0..0
	var cursorScrollRange   = 0..0

	//println("nSymbols Total = $nSymbols (including Group Codes")
	val colRow       = ColRow()

	gLKeyColN        = BegKeyCOL - 1;gLKeyRowN = BegKeyROW
	//BUILD KEYBOARD FRAME(S)  -  Extra space in y-axis to go to expanding the SentencePANEL
	symbolCountReport()
	symbolCountReport2()

	val kbOutContainer = container {val kbOutFrame = frame(gLOutWd, gLOutHt);image(kbOutFrame).xy(((width - gLOutWd) / 2).toInt(), ((height - gLOutHt) / 2).toInt())}
	val kbGroupFrame   = container {val frame = frame(gLGrpWd, gLGrpHt);image(frame).xy( 10, 10)} //Group Frame
	val kbSymbolFrame  = container {val frame = frame(gLSymWd, gLSymHt);image(frame).xy(gLGrpWd.toInt(), 10)} //Symbols
	fun kbActiveFrame()= container {val frame = activeFrame(gLSenWd-30, gLSenHt/4);image(frame).xy(25.0, gLSenHt-80)}


	println("P2:  width = $width, height = $height, gLSenHt = $gLSenHt, gLGrpHt = $gLGrpHt")
	//P1:  width = 1920.0, height = 1080.0, gLSymHt == gLGrpHt = 624.2
	//P2:  width = 1920.0, height = 1080.0, gLSenHt = 443.8, yLoc = 624.2 nSentences = 7  (in Window to my 7 Sceptre Screen Size)

	gLsentencePanelHt = gLSenHt.roundToInt()
	gLnSentences      = (gLsentencePanelHt / verbDiameter)
	println("P2:  width = $width, height = $height, gLSenHt = $gLSenHt, gLGrpHt = $gLGrpHt,  gLnSentences = $gLnSentences  (in Window)")

	val kbSentenceContainer = container {val frame = frame(gLSenWd, gLSenHt);image(frame).xy(10.0, gLGrpHt)} //Sentences
	fun clearSYMBOLpanel()   {solidRect( gLSymWd-52, gLSymHt-45, Colors["#111111"]).xy(gLGrpWd.toInt() + 18, 30)}
	fun clearSENTENCEpanel() {solidRect( gLSenWd-42, gLSenHt-25, Colors["#111111"]).xy(32, gLGrpHt.toInt() + 16);kbActiveFrame()}
	fun clearActiveSENTENCE(){solidRect( gLSenWd-42, gLSenHt/4, Colors["#111111"]).xy(32, gLGrpHt.toInt() + 16);kbActiveFrame()}

	fun sentenceUnderline(gLSenWdth: Double, rowN: Int) : Container {
		fun sentenceLine(width: Double, height: Double) : NativeImage {  //Special
			return NativeImage(width.toInt(), height.toInt()).context2d {
				lineWidth = 4.0;lineCap = LineCap.ROUND;stroke(Colors.DARKGRAY) {roundRect(10, 10, (width-16).toInt(), (height-16).toInt(), 5)}
			}
		}//End of Function(sentenceLine )
		return container {
			val senLine = sentenceLine(gLSenWdth+45, 20.0);image(senLine).xy((WIDTH - gLSenWdth)/2-40, gLSenHt + rowN * keyDim + 20)
			val xLoc = (WIDTH - gLSenWdth)/2-40
			val lfMark = sentenceLine(40.0, 40.0);image(lfMark).xy(xLoc, gLSenHt + rowN * keyDim)
			val rtMark = sentenceLine(40.0, 40.0);image(rtMark).xy(xLoc+gLSenWdth+5, gLSenHt + rowN * keyDim)
		}
	}


	//=================================================================================================================

	fun hooverPeek(symbolNmSET: SymbolNmSET) {
		if (hooverLine != null) hooverLine.setText("                                                      ")
		hooverLine = text(symbolNmSET.peekForm(), 22.0, Colors.WHITE, uiFont).xy(gLSymWd - 400, gLSymHt - 60).alignRightToRightOf(kbGroupFrame, 30.0)
	}

	fun showActiveSymbolInSentencePANEL(ndxN: Int, curXLoc: Double, curYLoc: Double, symbolNmSET: SymbolNmSET) {
		//println("\t>showActiveSymbol() curXLoc = $curXLoc,curYLoc = $curYLoc,  Word: ${symbolNmSET.SYM}")

		val montessoriView = getMontessoriView(symbolNmSET).xy(curXLoc, curYLoc)

		symbolNmSET.curXLoc =  curXLoc;symbolNmSET.curYLoc =  curYLoc

		montessoriView.draggable{}  //<<<<<<<<<<<<<<<Absolutely incredible!!!
		montessoriView.onMouseDrag {
			val proposedSwapSet = gLActiveSentence.handleDrag(ndxN, montessoriView)

			if (gLSwapSet != proposedSwapSet) {
				gLSwapSet = proposedSwapSet
				gLActiveSentence.hasChanged = true
			}

		}  // x = ${montessoriView.x}, y = ${montessoriView.y}")}

		montessoriView.onOver {	hooverPeek(symbolNmSET)	}

	}//End_of_Function( showActiveSymbolInSentencePANEL() )


	fun showActiveSentence() {

		clearActiveSENTENCE()

		with (gLActiveSentence) {
			var curXLoc = (sentencePANELwidth - accumKeyWidths) / 2.0
			val curYLoc = glActiveYLoc

			for ((ndxN, symbol) in symbolList.withIndex()) {
				showActiveSymbolInSentencePANEL(ndxN, curXLoc, curYLoc, symbol)//%%%
				curXLoc += symbol.shapeWidth + symbol.symbolMargin
			}

			if (gLActiveSentence.isTerminated())   sentenceUnderline(gLActiveSentence.accumKeyWidths, 0)
		}

	} //End_of_Function( showActiveSentence() )


	//==================================================================================================================

	fun showInactiveSymbolInSentencePANEL(ndxN: Int, curXLoc: Double, curYLoc: Double, symbolNmSET: SymbolNmSET) {
		//println("\t>showInactiveSymbol() curXLoc = $curXLoc,curYLoc = $curYLoc,  Word: ${symbolNmSET.SYM}")
		val montessoriView = getMontessoriView(symbolNmSET).xy(curXLoc, curYLoc)
		montessoriView.onOver {	hooverPeek(symbolNmSET)	}
	}//End_of_Function( showInactiveSymbolInSentencePANEL() )

	fun showInactiveSentences(scrollOS: Int = 0) {
		//Generates a new Buffer from scratch - todo?!   really not always necessary!
		clearSENTENCEpanel()
		gLSentenceBufferOnPANEL = SentenceBufferOnPANEL(document)

		val sentenceList = gLSentenceBufferOnPANEL.getFullPanelBuffer()
		val nSentences   = sentenceList.size

		println("in >Main.showInactiveSentences, gLSentenceBufferPANEL nSentences = $nSentences")
		//Inactive 4 rows  so  TOP of document = 0 to 3
		val last  = max(0, nSentences - 1)
		val first = max(0, last - 3)
		cursorDefaultRange = first..last     //This is the Scrolled down such that last line Accepted at the bottom...

		if (scrollOS == 0) {  //If no cursor change signal (== 0) then assuming called on an ACCEPT (?)
			cursorScrollRange = cursorDefaultRange
		} else {
			val testRange = cursorScrollRange.first + scrollOS..cursorScrollRange.last + scrollOS
			if (testRange.first >= 0 && testRange.last < nSentences) {
				cursorScrollRange = testRange
			}
		}

		//TODO   Raise anything that will not change per line to a gL const val?
		val rowDY  = gLSenHt / 6.5            //Incr per inactive sentence
		KeyYLoc    = gLGrpHt + gLSenHt / 3    //BaseY for inactive Sentences

		var relSentenceN = 0
		for (absNdxN in cursorScrollRange) {
			println(">>absNdxN = $absNdxN")
			if (absNdxN < nSentences) {
				with(sentenceList[absNdxN]) {
					KeyXLoc = (sentencePANELwidth - accumKeyWidths) / 2.0

					var curXLoc = KeyXLoc
					val curYLoc = KeyYLoc  + (relSentenceN * rowDY)

					for ((ndxN, symbol) in symbolList.withIndex()) {
						showInactiveSymbolInSentencePANEL(ndxN, curXLoc, curYLoc, symbol)//%%%
						curXLoc += symbol.shapeWidth + symbol.symbolMargin
					}
				}
			}
			relSentenceN += 1
		}
	} //End_of_Function( showInactiveSentences )


	//=================================================================================================================


	fun processSymbolSelected(symbolNmSET: SymbolNmSET) {
		println("\tprocessSymbolSelected(),  Key: symbol Hit =  ${symbolNmSET.fullKey}, command = ${symbolNmSET.cmmnd}")

		if ( symbolNmSET.isPrintable() )	{
			gLActiveSentence.addSymbol(symbolNmSET)
		} else if (symbolNmSET.isCmmnd() ) {
			if (symbolNmSET.cmmnd == COMMANDS.BS) {
				if (gLActiveSentence.isTerminated())
					gLActiveSentence.sentenceType  = SENTENCE_TYPE.UNKNOWN
				else
					gLActiveSentence.removeSymbol()
			} else if (symbolNmSET.cmmnd == COMMANDS.CLRALL) {
				document.handleCommands(COMMANDS.NEWBUF)
				gLActiveSentence = ActiveSentence()
				clearSENTENCEpanel()
			} else if (symbolNmSET.cmmnd == COMMANDS.ACCEPT) {
				//Special Active Sentence control, todo: check for EOS?
				gLActiveSentence.addSymbol(SymbolNmSET("LANGUAGE.PUNCTUATION.EndOfDocument"))   //TEMP - FAKE SYSTEM TO CASCADE STRUCTURES

				//Buffer manipulations
				document.handleCommands(symbolNmSET.cmmnd)
				gLActiveSentence = ActiveSentence()
				clearSENTENCEpanel()
				showActiveSentence()
				if (document.nSentence() > 0) showInactiveSentences()
			} else {
				document.handleCommands(symbolNmSET.cmmnd)
				if ( document.cursorScroll !=0 ) {
					showInactiveSentences(document.cursorScroll)
					document.cursorScroll = 0
				}
			}
		} else if (symbolNmSET.punct == PUNCTUATION.EOS) {
			println("Handling EOS1")
			gLActiveSentence.handleTerminator(symbolNmSET)
		}
		gLActiveSentence.dump()

		showActiveSentence()

	} //End_Of_Function( processSymbolSelected() )


	fun showKeysInSymbolsPANEL(superGroupNAME: String, groupCodeNAME: String) {
		println(">showKeysInSymbolsPANEL(), superGroupNAME = $superGroupNAME, groupCodeNAME = $groupCodeNAME")
		var fColor = BLACK;var sColor = BLACK;

		clearSYMBOLpanel()

		val symbolKeyAr = symbolPaths.symbolKeysBySGGC(superGroupNAME, groupCodeNAME)

		sColN = BegSCOL;sRowN = 1

		for (fullSymbolName in symbolKeyAr) {
			val symbolNmSET = SymbolNmSET(fullSymbolName)
			val endSymbol   = symbolNmSET.endSymbol
			val isStopper   = symbolNmSET.isStopper
			val activeVB    = endSymbol.vB
			val aspectRatio = if (activeVB.x1 > activeVB.y1) activeVB.x1 / activeVB.y1 else 1.0
			val subKeyWidth = (keyDim * aspectRatio + keyWidthMargin).toInt()

			KeyXLoc         = (sColN * StndKeyW + xComp).toDouble()
			KeyYLoc         = (sRowN * StndKeyH + yComp).toDouble()

			if (symbolNmSET.isLanguage()) {
				fColor  = COLORS.lang(symbolNmSET.GC)
				sColor  = BLACK
			}  else {
				sColor  = COLORS.stroke( superGroupNAME );				//if (fColor == BLACK && endSymbol.hD.fillColor!= BLACK) fColor = endSymbol.hD.fillColor
				if (endSymbol.hD.fillColor==BLACK) fColor = COLORS.fill( superGroupNAME )
				else {
					val ndxN = COLORS.COL.indexOf(symbolNmSET.SYM)
					fColor = COLORS.appColors[ndxN]
				}
			}

			if (symbolNmSET.isClickable()) {//Note:  allowing for BLANK Keys (functioning as Spacers)
				val subKey    = roundRect(subKeyWidth, keyDim,  gLkeyRad, gLkeyRad, fill=fColor, stroke=sColor, strokeThickness=4.0).xy(KeyXLoc, KeyYLoc)
				val keySymbol = renderSymbol(keyDim, endSymbol, strokeColor=sColor).centerXOn(subKey).alignBottomToBottomOf(subKey)

				subKey.onClick    { processSymbolSelected(symbolNmSET) }
				keySymbol.onClick { processSymbolSelected(symbolNmSET) }

				subKey.onOver     { hooverPeek(symbolNmSET) }
				keySymbol.onOver  { hooverPeek(symbolNmSET) }
			}

			sColN += 1
			if (sColN > MaxCOL || isStopper) {sRowN += 1;sColN = BegSCOL}
		}

	} //End_of_Function( showKeysInSymbolsPANEL )


	//=======================================DISPLAY  GROUP KEYS========================================================

	for (SG in symbolPaths.superGroups()) {
		val superGroupNAME   = SG.toString()
		val groupSymbolKeyAr = symbolPaths.groupKeysBySG(superGroupNAME)
		var fColor = BLACK;var sColor = BLACK;

		//println("Where superGroupNAME = $superGroupNAME, groupSymbolAr = $groupSymbolKeyAr")

		for (groupSymbolKey in groupSymbolKeyAr) {
			val groupSymbol     = symbolPaths.getSymbolByKeys(groupSymbolKey)
			val groupSymbolNAME = symbolPaths.lastKeySYMBOLNAME() //NOTE - 3RD KEY... WITH GROUPS AS PLACEHOLDER

			//TODO!!  MAKE THESE NAMES CLEARER... What is a CLASS and what is the NAME?
			//println("groupSymbol = $groupSymbol") //This is the CLASS Name ... that toString looks like: SymbolPaths$GROUPS$LANGUAGE$CONJUNCTIONS@44769b4e
			//println("\tgroupSymbolKey = $groupSymbolKey,  groupSymbolNAME = $groupSymbolNAME")
			val symbolNmSET  = SymbolNmSET(groupSymbolKey)
			val isLanguage   =  (symbolNmSET.SG == "LANGUAGE") || (symbolNmSET.SG == "GROUPS" && symbolNmSET.GC == "LANGUAGE")

			val rCN          = colRow.get(superGroupNAME[0])
			val keyW         = if (rCN.rowN == 1) LangKeyW else StndKeyW
			val keyComp      = if (rCN.rowN == 1) -30 else 20
			KeyXLoc          = (rCN.colN * keyW  + XCOMP + keyComp).toDouble()
			KeyYLoc          = (rCN.rowN * StndKeyH + YCOMP).toDouble()

			val activeVB     = groupSymbol.vB
			val aspectRatio  = if (activeVB.x1 > activeVB.y1) activeVB.x1 / activeVB.y1 else 1.0
			val subKeyWidth  = (keyDim * aspectRatio + keyWidthMargin).toInt()

			if (isLanguage) {
				sColor  = BLACK
				fColor  = COLORS.lang(groupSymbolNAME)
			} else {
				sColor  = COLORS.stroke(superGroupNAME)
				fColor  = COLORS.fill(superGroupNAME)
			}

			val subKey       = roundRect(subKeyWidth, keyDim, gLkeyRad, gLkeyRad, fill=fColor, stroke=sColor,strokeThickness=4.0).xy(KeyXLoc, KeyYLoc)
			val subSymbol    = renderSymbol(keyDim, groupSymbol, strokeColor = sColor).centerXOn(subKey).alignBottomToBottomOf(subKey)

			subKey.onClick    { showKeysInSymbolsPANEL(superGroupNAME, groupSymbolNAME) }  //symbolNmRec.fullKey()
			subSymbol.onClick { showKeysInSymbolsPANEL(superGroupNAME, groupSymbolNAME) }  //symbolNmRec.fullKey()

			subKey.onOver     {	hooverPeek(symbolNmSET)	}
			subSymbol.onClick {	hooverPeek(symbolNmSET)	}
		}

	}//End_of_Enclosure:: Build SuperGroups  GK Version

	//===================================Attach a timer to kB Panel to catch drag & drop change indicators and make changes and refresh to screen=================

	val myViewUpdater = kbSentenceContainer.addFixedUpdater(4.timesPerSecond) {
		if (gLActiveSentence.hasChangedCheck()) {
			gLActiveSentence.hasChanged = false
			showActiveSentence()
		}
	}

	kbActiveFrame()

} //End_of_Function( main() )

