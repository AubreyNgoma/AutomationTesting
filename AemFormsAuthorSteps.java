package za.co.resbank.serenitysteps;

import net.thucydides.core.annotations.Step;
import za.co.resbank.pages.*;
import za.co.resbank.forms.testing.utils.FormType;
import za.co.resbank.pages.AEM.AemAuthorFormsAndDocumentsPage;
import za.co.resbank.pages.AEM.AemAuthorStartPage;
import za.co.resbank.pages.External.ExternalC23Form;
import za.co.resbank.utils.Component;

public class AemFormsAuthorSteps {


    private T1FormAuthor t1Author;
    private ExternalC23Form C23Author;
    private AemAuthorStartPage aemAuthorStartPage;
    AemAuthorFormsAndDocumentsPage aemFormsAndDoc;
    private FormType formType = null;


    @Step
    public void is_on_aem_sarb_web_interface_page() {
        aemAuthorStartPage.clickForm();
        aemAuthorStartPage.clickFormsAndDocuments();
        aemAuthorStartPage.clickSarbFormsWebInterface();
    }



    @Step
    public void clickFormInCreateList(String formType)
    {
      aemAuthorStartPage.clickFormTypeInCreateList(formType);
    }

    @Step
    public void clickERSDSurvey(){
        aemAuthorStartPage.clickErsdSurvey();
    }
    @Step
    public void clickSarbBlankForm()
    {
        aemAuthorStartPage.clickSarbBlankForm();
    }
    @Step
    public void setTitle(String title){
        aemAuthorStartPage.setTitle(title);
    }
    @Step
    public void clickForms(){ aemAuthorStartPage.clickForm(); }
    @Step
    public void edits_the_form(String form) {

        formType = FormType.valueOf(form.toUpperCase());
        switch (formType){
            case C23:
                C23Author.open();
                break;
                default:
                    break;
        }
    }

    @Step
    public void previews_the_form(){
        aemAuthorStartPage.preview_form();
    }

    @Step
    public void see_form_design(){
        aemAuthorStartPage.see_form();
    }

    @Step
    public  void opens_components_list(){aemAuthorStartPage.search_component_exists();}

    @Step
    public boolean getFormaAndDocumentsButton(String btnText){
        if(btnText.equalsIgnoreCase(aemFormsAndDoc.getBtnText())){
            return true;
        }
        return false;
    }

    @Step
    public void confirms_component_is_on_the_list(String component) {

        Component comps = null;
        if (component.equals("SARB Save button")) {
            comps = Component.SAVE_BUTTON;
        }
        if (component.equals("SARB submit button")) {
            comps = Component.SUBMIT_BUTTON;
        }
        if (component.equals("SARB Validate button")) {
            comps = Component.VALIDATE_BUTTON;
        }

        switch (comps) {
            case SAVE_BUTTON:
                aemAuthorStartPage.confirm_saveButton_On_components();
                break;
            case SUBMIT_BUTTON:
                aemAuthorStartPage.confirm_submitButton_On_components();
                break;
            case VALIDATE_BUTTON:
                aemAuthorStartPage.confirm_validateButton_on_components();
                break;
        }
    }

    @Step
    public void adds_component_on_form_toolbar(String component){

        Component comps = null;
        if (component.equals("SARB Save button")) {
            comps = Component.SAVE_BUTTON;
        }
        if (component.equals("SARB submit button")) {
            comps = Component.SUBMIT_BUTTON;
        }
        if (component.equals("SARB Validate button")) {
            comps = Component.VALIDATE_BUTTON;
        }

        switch (comps) {
            case SAVE_BUTTON:
                aemAuthorStartPage.add_save_button_to_form();
                break;
            case SUBMIT_BUTTON:
                aemAuthorStartPage.add_submit_button_to_form();
                break;
            case VALIDATE_BUTTON:
                aemAuthorStartPage.add_validate_button_to_form();
                break;
                default:
                    break;
        }
    }

    @Step
    public  void confirms_first_added_component_is_available_on_preview(String component){
        Component comps = null;
        if (component.equals("SARB Save button")) {
            comps = Component.SAVE_BUTTON;
        }
        if (component.equals("SARB submit button")) {
            comps = Component.SUBMIT_BUTTON;
        }
        if (component.equals("SARB Validate button")) {
            comps = Component.VALIDATE_BUTTON;
        }

        switch (comps) {
            case SAVE_BUTTON:
                aemAuthorStartPage.confirm_save_appears_on_preview();
                break;
            case SUBMIT_BUTTON:
                aemAuthorStartPage.confirm_submit_appears_on_preview();
                break;
            case VALIDATE_BUTTON:
                aemAuthorStartPage.confirm_validate_appears_on_preview();
                break;
            default:
                break;
        }

    }

    @Step
    public void confirms_component_is_available_on_form_toolbar(String component){

        Component comps = null;
        if (component.equals("SARB Save button")) {
            comps = Component.SAVE_BUTTON;
        }
        if (component.equals("SARB submit button")) {
            comps = Component.SUBMIT_BUTTON;
        }
        if (component.equals("SARB Validate button")) {
            comps = Component.VALIDATE_BUTTON;
        }

        switch (comps) {
            case SAVE_BUTTON:
                aemAuthorStartPage.check_save_on_form_toolbar();
                break;
            case SUBMIT_BUTTON:
                aemAuthorStartPage.check_submit_on_form_toolbar();
                break;
            case VALIDATE_BUTTON:
                aemAuthorStartPage.check_validate_on_form_toolbar();
                break;
            default:
                break;
        }

    }

    @Step
    public  void confirms_second_added_component_is_available_on_preview(String component){
        Component comps = null;
        if (component.equals("SARB Save button")) {
            comps = Component.SAVE_BUTTON;
        }
        if (component.equals("SARB submit button")) {
            comps = Component.SUBMIT_BUTTON;
        }
        if (component.equals("SARB Validate button")) {
            comps = Component.VALIDATE_BUTTON;
        }

        switch (comps) {
            case SAVE_BUTTON:
                aemAuthorStartPage.confirm_second_save_appears_on_preview();
                break;
            case SUBMIT_BUTTON:
                aemAuthorStartPage.confirm_second_submit_appears_on_preview();
                break;
            case VALIDATE_BUTTON:
                aemAuthorStartPage.confirm_second_validate_appears_on_preview();
                break;
            default:
                break;
        }

    }


}
