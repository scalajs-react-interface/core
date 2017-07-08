package sri.core

import scala.scalajs.js
import scala.scalajs.js.|

object CreateElementJSNoInline {

  @inline
  def apply[C <: ReactClass](ctor: ComponentConstructor {
    type ComponentType = C
  }, props: C#PropsType, key: String | Int = null, ref: js.Function1[C, Unit] = null, children: js.Array[ReactNode] = emptyJSArray())
    : ReactElement { type Instance = C } = {

    if (ref != null)
      props.asInstanceOf[js.Dynamic].updateDynamic("ref")(ref)
    if (key != null)
      props
        .asInstanceOf[js.Dynamic]
        .updateDynamic("key")(key.asInstanceOf[js.Any])
    React
      .createElement(ctor, props.asInstanceOf[js.Any], children: _*)
      .asInstanceOf[ReactElement { type Instance = C }]
  }

}
