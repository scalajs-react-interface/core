package sri.core

import scala.scalajs.js
import scala.scalajs.js.{ConstructorTag, |}

object CreateElementNoPropsWithChildren {

  @inline
  def apply[C <: ReactScalaClass {
    type PropsType = JSProps { type ScalaProps = Null }
  }: ConstructorTag](
      key: String | Int = null,
      ref: js.Function1[C, Unit] = null,
      children: js.Array[ReactNode]): ReactElement { type Instance = C } = {
    CreateElementJS[C](
      componentConstructor[C],
      JSProps(scalaProps = null),
      key = key,
      ref = ref,
      children = children
    )
  }

}
