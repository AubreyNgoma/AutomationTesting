package za.co.resbank.cucumbersteps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.resbank.forms.testing.model.Persona;
import za.co.resbank.forms.testing.model.PersonaDetail;
import za.co.resbank.serenitysteps.AemFormsAuthorSteps;
import za.co.resbank.serenitysteps.FormsPortalEndUserSteps;


public class AemAuthorStepDefs {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Steps
    AemFormsAuthorSteps author;

    @Steps
    FormsPortalEndUserSteps maxBrowser;

    public void beforeOpen()
    {
        maxBrowser.goMax();
    }


    @And("^she is in the \"([^\"]*)\" folder$")
    public void she_is_in_the(String sectionName) throws Exception {
        author.is_on_aem_sarb_web_interface_page();
    }

    @And("^opens the \"([^\"]*)\" form for editing$")
    public void opens_the_form_for_editing(String formType) throws Exception {
        author.edits_the_form(formType);
    }

    @When("she Previews the form$")
    public void she_previews_the_form() throws Exception {
       author.previews_the_form();
    }

    @Then("^the form design should appear in preview mode$")
    public void the_form_design_should_appear_in_preview_mode() throws Exception {
        author.see_form_design();
    }


    @When("^she opens the components list$")
    public void sheOpensTheList() throws Throwable {
        author.opens_components_list();
    }

    @Then("^the \"([^\"]*)\" is available$")
    public void theIsAvailable(String component) throws Throwable {
        author.confirms_component_is_on_the_list(component);
    }

    @When("^she adds a \"([^\"]*)\" to the form toolbar$")
    public void sheAddsAToTheFormToolbar(String component){
        author.adds_component_on_form_toolbar(component);
    }

    @Then("^the \"([^\"]*)\" should appear in the form toolbar$")
    public void the_should_appear_in_the_form_toolbar(String component) throws Exception {
         author.confirms_first_added_component_is_available_on_preview(component);
    }

    @And("^the form already contains a \"([^\"]*)\"$")
    public void the_form_already_contains_a(String component) throws Exception {
         author.confirms_component_is_available_on_form_toolbar(component);
    }

    @Then("^two \"([^\"]*)\" should appear in the form toolbar$")
    public void two_should_appear_in_the_form_toolbar(String component) throws Exception {
         author.confirms_second_added_component_is_available_on_preview(component);
    }


    @Then("^She should see a button that contains text \"([^\"]*)\"$")
    public void sheShouldSeeAButtonThatContainsText(String btnText) throws Throwable {
       //Here we should get a buttons text and compare it to the parameter value
        if(!author.getFormaAndDocumentsButton(btnText)){
            LOGGER.info("The Button Could not be found that should contain the text : "+btnText);
            throw new RuntimeException("The Button Could not be found that should contain the text : "+btnText);
        }

    }

    @And("^She navigates to view the Forms$")
    public void sheNavigatesToViewTheForms() throws Throwable {
      author.clickForms();
    }

    @And("^she starts to create a new \"([^\"]*)\" with the \"([^\"]*)\" template$")
    public void sheStartsToCreateANew(String formType, String formCat) throws Throwable {
     // click create, get the list of items, choose the one equal to formType and click on it.
        author.clickFormInCreateList(formType);

        //this can be a switch if there are more than these 2 types of forms being used in the aem features.
        if(formCat.contains("Sarb blank")) {
            author.clickSarbBlankForm();
            LOGGER.info("Clicking on the SARB Blank Form card");
        }else if(formCat.contains("ERSD survey")){
            author.clickERSDSurvey();
            LOGGER.info("Clicking on the ERSD survey card");
        }
    }

    @Then("^she gives the form a title of \"([^\"]*)\"$")
    public void sheGivesTheFormATitleOf(String title) throws Throwable {
         author.setTitle(title);
         LOGGER.info("The title is being set to : "+title);
    }
}
