package sri.core

import scala.scalajs.js
import scala.scalajs.js.|

object CreateFunctionElementNoProps {

  @inline
  def apply[C <: ReactClass](func: () => ReactElement { type Instance = C }, key: String | Int = null)
    : ReactElement { type Instance = C } = {
    CreateElementJS(
      (((props: JSProps { type ScalaProps = Unit }) => func()): js.Function)
        .asInstanceOf[C],
      js.Dynamic.literal(),
      key = key,
      children = js.Array()
    )
  }
}
