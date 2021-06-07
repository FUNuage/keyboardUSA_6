import com.soywiz.korge.*
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Stage
import com.soywiz.korui.layout.MathEx.max


/********************************
 *
 * Data is stored into a Single simple Array of SymbolNames (full 3 parts).   This way such editing commands as "BackSpace can be easily handled.
 * The structure of Pages, Paragraphs, and Sentences is only generated as needed, which for reasonably small documents seems completely workable
 * and avoids the issues how to deal with such as BackSpaces when there are no symbols in the current Page and such situations.
 *
 * The decision to output the full 3 key levels as text can easily be changed, however a "Viewer App" seems to be the way to deal with reading files
 * that have been generated at other sources.
 */


enum class PUNCTUATION{SPACE, COMMA, ELLIPSE, EOS, EOA, EOP, EOD, ERR}
enum class SENTENCE_TYPE{PERIOD, EXCLAMATION, QUESTION, UNKNOWN}
enum class COMMANDS{BS, SEN_UP, SEN_DN, PAG_UP, PAG_DN, RF1, RF2, ACCEPT, NEWBUF, LTC1, LTC2, CLRALL, ERR}//UPA, DNA, listToConsole, ReadFile    UPARROW, DNARROW

//=====================================================================================================================

/**********************************
 *
 * The SentenceBufferOnPANEL object stores the set of all SENTENCES...
 * COMMAND KEYS cause the Sentence Cursor to be modified.
 * the getBuffer() function loads up only those sentences that can be displayed in the PANEL
 *
 */


//Todo, placeholder error handling...
fun beep(mssg: String) {println("beep! mssg: $mssg")}



class SentenceBufferOnPANEL(var document: Document) {  //Buffer unit, all sentences in document.  Edits made into buffer with no changes to document, then upon an accept.
    val panelNSenOS     = 3    //gLnSentences - 1     Todo   TEMP?!!!
    var noChangeMade    = true
    var sentenceList    = mutableListOf<Sentence>()
    var curTopSentenceN = 0
    var curBotSentenceN = panelNSenOS

    fun nSentence()     = sentenceList.size
    fun hasSentence()   = sentenceList.size > 0

    fun sentenceCursorUP() {
        document.cursorScroll = -1
        //if (sentenceList.size <= panelNSenOS) {beep(">CursorUP, Insufficient lines to scroll");return}
        //if (curBotSentenceN < sentenceList.size-1) {
        //    curBotSentenceN += 1
        //    document.cursorScroll = 1
        //} else beep("CursorUP, NOT true that: curBotSentenceN < sentenceList.size-1")
        //curTopSentenceN = curBotSentenceN - panelNSenOS
    }

    fun sentenceCursorDN() {
        document.cursorScroll = 1
        //if (sentenceList.size <= panelNSenOS) {beep(">CursorDN, Insufficient lines to scroll");return}
        //if (curTopSentenceN  > 0) {
        //    curTopSentenceN -= 1
        //    document.cursorScroll = -1
        //} else beep("CursorDN, NOT true that: (curTopSentenceN  > 0)")
        //curBotSentenceN = curTopSentenceN + panelNSenOS
    }

    fun setSentenceParams(sentenceN: Int, sentence: Sentence) {   //We are doing this once, upon loading into buffer, rather than per scroll...
        sentence.sentenceN = sentenceN;
        sentence.setParams()
        //println("accumKeyWidths = ${sentence.accumKeyWidths}")
    }

    //    fun getPanelBuffer() : MutableList<Sentence> {
    //        val maxNdx = nSentence()
    //        val bufferList  = mutableListOf<Sentence>()
    //        for (ndx in curTopSentenceN..curBotSentenceN) {
    //            if (ndx < maxNdx) bufferList.add(sentenceList[ndx])
    //        }
    //        return bufferList
    //    }

    fun getFullPanelBuffer() : MutableList<Sentence> {
        val bufferList  = mutableListOf<Sentence>()
        for (sentence in sentenceList) bufferList.add(sentence)
        return bufferList
    }

    fun list() {
        println("\t>SentenceBufferOnPANEL(), nSentences = ${nSentence()}")
        for ((ndx, sentence) in sentenceList.withIndex()) println("\t\t${ndx + 1}). ${sentence.fmtPlain()}")
    }

    fun addSentence(sentence: Sentence) {
        if (sentence.hasSymbol()) {sentenceList.add(sentence);noChangeMade = false}
    }

