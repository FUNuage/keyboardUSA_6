import com.soywiz.korio.util.isDigit
import kotlin.math.max

class ClassKeys(var svgFileName: String )  {
    var isStopper = false;var hasaNumber= false
    var sGn =  0;var gCn =  0;var cLn = -1;
    var supergroup = "";var groupCode  = "";var className  = ""

    fun kbCode()      : String =  if (supergroup != "GROUPS") "${supergroup[0]}${gCn}" else "${groupCode[0]}${cLn}"
    fun outComplete() : String = "${supergroup}.${groupCode}.${className}  ($sGn, $gCn, $cLn) -> code: ${kbCode()}"
    fun keysSimple()  : String = "${supergroup}.${groupCode}.${className}"
    fun keysClasses() : String = "${supergroup}().${groupCode}().${className}()"

    //Overloading params
    fun match(sG: String) = (sG == supergroup || sG == "*")
    fun match(sG: String, gC: String) = ((sG == supergroup && gC == groupCode) || sG == "*")
    fun match(sG: String, gC: String, cN: String) = ((sG == supergroup && gC == groupCode && cN == className) || sG == "*")

    init {
        //   /0_GROUPS/1_WORLD/1_GEOLOGY.svg
        //   /6_LANGUAGE/VERBS/wash,bathe,bath-(to).svg
        var path = svgFileName.replace(".svg", "").trim()
        if (path[0] == '/') path = path.substring(1 until path.length)
        val pathTokAr = path.split("/")
        //println("pathTokAr = $pathTokAr, pathTokAr.size = ${pathTokAr.size}")

        if (pathTokAr.size == 3)  {
            for ((ndx, path) in pathTokAr.withIndex()) {
                val fQu = path.indexOf("_", 0)
                val fQd = path.indexOf(".", 0)
                var fQ  = max(fQd, fQu);var lQ  = path.length

                when (ndx) {
                    0 -> {sGn = path.substring(0, fQu).toInt();supergroup= path.substring(fQu+1, lQ)}
                    1 -> {gCn = path.substring(0, fQu).toInt();groupCode = path.substring(fQu+1, lQ)}
                    2 -> {
                        var newPath   = path.toLowerCase()
                        val pathStrAr = path.split(",")
                        if (pathStrAr.size > 1) newPath = pathStrAr[0]
                        newPath = newPath.capitalize()

                        if (fQ in 1..4 && newPath[0].isDigit()) { //TODO: Currently NOT all numbered.  If numbered then '_' for row position, '.' as breaker to new Row...
                            hasaNumber= true;isStopper = (fQ == fQd)
                            cLn        = newPath.substring(0, fQ).toInt()
                            className  = newPath.substring(fQ, newPath.length)
                        } else {className  = newPath.substring(0, newPath.length)}

                        //TODO( ) Must Check for Duplicates, and generate a tie breaker somehow, for example "you_1", "you_2" or?
                        //TODO( ) SEN_DNhes are ILLEGAL CHARACTERS AND CANNOT BE IN THE CLASS NAME.
                        className  = className.replace("_", "").replace("(", "").replace(")", "")
                        //TODO Some kind of regex to handle ANY digit!
                        className  = className.replace("1", "").replace("2", "")
                        className  = if (supergroup == "GROUPS")  className.toUpperCase() else className.capitalize()
                        //println("\t${outComplete()}")
                    }
                }
            }
        }
    }
}//END_OF_CLASS( ClassKeys )}