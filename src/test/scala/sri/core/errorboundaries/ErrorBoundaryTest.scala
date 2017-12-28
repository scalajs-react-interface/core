package sri.core.errorboundaries

import org.scalajs.dom
import sri.core.{
  BaseTest,
  ComponentNotPure,
  ComponentNotPureS,
  ComponentP,
  CreateElement,
  CreateElementNoPropsWithChildren,
  ErrorInfo,
  ReactDOM,
  ReactNode,
  View
}

import scala.scalajs.js
import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
import scala.scalajs.js.JSON

class BadGuy extends ComponentP[BadGuy.Props] {
  override def render(): ReactNode = {
    if (props.giveMeMoney > 100000000)
      View(id = "bg-world")("I love this world")
    else throw new Exception("Fuck this world man.")
  }
}

object BadGuy {

  case class Props(giveMeMoney: Double)

  def apply(props: Props) = CreateElement[BadGuy](props)

}

class ErrorBoundary extends ComponentNotPureS[ErrorBoundary.State] {
  import ErrorBoundary._
  initialState(State())
  override def componentDidCatch(error: js.Error,
                                 errorInfo: ErrorInfo): Unit = {
    setState((state: State) => state.copy(hasError = true))
  }
  override def render(): ReactNode = {
    if (state.hasError) View(id = "error-boundary")("Something went wrong")
    else children
  }
}

object ErrorBoundary {

  case class State(hasError: Boolean = false)

  def apply(children: ReactNode*) =
    CreateElementNoPropsWithChildren[ErrorBoundary](
      children = children.toJSArray)
}

class ErrorBoundaryTest extends BaseTest {

  test("should render children when there is no error") {

    ReactDOM.render(
      ErrorBoundary(BadGuy(BadGuy.Props(giveMeMoney = 1000000000))),
      dom.document.getElementById(APP_ID))
    expect(dom.document.getElementById("bg-world").innerHTML)
      .toBe("I love this world")
  }

  test("should render backup error message when children throws exception ") {
    ReactDOM.render(ErrorBoundary(BadGuy(BadGuy.Props(giveMeMoney = 0.0001))),
                    dom.document.getElementById(APP_ID))
    expect(dom.document.getElementById("error-boundary").innerHTML)
      .toBe("Something went wrong")
  }
}
