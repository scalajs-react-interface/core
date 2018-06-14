package sri.core
package reactcomponent

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSExportStatic

class LifeCycle extends Component[LifeCycle.Props, LifeCycle.State] {

  import LifeCycle._

  initialState(State(""))

  override def componentDidMount(): Unit = {
    didMount = true
  }

  def render() = {
    println(s"Rendering State : ${state}")
    rendered = true
    null
  }

  override def shouldComponentUpdate(nextProps: PropsType,
                                     nextState: StateType): Boolean = {
    shouldUpdate = true
    true
  }

  override def getSnapshotBeforeUpdate(prevJSProps: JSProps {
    type ScalaProps = Props
  }, prevJSState: JSState {
    type ScalaState = State
  }): UndefOr[SnapShot] = {
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
    setState((state: State, props: Props) => state.copy("newState"))
  }
}

object LifeCycle {

  var willUnMount = false

  var didMount = false

  var snapshotBeforeUpdate = false

  var derivedStateFromProps = false

  var didUpdate = false

  var rendered = false

  var shouldUpdate = false

  case class State(name: String, count: Int = 0)
  case class Props(count: Int = 1)

  @JSExportStatic
  def getDerivedStateFromProps(props: JSProps { type ScalaProps = Props }, state: JSState {
    type ScalaState = State
  }) = {
    derivedStateFromProps = !derivedStateFromProps
    JSState(state.scalaState.copy(count = props.scalaProps.count))
  }

  @inline
  def apply(props: Props = Props(), ref: js.Function1[LifeCycle, Unit] = null) =
    CreateElement[LifeCycle](props, ref = ref)
}

class LifeCycleTest extends BaseTest {
  import LifeCycle._

  test("test Component life cycles") {

    val instance =
      ReactDOM.render(LifeCycle(),
                      org.scalajs.dom.document.getElementById(APP_ID))

    expect(didMount).toBeTruthy()
    expect(rendered).toBeTruthy()
    expect(derivedStateFromProps).toBeTruthy()
    expect(snapshotBeforeUpdate).toBeFalsy()
    expect(didUpdate).toBeFalsy()
    expect(shouldUpdate).toBeFalsy()
    instance.updateState()
    expect(derivedStateFromProps).toBeFalsy()
    expect(snapshotBeforeUpdate).toBeTruthy()
    expect(didUpdate).toBeTruthy()
    expect(shouldUpdate).toBeTruthy()

  }

}
