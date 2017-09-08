package net.grandcentrix.pancakes

import org.assertj.core.api.Assertions
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.File
import java.util.*


@RunWith(JUnit4::class)
class FlipperTest {

    @Test
    fun `sample1 requires 3 flips`() {
        val flips = calculateNumberOfFlips("---+-++-".toPancakes(), 3)
        Assertions.assertThat(flips).isEqualTo(Result.HAPPY(3))
    }

    @Test
    fun `all pancakes happy, no flip required`() {
        val flips = calculateNumberOfFlips("+++++".toPancakes(), 4)
        Assertions.assertThat(flips).isEqualTo(Result.HAPPY(0))
    }

    @Test
    fun `no solution possible`() {
        val flips = calculateNumberOfFlips("-+-+-".toPancakes(), 4)
        Assertions.assertThat(flips).isEqualTo(Result.IMPOSSIBLE)
    }

    @Test
    fun `implement more tests`() {
        // Do you think 3 tests are enough? In a real tournament you'll lose points when you submit wrong solutions for the data sets
        TODO("Add more tests before you try to solve the small or large data set")
    }

    //@Ignore
    @Test
    fun `small data set`() {
        solveDataSet("small.in")
    }

    //@Ignore
    @Test
    fun `large data set`() {
        solveDataSet("large.in")
    }

}

fun solveDataSet(filename: String) {
    val start = Date()
    println("starting $start")

    var inFile = File(filename)
    if (!inFile.exists()) {
        inFile = File("input/$filename")
    }
    if (!inFile.exists()) {
        throw Exception("Can't load input file ${File(filename).absoluteFile}")
    }
    println("reading from ${inFile.absoluteFile}")

    val input = inFile.readLines()
    val output = File("output/${inFile.nameWithoutExtension}.out")
    output.parentFile.mkdirs()
    val out = output.bufferedWriter()

    val numOfCases = input[0].toInt()
    for (case in 1..numOfCases) {
        val S = input[case]
        //print("$S => ")

        val result = parse(S)

        out.write("Case #$case: ${result}")
        out.newLine()
        //println("Case #$case: ${result}")
    }
    println("write in output file ${output.absoluteFile}")
    out.close()
    println("wrote ${output.readLines().size} lines")
    val finish = Date()
    val diff = finish.time - start.time
    println("\nDuration: ${diff / 1000.0}s\nfinished")

    Assertions.assertThat(File("solutions/${inFile.nameWithoutExtension}.out").readLines())
            .isEqualTo(output.readLines())
}

private fun parse(line: String): String {
    val split = line.split(" ")
    val flipperSize = split[1].toInt()
    val row = split[0].toPancakes()

    val result: Result = calculateNumberOfFlips(row, flipperSize)

    return when (result) {
        is Result.IMPOSSIBLE -> "IMPOSSIBLE"
        is Result.HAPPY -> "${result.flips}"
    }
}