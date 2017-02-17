package sri.core

import scala.scalajs.js.|
import scala.scalajs.{LinkingInfo, js}

object CreateElementJSNoInline {

  @inline
  def apply[C <: ReactClass](ctor: C,
                             props: js.Any,
                             key: String | Int = null,
                             ref: C => Unit = null,
                             children: js.Array[ReactNode] = emptyJSArray())
    : ReactElement { type Instance = C } = {

    if (ref != null)
      props.asInstanceOf[js.Dynamic].updateDynamic("ref")(ref)
    if (key != null)
      props
        .asInstanceOf[js.Dynamic]
        .updateDynamic("key")(key.asInstanceOf[js.Any])
    React
      .createElement(ctor, props, children: _*)
      .asInstanceOf[ReactElement { type Instance = C }]
  }

}
