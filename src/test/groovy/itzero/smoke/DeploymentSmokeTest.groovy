package itzero.smoke

import io.restassured.RestAssured
import org.junit.Before
import org.junit.Test

import java.util.concurrent.Callable

import static io.restassured.RestAssured.given
import static java.util.concurrent.TimeUnit.SECONDS
import static org.awaitility.Awaitility.await
import static org.hamcrest.Matchers.equalTo

class DeploymentSmokeTest {

    @Before
    void setup() {
        RestAssured.baseURI = getBaseUri()
        RestAssured.port = getPort()
    }

    @Test
    void itShouldBeRunningWithExpectedBuildVersion() {

        await().
                atMost(90, SECONDS).pollInterval(10, SECONDS).
                until(getActualBuildVersion(), equalTo(expectedBuildVersion()))
    }

    private Callable<String> getActualBuildVersion() {
        return new Callable<String>() {
            String call() throws Exception {
                try {
                    // @formatter:off
                    given().
                        log().all().
                    when().
                        get("/info").
                    then().
                        log().all().extract().body().jsonPath().getString("build.version")
                    // @formatter:on
                } catch (ConnectException e) {
                    e.printStackTrace()
                    ""
                }
            }
        }
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