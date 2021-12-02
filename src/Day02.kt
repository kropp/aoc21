import java.lang.IllegalStateException

fun main() {
    fun part1(input: List<String>): Int {
        val commands = input.map { Command(it) }
        var x = 0
        var y = 0
        for (command in commands) {
            when (command) {
                is Command.Up -> y -= command.amount
                is Command.Down -> y += command.amount
                is Command.Forward -> x += command.amount
            }
        }
        return x*y
    }

    fun part2(input: List<String>): Int {
        val commands = input.map { Command(it) }
        var x = 0
        var y = 0
        var aim = 0
        for (command in commands) {
            when (command) {
                is Command.Up -> aim -= command.amount
                is Command.Down -> aim += command.amount
                is Command.Forward -> {
                    x += command.amount
                    y += aim * command.amount
                }
            }
        }
        return x*y

    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

sealed class Command {
    abstract val amount: Int
    class Up(override val amount: Int) : Command()
    class Down(override val amount: Int) : Command()
    class Forward(override val amount: Int) : Command()
}

fun Command(str: String): Command {
    val amount = str.substringAfter(' ').toInt()
    return when (str.substringBefore(' ')) {
        "up" -> Command.Up(amount)
        "down" -> Command.Down(amount)
        "forward" -> Command.Forward(amount)
        else -> throw IllegalStateException()
    }
}
