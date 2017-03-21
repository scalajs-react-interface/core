package sri.core

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSuite}

import scala.scalajs.js

class BaseTest extends FunSuite with BeforeAndAfter with BeforeAndAfterAll {

  def getRenderedOutput(element: ReactElement) = {
    val shallowRendere = getShallowRenderer(element)
    shallowRendere.getRenderOutput()
  }

  def getShallowRenderer(element: ReactElement) = {
    val shallowRendere = ReactTestUtils.createRenderer()
    shallowRendere.render(element)
    shallowRendere
  }

  def getMountedInstance[T <: ReactClass](element: ReactElement): T = {
    getShallowRenderer(element).getMountedInstance[T]()
  }

  var domRegister: js.Function0[_] = null

  var app: js.Any = null
  override protected def beforeAll(): Unit = {
    js.Dynamic.global.`REACT_ELEMENT_TYPE` = REACT_ELEMENT_TYPE
    domRegister = JSDOMGlobal()
    val listenr: js.Function0[_] = () => {}
    js.Dynamic.global.window.addEventListener = listenr
    val raf: js.Function0[_] = () => {
      throw new Error("requestAnimationFrame is not supported in Node")
    }
    app = js.Dynamic.global.window.document.createElement("app")
    js.Dynamic.global.window.document.body.appendChild(app)
  }

  override protected def afterAll(): Unit = {
    domRegister() // cleanup
  }
}
