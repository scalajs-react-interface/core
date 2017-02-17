package sri.core

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName, ScalaJSDefined}
import scala.scalajs.js.{JSON, UndefOr, undefined}

@js.native
trait React extends js.Object {

  def createClass(specification: js.Object): ReactClass = js.native

  def createElement(tpe: js.Any,
                    props: js.Any,
                    children: ReactNode*): ReactElement =
    js.native

  def cloneElement(element: ReactElement,
                   props: js.Any = ???,
                   children: js.Any = ???): ReactElement = js.native

  def createFactory(tpe: js.Any): ReactComponentFactory[Any, Any] = js.native

  def render(elm: ReactElement,
             dom: js.Any,
             callback: js.Function = ???): js.Any = js.native

  def Children: ReactChildren = js.native

  val PropTypes: js.Dynamic = js.native
}

@js.native
@JSImport("react", JSImport.Namespace)
object React extends React

@js.native
trait ReactElement extends js.Object {

  type Instance <: ReactClass

  def key: UndefOr[String] = js.native

  def ref: UndefOr[js.Function] = js.native
}

@ScalaJSDefined
trait ReactClass extends js.Object {
  type Props
  type State
  var contextTypes: js.UndefOr[js.Any] = js.undefined
  var childContextTypes: js.UndefOr[js.Any] = js.undefined
}

@ScalaJSDefined
trait ReactScalaClass extends ReactClass {
  override type Props <: JSProps
  override type State <: JSState
}

@js.native
trait JSProps extends js.Object {
  type ScalaProps
  def key: UndefOr[String] = js.native

  def ref: UndefOr[js.Function] = js.native

  def sprops: ScalaProps = js.native

  def children: PropsChildren = js.native
}

object JSProps {
  @inline
  def apply[P](key: UndefOr[String] = undefined,
               ref: UndefOr[js.Function] = undefined,
               sprops: P) = {
    val dict = js.Dictionary[Any]("sprops" -> sprops)
    key.foreach(v => dict.update("key", v))
    ref.foreach(v => dict.update("ref", v))
    dict.asInstanceOf[JSProps { type ScalaProps = P }]
  }
}

@js.native
trait JSState extends js.Object {
  type ScalaState
  var sstate: ScalaState = js.native
}

object JSState {
  def apply[S](sstate: S) =
    js.Dictionary("sstate" -> sstate)
      .asInstanceOf[JSState { type ScalaState = S }]
}

@js.native
trait ReactChildren extends js.Object {

  def map(children: js.Object,
          fn: js.Function1[js.Object, _]): js.UndefOr[js.Object] = js.native

  def forEach(children: js.Object, fn: js.Function1[js.Object, _]): Unit =
    js.native

  def count(children: js.Object): Int = js.native

  def only(children: js.Object): js.Object = js.native

}

@js.native
@JSImport("react", "Component")
abstract class ReactComponentRaw[P <: js.Object, S <: js.Object]
    extends ReactClass {

  override type Props = P

  override type State = S

  def render(): ReactElement

  def props: P = js.native

  var state: S = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native

  def setState(newState: S, callback: UndefOr[() => _] = js.undefined): Unit =
    js.native

  def setState(func: js.Function2[S, P, S]): Unit = js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  def componentWillReceiveProps(nextProps: P): Unit = js.native

  def shouldComponentUpdate(nextProps: P, nextState: S): Boolean = js.native

  def componentWillUpdate(nextProps: P, nextState: S): Unit = js.native

  def componentDidUpdate(prevProps: P, prevState: S): Unit = js.native

}

@js.native
@JSImport("react", "Component")
private[core] class InternalReactComponent[P, S]
    extends js.Object
    with ReactScalaClass {

  override type Props = JSProps { type ScalaProps = P }

  override type State = JSState { type ScalaState = S }

  @JSName("props") private[core] var jsProps: Props = js.native

  @JSName("state") private[core] var jsState: State = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native

  @JSName("setState")
  def jsSetState(newState: State,
                 callback: UndefOr[() => _] = js.undefined): Unit = js.native

  @JSName("setState")
  def jsSetState(func: js.Function2[State, Props, State]): Unit = js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  @JSName("componentWillReceiveProps")
  def jsComponentWillReceiveProps(nextProps: Props): Unit = js.native

  @JSName("shouldComponentUpdate")
  def jsShouldComponentUpdate(nextProps: Props, nextState: State): Boolean =
    js.native

  @JSName("componentWillUpdate")
  def jsComponentWillUpdate(nextProps: Props, nextState: State): Unit =
    js.native

  @JSName("componentDidUpdate")
  def jsComponentDidUpdate(prevProps: Props, prevState: State): Unit =
    js.native

}

