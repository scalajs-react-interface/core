package sri.core
package reactcomponentjs

class ComponentJSLifeCycleTest extends BaseTest {

  import ComponentJSLifeCycle._

//  before {
  willMount = false
  didMount = false
  rendered = false
  willUpdate = false
  didUpdate = false
  shouldUpdate = false
  willReceiveProps = false
//  }

  test("test ComponentJS life cycles1") {
    val instance =
      ReactDOM.render(ComponentJSLifeCycle(),
                      org.scalajs.dom.document.getElementById(APP_ID))
    expect(willMount).toBeTruthy()
    expect(didMount).toBeTruthy()
    expect(rendered).toBeTruthy()
    expect(willUpdate).toBeFalsy()
    expect(didUpdate).toBeFalsy()
    expect(willReceiveProps).toBeFalsy()
    expect(shouldUpdate).toBeFalsy()
    instance.updateState()
    expect(willUpdate).toBeTruthy()
    expect(didUpdate).toBeTruthy()
    expect(willReceiveProps).toBeFalsy()
    expect(shouldUpdate).toBeTruthy()

  }

}
