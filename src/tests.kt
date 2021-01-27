class Test {
    fun testEntropyRule() {

        val validator = Validation()
        validator.addRule(EntropyRule())

        //validator.checkPassword("aaaaaaaa")
        validator.checkPassword("!!--CoolVa,pires!--")

    }

    fun testCommonPasswordsRule() {

        val validator = Validation()
        validator.addRule(CommonPasswordsRule("src/pswd-dict.txt"))

        //validator.checkPassword("aaaaaaaa")
        validator.checkPassword("srthtfg!1-")

    }

    fun testSpecialCharactersRule() {

        val validator = Validation()
        validator.addRule(SpecialCharactersRule())

        //validator.checkPassword("aaaaaaaa")
        validator.checkPassword("!----!as")

    }

    fun testNumbersRule() {

        val validator = Validation()
        validator.addRule(NumbersRule())

        //validator.checkPassword("aaaaaaaa")
        validator.checkPassword("123woo123")

    }

    fun testUppercaseRule() {

        val validator = Validation()
        validator.addRule(UppercaseRule())

        //validator.checkPassword("aaaaaaaa")
        validator.checkPassword("AAAA")

    }

    fun testLowercaseRule() {

        val validator = Validation()
        validator.addRule(LowercaseRule())

        validator.checkPassword("aaa")
        //validator.checkPassword("AAAA")

    }

    fun testLengthRule() {

        val validator = Validation()
        validator.addRule(LengthRule(10))

        validator.checkPassword("12345678910")
        //alidator.checkPassword("1")

    }
}