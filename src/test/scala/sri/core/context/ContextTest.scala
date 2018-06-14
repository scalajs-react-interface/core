package sri.core.context

import org.scalajs.dom
import sri.core.{
  BaseTest,
  ComponentNoPS,
  ComponentP,
  CreateElementNoProps,
  CreateElementNoPropsWithChildren,
  React,
  ReactDOM,
  ReactNode
}

import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav

class Child2 extends ComponentNoPS {

  def render() = {
    ContextRoot.themeContext.Consumer(theme => {
      assert(theme == "light")
      "Hello Theme Consumer"
    })
  }
}

object Child2 {

  def apply() = CreateElementNoProps[Child2]()
}

class Child1 extends ComponentNoPS {

  def render() = {
    ContextRoot.themeContext.Provider("light")(
      Child2()
    )
  }
}

object Child1 {

  def apply() = CreateElementNoProps[Child1]()

}

class ContextRoot extends ComponentNoPS {

  def render() = {
    ContextRoot.themeContext.Consumer(theme => {
      assert(theme == "dark")
      children
    })
  }
}

object ContextRoot {

  val themeContext = React.createContext[String]("dark")

  def apply(children: ReactNode*) =
    CreateElementNoPropsWithChildren[ContextRoot](children = children.toJSArray)
}

class ContextTest extends BaseTest {

  test("React Context") {

    ReactDOM.render(ContextRoot(Child1()), dom.document.getElementById(APP_ID))
  }
}
