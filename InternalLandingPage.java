package za.co.resbank.cucumbersteps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.resbank.forms.testing.data.FormReleaseDataProvider;
import za.co.resbank.forms.testing.data.FormTypeDataProvider;
import za.co.resbank.serenitysteps.InternalFormsProcessingEndUserSteps;

/**
 * This Step Definition file contains Cucumber step definitions for all steps related
 * to the internal page that shows all the forms that are set for release
 *
 * @author lean
 *
 */
public class InternalLandingPage {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Steps
    InternalFormsProcessingEndUserSteps internalUser;

    private FormTypeDataProvider formTypeDataProvider = new FormTypeDataProvider();
    private FormReleaseDataProvider formReleaseDataProvider = new FormReleaseDataProvider();
    private String status = null;


    @And("^he see a \"([^\"]*)\" form \"([^\"]*)\" in the \"([^\"]*)\" tab$")
    public void he_see_a_form_in_the_tab(String formType, String instanceName, String tab) throws Throwable {
      if(!internalUser.checkFormRelease(instanceName))
        {
           throw new Exception("Data Subject "+instanceName+" Could not be found");
        }
    }

    @When("^he view the \"([^\"]*)\" forms release detail for \"([^\"]*)\"$")
    public void he_view_the_forms_release_detail_for(String formType, String dataSubject) throws Throwable {

            internalUser.clickRelease(dataSubject);
    }

    @And("^\"([^\"]*)\" the form for \"([^\"]*)\"$")
    public void the_form_for(String option, String release) throws Throwable {
           internalUser.openCloseProspone(option);

    }


    @And("^view all the form instances$")
    public void view_all_the_form_instances() throws Throwable {
       internalUser.viewFormInstances();
    }


    @Given("^there is no form type with a \"([^\"]*)\" of \"([^\"]*)\"$")
    public void thereIsNoFormType(String formTypeCode, String formTypeValue) throws Throwable {
       //Here we need to make a jcr call to check if a certain form type actually exists already before executing the test,
        //if it do exist, we can delete it and re-create it if needed, alternative for not making this test fail.

    }

    @Given("^there is a form type with a \"([^\"]*)\" of \"([^\"]*)\"$")
    public void thereIsAFormType(String propertyType, String propertyValue) throws Throwable {
       //Uses the exact same method as above, the result should only be true, if false then the form type doesn't exist and
        //it should.
        if(!formTypeDataProvider.getFormTypeByPropertyType(propertyType,propertyValue).contains(propertyValue.toUpperCase())){
            throw new RuntimeException("The form type with a "+propertyValue+" "+propertyType+" does not exist!");
        }
    }
    static String PAGENAME = null;
    @When("^s?he navigates to view the \"([^\"]*)\" page$")
    public void sheNavigatesToViewThePage(String pageName) throws Throwable {
        PAGENAME = pageName;
          if(!internalUser.navigateToInternalPage(pageName)){
              throw new RuntimeException("Element "+pageName+" not found! This method only works for the 3 following links"+
                      "'Forms Processing, Forms Configuration, Party Configuration'");
          }

    }

    @And("^a new \"([^\"]*)\" form type with a \"([^\"]*)\" distribution type and a frequency of \"([^\"]*)\" is \"([^\"]*)\"$")
    public void aNewFormTypeWithADistributionTypeAndAFrequencyOfIs(String formType_Title, String distributionType, String frequency, String action) {
        Object[] vals;
       vals = formType_Title.split("-");
       internalUser.createNewFormType(vals[0].toString().replace('-',' ').trim(),vals[1].toString(),distributionType,frequency,action);

    }

    static String FORMTYPE = null;

    @Then("^the \"([^\"]*)\" form type should be displayed$")
    public void theFormTypeShouldBeDisplayed(String formType){
        FORMTYPE = formType;
        if(!internalUser.checkFormType(formType,PAGENAME)){
            throw new RuntimeException("No form type was found with the code: "+formType);
        }
    }

    @Then("^the \"([^\"]*)\" form type should not be displayed$")
    public void theFormTypeShouldNotBeDisplayed(String formType) throws Throwable {
        if(internalUser.checkFormType(formType,PAGENAME)){
            throw new RuntimeException("The form type was found with the code: "+formType+". It should not have saved due to missing fields.");
        }
    }

    @Then("^an error message should be displayed with text \"([^\"]*)\"$")
    public void anErrorMessageShouldBeDisplayedWithText(String err) throws Throwable {
        //Error message should reveal!

        throw new PendingException("Error Message should display, because that form Type already exist! Msg: "+err);
    }

    @Then("^s?he view the \"([^\"]*)\" forms \"([^\"]*)\"$")
    public void sheViewTheForms(String formType, String action) throws Throwable {
        LOGGER.info(formType+"  :  "+action+"  :  "+ PAGENAME);
        if(!internalUser.viewFormType(formType,action, PAGENAME)){
            throw new RuntimeException("Error occured while trying to click on "+action+" for the Form type "+formType);
        }
    }

    @And("Edit the form type \"([^\"]*)\" to \"([^\"]*)\"$")
    public void EditTheFormTypeTo(String formTypeField, String fieldValue) throws Throwable {
            internalUser.editFormType(formTypeField,fieldValue);
    }

    @When("^s?he is done with \"([^\"]*)\"$")
    public void sheIsDoneWith(String action) throws Throwable {
            internalUser.clickEditOrCancelForFormType(action);
    }

    @And("^the form type has a release count of \"([^\"]*)\"$")
    public void theFormTypeHasAReleaseCountOf(String releaseCount) throws Throwable {
       LOGGER.info("Checking form Type release Count!");
        if(!internalUser.checkReleaseCount(FORMTYPE,releaseCount)){
            throw new RuntimeException("The amount of releases for "+FORMTYPE+" does not match the amount needed for this scenario!");
        }
    }

