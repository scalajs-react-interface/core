package sri.core

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobalScope

object CoreGlobals {
  val REACT_ELEMENT_TYPE: js.Any =
    if (js.typeOf(js.Dynamic.global.`Symbol`) != "undefined" && !js
          .isUndefined(js.Dynamic.global.`Symbol`.`for`))
      js.Symbol.forKey("react.element")
    else 0xeac7;
}
