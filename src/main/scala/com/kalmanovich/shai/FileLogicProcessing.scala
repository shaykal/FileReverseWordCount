package com.kalmanovich.shai

import com.kalmanovich.shai.utils.PropertiesUtils
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.mutable
import scala.io.BufferedSource

/**
  * Created by Shai Kalmanovich on 10/15/2017.
  */
class FileLogicProcessing {

  val log: Logger = LoggerFactory.getLogger(this.getClass)

  lazy val numOfTopWords: Int = PropertiesUtils.numOfTopWords.toInt

  // This variable will hold map from a word to it counter.
  private val wordCountMap: mutable.HashMap[String, Int] = scala.collection.mutable.HashMap.empty[String, Int]


  /**
    * <i>startReadingFile</i> - This is the main function that runs the logic of the assignment.
    * @param fileName - The file name of the input file to process.
    */
  def startReadingFile(fileName: String) : Unit = {
    log.info(s"Going to start reading from file $fileName")
    val source: BufferedSource = io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(fileName), "UTF-8")
    val linesIterator = source.getLines
    for (line: String <- linesIterator) {
      log.debug(s"line is: $line")
      val words : Array[String] = line.split(" ")
      words.map(word => removePunctuation(word))
        .foreach(word => putWordInMap(reverseWord(word)))
    }

    val listOfTopWords = get5MostFrequentWords()
    log.info("Top words are: ")
    listOfTopWords.foreach(word => log.info(word))

    source.close()
  }

  /**
    * <i>removePunctuation</i> - This method removes the punctuation marks from the word
    * @param word - A single word as input.
    * @return - The word without punctuation marks. e.g. word. -> word etc.
    */
  def removePunctuation(word : String) : String = {
    val trimmedWord: String = word.trim
    val finalWord = if(!trimmedWord.isEmpty && (trimmedWord.charAt(trimmedWord.length-1).equals('.') || trimmedWord.charAt(trimmedWord.length-1).equals(',')) ) {
      trimmedWord.substring(0, trimmedWord.length - 1)
    } else {
      trimmedWord
    }

    finalWord
  }


  def reverseWord(word: String) : String =
    word.reverse


  /**
    * <i>putWordInMap</i> - This function puts a word in the map of words. If the word doesn't yet exist in the map, it puts it </br>
    * with the number 1 as the word count. If it already exists, it increments the counter by one of that word.
    * @param word - A single word to put in the map.
    */
  def putWordInMap(word: String) : Unit = {
    log.debug(s"Got word '$word'")
    val numberOfOccur: Int = wordCountMap.getOrElse(word, 0)
    log.debug(s"numberOfOccur is: $numberOfOccur")
    wordCountMap.put(word, numberOfOccur + 1)
  }


  /**
    * <i>get5MostFrequentWords</i> - This function returns the top 5 words from the map. (words with the highest word count)
    * @return - A list of words sorted by their counter in descending order.
    */
  def get5MostFrequentWords() : List[String] =
    wordCountMap.toList
                .sortBy(pair => -pair._2)
                .take(numOfTopWords)
                .map(pair => pair._1)


  /**
    * <i>getWordsMap</i> - This function returns the map of words.
    * @return - The map of words.
    */
  def getWordsMap: mutable.HashMap[String, Int] =
    wordCountMap

}