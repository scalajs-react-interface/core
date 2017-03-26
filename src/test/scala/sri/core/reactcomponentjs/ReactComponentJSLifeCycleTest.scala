package sri.core
package reactcomponentjs

class ComponentJSLifeCycleTest extends BaseTest {

  import ComponentJSLifeCycle._

  before {
    willMount = false
    didMount = false
    rendered = false
    willUpdate = false
    didUpdate = false
    shouldUpdate = false
    willReceiveProps = false
  }

  test("test ComponentJS life cycles1") {
    val instance =
      ReactDOM.render(ComponentJSLifeCycle(), app)
    assert(willMount)
    assert(didMount)
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
