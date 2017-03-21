package sri.core

import scala.scalajs.js
import scala.scalajs.js.|

object CreateElementSFNoP {

  @inline
  def apply[C <: ReactClass](func: () => ReactElement { type Instance = C }, key: String | Int = null)
    : ReactElement { type Instance = C } = {
    js.Dynamic
      .literal(
        `$$typeof` = js.Dynamic.global
          .selectDynamic("REACT_ELEMENT_TYPE"),
        `type` = func,
        props = js.Dynamic.literal(),
        key =
          if (key != null) "" + key
          else null,
        ref = null
      )
      .asInstanceOf[ReactElement { type Instance = C }]
  }
}
