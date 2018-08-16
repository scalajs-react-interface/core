package sri.core

import scala.scalajs.js
import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
@js.native
@JSImport("react", "Fragment")
object JSFragment extends JSComponent[js.Object]

object Fragment {

  def apply(key: String | Int = null)(children: ReactNode*) =
    CreateElementJS[JSFragment.type](JSFragment,
                                     js.Dynamic.literal(),
                                     key = key,
                                     children = children.toJSArray)
}

object FragmentC {

  def apply(children: ReactNode*) =
    CreateElementJS[JSFragment.type](JSFragment,
                                     js.Dynamic.literal(),
                                     children = children.toJSArray)
}
