package sri.core

import sri.enzyme.Enzyme

class FunctionComponentsTest extends BaseTest {
  import scala.language.existentials
  val HelloFunction = (props: String) => View(id = "functionid")(props)
  val FunctionNoProps = () => View(id = "NoPropsFunction")("ddd")
  test("shouldRenderFunctionComponentWithProps") {
    val wrapper =
      Enzyme.shallow(CreateFunctionElement(HelloFunction, "HeloFunc"))
    assert(wrapper.props().sprops.id == "functionid")
    assert(wrapper.props().children.toString == "HeloFunc")
  }
  test("shouldRenderFunctionComponentWithNoProps") {
    val wrapper =
      Enzyme.shallow(CreateFunctionElementNoProps(FunctionNoProps))
    assert(wrapper.props().sprops.id == "NoPropsFunction")
  }
}
