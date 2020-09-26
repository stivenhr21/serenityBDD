package net.serenitybdd.screenplay.questions

import com.beust.jcommander.internal.Lists
import com.google.common.collect.Sets
import net.serenitybdd.core.Serenity
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Question
import net.thucydides.core.model.Story
import net.thucydides.core.steps.BaseStepListener
import net.thucydides.core.steps.StepEventBus
import spock.lang.Specification

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat
import static org.hamcrest.Matchers.equalTo

class WhenRememberingAnswers extends Specification {

    def setup() {
        Serenity.initialize(this);
        StepEventBus.getEventBus().testSuiteStarted(Story.called("sample story"));
        StepEventBus.getEventBus().testStarted("sample test");
    }

    def "should be able to make assertions about values previously remembered"() {
        given:
            Actor tracy = Actor.named("Tracy")
        when:
            tracy.remember("age",30)
        then:
            tracy.should(seeThat(Remembered.valueOf("age"), equalTo(30)))
    }

    def "should be able to make assertions about different types"() {
        given:
            Actor tracy = Actor.named("Tracy")
        when:
            tracy.remember("color","Red")
        then:
            tracy.should(seeThat(Remembered.valueOf("color"), equalTo("Red")))
    }

}