    private fun loadSentenceBuffer() {
        println(">SentenceBufferOnPANEL.loadSentenceBuffer")
        var sentenceN = 0
        for (page in document.pageList) {
            for (paragraph in page.paragraphList) {
                for (sentence in paragraph.sentenceList) {
                    if (sentence.hasSymbol()) {setSentenceParams(sentenceN, sentence);addSentence(sentence);sentenceN += 1}
                }
            }
        }
        println("<SentenceBufferOnPANEL.loadSentenceBuffer, nSentence = ${nSentence()}")
    }

    init{
        loadSentenceBuffer()
    }
}//END_OF_CLASS( SentenceBufferOnPANEL() )

//=====================================================================================================================

open class Sentence() {    //}: Container() {
    var sentenceN      = 0
    var accumKeyWidths = 0.0
    var accumMaxHeight = 0
    var sentenceType   = SENTENCE_TYPE.UNKNOWN
    var symbolList     = mutableListOf<SymbolNmSET>()
    var hasChanged     = false


    open fun addSymbol(symbolNmSET: SymbolNmSET) {symbolList.add(symbolNmSET)}

    fun nSymbols() = symbolList.size

    open fun clear() {symbolList.clear()}

    fun hasChangedCheck()   = hasChanged //TODO Will require logic involving Commands/printable() and so on, I think.  Default to true for now...

    fun isTerminated() = sentenceType != SENTENCE_TYPE.UNKNOWN

    fun handleTerminator(symbolNmSET: SymbolNmSET) {
        println("Handling EOS2")
        when (symbolNmSET.SYM ) {
            "Period"          -> {sentenceType = SENTENCE_TYPE.PERIOD;println("Handling EOS2A")}
            "QuestionMark"    -> {sentenceType = SENTENCE_TYPE.QUESTION;println("Handling EOS2B")}
            "ExclamationMark" -> {sentenceType = SENTENCE_TYPE.EXCLAMATION;println("Handling EOS2C")}
        }
    }

    fun fmtPlain() : String {
        var txt = "";var tok = ""
        for (symbolNm in symbolList) {
            when (symbolNm.SYM ) {   //TODO - when should best be based on SENTENCE_TYPE?
                "Period"          -> {tok = ".";sentenceType = SENTENCE_TYPE.PERIOD}
                "QuestionMark"    -> {tok = "?";sentenceType = SENTENCE_TYPE.QUESTION}
                "ExclamationMark" -> {tok = "!";sentenceType = SENTENCE_TYPE.EXCLAMATION}
                else -> " " + symbolNm.SYM
            }
            txt += "${tok}"
        }
        return txt.trim() + "   width: $accumKeyWidths"
    }

    fun setParams() { //For Montessori Mode
        accumKeyWidths = 0.0;accumMaxHeight = 0
        for (symbol in symbolList) {
            accumKeyWidths += symbol.shapeWidth + symbol.symbolMargin
            accumMaxHeight  = max(accumMaxHeight, symbol.shapeHeight)
        }
    }

    fun keyForm() : String {
        var txt = "";for (symbolNm in symbolList) txt += "${symbolNm.fullKey} "
        txt.trim();if(symbolList.size > 1)txt += ".";return txt
    }

    fun hasSymbol() : Boolean {
        for (symbolNmSet in symbolList) if (symbolNmSet.isSymbol()) return true
        return false
    }
    fun listAllToFileFormat( sentenceN: Int, nSentence: Int) {
        if ( hasSymbol() ) {
            println("\t\t\tDOCUMENT.FORMAT.New_Sentence ${sentenceN+1} of $nSentence, nSymbols: ${symbolList.size} ")
            for (symbolNm in symbolList) {
                println("\t\t\t\t${symbolNm.fullKey}")  //keyForm = "$SG.$GC.$SYM"
            }
        }
    }

    fun saveToFile( sentenceN: Int, nSentence: Int) {
        if ( hasSymbol() ) {
            var text = ""
            var punct = when (sentenceType) {
                SENTENCE_TYPE.PERIOD      -> "."
                SENTENCE_TYPE.QUESTION    -> "?"
                SENTENCE_TYPE.EXCLAMATION -> "!"
                else -> "u"
            }
            for (symbolNm in symbolList) text += "${symbolNm.fullKey} "
            text = "\t${punct} $text${punct}"
            println(text)
        }
    }
} //END_OF_CLASS( Sentence )


