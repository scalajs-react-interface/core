package sri.core

import scala.scalajs.js
import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav

case class ViewProps(id: String)

class View extends ComponentP[ViewProps] {

  def render() =
    React.createElement("div", js.Dynamic.literal(id = props.id), children)
}

object View {

  @inline
  def apply(id: String, ref: js.Function1[View, Unit] = null)(
      children: ReactNode*) =
    CreateElementWithChildren[View](ViewProps(id),
                                    ref = ref,
                                    children = children.toJSArray)
}
