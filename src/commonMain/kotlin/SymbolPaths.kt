import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA

enum class ContainerState {NATIVE, BUTTON, CARTOUCHE, MONTESSORI, NULL}

/* Notes toward a BLANK / Spacer Key for formatting Symbols better
    1. Could be just background color
    2. Should not go to Sentence!  Special ID
    3. Try 1/2 Wide so take up minimum space
    4. Alternative idea would be to have "from Left" and "from Right" into each "PANEL" of Display...   this might be the best in longer run!?
 */

/*BEATLE BERRY, BROOM, BUCKET, BUTTERFLY
  CLOSET, CLOTH, CHEESE, CHERRIES
  DIGITAL, DEER, DAY, DESIRE OF MIND/HEART,  DISPOSE, DOUBT, DRESSER, DRUM

*/

/*
MONEY:  DOLLAR, EURO


CONCEPTS:  DIRECTIONS ->  EAST, WEST, NORTH, SOUTH
           EMOTIONS ->


ACQUIRE, ABOUT, ABOVE, ACT, ADD, ADULT, ADVANCE, AIRPLANE, AFFECTION, AGO, AGREEMENT, ALERT, ALIVE, ALL, ALLOW, ALSO, ALTERATION, ALTERNATE, AM, ANIMAL, ANIMAL-WATER, ANKLE, ANTICIPATE, APPLE, APPLIANCE, APPROACH, AREA, ARTIFACT, ARTIFICIAL INTELLIGENCE, ASSISTANCE, ATMOSPHERE, ATOM, AWHILE
BABY. BABY BOY, BABY GIRL, BACK, BAG, BALL, BALLOON, BASE, BASKET, BEARD, BEDPAN, BEETLE, BEFORE, BEGIN, BERRY, BESIDE, BETWEEN BIG, BIRD, BLISSYMBOL, BLOCK, BOARD, BOAT, BODY, BOILER, BOTTOM, BOW & ARROW, BOWL, BRAIN, BREAK, BRIDGE, BROADNESS, BROOM, BUCKET, BUILDING, BULB (FLOWER), BUS, BRUSH, BUSINESS, BUTTERFLY, BY, BY MEANS OF,
CABBAGE,CARROT, CELLERY, CAKE, CORN, CALCULATION, CAMERA, CAMEL, CAMPER, CAN, CANCELLATION, CANNON, CAP, CARD, CAVITY - CARIES, CARRIAGE, CART, CASE, CAT, CAUSE, CENT, CEREAL (GRAIN), CHAMBER POT, CHANGE, CHEEK, CHEESE, CHERRIES, CREAM, CRUMBS, CHEST OF DRAWERS, CHILD, CHOICE, CHRISTIAN, CIRCLE, CIRCULATION, CITIZEN, CLAY, CLOCK, CLOSE, CLOSET, CONCEPT, CONJURE, CONNECT, CONTINUE, COUNTRY
DAMAGE, DRUPE, DANGER, DAY, DEER, DROMEDARY, DEPARTURE, DESIRE OF MIND, DESIRE OF HEART, DEVICE, DIGITAL, DISPOSE, DIVISION, DO, DOUBT, DOWN, DRESSER, DRINK, DRUM
ETROG, EVERGREEN TREE, EURO, EASE, EARLY_MORNING, EDGE, END, ENGINE, EVENING, EXCHANGE,
 */





/*BASIC SYMBOLS THAT HAVE NOT BEEN COVERED YET:
    1.  PALM       (Needed for FRUIT::date
    2.  MONEY      (will be needed for BANK and Shops and so on
    3.  EXCHANGE   (Needed for University)
    4.  Adult ?,    5.  allowance,    6   alteration,    7   alteration,    8   artifact,    9   assistance,    10  avocado
    11  BAG,    12  BALL
    13  BATH (now as Wash?) but Bath is base for such as BATHROOM, BATHTUB
    14  BEGINNING,    15  BEING,    16  CAMEL,    17  CAMPER (TRAILER),    18  CAMERA,    19  CA,    20  CANDY,    21  CARROT,    22  CAUSE,    23  CEILING,    24  CELERY
    25  CHIMNEY,    26  CORN,    27  CRYSTAL,    28  CURRANTS,    29  CURTAINS,    30  CURRENT,    31  CYLINDER (have TUBE),    32  DAMAGE,    33  DANGER
    34  DAY,    35  DEER <<<,    36  DEED,    37  DELETION,    38  DEVICE <<<,    39  DEVIL,    40  DIRT  ??  not single, but on same level as Sand
    41  DRINK -  food + water,
    42  EAGLE 43 EAR 44. EASE  45 EAST  46 MONEY
    ELECTRICITY,    43  ETROG (what is?),    44  EVALUATION,    45  EVENING,    46  EVERGREEN TREE,    47  EYELID
    48

























 */

data class ViewBox(var x0: Double = 0.0, var y0: Double = 0.0, var x1: Double = 0.0, var y1: Double = 0.0)


/*
            HE

    nEle:   2                   //n most elementary Elements   OR   n Standing Graphical Elements
    pict:   1                   //how many elements are reasonably recognizable from Shape (from perspective of a child)
    discernable: F              //Likely to be immediately discernable by a 10yo Child assuming both symbols known: eg   pet =? 22T     FftT   Emphatic NO somewhat NO, weak Yes,  Emphatic Yes
    reused: TF                  //>3 in set of Basic Bliss Symbols (1000?)
    dm:                         //(to be) Modified for Digital


             11TF

   ================================================================================================================================
   Number of NAMED Subsymbols, which may themselves have NAMED sub Symbols  (Amphibious is not a Named Symbol,  WATER & GROUND ARE
   Number of Pictographic Symbols

    p, P                 Weakly or Strongly Pictographic  [in sense of immediate discernment, with child in mind]
    l, L                 Weakly or Strongly Logical


    Let's count and think in terms of the NAMED (searchable) Symbols Contained only.
    We can expand the usefulness of String by Containing the NAMES of these Elements, then these elements can contain ELEMENTS...
    lets put these into square Brackets:

    thus FROG:

        4 Named Sub Symbols
        1 Pictographic

    41[ANIMAL,WATER,GROUND,JUMP]

    ??combined??

    MODIFIED FOR (FUNuage) CARTOUCHE?   T/F

 */



data class Header(
    var type: String              = "",
    //var isPictographic: Boolean = false,
    //var isSimple : Boolean      = false,
    //var isReused : Boolean      = false,
    //var isStopper: Boolean      = false,
    var fillColor: RGBA         = Colors.BLACK,
    var level:     Int          = 80  //F)rom this Level,  and higher levels, so 100 is all level 80 and so on.
)


//data class Statistics(var pKnown: Double = 0.0, var sDev: Double = 0.0)


open class Symbol() {
    open val vB  = ViewBox()
    open val hD  = Header()
    //open val sT  = Statistics()
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
//            override val hD = Header("00F")//
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
                override val hD = Header("11l[SKY]")
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val pAr= arrayListOf(
                    "M0,8H8"
                )
            }//END_OF_SYMBOL( SKY )

            inner class GEOLOGY()  : Symbol() {  //Mountain
                override val hD = Header("11p[MOUNTAIN")
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val pAr= arrayListOf(
                    "M0,16H8M8,8V16M0,16L8,8"
                )
            }//END_OF_SYMBOL( GEOLOGY )

            inner class MATERIALS() : Symbol() {
                override val hD = Header("21p[THING, EARTH]")
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val pAr= arrayListOf(
                    "M0,10H4M0,14H4M0,10V14M4,10V14M0,16H4"
                )
            }//END_OF_SYMBOL( MATERIALS )

            inner class PHENOMENA() : Symbol() {  //Wind = Air + movement forward
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 13.5, y1 = 21.5)
                override val hD = Header("21p[AIR, FORWARD]")
                override val pAr= arrayListOf(
                    "M0,8H8M4,16L8,8M4,16H8M4,12H12M10,10L12,12M10,14L12,12"
                )
            }//END_OF_SYMBOL( PHENOMENA )

        } //END_OF_GROUPCODE( WORLD )

        inner class ANIMATE { //BEGIN GROUPCODE

            inner class PLANTS() : Symbol() {  //Flower
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 7.5, y1 = 21.5)
                override val hD = Header("11T")
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2,12V16M2,16a4,4 0 0,1 4,-4"
                )
            }//END_OF_SYMBOL( PLANTS )

            inner class FRUITS() : Symbol() {  //Flower
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 7.5, y1 = 21.5)
                override val hD = Header("11T")
                override val pAr= arrayListOf("M2,12a4,4 0 0,1 4,-4M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0")
            }//END_OF_SYMBOL( PLANTS )

            inner class ANIMAL_TYPES() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("21F")
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
                override val hD = Header("21P[ANIMAL, FROM]")
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
                override val hD = Header("31P[HEAD, DOT, DOT]")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,10V14M2,14H6",
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( FACE )

            inner class BODY_PARTS() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 13.5, y1 = 21.5)
                override val hD = Header("11p[TRUNK]")    //Torso
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H10M2,16H10M10,8a5,5 0 0,1 0,8",
                )
            }//END_OF_SYMBOL( BODY_PARTS )

            inner class FAMILY() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 13.5, y1 = 21.5)
                override val hD = Header("33p[MAN, WOMAN, PROTECTION")
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M2,12L6,8M6,8L10,12M10,8V12M8,16L10,12M10,12L12,16M8,16H12",
                )
            }//END_OF_SYMBOL( FAMILY )

            inner class PETS() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 19.5, y1 = 21.5)
                override val hD = Header("22T")
                override val pAr= arrayListOf(
                    "M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16",
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 4,0q0,3 -4,6q-4,-3 -4,-6",
                )
            }//END_OF_SYMBOL( PETS )

            inner class PERSONAL_ARTIFACTS() : Symbol() {  //TODO:  BEST IDEA:  ADD PERSONAL TO ARTIFACT,  TOOTHBRUSH NOT SO MUCH A "TOOL"
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 12.5, y1 = 21.5)
                override val hD = Header("11p[ARTIFACT, INDICATOR]")
                override val pAr= arrayListOf(
                    //"M0,12L4,16M4,8V16M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M0,16L4,12M4,12L8,16M0,16H8",
                    "M3,4H5M3,6H5M3,4V6M5,4V6"
                )
            }//END_OF_SYMBOL( PERSONAL_ARTIFACTS )

            inner class KITCHEN_TOOLS() : Symbol() {  //mouth + hand  Joined
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 7.5, y1 = 21.5)
                override val hD = Header("11p[MOUTH + HAND")
                override val pAr= arrayListOf("M0,12L4,16M4,8V16M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0")
            }//END_OF_SYMBOL( KITCHEN_TOOLS )

//            inner class FOOD() : Symbol() { //GK MODIFIED
//                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 17.5, y1 = 21.5)
//                override val hD = Header("22t")
//                override val pAr= arrayListOf("")
//            }//END_OF_SYMBOL( FOOD )

