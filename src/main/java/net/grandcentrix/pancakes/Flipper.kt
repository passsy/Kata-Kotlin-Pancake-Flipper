package net.grandcentrix.pancakes


fun calculateNumberOfFlips(row: List<Pancake>, flipperSize: Int): Result {
    // https://code.google.com/codejam/contest/3264486/dashboard
    TODO("calculate number of flips")
    return Result.IMPOSSIBLE
}

private fun List<Pancake>.allHappy(): Boolean = this.all { it.happy }

sealed class Result {
    object IMPOSSIBLE : Result()
    data class HAPPY(val flips: Int) : Result()
}


fun String.toPancakes(): List<Pancake> {
    return this.map { Pancake(it) }
}

data class Pancake(var happy: Boolean) {

    constructor(c: Char) : this(c == '+') {
        require(c in listOf('-', '+'))
    }

    fun flip() {
        happy = !happy
    }

    override fun toString(): String {
        return if (happy) "+" else "-"
    }
}