@js.native
@JSImport("react", "Component")
private[core] class InternalReactJSComponent[P <: ReactJSProps, S]
    extends js.Object
    with ReactClass {

  override type Props = P

  override type State = JSState { type ScalaState = S }

  def props: P = js.native

  @JSName("state") private[core] var jsState: State = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native

  @JSName("setState")
  def jsSetState(newState: State,
                 callback: UndefOr[() => _] = js.undefined): Unit = js.native

  @JSName("setState")
  def jsSetState(func: js.Function2[State, P, State]): Unit =
    js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  def componentWillReceiveProps(nextProps: P): Unit = js.native

  @JSName("shouldComponentUpdate")
  def jsShouldComponentUpdate(nextProps: P, nextState: State): Boolean =
    js.native

  @JSName("componentWillUpdate")
  def jsComponentWillUpdate(nextProps: P, nextState: State): Unit =
    js.native

  @JSName("componentDidUpdate")
  def jsComponentDidUpdate(prevProps: P, prevState: State): Unit =
    js.native

}

@ScalaJSDefined
abstract class ReactComponent[P, S]
    extends InternalReactComponent[P, S]
    with ReactClass {

  if (js.isUndefined(jsState) || jsState == null) {
    jsState = js.Dictionary[Any]("sstate" -> null).asInstanceOf[State]
  }

  @JSName("sProps")
  @inline
  def props: P = jsProps.sprops

  @JSName("sState")
  @inline
  def state: S = jsState.sstate

  @inline
  def propsDynamic = jsProps.asInstanceOf[js.Dynamic]

  @inline
  def children: PropsChildren = jsProps.children

  @inline
  def initialState(s: S): Unit = {
    jsState = JSState(s)
  }

  @JSName("sSetState")
  @inline
  def setState(newState: S, callback: UndefOr[() => _] = js.undefined): Unit = {
    jsSetState(JSState(newState), callback)
  }

  @JSName("sSetStateFunc")
  @inline
  def setState(func: js.Function2[S, P, S]): Unit = {
    jsSetState((s: State, p: Props) => JSState(func(s.sstate, p.sprops)))
  }

  @inline
  def getRef[T](name: String, cls: Class[T]) = {
    refs.selectDynamic(name).asInstanceOf[T]
  }

  def render(): ReactElement

  @JSName("sComponentWillUpdate")
  def componentWillUpdate(nextProps: P, nextState: S): Unit = ()

  @JSName("componentWillUpdate")
  override def jsComponentWillUpdate(nextProps: Props,
                                     nextState: State): Unit = {
    componentWillUpdate(nextProps.sprops, nextState.sstate)
  }

  @JSName("sShouldComponentUpdate")
  def shouldComponentUpdate(nextProps: P, nextState: S): Boolean = true

  @JSName("shouldComponentUpdate")
  override def jsShouldComponentUpdate(nextProps: Props,
                                       nextState: State): Boolean = {
    shouldComponentUpdate(nextProps.sprops, nextState.sstate)
  }

  @JSName("sComponentDidUpdate")
  def componentDidUpdate(prevProps: P, prevState: S): Unit = ()

  @JSName("componentDidUpdate")
  override def jsComponentDidUpdate(prevProps: Props, prevState: State): Unit = {
    componentDidUpdate(prevProps.sprops, prevState.sstate)
  }

  @JSName("sComponentWillReceiveProps")
  def componentWillReceiveProps(nextProps: P): Unit = ()

  @JSName("componentWillReceiveProps")
  override def jsComponentWillReceiveProps(nextProps: Props): Unit = {
    componentWillReceiveProps(nextProps.sprops)
  }

}

/**
  * it uses reference(eq,ne) equality for shouldComponentUpdate logic
  * @tparam P
  * @tparam S
  */
@ScalaJSDefined
abstract class ReactComponentPureRef[P <: AnyRef, S <: AnyRef]
    extends ReactComponent[P, S] {
  @JSName("sShouldComponentUpdate")
  override def shouldComponentUpdate(nextProps: P, nextState: S): Boolean = {
    (props ne nextProps) || (state ne nextState)
  }
}

/**
  * it uses reference(eq,ne) equality for shouldComponentUpdate logic
  * @tparam P
  */
@ScalaJSDefined
abstract class ReactComponentPureRefP[P <: AnyRef]
    extends ReactComponent[P, Unit] {
  @JSName("sShouldComponentUpdate")
  override def shouldComponentUpdate(nextProps: P, nextState: Unit): Boolean = {
    props ne nextProps
  }
}

