package sri.core.reactcomponent

import sri.core.{BaseTest, Component, CreateElement, ReactDOM, View}

import scala.scalajs.js

class RefsParent extends Component[RefsParent.Props, RefsParent.State] {
  import RefsParent._
  initialState(State())
  def render() = {
    RefsChild(ref = storeRef _)
  }

  var isUnMounted = false

  var childRef: RefsChild = null
  def storeRef(ref: RefsChild) = {
    if (!isUnMounted) {
      childRef = ref
      assert(childRef != null)
      assert(childRef.instanceField)
    } else { // when unmunted refs Callback is called with null
      assert(ref == null)

    }

  }

  override def componentWillUnmount(): Unit = {
    isUnMounted = true
  }

}

object RefsParent {
  case class Props()
  case class State()
  def apply(props: Props = Props()) = CreateElement[RefsParent](props)
}

class RefsChild extends Component[RefsChild.Props, RefsChild.State] {
  import RefsChild._
  initialState(State())
  def render() = {
    View(id = "childref")("childbuddy")
  }

  val instanceField: Boolean = true
}

object RefsChild {
  case class Props()
  case class State()
  def apply(props: Props = Props(), ref: js.Function1[RefsChild, Unit]) =
    CreateElement[RefsChild](props, ref = ref)
}

class RefsTest extends BaseTest {

  test("should be able to access instance of component with ref") {
    val instance =
      ReactDOM.render(RefsParent(),
                      org.scalajs.dom.document.getElementById(APP_ID))
    expect(instance != null).toBeTruthy()
  }

}
