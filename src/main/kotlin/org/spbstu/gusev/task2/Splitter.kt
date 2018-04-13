package org.spbstu.gusev.task2

import java.io.BufferedWriter
import java.io.File

object Splitter {

    private fun outputFilesNaming(typeOfOutput: Boolean, outputFilesName: String,
                                  filesAmount: Int): BufferedWriter {
        val symInUnicode = 96
        val outputFile = when (typeOfOutput) {
            true -> File("output/" + outputFilesName +
                    (filesAmount) + ".txt").bufferedWriter()
            false -> File("output/" + outputFilesName +
                    (filesAmount + symInUnicode).toChar() + ".txt").bufferedWriter()
        }
        return outputFile
    }

    fun splitByLines(outputFilesName: String, typeOfOutput: Boolean,
                     inputFilePosition: String, sizeInLines: Int) {
        val inputFile = File(inputFilePosition).readLines()
        var count = 0
        var counterForLines = 0
        for (filesAmount in 1..inputFile.size / sizeInLines + 1) {
            val outputFile = outputFilesNaming(typeOfOutput, outputFilesName, filesAmount)
            while (count < sizeInLines && counterForLines < inputFile.size) {
                outputFile.write(inputFile[counterForLines])
                outputFile.newLine()
                count++
                counterForLines++
            }
            count = 0
            outputFile.close()
        }
    }

    fun splitBySymbols(outputFilesName: String, typeOfOutput: Boolean,
                       inputFilePosition: String, sizeInSymbols: Int) {
        val inputFile = File(inputFilePosition).readText()
        var count = 0
        var counterForLines = 0
        for (filesAmount in 1..Math.ceil(inputFile.length / sizeInSymbols.toDouble()).toInt()) {
            val outputFile = outputFilesNaming(typeOfOutput, outputFilesName, filesAmount)
            while (count < sizeInSymbols && counterForLines < inputFile.length) {
                outputFile.append(inputFile[counterForLines])
                count++
                counterForLines++
            }
            count = 0
            outputFile.close()
        }
    }

    fun splitByAmount () {

    }
}