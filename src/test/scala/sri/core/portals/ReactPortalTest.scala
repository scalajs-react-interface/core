package sri.core.portals

import org.scalajs.dom._
import sri.core.{
  BaseTest,
  ComponentNoPS,
  ComponentP,
  Constants,
  CreateElement,
  CreateElementNoPropsWithChildren,
  CreateElementWithChildren,
  ReactDOM,
  ReactNode,
  View
}

import scala.scalajs.js
import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
import scala.scalajs.js.|

class Modal extends ComponentNoPS {

  import Modal._

  val ml = document.createElement("div")

  def render() = {
    ReactDOM.createPortal(children, ml)
  }

  override def componentDidMount(): Unit = {
    PORTAL_ELEMENT.appendChild(ml)
  }

  override def componentWillUnmount(): Unit = {
    PORTAL_ELEMENT.removeChild(ml)
  }
}

object Modal extends Constants {

  val PORTAL_ELEMENT = document.getElementById(PORTAL_ELEMENT_ID)

  case class Props()
  def apply(children: ReactNode*) =
    CreateElementNoPropsWithChildren[Modal](children = children.toJSArray)
}

class ReactPortalTest extends BaseTest {

  test("should render react element outside root element aka portals") {
    ReactDOM.render(Modal("Hello Portals"), document.getElementById(APP_ID))
    expect(Modal.PORTAL_ELEMENT.innerHTML).toBe("<div>Hello Portals</div>")
  }

}
