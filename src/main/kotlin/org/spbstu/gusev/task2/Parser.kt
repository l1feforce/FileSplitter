package org.spbstu.gusev.task2

import com.xenomachina.argparser.*
import java.io.IOException


class MyArgs(parser: ArgParser) {
    val outputFilesName by parser.storing("-o",
            help = "name of the output files").default("x")

    val filesTypeOfNumbering by parser.flagging("-d",
            help = "if true then files names will be like: ofile1, ofile2 etc" +
                    "else: ofileaa, ofileab, ..., ofileba, ofilebb, etc")

    val outputFilesSizeInLines by parser.storing("-l",
            help = "output files size in lines") { toInt() }.default(-1)

    val outputFilesSizeInChar by parser.storing("-c",
            help = "output files size in chars") { toInt() }.default(-1)

    val outputFilesSizeByAmount by parser.storing("-n",
            help = "output files size by amount of output files") { toInt() }.default(-1)

    val inputFileName by parser.positional("INPUT",
            help = "input file name in dir \"input/\"")

}


fun main(args: Array<String>) = mainBody {
    val argParser = ArgParser(args).parseInto(::MyArgs)
    argParser.run {
        val a = outputFilesSizeInLines != -1
        val b = outputFilesSizeInChar != -1
        val c = outputFilesSizeByAmount != -1
        if (!(!a && !b || !a && !c || !b && !c)) throw IOException("Can't enter multiple size options")
        if (a && outputFilesSizeInLines < 1 || b && outputFilesSizeInChar < 1 ||
                c && outputFilesSizeByAmount < 1) throw IOException("Can't enter size < 1")
        if (!(a || b || c)) Splitter.splitByLinesAmount(outputFilesName, filesTypeOfNumbering,
                "input/$inputFileName.txt", 100)
        when {
            a && outputFilesName != "-" -> Splitter.splitByLinesAmount(outputFilesName, filesTypeOfNumbering,
                    "input/$inputFileName.txt", outputFilesSizeInLines)
            b && outputFilesName != "-" -> Splitter.splitBySymbolsAmount(outputFilesName, filesTypeOfNumbering,
                    "input/$inputFileName.txt", outputFilesSizeInChar)
            c && outputFilesName != "-" -> Splitter.splitByFilesAmount(outputFilesName, filesTypeOfNumbering,
                    "input/$inputFileName.txt", outputFilesSizeByAmount)
            a -> Splitter.splitByLinesAmount(inputFileName, filesTypeOfNumbering,
                    "input/$inputFileName.txt", outputFilesSizeInLines)
            b -> Splitter.splitBySymbolsAmount(inputFileName, filesTypeOfNumbering,
                    "input/$inputFileName.txt", outputFilesSizeInChar)
            c -> Splitter.splitByFilesAmount(inputFileName, filesTypeOfNumbering,
                    "input/$inputFileName.txt", outputFilesSizeByAmount)
        }
    }
}
