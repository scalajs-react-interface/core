package sri.core
package reactcomponentjs

class ComponentJSLifeCycleTest extends BaseTest {

  import ComponentJSLifeCycle._

  test("test ComponentJS life cycles1") {
    val instance =
      ReactDOM.render(ComponentJSLifeCycle(),
                      org.scalajs.dom.document.getElementById(APP_ID))
    expect(didMount).toBeTruthy()
    expect(rendered).toBeTruthy()
    expect(derivedStateFromProps).toBeTruthy()
    expect(snapshotBeforeUpdate).toBeFalsy()
    expect(didUpdate).toBeFalsy()
    expect(shouldUpdate).toBeFalsy()
    instance.updateState()
    expect(derivedStateFromProps).toBeFalsy()
    expect(snapshotBeforeUpdate).toBeTruthy()
    expect(didUpdate).toBeTruthy()
    expect(shouldUpdate).toBeTruthy()

  }

}
