package za.co.resbank.pages.Internal;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DefaultUrl("")
public class InternalFormConfigConfigure extends PageObject {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @FindBy(xpath = "//*[@id=\"btn-delete-form-type\"]") WebElementFacade btnDelete;
    @FindBy(xpath = "//*[@id=\"btn-delete-form-type-confirm\"]") WebElementFacade btnConfirmDelete;
    @FindBy(xpath = "//*[@id=\"btn-delete-form-type-cancel\"]") WebElementFacade btnCancelDelete;
    @FindBy(xpath = "//*[@id=\"btn-acknowledge-form-type-delete\"]") WebElementFacade btnDeleteOkay;

    public void clickDeleteFormType(){
        btnDelete.click();
    }

    public void clickDeleteOrCancel(String action){
        if(action.equalsIgnoreCase("confirm")){
            btnConfirmDelete.click();
            btnDeleteOkay.click();
        }else if(action.equalsIgnoreCase("cancel")){
            btnCancelDelete.click();
        }
    }

    @FindBy(xpath = "//*[@id=\"form-type-code\"]") WebElementFacade code;
    @FindBy(xpath = "//*[@id=\"form-type-title\"]") WebElementFacade title;
    @FindBy(xpath = "//*[@id=\"btn-edit-form-type\"]") WebElementFacade btnEditFormType;
    @FindBy(xpath = "//*[@id=\"edited-confirm-modal\"]/div/div/div[2]") WebElementFacade editConfirmationMessage;
    @FindBy(xpath = "//*[@id=\"btn-edit-form-type-confirm\"]") WebElementFacade btnEditForFormType;
    @FindBy(xpath = "//*[@id=\"btn-edit-form-type-cancel\"]") WebElementFacade btnCancelforEditForFormType;
    @FindBy(xpath = "//*[@id=\"btn-acknowledge-form-type-edited\"]") WebElementFacade btnEditOkayForFormType;

    public void editFormType(String formTypeField, String fieldValue){
        WebElement distributionBox;
        WebElement frequencyBox;
        Select select = null;
        btnEditFormType.click();
        if(formTypeField.equalsIgnoreCase("code")){
            code.clear();
            code.type(fieldValue);
        }else if(formTypeField.equalsIgnoreCase("title")){
            title.clear();
            title.type(fieldValue);
        }else if(formTypeField.equalsIgnoreCase("release_frequency")){
            frequencyBox = this.getDriver().findElement(By.xpath("//*[@id=\"release-frequency\"]"));
            select = new Select(frequencyBox);
            select.selectByValue(fieldValue);
        }else if(formTypeField.equalsIgnoreCase("distribution_type")){
            distributionBox = this.getDriver().findElement(By.xpath("//*[@id=\"distribution-type\"]"));
            select = new Select(distributionBox);
            select.selectByValue(fieldValue);
        }
    }

    public void clickEditOrCancelForFormType(String action){
        if(action.contains("edit")){
            btnEditForFormType.click();
        }else if(action.contains("cancel")){
            btnCancelforEditForFormType.click();
        }
    }

    public String getEditErrorMessage(){
        LOGGER.info("Getting edit confirmation message. Msg: "+ editConfirmationMessage.getText());
        return editConfirmationMessage.getText();
    }

    public void clickEditOk(){
        if(btnEditOkayForFormType.isDisplayed()){
            btnEditOkayForFormType.click();
        }
    }

    public String getUpdatedFieldValue(String field){
        String val;
        if(field.equalsIgnoreCase("title")){
            WebElement fieldElement = this.getDriver().findElement(By.xpath("//*[@id=\"view-form-type-info\"]/div/div[2]/label[4]"));
            val =  fieldElement.getText();
            LOGGER.info("Getting value of title : "+ val);
            return val;

        }else if(field.equalsIgnoreCase("release_frequency")){
            val = this.getDriver().findElement(By.xpath("//*[@id=\"view-form-type-info\"]/div/div[2]/label[10]")).getText();
            LOGGER.info("Getting value of the release frequency : "+val);
            return val;
        }else if(field.equalsIgnoreCase("distribution_type")){
            val = this.getDriver().findElement(By.xpath("//*[@id=\"view-form-type-info\"]/div/div[2]/label[8]")).getText();
            LOGGER.info("Getting value of the distribution type : "+val);
            return val;
        }else if(field.equalsIgnoreCase("code")){
            val = this.getDriver().findElement(By.xpath("//*[@id=\"view-form-type-info\"]/div/div[2]/label[2]")).getText();
            LOGGER.info("Getting value of code : "+val);
            return val;
        }
        return "not found!";
    }

}
