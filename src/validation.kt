class ValidatorException(message: String) : Exception(message)
class Validation() {
    private var rules: ArrayList<Rule> = ArrayList()

    fun addRule(rule: Rule){
        for (r in rules) {
            if (r.equals(rule)) throw ValidatorException("Duplicates were found")
        }
        rules.add(rule)
    }

    fun checkPassword(password: String) {
        for (rule in rules) {
            if (!rule.checkRule(password)) throw ValidatorException(rule.toString())

            println("Success ${rule.nameClass()}")
        }
    }

}
