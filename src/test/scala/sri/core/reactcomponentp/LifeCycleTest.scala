package sri.core.reactcomponentp

import sri.core.{BaseTest, ComponentP, CreateElement, ReactDOM}

class LifeCycle extends ComponentP[LifeCycle.Props] {

  import LifeCycle._

  override def componentWillMount(): Unit = {
    willMount = true
  }

  override def componentDidMount(): Unit = {
    didMount = true
  }

  override def componentWillReceiveProps(nextProps: PropsType): Unit = {
    willReceiveProps = true
  }

  override def componentWillUpdate(nextProps: PropsType): Unit = {
    willUpdate = true
  }

  def render() = {
    rendered = true
    null
  }

  override def shouldComponentUpdate(nextProps: PropsType): Boolean = {
    shouldUpdate = true
    true
  }

  override def componentDidUpdate(prevProps: PropsType): Unit = {
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

  var willMount = false

  var willUnMount = false

  var didMount = false

  var willUpdate = false

  var didUpdate = false

  var willReceiveProps = false

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
  willMount = false
  didMount = false
  rendered = false
  willUpdate = false
  didUpdate = false
  shouldUpdate = false
  willReceiveProps = false

  test("test ComponentP life cycles") {

    val instance =
      ReactDOM.render(LifeCycle(),
                      org.scalajs.dom.document.getElementById(APP_ID))

    expect(willMount).toBeTruthy()
    expect(didMount).toBeTruthy()
    expect(rendered).toBeTruthy()
    expect(willUpdate).toBeFalsy()
    expect(didUpdate).toBeFalsy()
    expect(willReceiveProps).toBeFalsy()
    expect(shouldUpdate).toBeFalsy()
    instance.updateComponent()
    expect(willUpdate).toBeTruthy()
    expect(didUpdate).toBeTruthy()
    expect(willReceiveProps).toBeFalsy()

  }

}
