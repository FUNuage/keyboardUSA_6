import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA

enum class ContainerState {NATIVE, BUTTON, CARTOUCHE, MONTESSORI, NULL}

/* Notes toward a BLANK / Spacer Key for formatting Symbols better
    1. Could be just background color
    2. Should not go to Sentence!  Special ID
    3. Try 1/2 Wide so take up minimum space
    4. Alternative idea would be to have "from Left" and "from Right" into each "PANEL" of Display...   this might be the best in longer run!?
 */


data class ViewBox(var x0: Double = 0.0, var y0: Double = 0.0, var x1: Double = 0.0, var y1: Double = 0.0)

data class Header(var type: String = "", var isPictographic: Boolean = false, var isSimple : Boolean = false, var isReused: Boolean  = false, var isStopper: Boolean = false, var fillColor: RGBA = Colors.BLACK)

data class Statistics(var pKnown: Double = 0.0, var sDev: Double = 0.0)


open class Symbol() {
    open val vB  = ViewBox()
    open val hD  = Header()
    open val sT  = Statistics()
    open val pAr = arrayListOf<String>()
}


enum class SuperGROUPS {WORLD, ANIMATE, PERSON, SCENES, CONCEPTS, LANGUAGE}
//enum class LangGROUPS  {PRONOUNS, ARTICLES, ADVERBS, VERBS, ADJECTIVES, PREPOSITIONS, CONJUNCTIONS, INTERJECTIONS, EMOJIS, PUNCTUATION}
//                        PRO         ART     ADV      VER    ADJ         PRE            CON           INJ            EMO     PUN


class SymbolPaths() {
    private var keyMap      = mutableMapOf<String, Symbol>()

    //Note:  will be more of these for various special properties
    private var stopperList = mutableListOf<String>()
    private var whenList    = mutableListOf<String>()     //PREPOSITIONS: BEFORE / AFTER, but also CONCEPT.TIME words

    /*Future NOTE 03/27/2021 - but NOT NEAR FUTURE
          private var whenList      = mutableListOf<String>()     //PREPOSITIONS: BEFORE / AFTER, but also TIME CONCEPT words
          private var whereToList   = mutableListOf<String>()   //PREPOSITIONS: ON, IN, AT, BETWEEN...
          private var whereFromList = mutableListOf<String>() //PREPOSITIONS: ON, IN, AT, BETWEEN...

      Toward 3d Sentence Analysis
      Verbs are (ongoing or terminated Events) each associated with a series of naturally (frequency based?) associated open questions.
          for example: JUMP  - 1. What (jumped) 2. Where jumped to,  3.  [How high?]
          for example: CARRY  1.  Who or WhatThing  carried?  2. Carried What?   3.  Place-source - Carried From?   4.  Place_destination - Carried to?
          for example: SLEEP   Who / When / How [well, poorly, like-a-log]

       Idea:  keyAdd function could take a List of Lambdas or functions, where a single lambda would cause the fullSymbol KEY to be added to the appropriate LIST.
            fun addWhen(symClass: Symbol) =  whenList.add(keyName(keyName(symClass)))

        THEN the KeyAdd function would look something like:
              fun keyAdd(symClass: Symbol, isStopperTF: Boolean = false, lambdaAr: mutuableListOfLambda(where, when) {
                  where for each Lamda (or signal for one) the specific List is added to  - signal might make more sense?  eg:  (WHEN, WHAT, WHY) -or- (WHO, WHAT, TO_WHERE)
               }
  */


    private var keyAr    = keyMap.keys
    private var lastKeys = ""

    fun nSymbols() : Int = keyMap.size

    fun superGroups()    = SuperGROUPS.values()

    //This is a way to handle "Blank Keys" and other exceptions
    fun isStopper(keySet: String) = stopperList.contains(keySet)

    fun getSymbolByKeys(keySet: String) : Symbol {
        lastKeys = keySet
        return keyMap[keySet]  ?: Symbol()  //ELVIS LIVES!!!  todo?  need a flag on empty Symbol?
    }

    fun lastKeySYMBOLNAME() : String {
        val keyAr = lastKeys.split(".")
        if (keyAr.size > 2) return(keyAr[2])
        return ""
    }

    //TODO - NO DOUBT SOME FILTER METHOD WOULD BE FASTER/BETTER
    fun groupKeysBySG(sg: String) : ArrayList<String> {
        val ar   = ArrayList<String>()
        val tKey = "GROUPS.${sg}."
        for (key in keyAr) {if (key.indexOf(tKey, 0, false) == 0) {ar.add(key)}}
        return ar
    }

    fun symbolKeysBySGGC(sg: String, gc: String) : ArrayList<String> {
        val ar = ArrayList<String>()
        val tKey = "${sg}.${gc}."
        for (key in keyAr) {if (key.indexOf(tKey, 0, false) == 0) {ar.add(key)}}
        return ar
    }


    //========================================================BEGIN SPLICE GENERATED SYMBOLS HERE==================================================

//    inner class BLANK:Symbol() {
//        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
//        override val pAr= arrayListOf("M0,12")
//    }//END_OF_SYMBOL( BLANK )


//    inner class SUPERGROUPS {
//
//        inner class LANGUAGE() : Symbol() {
//            override val hD = Header("Basic.2", false, false, false)
//            override val sT = Statistics(0.5660, 0.9400)
//            override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
//            override val pAr= arrayListOf(
//                "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2,10a2,2 0 1,1 4,0q0,3 -4,6"
//            )
//        }
//    }

    inner class GROUPS { //BEGIN SUPERGROUP!
        fun getName() = "GROUPS"

        inner class WORLD { //BEGIN GROUPCODE

            inner class SKY() : Symbol() {
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7290, 0.9600)
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val pAr= arrayListOf(
                    "M0,8H8"
                )
            }//END_OF_SYMBOL( SKY )

            inner class GEOLOGY() : Symbol() {
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5660, 0.9400)
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val pAr= arrayListOf(
                    "M0,16H8M8,8V16M0,16L8,8"
                )
            }//END_OF_SYMBOL( GEOLOGY )

