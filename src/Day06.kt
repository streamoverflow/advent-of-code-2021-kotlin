fun main() {
    fun countFishes(fishStates: List<Int>, lastDayNum: Int): Long {
        val birthDays = LongArray(lastDayNum + 10)

        for (state in fishStates)
            birthDays[state + 1]++

        var fishCount = fishStates.count().toLong()
        for (day in 0..lastDayNum) {
            fishCount += birthDays[day]
            birthDays[day + 7] += birthDays[day]
            birthDays[day + 7 + 2] += birthDays[day]
        }

        return fishCount
    }

    fun part1(input: List<String>): Long {
        val fishStates = input.single().split(',').map { it.toInt() }
        return countFishes(fishStates, 80)
    }

    fun part2(input: List<String>): Long {
        val fishStates = input.single().split(',').map { it.toInt() }
        return countFishes(fishStates, 256)
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539L)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
