package org.spbstu.gusev.task2

import java.io.BufferedWriter
import java.io.File
import java.io.IOException
import java.lang.Math.ceil

object Splitter {

    private fun outputFilesNames(typeOfNumbering: Boolean, outputFilesName: String,
                                 filesAmount: Int): BufferedWriter {
        val englishSymbolsInUnicode = 96
        val englishAlphabetSize = 26
        if (filesAmount > englishAlphabetSize * englishAlphabetSize) throw IOException("Reached the maximum number of files")
        val filesAmount2 = if (filesAmount % englishAlphabetSize == 0) 26
        else filesAmount % englishAlphabetSize
        val firstChar = (ceil(filesAmount.toDouble() / englishAlphabetSize.toDouble())
                + englishSymbolsInUnicode).toChar()
        val secondChar = (filesAmount2 + englishSymbolsInUnicode).toChar()
        return when (typeOfNumbering) {
            true -> File("output/$outputFilesName$filesAmount.txt").bufferedWriter()
            false -> File("output/$outputFilesName$firstChar$secondChar.txt").bufferedWriter()
        }
    }

    fun splitByLinesAmount(outputFilesName: String, typeOfNumbering: Boolean,
                           inputFilePosition: String, sizeInLines: Int) {
        val inputFile = File(inputFilePosition).readLines()
        var count = 0
        var linesCounter = 0
        for (filesAmount in 1..ceil(inputFile.size.toDouble() / sizeInLines.toDouble()).toInt()) {
            val outputFile = outputFilesNames(typeOfNumbering, outputFilesName, filesAmount)
            while (count < sizeInLines && linesCounter < inputFile.size) {
                outputFile.write(inputFile[linesCounter])
                outputFile.newLine()
                count++
                linesCounter++
            }
            count = 0
            outputFile.close()
        }
    }


    fun splitBySymbolsAmount(outputFilesName: String, typeOfNumbering: Boolean,
                             inputFilePosition: String, sizeInSymbols: Int) {
        val inputFile = File(inputFilePosition).readText()
        var count = 0
        var linesCounter = 0
        for (filesAmount in 1..ceil(inputFile.length / sizeInSymbols.toDouble()).toInt()) {
            val outputFile = outputFilesNames(typeOfNumbering, outputFilesName, filesAmount)
            while (count < sizeInSymbols && linesCounter < inputFile.length) {
                outputFile.append(inputFile[linesCounter])
                if (count == 9) outputFile.newLine()
                count++
                linesCounter++
            }
            count = 0
            outputFile.close()
        }
    }

    /**
     * function distributes symbols in files
     * by inserting into each file
     * 'symbolsInFile/filesAmount' rounded upwards symbols
     */
    fun splitByFilesAmount(outputFilesName: String, typeOfNumbering: Boolean,
                           inputFilePosition: String, outputFilesAmount: Int) {
        val inputFile = File(inputFilePosition).readText()
        var count = 0
        var linesCounter = 0
        if (outputFilesAmount < 1) throw IOException("Incorrect number of output files")
        for (filesAmount in 1..outputFilesAmount) {
            val outputFile = outputFilesNames(typeOfNumbering, outputFilesName, filesAmount)
            while (count < ceil(inputFile.length.toDouble() / outputFilesAmount.toDouble()) && linesCounter < inputFile.length) {
                outputFile.append(inputFile[linesCounter])
                if (count == 9) outputFile.newLine()
                count++
                linesCounter++
            }
            count = 0
            outputFile.close()
        }
    }
}