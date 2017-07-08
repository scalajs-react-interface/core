package sri.core

import org.scalajs.dom
import sri.core.functioncomponent.FunctionComponentsTest
import sri.core.reactcomponent.{LifeCycleTest, PureTest}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobalScope
import scalajsjest.JestRunner

object TestRunner {

  def main(args: Array[String]): Unit = {
    val APP_ID = "app"
    val app = org.scalajs.dom.document.createElement("div")
    app.setAttribute("id", APP_ID)
    org.scalajs.dom.document.body.appendChild(app)
    JestRunner.run()
  }

}
//object
