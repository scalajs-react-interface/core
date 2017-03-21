package sri.core

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("jsdom-global", JSImport.Default)
object JSDOMGlobal extends js.Object {
  def apply(): js.Function0[_] = js.native
}

@js.native
@JSImport("./rafPolyFill.js", JSImport.Namespace)
object RAFPolyFill extends js.Object
