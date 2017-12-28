package sri

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g, literal => json}
import scala.scalajs.js.`|`

package object core {

  type ReactText = String | Double

  type ReactEmpty = Boolean | Null

  type ReactNode = ReactElement | ReactText | js.Array[ReactElement] | js.Array[
    String] | js.Array[Double] | ReactPortal

  type ReactElementNode = ReactElement | js.Array[ReactElement]
  @inline def emptyJSArray[A]() = js.Array[A]()

  @inline
  def componentConstructor[C <: ReactClass: js.ConstructorTag]
    : ComponentConstructor { type ComponentType = C } =
    js.constructorTag[C]
      .constructor
      .asInstanceOf[ComponentConstructor { type ComponentType = C }]

  //https://github.com/milessabin/shapeless/blob/master/core/src/main/scala/shapeless/package.scala#L40-L45
  trait =:!=[A, B] extends Serializable
  def unexpected: Nothing = sys.error("Unexpected invocation")
  implicit def neq[A, B]: A =:!= B = new =:!=[A, B] {}
  implicit def neqAmbig1[A]: A =:!= A = unexpected
  implicit def neqAmbig2[A]: A =:!= A = unexpected

}
