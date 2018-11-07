package za.co.resbank.cucumbersteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.resbank.serenitysteps.C23FormEndUserSteps;

/**
 * Steps for the C23 form, inserting and submitting data
 * User: Lean
 * Date: 7/25/18
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class C23Form {

    @Steps
    C23FormEndUserSteps c23FormEndUserSteps;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @And("^populates the form with the \"([^\"]*)\" dataset$")
    public void populates_the_form_with_the_dataset(String dataSetType) throws Throwable {
        c23FormEndUserSteps.changeTableFields(FormsPortalExternal.formTypes,dataSetType);
    }

    @And("^s?he clicks the Validate Button$")
    public void clicks_the_Validate_Button() throws Throwable {
        c23FormEndUserSteps.clickValidate();
    }

    @Then("^no validation errors are reported with the following wording \"([^\"]*)\"$")
    public void no_validation_errors_are_reported(String errorMessage) throws Throwable {

    }

    @Then("^the validation for \"([^\"]*)\" fails$")
    public void the_validation_for_fails(String ruleType) throws Throwable {
        ////Here a method should execute that targets all five tabs, and checks for the error messages at each field that contains a value.
        for(int i = 1; i < 6; i++){
            LOGGER.info("Checking for errors in table : "+i);
            if(!c23FormEndUserSteps.gotoTable("TABLE"+i,ruleType)){
                throw new RuntimeException("Something went wrong with rule :"+ruleType+" in table: "+i);
            }
        }
    }

    @And("^an error message is displayed with the following wording \"([^\"]*)\"$")
    public void an_error_message_is_displayed_with_the_following_wording(String errorMessage) throws Throwable {

    }

    @Then("^all the validations should \"([^\"]*)\"")
    public void all_the_validations_should(String ruleType) throws Throwable {
      //Here a method should execute that targets all five tabs, and checks for the error messages at each field that contains a value,
      // here it should not pick up any error fields.
        for(int i = 1; i < 6; i++){
            if(!c23FormEndUserSteps.gotoTable("TABLE"+i,ruleType)){
                throw new RuntimeException("Something went wrong with rule :"+ruleType+" in table: "+i);
            }
        }
    }
}