class ActiveSentence(var symbolNmSET: SymbolNmSET = SymbolNmSET()) : Sentence() {

    fun swapItems(itemFrom: Int, itemTo: Int)  : Set<Int> {
        //0 1 2 3 4 5
        //A B C D E F
        //we want 2 -> 3, & 3 -> 2  so that: A B C D E F -> A B D C E F    is the result, so itemFrom == 2, && itemTo == 3
        val itemSaved        = symbolList[itemFrom]  //we save C
        symbolList[itemFrom] = symbolList[itemTo]    //we make C become D
        symbolList[itemTo]   = itemSaved             //we make D become C
        hasChanged           = true
        return setOf(itemFrom, itemTo)
    }

    fun handleDrag(ndxN: Int, montessoriView: Container) : Set<Int>{
        val isOverBorderSymbolLf = ndxN > 0  && montessoriView.x < symbolList[ndxN-1].curXLoc
        val isOverBorderSymbolRt = ndxN < (nSymbols()-1) && montessoriView.x > symbolList[ndxN+1].curXLoc
        var swapSet : Set<Int>   = setOf(0, 0)

        if (isOverBorderSymbolLf) {
            swapSet  = setOf(ndxN-1, ndxN)
            if (gLSwapSet != swapSet) swapItems(ndxN-1, ndxN)
        } else if (isOverBorderSymbolRt) {
            swapSet = setOf(ndxN, ndxN+1)
            if (gLSwapSet != swapSet) swapItems(ndxN, ndxN+1)
        }

        return swapSet
    }

    override fun addSymbol(symbolNmSET: SymbolNmSET) {
        if (nSymbols() < gLMaxNSymbolsPerSentence) {
            var newTerminated = false
            if (symbolNmSET.punct == PUNCTUATION.EOS) {
                sentenceType = when (symbolNmSET.SYM) {
                    "Period" -> SENTENCE_TYPE.PERIOD
                    "QuestionMark" -> SENTENCE_TYPE.QUESTION
                    "ExclamationMark" -> SENTENCE_TYPE.EXCLAMATION
                    else -> SENTENCE_TYPE.UNKNOWN
                }
                newTerminated = (sentenceType != SENTENCE_TYPE.UNKNOWN)
            }
            symbolList.add(symbolNmSET)
            setParams()

            if (newTerminated) {
                println("1.  Last character a EOS symbol, sentenceType = $sentenceType")
            }
        } else {
            beep("Attempting to enter more than the Max Number of Symbols per line = ${gLMaxNSymbolsPerSentence}")
        }
    }

    fun removeSymbol() {
        val nSymbol = nSymbols()
        if (nSymbol>0) symbolList.removeAt(nSymbol-1)
        setParams()
    }

    fun dump() {
        if ( hasSymbol() ) {
            println("\n\n==========================================================")
            println("\n\t\t\tActiveSentence.dump(), nSymbols = ${nSymbols()} ")
            for (symbolNm in symbolList) {
                println("\t\t\t\t${symbolNm.fullKey}")  //keyForm = "$SG.$GC.$SYM"
            }
            println("gLActiveSentence.sentenceType   =  ${gLActiveSentence.sentenceType}   (Terminator type)")
            println("accumKeyWidths = $accumKeyWidths,  accumMaxHeight = $accumMaxHeight")
            println("==========================================================")
            println("\n\n")
        }
    }

    init{
        if ( symbolNmSET.isNotNULL() ) addSymbol(symbolNmSET)
    }
}


class Paragraph(var paragraphN: Int) {
    var sentenceList  = mutableListOf<Sentence>()
    fun nSentence()   = sentenceList.size
    fun hasSentence() = sentenceList.isNotEmpty()
    fun clear() {sentenceList.clear()}
    fun addSentence(sentence: Sentence)  {sentenceList.add(sentence);}
    fun listAllToFileFormat( paragraphN: Int, nParagraph: Int) {
        println("\t\tDOCUMENT.FORMAT.New_Paragraph ${paragraphN+1} of $nParagraph")
        if (hasSentence()) {
            for ((ndx, sentence) in sentenceList.withIndex()) {
                sentence.listAllToFileFormat(ndx, sentenceList.size)
            }
        }
    }
    fun saveToFile( paragraphN: Int, nParagraph: Int) {
        if (hasSentence()) {
            for ((ndx, sentence) in sentenceList.withIndex()) {
                sentence.saveToFile(ndx, sentenceList.size)
            }
            //println("EOA ${paragraphN+1} of $nParagraph")
        }
    }
}//END_OF_CLASS( Paragraph )

