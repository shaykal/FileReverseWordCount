package com.kalmanovich.shai

/**
  * Created by Shai Kalmanovich on 10/15/2017.
  */
object MainApp extends App {

  override def main(args: Array[String]) : Unit = {
    val fileName = "SampleTextFile_1000kb.txt"
    val outputFileName = "output.txt"

    val fr: FileLogicProcessing = new FileLogicProcessing

    fr.startReadingFile(fileName, outputFileName)
  }
}