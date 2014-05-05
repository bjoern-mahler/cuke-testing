package de.spinwork.testapp

import de.spinwork.datacuke.Fixtures

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

And(~'^the response should also be available in another step definition file$') { ->
    Fixtures.loader.usecases.base.assertGetReturnValue()
}