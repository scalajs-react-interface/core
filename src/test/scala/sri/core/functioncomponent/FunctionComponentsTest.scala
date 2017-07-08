package sri.core
package functioncomponent

import scala.scalajs.js

class FunctionComponentsTest extends BaseTest {

  import scala.language.existentials
  test(
    "shouldRenderFunctionComponentWithProps",
    () => {
      var vref: View = null
      def storeVRef(ref: View) = vref = ref
      val HelloFunction =
        (props: String) => View(id = "functionid", ref = storeVRef _)(props)
      val instance =
        ReactDOM.render(CreateElementSF(HelloFunction, "HeloFunc"),
                        org.scalajs.dom.document.getElementById(APP_ID))
      expect(vref.props.id).toBe("functionid")
      expect(vref.children.toString).toBe("HeloFunc")
    }
  )
  test(
    "shouldRenderFunctionComponentWithNoProps",
    () => {
      var vref: View = null
      def storeVRef(ref: View) = vref = ref
      val FunctionNoProps =
        () => View(id = "NoPropsFunction", ref = storeVRef _)("ddd")
      val instance =
        ReactDOM.render(CreateElementSFNoP(FunctionNoProps),
                        org.scalajs.dom.document.getElementById(APP_ID))

      expect(vref.props.id).toBe("NoPropsFunction")
    }
  )
}