//            inner class KITCHEN() : Symbol() { //GK MODIFIED
//                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 17.5, y1 = 21.5)
//                override val hD = Header("22t")
//                override val pAr= arrayListOf(
//                    // Original Kitchen, 3 parts::  "M0,8H8M0,16H8M8,8V16",  "M10,16L14,12M14,12L18,16M10,16H18", "M20,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M20,16H24",
//                    "M0,8H8M0,16H8M8,8V16",
//                    "M10,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M10,16H15"
//                )
//            }//END_OF_SYMBOL( KITCHEN )

        } //END_OF_GROUPCODE( PERSON )

        inner class SCENES {
            fun getName() = "SCENES"

            inner class CONSTRUCTIONS() : Symbol() { //GMMOD 02232021
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("11p[STRUCTURE]")
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
                override val hD = Header("32p[THING, WHEEL, LAND]")
                override val pAr= arrayListOf(
                    "M0,8H4M0,12H4M0,8V12M4,8V12M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.585786,15.414214L3.414214,12.585786M0.585786,12.585786L3.414214,15.414214",
                    "M0,18H8"
                )
            }//END_OF_SYMBOL( TRANSPORT_LAND )

            inner class TRANSPORT_WATER() : Symbol() {  //gk 0320 added water symbol
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("32p[THING, WHEEL, WATER]")
                override val pAr= arrayListOf(
                    "M0,8H4M0,12H4M0,8V12M4,8V12M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.585786,15.414214L3.414214,12.585786M0.585786,12.585786L3.414214,15.414214",
                    "M0,18a2.5,2.5 0 0,1 4,0M4,18a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( TRANSPORT_WATER )

            inner class TRANSPORT_AIR() : Symbol() {//gk 0320 added air symbol
            override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("32p[THING, WHEEL, SKY]")
                override val pAr= arrayListOf(
                    "M0,8H4M0,12H4M0,8V12M4,8V12M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.585786,15.414214L3.414214,12.585786M0.585786,12.585786L3.414214,15.414214",
                    "M0,4H8"
                )
            }//END_OF_SYMBOL( TRANSPORT_AIR )

            inner class TRANSPORT_PATHS() : Symbol() {//gk 0320 added ground symbol
            override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 17.5, y1 = 21.5)
                override val hD = Header("32p[PATH, ACTIVITY, TRANSPORT]")  //@@@GK MODIFIED TO BE MORE GENERAL THAN AUTO-PATH
                override val pAr= arrayListOf(
                    "M0,16H16",
                    "M8,8 H12 M8,12 H12  M8,8V12  M12,8 V12 M8,14 a2,2 0 1,1 4,0a2,2 0 1,1 -4,0 M8.586,15.41 L11.41,12.58 M8.585786,12.585 L11.41,15.41",
                    "M2,13L4,15M4,15L6,13"
                )
            }//END_OF_SYMBOL( TRANSPORT_PATHS )

        } //END_OF_GROUPCODE( SCENES )

        inner class CONCEPTS {

            inner class TIME() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 9.5, y1 = 21.5)
                override val hD = Header("11P[TIME]")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V12M4,12H8",
                )
            }//END_OF_SYMBOL( TIME )

            inner class ENUMERATION() : Symbol() { //ORIGINAL:: "M1,16L5,8M2,10H10M5,16L9,8M0,14H8",
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("55p[HAND]")
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                    "M3,8  H5  M3,16  H5  M3,8  V16  M5,8  V16",
                    "M6,8  H8  M6,16  H8  M6,8  V16  M8,8  V16",
                    "M9,8  H11  M9,16 H11  M9,8 V16 M11,8  V16",
                    "M12,16  H16  V14  H12 V16"
                )
            }//END_OF_SYMBOL( ENUMERATION )

            //inner class ENERGY() : Symbol() {
            //    override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 17.5, y1 = 21.5)
            //    override val hD = Header("")
            //    override val pAr= arrayListOf(
            //        "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
            //    )
            //}//END_OF_SYMBOL( ENERGY )

            inner class COLORSGROUP() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 5.5, y1 = 21.5)
                override val hD = Header("21P[EYE, EARTH]")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,16H4",
                )
            }//END_OF_SYMBOL( COLORSGROUP )


            inner class DIRECTIONS() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 15.5, y1 = 21.5)
                override val hD = Header("[GENERALIZATION, FORWARD]B")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12M0,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M6,12H14M12,10L14,12M12,14L14,12"
                )
            }//END_OF_SYMBOL( DIRECTIONS )


            inner class SHAPES() : Symbol() {
                override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 11.5, y1 = 21.5)
                override val hD = Header("[EYE, THING]B")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,10H10M6,14H10M6,10V14M10,10V14")
            }//END_OF_SYMBOL( SHAPES )

            //inner class PHYSICS() : Symbol() {
            //    override val vB = ViewBox(x0 = -0.75, y0 = -0.75, x1 = 17.5, y1 = 21.5)
            //    override val hD = Header("00F")
            //    override val pAr= arrayListOf(
            //        "M0,10L8,14  M8,10 V14  M8,10  L16,14  M4,12 a4,4 0 1,1 8,0  a4,4 0 1,1 -8,0",
            //    )
            //}//END_OF_SYMBOL( PHYSICS )

        } //END_OF_GROUPCODE( CONCEPTS )

        inner class LANGUAGE {

            //1"PRONOUNS",  2"ARTICLES",   3"ADVERBS",    4"VERBS", 5"ADJECTIVES", 65"PREPOSITIONS",  7"CONJUNCTIONS", 8"INTERJECTIONS", 9"EMOJIS", 10"PUNCTUATION"

            inner class PRONOUNS():Symbol() {  //MattL commented negatively to this one, I used "Reflect" Plus Montessori Triangle, I think....
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("11M[PRONOUN]")
                override val pAr= arrayListOf(  //TRIANGLE - Needs to be taller + exchange symbol
                    "M0,16L4,8M4,8L8,16M0,16H8",
                    "M0,12a4,4 0 1,0 8,0M2,14L4,16M4,16L6,14M4,8V16M2,10L4,8M4,8L6,10"
                )
            }//END_OF_SYMBOL( PRONOUNS )

            inner class ARTICLES():Symbol() { //SMALL (MEDITERANIAN?) BLUE TRIANGLE
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("11M[ARTICLES]")
                override val pAr= arrayListOf(
                    "M0,16L2,12M2,12L4,16M0,16H4"
                )
            }//END_OF_SYMBOL( ARTICLES )

            inner class ADVERBS():Symbol() {  //SMALL CIRCLE:: ORANGE
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("11M[ADVERBS]")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                )
            }//END_OF_SYMBOL( ADVERBS )

            inner class VERBS():Symbol() {  //LARG CIRCLE:: BRIGHT RED   //-0.75 -0.75 14 21.5
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=14.0, y1=21.5)
                override val hD = Header("11M[VERBS]")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 12,0 a4,4 0 1,1 -12,0",
                    "M2,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0"
                )
            }//END_OF_SYMBOL( VERBS )

            inner class ADJECTIVES():Symbol() {  //MEDIUM SIZED TRIANGLE :: DEEP BLUE
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("11M[ADJECTIVES]")
                override val pAr= arrayListOf(
                    "M0,12M0,16L4,8M4,8L8,16M0,16H8",
                )
            }//END_OF_SYMBOL( ADJECTIVES )

            inner class PREPOSITIONS():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("11M[PREPOSITIONS]")
                override val pAr= arrayListOf(  //todo!  using Moon to start
                    "M0,8a5,5 0 0,1 0,8M0,8a4,4 0 1,1 0,8"
                )
            }//END_OF_SYMBOL( PREPOSITIONS )

            inner class CONJUNCTIONS():Symbol() { //LONG NARROW RECTANGLE::  PINK-ISH
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("11M[CONJUNCTIONS]")
                override val pAr= arrayListOf( //from Card
                    "M0,10H8M0,14H8M0,10V14M8,10V14",
                )
            }//END_OF_SYMBOL( CONJUNCTIONS )

            inner class INTERJECTIONS():Symbol() { //CIRCLE OVER SMALL TRIANGLE:: GOLD
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("11M[INTERJECTIONS]")
                override val pAr= arrayListOf( //TRIANGLE + MOUTH
                    "M0,8a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                    "M0,16L4,8M4,8L8,16M0,16H8"
                )
            }//END_OF_SYMBOL( INTERJECTION )

            //==========================================================================================================

            inner class EMOJIS():Symbol() { //SMILEY FACE:: YELLOW
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("11M[EMOJIS]")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                    "M2.4,13 a1.5,1.5 0 1,0 3.0,0",
                    "M2,11a 0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5,11a 0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( EMOJIS )

            inner class PUNCTUATION():Symbol() {  //BIG COMMA:: BLACK?
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("11M[PUNCTUATION]")
                override val pAr= arrayListOf(
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0.2777,16q0.0694,0.6943,-0.5554,1.1109",
                )
            }//END_OF_SYMBOL( PUNCTUATION )

        }//END_OF_GROUPCODE( LANGUAGE )

    } //END_OF_SUPERGROUP( GROUPS )

    //v=================================================================== BEGIN SYMBOLS =========================================================================================


    inner class WORLD() {//BEGIN SUPERGROUP( WORLD )

        inner class MATERIALS {

            inner class Metal():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[THING, THING]C")
                override val pAr= arrayListOf(
                    "M0,10H8M0,14H8M0,10V14M8,10V14M4,10V14",
                )
            }//END_OF_SYMBOL( Metal )

            inner class Mud():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[EARTH + WATER]")
                override val pAr= arrayListOf(
                    "M0,16a2.5,2.5 0 0,1 4,0M4,16a2.5,2.5 0 0,0 4,0M0,16H12",
                )
            }//END_OF_SYMBOL( Mud )

            inner class Powder():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[STONE]B") //@@@PHYSICALLY 3 DOTS
                override val pAr= arrayListOf(
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,12a0.2083, 0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,16a0.2083, 0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Powder )

            inner class Salt():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[PLUS, FLAVORING, CRYSTAL]")
                override val pAr= arrayListOf(
                    "M0,12H8M4,8V16",
                    "M3,4H5M3,6H5M3,4V6M5,4V6",
                    "M10,16L14,8M10,16H14M10,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M16,10L18,8M18,8L20,10M16,10H20M16,14H20M16,10V14M20,10V14M16,14L18,16M18,16L20,14",
                )
            }//END_OF_SYMBOL( Salt )

            inner class Sand():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[POWDER, STONE]B")
                override val pAr= arrayListOf(
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,16H10M10,12V16M6,16L10,12",
                )
            }//END_OF_SYMBOL( Sand )

            inner class Stone():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[MOUNTAIN]")
                override val pAr= arrayListOf(
                    "M0,16H4M4,12V16M0,16L4,12",
                )
            }//END_OF_SYMBOL( Stone )

            inner class Wood():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("[MATERIAL, TREE]")
                override val pAr= arrayListOf(
                    "M0,10H4M0,14H4M0,10V14M4,10V14M0,16H4",
                    "M6,12a4,4 0 0,0 4,-4M10,8V16M10,8a4,4 0 0,0 4,4",
                )
            }//END_OF_SYMBOL( Wood )

        } //END_OF_GROUPCODE( MATERIALS )


        inner class SKY {

            inner class Cloud():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SKY + WATER]C")
                override val pAr= arrayListOf(
                    "M0,8H8M0,8a2.5,2.5 0 0,1 4,0M4,8a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Cloud )

            inner class Earth():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[EARTH]C")  //From child perspective NOT pictographic, or weak pictographic
                override val pAr= arrayListOf(
                    "M0,8L8,16M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                )
            }//END_OF_SYMBOL( Earth )

            inner class Moon():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[MOON]A")
                override val pAr= arrayListOf(
                    "M0,8a5,5 0 0,1 0,8M0,8a4,4 0 1,1 0,8",
                )
            }//END_OF_SYMBOL( Moon )

            inner class Sky():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SKY]B")
                override val pAr= arrayListOf(
                    "M0,8H8",
                )
            }//END_OF_SYMBOL( Sky )

            inner class Snow():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[WATER + STAR + DOWN]C")
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M4,8V14M4,14V18M3,15L5,17M3,17L5,15",
                )
            }//END_OF_SYMBOL( Snow )

            inner class Star():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("[STAR]A")
                override val pAr= arrayListOf("M1,10V14M0,11L2,13M0,13L2,11")
            }//END_OF_SYMBOL( Star )

            inner class Sun():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SUN]A")
                override val pAr= arrayListOf("M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0")
            }//END_OF_SYMBOL( Sun )

        } //END_OF_GROUPCODE( SKY )


        inner class GEOLOGY {

            inner class Ground():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[GROUND]B") //@@@ Hannes file names:  earth,ground,land.svg  we rename to GROUND
                override val pAr= arrayListOf(
                    "M0,16H8",
                )
            }//END_OF_SYMBOL( Ground )

            inner class Lake():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("[MANY, WATER]") //@@@Where we name the file with 'X' to MANY
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,16a2.5,2.5 0 0,1 4,0M10,16a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Lake )

            inner class Mountain():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[MOUNTAIN]B")
                override val pAr= arrayListOf(
                    "M0,16H8M8,8V16M0,16L8,8",
                )
            }//END_OF_SYMBOL( Mountain )

            inner class Ocean():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[MANY, MANY, WATER")  //@@@Here as Physically built, logically per Hannes  Many "Much Water" or related makes, likelyu, more sense.
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12L10,16M6,16L10,12",
                    "M12,16a2.5,2.5 0 0,1 4,0M16,16a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Ocean )

            inner class Rain():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[WATER + DOWN]") //@@@ DOWN ARROW IS NAMED DOWN?
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M2,14L4,16M4,16L6,14M4,8V16",
                )
            }//END_OF_SYMBOL( Rain )

            inner class River():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[FORWARD, WATER]")
                override val pAr= arrayListOf(
                    "M0,12H8M6,10L8,12M6,14L8,12M0,16a2.5,2.5 0 0,1 4,0M4,16a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( River )

            inner class Water():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[WATER]A")
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Water )

        } //END_OF_GROUPCODE( GEOLOGY )

        inner class PHENOMENA {

            inner class Ashes():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[WASTE, FIRE]")  //@@@WASTE has not been covered, this GROUP or PEOPLE (See "Poop"),  where to cover?
                override val pAr= arrayListOf(
                    "M0,16L8,8M2,10H6M2,14H6M2,10V14M6,10V14",
                    "M11,8a2.5,2.5 0 0,1 0,4M11,12a2.5,2.5 0 0,0 0,4",
                )
            }//END_OF_SYMBOL( Ashes )

            inner class Cloud():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SKY + WATER]B")
                override val pAr= arrayListOf(
                    "M0,8H8M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Cloud )

            inner class Fire():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("[FIRE]A")
                override val pAr= arrayListOf(
                    "M1,8a2.5,2.5 0 0,1 0,4M1,12a2.5,2.5 0 0,0 0,4",
                )
            }//END_OF_SYMBOL( Fire )

            inner class Fog():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SKY, WATER]")
                override val pAr= arrayListOf(
                    "M0,8H8M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Fog )

            inner class Ice():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[WATER, THING]")
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M2,10H6M2,14H6M2,10V14M6,10V14",
                )
            }//END_OF_SYMBOL( Ice )

            inner class Smoke():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[CLOUD, FIRE")
                override val pAr= arrayListOf(
                    "M0,8H8M0,8a2.5,2.5 0 0,1 4,0M4,8a2.5,2.5 0 0,0 4,0",
                    "M11,8a2.5,2.5 0 0,1 0,4M11,12a2.5,2.5 0 0,0 0,4",
                )
            }//END_OF_SYMBOL( Smoke )

            inner class Snow():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[WATER + STAR + DOWN]C") //@@@ Duplicated
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M4,8V14M4,14V18M3,15L5,17M3,17L5,15",
                )
            }//END_OF_SYMBOL( Snow )

            inner class Wind():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[AIR + FORWARD]")  //@@@ Where AIR is Compound with SKY,  SKY + NOSE   (Whew, I guess so)
                override val pAr= arrayListOf(
                    "M0,8H8M4,16L8,8M4,16H8M4,12H12M10,10L12,12M10,14L12,12",
                )
            }//END_OF_SYMBOL( Wind )

            inner class Electrical():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[ELECTRICAL]A")  //@@@ Where AIR is Compound with SKY,  SKY + NOSE   (Whew, I guess so)
                override val pAr= arrayListOf( "M0,12L4,8M0,12H4M0,16L4,12", "M1,4L2,6M2,6L3,4" )
            }//END_OF_SYMBOL( Electrical )

        }//END_OF_SUPERGROUP( WORLD )

    } //END_OF_SUPERGROUP( WORLD )

    //==============================================================================================================

    inner class ANIMATE() {//BEGIN SUPERGROUP( WORLD )

        inner class PLANTS {

            inner class Life():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SUN + SPLIT]A") //PER HANNES   SUN + PERSON
                override val pAr= arrayListOf("M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V16")
            }//END_OF_SYMBOL( Life)

            inner class Dead():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SUN + SPLIT + NEGATION, INDICATOR]C") //PER HANNES   SUN + PERSON
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V16M0,16L8,8",
                    "M3,4L4,6M4,6L5,4",
                    "M5.7917,4a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Dead )

            inner class Bark():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("[PROTECTION, TREE, INDICATOR]")  //@@@ Where TREE with Indicator to trunk is Bark
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12",
                    "M10,12a4,4 0 0,0 4,-4M14,8V16M14,8a4,4 0 0,0 4,4M15,14L17,12M15,14L17,16",
                )
            }//END_OF_SYMBOL( Bark )

            inner class Flower():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[FLOWER]A")
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2,12V16",
                )
            }//END_OF_SYMBOL( Flower )

            inner class Forest():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[MANY, MANY, TREE]")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12L10,16M6,16L10,12",
                    "M12,12a4,4 0 0,0 4,-4M16,8V16M16,8a4,4 0 0,0 4,4",
                )
            }//END_OF_SYMBOL( Forest )

            inner class Fruit():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("[FRUIT]A")
                override val pAr= arrayListOf(
                    "M2,12a4,4 0 0,1 4,-4M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                )
            }//END_OF_SYMBOL( Fruit )

            inner class Grass():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("11p[GRASS]A")
                override val pAr= arrayListOf("M0,16a4,4 0 0,1 4,-4")
            }//END_OF_SYMBOL( Grass )

            inner class Leaf():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[LEAF]A")
                override val pAr= arrayListOf("M2,8a5,5 0 0,0 0,8M2,8V16M2,8a5,5 0 0,1 0,8")
            }//END_OF_SYMBOL( Leaf )

            inner class Root():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[GROUND + ROOT]B")
                override val pAr= arrayListOf(
                    "M0,16H8M0,20a4,4 0 0,0 4,-4",
                )
            }//END_OF_SYMBOL( Root )

            inner class Seed():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[GROUND, DOT, DOT]B")
                override val pAr= arrayListOf(
                    "M0,16H8",
                    "M1.7917,18a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.7917,18a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Seed )

            inner class Stick():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[LINEAR_THING, TREE")  //@@@WHERE LINEAR_(THIN_)THING CONSTISTS OF THING + VERTICAL LINE
                override val pAr= arrayListOf(
                    "M1,8V16",
                    "M0,4H2M0,6H2M0,4V6M2,4V6",
                    "M3,12a4,4 0 0,0 4,-4M7,8V16M7,8a4,4 0 0,0 4,4",
                )
            }//END_OF_SYMBOL( Stick )

            inner class Tree():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[TREE]A")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 0,0 4,-4M4,8V16M4,8a4,4 0 0,0 4,4",
                )
            }//END_OF_SYMBOL( Tree )

        } //END_OF_GROUPCODE( PLANTS )


        inner class FRUITS {

            //Tree Fruits
            inner class Apple():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[APPLE]A")
                override val pAr= arrayListOf("M0,12a2,2 0 1,1 4,0M4,12a2,2 0 1,1 4,0M0,12a4,4 0 1,0 8,0M4,12a4,4 0 0,1 4,-4")
            }//END_OF_SYMBOL( Apple )

            inner class Peach():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[PEACH]B")
                override val pAr= arrayListOf("M4,12a4,4 0 0,1 4,-4M0,12a2,2 0 1,1 4,0M4,12a2,2 0 1,1 4,0M0,12a4,4 0 1,0 8,0M4,13a1.25,1.25 0 0,0 0,2M4,13a1.25,1.25 0 0,1 0,2")
            }//END_OF_SYMBOL( Peach )

            inner class Pear():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=8.5, y1=21.5)
                override val hD = Header("[PEAR]A")
                override val pAr= arrayListOf("M3,8a4,4 0 0,1 4,-4M3,8a5,5 0 0,0 -2,4M3,8a5,5 0 0,1 2,4M1,12a2.5,2.5 0 0,0 0,4M5,12a2.5,2.5 0 0,1 0,4M1,16H5")
            }//END_OF_SYMBOL( Pear )

            //Tropical Tree Fruits
            // avocado
            inner class Avocado():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=8.5, y1=21.5)
                override val hD = Header("[AVOCADO]C") //@@@  This just POOR shape, avocados are not PEAR Shaped   per xelify::   PEAR + PIT
                override val pAr= arrayListOf("M3,8a4,4 0 0,1 4,-4M3,8a5,5 0 0,0 -2,4 M3,8a5,5 0 0,1 2,4 M1,12a2.5,2.5 0 0,0 0,4M 5,12a2.5,2.5 0 0,1 0,4 M3,13a1.25,1.25 0 0,0 0,2 M3,13a1.25,1.25 0 0,1 0,2 M1,16H5")
            }//END_OF_SYMBOL( Avocado )

            inner class Banana():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[BANANA]A")
                override val pAr= arrayListOf("M0,8a4,4 0 0,1 4,-4M0,8a5,5 0 0,1 0,8M0,8a4,4 0 1,1 0,8")
            }//END_OF_SYMBOL( Banana )

            inner class Orange():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("[ORANGE]")
                override val pAr= arrayListOf("M2,12a4,4 0 0,1 4,-4M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2,12a2.5,2.5 0 0,0 0,4M2,12a2.5,2.5 0 0,1 0,4")
            }//END_OF_SYMBOL( Orange )

            inner class Pineapple():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[PINEAPPLE]A")
                override val pAr= arrayListOf("M0,4a4,4 0 0,1 4,4M4,4V8M4,8a4,4 0 0,1 4,-4M2,10a2,2 0 1,1 4,0M2,10V14M6,10V14M2,14a2,2 0 1,0 4,0")
            }//END_OF_SYMBOL( Pineapple )

            //Vine Fruits
            inner class Strawberry():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SRAWBERRY]B")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4,12a4,4 0 0,1 4,4",
                    "M0.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1.7917,13a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Strawberry )

            inner class Grape():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[GRAPE]B")
                override val pAr= arrayListOf("M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2,16a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4,12a4,4 0 0,1 4,-4")
            }//END_OF_SYMBOL( Grape )

            inner class Starfruit():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[FRUIT, STAR]B")
                override val pAr= arrayListOf(
                    "M2,12a4,4 0 0,1 4,-4  M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M9,10V14M8,11L10,13M8,13L10,11"
                )
            }//END_OF_SYMBOL( Starfruit )

            //currants

            //NOTE:   There are Symbols for:  Fruit compote, Fruit  Pie,  Juice

        } //END_OF_GROUPCODE( FRUITS )


        inner class ANIMAL_TYPES {

            inner class Animal():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[ANIMAL]A")
                override val pAr= arrayListOf(
                    "M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16",
                )
            }//END_OF_SYMBOL( Animal )

            inner class Horse():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("[ANIMAL + HORSE_HEAD]A")
                override val pAr= arrayListOf("M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16M8,12L12,8M12,8L14,10")
            }//END_OF_SYMBOL( Horse )

            inner class Deer():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[ANIMAL + HORN]A")
                override val pAr= arrayListOf("M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16M4,8a2,2 0 1,0 4,0")
            }//END_OF_SYMBOL( Deer )

            inner class Camel():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("[ANIMAL + HUMPSX2 + HEAD]A")
                override val pAr= arrayListOf("M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16M8,12L12,8M12,8L14,10M0,12a2,2 0 1,1 4,0M4,12a2,2 0 1,1 4,0")
            }//END_OF_SYMBOL( Camel )

            inner class Rabbit():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("[ANIMAL, TEETH, EAR]")  //@@@ Not adequate, use MOJI
                override val pAr= arrayListOf(
                    "M0,12   L2,16  M2,16   L4,12  M0,12   H4  M4,12    L6,16   M6,16   L8,12   M4,12    H8",
                    "M10,10  a2,2 0 1,1 4,0  q0,3 -4,6"
                )
            }//END_OF_SYMBOL( Rabbit )

            inner class Bird():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[BIRD]A")
                override val pAr= arrayListOf("M0,8a4,4 0 0,1 4,4M4,12a4,4 0 0,1 4,-4M3,14L4,12M4,12L5,14")
            }//END_OF_SYMBOL( Bird )

            inner class Eagle():Symbol() { //BIRD OF PREY, INCLUDING HAWK
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[BIRD+CLAWS]B")
                override val pAr= arrayListOf("M0,8a4,4 0 0,1 4,4M4,12a4,4 0 0,1 4,-4M3,14L4,12M4,12L5,14M1,14a1,1 0 1,1 2,0M5,14a1,1 0 1,1 2,0")
            }//END_OF_SYMBOL( Eagle )

            inner class Fish():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[FISH]A")
                override val pAr= arrayListOf("M0,12a4,4 0 1,0 8,0M0,16a4,4 0 1,1 8,0")
            }//END_OF_SYMBOL( Fish )

            inner class Insect():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[INSECT]A")
                override val pAr= arrayListOf("M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16M8,16L10,12M10,12L12,16")
            }//END_OF_SYMBOL( Insect )

            inner class Butterfly():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("BUTTERFLY]A")
                override val pAr= arrayListOf("M2,8a5,5 0 0,0 0,8M2,8a5,5 0 0,1 0,8M6,8a5,5 0 0,0 0,8M6,8a5,5 0 0,1 0,8")
            }//END_OF_SYMBOL( Butterfly )

            inner class Spider():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.55, y1=21.5)
                override val hD = Header("SPIDER]A")
                override val pAr= arrayListOf("M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0,16a4,4 0 1,1 8,0M4,12a2.5,2.5 0 0,1 0,4")
            }//END_OF_SYMBOL( Spider )

            inner class Louse():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=26.5, y1=21.5)
                override val hD = Header("[INSECT, KNIFE]")
                override val pAr= arrayListOf(
                    "M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16M8,16L10,12M10,12L12,16",
                    "M14,12L18,16M14,16L22,8M23,8L25,6M23,8L25,10",
                )
            }//END_OF_SYMBOL( Louse )

            inner class Snake():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[SNAKE]A")
                override val pAr= arrayListOf(
                    "M0,14a2,2 0 1,1 4,0M4,14a2,2 0 1,0 4,0M8,14a2,2 0 1,1 4,0",
                )
            }//END_OF_SYMBOL( Snake )

            inner class Frog():Symbol() { //gkMod to remove animal and shift left
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[ANIMAL, WATER, GROUND, JUMP]") //WATER+GROUND == AMPHIBIAN, WHICH IS NOT A SELF STANDING SYMBOL
                override val pAr= arrayListOf(
                    "M0,16  H8  M0,12  a2.5,2.5 0 0,1 4,0  M4,12  a2.5,2.5 0 0,0 4,0",
                    "M10,16  a4,4 0 1,1 8,0  M16,14  L18,16  M18,16  L20,14"
                )
            }//END_OF_SYMBOL( Frog )

            inner class Wings():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[WINGS]A")
                override val pAr= arrayListOf(
                    "M0,8a4,4 0 0,1 4,4M4,12a4,4 0 0,1 4,-4",
                )
            }//END_OF_SYMBOL( Wings )

            inner class Worm():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[SNAKE + GROUND]A") //Superimposed
                override val pAr= arrayListOf(
                    "M0,16H12M0,18a2,2 0 1,1 4,0M4,18a2,2 0 1,0 4,0M8,18a2,2 0 1,1 4,0",
                )
            }//END_OF_SYMBOL( Worm )

        } //END_OF_GROUPCODE( ANIMAL_TYPES )


        inner class ANIMALS_FROM {

            inner class Blood():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("[WATER, LIFE]")
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                    "M10,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M14,8V16",
                )
            }//END_OF_SYMBOL( Blood )

            inner class Egg():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[EGG+OVUM]") //@@@ COMMA IF NOT COMBINED, + IF COMBINED INSIDE ONE
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M2,12a2.5,2.5 0 0,1 4,0M2,12a2.5,2.5 0 0,0 4,0",
                )
            }//END_OF_SYMBOL( Egg )

            inner class Feather():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("[PART, WINGS]")
                override val pAr= arrayListOf(
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,12H4",
                    "M1.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,8a4,4 0 0,1 4,4M10,12a4,4 0 0,1 4,-4",
                )
            }//END_OF_SYMBOL( Feather )

            inner class Horn():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[HORN]")
                override val pAr= arrayListOf(
                    "M0,8a2,2 0 1,0 4,0",
                )
            }//END_OF_SYMBOL( Horn )

            inner class Food():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[MOUTH, GROUND]")  //@@@ Necessary to introduce, but this is not the most logical place
                override val pAr= arrayListOf("M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0,16H4")
            }//END_OF_SYMBOL( Food )

            inner class Meat():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("[FOOD, ANIMAL]") //@@@ POINTER TO BE A RESERVED WORD.. LARGELY IGNORED.
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0,16H4",
                    "M6,12H14M6,16L8,12M8,12L10,16M10,16L12,12M12,12L14,16",
                )
            }//END_OF_SYMBOL( Meat )

            inner class Tail():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=14.5, y1=21.5)
                override val hD = Header("[ANIMAL + TAIL, POINTER]") //tail + animal are combined..
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
                override val hD = Header("11P[FACE, POINTER]B")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,10V14M2,14H6",
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,19L4,17M4,17L6,19",
                )
            }//END_OF_SYMBOL( Chin )

            inner class Ear():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[EAR]A")
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0q0,3,-4,6",
                )
            }//END_OF_SYMBOL( Ear )

            inner class Eye():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("11P[EYE]A")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Eye )

            inner class HairOfHead():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=10.5, y1=21.5)
                override val hD = Header("[HEAD + HAIR, POINTER]") //@@@  CHANGED SYMBOL NAME!    ADD EYES FOR CONSISTENCY?!
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,10V14M2,14H6M2,6a1.25,1.25 0 0,0 0,2M4,6a1.25,1.25 0 0,0 0,2M6,6a1.25,1.25 0 0,0 0,2M7,6L9,4M7,6L9,8",
                )
            }//END_OF_SYMBOL( HairOfHead )

            //inner class Headwithhair():Symbol() {
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
            //    override val hD = Header("00F")
            //    override val pAr= arrayListOf(
            //        "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,10V14M2,14H6M2,6a1.25,1.25 0 0,0 0,2M4,6a1.25,1.25 0 0,0 0,2M6,6a1.25,1.25 0 0,0 0,2",
            //    )
            //}//END_OF_SYMBOL( Headwithhair )

            inner class Mouth():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[MOUTH]A")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                )
            }//END_OF_SYMBOL( Mouth )

            inner class Nose():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[NOSE]A")
                override val pAr= arrayListOf(
                    "M0,16L4,8M0,16H4",
                )
            }//END_OF_SYMBOL( Nose )

            inner class Tongue():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[PART, MOUTH, UP, DOWN]")
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
                override val hD = Header("[TOOTH]B")
                override val pAr= arrayListOf(
                    "M0,12L2,16M2,16L4,12M0,12H4",
                )
            }//END_OF_SYMBOL( Tooth )

        } //END_OF_GROUPCODE( FACE )


        inner class BODY_PARTS {

            inner class Back_Of_Body():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=8.5, y1=21.5)
                override val hD = Header("[STANDING_PERSON, POINTER]")
                override val pAr= arrayListOf(
                    "M0,6L2,8M0,10L2,8M3,8V16M3,16H7",
                )
            }//END_OF_SYMBOL( Backbody )

            inner class Fingernail():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=18.5, y1=21.5)
                override val hD = Header("[HAND, POINTER]")
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12",
                    "M10,12L14,16M14,8V16M15,8L17,6M15,8L17,10",
                )
            }//END_OF_SYMBOL( Fingernail )

            inner class Foot():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[LEGS, POINTER]")
                override val pAr= arrayListOf(
                    "M0,16L4,8M4,8L8,16M0,16H4M8,16H12M8,13L10,15M10,15L12,13",
                )
            }//END_OF_SYMBOL( Foot )

            inner class Hand():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[HAND]B")
                override val pAr= arrayListOf(
                    "M0,12L4,16M4,8V16",
                )
            }//END_OF_SYMBOL( Hand )

            inner class Heart():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[HEART, INDICATOR_THING")
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 4,0q0,3,-4,6q-4,-3,-4,-6",
                    "M3,4H5M3,6H5M3,4V6M5,4V6",
                )
            }//END_OF_SYMBOL( Heart )

            inner class Organ():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[BODY, IN]")  //@@@ EXCELLENT EXAMPLE OF A LOGICAL SYMBOL - INTRODUCE ORGAN FIRST:   NEED ORGAN! NEED PIPE/TUBE!
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H6M2,16H6M6,8a5,5 0 0,1 0,8",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"

                )
            }//END_OF_SYMBOL( Organ )

            inner class Knee():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=8.5, y1=21.5)
                override val hD = Header("[KNEELING, POINTER]")
                override val pAr= arrayListOf(
                    "M0,8V12M0,12L4,16M0,16H4M5,16L7,14M5,16L7,18",
                )
            }//END_OF_SYMBOL( Knee )

            inner class Legs():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[LEGS]A")
                override val pAr= arrayListOf(
                    "M0,16L4,8M4,8L8,16M0,16H4M8,16H12M7,12L9,10M7,12L9,14",
                )
            }//END_OF_SYMBOL( Leg )

            inner class Neck_Of_Body():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[BODY, POINTER]B")
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H10M2,16H10M10,8a5,5 0 0,1 0,8M4,5L6,7M6,7L8,5",
                )
            }//END_OF_SYMBOL( Neck_Of_Body )

            inner class Throat():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[FACE, NECK]")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,10V14M2,14H6M3,16V18M5,16V18M6,18L8,16M6,18L8,20",
                )
            }//END_OF_SYMBOL( Throat )

            inner class Skin():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=23.5, y1=21.5)
                override val hD = Header("[PROTECT, BODY]")
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12",
                    "M12,8a5,5 0 0,0 0,8M12,8H20M12,16H20M20,8a5,5 0 0,1 0,8",
                )
            }//END_OF_SYMBOL( Skin )

            inner class Stomach():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[TORSO, POINTER]")
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H10M2,16H10M10,8a5,5 0 0,1 0,8M4,10L6,12M6,12L8,10",
                )
            }//END_OF_SYMBOL( Stomach )

            inner class Food():Symbol() { //@@@ Necessary to introduce, but this is not the most logical place
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[MOUTH, GROUND]")
                override val pAr= arrayListOf("M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0,16H4")
            }//END_OF_SYMBOL( Food )


            inner class Bone():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("[BONE]")  //@@@ 2 JOINTS (DOTS) + ONE JOINER... BUT CAN SEE AS BONE/SINGLE BEST?
                override val pAr= arrayListOf(
                    "M-0.2083,8a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,10V14",
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Bone )


            inner class Intestine():Symbol() { //@@@ EXCLUDE FROM LEVELS < 300 ?
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[ORGAN, FOOD, TUBE]")
                override val pAr= arrayListOf(
                    "M2,8a5,5 0 0,0 0,8M2,8H6M2,16H6M6,8a5,5 0 0,1 0,8",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M10,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M10,16H14",
                    "M16,8V16M20,8V16M16,16a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                )
            }//END_OF_SYMBOL( Intestine )



            //inner class Breasts():Symbol() { //@@@ EXCLUDE FROM LEVELS < 300 ?
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
            //    override val hD = Header("32P[TORSO, POINTER, POINTER]")
            //    override val pAr= arrayListOf(
            //        "M2,8a5,5 0 0,0 0,8M2,8H10M2,16H10M10,8a5,5 0 0,1 0,8M2,14L4,12M4,12L6,14M6,14L8,12M8,12L10,14",
            //    )
            //}//END_OF_SYMBOL( Breasts )

            //inner class Liver():Symbol() {
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=31.5, y1=21.5)
            //    override val hD = Header("00F")
            //    override val pAr= arrayListOf(
            //        "M2,8a5,5 0 0,0 0,8M2,8H6M2,16H6M6,8a5,5 0 0,1 0,8",
            //        "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
            //        "M8,16L16,8M16,8V12M14,12H18M14,16H18M14,12V16M18,12V16",
            //        "M20,12L24,16M20,16L24,12",
            //        "M26,16L28,12M28,12L30,16",
            //    )
            //}//END_OF_SYMBOL( Liver )

            //inner class Oil():Symbol() {
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
            //    override val hD = Header("00F")
            //    override val pAr= arrayListOf(
            //        "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M0,16a2.5,2.5 0 0,1 4,0M4,16a2.5,2.5 0 0,0 4,0",
            //    )
            //}//END_OF_SYMBOL( Oil )

        } //END_OF_GROUPCODE( BODY_PARTS )


        inner class FAMILY {

            inner class Baby():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[CHILD]A")  //CHILD LAYING DOWN
                override val pAr= arrayListOf(
                    "M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4,12V16M4,14H8M8,12V16"
                )
            }//END_OF_SYMBOL( Baby )

            inner class Child():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[CHILD]A")
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0,12H4M2,12V16M0,16H4",
                )
            }//END_OF_SYMBOL( Child )

            inner class Person():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[PERSON]A")
                override val pAr= arrayListOf(
                    "M0,16H4M2,8V16",
                )
            }//END_OF_SYMBOL( Person )

            //======================================

            inner class Man():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("11P[MAN]A")
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16",
                )
            }//END_OF_SYMBOL( Man )

            inner class Woman():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[WOMAN]A")
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M0,16H4",
                )
            }//END_OF_SYMBOL( Woman )

            inner class Family():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[MAN, WOMAN, PROTECTION, STAR]") //@@@STAR REPRESENTING (FUTURE?) CHILD, OR CHILDREN
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M2,12L6,8M6,8L10,12M10,8V12M8,16L10,12M10,12L12,16M8,16H12M6,10V14M5,11L7,13M5,13L7,11",
                )
            }//END_OF_SYMBOL( Family )

            //=====================================

            inner class Protection():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[PROTECTION]A")
                override val pAr= arrayListOf("M0,12L4,8M4,8L8,12")
            }//END_OF_SYMBOL( Protection )

            inner class Father():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[PROTECTION, MAN]") //@@@ Consider as seperate 2 symbols, although touching...
                override val pAr= arrayListOf("M0,12L4,8M4,8L8,12M4,8V12M2,16L4,12M4,12L6,16")
            }//END_OF_SYMBOL( Father )

            inner class Mother():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[PROTECTION, WOMAN") //@@@ Consider as seperate 2 symbols, although touching...
                override val pAr= arrayListOf("M0,12L4,8M4,8L8,12M4,8V12M2,16L4,12M4,12L6,16M2,16H6")
            }//END_OF_SYMBOL( Mother )

            //===================================

            inner class Husband():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[PROTECTION, MAN]")
                override val pAr= arrayListOf("M2,8V12M0,16L2,12M2,12L4,16M2,12L6,8M6,8L10,12" )
            }//END_OF_SYMBOL( Husband )

            inner class Wife():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[PROTECTION, WOMAN]")
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12M8,8V12M6,16L8,12M8,12L10,16M6,16H10",
                )
            }//END_OF_SYMBOL( Wife )

            //=====================================================================


            inner class Brother():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[PROTECTION, MALE, TWO]")
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12M2,16L4,12M4,12L6,16",
                    "M10,13a1,1 0 1,1 2,0M10,16L12,13M10,16H12",
                )
            }//END_OF_SYMBOL( Brother )

            inner class Sister():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[PROTECTION, FEMALE, TWO]")
                override val pAr= arrayListOf(
                    "M0,12L4,8M4,8L8,12M2,16L4,12M4,12L6,16M2,16H6",
                    "M10,13a1,1 0 1,1 2,0M10,16L12,13M10,16H12",
                )
            }//END_OF_SYMBOL( Sister )

        } //END_OF_GROUPCODE( FAMILY )


        inner class PETS {

            inner class Cat():Symbol() {  //@@@MOJI??
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[ANIMAL + PAWS]A")
                override val pAr= arrayListOf(
                    "M0,12H8M0,16L2,12M2,12L4,16M4,16L6,12M6,12L8,16M0,16a1,1 0 1,1 2,0M4,16a1,1 0 1,1 2,0M8,16a1,1 0 1,1 2,0",
                )
            }//END_OF_SYMBOL( Cat )

            inner class Dog():Symbol() {//@@@MOJI??
            override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[ANIMAL, TAIL]A")
                override val pAr= arrayListOf(
                    "M2,8a2,2 0 1,0 0,4M2,12H10M2,16L4,12M4,12L6,16M6,16L8,12M8,12L10,16",
                )
            }//END_OF_SYMBOL( Dog )

            //@@@ ADD POSSIBILIITES:  CANARY, RABIT, FISH, PONY

        } //END_OF_GROUPCODE( PETS )

