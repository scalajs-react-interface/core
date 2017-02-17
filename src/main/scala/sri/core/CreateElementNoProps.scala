package sri.core

import scala.scalajs.js
import scala.scalajs.js.{ConstructorTag, |}

object CreateElementNoProps {

  @inline
  def apply[C <: ReactScalaClass { type Props = Unit }: ConstructorTag](
      key: String | Int = null,
      ref: C => Unit = null,
      children: js.Array[ReactNode] = emptyJSArray())
    : ReactElement { type Instance = C } = {
    CreateElementJS(
      js.constructorTag[C].constructor.asInstanceOf[C],
      js.Dynamic.literal(sprops = ()),
      key = key,
      ref = ref,
      children
    )
  }

}
