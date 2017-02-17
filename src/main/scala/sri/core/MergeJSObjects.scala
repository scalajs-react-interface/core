package sri.core

import scala.scalajs.js

object MergeJSObjects {

  @inline def apply(in: js.Object, extra: js.Object) = {
    js.Object
      .keys(extra)
      .foreach(
        key =>
          in.asInstanceOf[js.Dynamic]
            .updateDynamic(key)(
              extra.asInstanceOf[js.Dynamic].selectDynamic(key)))
  }
}
