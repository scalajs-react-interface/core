package sri.core.reactcomponentp

import sri.core.{
  BaseTest,
  ComponentP,
  CreateElement,
  JSProps,
  JSState,
  ReactDOM
}

import scala.scalajs.js.UndefOr

class LifeCycle extends ComponentP[LifeCycle.Props] {

  import LifeCycle._

  override def componentDidMount(): Unit = {
    didMount = true
  }

  def render() = {
    rendered = true
    null
  }

  override def shouldComponentUpdate(nextProps: PropsType): Boolean = {
    shouldUpdate = true
    true
  }

  override def getSnapshotBeforeUpdate(prevJSProps: JSProps {
    type ScalaProps = Props
  }): UndefOr[SnapShot] = {
    snapshotBeforeUpdate = true
  }

  override def componentDidUpdate(prevJSProps: JSProps {
    type ScalaProps = Props
  }, prevJSState: JSState {
    type ScalaState = Null
  }, snapShot: UndefOr[SnapShot]): Unit = {
    didUpdate = true
  }

  override def componentWillUnmount(): Unit = {
    println(s"*********************** unmount")
  }

  def updateComponent() = {
    forceUpdate()
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
  case class Props()

  @inline
  def apply(props: Props = Props()) =
    CreateElement[LifeCycle](props)
}

class LifeCycleTest extends BaseTest {

  import LifeCycle._

  test("test ComponentP life cycles") {

    val instance =
      ReactDOM.render(LifeCycle(),
                      org.scalajs.dom.document.getElementById(APP_ID))

    expect(didMount).toBeTruthy()
    expect(rendered).toBeTruthy()
    expect(snapshotBeforeUpdate).toBeFalsy()
    expect(didUpdate).toBeFalsy()
    expect(shouldUpdate).toBeFalsy()
    instance.updateComponent()
    expect(snapshotBeforeUpdate).toBeTruthy()
    expect(didUpdate).toBeTruthy()

  }

}