class Page( var pageN: Int) {
    var pageTitle      = ""
    var paragraphList  = mutableListOf<Paragraph>()
    fun hasParagraph() = paragraphList.isNotEmpty()
    fun clear() {paragraphList.clear()}
    fun nSentence() : Int  {var nSentence = 0;for (paragraph in paragraphList) nSentence += paragraph.nSentence();return nSentence}
    fun addParagraph(paragraph: Paragraph) {paragraphList.add(paragraph);}
    fun listAllToFileFormat(pageN: Int, nPage: Int) {
        if ( hasParagraph() ) {
            println("\tDOCUMENT.FORMAT.New_Page  ${pageN+1} of $nPage")
            for ((ndx, paragraph) in paragraphList.withIndex()) {
                paragraph.listAllToFileFormat(ndx, paragraphList.size)
            }
        }
    }
    fun saveToFile(pageN: Int, nPage: Int) {
        if ( hasParagraph() ) {
            for ((ndx, paragraph) in paragraphList.withIndex()) {
                paragraph.saveToFile(ndx, paragraphList.size)
            }
            println("EOP ${pageN+1} of $nPage")
        }
    }
} //END_OF_CLASS( Page )


//All interface should be to Document except Word
class Document() {
    val symbols            = Symbols(this)
    var pageList           = mutableListOf<Page>()
    var cursorScroll       = 0

    var docTitle : String  = ""
    var docAuthor :String  = ""
    var docDateStamp       = ""

    var PUNCT = PUNCTUATION.ERR
    var CMMND = COMMANDS.ERR


    fun nSentence() : Int {
        var nSentence = 0;
        for (page in pageList) nSentence += page.nSentence()
        return nSentence
    }

    fun hasSymbols() = symbols.hasSymbols()


    fun addPage(page: Page) {pageList.add(page)}


    class Symbols(var document: Document) {
        private var keysALL     = mutableListOf<SymbolNmSET>()
        private var curSentenceN= 0;private var curParagraphN = 0;private var curPageN = 0
        private var curPage     = Page( 0 )
        private var curParagraph= Paragraph( 0 )
        private var curSentence = Sentence()

        fun hasSymbols() = keysALL.size > 0

        fun reset() {
            curSentenceN = 0
            curSentence.clear()
            curParagraph.clear()
            curPage.clear()
            document.pageList.clear()
        }

        fun resetAll() {
            println(">Symbols.resetALL(), clearing all session data..")
            reset()
            keysALL.clear()
        }

        private fun eOS() {curParagraph.addSentence(curSentence);curSentence = Sentence();curSentenceN += 1;}
        private fun eOA() {eOS();if (curParagraph.hasSentence()) {curPage.addParagraph(curParagraph);curParagraph = Paragraph(curParagraphN)};curParagraphN += 1;}
        private fun eOP() {eOA();if (curPage.hasParagraph()) {document.addPage(curPage);curPage = Page(curPageN);curPageN += 1;}}
        private fun eOD() {eOP()} //for (key in keysALL) {println("${key.docFormat()}")

        fun reprocessALL() {
            var lastKey = SymbolNmSET()
            println("\t>Symbols.reprocessALL(), nKeys = ${keysALL.size}, sentenceCount = ${document.nSentence()}")
            reset()

            for (key in keysALL) {
                if ( key.isSymbol() ) curSentence.addSymbol(key)
                else if (key.isPunct()) {  //PUNCTUATION{SPACE, COMMA, EXCLMRK, EOS, EOA, EOP, ERR}
                    when (key.punct) {
                        PUNCTUATION.EOS -> {curSentence.addSymbol(key);eOS()}  //Period || ExclamationMark || QuestionMark
                        PUNCTUATION.EOA -> {curSentence.addSymbol(key);eOA()}
                        PUNCTUATION.EOP -> {curSentence.addSymbol(key);eOP()}
                        PUNCTUATION.EOD -> {eOD()}
                        PUNCTUATION.ELLIPSE, PUNCTUATION.COMMA -> curSentence.addSymbol(key)
                    }
                }
                lastKey = key
            }//for

            if ( lastKey.isSymbol() ) {curParagraph.addSentence(curSentence);} //Add partial sentence (last symbols) to document

            var sentenceCount = document.nSentence()  //DOES NOT COUNT PARTIALS - AS FOR LAST PAGE... IF NO EOP FOR LAST PAGE


            println("<reprocessALL, after processing sentenceCount = $sentenceCount")

        }//End_of_Function( reprocessALL() )