            inner class MATERIALS() : Symbol() {
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4940, 2.8300)
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val pAr= arrayListOf(
                    "M0,10H4M0,14H4M0,10V14M4,10V14M0,16H4"
                )
            }//END_OF_SYMBOL( MATERIALS )

            inner class PHENOMENA() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 13.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6140, 2.0800)
                override val pAr= arrayListOf(
                    "M0,8H8M4,16L8,8M4,16H8M4,12H12M10,10L12,12M10,14L12,12"
                )
            }//END_OF_SYMBOL( PHENOMENA )

        } //END_OF_GROUPCODE( WORLD )

        inner class ANIMATE { //BEGIN GROUPCODE

            inner class PLANTS() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 7.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1060, 0.5100)
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2,12V16M2,16a4,4 0 0,1 4,-4"
                )
            }//END_OF_SYMBOL( PLANTS )

            inner class ANIMAL_TYPES() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6650, 1.8600)
                override val pAr= arrayListOf(
                    //"M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16"
                    "M0,3       H8  M0,7   L2,3    M2,3   L4,7    M4,7    L6,3    M6,3    L8,7",  //ANIMAL
                    //TYPES
                    "M0,12a4,4 0 1,1 8,0",  "M3.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,12H6",  "M3.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( ANIMAL_TYPES )

            inner class ANIMALS_FROM() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6540, 2.5700)
                override val pAr= arrayListOf(
                    //"M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16", //Animal
                    //"M0,10V14M2,10L4,12M2,14L4,12" //From
                    "M0,3       H8  M0,7   L2,3    M2,3   L4,7    M4,7    L6,3    M6,3    L8,7",
                    "M3,10  V14   M5,10   L7,12   M5,14    L7,12"
                )
            }//END_OF_SYMBOL( ANIMALS_FROM )

        } //END_OF_GROUPCODE( ANIMATE )

        inner class PERSON {
            fun getName() = "PERSON"

            inner class FACE() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4350, 2.6700)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,10V14M2,14H6",
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( FACE )

            inner class BODY_PARTS() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 13.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8440, 2.2700)
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H10M2,16H10M10,8a5,5 0 0,1 0,8",
                )
            }//END_OF_SYMBOL( BODY_PARTS )

            inner class FAMILY() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 13.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0830, 0.7900)
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M2,12L6,8M6,8L10,12M10,8V12M8,16L10,12M10,12L12,16M8,16H12",
                )
            }//END_OF_SYMBOL( FAMILY )

            inner class PETS() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 19.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1670, 2.6200)
                override val pAr= arrayListOf(
                    "M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16",
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 4,0q0,3 -4,6q-4,-3 -4,-6",
                )
            }//END_OF_SYMBOL( PETS )

            inner class PERSONAL_ARTIFACTS() : Symbol() {  //TODO:  BEST IDEA:  ADD PERSONAL TO ARTIFACT,  TOOTHBRUSH NOT SO MUCH A "TOOL"
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 12.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6110, 0.7100)
                override val pAr= arrayListOf(
                    //"M0,12L4,16M4,8V16M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M0,16L4,12M4,12L8,16M0,16H8",
                    "M3,4H5M3,6H5M3,4V6M5,4V6"
                )
            }//END_OF_SYMBOL( PERSONAL_ARTIFACTS )

            inner class KITCHEN_TOOLS() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 7.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6110, 0.7100)
                override val pAr= arrayListOf(
                    "M0,12L4,16M4,8V16M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                )
            }//END_OF_SYMBOL( KITCHEN_TOOLS )

            inner class KITCHEN() : Symbol() { //GK MODIFIED
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 17.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1870, 1.4100)
                override val pAr= arrayListOf(
                  // Original Kitchen, 3 parts::  "M0,8H8M0,16H8M8,8V16",  "M10,16L14,12M14,12L18,16M10,16H18", "M20,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M20,16H24",
                  "M0,8H8M0,16H8M8,8V16",
                  "M10,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M10,16H15"
                )
            }//END_OF_SYMBOL( KITCHEN )

        } //END_OF_GROUPCODE( PERSON )

        inner class SCENES {
            fun getName() = "SCENES"

            inner class CONSTRUCTIONS() : Symbol() { //GMMOD 02232021
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4330, 1.4500)
                override val pAr= arrayListOf(
                    "M-0.2083,8a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,8H6",
                    "M7.7917,8a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,10V14M8,10V14",
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,16H6",
                    "M7.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    //"M3,6L4,4M4,4L5,6",  //Action indicator removed
                )
            }//END_OF_SYMBOL( CONSTRUCTIONS )

            inner class TRANSPORT_LAND() : Symbol() {//gk 0320 added ground symbol
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7080, 0.7100)
                override val pAr= arrayListOf(
                    "M0,8H4M0,12H4M0,8V12M4,8V12M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.585786,15.414214L3.414214,12.585786M0.585786,12.585786L3.414214,15.414214",
                    "M0,18H8"
                )
            }//END_OF_SYMBOL( TRANSPORT_LAND )

            inner class TRANSPORT_WATER() : Symbol() {  //gk 0320 added water symbol
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9860, 0.3200)
                override val pAr= arrayListOf(
                    "M0,8H4M0,12H4M0,8V12M4,8V12M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.585786,15.414214L3.414214,12.585786M0.585786,12.585786L3.414214,15.414214",
                    "M0,18a2.5,2.5 0 0,1 4,0M4,18a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( TRANSPORT_WATER )

            inner class TRANSPORT_AIR() : Symbol() {//gk 0320 added air symbol
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9620, 1.9800)
                override val pAr= arrayListOf(
                    "M0,8H4M0,12H4M0,8V12M4,8V12M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.585786,15.414214L3.414214,12.585786M0.585786,12.585786L3.414214,15.414214",
                    "M0,4H8"
                )
            }//END_OF_SYMBOL( TRANSPORT_AIR )

        } //END_OF_GROUPCODE( SCENES )

        inner class CONCEPTS {

            inner class TIME() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9220, 1.9300)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V12M4,12H8",
                )
            }//END_OF_SYMBOL( TIME )

            inner class ENUMERATION() : Symbol() { //ORIGINAL:: "M1,16L5,8M2,10H10M5,16L9,8M0,14H8",
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5820, 0.3000)
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                    "M3,8  H5  M3,16  H5  M3,8  V16  M5,8  V16",
                    "M6,8  H8  M6,16  H8  M6,8  V16  M8,8  V16",
                    "M9,8  H11  M9,16 H11  M9,8 V16 M11,8  V16",
                    "M12,16  H16  V14  H12 V16"
                )
            }//END_OF_SYMBOL( ENUMERATION )

            inner class ENERGY() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 17.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9710, 0.3700)
                override val pAr= arrayListOf(
                    "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                )
            }//END_OF_SYMBOL( ENERGY )

            inner class COLORSGROUP() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 5.5, y1 = 21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0110, 0.7600)
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,16H4",
                )
            }//END_OF_SYMBOL( COLORSGROUP )

            //inner class PHYSICS() : Symbol() {
            //    override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 17.5, y1 = 21.5)
            //    override val hD = Header("Basic.2", false, false, false)
            //    override val sT = Statistics(0.2440, 1.2000)
            //    override val pAr= arrayListOf(
            //        "M0,10L8,14  M8,10 V14  M8,10  L16,14  M4,12 a4,4 0 1,1 8,0  a4,4 0 1,1 -8,0",
            //    )
            //}//END_OF_SYMBOL( PHYSICS )

        } //END_OF_GROUPCODE( CONCEPTS )

        inner class LANGUAGE {

            //1"PRONOUNS",  2"ARTICLES",   3"ADVERBS",    4"VERBS", 5"ADJECTIVES", 65"PREPOSITIONS",  7"CONJUNCTIONS", 8"INTERJECTIONS", 9"EMOJIS", 10"PUNCTUATION"

            inner class PRONOUNS():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4190, 0.1100)
                override val pAr= arrayListOf(  //TRIANGLE - Needs to be taller + exchange symbol
                    "M0,16L4,8M4,8L8,16M0,16H8",
                    "M0,12a4,4 0 1,0 8,0M2,14L4,16M4,16L6,14M4,8V16M2,10L4,8M4,8L6,10"
                )
            }//END_OF_SYMBOL( PREPOSITIONS )

            inner class ARTICLES():Symbol() { //SMALL (MEDITERANIAN?) BLUE TRIANGLE
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0040, 2.4700)
                override val pAr= arrayListOf(
                    "M0,16L2,12M2,12L4,16M0,16H4"
                )
            }//END_OF_SYMBOL( ARTICLES )

            inner class ADVERBS():Symbol() {  //SMALL CIRCLE:: ORANGE
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1030, 2.6900)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                )
            }//END_OF_SYMBOL( ADVERBS )

            inner class VERBS():Symbol() {  //LARG CIRCLE:: BRIGHT RED   //-0.75 -0.75 14 21.5
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=14.0, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1030, 2.6900)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 12,0 a4,4 0 1,1 -12,0",
                    "M2,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0"
                )
            }//END_OF_SYMBOL( VERBS )

            inner class ADJECTIVES():Symbol() {  //MEDIUM SIZED TRIANGLE :: DEEP BLUE
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0170, 0.1900)
                override val pAr= arrayListOf(
                    "M0,12M0,16L4,8M4,8L8,16M0,16H8",
                )
            }//END_OF_SYMBOL( ADJECTIVES )

            inner class PREPOSITIONS():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4190, 0.1100)
                override val pAr= arrayListOf(  //todo!  using Moon to start
                    "M0,8a5,5 0 0,1 0,8M0,8a4,4 0 1,1 0,8"
                )
            }//END_OF_SYMBOL( PREPOSITIONS )

            inner class CONJUNCTIONS():Symbol() { //LONG NARROW RECTANGLE::  PINK-ISH
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0080, 1.4200)
                override val pAr= arrayListOf( //from Card
                    "M0,10H8M0,14H8M0,10V14M8,10V14",
                )
            }//END_OF_SYMBOL( CONJUNCTIONS )

            inner class INTERJECTIONS():Symbol() { //CIRCLE OVER SMALL TRIANGLE:: GOLD
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6230, 1.6600)
                override val pAr= arrayListOf( //TRIANGLE + MOUTH
                    "M0,8a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                    "M0,16L4,8M4,8L8,16M0,16H8"
                )
            }//END_OF_SYMBOL( INTERJECTION )

            //==========================================================================================================

            inner class EMOJIS():Symbol() { //SMILEY FACE:: YELLOW
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6070, 2.9100)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                    "M2.4,13 a1.5,1.5 0 1,0 3.0,0",
                    "M2,11a 0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5,11a 0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( EMOJIS )

            inner class PUNCTUATION():Symbol() {  //BIG COMMA:: BLACK?
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4280, 0.5800)
                override val pAr= arrayListOf(
                //"M0,10  a2,2 0 1,1 2,2  M2,12   V14",   //qmark - ORIG
                //"M1.7917,16   a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                //"M2,10  a2,2 0 1,1 2,2  M2,12   V14",   //qmark - MOD
                //"M3.7917,16   a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",

                //"M0,8  V14",  //exclamation mark - ORIG
                //"M-0.2083,16   a0.2083,0.2083 0 1,1 0.4166,0   a0.2083,0.2083 0 1,1 -0.4166,0",
                //"M4,8  V14",  //exclamation mark - MOD
                //"M3.8, 16   a0.2083,0.2083 0 1,1 0.4166,0   a0.2083,0.2083 0 1,1 -0.4166,0",

                "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                "M0.2777,16q0.0694,0.6943,-0.5554,1.1109",
                )
            }//END_OF_SYMBOL( PUNCTUATION )

        }//END_OF_GROUPCODE( LANGUAGE )

    } //END_OF_SUPERGROUP( GROUPS )

    //==============================================================================================================

    inner class WORLD() {//BEGIN SUPERGROUP( WORLD )

        inner class MATERIALS {

            inner class Metal():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2330, 0.9800)
                override val pAr= arrayListOf(
                    "M0,10H8M0,14H8M0,10V14M8,10V14M4,10V14",
                )
            }//END_OF_SYMBOL( Metal )

            inner class Mud():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7920, 2.4100)
                override val pAr= arrayListOf(
                    "M0,16a2.5,2.5 0 0,1 4,0M4,16a2.5,2.5 0 0,0 4,0M0,16H12",
                )
            }//END_OF_SYMBOL( Mud )

            inner class Powder():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6140, 1.8500)
                override val pAr= arrayListOf(
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Powder )

            inner class Salt():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3420, 2.2900)
                override val pAr= arrayListOf(
                    "M0,12H8M4,8V16",
                    "M3,4H5M3,6H5M3,4V6M5,4V6",
                    "M10,16L14,8M10,16H14M10,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M16,10L18,8M18,8L20,10M16,10H20M16,14H20M16,10V14M20,10V14M16,14L18,16M18,16L20,14",
                )
            }//END_OF_SYMBOL( Salt )

            inner class Sand():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6950, 2.2500)
                override val pAr= arrayListOf(
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,16H10M10,12V16M6,16L10,12",
                )
            }//END_OF_SYMBOL( Sand )

            inner class Stone():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8490, 2.9700)
                override val pAr= arrayListOf(
                    "M0,16H4M4,12V16M0,16L4,12",
                )
            }//END_OF_SYMBOL( Stone )

            inner class Wood():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1030, 1.7900)
                override val pAr= arrayListOf(
                    "M0,10H4M0,14H4M0,10V14M4,10V14M0,16H4",
                    "M6,12a4,4 0 0,0 4,-4M10,8V16M10,8a4,4 0 0,0 4,4",
                )
            }//END_OF_SYMBOL( Wood )

        } //END_OF_GROUPCODE( MATERIALS )

        inner class SKY {

            inner class Cloud():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7070, 1.0000)
                override val pAr= arrayListOf(
                    "M0,8H8M0,8a2.5,2.5 0 0,1 4,0M4,8a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Cloud )

            inner class Earth():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3230, 1.7300)
                override val pAr= arrayListOf(
                    "M0,8L8,16M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                )
            }//END_OF_SYMBOL( Earth )

            inner class Moon():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0790, 1.0000)
                override val pAr= arrayListOf(
                    "M0,8a5,5 0 0,1 0,8M0,8a4,4 0 1,1 0,8",
                )
            }//END_OF_SYMBOL( Moon )

            inner class Sky():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1420, 2.3000)
                override val pAr= arrayListOf(
                    "M0,8H8",
                )
            }//END_OF_SYMBOL( Sky )

            inner class Snow():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5570, 1.9000)
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M4,8V14M4,14V18M3,15L5,17M3,17L5,15",
                )
            }//END_OF_SYMBOL( Snow )

            inner class Star():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9300, 1.2100)
                override val pAr= arrayListOf("M1,10V14M0,11L2,13M0,13L2,11")
            }//END_OF_SYMBOL( Star )

            inner class Sun():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7080, 2.0200)
                override val pAr= arrayListOf("M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0")
            }//END_OF_SYMBOL( Sun )

        } //END_OF_GROUPCODE( SKY )

        inner class GEOLOGY {

            inner class Ground():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0980, 1.8000)
                override val pAr= arrayListOf(
                    "M0,16H8",
                )
            }//END_OF_SYMBOL( Ground )

            inner class Lake():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4620, 0.4600)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,16a2.5,2.5 0 0,1 4,0M10,16a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Lake )

            inner class Mountain():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1850, 0.2000)
                override val pAr= arrayListOf(
                    "M0,16H8M8,8V16M0,16L8,8",
                )
            }//END_OF_SYMBOL( Mountain )

            inner class Ocean():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6190, 2.5100)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12L10,16M6,16L10,12",
                    "M12,16a2.5,2.5 0 0,1 4,0M16,16a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Ocean )

            inner class Rain():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7190, 0.7000)
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M2,14L4,16M4,16L6,14M4,8V16",
                )
            }//END_OF_SYMBOL( Rain )

            inner class River():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1610, 0.5000)
                override val pAr= arrayListOf(
                    "M0,12H8M6,10L8,12M6,14L8,12M0,16a2.5,2.5 0 0,1 4,0M4,16a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( River )

            inner class Water():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7500, 2.0900)
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Water )

        } //END_OF_GROUPCODE( GEOLOGY )

        inner class PHENOMENA {

            inner class Ashes():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0220, 1.1900)
                override val pAr= arrayListOf(
                    "M0,16L8,8M2,10H6M2,14H6M2,10V14M6,10V14",
                    "M11,8a2.5,2.5 0 0,1 0,4M11,12a2.5,2.5 0 0,0 0,4",
                )
            }//END_OF_SYMBOL( Ashes )

            inner class Cloud():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4950, 2.0600)
                override val pAr= arrayListOf(
                    "M0,8H8M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Cloud )

            inner class Fire():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9840, 2.5800)
                override val pAr= arrayListOf(
                    "M1,8a2.5,2.5 0 0,1 0,4M1,12a2.5,2.5 0 0,0 0,4",
                )
            }//END_OF_SYMBOL( Fire )

            inner class Fog():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9720, 1.9200)
                override val pAr= arrayListOf(
                    "M0,8H8M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Fog )

            inner class Ice():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1980, 1.0100)
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M2,10H6M2,14H6M2,10V14M6,10V14",
                )
            }//END_OF_SYMBOL( Ice )

            inner class Smoke():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1920, 1.4600)
                override val pAr= arrayListOf(
                    "M0,8H8M0,8a2.5,2.5 0 0,1 4,0M4,8a2.5,2.5 0 0,0 4,0",
                    "M11,8a2.5,2.5 0 0,1 0,4M11,12a2.5,2.5 0 0,0 0,4",
                )
            }//END_OF_SYMBOL( Smoke )

            inner class Snow():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6440, 2.4800)
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M4,8V14M4,14V18M3,15L5,17M3,17L5,15",
                )
            }//END_OF_SYMBOL( Snow )

            inner class Wind():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8160, 0.4400)
                override val pAr= arrayListOf(
                    "M0,8H8M4,16L8,8M4,16H8M4,12H12M10,10L12,12M10,14L12,12",
                )
            }//END_OF_SYMBOL( Wind )

        }//END_OF_SUPERGROUP( WORLD )

    } //END_OF_SUPERGROUP( WORLD )

    //==============================================================================================================

    inner class ANIMATE() {//BEGIN SUPERGROUP( WORLD )

        inner class PLANTS {

            inner class Bark():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8870, 1.6400)
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12",
                    "M10,12a4,4 0 0,0 4,-4M14,8V16M14,8a4,4 0 0,0 4,4M15,14L17,12M15,14L17,16",
                )
            }//END_OF_SYMBOL( Bark )

            inner class Flower():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8580, 0.1800)
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2,12V16",
                )
            }//END_OF_SYMBOL( Flower )

            inner class Forest():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7280, 2.4200)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12L10,16M6,16L10,12",
                    "M12,12a4,4 0 0,0 4,-4M16,8V16M16,8a4,4 0 0,0 4,4",
                )
            }//END_OF_SYMBOL( Forest )

            inner class Fruit():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5730, 1.6400)
                override val pAr= arrayListOf(
                    "M2,12a4,4 0 0,1 4,-4M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                )
            }//END_OF_SYMBOL( Fruit )

            inner class Grass():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8580, 1.8700)
                override val pAr= arrayListOf("M0,16a4,4 0 0,1 4,-4")
            }//END_OF_SYMBOL( Grass )

            inner class Leaf():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6770, 0.0100)
                override val pAr= arrayListOf("M2,8a5,5 0 0,0 0,8M2,8V16M2,8a5,5 0 0,1 0,8")
            }//END_OF_SYMBOL( Leaf )

            inner class Root():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6670, 1.3800)
                override val pAr= arrayListOf(
                    "M0,16H8M0,20a4,4 0 0,0 4,-4",
                )
            }//END_OF_SYMBOL( Root )

            inner class Seed():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9450, 0.1400)
                override val pAr= arrayListOf(
                    "M0,16H8",
                    "M1.7917,18a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.7917,18a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Seed )

            inner class Stick():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8510, 1.6200)
                override val pAr= arrayListOf(
                    "M1,8V16",
                    "M0,4H2M0,6H2M0,4V6M2,4V6",
                    "M3,12a4,4 0 0,0 4,-4M7,8V16M7,8a4,4 0 0,0 4,4",
                )
            }//END_OF_SYMBOL( Stick )

            inner class Tree():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4500, 0.1400)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 0,0 4,-4M4,8V16M4,8a4,4 0 0,0 4,4",
                )
            }//END_OF_SYMBOL( Tree )

        } //END_OF_GROUPCODE( PLANTS )


        inner class ANIMAL_TYPES {

            inner class Animal():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8130, 0.2300)
                override val pAr= arrayListOf(
                    "M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16",
                )
            }//END_OF_SYMBOL( Animal )

            inner class Rabbit():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8130, 0.2300)
                override val pAr= arrayListOf(
                    "M0,12   L2,16  M2,16   L4,12  M0,12   H4  M4,12    L6,16   M6,16   L8,12   M4,12    H8",
                    "M10,10  a2,2 0 1,1 4,0  q0,3 -4,6"
                )
            }//END_OF_SYMBOL( Rabbit )

            inner class Bird():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4710, 0.7500)
                override val pAr= arrayListOf(
                    "M0,8a4,4 0 0,1 4,4M4,12a4,4 0 0,1 4,-4M3,14L4,12M4,12L5,14",
                )
            }//END_OF_SYMBOL( Bird )

            inner class Fishanimal():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2280, 0.5400)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M0,16a4,4 0 1,1 8,0",
                )
            }//END_OF_SYMBOL( Fishanimal )

            inner class Insect():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4760, 2.9100)
                override val pAr= arrayListOf(
                    "M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16M8,16L10,12M10,12L12,16",
                )
            }//END_OF_SYMBOL( Insect )

            inner class Louse():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=26.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9070, 1.1000)
                override val pAr= arrayListOf(
                    "M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16M8,16L10,12M10,12L12,16",
                    "M14,12L18,16M14,16L22,8M23,8L25,6M23,8L25,10",
                )
            }//END_OF_SYMBOL( Louse )

            inner class Snake():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9040, 1.1900)
                override val pAr= arrayListOf(
                    "M0,14a2,2 0 1,1 4,0M4,14a2,2 0 1,0 4,0M8,14a2,2 0 1,1 4,0",
                )
            }//END_OF_SYMBOL( Snake )

            inner class Frog():Symbol() { //gkMod to remove animal and shift left
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9040, 1.1900)
                override val pAr= arrayListOf(
                   "M0,16  H8  M0,12  a2.5,2.5 0 0,1 4,0  M4,12  a2.5,2.5 0 0,0 4,0",
                   "M10,16  a4,4 0 1,1 8,0  M16,14  L18,16  M18,16  L20,14"
                )
            }//END_OF_SYMBOL( Frog )

            inner class Wings():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6280, 2.4600)
                override val pAr= arrayListOf(
                    "M0,8a4,4 0 0,1 4,4M4,12a4,4 0 0,1 4,-4",
                )
            }//END_OF_SYMBOL( Wings )

            inner class Worm():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8240, 1.1800)
                override val pAr= arrayListOf(
                    "M0,16H12M0,18a2,2 0 1,1 4,0M4,18a2,2 0 1,0 4,0M8,18a2,2 0 1,1 4,0",
                )
            }//END_OF_SYMBOL( Worm )

        } //END_OF_GROUPCODE( ANIMAL_TYPES )


        inner class ANIMALS_FROM {

            inner class Blood():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9670, 1.7600)
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                    "M10,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M14,8V16",
                )
            }//END_OF_SYMBOL( Blood )

            inner class Egg():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8050, 1.4500)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M2,12a2.5,2.5 0 0,1 4,0M2,12a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Egg )

            inner class Feather():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9710, 0.5000)
                override val pAr= arrayListOf(
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,12H4",
                    "M1.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,8a4,4 0 0,1 4,4M10,12a4,4 0 0,1 4,-4",
                )
            }//END_OF_SYMBOL( Feather )

            inner class Horn():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9460, 1.7000)
                override val pAr= arrayListOf(
                    "M0,8a2,2 0 1,0 4,0",
                )
            }//END_OF_SYMBOL( Horn )

            inner class Meat():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7340, 2.5800)
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0,16H4",
                    "M6,12H14M6,16L8,12M8,12L10,16M10,16L12,12M12,12L14,16",
                )
            }//END_OF_SYMBOL( Meat )

            inner class Tail():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=14.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1420, 2.9400)
                override val pAr= arrayListOf(
                    "M0,8L2,10M0,12L2,10M5,8a2,2 0 1,0 0,4M5,12H13M5,16L7,12M7,12L9,16M9,16L11,12M11,12L13,16",
                )
            }//END_OF_SYMBOL( Tail )

        } //END_OF_GROUPCODE( ANIMALS_FROM )

    }//END_OF_SUPERGROUP( ANIMATE )

    //==============================================================================================================

    inner class PERSON() {//BEGIN SUPERGROUP( WORLD )

        inner class FACE {

            inner class Chin():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8920, 0.0400)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,10V14M2,14H6",
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,19L4,17M4,17L6,19",
                )
            }//END_OF_SYMBOL( Chin )

            inner class Ear():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2010, 2.9000)

                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0q0,3,-4,6",
                )
            }//END_OF_SYMBOL( Ear )

            inner class Eye():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6180, 2.9000)
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Eye )

            inner class Hairhead():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=10.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1280, 0.6600)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,10V14M2,14H6M2,6a1.25,1.25 0 0,0 0,2M4,6a1.25,1.25 0 0,0 0,2M6,6a1.25,1.25 0 0,0 0,2M7,6L9,4M7,6L9,8",
                )
            }//END_OF_SYMBOL( Hairhead )

            inner class Headwithhair():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1480, 2.0800)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,10V14M2,14H6M2,6a1.25,1.25 0 0,0 0,2M4,6a1.25,1.25 0 0,0 0,2M6,6a1.25,1.25 0 0,0 0,2",
                )
            }//END_OF_SYMBOL( Headwithhair )

            inner class Mouth():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3090, 0.2700)
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                )
            }//END_OF_SYMBOL( Mouth )

            inner class Nose():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", true, true, true)
                override val sT = Statistics(0.9250, 1.9300)
                override val pAr= arrayListOf(
                    "M0,16L4,8M0,16H4",
                )
            }//END_OF_SYMBOL( Nose )

            inner class Tongue():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2370, 2.1700)
                override val pAr= arrayListOf(
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,12H4",
                    "M1.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M12,10L14,8M14,8L16,10M14,8V16M16,14L18,16M18,16L20,14M18,8V16",
                )
            }//END_OF_SYMBOL( Tongue )

            inner class Tooth():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2640, 2.6700)
                override val pAr= arrayListOf(
                    "M0,12L2,16M2,16L4,12M0,12H4",
                )
            }//END_OF_SYMBOL( Tooth )

        } //END_OF_GROUPCODE( FACE )


        inner class BODY_PARTS {

            inner class Backbody():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=8.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1700, 1.1300)
                override val pAr= arrayListOf(
                    "M0,6L2,8M0,10L2,8M3,8V16M3,16H7",
                )
            }//END_OF_SYMBOL( Backbody )

            inner class Bone():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6270, 2.7100)
                override val pAr= arrayListOf(
                    "M-0.2083,8a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,10V14",
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Bone )

            inner class Breasts():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0930, 0.6800)
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H10M2,16H10M10,8a5,5 0 0,1 0,8M2,14L4,12M4,12L6,14M6,14L8,12M8,12L10,14",
                )
            }//END_OF_SYMBOL( Breasts )

            inner class Fingernail():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=18.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8880, 1.8300)
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12",
                    "M10,12L14,16M14,8V16M15,8L17,6M15,8L17,10",
                )
            }//END_OF_SYMBOL( Fingernail )

            inner class Foot():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6350, 2.2000)
                override val pAr= arrayListOf(
                    "M0,16L4,8M4,8L8,16M0,16H4M8,16H12M8,13L10,15M10,15L12,13",
                )
            }//END_OF_SYMBOL( Foot )

            inner class Hand():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9640, 0.5700)
                override val pAr= arrayListOf(
                    "M0,12L4,16M4,8V16",
                )
            }//END_OF_SYMBOL( Hand )

            inner class Heart():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4210, 1.8100)
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 4,0q0,3,-4,6q-4,-3,-4,-6",
                    "M3,4H5M3,6H5M3,4V6M5,4V6",
                )
            }//END_OF_SYMBOL( Heart )

            inner class Intestines():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7740, 1.1500)
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H6M2,16H6M6,8a5,5 0 0,1 0,8",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M10,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M10,16H14",
                    "M16,8V16M20,8V16M16,16a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                )
            }//END_OF_SYMBOL( Intestines )

            inner class Knee():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=8.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3720, 1.9400)
                override val pAr= arrayListOf(
                    "M0,8V12M0,12L4,16M0,16H4M5,16L7,14M5,16L7,18",
                )
            }//END_OF_SYMBOL( Knee )

            inner class Leg():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0950, 1.0900)
                override val pAr= arrayListOf(
                    "M0,16L4,8M4,8L8,16M0,16H4M8,16H12M7,12L9,10M7,12L9,14",
                )
            }//END_OF_SYMBOL( Leg )

            inner class Liver():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=31.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0340, 2.0300)
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H6M2,16H6M6,8a5,5 0 0,1 0,8",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M8,16L16,8M16,8V12M14,12H18M14,16H18M14,12V16M18,12V16",
                    "M20,12L24,16M20,16L24,12",
                    "M26,16L28,12M28,12L30,16",
                )
            }//END_OF_SYMBOL( Liver )

            inner class Neckbody():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8310, 2.7600)
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H10M2,16H10M10,8a5,5 0 0,1 0,8M4,5L6,7M6,7L8,5",
                )
            }//END_OF_SYMBOL( Neckbody )

            inner class Neckhead():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4980, 2.2900)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,10V14M2,14H6M3,16V18M5,16V18M6,18L8,16M6,18L8,20",
                )
            }//END_OF_SYMBOL( Neckhead )

            inner class Oil():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9350, 2.7000)
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M0,16a2.5,2.5 0 0,1 4,0M4,16a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Oil )

            inner class Skin():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=23.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5530, 0.7500)
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12",
                    "M12,8a5,5 0 0,0 0,8M12,8H20M12,16H20M20,8a5,5 0 0,1 0,8",
                )
            }//END_OF_SYMBOL( Skin )

            inner class Stomach():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4750, 0.5500)
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H10M2,16H10M10,8a5,5 0 0,1 0,8M4,10L6,12M6,12L8,10",
                )
            }//END_OF_SYMBOL( Stomach )

        } //END_OF_GROUPCODE( BODY_PARTS )


        inner class FAMILY {

            inner class Brother():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7310, 0.2900)
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12M2,16L4,12M4,12L6,16",
                    "M10,13a1,1 0 1,1 2,0M10,16L12,13M10,16H12",
                )
            }//END_OF_SYMBOL( Brother )

            inner class Baby():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4950, 1.8100)
                override val pAr= arrayListOf(
                    "M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4,12V16M4,14H8M8,12V16"
                )
            }//END_OF_SYMBOL( Baby )

            inner class Child():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4950, 1.8100)
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0,12H4M2,12V16M0,16H4",
                )
            }//END_OF_SYMBOL( Child )

            inner class Father():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0510, 0.2100)
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12M4,8V12M2,16L4,12M4,12L6,16",
                )
            }//END_OF_SYMBOL( Father )

            inner class Husband():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9740, 2.8500)
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M2,12L6,8M6,8L10,12",
                )
            }//END_OF_SYMBOL( Husband )

            inner class Man():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4330, 1.4800)
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16",
                )
            }//END_OF_SYMBOL( Man )

            inner class Mother():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4460, 1.2000)
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12M4,8V12M2,16L4,12M4,12L6,16M2,16H6",
                )
            }//END_OF_SYMBOL( Mother )

            inner class Person():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3090, 1.7300)
                override val pAr= arrayListOf(
                    "M0,16H4M2,8V16",
                )
            }//END_OF_SYMBOL( Person )

            inner class Sister():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7350, 0.0500)
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12M2,16L4,12M4,12L6,16M2,16H6",
                    "M10,13a1,1 0 1,1 2,0M10,16L12,13M10,16H12",
                )
            }//END_OF_SYMBOL( Sister )

            inner class Wife():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3310, 0.5200)
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12M8,8V12M6,16L8,12M8,12L10,16M6,16H10",
                )
            }//END_OF_SYMBOL( Wife )

            inner class Woman():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5560, 0.9500)
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M0,16H4",
                )
            }//END_OF_SYMBOL( Woman )

        } //END_OF_GROUPCODE( FAMILY )


        inner class PETS {

            inner class Cat():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0270, 2.4800)
                override val pAr= arrayListOf(
                    "M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16M0,16a1,1 0 1,1 2,0M4,16a1,1 0 1,1 2,0M8,16a1,1 0 1,1 2,0",
                )
            }//END_OF_SYMBOL( Cat )

            inner class Dog():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6520, 1.5600)
                override val pAr= arrayListOf(
                    "M2,8a2,2 0 1,0 0,4M2,12H10M2,16L4,12M4,12L6,16M6,16L8,12M8,12L10,16",
                )
            }//END_OF_SYMBOL( Dog )

        } //END_OF_GROUPCODE( PETS )


        inner class PERSONAL_ARTIFACTS { //GK MANUAL MOD, TODO?  ADD TOYS, PLAY THINGS?

            inner class Brush():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8230, 0.1200)
                override val pAr= arrayListOf(
                    "M0,8V12M0,12H8M8,8V12M4,8V16",
                )
            }//END_OF_SYMBOL( Brush )

            inner class Hammer():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3260, 1.2800)
                override val pAr= arrayListOf(
                    "M0,16L6,10M4,8L8,12",
                )
            }//END_OF_SYMBOL( Hammer )

            inner class Knife():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2900, 1.0300)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L8,8",
                )
            }//END_OF_SYMBOL( Knife )

            inner class Needle():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5190, 1.1700)
                override val pAr= arrayListOf(
                    "M0,8V16M0,8a2,2 0 1,1 0,4",
                )
            }//END_OF_SYMBOL( Needle )

            inner class Rope():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5780, 2.8800)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12L10,16M6,16L10,12",
                    "M12,16a4,4 0 0,1 4,-4M16,12a4,4 0 0,0 4,-4",
                )
            }//END_OF_SYMBOL( Rope )

            inner class Thread():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5780, 2.8800)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,16a4,4 0 0,1 4,-4M10,12a4,4 0 0,0 4,-4"
                )
            }//END_OF_SYMBOL( Thread )

            inner class Saw():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0160, 1.0900)
                override val pAr= arrayListOf(
                    "M0,20L12,8M4,16H8M8,12V16M8,12H12M12,8V12",
                )
            }//END_OF_SYMBOL( Saw )

            inner class Shovel():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7190, 0.8300)
                override val pAr= arrayListOf(
                    "M0,8H4M2,8V14M0,14H4M0,14a2,2 0 1,0 4,0",
                )
            }//END_OF_SYMBOL( Shovel )

            inner class Toothbrush():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3260, 2.0200)
                override val pAr= arrayListOf(
                    "M0,8V12M0,12H8M8,8V12M4,8V16",
                    "M10,12L12,16M12,16L14,12M10,12H14M14,12L16,16M16,16L18,12M14,12H18",
                )
            }//END_OF_SYMBOL( Toothbrush )

        } //END_OF_GROUPCODE( PERSONAL_ARTIFACTS )


        inner class KITCHEN_TOOLS {

            inner class Container():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6730, 1.2700)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0",
                )
            }//END_OF_SYMBOL( Container )

            inner class Cup():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4590, 1.2600)
                override val pAr= arrayListOf(
                    "M0,12V16M0,16H4M4,12V16M4,12a2,2 0 1,1 0,4",
                )
            }//END_OF_SYMBOL( Cup )

            inner class Dish():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3020, 2.7700)
                override val pAr= arrayListOf(
                    "M0,12L4,16M4,16H12M12,16L16,12",
                )
            }//END_OF_SYMBOL( Dish )

            inner class Drinkingglass():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7030, 2.1200)
                override val pAr= arrayListOf(
                    "M0,8V16M0,16H4M4,8V16",
                )
            }//END_OF_SYMBOL( Drinkingglass )

            inner class Fork():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1190, 1.2200)
                override val pAr= arrayListOf(
                    "M0,16L6,10M2,10L6,14M2,10L4,8M6,14L8,12",
                )
            }//END_OF_SYMBOL( Fork )

            inner class Knife():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8250, 2.7100)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L8,8",
                )
            }//END_OF_SYMBOL( Knife )

            inner class Spoon():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9000, 0.9400)
                override val pAr= arrayListOf(
                    "M0,16L4,12M4,12L4.5857,11.4142M4,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                )
            }//END_OF_SYMBOL( Spoon )

        } //END_OF_GROUPCODE( KITCHEN_TOOLS )

    }//END_OF_SUPERGROUP( PERSON )

    //==============================================================================================================

    inner class SCENES() {//BEGIN SUPERGROUP( WORLD )

        inner class CONSTRUCTIONS {

            inner class City():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5510, 2.0700)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12L10,16M6,16L10,12",
                    "M12,12L16,16M12,16L16,12",
                    "M18,12V16M18,16H26M26,12V16M18,12L22,8M22,8L26,12",
                )
            }//END_OF_SYMBOL( City )

            inner class House():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7010, 1.6800)
                override val pAr= arrayListOf(
                    "M0,12V16M0,16H8M8,12V16M0,12L4,8M4,8L8,12",
                )
            }//END_OF_SYMBOL( House )

            inner class Library():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4420, 1.3000)
                override val pAr= arrayListOf(
                    "M0,12V16M0,16H8M8,12V16M0,12L4,8M4,8L8,12",
                    "M10,8H14M10,16H14M10,8V16M14,8V16M14,8H18M14,16H18M18,8V16",
                )
            }//END_OF_SYMBOL( Library )

            inner class Neighbourhood():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6620, 0.4200)
                override val pAr= arrayListOf(
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,12H4",
                    "M1.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,12L10,16M6,16L10,12",
                    "M12,12L16,16M12,16L16,12",
                    "M18,12V16M18,16H26M26,12V16M18,12L22,8M22,8L26,12",
                )
            }//END_OF_SYMBOL( Neighbourhood )

            inner class Town():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2340, 1.6600)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12L10,16M6,16L10,12",
                    "M12,12V16M12,16H20M20,12V16M12,12L16,8M16,8L20,12",
                )
            }//END_OF_SYMBOL( Town )

            inner class University():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=29.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8330, 1.1300)
                override val pAr= arrayListOf(
                    "M0,12V16M0,16H8M8,12V16M0,12L4,8M4,8L8,12",
                    "M10,12a4,4 0 1,0 8,0M12,14L14,16M14,16L16,14M14,8V16M12,10L14,8M14,8L16,10",
                    "M20,12a4,4 0 1,1 8,0M20,12V16M20,16H28M28,12V16",
                )
            }//END_OF_SYMBOL( University )

            inner class Village():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9320, 2.6200)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12V16M6,16H14M14,12V16M6,12L10,8M10,8L14,12",
                )
            }//END_OF_SYMBOL( Village )

        } //END_OF_GROUPCODE( CONSTRUCTION )


        inner class TRANSPORT_PATHS {

            inner class Bridge():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2830, 1.1100)
                override val pAr= arrayListOf(
                    "M0,16H4M4,16a4,4 0 1,1 8,0M12,16H16",
                )
            }//END_OF_SYMBOL( Bridge )

            inner class Road():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2370, 1.6200)
                override val pAr= arrayListOf(
                    "M0,16H16M8,12H16M8,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M8.5857,15.4142L11.4142,12.5857M8.5857,12.5857L11.4142,15.4142M12,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M12.5857,15.4142L15.4142,12.5857M12.5857,12.5857L15.4142,15.4142M13,11L15,9M14,10L16,12M2,13L4,15M4,15L6,13",
                )
            }//END_OF_SYMBOL( Road )

            inner class Walkway():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=29.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9400, 0.4200)
                override val pAr= arrayListOf(
                    "M0,16H8M2,14L4,16M4,16L6,14",
                    "M10,16L12,12M12,12L14,16",
                    "M16,16L20,8M20,8L24,16M16,16H20M24,16H28",
                )
            }//END_OF_SYMBOL( Walkway )

        } //END_OF_GROUPCODE( TRANSPORT_PATHS )


        inner class TRANSPORT_LAND {

            inner class Automobile():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7950, 0.2100)
                override val pAr= arrayListOf(
                    "M0,12H8M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.5857,15.4142L3.4142,12.5857M0.5857,12.5857L3.4142,15.4142M4,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4.5857,15.4142L7.4142,12.5857M4.5857,12.5857L7.4142,15.4142M5,11L7,9M6,10L8,12",
                )
            }//END_OF_SYMBOL( Automobile )

            inner class Bicycle():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2200, 2.8500)
                override val pAr= arrayListOf(
                    "M0,13a1,1 0 1,1 2,0M0,16L2,13M0,16H2",
                    "M4,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4.5857,15.4142L7.4142,12.5857M4.5857,12.5857L7.4142,15.4142",
                )
            }//END_OF_SYMBOL( Bicycle )

            inner class Bus():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5930, 1.6000)
                override val pAr= arrayListOf(
                    "M0,12H12M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.5857,15.4142L3.4142,12.5857M0.5857,12.5857L3.4142,15.4142M4,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4.5857,15.4142L7.4142,12.5857M4.5857,12.5857L7.4142,15.4142M8,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M8.5857,15.4142L11.4142,12.5857M8.5857,12.5857L11.4142,15.4142M9,11L11,9M10,10L12,12",
                )
            }//END_OF_SYMBOL( Bus )

            inner class Truck():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4600, 0.5200)
                override val pAr= arrayListOf(
                    "M0,12H12M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.5857,15.4142L3.4142,12.5857M0.5857,12.5857L3.4142,15.4142M4,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4.5857,15.4142L7.4142,12.5857M4.5857,12.5857L7.4142,15.4142M8,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M8.5857,15.4142L11.4142,12.5857M8.5857,12.5857L11.4142,15.4142M9,11L11,9M10,10L12,12M0,8V12M0,8H4M4,8V12",
                )
            }//END_OF_SYMBOL( Truck )

        } //END_OF_GROUPCODE( TRANSPORT_LAND )


        inner class TRANSPORT_AIR {

            inner class Airplane():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0010, 2.6600)
                override val pAr= arrayListOf(
                    "M0,8a4,4 0 0,1 4,4M4,12a4,4 0 0,1 4,-4M2,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2.5857,15.4142L5.4142,12.5857M2.5857,12.5857L5.4142,15.4142",
                )
            }//END_OF_SYMBOL( Airplane )

        } //END_OF_GROUPCODE( TRANSPORT_AIR )


        inner class TRANSPORT_WATER {

            inner class Boat():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1980, 1.6800)
                override val pAr= arrayListOf(
                    "M4,8V12M0,12H8M0,12a4,4 0 1,0 8,0",
                )
            }//END_OF_SYMBOL( Boat )

            inner class Kayak():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=24.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3610, 0.4600)
                override val pAr= arrayListOf(
                    "M4,8V12M0,12H8M0,12a4,4 0 1,0 8,0",
                    "M10,16a2.5,2.5 0 0,1 4,0M14,16a2.5,2.5 0 0,0 4,0M11,16L15,12M13,18L17,14M11,16L13,18M15,12L17,14M16,13L18,11M17,10L21,6M19,12L23,8M17,10L19,12M21,6L23,8",
                )
            }//END_OF_SYMBOL( Kayak )

        } //END_OF_GROUPCODE( TRANSPORT_WATER )

    }//END_OF_SUPERGROUP( SCENES )

    //==============================================================================================================

    inner class CONCEPTS() {//BEGIN SUPERGROUP( WORLD )

        inner class TIME {

            inner class Clock():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6060, 0.2700)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V12M4,12H8",
                    "M3,4H5M3,6H5M3,4V6M5,4V6",
                )
            }//END_OF_SYMBOL( Clock )

            inner class Day():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8520, 2.0300)
                override val pAr= arrayListOf(
                    "M0,16H8M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                )
            }//END_OF_SYMBOL( Day )

            inner class Month():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3080, 0.3300)
                override val pAr= arrayListOf(
                    "M0,8V16M0,8a4,4 0 1,1 0,8",
                )
            }//END_OF_SYMBOL( Month )

            inner class Night():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5320, 2.0400)
                override val pAr= arrayListOf(
                    "M0,16H8M4,8a5,5 0 0,1 0,8M4,8a4,4 0 1,1 0,8",
                )
            }//END_OF_SYMBOL( Night )

            inner class Year():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8940, 2.5200)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M6,10L8,12M8,12L10,10",
                )
            }//END_OF_SYMBOL( Year )

        } //END_OF_GROUPCODE( TIME )


        //inner class ENERGY {
        //
        //    inner class Bioenergy():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=25.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.4940, 2.4400)
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M20,12V16M20,16a4,4 0 0,1 4,-4",
        //        )
        //    }//END_OF_SYMBOL( Bioenergy )
        //
        //    inner class Energy():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.9970, 0.8500)
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //        )
        //    }//END_OF_SYMBOL( Energy )
        //
        //    inner class Energymental():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.6870, 0.7600)
        //        override val pAr= arrayListOf(
        //            "M0,12L4,8M0,12H4M0,16L4,12",
        //            "M6,12a4,4 0 1,1 8,0",
        //        )
        //    }//END_OF_SYMBOL( Energymental )
        //
        //    inner class Energyphysical():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.6410, 0.8400)
        //        override val pAr= arrayListOf(
        //            "M0,12L4,8M0,12H4M0,16L4,12",
        //            "M8,8a5,5 0 0,0 0,8M8,8H16M8,16H16M16,8a5,5 0 0,1 0,8",
        //        )
        //    }//END_OF_SYMBOL( Energyphysical )
        //
        //    inner class Geothermalenergy():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.6590, 0.0500)
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,16H26",
        //        )
        //    }//END_OF_SYMBOL( Geothermalenergy )
        //
        //    inner class Oilpower():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.3000, 1.6700)
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,12a2.5,2.5 0 0,1 4,0M22,12a2.5,2.5 0 0,0 4,0M18,16a2.5,2.5 0 0,1 4,0M22,16a2.5,2.5 0 0,0 4,0",
        //        )
        //    }//END_OF_SYMBOL( Oilpower )
        //
        //    inner class Solarenergy():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.9930, 1.6900)
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //        )
        //    }//END_OF_SYMBOL( Solarenergy )
        //
        //    inner class Wavepower():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.5570, 0.6300)
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,16a2.5,2.5 0 0,1 4,0M22,16a2.5,2.5 0 0,0 4,0M18,12L20,14M20,14L22,12",
        //        )
        //    }//END_OF_SYMBOL( Wavepower )
        //
        //    inner class Windpower():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=31.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.0980, 0.9000)
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,8H26M22,16L26,8M22,16H26M22,12H30M28,10L30,12M28,14L30,12",
        //        )
        //    }//END_OF_SYMBOL( Windpower )
        //
        //} //END_OF_GROUPCODE( ENERGY )

        inner class ENUMERATION {

            //NOTE!! These are also in the ADJECTIVES:   None();Few();Some();Many();ManyMany();All()



            inner class None():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0870, 0.8600)
                override val pAr= arrayListOf(
                    "M0,12H4",
                    "M6,10H10M6,14H10M6,10V14M10,10V14"
                )
            }//END_OF_SYMBOL( None )

            inner class Few():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0870, 0.8600)
                override val pAr= arrayListOf(
                    "M0,10L2,8M2,8V16M2,16L4,14",
                    "M6,12L10,16M6,16L10,12",
                    "M7,4L8,6M8,6L9,4",
                )
            }//END_OF_SYMBOL( Few )

            inner class Some():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1860, 2.3800)
                override val pAr= arrayListOf(
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,12H4",
                    "M1.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,4L2,6M2,6L3,4",
                )
            }//END_OF_SYMBOL( Some )

            inner class Many():Symbol() {  //file name:  Groupof  from fileName: group_of,much_of,many_of,quantity_of
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8570, 1.7500)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                )
            }//END_OF_SYMBOL( Many )

            inner class ManyMany():Symbol() {  //file name:
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8570, 1.7500)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    //"M1,4L2,6M2,6L3,4", 'V' Indicator
                    "M6,12L10,16M6,16L10,12"
                )
            }//END_OF_SYMBOL( ManyMany )

            inner class All():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1090, 0.0400)
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16M0,8L8,16M0,16L8,8",
                )
            }//END_OF_SYMBOL( All )

            inner class One():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5820, 0.3000)
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                )
            }//END_OF_SYMBOL( One )

            inner class Two():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5820, 0.3000)
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                    "M3,8  H5  M3,16  H5  M3,8  V16  M5,8  V16",
                )
            }//END_OF_SYMBOL( Two )

            inner class Three():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5820, 0.3000)
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                    "M3,8  H5  M3,16  H5  M3,8  V16  M5,8  V16",
                    "M6,8  H8  M6,16  H8  M6,8  V16  M8,8  V16",
                )
            }//END_OF_SYMBOL( Three )

            inner class Four():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5820, 0.3000)
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                    "M3,8  H5  M3,16  H5  M3,8  V16  M5,8  V16",
                    "M6,8  H8  M6,16  H8  M6,8  V16  M8,8  V16",
                    "M9,8  H11  M9,16 H11  M9,8 V16 M11,8  V16",
                )
            }//END_OF_SYMBOL( Four )

            inner class Five():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5820, 0.3000)
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                    "M3,8  H5  M3,16  H5  M3,8  V16  M5,8  V16",
                    "M6,8  H8  M6,16  H8  M6,8  V16  M8,8  V16",
                    "M9,8  H11  M9,16 H11  M9,8 V16 M11,8  V16",
                    "M12,16  H16  V14  H12 V16"
                )
            }//END_OF_SYMBOL( Five )

        }//END_OF_GROUPCODE( ENUMERATION )

        //inner class PHYSICS {
        //
        //    inner class Physics():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.5820, 0.3000)
        //        override val pAr= arrayListOf(
        //            "M0,12a4,4 0 1,1 8,0M0,16L4,8M4,8L8,16M0,16H8",
        //            "M10,10L18,14M18,10V14M18,10L26,14M14,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //        )
        //    }//END_OF_SYMBOL( Physics )
        //
        //} //END_OF_GROUPCODE( PHYSICS )


        //inner class SCIENCES {
        //
        //    inner class Biology():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.0240, 1.7400)
        //        override val pAr= arrayListOf(
        //            "M0,12a4,4 0 1,1 8,0M0,16L4,8M4,8L8,16M0,16H8",
        //            "M10,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M14,8V16",
        //        )
        //    }//END_OF_SYMBOL( Biology )
        //
        //    inner class Physics():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("Basic.2", false, false, false)
        //        override val sT = Statistics(0.3000, 0.4600)
        //        override val pAr= arrayListOf(
        //            "M0,12a4,4 0 1,1 8,0M0,16L4,8M4,8L8,16M0,16H8",
        //            "M10,10L18,14M18,10V14M18,10L26,14M14,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //        )
        //    }//END_OF_SYMBOL( Physics )
        //
        //} //END_OF_GROUPCODE( SCIENCES )

        inner class COLORSGROUP {

            inner class Colors():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false, true)
                override val sT = Statistics(0.3000, 0.4600)
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,16H4"
                )
            }//END_OF_SYMBOL( Color )

            inner class Red():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false, true, COLORS.name("RED"))
                override val sT = Statistics(0.3000, 0.4600)
                override val pAr= arrayListOf(  "M1,8a2.5,2.5 0 0,1 0,4M1,12a2.5,2.5 0 0,0 0,4", ) //Fire
            }//END_OF_SYMBOL( Red )

            inner class Blue():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false, true, COLORS.name("BLUE"))
                override val sT = Statistics(0.3000, 0.4600)
                override val pAr= arrayListOf("M0,8H8M0,8a2.5,2.5 0 0,1 4,0M4,8a2.5,2.5 0 0,0 4,0") //Cloud
            }//END_OF_SYMBOL( Blue )

            inner class Green():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false, true, COLORS.name("GREEN"))
                override val sT = Statistics(0.3000, 0.4600)
                override val pAr= arrayListOf( "M0,16a4,4 0 0,1 4,-4")   //Grass
            }//END_OF_SYMBOL( Green )

            inner class Yellow():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false, true, COLORS.name("YELLOW"))
                override val sT = Statistics(0.3000, 0.4600)
                override val pAr= arrayListOf("M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0") //Sun
            }//END_OF_SYMBOL( Yellow )

        }//END_OF_GROUPCODE(COLORSGROUP )

    }//END_OF_SUPERGROUP( CONCEPTS )

    //==============================================================================================================

    inner class LANGUAGE() { //BEGIN SUPERGROUP( LANGUAGE )

        inner class PREPOSITIONS {

            inner class At():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3630, 2.8800)
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( At )


            inner class Above():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3630, 2.8800)
                override val pAr= arrayListOf(
                    "M0,12H8",
                    "M3.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Above )

            inner class Below():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3630, 2.8800)
                override val pAr= arrayListOf(
                    "M0,12H8",
                    "M3.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Below )

            inner class Before():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3630, 2.8800)
                override val pAr= arrayListOf(
                    "M-0.2083,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,8V16"
                )
            }//END_OF_SYMBOL( Before )

            inner class After():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3630, 2.8800)
                override val pAr= arrayListOf(
                    "M0,8V16",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( After )

            inner class Inside():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3630, 2.8800)
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Inside )

            inner class Outside():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3630, 2.8800)
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16",
                    "M9.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Outside )

            inner class On():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3630, 2.8800)
                override val pAr= arrayListOf(
                    "M0,16H4M0,14L2,16M2,16L4,14"
                )
            }//END_OF_SYMBOL( On )

            inner class To():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3630, 2.8800)
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12M4,10V14"
                )
            }//END_OF_SYMBOL( To )

        } //END_OF_GROUPCODE( PREPOSITIONS )   //<<EMPTY, SEE NOTE

        inner class ARTICLES {   //BEGIN GROUPCODE( ARTICLES )

            inner class An():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0880, 0.6500)
                override val pAr= arrayListOf(
                    "M0,11L2,13"
                )
            }//END_OF_SYMBOL( An )

            inner class ThatThere():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=4.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0880, 0.6500)
                override val pAr= arrayListOf(
                    "M-0.2083,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,13L3,11",
                )
            }//END_OF_SYMBOL( ThatThere )

            inner class This():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=4.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3630, 2.8800)
                override val pAr= arrayListOf(
                    "M0,13L2,11",
                    "M2.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( This )

        } //END_OF_GROUPCODE( ARTICLES )

        inner class ADJECTIVES {  //BEGIN GROUPCODE( ADJECTIVES )

            inner class BLANK0:Symbol() {override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5);override val pAr= arrayListOf("M0,12")}
            inner class BLANK1:Symbol() {override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5);override val pAr= arrayListOf("M0,12")}
            inner class BLANK2:Symbol() {override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5);override val pAr= arrayListOf("M0,12")}

            inner class All():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1090, 0.0400)
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16M0,8L8,16M0,16L8,8",
                )
            }//END_OF_SYMBOL( All )

            inner class At():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0100, 2.6600)
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( At )

            inner class Bad():Symbol() {  //TODO There are two different 'Bad' files
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0320, 2.7800)
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 4,0q0,3 -4,6q-4,-3 -4,-6",
                    "M3,4L4,6M4,6L5,4",
                    "M10,12H14",
                    "M16,10V13",
                    "M15.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Bad )

            inner class Deep():Symbol() {  //TODO There are two different 'Bad' files
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0320, 2.7800)
                override val pAr= arrayListOf(
                    "M0,8V16M0,14L2,16M2,16L4,14M4,8V16",
                    "M1,4L2,6M2,6L3,4"
                )
            }//END_OF_SYMBOL( Bad )

            inner class Fast():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4120, 2.2800)
                override val pAr= arrayListOf(
                    "M0,12H8M6,10L8,12M6,14L8,12",
                    //"M3,4L4,6M4,6L5,4" Indicator?
                    "M10,10V13", "M9.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Fast )

            inner class Slow():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4120, 2.2800)
                override val pAr= arrayListOf(
                    "M0,12H8M6,10L8,12M6,14L8,12",
                    //"M3,4L4,6M4,6L5,4",
                    "M10,12H14", "M16,10V13",
                    "M15.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Slow )

            inner class Huge():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0320, 2.7800)
                override val pAr= arrayListOf(
                    "M0,8H4M2,8V16M0,16H4",
                    "M1,4L2,6M2,6L3,4",
                    "M6,10V13",
                    "M5.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Huge )

            inner class High_Tall():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0320, 2.7800)
                override val pAr= arrayListOf(
                    "M0,8H4M0,16H4", "M1,4L2,6M2,6L3,4"
                )
            }//END_OF_SYMBOL( High_Tall )

            inner class Big():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0990, 0.5500)
                override val pAr= arrayListOf(
                    "M0,8H4M2,8V16M0,16H4",
                    "M1,4L2,6M2,6L3,4",
                )
            }//END_OF_SYMBOL( Big )

            inner class Cold():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9150, 1.5300)
                override val pAr= arrayListOf(
                    "M0,12L2,10M0,12L2,14M2,12a2.5,2.5 0 0,1 4,0M6,12a2.5,2.5 0 0,0 4,0M4,10H8M4,14H8M4,10V14M8,10V14M10,10L12,12M10,14L12,12",
                    "M5,4L6,6M6,6L7,4",
                )
            }//END_OF_SYMBOL( Cold )

            inner class Coldoppositehot():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4660, 1.1500)
                override val pAr= arrayListOf(
                    "M0,10L2,8M2,8V16M2,16L4,14",
                    "M6,12L8,10M6,12L8,14M10,8a2.5,2.5 0 0,1 0,4M10,12a2.5,2.5 0 0,0 0,4M12,10L14,12M12,14L14,12",
                    "M9,4L10,6M10,6L11,4",
                )
            }//END_OF_SYMBOL( Coldoppositehot )

            inner class Correct():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3300, 1.5100)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0M2,12H6M4,10V14",
                    "M3,4L4,6M4,6L5,4",
                    "M10,10V13",
                    "M9.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Correct )

            inner class Correctthinking():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8400, 2.7700)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0M2,12H6M4,10V14",
                )
            }//END_OF_SYMBOL( Correctthinking )

            inner class Different():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3940, 2.8000)
                override val pAr= arrayListOf(
                    "M0,16L8,8M2,10H6M2,14H6",
                )
            }//END_OF_SYMBOL( Different )

            inner class Dirty():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9370, 0.1500)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M7.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M7,4L8,6M8,6L9,4",
                    "M11.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Dirty )

            inner class Dry():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4170, 2.3100)
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M0,16L8,8",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Dry )

            inner class Empty():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5970, 2.9500)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M4,14a4,4 0 1,1 8,0M10,12L12,14M12,14L14,12",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Empty )

            inner class Far():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9960, 1.5800)
                override val pAr= arrayListOf(
                    "M0,10V14",
                    "M1.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M8,10V14",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Far )

            inner class Fat():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0020, 2.2900)
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12M2,8V16",
                    "M3.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M7.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M10,8V16M10,12L12,10M10,12L12,14",
                    "M5,4L6,6M6,6L7,4",
                )
            }//END_OF_SYMBOL( Fat )

            inner class None():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0870, 0.8600)
                override val pAr= arrayListOf(
                    "M0,12H4",
                    "M6,10H10M6,14H10M6,10V14M10,10V14"
                )
            }//END_OF_SYMBOL( None )

            inner class Few():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0870, 0.8600)
                override val pAr= arrayListOf(
                    "M0,10L2,8M2,8V16M2,16L4,14",
                    "M6,12L10,16M6,16L10,12",
                    "M7,4L8,6M8,6L9,4",
                )
            }//END_OF_SYMBOL( Few )

            inner class Some():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1860, 2.3800)
                override val pAr= arrayListOf(
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,12H4",
                    "M1.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,4L2,6M2,6L3,4",
                )
            }//END_OF_SYMBOL( Some )

            inner class Full():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1730, 1.2000)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M0,12H8",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Full )

            inner class Good():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1900, 2.2000)
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 4,0q0,3 -4,6q-4,-3 -4,-6",
                    "M3,4L4,6M4,6L5,4",
                    "M10,12H14M12,10V14",
                    "M16,10V13",
                    "M15.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Good )

            inner class Many():Symbol() {  //file name:  Groupof  from fileName: group_of,much_of,many_of,quantity_of
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8570, 1.7500)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                )
            }//END_OF_SYMBOL( Many )

            inner class ManyMany():Symbol() {  //file name:
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8570, 1.7500)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    //"M1,4L2,6M2,6L3,4", 'V' Indicator
                    "M6,12L10,16M6,16L10,12"
                )
            }//END_OF_SYMBOL( ManyMany )

            inner class Heavy():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4160, 1.6100)
                override val pAr= arrayListOf(
                    "M0,12H8M2,16L4,12M4,12L6,16M2,16H6",
                    "M3,4L4,6M4,6L5,4",
                    "M10,14L12,16M12,16L14,14M12,8V16",
                )
            }//END_OF_SYMBOL( Heavy )

            inner class Here():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0530, 0.2200)
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Here )

            inner class In():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6490, 2.1000)
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( In )

            inner class Incorrect():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5120, 1.7600)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0M2,12H6",
                    "M3,4L4,6M4,6L5,4",
                    "M10,10V13",
                    "M9.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Incorrect )

            inner class IncorrectThinking():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3390, 1.3000)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0M2,12H6",
                )
            }//END_OF_SYMBOL( IncorrectThinking )

            inner class Leftturn():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0000, 1.2900)
                override val pAr= arrayListOf(
                    "M0,8H4M4,8V16",
                )
            }//END_OF_SYMBOL( Leftturn )

            inner class Lightweight():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2400, 2.8500)
                override val pAr= arrayListOf(
                    "M0,12H8M2,16L4,12M4,12L6,16M2,16H6",
                    "M3,4L4,6M4,6L5,4",
                    "M10,12H14M12,12V16M10,16H14",
                )
            }//END_OF_SYMBOL( Lightweight )

            inner class Linear():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5460, 1.0600)
                override val pAr= arrayListOf(
                    "M1,8V16",
                    "M0,4L1,6M1,6L2,4",
                )
            }//END_OF_SYMBOL( Linear )

            inner class Little():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6410, 2.7800)
                override val pAr= arrayListOf(
                    "M0,12H4M2,12V16M0,16H4",
                    "M1,4L2,6M2,6L3,4",
                )
            }//END_OF_SYMBOL( Little )

            inner class Long():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6420, 0.6700)
                override val pAr= arrayListOf(
                    "M0,10V14M0,12H8M8,10V14",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Long )

            inner class Name():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0650, 2.4200)
                override val pAr= arrayListOf(
                    "M0,8L8,16M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                )
            }//END_OF_SYMBOL( Name )

            inner class Nearness():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9620, 0.1100)
                override val pAr= arrayListOf(
                    "M0,10V14M4,10V14",
                )
            }//END_OF_SYMBOL( Nearness )

            inner class New():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6970, 1.8400)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V16M4,12H12M10,10L12,12M10,14L12,12",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( New )

            inner class Old():Symbol() {  //Opposite New
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7320, 1.6000)
                override val pAr= arrayListOf(
                    "M0,10L2,8M2,8V16M2,16L4,14",
                    "M6,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M10,8V16M10,12H18M16,10L18,12M16,14L18,12",
                    "M9,4L10,6M10,6L11,4",
                )
            }//END_OF_SYMBOL( Oldoppositenew )

            inner class Rightturn():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4890, 0.2600)
                override val pAr= arrayListOf(
                    "M0,8H4M0,8V16",
                )
            }//END_OF_SYMBOL( Rightturn )

            inner class Rotation():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7420, 1.9000)
                override val pAr= arrayListOf(
                    "M0,14L2,12M2,12L4,14M6,8a4,4 0 1,1 -4,4",
                )
            }//END_OF_SYMBOL( Rotation )

            inner class Rotten():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=25.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4720, 1.6600)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V16M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                    "M3,4L4,6M4,6L5,4",
                    "M10,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M12,10V14",
                    "M11,4H13M11,6H13M11,4V6M13,4V6",
                    "M16,16L24,8",
                )
            }//END_OF_SYMBOL( Rotten )

            inner class Sharpknife():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0540, 2.2700)
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L8,8",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Sharpknife )

            inner class Short():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1920, 0.3200)
                override val pAr= arrayListOf(
                    "M0,10V14M0,12H4M4,10V14",
                    "M1,4L2,6M2,6L3,4",
                )
            }//END_OF_SYMBOL( Short )

            inner class Smooth():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7530, 1.0900)
                override val pAr= arrayListOf(
                    "M0,12L4,16M4,8V16",
                    "M1,4L2,6M2,6L3,4",
                    "M6,8V16M6,16H10M10,8V16",
                )
            }//END_OF_SYMBOL( Smooth )

            inner class Squaredescription():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9680, 0.3000)
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,4L2,6M2,6L3,4",
                    "M6,10H10M6,14H10M6,10V14M10,10V14",
                    "M12,8H20M12,16H20M12,8V16M20,8V16",
                )
            }//END_OF_SYMBOL( Squaredescription )

            inner class There():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8540, 0.2900)
                override val pAr= arrayListOf(
                    "M-0.2083,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,12L6,10M4,12L6,14",
                )
            }//END_OF_SYMBOL( There )

            inner class Thin():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6150, 2.9800)
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12M2,8V16M6,8V16M6,12L8,10M6,12L8,14",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Thin )

            inner class Thin2():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5460, 2.3900)
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12M2,8V16M6,8V16M6,12L8,10M6,12L8,14",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Thin )

            inner class Warm():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(1.0000, 0.1800)
                override val pAr= arrayListOf(
                    "M0,12L2,10M0,12L2,14M4,8a2.5,2.5 0 0,1 0,4M4,12a2.5,2.5 0 0,0 0,4M6,10L8,12M6,14L8,12",
                    "M3,4L4,6M4,6L5,4",
                    "M10,12H14M12,12V16M10,16H14",
                )
            }//END_OF_SYMBOL( Warm )

            inner class Hot_temperature:Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(1.0000, 0.1800)
                override val pAr= arrayListOf(
                    "M0,12L2,10M0,12L2,14M4,8a2.5,2.5 0 0,1 0,4M4,12a2.5,2.5 0 0,0 0,4M6,10L8,12M6,14L8,12",
                    "M3,4L4,6M4,6L5,4"
                )
            }//END_OF_SYMBOL( Hot_temperature )

            inner class Wet():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6990, 1.2600)
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Wet )

            inner class What():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7260, 1.8000)
                override val pAr= arrayListOf(
                    "M-0.2083,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0.27773333333333333,12q0.06943,0.69433 -0.5554,1.1109",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2.27773,12q0.06943,0.69433 -0.55546,1.11093",
                    "M4,10H8M4,14H8M4,10V14M8,10V14",
                )
            }//END_OF_SYMBOL( What )

            inner class WhatThing():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9430, 0.9700)
                override val pAr= arrayListOf(
                    "M0,11a1,1 0 1,1 1,1M1,12V13",
                    "M0.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,10H8M4,14H8M4,10V14M8,10V14",
                )
            }//END_OF_SYMBOL( WhatThing )

            inner class Who():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2770, 2.5200)
                override val pAr= arrayListOf(
                    "M0,11a1,1 0 1,1 1,1M1,12V13",
                    "M0.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,16H8M6,8V16",
                )
            }//END_OF_SYMBOL( Who )

            inner class Wide():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3000, 0.3600)
                override val pAr= arrayListOf(
                    "M0,8V16M0,12L2,10M0,12L2,14M6,10L8,12M6,14L8,12M8,8V16",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Wide )

            inner class With():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2550, 1.8900)
                override val pAr= arrayListOf(
                    "M0,8H4M2,6V10",
                )
            }//END_OF_SYMBOL( With )

        } //END_OF_GROUPCODE( ADJECTIVES )

        inner class PRONOUNS {
            inner class He():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.2720, 2.9500)
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16",
                    "M6,13a1,1 0 1,1 1,1M7,14a1,1 0 1,1 -1,1",
                )
            }//END_OF_SYMBOL( He )

            inner class I():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3160, 2.1500)
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16",
                    "M6,13L7,12M7,12V16M6,16H8",
                )
            }//END_OF_SYMBOL( I )

            inner class Me():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7340, 1.8200)
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M0,16H4",
                    "M6,13L7,12M7,12V16M6,16H8",
                )
            }//END_OF_SYMBOL( Me )

            inner class She():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3700, 0.6000)
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M0,16H4",
                    "M6,13a1,1 0 1,1 1,1M7,14a1,1 0 1,1 -1,1",
                )
            }//END_OF_SYMBOL( She )

            inner class They():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6960, 0.9200)
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M0,16H4",
                    "M1,4L3,6M1,6L3,4",
                    "M6,13a1,1 0 1,1 1,1M7,14a1,1 0 1,1 -1,1",
                )
            }//END_OF_SYMBOL( They )

            inner class Those():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=4.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3380, 2.2500)
                override val pAr= arrayListOf(
                    "M-0.2083,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,13L3,11",
                    "M1,4L3,6M1,6L3,4",
                )
            }//END_OF_SYMBOL( Those )

            inner class We():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.3850, 2.2000)
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M0,16H4",
                    "M1,4L3,6M1,6L3,4",
                    "M6,13L7,12M7,12V16M6,16H8",
                )
            }//END_OF_SYMBOL( We )

            inner class You():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5480, 2.5800)
                override val pAr= arrayListOf(
                    "M0,16H4M2,8V16",
                    "M6,13a1,1 0 1,1 2,0M6,16L8,13M6,16H8",
                )
            }//END_OF_SYMBOL( You )

        } //END_OF_GROUPCODE( PRONOUNS )


        inner class INTERJECTIONS {

            inner class Yes_indeed():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7630, 2.0400)
                override val pAr= arrayListOf(
                    "M0,12H4M2,10V14", "M6,10V13",
                    "M5.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M8,10V13",  "M7.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Yes_indeed )

            inner class No_indeed():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7630, 2.0400)
                override val pAr= arrayListOf(
                    "M0,12H4", "M6,10V13",
                    "M5.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M8,10V13",  "M7.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( No_indeed )

            inner class Wow_super():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7630, 2.0400)
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 4,0q0,3 -4,6q-4,-3 -4,-6M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    //"M3,4L4,6M4,6L5,4", indicator...
                    "M10,12H14M12,10V14",
                    "M16,10V13",
                    "M15.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Wow_super )

            inner class Help_exclamatory():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7630, 2.0400)
                override val pAr= arrayListOf(
                    "M0,16L4,8M2,16H6M4,8V16",
                    "M3,6L4,4M4,4L5,6",
                    //exclamation mark added gkmod 0329
                    "M6,10V13", "M5.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Help_exclamatory )
        }//END_OF_GROUPCODE( INTERJECTIONS )

        inner class ADVERBS {

            inner class How():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7630, 2.0400)
                override val pAr= arrayListOf(
                    "M-0.2083,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0.2777,12q0.0694,0.6943,-0.5554,1.1109",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2.2777,12q0.0694,0.6943,-0.5554,1.1109",
                    "M4,16L6,12M6,12L8,16",
                )
            }//END_OF_SYMBOL( How )

            inner class Howq():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9910, 1.3000)
                override val pAr= arrayListOf(
                    "M0,11a1,1 0 1,1 1,1M1,12V13",
                    "M0.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,16L6,12M6,12L8,16",
                )
            }//END_OF_SYMBOL( Howq )

            inner class WHEN():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.7910, 1.9800)
                override val pAr= arrayListOf(
                    "M0,11a1,1 0 1,1 1,1M1,12V13",
                    "M0.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M8,8V12M8,12H12",
                )
            }//END_OF_SYMBOL( When )

            inner class Where():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.6980, 0.5300)
                override val pAr= arrayListOf(
                    "M0,16H8",
                    "M2.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.2777,12q0.0694,0.6943,-0.5554,1.1109",
                    "M4.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.2777,12q0.0694,0.6943,-0.5554,1.1109",
                )
            }//END_OF_SYMBOL( Where )

            inner class Whereq():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.1310, 1.9100)
                override val pAr= arrayListOf(
                    "M0,16H8M3,11a1,1 0 1,1 1,1M4,12V13",
                    "M3.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Whereq )

            inner class Not():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9550, 1.6200)
                override val pAr= arrayListOf(
                    "M0,12H4",
                    "M6,10V13",
                    "M5.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Not )

        } //END_OF_GROUPCODE( ADVERBS )

        inner class VERBS { //STARTER SET, EXCEPTIONS

            inner class BLANK:Symbol() {override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5);override val pAr= arrayListOf("M0,12")}//END_OF_SYMBOL( BLANK )

            inner class Am_Is():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12 a2,2 0 1,1 4,0 a2,2 0 1,1 -4,0  M2,10 V14"
                    //"M1,6L2,4M2,4L3,6"
                )
            }//END_OF_SYMBOL( Is )

            inner class Have():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,16H4M0,14H4M2,12V16",
                    //"M1,6L2,4M2,4L3,6" Indicator
                )
            }//END_OF_SYMBOL( Have )

            inner class Can():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M-0.2083,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,12L4,16M4,16L6,12",
                    //"M3,6L4,4M4,4L5,6" Indicator
                )
            }//END_OF_SYMBOL( Can )

            inner class See():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    //"M1,6L2,4M2,4L3,6" //Indicator
                )
            }//END_OF_SYMBOL( See )

            inner class Hear():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0q0,3 -4,6",
                    //"M1,6L2,4M2,4L3,6" //Indicator
                )
            }//END_OF_SYMBOL( Hear )

            inner class Feel():Symbol() { //Touch
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                   "M0,12L4,16M4,8V16",
                   //"M1,6L2,4M2,4L3,6" //Indicator
                )
            }//END_OF_SYMBOL( Feel )

            inner class Know():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0M0,12V16M0,16H8M8,12V16",
                     //"M3,6L4,4M4,4L5,6"
                )
            }//END_OF_SYMBOL( Know )

            inner class Get():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M2,14L4,16M4,16L6,14M4,8V16"
                    //"M3,6L4,4M4,4L5,6"
                )
            }//END_OF_SYMBOL( Get )

            inner class Give():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M2,10L4,8M4,8L6,10M4,8V16",
                    //"M3,6L4,4M4,4L5,6"
                )
            }//END_OF_SYMBOL( Give )

            inner class Stand():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=23.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,8V16M0,16H4",
                    //"M1,6L2,4M2,4L3,6"
                )
            }//END_OF_SYMBOL( Stand )

            inner class Sit():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,8V12M0,12H4M4,12V16",
                    //"M1,6L2,4M2,4L3,6"
                )
            }//END_OF_SYMBOL( Sit )

            inner class Lie_down():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,16H8M8,12V16"
                )
            }//END_OF_SYMBOL( Lie_down )

            inner class Eat():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    //"M5,6 L6,4 M6,4 L7,6", Action Indicator
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0,16H4",
                    //"M1,6L2,4M2,4L3,6"
                )
            }//END_OF_SYMBOL( Eat )

            inner class Move_carry():Symbol() { //GK custom added 0322
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M8,12H12M10,10L12,12M10,14L12,12"
                )
            }//END_OF_SYMBOL( Move )

            inner class Crawl():Symbol() { //GK custom added 0322
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,8V12M0,12L4,16M0,16H4",
                    //"M1,6L2,4M2,4L3,6",  Indicator
                    "M6,12H14M12,10L14,12M12,14L14,12"
                )
            }//END_OF_SYMBOL( Crawl )

            inner class Fly():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,8a4,4 0 0,1 4,4M4,12a4,4 0 0,1 4,-4",
                )
            }//END_OF_SYMBOL( Fly )

            inner class Walk():Symbol() { //GK custom added 0322
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,16L4,8M4,8L8,16M0,16H4M8,16H12",
                    //"M3,6L4,4M4,4L5,6"   Indicator
                )
            }//END_OF_SYMBOL( Walk )

            inner class Run():Symbol() { //GK custom added 0322
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,16L4,8M4,8L8,16M0,16H4M8,16H12",
                    "M08,12H13.5  M12,10L14,12   M12,14L14,12",
                    "M15.5,10V13",
                    "M15.3,14a0.2083,0.2083 0 1,1 0.4166,0  a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Run )

            inner class Bounce():Symbol() { //GK custom added 0322
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,8L4,12M0,16L4,12M0,14V16M0,16H2M4,8V16"
                )
            }//END_OF_SYMBOL( Bounce )

            inner class Jump():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,16a4,4 0 1,1 8,0M6,14L8,16M8,16L10,14"
                )
            }//END_OF_SYMBOL( Jump )

            inner class Pull():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,10H4M0,14H4M0,10V14M4,10V14M4,12H8M6,10L8,12M6,14L8,12",
                    //"M1,6L2,4M2,4L3,6"
                )
            }//END_OF_SYMBOL( Pull )

            inner class Push():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12H8M6,10L8,12M6,14L8,12M8,10H12M8,14H12M8,10V14M12,10V14",
                    //"M9,6L10,4M10,4L11,6"
                )
            }//END_OF_SYMBOL( Push )

            inner class Drink():Symbol() { //GKMOD
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2,16H6",
                    //"M3,6L4,4M4,4L5,6"  indicator
                )
            }//END_OF_SYMBOL( Drink )

            inner class Hunt():Symbol() { //GKMOD
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=23.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    //"M0,12H12M1,12a2,2 0 1,0 4,0", rifle
                    "M0,8a4,4 0 1,1 0,8M0,12H8M6,10L8,12M6,14L8,12",
                    //"M5,6 L6,4 M6,4 L7,6", Action Indicator
                    "M12,12 H20 M12,16  L14,12  M14,12  L16,16  M16,16   L18,12  M18,12   L20,16"
                )
            }//END_OF_SYMBOL( Hunt )

            inner class Sleep():Symbol() { //GKMOD
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    //"M3,6L4,4M4,4L5,6"
                )
            }//END_OF_SYMBOL( Sleep )



        }//END_OF_GROUPCODE( VERBS )

        inner class CONJUNCTIONS {

            inner class And():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12H4M2,10V14"
                )
            }//END_OF_SYMBOL( And )

            inner class Or():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9600, 0.6100)
                override val pAr= arrayListOf("M0,12L2,10M0,12L2,14", "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0")
            }//END_OF_SYMBOL( Or )

            inner class But():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12H4M4,10V14"
                )
            }//END_OF_SYMBOL( But )

            inner class If():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0020, 2.9200)
                override val pAr= arrayListOf(
                    "M0,11a1,1 0 1,1 1,1M1,12V13",
                    "M0.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,10L6,12M4,14L6,12",
                )
            }//END_OF_SYMBOL( If )

            inner class Unless():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.0020, 2.9200)
                override val pAr= arrayListOf("M0,12H4M4,10V14", "M6,12L8,10M6,12L8,14","M7.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0")
            }//END_OF_SYMBOL( Unless )

            inner class Therefore():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4120, 2.2800)
                override val pAr= arrayListOf("M0,10L2,12M0,14L2,12M0,10V14")
            }//END_OF_SYMBOL( Therefore )

            inner class Because():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.4120, 2.2800)
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12M0,10V14",
                    "M4,11a1,1 0 1,1 1,1M5,12V13",
                    "M4.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Because )

        }//END_OF_GROUPCODE( CONJUNCTIONS )

        inner class PUNCTUATION {

            inner class Space():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf("M0,0")
            }

            inner class Comma():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0.27773333333333333,16q0.06943333333333333,0.6943333333333334 -0.5554666666666667,1.1109333333333333",
                )
            }//END_OF_SYMBOL( Comma )

            inner class QuestionMark():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5640, 1.0600)
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 2,2M2,12V14",
                    "M1.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( QuestionMark )

            inner class Ellipse():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5640, 1.0600)
                override val pAr= arrayListOf(
                    "M1.5, 16  a0.2083,0.2083 0 1,1 0.4166,0  a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4, 16  a0.2083,0.2083 0 1,1 0.4166,0  a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6.5, 16  a0.2083,0.2083 0 1,1 0.4166,0  a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Ellipse )

            inner class ExclamationMark():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5640, 1.0600)
                override val pAr= arrayListOf(
                    "M0,8V14",
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Exclamationmark )

            inner class Period():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9450, 2.5700)
                override val pAr= arrayListOf(
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Period )

            inner class AcceptKey():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=6.0, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val pAr= arrayListOf(
                    "M4,8 V12 M0,12   L2,10  M0,12  L2,14  M0,12  H4"
                )
            }//END_OF_SYMBOL( AcceptKey )

            inner class EndOfParagraph():Symbol() {//Temp, using Card symbol (page laying down) Modified to make width of Page
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9450, 2.5700)
                override val pAr= arrayListOf("M0,10  H4  M0,14  H4  M0,10  V14  M4,10  V14")
            }//END_OF_SYMBOL( EndOfParagraph )

            inner class EndOfPage():Symbol() { //Temp, using PAGEe symbol
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9450, 2.5700)
                override val pAr= arrayListOf("M0,8 H4  M0,16H4  M0,8  V16  M4,8  V16")
            }//END_OF_SYMBOL( EndOfPage )

            inner class EndOfDocument():Symbol() { //Temp, using Book symbol
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9450, 2.5700)
                override val pAr= arrayListOf("M0,8H4M0,16H4M0,8V16M4,8V16M4,8H8M4,16H8M8,8V16")
            }//END_OF_SYMBOL( EndOfDocument )

            //  <path d="M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V16M4,12H12M10,10L12,12M10,14L12,12"></path><path d="M3,4L4,6M4,6L5,4"></path>

            inner class NewBuffer():Symbol() { //Temp, using Book symbol
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9450, 2.5700)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V16M4,12H12M10,10L12,12M10,14L12,12",
                    "M3,4L4,6M4,6L5,4"
                )
            }//END_OF_SYMBOL( NewBuffer)

           inner class UpArrowSentence():Symbol() { //Temp, using Book symbol
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9450, 2.5700)
                override val pAr= arrayListOf("M0,10L2,8M2,8L4,10M2,8V16")
            }//END_OF_SYMBOL( UpArrowSentence )

            inner class DnArrowSentence():Symbol() { //Temp, using Book symbol
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.9450, 2.5700)
                override val pAr= arrayListOf("M0,14L2,16M2,16L4,14M2,8V16")
            }//END_OF_SYMBOL( DnArrowSentence )

            //inner class UpArrowParagraph():Symbol() { //Temp, using Book symbol
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
            //    override val hD = Header("Basic.2", false, false, false)
            //    override val sT = Statistics(0.9450, 2.5700)
            //    override val pAr= arrayListOf("M0,10L2,8M2,8L4,10M2,8V16")
            //}//END_OF_SYMBOL( UpArrowParagraph )
            //
            //inner class DnArrowParagraph():Symbol() { //Temp, using Book symbol
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
            //    override val hD = Header("Basic.2", false, false, false)
            //    override val sT = Statistics(0.9450, 2.5700)
            //    override val pAr= arrayListOf("M0,14L2,16M2,16L4,14M2,8V16")
            //}//END_OF_SYMBOL( DnArrowParagraph )
            //
            //
            //inner class UpArrowPage():Symbol() { //Temp, using Book symbol
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
            //    override val hD = Header("Basic.2", false, false, false)
            //    override val sT = Statistics(0.9450, 2.5700)
            //    override val pAr= arrayListOf("M0,10L2,8M2,8L4,10M2,8V16")
            //}//END_OF_SYMBOL( UpArrowPage )
            //
            //inner class DnArrowPage():Symbol() { //Temp, using Book symbol
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
            //    override val hD = Header("Basic.2", false, false, false)
            //    override val sT = Statistics(0.9450, 2.5700)
            //    override val pAr= arrayListOf("M0,14L2,16M2,16L4,14M2,8V16")
            //}//END_OF_SYMBOL( DnArrowPage )


            //SPECIAL KEYBOARD COMMANDS, NOT SYMBOLS
            inner class Backspace():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf("M0,12L2,10M0,12L2,14M0,12H8")
            }

            inner class ListToConsole1():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf("M0,6 H12", "M0,9 H12", "M0,12 H12")
            }

            inner class ListToConsole2():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf("M0,6 H12", "M0,9 H12", "M0,12 H12")
            }

            inner class ClearAll():Symbol() { //emptying, voidance, without Indicator
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf("M0,12a4,4 0 1,0 8,0M4,14a4,4 0 1,1 8,0M10,12L12,14M12,14L14,12")
            }

            inner class ReadFile1():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,6L2,4M2,4L3,6",
                    "M6,8H10M6,16H10M6,8V16M10,8V16",
                    "M1,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }

            inner class ReadFile2():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5750, 0.4200)
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,6L2,4M2,4L3,6",
                    "M6,8H10M6,16H10M6,8V16M10,8V16",
                    "M1,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }


