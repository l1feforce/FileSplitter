package org.spbstu.gusev.task2

import com.xenomachina.argparser.*
import java.io.IOException


class MyArgs(parser: ArgParser) {
    val filesName by parser.storing("-o",
            help = "name of the files").default("x")

    val filesNumbering by parser.flagging("-d",
            help = "if true then files will be like: ofile1, ofile2 etc" +
                    "else: ofilea, ofileb, etc")


    val filesOutputSizeInLines by parser.storing("-l",
            help = "output files size in lines") { toInt() }.default(-1)

    val filesOutputSizeInChar by parser.storing("-c",
            help = "output files size in chars") { toInt() }.default(-1)

    val filesOutputSizeByAmount by parser.storing("-n",
            help = "output files size by amount of output files") { toInt() }.default(-1)

    val filePosition by parser.positional("INPUT",
            help = "input file position")

}


fun main(args: Array<String>) = mainBody {
    val argParser = ArgParser(args).parseInto(::MyArgs)
    argParser.run {
         val a = filesOutputSizeInLines != -1
         val b = filesOutputSizeInChar != -1
         val c = filesOutputSizeByAmount != -1
         //in this case it's the simplest way to throw exception when more then one argument 'true'
         if (!(!a && !b || !a && !c || !b && !c)) throw IOException("Can't enter multiple size options")
         if (!(a || b || c)) Splitter.splitByLines(filesName, filesNumbering, filePosition, 100)
        when {
            a -> Splitter.splitByLines(filesName, filesNumbering, filePosition, filesOutputSizeInLines)
            b -> Splitter.splitBySymbols(filesName, filesNumbering, filePosition, filesOutputSizeInChar)
            c -> Splitter.splitByAmount(filesName, filesNumbering, filePosition, filesOutputSizeByAmount)
        }
    }
}