        // Interface functions ===========================

        fun appendSymbol(symbolNmSET: SymbolNmSET){
            println("\t>Document.appendSymbol()\t\t${symbolNmSET.fullKey}")
            keysALL.add(symbolNmSET)
        }

        fun ltc1() { //TEMP
            println("====================================================================================\n")
            println("\n\n>Document.listAllToFileFormat()...")
            println("====================================================================================\n")
            if (document.docTitle.isNotEmpty())  println("Document.Heading.Title:  ${document.docTitle}")
            if (document.docAuthor.isNotEmpty()) println("Document.Heading.Author: ${document.docAuthor}")
            println("Document.Heading.nPages: ${document.pageList.size}")
            if ( document.pageList.isNotEmpty() ) {
                for ((ndx, page) in document.pageList.withIndex()) {
                    page.listAllToFileFormat(ndx, document.pageList.size)
                }
            }
            println("\n\n\n\n")
        }

        fun ltc2() { //TEMP - format for reuse to Reader!
            if ( document.pageList.isNotEmpty() ) {
                for (page in document.pageList) {
                    for (paragraph in page.paragraphList) {
                        for (sentence in paragraph.sentenceList) {
                            for (symbol in sentence.symbolList) {
                                symbol.docFormat()
                            }
                        }
                    }
                }
            }
        }

        fun saveToFile() {
            if (document.docTitle.isNotEmpty())  println("Document.Heading.Title:  ${document.docTitle}")
            if (document.docAuthor.isNotEmpty()) println("Document.Heading.Author: ${document.docAuthor}")
            if ( document.pageList.isNotEmpty() ) {
                for ((ndx, page) in document.pageList.withIndex()) {
                    page.saveToFile(ndx, document.pageList.size)
                }
            }

        }

    } //END_OF_CLASS( Symbols() )

    //========================================================================================


    //document.
    fun handleCommands(cmmnd: COMMANDS) {
        println("\t>DOCUMENT.>>>>HANDLE COMMANDS<<<(), cmmnd = $cmmnd")
        when ( cmmnd ) {
            COMMANDS.BS      ->  {}
            //SENTENCE
            COMMANDS.SEN_UP  ->  {gLSentenceBufferOnPANEL.sentenceCursorUP()}
            COMMANDS.SEN_DN  ->  {gLSentenceBufferOnPANEL.sentenceCursorDN()}
            COMMANDS.ACCEPT  -> {
                if (!gLActiveSentence.isTerminated())  {
                    println("Funya agin Kunte!  Auto terminating to period by default...  0427A")    //TODO - NOT WORKING,  CONFUSED WITH UNDERLINE...
                    gLActiveSentence.addSymbol(SymbolNmSET( "LANGUAGE.PUNCTUATION.Period"))
                }
                for (symbolSet in gLActiveSentence.symbolList)
                    symbols.appendSymbol(symbolSet)
                symbols.reprocessALL()
            }
            COMMANDS.RF1     ->  {readDocument1( this);gLSentenceBufferOnPANEL = SentenceBufferOnPANEL(this);gLSentenceBufferOnPANEL.list() }
            COMMANDS.RF2     ->  {readDocument2( this);gLSentenceBufferOnPANEL = SentenceBufferOnPANEL(this);gLSentenceBufferOnPANEL.list() }
            COMMANDS.LTC1    ->  {symbols.reprocessALL();symbols.ltc1()}
            COMMANDS.LTC2    ->  {symbols.reprocessALL();symbols.saveToFile()} //ltc2
            COMMANDS.CLRALL  ->  {symbols.resetAll();gLSentenceBufferOnPANEL = SentenceBufferOnPANEL(this);}  //Complete reset
            COMMANDS.NEWBUF  ->  {gLSentenceBufferOnPANEL = SentenceBufferOnPANEL(this);gLSentenceBufferOnPANEL.list()}
            else -> {println("\t\tError, unhandled command!")}
        }
    }

