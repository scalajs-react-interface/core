package sri.core.reactcomponents

import sri.core.{BaseTest, CreateElementNoProps, ComponentS, ReactDOM}

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class LifeCycle extends ComponentS[LifeCycle.State] {

  import LifeCycle._

  initialState(State(""))

  override def componentWillMount(): Unit = {
    willMount = true
  }

  override def componentDidMount(): Unit = {
    println(s"component did mount")
    didMount = true
  }

//  override def componentWillReceiveProps(nextProps: PropsType): Unit = {
//    willReceiveProps = true
//  }

  override def componentWillUpdate(nextProps: PropsType,
                                   nextState: StateType): Unit = {
    println(
      s"********************* nextProps : $nextProps   nextSTate : $nextState")
    willUpdate = true
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

  override def componentDidUpdate(prevProps: PropsType,
                                  prevState: StateType): Unit = {
    println(
      s"********************* prevProps : $prevProps   prevSTate : $prevState")
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

  var willMount = false

  var willUnMount = false

  var didMount = false

  var willUpdate = false

  var didUpdate = false

  var willReceiveProps = false

  var rendered = false

  var shouldUpdate = false

  case class State(name: String)

  @inline
  def apply() =
    CreateElementNoProps[LifeCycle]()
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

  test("test ComponentS life cycles") {

    val instance =
      ReactDOM.render(LifeCycle(), app)

    assert(willMount)
    assert(didMount)
    assert(rendered)
    assert(!willUpdate)
    assert(!didUpdate)
    assert(!willReceiveProps)
    assert(!shouldUpdate)
    instance.updateState()
    assert(willUpdate)
    assert(didUpdate)
    assert(!willReceiveProps)
    assert(shouldUpdate)

  }

}
