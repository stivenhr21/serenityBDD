package net.serenitybdd.rest.configuring

import com.github.tomakehurst.wiremock.junit.WireMockRule
import io.restassured.specification.FilterableRequestSpecification
import net.serenity.test.utils.rules.TestCase
import net.serenitybdd.rest.decorators.ResponseDecorated
import net.serenitybdd.rest.rules.RestConfigurationAction
import net.serenitybdd.rest.rules.RestConfigurationRule
import net.thucydides.core.steps.BaseStepListener
import org.junit.Rule
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static net.serenitybdd.rest.SerenityRest.given
import static net.serenitybdd.rest.SerenityRest.reset

/**
 * User: YamStranger
 * Date: 3/30/16
 * Time: 9:57 AM
 */
class WhenConfiguringAuthRequestParameters extends Specification {

    @Rule
    def WireMockRule wire = new WireMockRule(0);

    @Rule
    def RestConfigurationRule rule = new RestConfigurationRule(new RestConfigurationAction() {
        @Override
        void apply() {
            reset()
        }
    },)

    @Rule
    def TestCase<BaseStepListener> test = new TestCase({
        Mock(BaseStepListener)
    }.call());

    def "should be returned wrapped request specification after authentication-basic configuration"() {
        given: "request initialised"
            def request = (FilterableRequestSpecification) given();
        when: "changing authentication configuration and getting request specification"
            def requestAfterLog = request.authentication().basic("user", "password")
        then: "same request should be returned after log operation"
            requestAfterLog == request
    }

    def "should be returned wrapped request specification after auth-oauth2 configuration"() {
        given: "request initialised"
            def request = (FilterableRequestSpecification) given();
        when: "changing authentication configuration and getting request specification"
            def responseAfterLog = request.auth().oauth2("asdfsafas")
        then: "wrapped response specification should be returned after log operation"
            responseAfterLog ==  request
    }

    def "should be returned wrapped request specification after auth-preemptive-basic configuration"() {
        given: "request initialised"
            def request = (FilterableRequestSpecification) given();
        when: "changing authentication configuration and getting request specification"
            def responseAfterLog = request.auth().preemptive().basic("user", "password")
        then: "wrapped response specification should be returned after log operation"
            responseAfterLog == request
    }

    def "should be returned wrapped request specification after authentication-none configuration"() {
        given: "request initialised"
            def request = (FilterableRequestSpecification) given();
        when: "changing authentication configuration and getting request specification"
            def responseAfterLog = request.authentication().none()
        then: "wrapped response specification should be returned after log operation"
            responseAfterLog == request
    }

    def "should be returned wrapped response after authentication-none configuration and executing request"() {
        given: "rest with default config updated"
            def body = "<root>" +
                "<value>1</value>" +
                "</root>"

            def base = "http://localhost:${wire.port()}"
            def path = "/test/log/levels"
            def url = "$base$path"

            stubFor(get(urlMatching(path))
                .withRequestBody(matching(".*"))
                .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/xml")
                .withBody(body)));
            def request = (FilterableRequestSpecification) given();
        when: "changing authentication configuration and and executing request"
            def responseAfterExecutingRequest = request.authentication().basic("login","password").get(url)
        then: "wrapped response should be returned after executing request"
            responseAfterExecutingRequest instanceof ResponseDecorated
        and: "status code returned as expected"
            responseAfterExecutingRequest.then().statusCode(200)
    }
}
