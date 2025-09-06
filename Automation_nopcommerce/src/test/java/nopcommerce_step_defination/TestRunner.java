package nopcommerce_step_defination;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/nopcommerce_features", 
					glue = "nopcommerce_step_defination", 
					plugin= {"pretty", "html:target/HTML/htmlreport1.html", 
							"json:target/JSON/jsonreport1",
							"junit:target/JUNIT/junitreport1"})
public class TestRunner {
	
}
