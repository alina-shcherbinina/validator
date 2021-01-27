fun main(args: Array<String>) {
    println("====TESTING AREA====")
    var t: Test = Test()

    t.testLengthRule()
    t.testLowercaseRule()
    t.testUppercaseRule()
    t.testNumbersRule()
    t.testCommonPasswordsRule()
    t.testEntropyRule()

    println("====TESTING AREA====")

    println("------main------")

    val password = "$\$VampiresAreReallyCool1--!"
    val validator = Validation()

    println("password: $password")

    validator.addRule(LengthRule(5))
    validator.addRule(LowercaseRule())
    validator.addRule(UppercaseRule())
    validator.addRule(NumbersRule())
    validator.addRule(SpecialCharactersRule())
    validator.addRule(CommonPasswordsRule("src/pswd-dict.txt"))
    validator.addRule(EntropyRule())

    validator.checkPassword(password)

}

