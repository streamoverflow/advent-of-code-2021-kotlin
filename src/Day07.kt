import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val crabPositions = input.single().split(',').map { it.toInt() }
        val crabsRange = crabPositions.minOf { it } .. crabPositions.maxOf { it }

        val (pos, fuel) = crabsRange
            .map { pos -> Pair(pos, crabPositions.sumOf { abs(pos - it) })}
            .minByOrNull { it.second }
            ?: throw Exception("Input is empty!")

        return fuel
    }

    fun part2(input: List<String>): Int {
        val crabPositions = input.single().split(',').map { it.toInt() }
        val crabsRange = crabPositions.minOf { it } .. crabPositions.maxOf { it }

        fun progressionSum(first : Int, last : Int) : Int =
            (1 + abs(first - last)) * (first + last) / 2

        val (pos, fuel) = crabsRange
            .map { pos -> Pair(pos, crabPositions.sumOf { progressionSum(1, abs(pos - it)) })}
            .minByOrNull { it.second }
            ?: throw Exception("Input is empty!")

        return fuel
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
