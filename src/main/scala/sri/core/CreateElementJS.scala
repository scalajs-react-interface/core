package sri.core

import scala.scalajs.{LinkingInfo, js}
import scala.scalajs.js.{ConstructorTag, |}

object CreateElementJS {
  @inline
  def apply[C <: ReactClass](ctor: ComponentConstructor {
    type ComponentType = C
  }, props: C#PropsType, key: String | Int = null, ref: js.Function1[C, Unit] = null, children: js.Array[ReactNode] = emptyJSArray())
    : ReactElement { type Instance = C } = {

    if (LinkingInfo.developmentMode) {
      if (ref != null)
        props.asInstanceOf[js.Dynamic].updateDynamic("ref")(ref)
      if (key != null)
        props
          .asInstanceOf[js.Dynamic]
          .updateDynamic("key")(key.asInstanceOf[js.Any])
      React
        .createElement(ctor, props.asInstanceOf[js.Any], children: _*)
        .asInstanceOf[ReactElement { type Instance = C }]
    } else { // https://babeljs.io/docs/plugins/transform-react-inline-elements/
      if (children.length == 1) {
        props
          .asInstanceOf[js.Dynamic]
          .updateDynamic("children")(children(0).asInstanceOf[js.Any])
      } else if (children.length > 1)
        props
          .asInstanceOf[js.Dynamic]
          .updateDynamic("children")(js.Array(children: _*))
      js.Dynamic
        .literal(
          `$$typeof` = js.Dynamic.global
            .selectDynamic("REACT_ELEMENT_TYPE"),
          `type` = ctor,
          props = props.asInstanceOf[js.Any],
          ref = ref,
          key =
            if (key != null) "" + key
            else key.asInstanceOf[js.Any]
        )
        .asInstanceOf[ReactElement { type Instance = C }]
    }

  }

}
