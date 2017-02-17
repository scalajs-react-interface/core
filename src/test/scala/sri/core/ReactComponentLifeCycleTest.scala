package sri.core

import sri.enzyme.Enzyme
import scala.scalajs.js
class ReactComponentLifeCycleTest extends BaseTest {
  import ReactComponentLifeCycle._
  before {
    willMount = false
    didMount = false
    rendered = false
    willUpdate = false
    didUpdate = false
    shouldUpdate = false
    willReceiveProps = false
  }

  test("test life cycles1") {

    val instance = Enzyme
      .shallow(ReactComponentLifeCycle())
      .instance()
    assert(willMount)
    //    assert(didMount) // TODO check didMount not called in shallowRender
    assert(rendered)
    assert(!willUpdate)
    assert(!didUpdate)
    assert(!willReceiveProps)
    assert(!shouldUpdate)
    instance.updateState()
    assert(willUpdate)
    assert(didUpdate)
    assert(!willReceiveProps)
    assert(shouldUpdate)

  }

}
