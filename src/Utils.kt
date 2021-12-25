import java.io.File

fun readInput(name: String) =
    File("src/inputs", "$name.txt").readLines()

fun CharSequence.splitIgnoreEmpty(vararg delimiters: Char): List<String> =
    this.split(*delimiters).filter { it.isNotEmpty() }
