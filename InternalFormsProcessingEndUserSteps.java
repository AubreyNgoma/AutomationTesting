package za.co.resbank.serenitysteps;


import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.resbank.forms.testing.data.FormReleaseDataProvider;
import za.co.resbank.forms.testing.data.PersonaDataProvider;
import za.co.resbank.forms.testing.model.Persona;
import za.co.resbank.forms.testing.model.PersonaDetail;
import za.co.resbank.forms.testing.model.UserCredential;
import za.co.resbank.pages.Internal.*;
import za.co.resbank.pages.LoginPage;
import za.co.resbank.utils.Status;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aubrey
 * Date: 6/20/18
 * Time: 2:51 PM
 *
 */



public class InternalFormsProcessingEndUserSteps {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private LoginPage internalLoginPage;
    private InternalFormsPortal intFormsPortal;
    private InternalFormsConfiguration internalFormsConfiguration;
    private InternalFormsProcessing internalFormsProcessing;
    private InternalFormConfigConfigure internalFormConfigConfigure;
    private InternalFormConfigProcessing internalFormConfigProcessing;
    private InternalPartyConfigDataSubject internalPartyConfigDataSubject;
    private InternalPartyConfigDataSubjectGroup internalPartyConfigDataSubjectGroup;
    private InternalPartyConfigManagingEntity internalPartyConfigManagingEntity;
    private InternalPartyConfigExternalUser internalPartyConfigExternalUser;
    private UserCredential userCredential;
    private FormReleaseDataProvider formReleaseDataProvider = new FormReleaseDataProvider();

    @Step
    public void enterUsername(String name){
        internalLoginPage.enter_username(name);
    }
    @Step
    public void enterPassword(String password){
        internalLoginPage.enter_password(password);
    }
    @Step
    public void submit(){
        internalLoginPage.hits_submit();
    }
    @Step
    public void is_on_the_internal_login_page() {
        internalLoginPage.open();
    }

    @Step
    public boolean successfully_logged_into_the_forms_portal(){
        return  internalLoginPage.logged_into_forms_portal();
    }

   @Step
   public PersonaDetail getPersona(Persona persona){
       PersonaDataProvider personaDataProvider = new PersonaDataProvider();
       return personaDataProvider.getPersona(persona);
   }

    @Step
    public boolean  forms_processing_page_displayed(){
        return internalLoginPage.forms_processing_page_is_displayed();
    }

    @Step
    public void clickRelease(String dataSubject) throws Exception {
        intFormsPortal.viewFormRelease(dataSubject);
    }

