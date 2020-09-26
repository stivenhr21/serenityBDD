package net.serenitybdd.screenplay;

import net.serenitybdd.core.eventbus.Broadcaster;
import net.serenitybdd.screenplay.conditions.SilentPerformable;
import net.serenitybdd.screenplay.events.ActorAsksQuestion;
import net.thucydides.core.steps.StepEventBus;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BooleanQuestionConsequence<T> extends BaseConsequence<T> {
    private final Question<Boolean> question;
    private final String subject;

    private final static SilentPerformable DO_NOTHING = new SilentPerformable();

    public BooleanQuestionConsequence(Question<Boolean> actual) {
        this(null, actual);
    }

    public BooleanQuestionConsequence(String subjectText, Question<Boolean> actual) {
        this.question = actual;
        this.subject = QuestionSubject.fromClass(actual.getClass()).andQuestion(actual).subject();
        this.subjectText = Optional.ofNullable(subjectText);
    }

    @Override
    public void evaluateFor(Actor actor) {
        if (thisStepShouldBeIgnored() && !StepEventBus.getEventBus().softAssertsActive()) { return; }

        Broadcaster.getEventBus().post(new ActorAsksQuestion(question, actor.getName()));
        try {
            optionalPrecondition.orElse(DO_NOTHING).performAs(actor);
            assertThat(reason(), question.answeredBy(actor), is(true));
        } catch (Throwable actualError) {

            throwComplaintTypeErrorIfSpecified(errorFrom(actualError));

            throwDiagosticErrorIfProvided(errorFrom(actualError));

            throw actualError;
        }
    }

    private String reason() {
        return "Expected " + QuestionSubject.fromClass(question.getClass()).andQuestion(question).subject();
    }

    private void throwDiagosticErrorIfProvided(Error actualError) {
        if (question instanceof QuestionDiagnostics) {
            throw Complaint.from(((QuestionDiagnostics) question).onError(), actualError);
        }
    }

    @Override
    public String toString() {
        String template = explanation.orElse("Then %s");
        return addRecordedInputValuesTo(String.format(template, subjectText.orElse(subject)));
    }
}
