package com.kalmanovich.shai

/**
  * Created by Shai Kalmanovich on 10/15/2017.
  */
object MainApp extends App {

  override def main(args: Array[String]) : Unit = {
    val fileName = """F:\scala\workspace\FileReverseWordCount\src\main\resources\SampleTextFile_10kb.txt"""

    val fr: FileReverse = new FileReverse
    fr.startReadingFile(fileName)
  }
}