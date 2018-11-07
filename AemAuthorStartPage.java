package za.co.resbank.pages.AEM;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main authoring page in AEM
 * 
 * @author gregf
 *
 */
@DefaultUrl("http://localhost:4502/aem/forms.html/content/dam/formsanddocuments/sarb-forms-web-interface")
public class AemAuthorStartPage extends PageObject {
    private  AemCreateAf createBlankAdaptiveForm = new AemCreateAf();
    Actions act;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    WebDriverWait wait;

    //here we have a problem, the id of the xpath renews each time the page refreshes which leads to chaos.
    public void clickFormTypeInCreateList(String formType){
        createBlankAdaptiveForm.open();
    }

    @FindBy(xpath = "/html/body/form/div/coral-panelstack/coral-panel[1]/coral-panel-content/div/div/div[2]/coral-masonry/coral-masonry-item[5]")
    WebElementFacade sarbBlankFormCard;

    @FindBy(xpath = "//*[@id=\"createguidewizardnextbutton\"]/coral-button-label")
    WebElementFacade btnNext;

    public void clickSarbBlankForm()
    {
        //Broken, due to hovering affect
        Actions action = new Actions(this.getDriver());
        WebElement we = this.getDriver().findElement(By.xpath("/html/body/form/div/coral-panelstack/coral-panel[1]/coral-panel-content/div/div/div[2]/coral-masonry/coral-masonry-item[5]"));
        action.moveToElement(we).moveToElement(this.getDriver().findElement(By.xpath("/html/body/form/div/coral-panelstack/coral-panel[1]/coral-panel-content/div/div/div[2]/coral-masonry/coral-masonry-item[5]"))).click().build().perform();
         btnNext.click();
    }

    @FindBy(xpath = "/html/body/form/div/coral-panelstack/coral-panel[1]/coral-panel-content/div/div/div[2]/coral-masonry/coral-masonry-item[2]/coral-card")
    WebElementFacade ersdSurveyCard;

    public void clickErsdSurvey()
    {
        //Broken, due to hovering affect
         ersdSurveyCard.click();
         btnNext.click();
    }

    @FindBy(xpath = "//*[@id=\"fm-id-title\"]")
    WebElementFacade titleField;
    public void setTitle(String title){
        titleField.type(title);
    }


    //navigating to the sarb web form interface///////
    @FindBy(xpath = "//*[@id=\"globalnav-start-home-collection\"]/coral-masonry-item[6]/div/coral-icon[1]")
    WebElementFacade forms;

    @FindBy(xpath = "//*[@id=\"globalnav-start-home-collection\"]/coral-masonry-item[1]/div")
    WebElementFacade formsAndDocuments;

    @FindBy(xpath = "//*[@id=\"granite-shell-content\"]/div[2]/div/div[2]/coral-masonry/coral-masonry-item/a/coral-card/div/coral-card-content")
    WebElementFacade  sarbFormWebInterface;

    public void clickSarbFormsWebInterface(){
        sarbFormWebInterface.click();
    }

    public void clickForm(){
        forms.click();
    }

    public void clickFormsAndDocuments(){
        formsAndDocuments.click();
    }
    ///End      //


    //navigating to the preview page to preview the t1 form and confirming the form template is available
    @FindBy(xpath = "//*[@id=\"Content\"]/div[1]/coral-actionbar/coral-actionbar-secondary/coral-actionbar-item[4]/button/coral-button-label")
    WebElementFacade previewButton;

    public void preview_form(){
        previewButton.click();
    }

    @FindBy(xpath = "//*[@id=\"OverlayWrapper\"]/div[2]/div/div[1]")
    WebElementFacade form_design;

    public  void see_form(){
        form_design.isPresent();
    }
    //End

    //Searching for components in the components section
    @FindBy(xpath =  "//*[@id=\"Content\"]/div[1]/coral-actionbar/coral-actionbar-primary/coral-actionbar-item[1]/button")
    WebElementFacade toggleSidePanel;

    @FindBy(id =  "coral-id-7")
    WebElementFacade componentsButton;

    @FindBy(css ="#coral-id-118 > span")
    WebElementFacade dropDownList;

    @FindBy(css ="#coral-id-119 > coral-selectlist-item:nth-child(3)")
    WebElementFacade sarbButton;

    public void search_component_exists(){
        toggleSidePanel.click();
        componentsButton.click();
        dropDownList.click();
        sarbButton.click();
    }
    ///

    //confirming that the component is available
    @FindBy(xpath =  "//*[@id=\"coral-id-144\"]/coral-panel-content/div/div[2]/coral-list/coral-list-item[2]/div/div/coral-list-item-content/coral-columnview-item/coral-columnview-item-content/div[1]")
    WebElementFacade saveButtonOnComponents;

