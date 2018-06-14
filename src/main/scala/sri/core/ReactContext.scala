package sri.core

import scala.scalajs.js
import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
@js.native
trait ReactContextJS extends js.Object {

  val Provider
    : ComponentConstructor { type ComponentType = JSComponent[js.Object] } =
    js.native

  val Consumer
    : ComponentConstructor { type ComponentType = JSComponent[js.Object] } =
    js.native

}

sealed trait ReactContext[T] extends js.Object

object ReactContext {

  implicit class ReactContextImpl[T](val in: ReactContext[T]) extends AnyVal {

    def Provider(value: T)(children: ReactNode*) =
      CreateElementJS[JSComponent[js.Object]](
        in.asInstanceOf[ReactContextJS].Provider,
        js.Dynamic.literal(value = value.asInstanceOf[js.Any],
                           children = children.toJSArray))

    def Consumer(children: js.Function1[T, ReactNode]) =
      CreateElementJS[JSComponent[js.Object]](
        in.asInstanceOf[ReactContextJS].Consumer,
        js.Dynamic.literal(),
        children = js.Array(children.asInstanceOf[ReactNode]))

  }

}
