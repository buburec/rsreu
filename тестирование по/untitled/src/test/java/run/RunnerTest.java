package run;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@withdraval",
        snippets = SnippetType.CAMELCASE,
        plugin = {"pretty", "html:target/cucumber", "junit:target/surefire-reports/junit-report.xml"}
)

public class RunnerTest {
}