    @FindBy(xpath =  "//*[@id=\"coral-id-144\"]/coral-panel-content/div/div[2]/coral-list/coral-list-item[3]/div/div/coral-list-item-content/coral-columnview-item/coral-columnview-item-content/div[1]")
    WebElementFacade submitButtonOnComponents;

    @FindBy(xpath =  "//*[@id=\"coral-id-144\"]/coral-panel-content/div/div[2]/coral-list/coral-list-item[4]/div/div/coral-list-item-content/coral-columnview-item/coral-columnview-item-content/div[1]")
    WebElementFacade validateButtonOnComponents;

    public boolean confirm_saveButton_On_components(){
        return saveButtonOnComponents.isPresent();
    }

    public boolean confirm_submitButton_On_components(){
        return submitButtonOnComponents.isPresent();
    }

    public boolean confirm_validateButton_on_components(){return validateButtonOnComponents.isPresent(); }

    //End of confirmation

    //Adding Sarb components on the form/////////////////////////

    @FindBy(css = "#OverlayWrapper > div.cq-Overlay.cq-Overlay--component > div > div:nth-child(3) > div")
    WebElementFacade formToolbar;

    @FindBy(xpath = "//*[@id=\"EditableToolbar\"]/button[4]/coral-icon")
    WebElementFacade addToolbarButton;


    @FindBy(css = "body > coral-dialog > div.coral-Dialog-wrapper > coral-dialog-content > coral-selectlist > coral-selectlist-group:nth-child(2) > coral-selectlist-item:nth-child(2)")
    WebElementFacade addSaveButton;

    @FindBy(css = "body > coral-dialog.coral-Dialog.InsertComponentDialog.coral-Dialog--closable.is-open > div.coral-Dialog-wrapper > coral-dialog-content > coral-selectlist > coral-selectlist-group:nth-child(2) > coral-selectlist-item:nth-child(3)")
    WebElementFacade addSubmitButton;

    @FindBy(css = "body > coral-dialog > div.coral-Dialog-wrapper > coral-dialog-content > coral-selectlist > coral-selectlist-group:nth-child(2) > coral-selectlist-item:nth-child(4)")
    WebElementFacade addValidateButton;

    public void add_save_button_to_form(){
        formToolbar.click();
        addToolbarButton.click();
        addSaveButton.click();

    }

    public void add_submit_button_to_form() {
        formToolbar.click();
        addToolbarButton.click();
        addSubmitButton.click();
    }

    public void add_validate_button_to_form(){
        formToolbar.click();
        addToolbarButton.click();
        addValidateButton.click();
    }

    //End of adding components/////////////////////////////////////

    //confirming first added component appears on the preview page

    @FindBy(css = "#guideContainer-rootPanel-toolbar-saveguidedraft___widget")
    WebElementFacade first_save_on_preview;

    @FindBy(css = "#sarb_submit")
    WebElementFacade first_submit_on_preview;

    @FindBy(css = "#sarb_validate")
    WebElementFacade first_validate_on_preview;

    public boolean confirm_save_appears_on_preview(){return first_save_on_preview.isPresent();}
    public boolean confirm_submit_appears_on_preview(){return first_submit_on_preview.isPresent();}
    public boolean confirm_validate_appears_on_preview(){return first_validate_on_preview.isPresent();}

    //End

    //Checking if form toolbar has a component before adding the second one

    @FindBy(css = "l#OverlayWrapper > div.cq-Overlay.cq-Overlay--component > div > div:nth-child(3) > div > div:nth-child(3)")
    WebElementFacade check_save_on_form;

    @FindBy(css = "#OverlayWrapper > div.cq-Overlay.cq-Overlay--component > div > div:nth-child(3) > div > div:nth-child(2)")
    WebElementFacade check_submit_on_form;

    @FindBy(css = "#OverlayWrapper > div.cq-Overlay.cq-Overlay--component > div > div:nth-child(3) > div > div.cq-Overlay.cq-Overlay--component.cq-draggable.cq-droptarget.is-selected.is-active > span")
    WebElementFacade check_validate_on_form;

    public boolean check_save_on_form_toolbar(){return check_save_on_form.isPresent();}
    public boolean check_submit_on_form_toolbar(){return check_submit_on_form.isVisible();}
    public boolean check_validate_on_form_toolbar(){return check_validate_on_form.isPresent();}

    //End

    //confirming Second added component appears on the preview page
    @FindBy(css = "#guideContainer-rootPanel-toolbar-saveguidedraft___widget")
    WebElementFacade second_save_on_preview;

    @FindBy(css = "#sarb_submit")
    WebElementFacade second_submit_on_preview;

    @FindBy(css = "#sarb_validate")
    WebElementFacade second_validate_on_preview;

    public boolean confirm_second_save_appears_on_preview(){return second_save_on_preview.isPresent();}
    public boolean confirm_second_submit_appears_on_preview(){return second_submit_on_preview.isPresent();}
    public boolean confirm_second_validate_appears_on_preview(){return second_validate_on_preview.isPresent();}

    //End

}