import java.io.File
import java.util.HashMap
import kotlin.math.log2

abstract class Rule(){

    abstract fun checkRule(password: String): Boolean

    override fun toString(): String {
        return Rule::class.java.name + " error message"
    }

    open fun nameClass(): String {
        return Rule::class.java.name
    }

    fun equals(other: Rule): Boolean {
        if (this.nameClass() === other.nameClass()) return true
        return false
    }
}


class LengthRule(private val len: Int) : Rule() {

    override fun checkRule(password: String): Boolean {
        if (password.length < len) return false
        return true
    }

    override fun toString(): String {
        return LengthRule::class.java.name + " password should be more than $len characters \n"
    }

    override fun nameClass(): String {
        return LengthRule::class.java.name
    }
}


class UppercaseRule() : Rule() {
    override fun checkRule(password: String): Boolean {
        for (symbol: Char in password) {
            if (symbol in 'A'..'Z') return true
        }
        return false
    }

    override fun toString(): String {
        return UppercaseRule::class.java.name + " password should include lowercase \n"
    }

    override fun nameClass(): String {
        return UppercaseRule::class.java.name
    }
}

class LowercaseRule() : Rule() {
    override fun checkRule(password: String): Boolean {
        for (symbol: Char in password) {
            if (symbol in 'a'..'z') return true
        }
        return false
    }

    override fun toString(): String {
        return LowercaseRule::class.java.name + " password should include uppercase \n"
    }

    override fun nameClass(): String {
        return LowercaseRule::class.java.name
    }
}

class NumbersRule() : Rule() {
    override fun checkRule(password: String): Boolean {
        for (symbol: Char in password) {
            if (symbol in '0'..'9') return true
        }
        return false
    }

    override fun toString(): String {
        return NumbersRule::class.java.name + " password should include numbers \n"
    }

    override fun nameClass(): String {
        return NumbersRule::class.java.name
    }
}

class SpecialCharactersRule() : Rule() {

    var specialCharacters: Array<Char> = arrayOf('-', '_', '.', '#', '@', '!', '^', '*', '+')

    override fun checkRule(password: String): Boolean {
        for (symbol: Char in password) {
            if (symbol in specialCharacters) return true
        }
        return false
    }

    override fun toString(): String {
        return SpecialCharactersRule::class.java.name +
                " password should include special characters '-', '_', '.', '#', '@', '!', '^', '*', '+' \n"
    }

    override fun nameClass(): String {
        return SpecialCharactersRule::class.java.name
    }
}


class CommonPasswordsRule(private val fileName: String): Rule() {
    val words = File(fileName).readText().split("\n")

    override fun checkRule(password: String): Boolean {
        for (word in words) {
            if (password == word) return false
        }
        return true
    }

    override fun toString(): String {
        return CommonPasswordsRule::class.java.name + " password should be stronger \n"
    }

    override fun nameClass(): String {
        return CommonPasswordsRule::class.java.name
    }
}


class EntropyRule(): Rule() {

    override fun checkRule(password: String): Boolean {
        val list = password.chunked(1).toList()

        val frequencyMap: MutableMap<String, Int> = HashMap()

        for (s in list) {
            var count = frequencyMap[s]
            if (count == null) count = 0
            frequencyMap[s] = count + 1
        }

        var H: Double = 0.0
        var N: Int = frequencyMap.size

        for (f in frequencyMap) {
            H += (f.value.toDouble() / N) * log2((f.value.toDouble() / N))
        }

        H *= (-1)

        if (H < 3) return false

        return true

    }

    override fun toString(): String {
        return EntropyRule::class.java.name + " password should have high entropy \n"
    }

    override fun nameClass(): String {
        return EntropyRule::class.java.name
    }
}