    @And("^\"([^\"]*)\" to delete the \"([^\"]*)\" form type$")
    public void toDeleteTheFormType(String action, String formType) throws Throwable {
        LOGGER.info("Attempting to delete form type "+ formType);
        internalUser.deleteFormType();
        internalUser.clickCancelOrDelete(action);
    }

    @Then("^an alert message should display with a message of \"([^\"]*)\"$")
    public void anAlertMessageShouldDisplayWithAMessageOf(String message) throws Throwable {
        if(!internalUser.checkEditErrorMessage(message)){
            throw new RuntimeException("The message in the confirmation block does not equal '"+message+"'");
        }
    }

    @And("^the \"([^\"]*)\" of \"([^\"]*)\" should now be \"([^\"]*)\"$")
    public void theOfShouldNowBe(String field, String code, String value) throws Throwable {
        if(!internalUser.confirmEditedValue(field,value)){
            throw new RuntimeException("The field "+field+" did not update correctly to "+value+".");
        }
    }

    @Given("^there is currently no form types$")
    public void thereIsCurrentlyNoFormTypes() throws Throwable {
       //Here we should make an jcr request to see if there are any form types in the jcr, in this scenario there should be none.

    }

    @Then("^no form types should be displayed$")
    public void noFormTypesShouldBeDisplayed() throws Throwable {
       //Checking if any form types are displayed in the forms config page.
        if(!internalUser.checkFormType("","forms configuration")){
            throw new RuntimeException("There is form types and there should have been none in this case!");
        }
    }

    @Given("^there is a \"([^\"]*)\" form type with a \"([^\"]*)\" of \"([^\"]*)\"$")
    public void thereIsAFormTypeWithAOf(String formType, String propertyType, String propertyValue) throws Throwable {
        //Here we should make an jcr request to see if there are any form types in the jcr, in this scenario there should be none.
        if(!formTypeDataProvider.getFormTypeByPropertyType(propertyType,propertyValue).contains(formType)){
            throw new RuntimeException("The "+formType+" with a "+propertyValue+" "+propertyType+" does not exist!");
        }
    }

    @Then("^the \"([^\"]*)\" form type with a \"([^\"]*)\" of \"([^\"]*)\" should be displayed$")
    public void theFormTypeWithAOfShouldBeDisplayed(String formType, String propertyType, String propertyValue) throws Throwable {
            //Here we should check if the form type is shown inside of the table.
        if(!internalUser.checkFormTypeByPropertyType(formType,propertyType,propertyValue)){
            throw new RuntimeException("There is no "+formType+" form type with a "+propertyValue+" "+propertyType);
        }

    }

    @And("^start to create a new release with a \"([^\"]*)\" of \"([^\"]*)\"$")
    public void startToCreateANewReleaseWithAOf(String releaseField, String fieldValue) throws Throwable {
       //Here we should click on the create new release button and enter the necessary fields.
        internalUser.clickCreateRelease();
        internalUser.enterFieldsForRelease(releaseField,fieldValue);
        internalUser.clickCreateOrCancelForRelease("create");

    }
    static String PERIODVALUE = "";
    static String STATUSVALUE = "";
    @Then("^the new release should display with a \"([^\"]*)\" of \"([^\"]*)\" and \"([^\"]*)\" of \"([^\"]*)\"$")
    public void theNewReleaseShouldDisplayWithAOfAndOf(String releasePeriod, String periodValue, String releaseStatus, String statusValue) throws Throwable {
       //Here we should check if the new created release is shown in the table with its exact data.
        PERIODVALUE = periodValue;
        STATUSVALUE = statusValue;
        if(!internalUser.confirmNewCreatedRelease(periodValue,statusValue)){
            throw new RuntimeException("The Release with a "+releasePeriod+" of "+periodValue+" was not created successfully!");
        }
    }

    @Then("^the new release should not display with a \"([^\"]*)\" of \"([^\"]*)\" and \"([^\"]*)\" of \"([^\"]*)\"$")
    public void theNewReleaseShouldNotDisplayWithAOfAndOf(String releasePeriod, String periodValue, String releaseStatus, String statusValue) throws Throwable {
        if(internalUser.confirmNewCreatedRelease(periodValue,statusValue)){
            throw new RuntimeException("The Release with a "+releasePeriod+" of "+periodValue+" was created and should not have!");
        }
    }

    @And("^the \"([^\"]*)\" form type contains a release in the \"([^\"]*)\" state with a Period of \"([^\"]*)\"$")
    public void theFormTypeContainsAReleaseInTheStateWithAPeriodOf(String formType, String status, String releasePeriod) throws Throwable {
        if(!formReleaseDataProvider.getFormReleaseByDisplayName(formType,releasePeriod).contains(status)){
            throw new RuntimeException("There was no release found with a displayName of "+releasePeriod+" in the "+status+" state");
        }
    }

    @When("^she view the detail of the release$")
    public void sheViewTheDetailOfTheRelease() throws Throwable {
        if(!internalUser.clickReleaseDetail(PERIODVALUE,STATUSVALUE)){
            throw new RuntimeException("Error while trying to click on the release detail button for the form release "+PERIODVALUE);
        }
    }

    @And("^choose to \"([^\"]*)\" the release$")
    public void chooseToTheRelease(String action) throws Throwable {
       internalUser.clickActionForRelease(action);
    }


    @And("^\"([^\"]*)\" the changes made to the \"([^\"]*)\"$")
    public void theChangesMadeToThe(String action, String field) throws Throwable {

        internalUser.clickEditOrCancelForRelease(action);
    }
}
