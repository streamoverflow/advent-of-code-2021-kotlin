const val BOARD_SIZE = 5

fun main() {
    data class BingoResult(val score : Int, val turns : Int)

    fun scoreBingo(boardStrings : List<String>, numbersOrder : List<Int>) : BingoResult? {
        val board = boardStrings.map { line -> line.splitIgnoreEmpty(' ', ).map { it.toInt() }.toIntArray() }.toTypedArray()

        val numbersOnBoard = HashMap<Int, Triple<Int, Int, Boolean>>()
        for (c in 0 until BOARD_SIZE)
        for (r in 0 until BOARD_SIZE)
            numbersOnBoard[board[r][c]] = Triple(r, c, false)

        val unusedInRows = IntArray(BOARD_SIZE) { BOARD_SIZE }
        val unusedInCols = IntArray(BOARD_SIZE) { BOARD_SIZE }

        var turns = 0
        for (n in numbersOrder) {
            turns++

            val (r, c, _) = numbersOnBoard[n] ?: continue
            numbersOnBoard[n] = Triple(r, c, true)
            unusedInCols[c] -= 1
            unusedInRows[r] -= 1

//            if (unusedInCols[c] == 0 && unusedInRows[r] == 0)
//                throw Exception("Ambiguous bingo")

            if (unusedInCols[c] == 0 || unusedInRows[r] == 0) {
                val scoreOfUnused = numbersOnBoard.values.filter { !it.third }.sumOf { (r, c, _) -> board[r][c] }
//                val scoreOfBingo = numbersOnBoard.values.filter { (r, c, _) -> unusedInCols[c] == 0 || unusedInRows[r] == 0 }.sumOf { (r, c, _) -> board[r][c] }
                val score = n * scoreOfUnused
                return BingoResult(score, turns)
            }
        }

        return null // no bingo
    }

    fun part1(inputLines: List<String>): Int {
        val numbersOrder = inputLines.first().split(',').map { it.toInt() }
        val bingoBoards = inputLines.drop(1).chunked(BOARD_SIZE + 1)

        return bingoBoards
            .mapNotNull { scoreBingo(it.drop(1), numbersOrder) }
            .sortedBy { it.turns  }
            .first()
            .score
    }

    fun part2(inputLines: List<String>): Int {
        val numbersOrder = inputLines.first().split(',').map { it.toInt() }
        val bingoBoards = inputLines.drop(1).chunked(BOARD_SIZE + 1)

        return bingoBoards
            .mapNotNull { scoreBingo(it.drop(1), numbersOrder) }
            .sortedByDescending { it.turns  }
            .first()
            .score
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
