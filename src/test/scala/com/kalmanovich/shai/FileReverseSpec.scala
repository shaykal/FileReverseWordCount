package com.kalmanovich.shai

import com.kalmanovich.shai.utils.PropertiesUtils
import org.scalatest.FunSuite

/**
  * Created by Shai Kalmanovich on 10/15/2017.
  */
class FileReverseSpec extends FunSuite {

  test("putWordInMap should put 1 in case of empty key") {
    val word = "word"
    val fr: FileLogicProcessing = new FileLogicProcessing
    fr.putWordInMap(word)
    val wordCount: Int = fr.getWordsMap.getOrElse(word, 0)
    assert(wordCount == 1)
  }

  test("putWordInMap should add 1 in case of existing key") {
    val word = "word"
    val fr: FileLogicProcessing = new FileLogicProcessing
    fr.putWordInMap(word)
    val wordCount1: Int = fr.getWordsMap.getOrElse(word, 0)
    assert(wordCount1 == 1)

    fr.putWordInMap(word)
    val wordCount2: Int = fr.getWordsMap.getOrElse(word, 0)
    assert(wordCount2 == 2)
  }

  test("get5MostFrequentWords should return only top 5 words from the map") {
    lazy val numOfTopWords = PropertiesUtils.numOfTopWords.toInt

    val fr: FileLogicProcessing = new FileLogicProcessing

    for(i <- 1 to 6; j <- i to 6){
      fr.putWordInMap("word"+j)
    }

    val answer = fr.get5MostFrequentWords()

    assert(answer.size == numOfTopWords) // default is 5
    assert(answer == List("word6","word5","word4","word3","word2"))
  }

  test("removePunctuation should successfully remove . from word that ends with it") {
    val fr: FileLogicProcessing = new FileLogicProcessing
    val word = "word."
    val word1Answer = fr.removePunctuation(word)
    assert(word1Answer == "word")
  }

  test("removePunctuation should successfully remove , from word that ends with it") {
    val fr: FileLogicProcessing = new FileLogicProcessing
    val word = "word,"
    val word1Answer = fr.removePunctuation(word)
    assert(word1Answer == "word")
  }

  test("removePunctuation should successfully NOT remove . if the word doesn't end with it") {
    val fr: FileLogicProcessing = new FileLogicProcessing
    val word = "word@word.com"
    val word1Answer = fr.removePunctuation(word)
    assert(word1Answer == "word@word.com")
  }
}