package com.kalmanovich.shai

import com.kalmanovich.shai.utils.PropertiesUtils
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.mutable

/**
  * Created by Shai Kalmanovich on 10/15/2017.
  */
class FileReverse {

  val log: Logger = LoggerFactory.getLogger(this.getClass)

  lazy val numOfTopWords = PropertiesUtils.numOfTopWords.toInt

  private val wordCountMap: mutable.HashMap[String, Int] = scala.collection.mutable.HashMap.empty[String, Int]


  def startReadingFile(filename: String) : Unit = {
    log.info(s"Going to start reading from file $filename")
    val source = io.Source.fromFile(filename)
    val linesIterator = source.getLines
    for (line: String <- linesIterator) {
      log.debug(s"line is: $line")
      val words : Array[String] = line.split(" ")
      words.foreach(word => putWordInMap(reverseWord(word)))
    }

    val listOfTopWords = get5MostFrequentWords()
    log.info("Top words are: ")
    listOfTopWords.foreach(word => log.info(word))

    source.close()
  }

  def reverseWord(word: String) : String =
    word.reverse



  def putWordInMap(word: String) : Unit = {
    log.debug(s"Got word '$word'")
    val numberOfOccur: Int = wordCountMap.getOrElse(word, 0)
    log.debug(s"numberOfOccur is: $numberOfOccur")
    wordCountMap.put(word, numberOfOccur + 1)
  }

  def get5MostFrequentWords() : List[String] =
    wordCountMap.toList
                .sortBy(pair => -pair._2)
                .take(numOfTopWords)
                .map(pair => pair._1)


  def getWordsMap =
    wordCountMap

}
