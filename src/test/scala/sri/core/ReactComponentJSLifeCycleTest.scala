package sri.core

import sri.enzyme.Enzyme

class ReactComponentJSLifeCycleTest extends BaseTest {

  import ReactComponentJSLifeCycle._

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
    val instance = Enzyme.shallow(ReactComponentJSLifeCycle()).instance()
    assert(willMount)
    //    assert(didMount == DID_MOUNT) TODO check didMount not called in shallowRender
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
