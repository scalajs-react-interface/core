package sri

import scala.collection.GenTraversableOnce
import scala.reflect.ClassTag
import scala.scalajs.{LinkingInfo, js}
import scala.scalajs.js.Dynamic.{global => g, literal => json}
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.`|`

package object core {

  type RefType = js.Function

  type PropsChildrenType = ReactElement | js.Array[ReactChildren]

  type ReactText = String | Double

  type ReactEmpty = Boolean | Null

  type PropsChildren = ReactElement

  type ReactNode = ReactElement | ReactText | js.Array[ReactElement] | js.Array[
    String] | js.Array[Double]

  @inline
  def REACT_ELEMENT_TYPE: js.Any =
    if (!js.isUndefined(js.Dynamic.global.`Symbol`) && !js.isUndefined(
          js.Dynamic.global.`Symbol`.`for`))
      js.Dynamic.global.`Symbol`.`for`("react.element")
    else 0xeac7;

  @inline def setReactElementType =
    js.Dynamic.global.`REACT_ELEMENT_TYPE` = REACT_ELEMENT_TYPE

  /* this works here, but not in the general case!
   * (see https://github.com/scala-js/scala-js/pull/2070 )
   */
  //TODO We need to find a better solution here
//  implicit def UnionEvidence[A: ClassTag, B: ClassTag](
//      ab: A | B)(implicit eva: A => js.Any, evb: B => js.Any): js.Any =
//    ab.asInstanceOf[js.Any]

  implicit def genTravarsableToJSArrayReactElement(
      elm: GenTraversableOnce[ReactElement]): ReactNode =
    elm.toJSArray.asInstanceOf[ReactNode]

  implicit class Boolean_Ext_Methods(val value: Boolean) extends AnyVal {
    def ?=(elm: => ReactNode): ReactElement =
      if (value) elm.asInstanceOf[ReactElement] else null
  }

  implicit class UndefOr_Ext_Methods(val value: js.UndefOr[_]) extends AnyVal {
    def isUndefinedOrNull: Boolean = value.isEmpty || value == null

    def isDefinedAndNotNull: Boolean = value.isDefined && value != null
  }

  @inline def emptyJSArray[A]() = js.Array[A]()

  implicit class String_Ext_Methods(val value: String) extends AnyVal {

    def removeForwardSlashes =
      if (value != null) value.replaceAll("/", "") else value

    def removeTrailingSlash =
      if (value != null) value.replaceAll("/$", "") else value
  }

  trait CoreAll {}

  object all extends CoreAll

}
