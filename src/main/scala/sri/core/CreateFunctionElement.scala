package sri.core

import scala.scalajs.js
import scala.scalajs.js.{ConstructorTag, |}

object CreateFunctionElement {

  @inline
  def apply[C <: ReactClass, P](func: P => ReactElement { type Instance = C }, props: P, key: String | Int = null)
    : ReactElement { type Instance = C } = {
    CreateElementJS(
      (((props: JSProps { type ScalaProps = P }) => func(props.sprops)): js.Function)
        .asInstanceOf[C],
      JSProps(sprops = props),
      key = key,
      children = js.Array()
    )
  }
}
