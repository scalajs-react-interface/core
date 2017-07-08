package sri.core
package reactcomponentjs

import scala.scalajs.js

object ComponentJSLifeCycle {

  var willMount = false

  var willUnMount = false

  var didMount = false

  var willUpdate = false

  var didUpdate = false

  var willReceiveProps = false

  var rendered = false

  var shouldUpdate = false

  case class State(name: String)

  class Component extends ComponentJS[js.Object, State] {

    initialState(State(""))

    override def componentWillMount(): Unit = {
      willMount = true
    }

    override def componentDidMount(): Unit = {
      didMount = true
    }

    override def componentWillReceiveProps(nextProps: js.Object): Unit = {
      willReceiveProps = true
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

    override def componentWillUpdate(nextProps: js.Object,
                                     nextState: StateType): Unit = {
      willUpdate = true
    }

    override def componentDidUpdate(prevProps: js.Object,
                                    nextJSState: StateType): Unit = {
      didUpdate = true
    }

    override def componentWillUnmount(): Unit = {}

    def updateState() = {
      setState((state: State) => state.copy("newState"))
    }
  }

  def apply(key: js.UndefOr[String] = js.undefined,
            ref: js.Function1[Component, Unit] = null) =
    CreateElementJS[Component](componentConstructor[Component],
                               new js.Object {})

}
