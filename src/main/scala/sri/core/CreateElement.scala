package sri.core

import scala.scalajs.js
import scala.scalajs.js.{ConstructorTag, |}

object CreateElement {

  @inline
  def apply[C <: ReactScalaClass: ConstructorTag](
      props: C#Props#ScalaProps,
      key: String | Int = null,
      ref: C => Unit = null,
      children: js.Array[ReactNode] = emptyJSArray())
    : ReactElement { type Instance = C } = {
    CreateElementJS(
      js.constructorTag[C].constructor.asInstanceOf[C],
      js.Dynamic.literal(sprops = props.asInstanceOf[js.Any]),
      key = key,
      ref = ref,
      children
    )
  }

}
