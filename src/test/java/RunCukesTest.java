import cucumber.api.junit.Cucumber;
import de.spinwork.datacuke.Fixtures;
import de.spinwork.datacuke.testutil.EmbeddedTomcat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import usecases.BaseUseCase;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"html:target/cucumber-html-report"})
public class RunCukesTest {

    private static EmbeddedTomcat tomcat = new EmbeddedTomcat();

    @BeforeClass
    public static void setup() {
        tomcat.start();
        tomcat.deploy("");
    }

    @AfterClass
    public static void teardown() {
        tomcat.stop();
    }
}