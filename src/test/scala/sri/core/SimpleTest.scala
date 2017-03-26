package sri.core

import sri.core.reactcomponent.LifeCycle

import scala.scalajs.js

class SimpleTest extends BaseTest {
  test("hello") {
    val app = js.Dynamic.global.window.document.createElement("app")
    js.Dynamic.global.window.document.body.appendChild(app)
    js.Dynamic.global.window.addEventListener = (() => {}): js.Function
    js.Dynamic.global.window.requestAnimationFrame = (() => {
      throw new Error("requestAnimationFrame is not supported in Node")
    }): js.Function
    var lref: LifeCycle = null
    def storeRef(ref: LifeCycle) = {
      println(s"huhuhuhuh got ref : ::: $ref")
      lref = ref
    }
    val instance = ReactDOM.render(LifeCycle(ref = storeRef _), app)
    println(s"hello : ${lref.updateState()}")
  }
}
