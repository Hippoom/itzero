package itzero.smoke

import io.restassured.RestAssured
import org.junit.Before
import org.junit.Test

import static io.restassured.RestAssured.given
import static org.hamcrest.Matchers.equalTo

class DeploymentSmokeTest {

    @Before
    void setup() {
        RestAssured.baseURI = getBaseUri()
        RestAssured.port = getPort()
    }

    @Test
    void itShouldBeRunningWithExpectedBuildVersion() {
        // @formatter:off
        given().
            log().all().
        when().
            get("/info").
        then().
            log().all().
            body("build.version", equalTo(expectedBuildVersion()))
        // @formatter:on

    }

    private String getBaseUri() {
        System.getenv("ITZERO_BASE_URI")
    }

    private int getPort() {
        System.getenv("ITZERO_PORT").toInteger()
    }

    private String expectedBuildVersion() {
        Properties properties = new Properties()
        properties.load(DeploymentSmokeTest.class.getClassLoader().getResourceAsStream("META-INF/build-info.properties"))
        properties."build.version"

    }
}