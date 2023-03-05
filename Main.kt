import java.security.InvalidParameterException
import kotlin.random.Random

/**
 * The starting point.
 */
fun main() {
    guess()
}

/**
 * Starts a guessing game by randomly generating a number and letting you guess this number, until you won the game.
 */
fun guess() {
    val randomNumber = getRandomNumber()

    while(true) {
        val guessedNumber = getInput()
        val checkNumbers = checkNumbers(randomNumber, guessedNumber)

        println(checkNumbers)

        if (checkNumbers == "4:4") {
            println("You win!")
            return
        }
    }
}

/**
 * Gets a random 4-digit number without repeating digits.
 * @return A random 4-digit number without repeating digits.
 */
fun getRandomNumber(): String {
    while(true) {
        val n1 = Random.nextInt(0, 10)
        val n2 = Random.nextInt(0, 10)
        val n3 = Random.nextInt(0, 10)
        val n4 = Random.nextInt(0, 10)

        var ready = n1 != n2 && n1 != n3 && n1 != n4 &&
                n2 != n3 && n2 != n4 &&
                n3 != n4

        if (ready)
            return n1.toString() + n2.toString() + n3.toString() + n4.toString()
    }
}

/**
 * Checks, how close given number to given other number is.
 * @param n1 Number to check.
 * @param n2 Number to check.
 * @return "(correctly guessed digits out of order):(correctly guessed digits in order)"
 */
fun checkNumbers(n1: String, n2: String): String {
    var counterUnordered: Int = 0
    var counterOrdered: Int = 0

    if (n1.length != 4 || n2.length != 4)
        throw InvalidParameterException()

    for(i in n1.indices) {
        if (n2.contains(n1[i])) {
            counterUnordered++
        }
        if (n1[i] == n2[i])
            counterOrdered++
    }

    return "$counterUnordered:$counterOrdered"
}

/**
 * Gets the correct input from user.
 * @return A 4-digit Number the user has guessed.
 */
fun getInput(): String {
    while(true) {
        print("Guess the 4 numbers: ")
        val input = readln()

        if (!inputIsValid(input))
            println("The given Input is not valid. Please try again.")
        else
            return input
    }
}

/**
 * Checks, whether given input is valid for the guessing-game.
 * @param input The given input.
 * @return Whether given input is valid or not.
 */
fun inputIsValid(input: String): Boolean {
    val converted: Int? = input.toIntOrNull()
    return input.length == 4 && converted != null
}
