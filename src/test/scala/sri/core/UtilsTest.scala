package sri.core

import sri.macros.{FunctionObjectMacro, OptDefault, OptionalParam}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait ExtraProps extends js.Object {
  var other: String
}

class UtilsTest extends BaseTest {

  @inline
  def InObject(key: OptionalParam[String] = OptDefault): js.Object = {
    val p = FunctionObjectMacro()
    p
  }
  test("shouldMergeObjects") {
    val in = InObject("inkey")
    val other = new ExtraProps {
      override var other: String = "other"
    }
    MergeJSObjects(in, other)
    assert(js.Object.keys(in).length == 2)
    assert(in.asInstanceOf[js.Dynamic].key.toString == "inkey")
    assert(in.asInstanceOf[js.Dynamic].other.toString == "other")

  }
}
