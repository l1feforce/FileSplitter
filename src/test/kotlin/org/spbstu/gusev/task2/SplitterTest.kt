package org.spbstu.gusev.task2

import org.junit.Assert.*
import java.io.File

class SplitterTest {

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @org.junit.Test
    fun splitByLines() {
        Splitter.splitByLines("file", true,
                "input/inputfile.txt", 10)
        assertFileContent("output/file1.txt",
                "111111\n" +
                        "222222\n" +
                        "333333\n" +
                        "444444\n" +
                        "555555\n" +
                        "666666\n" +
                        "777777\n" +
                        "888888\n" +
                        "999999\n" +
                        "000000")
        assertFileContent("output/file2.txt",
                "999999\n" +
                        "888888\n" +
                        "777777\n" +
                        "666666\n" +
                        "555555\n" +
                        "444444\n" +
                        "333333\n" +
                        "222222\n" +
                        "111111\n" +
                        "000000")
        assertFileContent("output/file3.txt",
                "111111")

        Splitter.splitByLines("file", false,
                "input/anotherInputfile.txt", 5)

        assertFileContent("output/filea.txt",
                "111111\n" +
                        "222222\n" +
                        "333333\n" +
                        "444444\n" +
                        "555555")
        assertFileContent("output/fileb.txt",
                "666666\n" +
                        "777777\n" +
                        "888888\n" +
                        "999999\n" +
                        "000000")
        assertFileContent("output/filec.txt",
                "999999\n" +
                        "888888\n" +
                        "777777\n" +
                        "666666\n" +
                        "555555")
        assertFileContent("output/filed.txt",
                "444444\n" +
                        "333333")

    }

    @org.junit.Test
    fun splitBySymbols() {
        Splitter.splitBySymbols("fileForSecondTest", true,
                "input/inputfile1.txt", 6)

        assertFileContent("output/fileForSecondTest1.txt",
                "123456")
        assertFileContent("output/fileForSecondTest2.txt",
                "123456")
        assertFileContent("output/fileForSecondTest3.txt",
                "123")

        Splitter.splitBySymbols("fileForSecondTest", false,
                "input/inputfile1.txt", 6)

        assertFileContent("output/fileForSecondTesta.txt",
                "123456")
        assertFileContent("output/fileForSecondTestb.txt",
                "123456")
        assertFileContent("output/fileForSecondTestc.txt",
                "123")
    }
}