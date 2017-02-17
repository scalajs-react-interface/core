package sri.core

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}

object ReactComponentJSLifeCycle {

  var willMount = false

  var willUnMount = false

  var didMount = false

  var willUpdate = false

  var didUpdate = false

  var willReceiveProps = false

  var rendered = false

  var shouldUpdate = false

  @ScalaJSDefined
  class Component extends ReactComponentJS[ReactJSProps, String] {

    initialState("")

    override def componentWillMount(): Unit = {
      willMount = true
    }

    override def componentDidMount(): Unit = {
      println(s"component did mount")
      didMount = true
    }

    override def componentWillReceiveProps(nextProps: ReactJSProps): Unit = {
      willReceiveProps = true
    }

    @JSName("sComponentWillUpdate")
    override def componentWillUpdate(nextProps: ReactJSProps,
                                     nextState: String): Unit = {
      willUpdate = true
    }

    def render() = {
      rendered = true
      null
    }

    @JSName("sShouldComponentUpdate")
    override def shouldComponentUpdate(nextProps: ReactJSProps,
                                       nextState: String): Boolean = {
      shouldUpdate = true
      true
    }

    @JSName("sComponentDidUpdate")
    override def componentDidUpdate(prevProps: ReactJSProps,
                                    prevState: String): Unit = {
      didUpdate = true
    }

    override def componentWillUnmount(): Unit = {
      println(s"*********************** unmount")
    }

    def updateState() = {
      setState("newState")
    }
  }

  def apply(key: js.UndefOr[String] = js.undefined,
            ref: js.Function1[Component, Unit] = null) =
    CreateElementJS(js.constructorOf[Component].asInstanceOf[Component], null)

}
