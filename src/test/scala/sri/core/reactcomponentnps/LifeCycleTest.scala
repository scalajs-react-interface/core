package sri.core.reactcomponentnps

import sri.core.{
  BaseTest,
  ComponentNoPS,
  ComponentP,
  ComponentS,
  CreateElementNoProps,
  CreateElementWithChildren,
  JSProps,
  JSState,
  ReactDOM,
  ReactNode,
  View
}

import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
import scala.scalajs.js.UndefOr

class GlobalComponent extends ComponentS[GlobalState] {

  initialState(
    GlobalState(
      parentProps = PureParent
        .Props("parentProp")))

  def render() = {
    PureParent(state.parentProps)(
      RNPSChild()
    )
  }

  def dummyStateUpdate() = setState((state: GlobalState) => state)

  def newStateUpdate() =
    setState((state: GlobalState) =>
      state.copy(parentProps = state.parentProps.copy()))

}

case class GlobalState(parentProps: PureParent.Props)

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
  case class Props(name: String)
  case class State()

  var willUpdate = false

  val y: Null = null
  val x: AnyRef = y
  def apply(props: Props)(children: ReactNode*) =
    CreateElementWithChildren[PureParent](props, children = children.toJSArray)
}

class RNPSChild extends ComponentNoPS {
  import RNPSChild._
  def render() = {
    rendered = true
    View(id = "noprops and state")(null)
  }

  override def componentDidMount(): Unit = {
    didMount = true
  }

  override def shouldComponentUpdate(): Boolean = {
    shouldUpdate = true
    true
  }

  override def getSnapshotBeforeUpdate(): UndefOr[SnapShot] = {
    snapshotBeforeUpdate = true
  }

  override def componentDidUpdate(prevJSProps: JSProps {
    type ScalaProps = Null
  }, prevJSState: JSState {
    type ScalaState = Null
  }, snapShot: UndefOr[SnapShot]): Unit = {
    didUpdate = true
  }

}

object RNPSChild {

  var willUnMount = false

  var didMount = false

  var snapshotBeforeUpdate = false

  var didUpdate = false

  var rendered = false

  var shouldUpdate = false

  def apply() = CreateElementNoProps[RNPSChild]()
}

class LifeCycleTest extends BaseTest {

  test("test ComponentNPS life cycles") {

    import RNPSChild._

    val instance =
      ReactDOM.render(CreateElementNoProps[GlobalComponent](),
                      org.scalajs.dom.document.getElementById(APP_ID))

    expect(didMount).toBeTruthy()
    expect(rendered).toBeTruthy()
    expect(snapshotBeforeUpdate).toBeFalsy()
    expect(didUpdate).toBeFalsy()
    expect(shouldUpdate).toBeFalsy()
    instance.newStateUpdate()
    expect(snapshotBeforeUpdate).toBeTruthy()
    expect(didUpdate).toBeTruthy()
    expect(shouldUpdate).toBeTruthy()

  }

}
