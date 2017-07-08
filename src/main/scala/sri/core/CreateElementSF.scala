package sri.core

import scala.scalajs.js
import scala.scalajs.js.|

object CreateElementSF {

  @inline
  def apply[C <: ReactClass, P](func: P => ReactElement { type Instance = C }, props: P, key: String | Int = null)
    : ReactElement { type Instance = C } = {
    js.Dynamic
      .literal(
        `$$typeof` = CoreGlobals.REACT_ELEMENT_TYPE,
        `type` = func,
        props = props.asInstanceOf[js.Any],
        key =
          if (key != null) "" + key
          else null,
        ref = null
      )
      .asInstanceOf[ReactElement { type Instance = C }]
  }
}