    //Gets all the data subject in the internal view to see if the one exists named in the feature file
    @Step
    public boolean checkFormRelease(String value) {
        List<WebElement> dataSubjects = intFormsPortal.getFormReleases();
        for(WebElement item: dataSubjects){
            if(item.getText().equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }

    @Step
    public  void goesToStatus(String status){
        Status tab = null;
        if (status.equals("CREATED")) {
            tab = Status.Created;
        }
        if (status.equals("READY")) {
            tab = Status.Ready;
        }
        if (status.equals("IN_PROGRESS")) {
            tab = Status.Created;
        }
        if (status.equals("SUBMITTED")) {
            tab = Status.Created;
        }

        switch (tab){
            case Created:
                intFormsPortal.goToCreated();
                break;
            case Ready:
                intFormsPortal.goToReady();
                break;
            case In_Progress:
                intFormsPortal.goToProgressStatus();
                break;
            case Submitted:
                intFormsPortal.goToSubmittedStatus();
                break;
                default:
                    break;
        }
    }

    //Method for the forms release section
    @Step
    public void openCloseProspone(String option)
    {
        intFormsPortal.clickOption(option);
    }

    @Step
    public void viewFormRelease(String dataSubject) throws Exception {
        intFormsPortal.viewFormRelease(dataSubject);
    }

    @Step
    public void viewFormInstances()
    {
        intFormsPortal.viewFormInstance();
    }

    @Step
    public boolean navigateToInternalPage(String pageName){
            if (pageName.equalsIgnoreCase("Forms Processing")) {
                LOGGER.info("Attempting to click on the forms processing link.");
                 internalFormsProcessing.clickFormsProcessingLink();
                    return true;

            } else if (pageName.equalsIgnoreCase("Forms Configuration")) {
                LOGGER.info("Attempting to click on the forms configuration link.");
                internalFormsConfiguration.clickFormsConfigurationLink();
                return true;

            } else if (pageName.equalsIgnoreCase("Party Configuration")) {
                LOGGER.info("Attempting to click on the party configuration link.");
               internalPartyConfigDataSubject.clickPartyConfigLink();
                    return true;
            }
            return false;
    }

    @Step
    public boolean createNewFormType(String code, String title, String distributionType,String frequency, String action){
        return internalFormsConfiguration.createNewFormType(code,title,distributionType,frequency,action);
    }

    @Step
    public boolean checkFormType(String formType, String page){
        if(page.contains("processing")){
           return internalFormsProcessing.checkFormType(formType);
        }else {
            return internalFormsConfiguration.checkFormType(formType);
        }
    }

    @Step
    public boolean checkFormTypeByPropertyType(String formType, String propertyType, String propertyValue){
        return internalFormsConfiguration.checkFormTypeByPropertyType(formType,propertyType,propertyValue);
    }

    @Step
    public boolean viewFormType(String formType, String action, String pageName){
        if(checkFormType(formType,pageName)){
            return clickOnFormTypeAction(formType,action, pageName);
        }
        return false;
    }

    @Step
    private List<WebElement> getFormTypeList(String page){
        LOGGER.info(page);
        if(page.equalsIgnoreCase("forms processing")){
            return internalFormsProcessing.getFormTypeList();
        }else if(page.equalsIgnoreCase("forms configuration")) {
            LOGGER.info("Returning list of "+page+" form types");
            return internalFormsConfiguration.getFormTypeList();
        }
        return null;
    }

    @Step
    public boolean clickOnFormTypeAction(String formType, String action, String pageName){
        int index = 0;
        LOGGER.info(formType+" : "+action);
        List<WebElement> formTypeList = getFormTypeList(pageName);
        for(WebElement items:formTypeList){
            index++;
            if(items.getText().contains(formType)&&action.contains("configuration")){
                internalFormsConfiguration.clickReleaseDetail(index);
                return true;
            }else if(items.getText().contains(formType)&&action.contains("processing")){
                internalFormsProcessing.clickReleaseDetail(index);
                return true;
            }
        }
        return false;
    }
    @Step
    public void editFormType(String formTypeField, String fieldValue){
        internalFormConfigConfigure.editFormType(formTypeField,fieldValue);
    }

    @Step
    public boolean checkEditErrorMessage(String message){
        LOGGER.info("Comparing Edit Confirmation Message");
            if(message.equalsIgnoreCase(internalFormConfigConfigure.getEditErrorMessage())&&message.contains("Form Type")){
                internalFormConfigConfigure.clickEditOk();
                return true;
        }else if(message.equalsIgnoreCase(internalFormConfigProcessing.getEditReleaseConfirmedMessage())&&message.contains("Form Release")){
                internalFormConfigProcessing.clickEditOkayForRelease();
                return true;
            }else if(message.equalsIgnoreCase(internalFormConfigConfigure.getEditErrorMessage())&&message.contains("Successfully unlinked the Data Source")){

                return true;
            }
        return false;
    }

    @Step
    public boolean confirmEditedValue(String field, String value){
        LOGGER.info("Comparing the "+field+" fields value with updated value.");
        String updatedValue = internalFormConfigConfigure.getUpdatedFieldValue(field);
        if(updatedValue.isEmpty()){
            LOGGER.error("The data bounded after editing is not displaying! Something server side is wrong. Bug Reported.");
        }
        return updatedValue.equalsIgnoreCase(value);
    }

    @Step
    public void clickEditOrCancelForFormType(String action){
        internalFormConfigConfigure.clickEditOrCancelForFormType(action.toLowerCase());
    }

    @Step
    public void deleteFormType(){
        internalFormConfigConfigure.clickDeleteFormType();
    }

    @Step
    public void clickCancelOrDelete(String action){
        internalFormConfigConfigure.clickDeleteOrCancel(action);
    }

    @Step
    public boolean checkReleaseCount(String formType, String amount){
        return internalFormsConfiguration.getFormTypeReleaseCount(formType,amount);
    }

    @Step
    public void clickCreateRelease(){
        internalFormConfigProcessing.clickCreateRelease();
    }

    @Step
    public void clickCreateOrCancelForRelease(String actionType){
        internalFormConfigProcessing.clickCreateOrCancelForRelease(actionType);
    }

    @Step
    public boolean clickReleaseDetail(String periodValue, String statusValue){
        return internalFormsConfiguration.clickOnReleaseDetail(periodValue, statusValue);
    }

    @Step
    public void enterFieldsForRelease(String releaseField, String fieldValue){
        internalFormConfigProcessing.enterValuesForRelease(releaseField,fieldValue);
    }

    @Step
    public boolean confirmNewCreatedRelease(String periodValue, String statusValue){
        return internalFormsConfiguration.checkNewCreatedRelease(periodValue,statusValue);
    }

    @Step
    public void clickActionForRelease(String action){
        if(action.equalsIgnoreCase("Edit")) {
            internalFormConfigProcessing.clickEditForRelease();
        }
    }
    @Step
    public void clickEditOrCancelForRelease(String action){
        if(action.equalsIgnoreCase("confirm")){
            internalFormConfigProcessing.clickConfirmEditForRelease();
        }else if(action.equalsIgnoreCase("decline")){
            internalFormConfigProcessing.clickCancelEditForRelease();
        }
    }
}
