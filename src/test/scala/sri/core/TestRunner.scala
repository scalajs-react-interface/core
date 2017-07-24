package sri.core

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
