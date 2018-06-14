package sri.core.reactcomponents

import sri.core.{
  BaseTest,
  ComponentS,
  CreateElementNoProps,
  JSProps,
  JSState,
  ReactDOM
}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

class LifeCycle extends ComponentS[LifeCycle.State] {

  import LifeCycle._

  initialState(State(""))

  override def componentDidMount(): Unit = {
    didMount = true
  }

  def render() = {
    rendered = true
    null
  }

  override def shouldComponentUpdate(nextProps: PropsType,
                                     nextState: StateType): Boolean = {
    shouldUpdate = true
    true
  }

  override def getSnapshotBeforeUpdate(
      prevJSProps: PropsType,
      prevJSState: StateType): js.UndefOr[SnapShot] = {
    snapshotBeforeUpdate = true
  }
  override def componentDidUpdate(prevProps: PropsType,
                                  prevState: StateType,
                                  snapshot: js.UndefOr[SnapShot]): Unit = {
    didUpdate = true
  }

  override def componentWillUnmount(): Unit = {
    println(s"*********************** unmount")
  }

  def updateState() = {
    setState((state: State) => state.copy("newState"))
  }
}

object LifeCycle {

  var willUnMount = false

  var didMount = false

  var snapshotBeforeUpdate = false

  var didUpdate = false

  var rendered = false

  var shouldUpdate = false

  case class State(name: String)

  @inline
  def apply() =
    CreateElementNoProps[LifeCycle]()
}

class LifeCycleTest extends BaseTest {
  import LifeCycle._

  test("test ComponentS life cycles") {

    val instance =
      ReactDOM.render(LifeCycle(),
                      org.scalajs.dom.document.getElementById(APP_ID))

    expect(didMount).toBeTruthy()
    expect(rendered).toBeTruthy()
    expect(snapshotBeforeUpdate).toBeFalsy()
    expect(didUpdate).toBeFalsy()
    expect(shouldUpdate).toBeFalsy()
    instance.updateState()
    expect(snapshotBeforeUpdate).toBeTruthy()
    expect(didUpdate).toBeTruthy()
    expect(shouldUpdate).toBeTruthy()

  }

}
