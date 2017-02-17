package sri.core

import scala.scalajs.js
import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
import scala.scalajs.js.annotation.ScalaJSDefined

case class ViewProps(id: String)

@ScalaJSDefined
class View extends ReactComponent[ViewProps, Unit] {

  def render() =
    React.createElement("div", js.Dynamic.literal(id = props.id), children)
}

object View {

  @inline
  def apply(id: String)(children: ReactNode*) =
    CreateElement[View](ViewProps(id), children = children.toJSArray)
}
