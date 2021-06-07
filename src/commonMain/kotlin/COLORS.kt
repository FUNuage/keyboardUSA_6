import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA


/*

Important:: I think the all caps on this Class Name avoids problems with all other Kotlin, or KorGE libraries?

These COLORS were chosen as "placeholder" without much attention to anything but to get a somewhat Unique Color that somewhat matches, for example,
the best colors I could find for Montessori.

They are NOT final!

However, I think this CLASS can give detailed choices to any color needed in our ENTIRE project, and accordingly should be adopted to be used
throughout the project.

GK  02/23/2021

 */





class COLORS {
    companion object {

        val superGroupColors = listOf(
            Colors["#cfb886"],
            Colors["#a1e175"],
            Colors["#ebe986"],
            Colors["#768af0"],
            Colors["#93cfdc"],
            Colors["#a68dd3"]
        )

        val COL     = listOf("Red", "Green", "Yellow", "Blue")
        val SG      = listOf("WORLD", "ANIMATE", "PERSON", "SCENES", "CONCEPTS", "LANGUAGE", "GROUPS")
        //       1"ARTICLES",   2"ADVERBS", 3"VERBS", 4"ADJECTIVES", 5"PREPOSITIONS",  6"PRONOUNS", 7"CONJUNCTIONS", 8"INTERJECTIONS", 9"EMOJIS", 10"PUNCTUATION"
        private val LNG  = listOf("ARTICLES", "ADVERBS", "VERBS", "ADJECTIVES", "PREPOSITIONS",  "PRONOUNS", "CONJUNCTIONS", "INTERJECTIONS", "EMOJIS", "PUNCTUATION") //TODO!!  Starting Point..
        val PNL     = listOf("OUTSIDE", "GROUPS", "SYMBOLS", "SENTENCE")
        //can access directly using var + index
        val appColors = listOf(
            Colors["#f01439"],  //Red
            Colors["#0cac0f"],  //Green
            Colors["#f6e46a"],  //Yellow
            Colors["#6bbcff"],  //Picked a 'baby" blue
        )
        val stroke  = listOf(
            Colors["#f1cdce"],
            Colors["#abd1b6"],
            Colors["#ebd78b"],
            Colors["#d2c6e6"],
            Colors["#93cfdc"],
            Colors["#a68dd3"])
        val fill    = listOf(
            Colors["#5aa293"],
            Colors["#478052"],
            Colors["#a78645"],
            Colors["#4e61a4"],
            Colors["#8b556d"],
            Colors["#ea2a2a"])

    //      "PREPOSITIONS",     "PRONOUNS",    "CONJUNCTIONS", "INTERJECTIONS",      "EMOJIS",        "PUNCTUATION"
        val langFill= listOf(
            Colors["#30b6c5"],  //"ARTICLES",
            Colors["#c78238"], // "ADVERBS",
            Colors["#cd1e42"],  //"VERBS",
            Colors["#435baa"],  // "ADJECTIVES"
            Colors["#67b483"],
            Colors["#724d85"],
            Colors["#eab5f7"],
            Colors["#efb14d"],
            Colors["#f6e46a"],
            Colors["#a6a7a0"]
        )
        //todo  support frame color (dark), as separate from Fill & Stroke?

        fun fill(superGroup: String):   RGBA {val ndx  = SG.indexOf(superGroup);return if (ndx in 0..5) fill[ndx]    else fill[0]}
        fun stroke(superGroup: String): RGBA {val ndx  = SG.indexOf(superGroup);return if (ndx in 0..5) stroke[ndx]  else stroke[0]}
        fun lang(groupCode: String):    RGBA {val ndx  =LNG.indexOf(groupCode); return if (ndx in 0..9) langFill[ndx]else langFill[9]}
        fun colorName(colorName: String):    RGBA {val ndx  =COL.indexOf(colorName); return if (ndx in 0..2) appColors[ndx] else langFill[9]}

        val kbFrame = Colors.WHITE //VO
        fun kbFrame(panel: String = "OUTSIDE"): RGBA {return Colors.WHITE} //GK
        fun name(colorName: String):    RGBA {val ndx  =COL.indexOf(colorName); return if (ndx in 0..2) appColors[ndx] else langFill[9]} //GK
    }
}