//        inner class TOYS { //TODO  - Seems like a good child-centric Group To Add early.
//           //BALL, BALLOON, BIKE, DOLL, SKATEBOARD, STUFFED ANIMAL, SANDBOX, SEESAW/TEETER-TOTTER,  (SWING)
//        }

        inner class PERSONAL_ARTIFACTS { //GK MANUAL MOD, TODO?  ADD TOYS, PLAY THINGS?

            inner class Comb():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[COMB]A")
                override val pAr= arrayListOf(
                    "M0,12V16M0,16H8M8,12V16M4,12V16",
                )
            }//END_OF_SYMBOL( Comb )

            inner class Brush():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[BRUSH]A")
                override val pAr= arrayListOf(
                    "M0,8V12M0,12H8M8,8V12M4,8V16",
                )
            }//END_OF_SYMBOL( Brush )

            inner class Hammer():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[HAMMER]B")
                override val pAr= arrayListOf(
                    "M0,16L6,10M4,8L8,12",
                )
            }//END_OF_SYMBOL( Hammer )

            inner class Knife():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[KNIFE]A")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L8,8",
                )
            }//END_OF_SYMBOL( Knife )

            inner class Needle():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("[NEEDLE]C")
                override val pAr= arrayListOf(
                    "M0,8V16M0,8a2,2 0 1,1 0,4",
                )
            }//END_OF_SYMBOL( Needle )

            inner class Rope():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[MANY, MANY, FIBER]")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12L10,16M6,16L10,12",
                    "M12,16a4,4 0 0,1 4,-4M16,12a4,4 0 0,0 4,-4",
                )
            }//END_OF_SYMBOL( Rope )

            inner class Fiber():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[FIBER]A")
                override val pAr= arrayListOf("M0,16a4,4 0 0,1 4,-4M4,12a4,4 0 0,0 4,-4")
            }//END_OF_SYMBOL( Fiber )

            inner class Cloth():Symbol() { //SHOULD REALLY BE WOVEN FIBERS - NOT STRAIGHT LINES
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[CLOTH]A")
                override val pAr= arrayListOf("M2,8V16M6,8V16M0,10H8M0,14H8")
            }//END_OF_SYMBOL( Cloth )

            inner class Money():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[MONEY]")
                override val pAr= arrayListOf("M0,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2,8V16")
            }//END_OF_SYMBOL( Money )

              inner class Key():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[KEY]")
                override val pAr= arrayListOf("M0,12H8M0,12a2,2 0 1,0 4,0M8,12V16")
            }//END_OF_SYMBOL( Key )

            inner class Thread():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("21P[MANY, FIBER]")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,16a4,4 0 0,1 4,-4M10,12a4,4 0 0,0 4,-4"
                )
            }//END_OF_SYMBOL( Thread )

            inner class Saw():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[SAW+TEETH]")
                override val pAr= arrayListOf(
                    "M0,20L12,8M4,16H8M8,12V16M8,12H12M12,8V12",
                )
            }//END_OF_SYMBOL( Saw )

            inner class Tube():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[BODY, IN]")
                override val pAr= arrayListOf(
                    "M0,8V16M4,8V16M0,16a2,2 0 1,1 4,0a2,2 0 1,1 -4,0"
                )
            }//END_OF_SYMBOL( Tube )

            inner class Shovel():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[SHOVEL]B")
                override val pAr= arrayListOf(
                    "M0,8H4M2,8V14M0,14H4M0,14a2,2 0 1,0 4,0",
                )
            }//END_OF_SYMBOL( Shovel )

            inner class Soap():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("[THING, WASH]")  //@@@ Where WASH is BOWL + WATER
                override val pAr= arrayListOf(
                    "M0,10H4M0,14H4M0,10V14M4,10V14",
                    "M6,12a4,4 0 1,0 8,0M6,12a2.5,2.5 0 0,1 4,0M10,12a2.5,2.5 0 0,0 4,0"
                )
            }//END_OF_SYMBOL( Soap )

            inner class Bed():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[BED]B")
                override val pAr= arrayListOf(
                    "M0,8V16M0,12H8M4,12L8,8M8,8V16"
                )
            }//END_OF_SYMBOL( Pillow )

            inner class Pillow():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[PILLOW, BED, POINTER]")
                override val pAr= arrayListOf(
                    "M0,8V16M0,12H8M4,12L8,8M8,8V16M6,5L8,7M8,7L10,5"
                )
            }//END_OF_SYMBOL( Pillow )

            inner class Blanket():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("[CLOTH, PROTECT, BED, POINTER]")
                override val pAr= arrayListOf(
                    "M2,8V16M6,8V16M0,10H8M0,14H8",
                    "M10,12L14,8M14,8L18,12",
                    "M20,8V16M20,12H28M24,12L28,8M28,8V16"
                )
            }//END_OF_SYMBOL( Pillow )

            inner class Toothbrush():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("[BRUSH, TEETH]")
                override val pAr= arrayListOf(
                    "M0,8V12M0,12H8M8,8V12M4,8V16",
                    "M10,12L12,16M12,16L14,12M10,12H14M14,12L16,16M16,16L18,12M14,12H18",
                )
            }//END_OF_SYMBOL( Toothbrush )

        } //END_OF_GROUPCODE( PERSONAL_ARTIFACTS )

        //==============================================================
        inner class KITCHEN_TOOLS {

            inner class Container():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[CONTAINER]A")
                override val pAr= arrayListOf("M0,12a4,4 0 1,0 8,0")
            }//END_OF_SYMBOL( Container )

            inner class Pitcher():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[PITCHER]A")
                override val pAr= arrayListOf("M0,8L4,12M4,8V16M4,16H8M8,8V16M8,10a2,2 0 1,1 0,4")
            }//END_OF_SYMBOL( Pitcher )

            inner class Cup():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("[CUP]A")
                override val pAr= arrayListOf("M0,12V16M0,16H4M4,12V16M4,12a2,2 0 1,1 0,4")
            }//END_OF_SYMBOL( Cup )

            inner class Dish():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("[DISH]A")
                override val pAr= arrayListOf("M0,12L4,16M4,16H12M12,16L16,12")
            }//END_OF_SYMBOL( Dish )

            inner class Drinkingglass():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[DRINKINGGLASS]A")
                override val pAr= arrayListOf("M0,8V16M0,16H4M4,8V16")
            }//END_OF_SYMBOL( Drinkingglass )

            inner class Fork():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[FORK]A")
                override val pAr= arrayListOf("M0,16L6,10M2,10L6,14M2,10L4,8M6,14L8,12")
            }//END_OF_SYMBOL( Fork )

            inner class Knife():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[KNIFE]A")
                override val pAr= arrayListOf("M0,12L4,16M0,16L8,8")
            }//END_OF_SYMBOL( Knife )

            inner class Spoon():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SPOON]A")
                override val pAr= arrayListOf("M0,16L4,12M4,12L4.5857,11.4142M4,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0")
            }//END_OF_SYMBOL( Spoon )

            inner class Pot():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[POT]A")
                override val pAr= arrayListOf("M2,8a2,2 0 1,0 0,4M2,8V16M2,16H10M10,8V16M10,8a2,2 0 1,1 0,4")
            }//END_OF_SYMBOL( Pot )

            inner class Pan():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[PAN]A")
                override val pAr= arrayListOf("M0,12V16M0,16H8M8,12V16M8,12L12,8")
            }//END_OF_SYMBOL( Pan )

        } //END_OF_GROUPCODE( KITCHEN_TOOLS )

    }//END_OF_SUPERGROUP( PERSON )

    //==============================================================================================================

    inner class SCENES() {//BEGIN SUPERGROUP( WORLD )

        inner class CONSTRUCTIONS {

            inner class Door():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[DOOR]B")
                override val pAr= arrayListOf("M0,8V16M0,8H8M8,8V16")
            }//END_OF_SYMBOL( Door )

            inner class Window():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[ENCLOSURE+PANES]A")
                override val pAr= arrayListOf("M0,8H8M0,16H8M0,8V16M8,8V16M0,12H8M4,8V16")
            }//END_OF_SYMBOL( Window )

            inner class Construction():Symbol() { //also Structure
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[CONSTRUCTION]B")
                override val pAr= arrayListOf(
                    "M-0.2083,8a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,8H6",
                    "M7.7917,8a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,10V14 M8,10V14",
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,16H6",
                    "M7.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Construction )

            inner class House():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[FOUNDATION, PROTECTION]A")  //@@@This is a Pictographically Combined Single Symbol, consisting of two logical Sub Symbols...an exception to the rule.
                override val pAr= arrayListOf(
                    "M0,12V16M0,16H8M8,12V16M0,12L4,8M4,8L8,12",
                )
            }//END_OF_SYMBOL( House )

            inner class Village():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("[HOUSE, MANY]")  //@@@  First MANY is more a "GROUP OF" but for consistency lets always use Many?
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12V16M6,16H14M14,12V16M6,12L10,8M10,8L14,12",
                )
            }//END_OF_SYMBOL( Village )

            inner class Neighbourhood():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
                override val hD = Header("[PART, MANY, MANY, HOUSE]")
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
                override val hD = Header("[HOUSE, MANY, MANY")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12L10,16M6,16L10,12",
                    "M12,12V16M12,16H20M20,12V16M12,12L16,8M16,8L20,12",
                )
            }//END_OF_SYMBOL( Town )

            inner class City():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
                override val hD = Header("[HOUSE, MANY, MANY, MANY")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    "M6,12L10,16M6,16L10,12",
                    "M12,12L16,16M12,16L16,12",
                    "M18,12V16M18,16H26M26,12V16M18,12L22,8M22,8L26,12",
                )
            }//END_OF_SYMBOL( City )

            inner class Library():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("[HOUSE, BOOK]")
                override val pAr= arrayListOf(
                    "M0,12V16M0,16H8M8,12V16M0,12L4,8M4,8L8,12",
                    "M10,8H14M10,16H14M10,8V16M14,8V16M14,8H18M14,16H18M18,8V16",
                )
            }//END_OF_SYMBOL( Library )

            inner class University():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=29.5, y1=21.5)
                override val hD = Header("[HOUSE, EXCHANGE, KNOWLEDGE]")   //@@@ This can also be 2 symbols where Eduction is:   Exchange +  Knowledge
                override val pAr= arrayListOf(
                    "M0,12V16M0,16H8M8,12V16M0,12L4,8M4,8L8,12",
                    "M10,12a4,4 0 1,0 8,0M12,14L14,16M14,16L16,14M14,8V16M12,10L14,8M14,8L16,10",
                    "M20,12a4,4 0 1,1 8,0M20,12V16M20,16H28M28,12V16",
                )
            }//END_OF_SYMBOL( University )

        } //END_OF_GROUPCODE( CONSTRUCTION )


        inner class TRANSPORT_PATHS {

            inner class Bridge():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("[BRIDGE]A")
                override val pAr= arrayListOf(
                    "M0,16H4M4,16a4,4 0 1,1 8,0M12,16H16",
                )
            }//END_OF_SYMBOL( Bridge )

            inner class Road():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("[PLACE + AUTOMOBILE]")
                override val pAr= arrayListOf(
                    "M0,16H16M8,12H16M8,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M8.5857,15.4142L11.4142,12.5857M8.5857,12.5857L11.4142,15.4142M12,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M12.5857,15.4142L15.4142,12.5857M12.5857,12.5857L15.4142,15.4142M13,11L15,9M14,10L16,12M2,13L4,15M4,15L6,13",
                )
            }//END_OF_SYMBOL( Road )

            inner class Walkway():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=29.5, y1=21.5)
                override val hD = Header("[PLACE, ACTIVITY, WALK]")
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
                override val hD = Header("[AUTOMOBILE]A")
                override val pAr= arrayListOf(
                    "M0,12H8M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.5857,15.4142L3.4142,12.5857M0.5857,12.5857L3.4142,15.4142M4,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4.5857,15.4142L7.4142,12.5857M4.5857,12.5857L7.4142,15.4142M5,11L7,9M6,10L8,12",
                )
            }//END_OF_SYMBOL( Automobile )

            inner class Bicycle():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[TWO, WHEEL]")
                override val pAr= arrayListOf( //@@@ to be modified to use || instead of 2
                    "M0,13a1,1 0 1,1 2,0M0,16L2,13M0,16H2",
                    "M4,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4.5857,15.4142L7.4142,12.5857M4.5857,12.5857L7.4142,15.4142",
                )
            }//END_OF_SYMBOL( Bicycle )

            inner class Bus():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[BUS]B")
                override val pAr= arrayListOf(
                    "M0,12H12M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.5857,15.4142L3.4142,12.5857M0.5857,12.5857L3.4142,15.4142M4,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4.5857,15.4142L7.4142,12.5857M4.5857,12.5857L7.4142,15.4142M8,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M8.5857,15.4142L11.4142,12.5857M8.5857,12.5857L11.4142,15.4142M9,11L11,9M10,10L12,12",
                )
            }//END_OF_SYMBOL( Bus )

            inner class Truck():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("[THING + BUS]B")
                override val pAr= arrayListOf(
                    "M0,12H12M0,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0.5857,15.4142L3.4142,12.5857M0.5857,12.5857L3.4142,15.4142M4,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M4.5857,15.4142L7.4142,12.5857M4.5857,12.5857L7.4142,15.4142M8,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M8.5857,15.4142L11.4142,12.5857M8.5857,12.5857L11.4142,15.4142M9,11L11,9M10,10L12,12M0,8V12M0,8H4M4,8V12",
                )
            }//END_OF_SYMBOL( Truck )

        } //END_OF_GROUPCODE( TRANSPORT_LAND )


        inner class TRANSPORT_AIR {

            inner class Airplane():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[WINGS + WHEEL]A")
                override val pAr= arrayListOf(
                    "M0,8a4,4 0 0,1 4,4M4,12a4,4 0 0,1 4,-4M2,14a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2.5857,15.4142L5.4142,12.5857M2.5857,12.5857L5.4142,15.4142",
                )
            }//END_OF_SYMBOL( Airplane )

            inner class BalloonHotAir():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[BALLOON+ENCLOSURE]")
                override val pAr= arrayListOf("M0,8a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M2,12V14M2,14H6M6,12V14")
            }//END_OF_SYMBOL( BalloonHotAir )

        } //END_OF_GROUPCODE( TRANSPORT_AIR )


        inner class TRANSPORT_WATER {

            inner class Boat():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[CONTAINER + MAST]A")
                override val pAr= arrayListOf(
                    "M4,8V12M0,12H8M0,12a4,4 0 1,0 8,0",
                )
            }//END_OF_SYMBOL( Boat )

            inner class Kayak():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=24.5, y1=21.5)
                override val hD = Header("[BOAT, PADDLE + WATER]")
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
                override val hD = Header("[TIME, INDICATOR]")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V12M4,12H8",
                    "M3,4H5M3,6H5M3,4V6M5,4V6",
                )
            }//END_OF_SYMBOL( Clock )

            inner class Day():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SUN + GROUND]")
                override val pAr= arrayListOf(
                    "M0,16H8M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                )
            }//END_OF_SYMBOL( Day )

            inner class Month():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[MOON]") //MODIFIED 1/2 MOON
                override val pAr= arrayListOf(
                    "M0,8V16M0,8a4,4 0 1,1 0,8",
                )
            }//END_OF_SYMBOL( Month )

            inner class Night():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[GROUND + MOON]")
                override val pAr= arrayListOf(
                    "M0,16H8M4,8a5,5 0 0,1 0,8M4,8a4,4 0 1,1 0,8",
                )
            }//END_OF_SYMBOL( Night )

            inner class Year():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[SUN + ARROWHEAD")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M6,10L8,12M8,12L10,10",
                )
            }//END_OF_SYMBOL( Year )

        } //END_OF_GROUPCODE( TIME )


        //inner class ENERGY {
        //
        //    inner class Bioenergy():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=25.5, y1=21.5)
        //        override val hD = Header("00F")
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,10a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M20,12V16M20,16a4,4 0 0,1 4,-4",
        //        )
        //    }//END_OF_SYMBOL( Bioenergy )
        //
        //    inner class Energy():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
        //        override val hD = Header("00F")
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //        )
        //    }//END_OF_SYMBOL( Energy )
        //
        //    inner class Energymental():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
        //        override val hD = Header("00F")
        //        override val pAr= arrayListOf(
        //            "M0,12L4,8M0,12H4M0,16L4,12",
        //            "M6,12a4,4 0 1,1 8,0",
        //        )
        //    }//END_OF_SYMBOL( Energymental )
        //
        //    inner class Energyphysical():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
        //        override val hD = Header("00F")
        //        override val pAr= arrayListOf(
        //            "M0,12L4,8M0,12H4M0,16L4,12",
        //            "M8,8a5,5 0 0,0 0,8M8,8H16M8,16H16M16,8a5,5 0 0,1 0,8",
        //        )
        //    }//END_OF_SYMBOL( Energyphysical )
        //
        //    inner class Geothermalenergy():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("00F")
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,16H26",
        //        )
        //    }//END_OF_SYMBOL( Geothermalenergy )
        //
        //    inner class Oilpower():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("00F")
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,12a2.5,2.5 0 0,1 4,0M22,12a2.5,2.5 0 0,0 4,0M18,16a2.5,2.5 0 0,1 4,0M22,16a2.5,2.5 0 0,0 4,0",
        //        )
        //    }//END_OF_SYMBOL( Oilpower )
        //
        //    inner class Solarenergy():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("00F")
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //        )
        //    }//END_OF_SYMBOL( Solarenergy )
        //
        //    inner class Wavepower():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("00F")
        //        override val pAr= arrayListOf(
        //            "M0,10L8,14M8,10V14M8,10L16,14M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
        //            "M18,16a2.5,2.5 0 0,1 4,0M22,16a2.5,2.5 0 0,0 4,0M18,12L20,14M20,14L22,12",
        //        )
        //    }//END_OF_SYMBOL( Wavepower )
        //
        //    inner class Windpower():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=31.5, y1=21.5)
        //        override val hD = Header("00F")
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
                override val hD = Header("[MINUS, THING]")
                override val pAr= arrayListOf(
                    "M0,12H4",
                    "M6,10H10M6,14H10M6,10V14M10,10V14"
                )
            }//END_OF_SYMBOL( None )

            inner class Few():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("NUMBER, INDICATOR, SMALL")
                override val pAr= arrayListOf(
                    "M1,16L5,8M2,10H10M5,16L9,8M0,14H8",
                    "M4,4L5,6M5,6L6,4",
                    "M12,12H16M14,12V16M12,16H16"
                )
            }//END_OF_SYMBOL( Few )

            inner class Some():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[PART, INDICATOR]")
                override val pAr= arrayListOf(
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,12H4",
                    "M1.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,4L2,6M2,6L3,4",
                )
            }//END_OF_SYMBOL( Some )

            inner class Many():Symbol() {  //file name:  Group_of  from fileName: group_of,much_of,many_of,quantity_of
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[MANY]")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                )
            }//END_OF_SYMBOL( Many )

            inner class ManyMany():Symbol() {  //file name: very much, very many
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[MANY, MANY, INDICATOR]")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    //"M1,4L2,6M2,6L3,4", 'V' Indicator
                    "M6,12L10,16M6,16L10,12"
                )
            }//END_OF_SYMBOL( ManyMany )

            inner class All():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[ENCLOSURE + MANY]")
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16M0,8L8,16M0,16L8,8",
                )
            }//END_OF_SYMBOL( All )

            inner class One():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("[ONE]")
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                )
            }//END_OF_SYMBOL( One )

            inner class Two():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("[ONE, ONE]")
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                    "M3,8  H5  M3,16  H5  M3,8  V16  M5,8  V16",
                )
            }//END_OF_SYMBOL( Two )

            inner class Three():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("[ONE, ONE, ONE]")
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                    "M3,8  H5  M3,16  H5  M3,8  V16  M5,8  V16",
                    "M6,8  H8  M6,16  H8  M6,8  V16  M8,8  V16",
                )
            }//END_OF_SYMBOL( Three )

            inner class Four():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("[ONE, ONE, ONE, ONE]")
                override val pAr= arrayListOf(
                    "M0,8  H2  M0,16  H2  M0,8  V16  M2,8  V16",
                    "M3,8  H5  M3,16  H5  M3,8  V16  M5,8  V16",
                    "M6,8  H8  M6,16  H8  M6,8  V16  M8,8  V16",
                    "M9,8  H11  M9,16 H11  M9,8 V16 M11,8  V16",
                )
            }//END_OF_SYMBOL( Four )

            inner class Five():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("[FIVE]")
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
        //        override val hD = Header("00F")
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
        //        override val hD = Header("00F")
        //        override val pAr= arrayListOf(
        //            "M0,12a4,4 0 1,1 8,0M0,16L4,8M4,8L8,16M0,16H8",
        //            "M10,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M14,8V16",
        //        )
        //    }//END_OF_SYMBOL( Biology )
        //
        //    inner class Physics():Symbol() {
        //        override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=27.5, y1=21.5)
        //        override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,16H4"
                )
            }//END_OF_SYMBOL( Color )

            inner class Red():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("[FIRE + COLOR]", COLORS.name("RED"))
                override val pAr= arrayListOf(  "M1,8a2.5,2.5 0 0,1 0,4M1,12a2.5,2.5 0 0,0 0,4", ) //Fire
            }//END_OF_SYMBOL( Red )

            inner class Blue():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[CLOUD + COLOR]", COLORS.name("BLUE"))
                override val pAr= arrayListOf("M0,8H8M0,8a2.5,2.5 0 0,1 4,0M4,8a2.5,2.5 0 0,0 4,0") //Cloud
            }//END_OF_SYMBOL( Blue )

            inner class Green():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[GRASS + COLOR]", COLORS.name("GREEN"))
                override val pAr= arrayListOf( "M0,16a4,4 0 0,1 4,-4")   //Grass
            }//END_OF_SYMBOL( Green )

            inner class Yellow():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[SUN + COLOR", COLORS.name("YELLOW"))
                override val pAr= arrayListOf("M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0") //Sun
            }//END_OF_SYMBOL( Yellow )

        }//END_OF_GROUPCODE(COLORSGROUP )


        inner class DIRECTIONS() {

            inner class BLANK:Symbol() {override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5);override val pAr= arrayListOf("M0,12")}//END_OF_SYMBOL( BLANK )

            inner class Right():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[VERTICALLINE, HORIZONTALLINE]B")
                override val pAr= arrayListOf("M0,8H4M0,8V16")
            }//END_OF_SYMBOL( Right )

            inner class Left():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[VERTICALLINE, HORIZONTALLINE]B")
                override val pAr= arrayListOf("M0,8H4M4,8V16")
            }//END_OF_SYMBOL( Left )

            inner class Up():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[ARROW]A")
                override val pAr= arrayListOf("M0,10L2,8M2,8L4,10M2,8V16")
            }//END_OF_SYMBOL( Up )

            inner class Down():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[ARROW]A")
                override val pAr= arrayListOf("M0,14L2,16M2,16L4,14M2,8V16")
            }//END_OF_SYMBOL( Down)

            inner class North():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[EARTH, ARROW]A")
                override val pAr= arrayListOf("M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M2,11L4,9M4,9L6,11")
            }//END_OF_SYMBOL( North )

            inner class South():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[EARTH, ARROW]A")
                override val pAr= arrayListOf("M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M2,13L4,15M4,15L6,13")
            }//END_OF_SYMBOL( South )

            inner class East():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[EARTH, ARROW]A")
                override val pAr= arrayListOf("M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M5,10L7,12M5,14L7,12")
            }//END_OF_SYMBOL( East )

            inner class West():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[EARTH, ARROW]A")
                override val pAr= arrayListOf("M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M1,12L3,10M1,12L3,14")
            }//END_OF_SYMBOL( West )

            //east, west, south, north
        }//END_OF_GROUPCODE( DIRCTIONS())


        inner class SHAPES() {

            inner class BLANK:Symbol() {override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5);override val pAr= arrayListOf("M0,12")}//END_OF_SYMBOL( BLANK )

            inner class Shape():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("[EYE, THING]C")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,10H10M6,14H10M6,10V14M10,10V14"
                )
            }//END_OF_SYMBOL( Shape )

            inner class Line():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("[LINE]C")
                override val pAr= arrayListOf("M0,8V16")
            }//END_OF_SYMBOL( Line )

            inner class Curve():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("[CURVE]C")
                override val pAr= arrayListOf("M0,8a4,4 0 1,1 0,8")
            }//END_OF_SYMBOL( Curve )



            inner class RoundShape():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[SHAPE, ROUND]C")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,10H10M6,14H10M6,10V14M10,10V14",
                    "M12,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0"
                )
            }//END_OF_SYMBOL( RoundShape )

            inner class SquareShape():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[SHAPE, SQUARE]C")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,10H10M6,14H10M6,10V14M10,10V14",
                    "M12,8H20M12,16H20M12,8V16M20,8V16"
                )
            }//END_OF_SYMBOL( SquareShape )

            inner class TriangleShape():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[SHAPE, TRIANGLE]C")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,10H10M6,14H10M6,10V14M10,10V14",
                    "M12,16L16,8M16,8L20,16M12,16H20"
                )
            }//END_OF_SYMBOL( TriangleShape )

            inner class DiamondShape():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("[SHAPE, DIAMOND]C")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6,10H10M6,14H10M6,10V14M10,10V14",
                    "M12,12L14,8M14,8L16,12M12,12L14,16M14,16L16,12"
                )
            }//END_OF_SYMBOL( DiamondShape )

            //Polygon  <<<many sided

            inner class Parallel():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("[REFERENCELINE, REFERENCELINE]C")
                override val pAr= arrayListOf("M0,8V16M2,8V16")
            }//END_OF_SYMBOL( Parallel )

            inner class Perpendicular():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("[REFERENCELINE, REFERENCELINE]C")
                override val pAr= arrayListOf("M0,16H8M4,8V16")
            }//END_OF_SYMBOL( Perpendicular )

        }//END_OF_GROUPCODE( SHAPES())

    }//END_OF_SUPERGROUP( CONCEPTS )

    //==============================================================================================================

    inner class LANGUAGE() { //BEGIN SUPERGROUP( LANGUAGE )

        inner class PREPOSITIONS {

            inner class At():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("20L")
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( At )


            inner class Above():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("20L")
                override val pAr= arrayListOf(
                    "M0,12H8",
                    "M3.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Above )

            inner class Below():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("20L")
                override val pAr= arrayListOf(
                    "M0,12H8",
                    "M3.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Below )

            inner class Before():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("20L")
                override val pAr= arrayListOf(
                    "M-0.2083,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,8V16"
                )
            }//END_OF_SYMBOL( Before )

            inner class After():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("20L")
                override val pAr= arrayListOf(
                    "M0,8V16",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( After )

            inner class Inside():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("20L")
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Inside )

            inner class Outside():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("20L")
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16",
                    "M9.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Outside )

            inner class Indoors():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("21P[HOUSE, INDICATOR, DOT]")
                override val pAr= arrayListOf(
                    "M0,12V16M0,16H8M8,12V16M0,12L4,8M4,8L8,12",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3,4L4,6M4,6L5,4"
                )
            }//END_OF_SYMBOL( Indoors )

            inner class Outdoors():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("21P[HOUSE, INDICATOR, DOT]")
                override val pAr= arrayListOf(
                    "M0,12V16M0,16H8M8,12V16M0,12L4,8M4,8L8,12",
                    "M9.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3,4L4,6M4,6L5,4"
                )
            }//END_OF_SYMBOL( Outdoors )

            inner class On():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("20L")
                override val pAr= arrayListOf(
                    "M0,16H4M0,14L2,16M2,16L4,14"
                )
            }//END_OF_SYMBOL( On )

            inner class To():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("20L")
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12M4,10V14"
                )
            }//END_OF_SYMBOL( To )

        } //END_OF_GROUPCODE( PREPOSITIONS )   //<<EMPTY, SEE NOTE

        inner class ARTICLES {   //BEGIN GROUPCODE( ARTICLES )

            inner class An():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,11L2,13"
                )
            }//END_OF_SYMBOL( An )

            inner class ThatThere():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=4.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M-0.2083,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,13L3,11",
                )
            }//END_OF_SYMBOL( ThatThere )

            inner class This():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=4.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16M0,8L8,16M0,16L8,8",
                )
            }//END_OF_SYMBOL( All )

            inner class At():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( At )

            inner class Bad():Symbol() {  //TODO There are two different 'Bad' files
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8V16M0,14L2,16M2,16L4,14M4,8V16",
                    "M1,4L2,6M2,6L3,4"
                )
            }//END_OF_SYMBOL( Bad )

            inner class Fast():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H8M6,10L8,12M6,14L8,12",
                    //"M3,4L4,6M4,6L5,4" Indicator?
                    "M10,10V13", "M9.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Fast )

            inner class Slow():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H8M6,10L8,12M6,14L8,12",
                    //"M3,4L4,6M4,6L5,4",
                    "M10,12H14", "M16,10V13",
                    "M15.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Slow )

            inner class Huge():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8H4M2,8V16M0,16H4",
                    "M1,4L2,6M2,6L3,4",
                    "M6,10V13",
                    "M5.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Huge )

            inner class High_Tall():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8H4M0,16H4", "M1,4L2,6M2,6L3,4"
                )
            }//END_OF_SYMBOL( High_Tall )

            inner class Big():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8H4M2,8V16M0,16H4",
                    "M1,4L2,6M2,6L3,4",
                )
            }//END_OF_SYMBOL( Big )

            inner class Cold():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12L2,10M0,12L2,14M2,12a2.5,2.5 0 0,1 4,0M6,12a2.5,2.5 0 0,0 4,0M4,10H8M4,14H8M4,10V14M8,10V14M10,10L12,12M10,14L12,12",
                    "M5,4L6,6M6,6L7,4",
                )
            }//END_OF_SYMBOL( Cold )

            inner class Coldoppositehot():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10L2,8M2,8V16M2,16L4,14",
                    "M6,12L8,10M6,12L8,14M10,8a2.5,2.5 0 0,1 0,4M10,12a2.5,2.5 0 0,0 0,4M12,10L14,12M12,14L14,12",
                    "M9,4L10,6M10,6L11,4",
                )
            }//END_OF_SYMBOL( Coldoppositehot )

            inner class Correct():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0M2,12H6M4,10V14",
                    "M3,4L4,6M4,6L5,4",
                    "M10,10V13",
                    "M9.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Correct )

            inner class Correctthinking():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0M2,12H6M4,10V14",
                )
            }//END_OF_SYMBOL( Correctthinking )

            inner class Different():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,16L8,8M2,10H6M2,14H6",
                )
            }//END_OF_SYMBOL( Different )

            inner class Dirty():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M0,16L8,8",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Dry )

            inner class Empty():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M4,14a4,4 0 1,1 8,0M10,12L12,14M12,14L14,12",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Empty )

            inner class Far():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H4",
                    "M6,10H10M6,14H10M6,10V14M10,10V14"
                )
            }//END_OF_SYMBOL( None )

            inner class Few():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10L2,8M2,8V16M2,16L4,14",
                    "M6,12L10,16M6,16L10,12",
                    "M7,4L8,6M8,6L9,4",
                )
            }//END_OF_SYMBOL( Few )

            inner class Some():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M1.7917,10a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0,12H4",
                    "M1.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,4L2,6M2,6L3,4",
                )
            }//END_OF_SYMBOL( Some )

            inner class Full():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M0,12H8",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Full )

            inner class Good():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                )
            }//END_OF_SYMBOL( Many )

            inner class ManyMany():Symbol() {  //file name:
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L4,12",
                    //"M1,4L2,6M2,6L3,4", 'V' Indicator
                    "M6,12L10,16M6,16L10,12"
                )
            }//END_OF_SYMBOL( ManyMany )

            inner class Heavy():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H8M2,16L4,12M4,12L6,16M2,16H6",
                    "M3,4L4,6M4,6L5,4",
                    "M10,14L12,16M12,16L14,14M12,8V16",
                )
            }//END_OF_SYMBOL( Heavy )

            inner class Here():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Here )

            inner class In():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8H8M0,16H8M0,8V16M8,8V16",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( In )

            inner class Incorrect():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0M2,12H6",
                    "M3,4L4,6M4,6L5,4",
                    "M10,10V13",
                    "M9.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Incorrect )

            inner class IncorrectThinking():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0M2,12H6",
                )
            }//END_OF_SYMBOL( IncorrectThinking )

            inner class Leftturn():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8H4M4,8V16",
                )
            }//END_OF_SYMBOL( Leftturn )

            inner class Lightweight():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H8M2,16L4,12M4,12L6,16M2,16H6",
                    "M3,4L4,6M4,6L5,4",
                    "M10,12H14M12,12V16M10,16H14",
                )
            }//END_OF_SYMBOL( Lightweight )

            inner class Linear():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M1,8V16",
                    "M0,4L1,6M1,6L2,4",
                )
            }//END_OF_SYMBOL( Linear )

            inner class Little():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H4M2,12V16M0,16H4",
                    "M1,4L2,6M2,6L3,4",
                )
            }//END_OF_SYMBOL( Little )

            inner class Long():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10V14M0,12H8M8,10V14",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Long )

            inner class Name():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8L8,16M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                )
            }//END_OF_SYMBOL( Name )

            inner class Nearness():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10V14M4,10V14",
                )
            }//END_OF_SYMBOL( Nearness )

            inner class New():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V16M4,12H12M10,10L12,12M10,14L12,12",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( New )

            inner class Old():Symbol() {  //Opposite New
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=19.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10L2,8M2,8V16M2,16L4,14",
                    "M6,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M10,8V16M10,12H18M16,10L18,12M16,14L18,12",
                    "M9,4L10,6M10,6L11,4",
                )
            }//END_OF_SYMBOL( Oldoppositenew )

            inner class Rightturn():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8H4M0,8V16",
                )
            }//END_OF_SYMBOL( Rightturn )

            inner class Rotation():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,14L2,12M2,12L4,14M6,8a4,4 0 1,1 -4,4",
                )
            }//END_OF_SYMBOL( Rotation )

            inner class Rotten():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=25.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12L4,16M0,16L8,8",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Sharpknife )

            inner class Short():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10V14M0,12H4M4,10V14",
                    "M1,4L2,6M2,6L3,4",
                )
            }//END_OF_SYMBOL( Short )

            inner class Smooth():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12L4,16M4,8V16",
                    "M1,4L2,6M2,6L3,4",
                    "M6,8V16M6,16H10M10,8V16",
                )
            }//END_OF_SYMBOL( Smooth )

            inner class Squaredescription():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=21.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M-0.2083,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,12L6,10M4,12L6,14",
                )
            }//END_OF_SYMBOL( There )

            inner class Thin():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12M2,8V16M6,8V16M6,12L8,10M6,12L8,14",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Thin )

            inner class Thin2():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10L2,12M0,14L2,12M2,8V16M6,8V16M6,12L8,10M6,12L8,14",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Thin )

            inner class Warm():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12L2,10M0,12L2,14M4,8a2.5,2.5 0 0,1 0,4M4,12a2.5,2.5 0 0,0 0,4M6,10L8,12M6,14L8,12",
                    "M3,4L4,6M4,6L5,4",
                    "M10,12H14M12,12V16M10,16H14",
                )
            }//END_OF_SYMBOL( Warm )

            inner class Hot_temperature:Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12L2,10M0,12L2,14M4,8a2.5,2.5 0 0,1 0,4M4,12a2.5,2.5 0 0,0 0,4M6,10L8,12M6,14L8,12",
                    "M3,4L4,6M4,6L5,4"
                )
            }//END_OF_SYMBOL( Hot_temperature )

            inner class Wet():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Wet )

            inner class What():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,11a1,1 0 1,1 1,1M1,12V13",
                    "M0.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,10H8M4,14H8M4,10V14M8,10V14",
                )
            }//END_OF_SYMBOL( WhatThing )

            inner class Who():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,11a1,1 0 1,1 1,1M1,12V13",
                    "M0.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,16H8M6,8V16",
                )
            }//END_OF_SYMBOL( Who )

            inner class Wide():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8V16M0,12L2,10M0,12L2,14M6,10L8,12M6,14L8,12M8,8V16",
                    "M3,4L4,6M4,6L5,4",
                )
            }//END_OF_SYMBOL( Wide )

            inner class With():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8H4M2,6V10",
                )
            }//END_OF_SYMBOL( With )

        } //END_OF_GROUPCODE( ADJECTIVES )

        inner class PRONOUNS {
            inner class He():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16",
                    "M6,13a1,1 0 1,1 1,1M7,14a1,1 0 1,1 -1,1",
                )
            }//END_OF_SYMBOL( He )

            inner class I():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16",
                    "M6,13L7,12M7,12V16M6,16H8",
                )
            }//END_OF_SYMBOL( I )

            inner class Me():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M0,16H4",
                    "M6,13L7,12M7,12V16M6,16H8",
                )
            }//END_OF_SYMBOL( Me )

            inner class She():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M0,16H4",
                    "M6,13a1,1 0 1,1 1,1M7,14a1,1 0 1,1 -1,1",
                )
            }//END_OF_SYMBOL( She )

            inner class They():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M0,16H4",
                    "M1,4L3,6M1,6L3,4",
                    "M6,13a1,1 0 1,1 1,1M7,14a1,1 0 1,1 -1,1",
                )
            }//END_OF_SYMBOL( They )

            inner class Those():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=4.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M-0.2083,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M1,13L3,11",
                    "M1,4L3,6M1,6L3,4",
                )
            }//END_OF_SYMBOL( Those )

            inner class We():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M2,8V12M0,16L2,12M2,12L4,16M0,16H4",
                    "M1,4L3,6M1,6L3,4",
                    "M6,13L7,12M7,12V16M6,16H8",
                )
            }//END_OF_SYMBOL( We )

            inner class You():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,16H4M2,8V16",
                    "M6,13a1,1 0 1,1 2,0M6,16L8,13M6,16H8",
                )
            }//END_OF_SYMBOL( You )

        } //END_OF_GROUPCODE( PRONOUNS )


        inner class INTERJECTIONS {

            inner class Yes_indeed():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H4M2,10V14", "M6,10V13",
                    "M5.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M8,10V13",  "M7.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Yes_indeed )

            inner class No_indeed():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H4", "M6,10V13",
                    "M5.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M8,10V13",  "M7.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( No_indeed )

            inner class Wow_super():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
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
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,11a1,1 0 1,1 1,1M1,12V13",
                    "M0.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,16L6,12M6,12L8,16",
                )
            }//END_OF_SYMBOL( Howq )

            inner class WHEN():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,11a1,1 0 1,1 1,1M1,12V13",
                    "M0.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M8,8V12M8,12H12",
                )
            }//END_OF_SYMBOL( When )

            inner class Where():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,16H8M3,11a1,1 0 1,1 1,1M4,12V13",
                    "M3.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Whereq )

            inner class Not():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12 a2,2 0 1,1 4,0 a2,2 0 1,1 -4,0  M2,10 V14"
                    //"M1,6L2,4M2,4L3,6"
                )
            }//END_OF_SYMBOL( Is )

            inner class Have():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,16H4M0,14H4M2,12V16",
                    //"M1,6L2,4M2,4L3,6" Indicator
                )
            }//END_OF_SYMBOL( Have )

            inner class Can():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M-0.2083,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M2,12L4,16M4,16L6,12",
                    //"M3,6L4,4M4,4L5,6" Indicator
                )
            }//END_OF_SYMBOL( Can )

            inner class See():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0",
                    "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    //"M1,6L2,4M2,4L3,6" //Indicator
                )
            }//END_OF_SYMBOL( See )

            inner class Hear():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 4,0q0,3 -4,6",
                    //"M1,6L2,4M2,4L3,6" //Indicator
                )
            }//END_OF_SYMBOL( Hear )

            inner class Feel():Symbol() { //Touch
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12L4,16M4,8V16",
                    //"M1,6L2,4M2,4L3,6" //Indicator
                )
            }//END_OF_SYMBOL( Feel )

            inner class Know():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0M0,12V16M0,16H8M8,12V16",
                    //"M3,6L4,4M4,4L5,6"
                )
            }//END_OF_SYMBOL( Know )

            inner class Get():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M2,14L4,16M4,16L6,14M4,8V16"
                    //"M3,6L4,4M4,4L5,6"
                )
            }//END_OF_SYMBOL( Get )

            inner class Give():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M2,10L4,8M4,8L6,10M4,8V16",
                    //"M3,6L4,4M4,4L5,6"
                )
            }//END_OF_SYMBOL( Give )

            inner class Stand():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=23.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8V16M0,16H4",
                    //"M1,6L2,4M2,4L3,6"
                )
            }//END_OF_SYMBOL( Stand )

            inner class Sit():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8V12M0,12H4M4,12V16",
                    //"M1,6L2,4M2,4L3,6"
                )
            }//END_OF_SYMBOL( Sit )

            inner class Lie_down():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,16H8M8,12V16"
                )
            }//END_OF_SYMBOL( Lie_down )

            inner class Eat():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    //"M5,6 L6,4 M6,4 L7,6", Action Indicator
                    "M0,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M0,16H4",
                    //"M1,6L2,4M2,4L3,6"
                )
            }//END_OF_SYMBOL( Eat )

            inner class Move_carry():Symbol() { //GK custom added 0322
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,0 8,0M8,12H12M10,10L12,12M10,14L12,12"
                )
            }//END_OF_SYMBOL( Move )

            inner class Crawl():Symbol() { //GK custom added 0322
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8V12M0,12L4,16M0,16H4",
                    //"M1,6L2,4M2,4L3,6",  Indicator
                    "M6,12H14M12,10L14,12M12,14L14,12"
                )
            }//END_OF_SYMBOL( Crawl )

            inner class Fly():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8a4,4 0 0,1 4,4M4,12a4,4 0 0,1 4,-4",
                )
            }//END_OF_SYMBOL( Fly )

            inner class Walk():Symbol() { //GK custom added 0322
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,16L4,8M4,8L8,16M0,16H4M8,16H12",
                    //"M3,6L4,4M4,4L5,6"   Indicator
                )
            }//END_OF_SYMBOL( Walk )

            inner class Run():Symbol() { //GK custom added 0322
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,16L4,8M4,8L8,16M0,16H4M8,16H12",
                    "M08,12H13.5  M12,10L14,12   M12,14L14,12",
                    "M15.5,10V13",
                    "M15.3,14a0.2083,0.2083 0 1,1 0.4166,0  a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Run )

            inner class Bounce():Symbol() { //GK custom added 0322
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8L4,12M0,16L4,12M0,14V16M0,16H2M4,8V16"
                )
            }//END_OF_SYMBOL( Bounce )

            inner class Jump():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,16a4,4 0 1,1 8,0M6,14L8,16M8,16L10,14"
                )
            }//END_OF_SYMBOL( Jump )

            inner class Pull():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10H4M0,14H4M0,10V14M4,10V14M4,12H8M6,10L8,12M6,14L8,12",
                    //"M1,6L2,4M2,4L3,6"
                )
            }//END_OF_SYMBOL( Pull )

            inner class Push():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H8M6,10L8,12M6,14L8,12M8,10H12M8,14H12M8,10V14M12,10V14",
                    //"M9,6L10,4M10,4L11,6"
                )
            }//END_OF_SYMBOL( Push )

            inner class Drink():Symbol() { //GKMOD
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a2.5,2.5 0 0,1 4,0M4,12a2.5,2.5 0 0,0 4,0M2,12a2,2 0 1,1 4,0a2,2 0 1,1 -4,0M2,16H6",
                    //"M3,6L4,4M4,4L5,6"  indicator
                )
            }//END_OF_SYMBOL( Drink )

            inner class Hunt():Symbol() { //GKMOD
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=23.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    //"M0,12H12M1,12a2,2 0 1,0 4,0", rifle
                    "M0,8a4,4 0 1,1 0,8M0,12H8M6,10L8,12M6,14L8,12",
                    //"M5,6 L6,4 M6,4 L7,6", Action Indicator
                    "M12,12 H20 M12,16  L14,12  M14,12  L16,16  M16,16   L18,12  M18,12   L20,16"
                )
            }//END_OF_SYMBOL( Hunt )

            inner class Sleep():Symbol() { //GKMOD
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H4M2,10V14"
                )
            }//END_OF_SYMBOL( And )

            inner class Or():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,12L2,10M0,12L2,14", "M1.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0")
            }//END_OF_SYMBOL( Or )

            inner class But():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12H4M4,10V14"
                )
            }//END_OF_SYMBOL( But )

            inner class If():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,11a1,1 0 1,1 1,1M1,12V13",
                    "M0.7917,14a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M3.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4,10L6,12M4,14L6,12",
                )
            }//END_OF_SYMBOL( If )

            inner class Unless():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,12H4M4,10V14", "M6,12L8,10M6,12L8,14","M7.7917,12a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0")
            }//END_OF_SYMBOL( Unless )

            inner class Therefore():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=3.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,10L2,12M0,14L2,12M0,10V14")
            }//END_OF_SYMBOL( Therefore )

            inner class Because():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=7.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,0")
            }

            inner class Comma():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M0.27773333333333333,16q0.06943333333333333,0.6943333333333334 -0.5554666666666667,1.1109333333333333",
                )
            }//END_OF_SYMBOL( Comma )

            inner class QuestionMark():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,10a2,2 0 1,1 2,2M2,12V14",
                    "M1.7917,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( QuestionMark )

            inner class Ellipse():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M1.5, 16  a0.2083,0.2083 0 1,1 0.4166,0  a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M4, 16  a0.2083,0.2083 0 1,1 0.4166,0  a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M6.5, 16  a0.2083,0.2083 0 1,1 0.4166,0  a0.2083,0.2083 0 1,1 -0.4166,0"
                )
            }//END_OF_SYMBOL( Ellipse )

            inner class ExclamationMark():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,8V14",
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Exclamationmark )

            inner class Period():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=1.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M-0.2083,16a0.2083,0.2083 0 1,1 0.4166,0a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Period )

            inner class AcceptKey():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=6.0, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M4,8 V12 M0,12   L2,10  M0,12  L2,14  M0,12  H4"
                )
            }//END_OF_SYMBOL( AcceptKey )

            inner class EndOfParagraph():Symbol() {//Temp, using Card symbol (page laying down) Modified to make width of Page
            override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,10  H4  M0,14  H4  M0,10  V14  M4,10  V14")
            }//END_OF_SYMBOL( EndOfParagraph )

            inner class EndOfPage():Symbol() { //Temp, using PAGEe symbol
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,8 H4  M0,16H4  M0,8  V16  M4,8  V16")
            }//END_OF_SYMBOL( EndOfPage )

            inner class EndOfDocument():Symbol() { //Temp, using Book symbol
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,8H4M0,16H4M0,8V16M4,8V16M4,8H8M4,16H8M8,8V16")
            }//END_OF_SYMBOL( EndOfDocument )

            //  <path d="M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V16M4,12H12M10,10L12,12M10,14L12,12"></path><path d="M3,4L4,6M4,6L5,4"></path>

            inner class NewBuffer():Symbol() { //Temp, using Book symbol
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=13.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0M4,8V16M4,12H12M10,10L12,12M10,14L12,12",
                    "M3,4L4,6M4,6L5,4"
                )
            }//END_OF_SYMBOL( NewBuffer)

            inner class UpArrowSentence():Symbol() { //Temp, using Book symbol
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,10L2,8M2,8L4,10M2,8V16")
            }//END_OF_SYMBOL( UpArrowSentence )

            inner class DnArrowSentence():Symbol() { //Temp, using Book symbol
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,14L2,16M2,16L4,14M2,8V16")
            }//END_OF_SYMBOL( DnArrowSentence )

            //inner class UpArrowParagraph():Symbol() { //Temp, using Book symbol
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
            //    override val hD = Header("00F")
            //    override val pAr= arrayListOf("M0,10L2,8M2,8L4,10M2,8V16")
            //}//END_OF_SYMBOL( UpArrowParagraph )
            //
            //inner class DnArrowParagraph():Symbol() { //Temp, using Book symbol
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
            //    override val hD = Header("00F")
            //    override val pAr= arrayListOf("M0,14L2,16M2,16L4,14M2,8V16")
            //}//END_OF_SYMBOL( DnArrowParagraph )
            //
            //
            //inner class UpArrowPage():Symbol() { //Temp, using Book symbol
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
            //    override val hD = Header("00F")
            //    override val pAr= arrayListOf("M0,10L2,8M2,8L4,10M2,8V16")
            //}//END_OF_SYMBOL( UpArrowPage )
            //
            //inner class DnArrowPage():Symbol() { //Temp, using Book symbol
            //    override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=5.5, y1=21.5)
            //    override val hD = Header("00F")
            //    override val pAr= arrayListOf("M0,14L2,16M2,16L4,14M2,8V16")
            //}//END_OF_SYMBOL( DnArrowPage )


            //SPECIAL KEYBOARD COMMANDS, NOT SYMBOLS
            inner class Backspace():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,12L2,10M0,12L2,14M0,12H8")
            }

            inner class ListToConsole1():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,6 H12", "M0,9 H12", "M0,12 H12")
            }

            inner class ListToConsole2():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=17.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,6 H12", "M0,9 H12", "M0,12 H12")
            }

            inner class ClearAll():Symbol() { //emptying, voidance, without Indicator
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=15.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf("M0,12a4,4 0 1,0 8,0M4,14a4,4 0 1,1 8,0M10,12L12,14M12,14L14,12")
            }

            inner class ReadFile1():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=11.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
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
//                override val hD = Header("00F")
//                override val pAr= arrayListOf("M0,6 H12", "M0,9 H12", "M0,12 H12")
//            }


        } //END_OF_GROUPCODE( PUNCTUATION )

        inner class EMOJIS {

            inner class Facefrowny():Symbol() {//BROKEN, TODO: FIX?
            override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12  a4,4 0 1,1  8,0 a4,4 0 1,1 -8,0",
                    "M5.4,14.5 a1.3, 1.3 0 1 ,0 -3, 0.01",
                )
            }//END_OF_SYMBOL( Facefrowny )

            inner class Faceidea():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=10.5, y1=21.5)
                override val hD = Header("00F")
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
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0a4,4 0 1,1 -8,0",
                    "M2.4,13 a1.5,1.5 0 1,0 3.0,0",
                    "M2,11a 0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5,11a 0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Facesmiley )

            inner class Facestraight():Symbol() {
                override val vB = ViewBox(x0=-0.75, y0=-0.75, x1=9.5, y1=21.5)
                override val hD = Header("00F")
                override val pAr= arrayListOf(
                    "M0,12a4,4 0 1,1 8,0 a4,4 0 1,1 -8,0",
                    "M2.2,13.8   H5.5",
                    "M2,11 a0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                    "M5,11 a0.2083,0.2083 0 1,1 0.4166,0 a0.2083,0.2083 0 1,1 -0.4166,0",
                )
            }//END_OF_SYMBOL( Facestraight )

        } //END_OF_GROUPCODE( EMOJIS )

    } //END_OF_SUPERGROUP( LANGUAGE )


    //^=========================================================END SYMBOLS===============================================================================

    //========================================================END SPLICE GENERATED SYMBOLS HERE==================================================

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
            keyAdd(FRUITS())
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

        with( GROUPS().SCENES()) { keyAdd(CONSTRUCTIONS());keyAdd(TRANSPORT_LAND());keyAdd(TRANSPORT_WATER());keyAdd(TRANSPORT_AIR());keyAdd(TRANSPORT_PATHS()); }

        with( GROUPS().CONCEPTS()) {keyAdd(TIME());keyAdd(ENUMERATION());keyAdd(COLORSGROUP());keyAdd(DIRECTIONS());keyAdd(SHAPES())}  //keyAdd(ENERGY());


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
            keyAdd(Electrical(),true)
        }

        //=================================NEW SUPERGROUP===============================================================

        with( ANIMATE().PLANTS() ) {
            keyAdd(Life());keyAdd(Dead());keyAdd(Seed(),true)
            keyAdd(Tree());keyAdd(Root());keyAdd(Bark());keyAdd(Stick());keyAdd(Leaf(),true)
            keyAdd(Forest(),true)
            keyAdd(Flower(),true)
            keyAdd(Fruit(),true)
            keyAdd(Grass(),true)
        }

        with( ANIMATE().FRUITS() ) {
            //TREE FRUITS
            keyAdd(Apple());keyAdd(Peach()); keyAdd(Pear(),true)
            keyAdd(Avocado());keyAdd(Banana());keyAdd(Pineapple());keyAdd(Orange(),true)
            keyAdd(Strawberry());keyAdd(Grape(),true)
            keyAdd(Starfruit(), true)
        }

        with( ANIMATE().ANIMAL_TYPES() ) {
            //TODO!   ADD HORSE!!!
            keyAdd(Animal());keyAdd(Rabbit());keyAdd(Deer());keyAdd(Camel());keyAdd(Horse(), true)
            keyAdd(Wings());keyAdd(Bird());keyAdd((Eagle()),true)
            keyAdd(Fish(),true)
            keyAdd(Frog(),true)  //amphibians...
            keyAdd(Insect());keyAdd(Butterfly());keyAdd(Spider());keyAdd(Louse(),true);
            keyAdd(Worm(),true)
            keyAdd(Snake(),true)
        }

        with( ANIMATE().ANIMALS_FROM() ) {
            keyAdd(Food());keyAdd(Meat(),true)
            keyAdd(Egg());keyAdd(Feather(),true)
            keyAdd(Horn());keyAdd(Tail(),true)
            keyAdd(Blood(),true); //keyAdd(Leather());
        }

        //=================================NEW SUPERGROUP===============================================================
        //1 (x)PERSON().FACE()
        with( PERSON().FACE() ) {
            keyAdd(Eye());keyAdd(Ear()); keyAdd(Nose(),true)
            keyAdd(Mouth());keyAdd(Tongue());keyAdd(Tooth(),true)
            keyAdd(Chin(),true)
            keyAdd(HairOfHead(),true)
        }

        //2 (x)PERSON().BODY_PARTS()
        with( PERSON().BODY_PARTS() ) {
            keyAdd(Legs());keyAdd(Knee());keyAdd(Foot(), true)
            keyAdd(Back_Of_Body());keyAdd(Neck_Of_Body());keyAdd(Throat(), true)
            keyAdd(Hand());keyAdd(Fingernail(), true)
            keyAdd(Heart());keyAdd(Skin(), true)
            keyAdd(Stomach());keyAdd(Food(), true);
            keyAdd(Organ());keyAdd(Bone(), true)
            keyAdd(Intestine(), true);//keyAdd(Liver());keyAdd(Oil(), true)
        }

        //3 (x)PERSON().FAMILY()
        with( PERSON().FAMILY() ) {
            keyAdd(Baby());keyAdd(Child());keyAdd(Person(), true);
            keyAdd(Man());keyAdd(Woman() ); keyAdd(Family(), true)
            keyAdd(Protection());keyAdd(Father());keyAdd(Mother(), true)
            keyAdd(Husband());keyAdd(Wife(), true)
            keyAdd(Brother());keyAdd(Sister(), true)
        }

        //4 (x)PERSON().PETS()
        with (PERSON().PETS()) {
            keyAdd(Cat())
            keyAdd(Dog())
        }

        //5 (x)PERSON().PERSONAL_ARTIFACTS()
        with( PERSON().PERSONAL_ARTIFACTS() ) {
            keyAdd( Comb() );keyAdd( Brush ());keyAdd( Soap() );keyAdd(Toothbrush(), true) //@@@Added SOAP
            keyAdd( Bed());keyAdd( Blanket());keyAdd( Pillow(), true ); //@@@ added Pillow
            keyAdd(Hammer());keyAdd(Knife(), true)
            keyAdd(Tube(), true)
            keyAdd(Fiber());keyAdd(Thread());keyAdd(Cloth());keyAdd(Needle());keyAdd(Rope(), true)
            //@@@ human artifacts
            keyAdd(Key());keyAdd(Money(), true);
            //@@@ future Garage Items
            keyAdd(Saw());keyAdd(Shovel(), true)   //Bigger + impersonal  + todo: add Rake
        }

        //6 (x)PERSON().KITCHEN_TOOLS()
        with ( PERSON().KITCHEN_TOOLS() ) {
            keyAdd(Container());keyAdd(Pitcher());keyAdd(Dish(), true)
            keyAdd(Cup());keyAdd(Drinkingglass(), true)
            keyAdd(Fork());keyAdd(Knife());keyAdd(Spoon(), true)
            keyAdd(Pot());keyAdd(Pan(), true)
        }

        //=================================NEW SUPERGROUP===============================================================
        with(SCENES().CONSTRUCTIONS()) {
            keyAdd(Door());keyAdd(Window(), true)
            keyAdd(Construction(), true)
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
            keyAdd(Airplane(),true)
            keyAdd(BalloonHotAir(),true)
            //keyAdd(Rocket(),true)
        }

        with( SCENES().TRANSPORT_WATER() ) {
            keyAdd(Boat(), true)
            keyAdd(Kayak(),true)
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

        with(CONCEPTS().DIRECTIONS() ) {
            keyAdd(Right());keyAdd(Left(), true)
            keyAdd(Up());keyAdd(Down(), true)
            keyAdd(North());keyAdd(South());keyAdd(BLANK());keyAdd(East());keyAdd(West(), true)
        }

        with(CONCEPTS().SHAPES() ) {
            keyAdd(Shape(), true)
            keyAdd(Line());keyAdd(BLANK());keyAdd(Curve(), true)
            keyAdd(RoundShape());keyAdd(SquareShape());keyAdd(TriangleShape());keyAdd(DiamondShape(), true);
            keyAdd(Parallel());keyAdd(Perpendicular(), true);
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
            keyAdd(Inside());keyAdd(Outside(),   true)
            keyAdd(Indoors());keyAdd(Outdoors(), true)
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