package com.kalmanovich.shai.utils

import com.typesafe.config.ConfigFactory

import scala.util.Try

/**
  * Created by Shai Kalmanovich on 10/15/2017.
  */
object PropertiesUtils {

  lazy val numOfTopWords: String = load(Consts.NUM_OF_TOP_WORDS)

  /**
    * <i>load</i> - This method loads the properties from the applcation.conf file.
    * It can be controlled by VM property e.g. -Dconfig.file=src\test\resources\application-test.conf
    * @return - The string of the value of the property name.
    */
  def load(entry: String): String = {
    Try(ConfigFactory.load.getString(entry)).getOrElse(Consts.EMPTY_STRING)
  }
}
