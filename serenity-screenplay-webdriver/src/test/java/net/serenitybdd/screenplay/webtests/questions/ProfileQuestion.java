package net.serenitybdd.screenplay.webtests.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.SelectedVisibleTextValue;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.webtests.model.Customer;
import net.serenitybdd.screenplay.webtests.pages.ProfilePage;

public class ProfileQuestion implements Question<Customer> {
    @Override
    public Customer answeredBy(Actor actor) {
        String name = Value.of(ProfilePage.NAME)
                           .viewedBy(actor)
                           .value();

        String country = SelectedVisibleTextValue.of(ProfilePage.COUNTRY).viewedBy(actor).value();
        return new Customer(name, country);
    }
}
