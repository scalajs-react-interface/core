package sri.core

import org.scalajs.dom._

import scalajsjest.JestRunner

object TestRunner extends Constants {

  def main(args: Array[String]): Unit = {

    val app = document.createElement("div")
    app.setAttribute("id", APP_ID)
    val pl = document.createElement("div")
    pl.setAttribute("id", PORTAL_ELEMENT_ID)
    document.body.appendChild(app)
    document.body.appendChild(pl)
    JestRunner.run()
  }

}
//object