    //================================================================================
    fun allKeys(symbolNmSET: SymbolNmSET) {    //<<<<<<<<<<<<<<<<<GENERAL INPUT POINT....
        if (symbolNmSET.isCmmnd()) {
            handleCommands(symbolNmSET.cmmnd);
        } else {
            //println(">documents.allKeys(), symbolNmSET: ${symbolNmSET.shortForm()}")
            symbols.appendSymbol(symbolNmSET)
        }
    }

    fun addSymbol(symbol: Symbol) { //Used to read documents at this time
        allKeys( SymbolNmSET( keyName(symbol) ) )
    }

    //===================================================================================

    fun setDocumentTitle(title: String)   {docTitle = title	}
    fun setDocumentAuthor(author: String) {docAuthor = author}

}//END_OF_CLASS( Document() )


fun readDocument1(document: Document) {
    with( symbolPaths ) {
        document.setDocumentTitle("Who Am I?  - The Amazing Story of Fast Little Yellow Bird")
        document.setDocumentAuthor("G. Koller")
        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(LANGUAGE().ADVERBS().Not())
        document.addSymbol(PERSON().PETS().Cat())
        document.addSymbol(LANGUAGE().CONJUNCTIONS().And())
        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(LANGUAGE().ADVERBS().Not())
        document.addSymbol(PERSON().PETS().Dog())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())  //2
        document.addSymbol(LANGUAGE().PUNCTUATION().EndOfParagraph())  //PARAGRAPH

        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Have())
        document.addSymbol(PERSON().FACE().Eye())
        document.addSymbol(LANGUAGE().CONJUNCTIONS().And())
        document.addSymbol(PERSON().FACE().Ear())
        document.addSymbol(LANGUAGE().CONJUNCTIONS().And())
        document.addSymbol(PERSON().FACE().Nose());
        document.addSymbol(LANGUAGE().PUNCTUATION().Period()) //3

        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(LANGUAGE().ADVERBS().Not())
        document.addSymbol(LANGUAGE().ADJECTIVES().Big())
        document.addSymbol(ANIMATE().ANIMAL_TYPES().Insect())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())//4
        document.addSymbol(LANGUAGE().PUNCTUATION().EndOfParagraph())  //PARAGRAPH

        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Have())
        document.addSymbol(PERSON().BODY_PARTS().Bone())
        document.addSymbol(LANGUAGE().CONJUNCTIONS().And())
        document.addSymbol(PERSON().BODY_PARTS().Leg())
        document.addSymbol(LANGUAGE().CONJUNCTIONS().And())
        document.addSymbol(PERSON().BODY_PARTS().Heart())
        document.addSymbol(LANGUAGE().CONJUNCTIONS().But())
        document.addSymbol(LANGUAGE().ADVERBS().Not())
        document.addSymbol(PERSON().BODY_PARTS().Hand())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())//5

        document.addSymbol(LANGUAGE().PRONOUNS().You())
        document.addSymbol(LANGUAGE().VERBS().Jump())
        document.addSymbol(LANGUAGE().PREPOSITIONS().Above())
        document.addSymbol(ANIMATE().PLANTS().Grass())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())  //6

        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(LANGUAGE().ADVERBS().Not())
        document.addSymbol(LANGUAGE().ADJECTIVES().Big())
        document.addSymbol(ANIMATE().ANIMAL_TYPES().Insect())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())//7

        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(LANGUAGE().ADVERBS().Not())
        document.addSymbol(LANGUAGE().ADJECTIVES().Big())
        document.addSymbol(ANIMATE().ANIMAL_TYPES().Worm())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())
        document.addSymbol(LANGUAGE().PUNCTUATION().EndOfParagraph())//8  //PARAGRAPH
        document.addSymbol(LANGUAGE().PUNCTUATION().EndOfPage())          //PAGE 1

        document.addSymbol(LANGUAGE().PRONOUNS().You())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(LANGUAGE().ADJECTIVES().Big())
        document.addSymbol(LANGUAGE().ADJECTIVES().Wide())
        document.addSymbol(ANIMATE().ANIMAL_TYPES().Rabbit())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())

        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(LANGUAGE().ADJECTIVES().Little())
        document.addSymbol(CONCEPTS().COLORSGROUP().Yellow())
        document.addSymbol(ANIMATE().ANIMAL_TYPES().Bird())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())

        document.addSymbol(LANGUAGE().PRONOUNS().Those())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(CONCEPTS().ENUMERATION().Many())
        document.addSymbol(LANGUAGE().ADJECTIVES().Big())
        document.addSymbol(CONCEPTS().COLORSGROUP().Green())
        document.addSymbol(ANIMATE().ANIMAL_TYPES().Frog())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())

        document.addSymbol(LANGUAGE().PRONOUNS().Those())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(CONCEPTS().ENUMERATION().Five())
        document.addSymbol(CONCEPTS().COLORSGROUP().Green())
        document.addSymbol(ANIMATE().ANIMAL_TYPES().Frog())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())
        document.addSymbol(LANGUAGE().PUNCTUATION().EndOfParagraph())  //PARAGRAPH

        document.addSymbol(LANGUAGE().PRONOUNS().You())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(LANGUAGE().ADJECTIVES().Wide())
        document.addSymbol(LANGUAGE().ADJECTIVES().Wide())
        document.addSymbol(ANIMATE().ANIMAL_TYPES().Rabbit())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())

        document.addSymbol(LANGUAGE().PRONOUNS().You())
        document.addSymbol(LANGUAGE().VERBS().Can())
        document.addSymbol(LANGUAGE().ADVERBS().Not())
        document.addSymbol(LANGUAGE().VERBS().Jump())
        document.addSymbol(LANGUAGE().PREPOSITIONS().Above())
        document.addSymbol(LANGUAGE().ADJECTIVES().Fast())
        document.addSymbol(LANGUAGE().ADJECTIVES().Wide())
        document.addSymbol(WORLD().GEOLOGY().River())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())
        document.addSymbol(LANGUAGE().PUNCTUATION().EndOfPage())

        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(LANGUAGE().ADJECTIVES().Fast())
        document.addSymbol(LANGUAGE().ADJECTIVES().Little())
        document.addSymbol(CONCEPTS().COLORSGROUP().Yellow())
        document.addSymbol(ANIMATE().ANIMAL_TYPES().Bird())
        document.addSymbol(LANGUAGE().PUNCTUATION().ExclamationMark())

        document.addSymbol(LANGUAGE().EMOJIS().Facesmiley())
        document.addSymbol(LANGUAGE().EMOJIS().Facesmiley())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())

        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Can())
        document.addSymbol(LANGUAGE().VERBS().Fly())
        document.addSymbol(LANGUAGE().PREPOSITIONS().Above())
        document.addSymbol(LANGUAGE().ADJECTIVES().Big())
        document.addSymbol(WORLD().GEOLOGY().Mountain())
        document.addSymbol(LANGUAGE().CONJUNCTIONS().And())
        document.addSymbol(LANGUAGE().PREPOSITIONS().Above())
        document.addSymbol(LANGUAGE().ADJECTIVES().Fast())
        document.addSymbol(WORLD().GEOLOGY().River())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())
        document.addSymbol(LANGUAGE().PUNCTUATION().EndOfParagraph())   //PARAGRAPH
        document.addSymbol(LANGUAGE().PUNCTUATION().EndOfDocument())
    }
}


