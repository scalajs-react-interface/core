package sri.core

import scala.scalajs.js
import scala.scalajs.js.{ConstructorTag, |}

object CreateElementWithChildren {

  @inline
  def apply[C <: ReactScalaClass: ConstructorTag](
      props: C#ScalaPropsType,
      key: String | Int = null,
      ref: js.Function1[C, Unit] = null,
      children: js.Array[ReactNode]): ReactElement { type Instance = C } = {
    CreateElementJS[C](
      componentConstructor[C],
      JSProps(props),
      key = key,
      ref = ref,
      children = children
    )
  }

}
