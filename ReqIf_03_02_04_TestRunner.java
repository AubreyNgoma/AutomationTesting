package za.co.resbank;


import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/reqif_03_02_04_external_interface_no_of_forms_per_profile/")
public class ReqIf_03_02_04_TestRunner {
}
