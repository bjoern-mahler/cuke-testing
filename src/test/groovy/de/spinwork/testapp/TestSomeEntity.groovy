package de.spinwork.testapp

import de.spinwork.testapp.base.SomeEntity

/**
 */
class TestSomeEntity extends GroovyTestCase {

    @Override
    void setUp() {
    }

    void testCreation() {
        int a = 2
        [1, 2, 3].each { Integer it ->
            a = a + it + it
        }
        SomeEntity entity = new SomeEntity(id: 1, property: 'test')
        assertEquals(1, entity.id)
        assertEquals 'test', entity.property
    }
}
