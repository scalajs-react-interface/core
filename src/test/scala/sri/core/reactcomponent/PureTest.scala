package sri.core.reactcomponent

import sri.core.{
  BaseTest,
  CreateElement,
  CreateElementNoProps,
  CreateElementWithChildren,
  JSProps,
  JSState,
  Component,
  ComponentP,
  ComponentS,
  ReactDOM,
  ReactNode,
  View
}

import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class GlobalComponent extends ComponentS[GlobalState] {

  initialState(
    GlobalState(
      parentProps = PureParent
        .Props("parentProp", childProps = PureChild.Props("childProps"))))

  def render() = {
    PureParent(state.parentProps)(
      PureChild(state.parentProps.childProps)
    )
  }

  def dummyStateUpdate() = setState((state: GlobalState) => state)

  def newStateUpdate() =
    setState((state: GlobalState) =>
      state.copy(parentProps = state.parentProps.copy()))

  def newStateWithNewChildPropsUpdate() =
    setState(
      (state: GlobalState) =>
        state.copy(parentProps = state.parentProps.copy(
          childProps = state.parentProps.childProps.copy())))
}

case class GlobalState(parentProps: PureParent.Props)

@ScalaJSDefined
class PureParent extends ComponentP[PureParent.Props] {
  import PureParent._
  def render() = {
    View(id = props.name)(children)
  }

  override def componentWillUpdate(nextJSProps: JSProps {
    type ScalaProps = Props
  }): Unit = {
    willUpdate = true
  }

}

object PureParent {
  case class Props(name: String, childProps: PureChild.Props)
  case class State()

  var willUpdate = false

  val y: Null = null
  val x: AnyRef = y
  def apply(props: Props)(children: ReactNode*) =
    CreateElementWithChildren[PureParent](props, children = children.toJSArray)
}

@ScalaJSDefined
class PureChild extends Component[PureChild.Props, PureChild.State] {
  import PureChild._
  initialState(State("childState"))
  def render() = {
    View(id = props.name)(null)
  }

  override def componentWillUpdate(nextJSProps: JSProps {
    type ScalaProps = Props
  }, nextJSState: JSState { type ScalaState = State }): Unit = {
    willUpdate = true
  }

  def dummyStateUpdate() = setState((state: State, props: Props) => state)

  def newStateUpdate() = setState((state: State, props: Props) => state.copy())
}

object PureChild {
  var willUpdate = false
  case class Props(name: String)
  case class State(name: String)

  def apply(props: Props) = CreateElement[PureChild](props)
}

class PureTest extends BaseTest {

  before {
    PureParent.willUpdate = false
    PureChild.willUpdate = false
  }

  test("when parent didn't updated child shouldn't update") {
    val instance =
      ReactDOM.render(CreateElementNoProps[GlobalComponent](), app)
    assert(!PureParent.willUpdate)
    assert(!PureChild.willUpdate)
    instance.dummyStateUpdate()
    assert(!PureParent.willUpdate)
    assert(!PureChild.willUpdate)
  }

  test(
    "when parent state changed and child props didn't changed then parent will update child shouldn't update") {
    val instance =
      ReactDOM.render(CreateElementNoProps[GlobalComponent](), app)
    assert(!PureParent.willUpdate)
    assert(!PureChild.willUpdate)
    instance.newStateUpdate()
    assert(PureParent.willUpdate)
    assert(!PureChild.willUpdate)
  }

  test(
    "when parent state changed and child props also changed then parent will update child should also update") {
    val instance =
      ReactDOM.render(CreateElementNoProps[GlobalComponent](), app)
    assert(!PureParent.willUpdate)
    assert(!PureChild.willUpdate)
    instance.newStateWithNewChildPropsUpdate()
    assert(PureParent.willUpdate)
    assert(PureChild.willUpdate)
  }
}
