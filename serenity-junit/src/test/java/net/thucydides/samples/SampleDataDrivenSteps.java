package net.thucydides.samples;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleDataDrivenSteps extends ScenarioSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThucydidesRunner.class);

    public SampleDataDrivenSteps(Pages pages) {
        super(pages);
    }

    public String name;
    public String age;
    public String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @StepGroup
    public void data_driven_test_step() {
        enter_name_and_age(name, age);
        enter_address(address);
    }

    @Step
    public void enter_address(String address) {
        LOGGER.info("Entering address " + address);
    }

    @Step
    public void enter_name_and_age(String name, String age) {
        LOGGER.info("Entering name and age " + name + "/" + age);
    }

}
