package org.spbstu.gusev.task2

import java.io.BufferedWriter
import java.io.File
import java.io.IOException
import java.lang.Math.ceil

object Splitter {

    private fun outputFilesNaming(typeOfOutput: Boolean, outputFilesName: String,
                                  filesAmount: Int): BufferedWriter {
        val symInUnicode = 96
        return when (typeOfOutput) {
            true -> File("output/" + outputFilesName +
                    (filesAmount) + ".txt").bufferedWriter()
            false -> File("output/" + outputFilesName +
                    (filesAmount + symInUnicode).toChar() + ".txt").bufferedWriter()
        }
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
        for (filesAmount in 1..ceil(inputFile.length / sizeInSymbols.toDouble()).toInt()) {
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

    /**
     * function distributes symbols in files
     * by inserting into each file
     * 'symbolsInFile/amountOfFiles' rounded upwards symbols
     */
    fun splitByAmount(outputFilesName: String, typeOfOutput: Boolean,
                      inputFilePosition: String, sizeInFilesAmount: Int) {
        val inputFile = File(inputFilePosition).readText()
        var count = 0
        var counterForLines = 0
        if (sizeInFilesAmount < 1) throw IOException("Incorrect number of output files")
        for (filesAmount in 1..sizeInFilesAmount) {
            val outputFile = outputFilesNaming(typeOfOutput, outputFilesName, filesAmount)
            while (count < ceil(inputFile.length.toDouble()/sizeInFilesAmount.toDouble()) && counterForLines < inputFile.length) {
                outputFile.append(inputFile[counterForLines])
                count++
                counterForLines++
            }
            count = 0
            outputFile.close()
        }
    }
}