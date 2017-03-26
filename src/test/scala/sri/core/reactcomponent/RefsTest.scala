package sri.core.reactcomponent

import sri.core.{BaseTest, CreateElement, Component, ReactDOM, View}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class RefsParent extends Component[RefsParent.Props, RefsParent.State] {
  import RefsParent._
  initialState(State())
  def render() = {
    println(s"Refs rendered called man hmm....")
    RefsChild(ref = storeRef _)
  }

  var childRef: RefsChild = null
  def storeRef(ref: RefsChild) = {
    println(s"Storing ref buddy : $ref")
    childRef = ref
    assert(childRef != null)
    assert(childRef.instanceField)
  }

}

object RefsParent {
  case class Props()
  case class State()
  def apply(props: Props = Props()) = CreateElement[RefsParent](props)
}

@ScalaJSDefined
class RefsChild extends Component[RefsChild.Props, RefsChild.State] {
  import RefsChild._
  initialState(State())
  def render() = {
    View(id = "childref")("childbuddy")
  }

  override def componentDidMount(): Unit = {
    println(s"Component did mount called child ref :S ")
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
    val instance = ReactDOM.render(RefsParent(), app)
    assert(instance != null)
  }
}
