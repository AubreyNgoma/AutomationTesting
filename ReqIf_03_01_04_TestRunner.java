package za.co.resbank;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/reqif_03_01_04_internal_interface_user_management")
public class ReqIf_03_01_04_TestRunner {}
