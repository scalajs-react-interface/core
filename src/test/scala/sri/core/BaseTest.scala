package sri.core

import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.scalajs.js

class BaseTest extends FunSuite with BeforeAndAfter {

  js.Dynamic.global.`REACT_ELEMENT_TYPE` = REACT_ELEMENT_TYPE
}
