package sri.core.reactcomponentp

import sri.core.{BaseTest, CreateElement, ComponentP, ReactDOM}

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
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
  before {
    willMount = false
    didMount = false
    rendered = false
    willUpdate = false
    didUpdate = false
    shouldUpdate = false
    willReceiveProps = false
  }

  test("test ComponentP life cycles") {

    val instance = ReactDOM.render(LifeCycle(), app)
    assert(willMount)
    assert(didMount)
    assert(rendered)
    assert(!willUpdate)
    assert(!didUpdate)
    assert(!willReceiveProps)
    assert(!shouldUpdate)
    instance.updateComponent()
    assert(willUpdate)
    assert(didUpdate)
    assert(!willReceiveProps)

  }

}
