package za.co.resbank.serenitysteps;


import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import za.co.resbank.cucumbersteps.FormsPortalExternal;
import za.co.resbank.forms.testing.data.PersonaDataProvider;
import za.co.resbank.forms.testing.model.Persona;
import za.co.resbank.forms.testing.model.PersonaDetail;
import za.co.resbank.forms.testing.model.UserCredential;
import za.co.resbank.pages.External.ExternalC23Form;
import za.co.resbank.pages.External.ExternalFormsPortal;
import za.co.resbank.pages.LoginPage;
import za.co.resbank.utils.TabStatuses;
import za.co.resbank.utils.dataProvider;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class FormsPortalEndUserSteps {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    //The methods created here are all for the forms portal functionality and tests, external users only.

    LoginPage login_pageObject;
    ExternalC23Form c23PageObject;
    ExternalFormsPortal formsPortal;

    private dataProvider reader = new dataProvider();
    private  List<Object> data = null;
    private UserCredential userCredential;
    private TabStatuses menu = null;



    public void goMax(){

        login_pageObject.getDriver().manage().window().maximize();
    }

    public String getUrl(){
        return formsPortal.getDriver().getCurrentUrl();
    }
    @Step
    public String checkFormVersion(){
         return formsPortal.getDriver().getCurrentUrl();
    }

    @Step
    public void is_on_external_login_page(){
        login_pageObject.open();
    }

    @Step
    public void enterEmail(String value){
        login_pageObject.enter_username(value);
    }

    @Step
    public void enterPassword(String value){
        login_pageObject.enter_password(value);

    }

    @Step
    public void submit(){
        login_pageObject.hits_submit();
    }

    @Step
    public boolean progressTabEmpty(){
        List<WebElement> amount = formsPortal.getProgressTableList();
        if(amount.size()>1){
            return false;
        }
        return true;
    }

    @Step
    public boolean isOnReadyPage(){
       return formsPortal.readyFormDisplayed();
    }



   @Step
   public boolean checkAmountZero(String tab){
       int opt = 0;
       if(tab.equals("READY")){opt=1;menu = TabStatuses.values()[opt];}
       if(tab.equals("IN_PROGRESS")){opt=2;menu = TabStatuses.values()[opt];}
       if(tab.equals("SUBMITTED")){opt=3;menu = TabStatuses.values()[opt];}
       switch (menu){
           case READY:
               List<WebElement> elements = formsPortal.getDriver().findElements(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr/td[3]"));
               return elements.size() == 0;
           case IN_PROGRESS:
               List<WebElement> forms = formsPortal.getDriver().findElements(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[3]"));
               return forms.size() != 0;
           case SUBMITTED:
               formsPortal.submittedTabClick();
               List<WebElement> instance = formsPortal.getDriver().findElements(By.cssSelector("#menu2 > table > tbody > tr > td:nth-child(3)"));
               return instance.size() != 0;
           case CREATED://todo
               return true;
           default:
               return false;
       }
   }

    @Step
    public boolean checkAmountIsLessThan(Object entity, String tab){
        int opt = 0;
        if(tab.equals("READY")){opt=1;menu = TabStatuses.values()[opt];}
        if(tab.equals("IN_PROGRESS")){opt=2;menu = TabStatuses.values()[opt];}
        if(tab.equals("SUBMITTED")){opt=3;menu = TabStatuses.values()[opt];}
        switch (menu){
            case READY:
                List<WebElement> elements = formsPortal.getDriver().findElements(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr/td[3]"));
                return elements.size() > 1 && elements.size() < 50;
            case IN_PROGRESS:
                List<WebElement> forms = formsPortal.getDriver().findElements(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[3]"));
                return (forms.size() < 50 && forms.size() > 1);
            case SUBMITTED:
                formsPortal.submittedTabClick();
                List<WebElement> instance = formsPortal.getDriver().findElements(By.cssSelector("#menu2 > table > tbody > tr > td:nth-child(3)"));
                return (instance.size() < 50 && instance.size() > 1);
            default:
               return false;
        }
    }



    @Step
    public boolean checkAmountIsMoreThan(Object entity, String tab)
    {
        int opt = 0;
        if(tab.equals("READY")){opt=1;menu = TabStatuses.values()[opt];}
        if(tab.equals("IN_PROGRESS")){opt=2;menu = TabStatuses.values()[opt];}
        if(tab.equals("SUBMITTED")){opt=3;menu = TabStatuses.values()[opt];}
        switch (menu)
        {
            case READY:
                List<WebElement> elements = formsPortal.getDriver().findElements(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr/td[3]"));
                return elements.size() > 50;
            case IN_PROGRESS:
                List<WebElement> forms = formsPortal.getDriver().findElements(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[3]"));
                return forms.size() <= 50;
            case SUBMITTED:
                formsPortal.submittedTabClick();
                List<WebElement> instance = formsPortal.getDriver().findElements(By.cssSelector("#menu2 > table > tbody > tr > td:nth-child(3)"));
                return (instance.size()>50);
            default:
               return false;
        }
    }

    @Step
    public PersonaDetail getPersona(Persona persona) throws IOException, SAXException, ParserConfigurationException {
        
      PersonaDataProvider dataProvider = new PersonaDataProvider();
      return dataProvider.getPersona(persona);
    }

    @Step
    public boolean editViewSubmit(String mode, String dataSubject, String status){
         return formsPortal.clickExternalFormButton(mode,dataSubject,status, FormsPortalExternal.PERIOD);
    }

    @Step
    public void changeC23Form(){
        c23PageObject.changeC23FormToFail();
    }

    @Step
    public void c23Submit(){c23PageObject.clickSubmit();}
    @Step
    public void C23Save() {
        c23PageObject.clickSave();
    }

    @Step
    public void C23Close(){
        c23PageObject.clickClose();
    }

    @Step
    public void click_Confirm_Submission()
    {
        c23PageObject.clickConfirmSubmission();
    }

    @Step
    public void click_Cancel_Submission()
    {
        c23PageObject.clickCancelSubmission();
    }

    @Step
    public void click_Okay_After_Submission()
    {
        c23PageObject.clickOkAfterSubmission();
    }
    @Step
    public void changeField(Object value){
        formsPortal.changeField(value);
    }

    @Step
    public void checkErrorMessage(String message) throws Exception {
            String msg = login_pageObject.checkErrMessage(message);
        if(!msg.equalsIgnoreCase(message)){
             throw  new Exception("Wrong Error Message has been displayed. "+message);
        }
    }

    @Step
    public boolean formError(){
        return formsPortal.formError();
    }
    @Step
    public boolean compareNowVsDueDate(String dataSubject, String action) throws ParseException {
        try {
            if(action.equalsIgnoreCase("Before")){
                return formsPortal.compareDatesInProgress("2018-02",dataSubject, action);
            }else {
                return formsPortal.compareDatesInProgress(FormsPortalExternal.PERIOD, dataSubject, action);
            }
        } catch (ParseException e) {

          throw new ParseException(e.getMessage(),1);
        }
    }
}
