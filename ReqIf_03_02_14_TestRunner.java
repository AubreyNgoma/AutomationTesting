package za.co.resbank;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/reqif_03_02_14_external_interface_editing_rules")
public class ReqIf_03_02_14_TestRunner {
}