fun readDocument2(document: Document) {
    with( symbolPaths ) {
        document.setDocumentTitle("Who Am I?  - The Not so Amazing Story of Little NOT Cat")
        document.setDocumentAuthor("G. Koller")
        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Am_Is())
        document.addSymbol(LANGUAGE().ADVERBS().Not())
        document.addSymbol(PERSON().PETS().Cat())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())

        document.addSymbol(LANGUAGE().PRONOUNS().I())
        document.addSymbol(LANGUAGE().VERBS().Can())
        document.addSymbol(LANGUAGE().VERBS().Fly())
        document.addSymbol(LANGUAGE().PREPOSITIONS().Above())
        document.addSymbol(LANGUAGE().ADJECTIVES().Big())
        document.addSymbol(WORLD().GEOLOGY().Mountain())
        document.addSymbol(LANGUAGE().CONJUNCTIONS().And())
        document.addSymbol(LANGUAGE().PREPOSITIONS().Above())
        document.addSymbol(LANGUAGE().ADJECTIVES().Fast())
        document.addSymbol(WORLD().GEOLOGY().River())
        document.addSymbol(LANGUAGE().PUNCTUATION().Period())

        document.addSymbol(LANGUAGE().PUNCTUATION().EndOfParagraph())   //PARAGRAPH
        document.addSymbol(LANGUAGE().PUNCTUATION().EndOfDocument())
    }

}


