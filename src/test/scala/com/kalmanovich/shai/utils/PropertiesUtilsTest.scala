package com.kalmanovich.shai.utils

import org.scalatest.FunSuite

/**
  * Created by Shai Kalmanovich on 10/15/2017.
  */
class PropertiesUtilsTest extends FunSuite {

  test("loading properties file is successful") {
    val numOfTopWords: String = PropertiesUtils.numOfTopWords

    assert(numOfTopWords == "5")
  }

}
