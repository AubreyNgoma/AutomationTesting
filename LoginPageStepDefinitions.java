package za.co.resbank.cucumbersteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.util.PendingException;
import za.co.resbank.forms.testing.data.PersonaDataProvider;
import za.co.resbank.forms.testing.model.Persona;
import za.co.resbank.forms.testing.model.PersonaDetail;
import za.co.resbank.forms.testing.personas.CurrentPersona;
import za.co.resbank.serenitysteps.FormsPortalEndUserSteps;
import za.co.resbank.serenitysteps.InternalFormsProcessingEndUserSteps;

/**
 * Created with IntelliJ IDEA.
 * User: Lean
 * Date: 6/4/18
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */


public class LoginPageStepDefinitions {

    private CurrentPersona currentPersona;
    private Persona persona;
    private PersonaDetail personaDetail;
    private PersonaDataProvider personaDataProvider = new PersonaDataProvider();
    @Steps
    FormsPortalEndUserSteps externalUser;

    @Steps
    InternalFormsProcessingEndUserSteps internalUser;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @When("^s?he enters h(?:is|er) \"([^\"]*)\" credentials$")
    public void user_enters_credentials(String credentialSet) throws Throwable {
        currentPersona = CurrentPersona.getInstance();
        // Load persona details
        personaDetail =  personaDataProvider.getPersona(currentPersona.getPersona());
        LOGGER.info("Persona Details loaded: "+personaDetail.toString());
        if(credentialSet.equals("VALID")){
            externalUser.enterEmail(personaDetail.getUserName());
            externalUser.enterPassword(personaDetail.getValidPassword());
        }else if(credentialSet.equals("INVALID_WRONG_PASSWORD")){
            externalUser.enterEmail(personaDetail.getUserName());
            externalUser.enterPassword(personaDetail.getInvalidPassword());
        }else if(credentialSet.equals("INVALID_WRONG_USERNAME")){
            externalUser.enterEmail(personaDetail.getInvalidUsername());
            externalUser.enterPassword(personaDetail.getValidPassword());
        }
        externalUser.submit();
    }

    @Then("^the forms portal should display$")
    public void the_forms_portal_should_display() throws Throwable {
       //Here we will see if a certain component on the forms portal are being displayed to ensure it is in fact logged in.
        if(!externalUser.isOnReadyPage()){
            throw new Exception("User login failed, system error");
        }
    }

    @Then("^the login attempt should be unsuccessful$")
    public void the_login_attempt_should_be_unsuccessful() throws Throwable {
      // Here nothing will happen, the certainty depends on the pop up message 
      // that is still being implemented by the development team.
    }

    @And("^a failure message should display with the following wording \"([^\"]*)\"$")
    public void a_failure_message_should_display_with_the_following_wording(String message) throws Throwable {
       externalUser.checkErrorMessage(message);
    }

    @Then("^a anti-robot pop up must display to test if you are human$")
    public void a_anti_robot_pop_up_must_display_to_test_if_you_are_human() throws Throwable {
      //here the anti-robot popup will be revealed after numerous attempts of logging in with the wrong credentials.
        throw new PendingException("This service still needs to be activated");
    }

    @Then("^he will be logged in successfully$")
    public void he_will_be_logged_in_successfully() throws Throwable {
           if(!externalUser.isOnReadyPage()){
               throw new Exception("Something went wrong with logging in the user");
           }

    }

    @Then("^he should be logged in successfully$")
    public void heShouldBeLoggedInSuccessfully() throws Throwable {
        internalUser.successfully_logged_into_the_forms_portal();
    }


    @When("^\"([^\"]*)\" enters \"([^\"]*)\" credentials more than (\\d+) times$")
    public void enters_credentials_more_than_times(String username, String credentialSet, int number) throws Throwable {
        currentPersona = CurrentPersona.getInstance();
        personaDetail = new PersonaDetail(currentPersona.getPersona());
        LOGGER.info("Persona Details loaded: "+personaDetail.toString());

        for(int i =0; i < 5; i++){
            externalUser.enterEmail(personaDetail.getUserName());
            externalUser.enterPassword(personaDetail.getValidPassword());
            externalUser.submit();
        }
    }

    @Then("^\"([^\"]*)\" will be logged in successfully$")
    public void WillBeLoggedInSuccessfully(String name) throws Throwable {

    }

    @And("^the internal forms processing page should be displayed$")
    public void theFormsProcessingPageShouldBeDisplayed() throws Throwable {
        internalUser.forms_processing_page_displayed();
    }



}