//            inner class ListToPrinter():Symbol() {
//                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
//                override val hD = Header("Basic.2", false, false, false)
//                override val sT = Statistics(0.5750, 0.4200)
//                override val pAr= arrayListOf("M0,6 H12", "M0,9 H12", "M0,12 H12")
//            }


        } //END_OF_GROUPCODE( PUNCTUATION )

        inner class EMOJIS {

            inner class Facefrowny():Symbol() {//BROKEN, TODO: FIX?
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.8570, 0.6900)
                override val pAr= arrayListOf(
                      "M0,12  a4,4 0 1,1  8,0 a4,4 0 1,1 -8,0",
                      "M5.4,14.5 a1.3, 1.3 0 1 ,0 -3, 0.01",
                )
            }//END_OF_SYMBOL( Facefrowny )

            inner class Faceidea():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=10.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5180, 2.8500)
                override val pAr= arrayListOf(
                    "M 0.5, 12 a4.8, 4 0 1,1 8,0 a4,4 0 1,1 -8,0",
                    "M2.3,13.8 H5.5",
                    "M2.5,11 a0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.5,11 a0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4.5,2 V8",
                    "M4.35, .78 a0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166, 0",
                )
            }//END_OF_SYMBOL( Faceidea )

            inner class Facesmiley():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5790, 0.4700)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                    "M2.4,13 a1.5,1.5 0 1,0 3.0,0",
                    "M2,11a 0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5,11a 0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Facesmiley )

            inner class Facestraight():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("Basic.2", false, false, false)
                override val sT = Statistics(0.5210, 1.1300)
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0 a4,4 0 1,1 -8,0",
                    "M2.2,13.8   H5.5",
                    "M2,11 a0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5,11 a0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Facestraight )

        } //END_OF_GROUPCODE( EMOJIS )

    } //END_OF_SUPERGROUP( LANGUAGE )

    //========================================================EHD SPLICE GENERATED SYMBOLS HERE==================================================

    //private fun keyName(symClass: Symbol) = symClass::class.qualifiedName!!.replace("SymbolPaths.", "").trim()

    private fun keyAdd(symClass: Symbol, isStopperTF: Boolean = false) {
        val keyName = keyName(symClass)
        keyMap.put(keyName, symClass)
        if (isStopperTF)stopperList.add(keyName)
    }

    //==================================================================================================================


    init {
        //These Exist, but not used to conserve screen space
        //with(SUPERGROUPS() ) {keyAdd(WORLD()); keyAdd(LIFE());keyAdd(WORLD());keyAdd(LIFE());keyAdd(PERSON());keyAdd(MAN_MADE()); keyAdd(CONCEPTS());keyAdd(LANGUAGE())}

        with( GROUPS().WORLD() ) {
            keyAdd(SKY())
            keyAdd(GEOLOGY())
            keyAdd(MATERIALS())
            keyAdd(PHENOMENA())
        }

        with (GROUPS().ANIMATE()) {
            keyAdd(PLANTS())
            keyAdd(ANIMAL_TYPES())
            keyAdd(ANIMALS_FROM())
        }

        with(GROUPS().PERSON() ) {
            keyAdd(FACE())
            keyAdd(BODY_PARTS())
            keyAdd(FAMILY())
            keyAdd(PETS())
            keyAdd(PERSONAL_ARTIFACTS())
            keyAdd(KITCHEN_TOOLS())
        }

        with( GROUPS().SCENES()) { keyAdd(CONSTRUCTIONS());keyAdd(TRANSPORT_LAND());keyAdd(TRANSPORT_WATER());keyAdd(TRANSPORT_AIR()) }

        with( GROUPS().CONCEPTS()) {keyAdd(TIME());keyAdd(ENUMERATION());keyAdd(COLORSGROUP())}  //keyAdd(ENERGY());


        // 1"PRONOUNS", 2"ARTICLES",   3"ADVERBS",    4"VERBS", 5"ADJECTIVES", 6"PREPOSITIONS",  7"CONJUNCTIONS", 8"INTERJECTIONS", 9"EMOJIS", 10"PUNCTUATION"
        with(GROUPS().LANGUAGE()) {
            keyAdd(PRONOUNS())
            keyAdd(ARTICLES());keyAdd(ADVERBS())
            keyAdd(VERBS())
            keyAdd(ADJECTIVES())
            keyAdd(PREPOSITIONS())
            keyAdd(CONJUNCTIONS())
            keyAdd(INTERJECTIONS())
            keyAdd(EMOJIS())
            keyAdd(PUNCTUATION())
        }

        //====================================BEGIN SYMBOL LEVEL=======================================================

        with(WORLD().MATERIALS()) {
            keyAdd(Wood());keyAdd(Metal(),true);
            keyAdd(Stone());keyAdd(Sand(),true);
            keyAdd(Mud(),true);
            keyAdd(Salt(),true);
            keyAdd(Powder(),true);
        }

        with(WORLD().SKY()) {
            keyAdd(Sky(),true);
            keyAdd(Cloud(), true);
            keyAdd(Moon());keyAdd(Sun()); keyAdd(Star(), true);
            keyAdd(Snow(), true);
            keyAdd(Earth(), true);
        }

        with(WORLD().GEOLOGY()) {
            keyAdd(Ground(),true)
            keyAdd(Mountain(),true)
            keyAdd(Water(),true)
            keyAdd(Rain(),true)
            keyAdd(Lake());keyAdd(Ocean(),true)
            keyAdd(River(),true)
        }

        with( WORLD().PHENOMENA() ) {
            keyAdd(Fire());keyAdd(Ashes());keyAdd(Smoke(),true);
            keyAdd(Cloud(),true)
            keyAdd(Fog(),true)
            keyAdd(Ice());keyAdd(Snow(),true)
            keyAdd(Wind(),true)
        }

        //=================================NEW SUPERGROUP===============================================================

        with( ANIMATE().PLANTS() ) {
            keyAdd(Seed(),true)
            keyAdd(Tree());keyAdd(Root());keyAdd(Bark());keyAdd(Stick());keyAdd(Leaf(),true)
            keyAdd(Forest(),true)
            keyAdd(Flower(),true)
            keyAdd(Fruit(),true)
            keyAdd(Grass(),true)
        }

        with( ANIMATE().ANIMAL_TYPES() ) {
            keyAdd(Animal());keyAdd(Rabbit(), true)
            keyAdd(Wings());keyAdd(Bird(),true)
            keyAdd(Fishanimal(),true)
            keyAdd(Frog(),true)  //amphibians...
            keyAdd(Insect());keyAdd(Louse(),true);
            keyAdd(Worm(),true)
            keyAdd(Snake(),true)
        }

        with( ANIMATE().ANIMALS_FROM() ) {
            keyAdd(Blood(),true)
            keyAdd(Egg());keyAdd(Feather(),true)
            keyAdd(Horn(),true)
            keyAdd(Meat(),true)
            keyAdd(Tail(),true)
        }

        //=================================NEW SUPERGROUP===============================================================
        //1 (x)PERSON().FACE()
        with( PERSON().FACE() ) {
            keyAdd(Eye());keyAdd(Ear()); keyAdd(Nose(),true)
            keyAdd(Mouth());keyAdd(Tongue());keyAdd(Tooth(),true)
            keyAdd(Chin(),true)
            keyAdd(Hairhead());keyAdd(Headwithhair(),true)
        }
        //2 (x)PERSON().BODY_PARTS()
        with( PERSON().BODY_PARTS() ) {
            keyAdd(Bone(), true)
            keyAdd(Backbody());keyAdd(Neckbody());keyAdd(Neckhead());keyAdd(Breasts(), true)
            keyAdd(Foot());keyAdd(Hand());keyAdd(Fingernail(), true)
            keyAdd(Heart());keyAdd(Skin(), true)
            keyAdd(Intestines()); keyAdd(Stomach());keyAdd(Liver());keyAdd(Oil(), true)
            keyAdd(Leg());keyAdd(Knee(), true)
        }
        //3 (x)PERSON().FAMILY()
        with( PERSON().FAMILY() ) {
            keyAdd(Baby());keyAdd(Child());keyAdd(Person(), true);
            keyAdd(Father());keyAdd(Mother(), true)
            keyAdd(Brother());keyAdd(Sister(), true)
            keyAdd(Man());keyAdd(Woman(), true)
            keyAdd(Husband());keyAdd(Wife(), true)
        }
        //4 (x)PERSON().PETS()
        with (PERSON().PETS()) {
            keyAdd(Cat())
            keyAdd(Dog())
        }

        //5 (x)PERSON().PERSONAL_ARTIFACTS()
        with( PERSON().PERSONAL_ARTIFACTS() ) {
            keyAdd( Brush ());keyAdd(Toothbrush(), true)
            keyAdd(Hammer());keyAdd(Knife(), true)
            keyAdd(Thread());keyAdd(Needle(), true);
            keyAdd(Saw());keyAdd(Shovel(), true)   //Bigger + impersonal  + todo: add Rake
            keyAdd(Rope(), true)                   //thread
        }
        //6 (x)PERSON().KITCHEN_TOOLS()
        with ( PERSON().KITCHEN_TOOLS() ) {
            keyAdd(Container());keyAdd(Dish(), true)
            keyAdd(Cup());keyAdd(Drinkingglass(), true)
            keyAdd(Fork());keyAdd(Knife());keyAdd(Spoon())
        }

        //=================================NEW SUPERGROUP===============================================================
        with(SCENES().CONSTRUCTIONS()) {
            keyAdd(House());keyAdd(Village()); keyAdd(Town());keyAdd(City(), true)
            keyAdd(Neighbourhood(), true)
            keyAdd(Library(), true)
            keyAdd(University(), true)
        }

        with( SCENES().TRANSPORT_PATHS() ) {
            keyAdd(Walkway(),true)
            keyAdd(Bridge(),true)
            keyAdd(Road(),true)
        }

        with( SCENES().TRANSPORT_LAND() ) {
            keyAdd(Bicycle(), true)
            keyAdd(Automobile(), true)
            keyAdd(Bus(), true)
            keyAdd(Truck(), true)
        }

        with( SCENES().TRANSPORT_AIR() ) {
            keyAdd(Airplane())
        }

        with( SCENES().TRANSPORT_WATER() ) {
            keyAdd(Boat(), true)
            keyAdd(Kayak())
        }

        //=================================NEW SUPERGROUP===============================================================

        with( CONCEPTS().TIME() ) {
            keyAdd(Clock(),true)
            keyAdd(Day());  keyAdd(Night(),true)
            keyAdd(Month());keyAdd(Year(),true)
        }

        //with( CONCEPTS().ENERGY() ) {
        //    keyAdd(Energy(), true)
        //    keyAdd(Energymental());keyAdd(Energyphysical(), true)
        //    keyAdd(Geothermalenergy()); keyAdd(Bioenergy());keyAdd(Solarenergy()); keyAdd(Wavepower());keyAdd(Windpower(), true)
        //    keyAdd(Oilpower(), true) //Booo,  Hisssss  big frowny
        //}

        with( CONCEPTS().ENUMERATION()) {
            keyAdd(None());keyAdd(Few());keyAdd(Some());keyAdd(Many());keyAdd(ManyMany() );keyAdd(All(), true)//These also shared with Adjectives
            keyAdd( One(), true )
            keyAdd( Two(), true )
            keyAdd( Three(), true )
            keyAdd( Four(), true )
            keyAdd( Five(), true )
        }

        //with(CONCEPTS().PHYSICS() ) {
        //    keyAdd(Physics())
        //    keyAdd(CONCEPTS().SCIENCES().Biology()) //Borrowing from another group here.
        //    keyAdd(Physics())
        //}

        with(CONCEPTS().COLORSGROUP() ) {
            keyAdd(Colors(), true)
            keyAdd(Red())
            keyAdd(Yellow())
            keyAdd(Green())
            keyAdd(Blue())

        }

        //=================================NEW SUPERGROUP===============================================================
        //       1"ARTICLES",   2"ADVERBS", 3"VERBS", 4"ADJECTIVES", 5"PREPOSITIONS",  6"PRONOUNS", 7"CONJUNCTIONS", 8"INTERJECTIONS", 9"EMOJIS", 10"PUNCTUATION"

        with( LANGUAGE().ARTICLES()) {
            keyAdd(An(), true)
            keyAdd(ThatThere())
            keyAdd(This(), true)
        }

        with (LANGUAGE().ADVERBS()) {
            keyAdd(How()); keyAdd(Howq()); keyAdd(WHEN()); keyAdd(Where()); keyAdd(Whereq(), true);
            keyAdd(Not());
        }

        with( LANGUAGE().VERBS() ) {
            keyAdd(Am_Is());keyAdd(Can());keyAdd(BLANK());keyAdd(See());keyAdd(Hear());keyAdd(Feel(), true);
            keyAdd(Eat());keyAdd(Sleep());keyAdd(Drink());keyAdd(Hunt(),true);
            keyAdd(Move_carry());keyAdd(Jump());keyAdd(Crawl());keyAdd(Walk());keyAdd(Run());keyAdd(Fly(),true);
            keyAdd(Have());keyAdd(BLANK());keyAdd(BLANK());keyAdd(Bounce(), true); //From reflection
            keyAdd(Stand());keyAdd(Sit());keyAdd(Lie_down(), true)
            keyAdd(Get());keyAdd(Give());keyAdd(Know(),true)
            keyAdd(Pull());keyAdd(Push(),true);
        }

        with( LANGUAGE().ADJECTIVES() ) {
            keyAdd(Good());keyAdd(Bad(), true)   //Like & Dislike properly also?
            keyAdd(Huge()); keyAdd(Big()); keyAdd(Little());keyAdd(BLANK0());keyAdd(High_Tall());keyAdd(Deep());  //Add Huge?
            keyAdd(Short());keyAdd(Long());keyAdd(Slow());keyAdd(Fast(), true)
            keyAdd(None());keyAdd(Few());keyAdd(Some());keyAdd( Many());keyAdd( ManyMany() );keyAdd(All(), true)//add Many Many?
            keyAdd(Thin());keyAdd(Wide(), true)
            keyAdd(Far());keyAdd(Rotten());keyAdd(BLANK1());keyAdd(Cold()); keyAdd(Warm());keyAdd(Hot_temperature(), true)
            keyAdd(Correctthinking());keyAdd(IncorrectThinking());keyAdd(BLANK2());keyAdd(Full());keyAdd(Empty(), true)


            //funny, great, huge less, more
            //These should be good / checked out with NK
            //keyAdd(New());keyAdd(Smooth());keyAdd(Dirty());keyAdd(Dry());keyAdd(Wet());keyAdd(Dirty())

            //NOTE:  TODO!!  Some improperly placed here, some basic missing?  Too many!?   THOUGHT:  REDUCE TO (7ROWS * ~6 AVG FOR 40+   ONLY 13 ABOVE AT THIS POINT...
            // //keyAdd(And())            //keyAdd(At())

            ////keyAdd(Coldoppositehot())                   //           //keyAdd(Different())
            //keyAdd(Fat())
            //keyAdd(Here(adverb))                      ////keyAdd(In(x))            //keyAdd(Incorrect())
             ////keyAdd(Leftturn())            //keyAdd(Lightweight())            ////keyAdd(Linear())
            ////keyAdd(Name())            ////keyAdd(Nearness(x))
            ////keyAdd(Old())            //// keyAdd(Rightturn())            //keyAdd(Rotation(noun))               //keyAdd(Sharpknife())
            //
            //// keyAdd(Squaredescription())
            //keyAdd(What())            ////keyAdd(WhatThing())            ////keyAdd(Who())       ////keyAdd(With())
        }

        with( LANGUAGE().CONJUNCTIONS() ) {
            keyAdd(If(), true)
            keyAdd(Therefore());keyAdd(Because(), true);
            keyAdd(And());keyAdd(Or(), true)
            keyAdd(But()); keyAdd(Unless(), true)
        }

        with( LANGUAGE().PREPOSITIONS() ) {
            keyAdd(Above());keyAdd(Below());keyAdd(Before());keyAdd(After(), true)
            keyAdd(Inside());keyAdd(Outside(), true)
            keyAdd(At());keyAdd(On(), true)
            keyAdd(To())
        }

        with( LANGUAGE().INTERJECTIONS()) {
            keyAdd( Yes_indeed() );keyAdd( No_indeed(), true )//added manually... 0329
            keyAdd( Wow_super(), true )//added manually... 0329
            keyAdd( Help_exclamatory() )//added manually... 0329
        }

        with (LANGUAGE().PRONOUNS()) {
            keyAdd(I()); keyAdd(Me(), true)
            keyAdd(You(), true)
            keyAdd(We(), true)
            keyAdd(He());keyAdd(She(), true)
            keyAdd(They(), true)
            keyAdd(Those(), true)
        }

        with( LANGUAGE().EMOJIS() ) {
            keyAdd(Facesmiley())
            keyAdd(Facefrowny())
            keyAdd(Faceidea())
            keyAdd(Facestraight())
        }

        //COMMANDS HERE ALSO. TEMP!!
        with( LANGUAGE().PUNCTUATION() ) {
            keyAdd(Backspace(), true)
            keyAdd(Space()); keyAdd(Comma());keyAdd(Ellipse(), true);

            keyAdd(Period());keyAdd(QuestionMark());keyAdd(ExclamationMark());keyAdd(AcceptKey(), true);
            keyAdd(UpArrowSentence());keyAdd(DnArrowSentence(), true);
            keyAdd(EndOfParagraph());keyAdd(EndOfPage());keyAdd(EndOfDocument(), true)
            keyAdd(NewBuffer(), true)
            keyAdd(ListToConsole1());keyAdd(ListToConsole2());keyAdd(ClearAll());keyAdd(ReadFile1());keyAdd(ReadFile2())
        }
    }

}//END_OF_CLASS( SymbolPaths )