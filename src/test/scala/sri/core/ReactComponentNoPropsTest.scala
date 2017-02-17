package sri.core

import sri.enzyme.Enzyme

import scala.scalajs.js.annotation.ScalaJSDefined

case class NoPropsState(name: String = "noprops state")

@ScalaJSDefined
class NoPropsComponent extends ReactComponent[Unit, NoPropsState] {
  initialState(NoPropsState())
  def render() = View("ReactComponentNoProps")("testit")
}

class ReactComponentNoPropsTest extends BaseTest {

  test("shouldRenderNoPropsComponent") {
    val wrapper = Enzyme.shallow(CreateElement[NoPropsComponent](props = ()))
    assert(wrapper.state().sstate.name == "noprops state")
  }
}
