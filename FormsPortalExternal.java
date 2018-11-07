package za.co.resbank.cucumbersteps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.jruby.javasupport.Java;
import za.co.resbank.forms.testing.steps.cucumber.FormUserSteps;
import za.co.resbank.forms.testing.steps.serenity.FormUserEndUserSteps;
import za.co.resbank.serenitysteps.FormsPortalEndUserSteps;


public class FormsPortalExternal{

	@Steps
	FormsPortalEndUserSteps testUser;

    @Steps
    FormUserEndUserSteps genericUser;

    private String tab = null;
    private String dataSubject = null;
    public static String PERIOD = "2017-02";
    String firstUrl = "";
    String[] url = null;
    String[] url2 = null;


    //Ready form instance Steps Will start here
    @Then("^there should be (\\d+) forms in the \"([^\"]*)\" tab$")
    public void there_should_be_forms_in_the_tab(int numForms, String tab) throws Throwable {
        if (!testUser.checkAmountZero(tab)) {
            throw new Exception("There are currently more than 0 forms in the " + tab + " tab");
        }
    }
    //Ready form instance Steps Will end here

    //In Progress Steps Will start here
    @And("^there is currently no forms inside of the \"([^\"]*)\" status$")
    public void there_is_currently_no_forms_inside_of_the_status(String status) throws Throwable {
           //1. Query JCR to check if any forms exists inside of the JCR status Status
           //The query to jcr is not needed, because the user created for this wont have any permission to see the forms, meaning the tables will always be empty
           //2. If any exists, it should be removed from the JCR.
    }







    @Then("^he should see an entry for a \"([^\"]*)\" form for the \"([^\"]*)\"$")
    public void he_should_see_an_entry_for_a_form_for_the(String formType, String dataSubject) throws Throwable {
               //This object initialization is mandatory, because it depends on the status of the current scenario, the method is implemented with a switch statement, so if its
               // in the ready status, it will return all the forms inside of the ready state.
                this.dataSubject = dataSubject;
                tab = FormUserSteps.STATUS;
       try{
        if(!genericUser.checkDataSubjects(dataSubject,tab))
        {
            throw new Exception("There are currently no "+dataSubject+" data subject listed inside the "+tab+" status!");
        }
       }catch (NullPointerException ex){
           throw new Exception("There is no forms selected inside of the table. Please add data before trying again");
       }
    }
    //In Progress Steps Will end here

    //Submit form steps will start here
    @And("^the \"([^\"]*)\" of submission is \"([^\"]*)\" the \"([^\"]*)\"$")
    public void theOfSubmissionIsThe(String date,String action, String dueDate) throws Throwable {
          if(!testUser.compareNowVsDueDate(FormUserSteps.DATASUBJECT,action)){
              throw new Exception("Date of submission is not "+action+" the actual Due date");
          }
    }

    @And("^s?he \"([^\"]*)\" the listed \"([^\"]*)\" form for \"([^\"]*)\"$")
    public void submitsTheListedFormFor(String mode, String formType, String dataSubject) throws Throwable {
        tab = FormUserSteps.STATUS;
        testUser.c23Submit();
    }

    @Then("^the submission of the form should be successful$")
    public void theSubmissionOfTheFormShouldBeSuccessful() throws Throwable {

    }
    //End of form submission

    //16.09, 16.10 working on released form instance Steps Will start here
    @And("^s?he (.*) editing a (.*) form for the (.*)$")
    public void EditingAFormForThe(String mode,String formType, String dataSubject) throws Throwable {
           tab = FormUserSteps.STATUS;
        if(!genericUser.checkDataSubjects(dataSubject,tab))
        {
        testUser.editViewSubmit(mode, dataSubject, tab);
        }
        else{
            throw new Exception("Something went wrong with clicking on " +mode);
        }
    }
//c_01_002_0001
    //Here we will make changes to the c23 form, it may contain a lot of fields.
    @And("^makes some changes to the form$")
    public void makesSomeChangesToTheForm() throws Throwable {
        //Here we will click a button that will automatically fill the fields of the c23 form.
            testUser.changeC23Form();
    }

    @And("^saves the form$")
    public void savesTheForm() throws Throwable {
        firstUrl = testUser.checkFormVersion();
         url = firstUrl.split("/");
              testUser.C23Save();
    }

