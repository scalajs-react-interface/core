package sri.core
package reactcomponentjs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

class ComponentJSLifeCycle
    extends ComponentJS[js.Object, ComponentJSLifeCycle.State] {
  import ComponentJSLifeCycle._
  initialState(State(""))

  override def componentDidMount(): Unit = {
    didMount = true
  }

  def render() = {
    rendered = true
    null
  }

  override def shouldComponentUpdate(nextProps: js.Object,
                                     nextState: StateType): Boolean = {
    shouldUpdate = true
    true
  }

  override def getSnapshotBeforeUpdate(prevProps: js.Object,
                                       prevJSState: JSState {
                                         type ScalaState = State
                                       }): js.UndefOr[SnapShot] = {
    snapshotBeforeUpdate = true
  }

  override def componentDidUpdate(prevProps: js.Object,
                                  prevJSState: StateType,
                                  snapShot: js.UndefOr[SnapShot]): Unit = {
    didUpdate = true
  }

  override def componentWillUnmount(): Unit = {}

  def updateState() = {
    setState((state: State) => state.copy("newState"))
  }
}
object ComponentJSLifeCycle {

  var willUnMount = false

  var didMount = false

  var snapshotBeforeUpdate = false

  var derivedStateFromProps = false

  var didUpdate = false

  var rendered = false

  var shouldUpdate = false

  case class State(name: String)

  @JSExportStatic
  def getDerivedStateFromProps(props: js.Object, state: JSState {
    type ScalaState = State
  }) = {
    derivedStateFromProps = !derivedStateFromProps
    JSState(state.scalaState.copy())
  }

  def apply(key: js.UndefOr[String] = js.undefined,
            ref: js.Function1[ComponentJSLifeCycle, Unit] = null) =
    CreateElementJS[ComponentJSLifeCycle](
      componentConstructor[ComponentJSLifeCycle],
      new js.Object {})

}
