package za.co.resbank.pages.Internal;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("")
public class InternalFormConfigProcessing extends PageObject {

    @FindBy(xpath = "//*[@id=\"btn-create-release\"]") WebElementFacade btnCreateRelease;
    @FindBy(xpath = "//*[@id=\"btn-edit-release-confirm\"]") WebElementFacade btnConfirmEditForRelease;
    @FindBy(xpath = "//*[@id=\"btn-edit-release-cancel\"]") WebElementFacade btnCancelEditForRelease;
    @FindBy(xpath = "//*[@id=\"btn-acknowledge-release-edited\"]") WebElementFacade btnOkayEditForRelease;
    @FindBy(xpath = "//*[@id=\"edited-confirm-modal\"]/div/div/div[2]") WebElementFacade editReleaseMessage;
    @FindBy(xpath = "//*[@id=\"release-display-name\"]") WebElementFacade fieldReleaseDisplayName;
    @FindBy(xpath = "//*[@id=\"btn-create-release-confirm\"]") WebElementFacade btnReleaseCreate;
    @FindBy(xpath = "//*[@id=\"btn-create-release-cancel\"]") WebElementFacade btnReleaseCancel;
    @FindBy(xpath = "//*[@id=\"btn-edit-release\"]") WebElementFacade btnEditForRelease;

    public void clickCreateRelease(){
        if(btnCreateRelease.isDisplayed()){
            btnCreateRelease.click();
        }
    }

    //todo add different fields
    public void enterValuesForRelease(String field, String value){
        fieldReleaseDisplayName.type(value);
    }

    public void clickCreateOrCancelForRelease(String actionType){

        if(actionType.equalsIgnoreCase("create")){
            btnReleaseCreate.click();
        }else{
            btnReleaseCancel.click();
        }
    }

    public void clickEditForRelease(){
        if(btnEditForRelease.isDisplayed()){
            btnEditForRelease.click();
        }
    }

    public String getEditReleaseConfirmedMessage(){
        return editReleaseMessage.getText();
    }
    public void clickConfirmEditForRelease(){
        if(btnConfirmEditForRelease.isDisplayed()){
            btnConfirmEditForRelease.click();
        }
    }

    public void clickCancelEditForRelease(){
        if(btnCancelEditForRelease.isDisplayed()){
            btnCancelEditForRelease.click();
        }
    }

    public void clickEditOkayForRelease(){
        if(btnOkayEditForRelease.isDisplayed()){
            btnOkayEditForRelease.click();
        }
    }
}