    @Then("^the form version will be incremented by (\\d+)$")
    public void theFormVersionWillBeIncrementedBy(int number) throws Throwable {
       String tmpUrl = testUser.checkFormVersion();
        url2 = tmpUrl.split("/");
        if(url[11].equals(url2[11])){
            throw new Exception("versions are equal - meaning it didn't update (Increment)");
        }
    }

    @When("^s?he \"([^\"]*)\" the form$")
    public void heTheForm(String closes) throws Throwable {
       testUser.C23Close();
    }
    //working on released form instance Steps Will end here

    //Open in progress form instance Steps Will Start here

    @And("^s?he opens the listed \"([^\"]*)\" form for the \"([^\"]*)\" in \"([^\"]*)\" mode$")
    public void opens_the_listed_form_for_the_in_mode(String formType, String dataSubject, String mode) throws Throwable {
        try{
            tab = FormUserSteps.STATUS;
        if(!testUser.editViewSubmit(mode,dataSubject,tab)){
            throw new RuntimeException(mode+"  :  "+dataSubject+"  :  "+tab+"  :  "+FormsPortalExternal.PERIOD);
          }
        }catch (IndexOutOfBoundsException ex){
            throw new Exception("Tab is empty. Msg: "+ex.getMessage());
        }
    }
    //Open in progress form instance Steps Will end here
    @Then("^selects \"([^\"]*)\" inside of the confirmation page$")
    public void selects_inside_of_the_confirmation_page(String option) throws Throwable {
        if(option.equalsIgnoreCase("accept")){testUser.click_Confirm_Submission();testUser.click_Okay_After_Submission();}
        if(option.equalsIgnoreCase("cancel")){testUser.click_Cancel_Submission();}
    }
    @And("^the confirmation page should disappear without submitting the changes$")
    public void the_confirmation_page_should_disappear_without_submitting_the_changes() throws Throwable {
        //the confirmation box should just disappear
    }
     //Submitted tab scenarios will start here S16.15
     @And("^the \"([^\"]*)\" form named \"([^\"]*)\" should have a version more than (\\d+)$")
     public void the_form_named_should_have_a_version_more_than(String formType, String dataSubject, int version) throws Throwable {
         String[] url = testUser.getUrl().split("/");
         if((double)version >= Double.parseDouble(url[11]))
         {
             throw new Exception("The Managing Entity only have version:"+version);
         }
     }


    //Submitted tab scenarios will end here
    @And("^the \"([^\"]*)\" button should not be displayed$")
    public void the_button_should_not_be_displayed(String buttonType) throws Throwable {
        //Here a certain button needs to be located in the tabs.
        if(!genericUser.checkButtonOfType(buttonType,tab, this.dataSubject, FormUserSteps.PERMISSIONLEVEL)){
            throw new RuntimeException("The action is displayed when it shouldn't have been displayed");
        }
        System.out.println(genericUser.checkButtonOfType(buttonType,tab,this.dataSubject,FormUserSteps.PERMISSIONLEVEL));
    }

    @And("^the \"([^\"]*)\" button should be displayed$")
    public void the_button_should_be_displayed(String buttonType) throws Throwable {
     //   Here a certain button needs to be located in the tabs.
        if(!genericUser.checkButtonOfType(buttonType,tab, this.dataSubject, FormUserSteps.PERMISSIONLEVEL)){
            throw new RuntimeException("The action is not displayed when it should've been displayed");
        }
    }
    public static String formTypes = "";

    @And("^\"([^\"]*)\" editing a \"([^\"]*)\" form for the \"([^\"]*)\"$")
    public void editing_a_form_for_the(String mode, String formType, String dataSubject) throws Throwable {
          formTypes = formType;
          if(!testUser.editViewSubmit(mode,dataSubject,FormUserSteps.STATUS)){
              throw new RuntimeException(dataSubject+" could not be found inside the "+FormUserSteps.STATUS+" status!");
          }
    }


    @And("^\"([^\"]*)\" the listed \"([^\"]*)\" form for \"([^\"]*)\" with errors$")
    public void theListedFormForWithErrors(String Submit, String formType, String dataSubject) throws Throwable {

    }

    @Then("^there should be an error containing the text \"([^\"]*)\"$")
    public void thereShouldBeAnErrorContainingTheText(String errorMessage) throws Throwable {

        if(!testUser.formError()){
            throw new RuntimeException("Error message could not be found! Please make sure the c23 form contain errors for this scenario");
        }
    }
}
