package sri

import scala.collection.GenTraversableOnce
import scala.reflect.ClassTag
import scala.scalajs.{LinkingInfo, js}
import scala.scalajs.js.Dynamic.{global => g, literal => json}
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.`|`

package object core {

  type RefType = js.Function

  type PropsChildrenType = ReactElement | js.Array[ReactElement]

  type ReactText = String | Double

  type ReactEmpty = Boolean | Null

  type PropsChildren = ReactElement

  type ReactNode = ReactElement | ReactText | js.Array[ReactElement] | js.Array[
    String] | js.Array[Double]

  @inline def emptyJSArray[A]() = js.Array[A]()

  @inline
  def REACT_ELEMENT_TYPE: js.Any =
    if (!js.isUndefined(js.Dynamic.global.`Symbol`) && !js.isUndefined(
          js.Dynamic.global.`Symbol`.`for`))
      js.Dynamic.global.`Symbol`.`for`("react.element")
    else 0xeac7;

  @inline def setReactElementType =
    js.Dynamic.global.`REACT_ELEMENT_TYPE` = REACT_ELEMENT_TYPE

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
