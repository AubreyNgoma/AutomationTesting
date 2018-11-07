package za.co.resbank;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/reqif_03_02_12_external_interface_submitting_multiple_versions")
public class ReqIf_03_02_12_TestRunner {
}
