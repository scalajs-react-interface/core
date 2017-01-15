package sri

import scala.collection.GenTraversableOnce
import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g,literal => json}
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce
import scala.scalajs.js.`|`

package object core {

  type RefType = js.Function

  type PropsChildrenType = ReactElement | js.Array[ReactChildren]

  type ReactText = String | Double

  type ReactEmpty = Boolean | Null

  type ReactNode = ReactElement | ReactText | js.Array[ReactElement] | js.Array[String] | js.Array[Double] | js.Array[ReactElementU[_, _]]


  /**
    *
    * // Production mode
      // http://babeljs.io/blog/2015/03/31/5.0.0/#inline-elements
      // Logic here taken from:
      // https://github.com/babel/babel/blob/master/packages/babel-helpers/src/helpers.js
      // https://github.com/babel/babel/tree/master/packages/babel-plugin-transform-react-inline-elements/test/fixtures/inline-elements
    https://github.com/japgolly/scalajs-react/blob/master/core/src/main/scala/japgolly/scalajs/react/vdom/Builder.scala
    * @param tag
    * @param props
    * @param children
    * @return
    */
  @noinline
  def inlineReactElement(tag: String, props: js.Object, children: ReactNode*): ReactElement = {
    val ref = props.asInstanceOf[js.Dynamic].ref.asInstanceOf[js.UndefOr[js.Any]]
    if (ref.isDefined)
      React.createElement(tag, props, children: _*)
    else {

      val REACT_ELEMENT_TYPE: js.Any =
        try
          js.Dynamic.global.Symbol.`for`("react.element")
        catch {
          case _: Throwable => 0xeac7
        }
      val key = props.asInstanceOf[js.Dynamic].key.asInstanceOf[js.UndefOr[js.Any]]

      val clen = children.length
      if (clen != 0) {
        val c: js.Any = if (clen == 1) children(0).asInstanceOf[js.Any] else (children.toJSArray)
        props.asInstanceOf[js.Dynamic].updateDynamic("children")(c)
      }

      json(
        `$$typeof` = REACT_ELEMENT_TYPE,
        `type` = tag,
        key = key.fold(null: js.Any)("" + _),
        ref = null,
        props = props,
        _owner = null)
        .asInstanceOf[ReactElement]
    }

  }
  /* this works here, but not in the general case!
  * (see https://github.com/scala-js/scala-js/pull/2070 )
  */
  //TODO We need to find a better solution here
  implicit private[sri] def UnionEvidence[A: ClassTag, B: ClassTag](ab: A | B)(implicit eva: A => js.Any, evb: B => js.Any): js.Any = ab.asInstanceOf[js.Any]

  implicit def genTravarsableToJSArrayReactElement(elm: GenTraversableOnce[ReactElement]): ReactNode = elm.toJSArray.asInstanceOf[ReactNode]

  implicit class Boolean_Ext_Methods(val value: Boolean) extends AnyVal {
    def ?=(elm: => ReactNode): ReactElement = if (value) elm.asInstanceOf[ReactElement] else null
  }

  implicit class UndefOr_Ext_Methods(val value: js.UndefOr[_]) extends AnyVal {
    def isUndefinedOrNull: Boolean = value.isEmpty || value == null

    def isDefinedAndNotNull: Boolean = value.isDefined && value != null
  }

  implicit class String_Ext_Methods(val value: String) extends AnyVal {

    def removeForwardSlashes = if (value != null) value.replaceAll("/", "") else value

    def removeTrailingSlash = if (value != null) value.replaceAll("/$", "") else value
  }

  trait CoreAll extends ElementFactory with ElementFactoryLegacy {
    @inline def load[T](lib: String): T = g.require(lib).asInstanceOf[T]

    @inline def loadDynamic(lib: String): js.Dynamic = load[js.Dynamic](lib)

  }

  object all extends CoreAll

}