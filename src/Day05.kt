import kotlin.math.min
import kotlin.math.max

fun main() {
    fun drawLine(board: Array<IntArray>, x1: Int, y1: Int, x2: Int, y2: Int) {
        for (x in min(x1, x2)..max(x1, x2))
        for (y in min(y1, y2)..max(y1, y2))
            board[x][y]++
    }

    fun drawDiagonalLine(board: Array<IntArray>, x1: Int, y1: Int, x2: Int, y2: Int) {
        val dx = if (x1 < x2) +1 else -1
        val dy = if (y1 < y2) +1 else -1

        var x = x1
        var y = y1
        while (x != x2 && y != y2) {
            board[x][y]++

            x += dx
            y += dy
        }
        board[x2][y2]++
    }

    fun part1(input: List<String>): Int {
        val lines = input.map { it.splitIgnoreEmpty(' ', '-', '>', ',').map { word -> word.toInt()}.toIntArray() }
        val maxCoord = lines.maxOf { it.maxOf { n -> n } }
        val board = Array(maxCoord + 1) { IntArray(maxCoord + 1) }

        for ((x1, y1, x2, y2) in lines) {
            if (x1 == x2 || y1 == y2)
                drawLine(board, x1, y1, x2, y2)
        }

        val overlappingPointsCount = board.sumOf { line -> line.count {it > 1} }
        return overlappingPointsCount
    }

    fun part2(input: List<String>): Int {
        val lines = input.map { it.splitIgnoreEmpty(' ', '-', '>', ',').map { word -> word.toInt()}.toIntArray() }
        val maxCoord = lines.maxOf { it.maxOf { n -> n } }
        val board = Array(maxCoord + 1) { IntArray(maxCoord + 1) }

        for ((x1, y1, x2, y2) in lines) {
            if (x1 == x2 || y1 == y2)
                drawLine(board, x1, y1, x2, y2)
            else
                drawDiagonalLine(board, x1, y1, x2, y2)
        }

        val overlappingPointsCount = board.sumOf { line -> line.count {it > 1} }
        return overlappingPointsCount
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
