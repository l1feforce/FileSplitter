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
        Splitter.splitByLinesAmount("file", true,
                "src/test/resources/inputfile1.txt", 10)
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

        Splitter.splitByLinesAmount("file", false,
                "src/test/resources/inputfile1.1.txt", 5)
        assertFileContent("output/fileaa.txt",
                "111111\n" +
                        "222222\n" +
                        "333333\n" +
                        "444444\n" +
                        "555555")
        assertFileContent("output/fileab.txt",
                "666666\n" +
                        "777777\n" +
                        "888888\n" +
                        "999999\n" +
                        "000000")
        assertFileContent("output/fileac.txt",
                "999999\n" +
                        "888888\n" +
                        "777777\n" +
                        "666666\n" +
                        "555555")
        assertFileContent("output/filead.txt",
                "444444\n" +
                        "333333")

        Splitter.splitByLinesAmount("filename", true,
                "src/test/resources/inputfile1.2.txt", 5)
        assertFileContent("output/filename1.txt",
                "111111\n" +
                        "222222\n" +
                        "\n" +
                        "333333\n" +
                        "444444")
        assertFileContent("output/filename2.txt",
                "\n" +
                        "555555\n" +
                        "666666\n" +
                        "777777\n")
        assertFileContent("output/filename3.txt",
                "888888\n" +
                        "999999\n" +
                        "000000\n" +
                        "999999\n" +
                        "888888")
        assertFileContent("output/filename4.txt",
                "777777\n" +
                        "\n" +
                        "666666\n" +
                        "555555\n" +
                        "444444")
        assertFileContent("output/filename5.txt",
                "333333")
        File("output/").listFiles().forEach { it.delete() }

    }

    @org.junit.Test
    fun splitBySymbols() {
        Splitter.splitBySymbolsAmount("fileForSecondTest", true,
                "src/test/resources/inputfile2.txt", 6)
        assertFileContent("output/fileForSecondTest1.txt",
                "123456")
        assertFileContent("output/fileForSecondTest2.txt",
                "123456")
        assertFileContent("output/fileForSecondTest3.txt",
                "123")

        Splitter.splitBySymbolsAmount("fileForSecondTest", false,
                "src/test/resources/inputfile2.txt", 6)
        assertFileContent("output/fileForSecondTestaa.txt",
                "123456")
        assertFileContent("output/fileForSecondTestab.txt",
                "123456")
        assertFileContent("output/fileForSecondTestac.txt",
                "123")

        Splitter.splitBySymbolsAmount("outputfile1", false,
                "src/test/resources/inputfile2.1.txt", 20)
        assertFileContent("output/outputfile1aa.txt",
                "1234567890\n" +
                        "1234567890")
        assertFileContent("output/outputfile1ab.txt",
                "1234567890\n" +
                        "1234567890")
        assertFileContent("output/outputfile1ac.txt",
                "1234567890\n" +
                        "1234567890")
        assertFileContent("output/outputfile1ad.txt",
                "1234567890")

       File("output/").listFiles().forEach { it.delete() }
    }

    @org.junit.Test
    fun splitByAmount() {
        Splitter.splitByFilesAmount("fileForThirdTest", true,
                "src/test/resources/inputfile3.txt", 5)
        assertFileContent("output/fileForThirdTest1.txt",
                "1")
        assertFileContent("output/fileForThirdTest2.txt",
                "2")
        assertFileContent("output/fileForThirdTest3.txt",
                "3")
        assertFileContent("output/fileForThirdTest4.txt",
                "")
        assertFileContent("output/fileForThirdTest5.txt",
                "")

        Splitter.splitByFilesAmount("fileForThirdTest", false,
                "src/test/resources/inputfile3.1.txt", 3)
        assertFileContent("output/fileForThirdTestaa.txt",
                "1234")
        assertFileContent("output/fileForThirdTestab.txt",
                "5678")
        assertFileContent("output/fileForThirdTestac.txt",
                "90")

        File("output/").listFiles().forEach { it.delete() }
    }
}