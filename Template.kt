/* SOME UNTESTED INITIAL DEVELOPMENT IDEAS FOR TEMPLATES

data class TemplateRec(var symbolN, var list: Array<String>, var tenseOn: Boolean = false)

open class TemplateBase() {
    var levelN      = 0
	val symbolSlots = mutableMapOf<String, TemplateRec>()
	var forceOrderOn= false
}

class Template1() : TemplateBase() {
	init {
		levelN       = 3     //Most basic levels will NOT use the Keyboard but a "KeyPad"
		forceOrderOn = true	 //Symbols must be added in exactly the following Strict Order
		//Display these                         Accept these only
		symbolSlots["LANGUAGE.VERB.*"]        = TemplateRec(3, arrayOf("Jump", "Fly") )
		symbolSlots["ANIMATE:ANIMAL_TYPES.*"] = TemplateRec(2, arrayOf("Frog", "Cat", "Dog"))
		symbolSlots["LANGUAGE.ARTICLE.The"]   = TemplateRec(1, arrayOf("The"))
		symbolSlots["LANGUAGE.PREPOSITION.*"] = TemplateRec(4, arrayOf("Over"))
		symbolSlots["ANIMATE.PLANT.*"]        = TemplateRec(6, arrayOf("Flower"))
		symbolSlots["LANGUAGE.ARTICLE.*"]     = TemplateRec(5, arrayOf("A", "The"))   //<<<Cart first BALANCE HERE
	}
}
*****************************************************/
