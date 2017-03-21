package sri.core
package functioncomponent

class FunctionComponentsTest extends BaseTest {
  import scala.language.existentials

  test("shouldRenderFunctionComponentWithProps") {
    var vref: View = null
    def storeVRef(ref: View) = vref = ref
    val HelloFunction =
      (props: String) => View(id = "functionid", ref = storeVRef _)(props)
    val instance =
      ReactDOM.render(CreateElementSF(HelloFunction, "HeloFunc"), app)
    assert(vref.props.id == "functionid")
    assert(vref.children.toString == "HeloFunc")
  }
  test("shouldRenderFunctionComponentWithNoProps") {
    var vref: View = null
    def storeVRef(ref: View) = vref = ref
    val FunctionNoProps =
      () => View(id = "NoPropsFunction", ref = storeVRef _)("ddd")
    val instance =
      ReactDOM.render(CreateElementSFNoP(FunctionNoProps), app)
    assert(vref.props.id == "NoPropsFunction")
  }
}