/**
  * it uses reference(eq,ne) equality for shouldComponentUpdate logic
  * @tparam S
  */
@ScalaJSDefined
abstract class ReactComponentPureRefS[S <: AnyRef]
    extends ReactComponent[Unit, S] {
  @JSName("sShouldComponentUpdate")
  override def shouldComponentUpdate(nextProps: Unit, nextState: S): Boolean = {
    state ne nextState
  }
}

/**
  * it uses value(==/equals) equality for shouldComponentUpdate logic
  * @tparam P
  * @tparam S
  */
@ScalaJSDefined
abstract class ReactComponentPureValue[P, S] extends ReactComponent[P, S] {
  @JSName("sShouldComponentUpdate")
  override def shouldComponentUpdate(nextProps: P, nextState: S): Boolean = {
    (props != nextProps) || (state != nextState)
  }
}

/**
  * it uses value(==/equals) equality for shouldComponentUpdate logic
  * @tparam P
  */
@ScalaJSDefined
abstract class ReactComponentPureValueP[P] extends ReactComponent[P, Unit] {
  @JSName("sShouldComponentUpdate")
  override def shouldComponentUpdate(nextProps: P, nextState: Unit): Boolean = {
    (props != nextProps)
  }
}

/**
  * it uses value(==/equals) equality for shouldComponentUpdate logic
  * @tparam S
  */
@ScalaJSDefined
abstract class ReactComponentPureValueS[S] extends ReactComponent[Unit, S] {
  @JSName("sShouldComponentUpdate")
  override def shouldComponentUpdate(nextProps: Unit, nextState: S): Boolean = {
    (state != nextState)
  }
}

@ScalaJSDefined
abstract class ReactComponentJS[P <: ReactJSProps, S]
    extends InternalReactJSComponent[P, S]
    with ReactClass {

  if (js.isUndefined(jsState) || jsState == null) {
    jsState = js.Dictionary[Any]("sstate" -> null).asInstanceOf[State]
  }

  def render(): ReactElement

  @JSName("sState")
  @inline
  def state: S = jsState.sstate

  @inline
  def children: PropsChildren = props.children.get

  @inline
  def initialState(s: S): Unit = {
    jsState = JSState(s)
  }

  @JSName("sSetState")
  @inline
  def setState(newState: S, callback: UndefOr[() => _] = js.undefined): Unit = {
    jsSetState(JSState(newState), callback)
  }

  @JSName("sSetStateFunc")
  @inline
  def setState(func: js.Function2[S, P, S]): Unit = {
    jsSetState((s: State, p: Props) => JSState(func(s.sstate, p)))
  }

  @JSName("sComponentWillUpdate")
  def componentWillUpdate(nextProps: P, nextState: S): Unit = ()

  @JSName("componentWillUpdate")
  override def jsComponentWillUpdate(nextProps: P, nextState: State): Unit = {
    componentWillUpdate(nextProps.asInstanceOf[P], nextState.sstate)
  }

  @JSName("sShouldComponentUpdate")
  def shouldComponentUpdate(nextProps: P, nextState: S): Boolean = true

  @JSName("shouldComponentUpdate")
  override def jsShouldComponentUpdate(nextProps: P,
                                       nextState: State): Boolean = {
    shouldComponentUpdate(nextProps.asInstanceOf[P], nextState.sstate)
  }

  @JSName("sComponentDidUpdate")
  def componentDidUpdate(prevProps: P, prevState: S): Unit = ()

  @JSName("componentDidUpdate")
  override def jsComponentDidUpdate(prevProps: P, prevState: State): Unit = {
    componentDidUpdate(prevProps, prevState.sstate)
  }
}

@ScalaJSDefined
abstract class ReactJSProps extends js.Object {
  val key: js.UndefOr[String] = js.undefined
  val ref: UndefOr[js.Function] = js.undefined
  val children: js.UndefOr[PropsChildren] = undefined
}

@js.native
trait ReactComponentFactory[P, S] extends ReactComponent[P, S] {
  def apply(props: js.Any, children: ReactNode*): ReactElement =
    js.native
}

@js.native
trait ReactComponentConstructor[C <: ReactComponent[_, _]] extends js.Object

/**
  * typed version of js.concstructorOf[ C <: ReactJSComponent]
  * @tparam P
  * @tparam S
  */
@js.native
trait ReactTypedConstructor[P, S, I] extends js.Object {
  var contextTypes: js.UndefOr[js.Any] = js.native
  var childContextTypes: js.UndefOr[js.Any] = js.native
}
