package de.spinwork.testapp

import de.spinwork.datacuke.Fixtures
import usecases.BaseUseCase

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


BaseUseCase test = new BaseUseCase()

Map map = new HashMap()

Before('@1234') {
    Fixtures.loader.data << [data: 'test']

    map << [x: 'y']
}

Before('@1235') {
    Fixtures.loader.usecases << [base: new BaseUseCase()]
}

Given(~'^a thing$') { ->
    println 'starting some cool test'
    Fixtures.loader.data.data = 'changedTest'
    assertEquals('changedTest', Fixtures.loader.data.data )
}

When(~'^doing something$') { ->
    assertEquals('changedTest', Fixtures.loader.data.data )
    assertEquals('y', map.x)
}

And(~'^there is one other thing$') { ->
    assertEquals('changedTest', Fixtures.loader.data.data )
    map.x = 'z'
}

Then(~'^everything is fine$') { ->
    assertEquals('changedTest', Fixtures.loader.data.data )
}

Given(~'^a base usecase$') { ->
    assertNotNull(Fixtures.loader.usecases.base)
    assertEquals('BaseUseCase', Fixtures.loader.usecases.base.getClass().simpleName)
}

When(~'^calling the get method on the usecase$') { ->
    Fixtures.loader.usecases.base.get()
    test.get()
    assertEquals('z', map.x)
}

Then(~'^there should be a response map with a key "([^"]*)" and the value "([^"]*)"$') { String expectedKey, String expectedValue ->
    assertNotNull(Fixtures.loader.usecases.base.response)
    assertEquals('testValue', Fixtures.loader.usecases.base.response.test)
    Fixtures.loader.usecases.base.assertGetReturnValue()
    test.assertGetReturnValue()
}