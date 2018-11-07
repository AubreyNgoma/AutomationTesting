package za.co.resbank.pages.Internal;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@DefaultUrl("http://localhost:4502/content/sarb-forms-web-interface/internal/form-config-all-forms.html")
public class InternalFormsConfiguration extends PageObject {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private WebDriverWait wait;
    private Actions act;
    private List<WebElement> periodList;
    private List<WebElement> statusList;
    int index = 0;

    public static @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul/li[2]/a") WebElementFacade FORMSCONFIGLINK;
    @FindBy(xpath = "//*[@id=\"btn-create-form-type\"]") WebElementFacade btnAddNewFormType;
    @FindBy(xpath = "//*[@id=\"btn-create-form-type-confirm\"]") WebElementFacade btnCreate;
    @FindBy(xpath = "//*[@id=\"btn-acknowledge-form-type-created\"]") WebElementFacade btnOk;
    @FindBy(xpath = "//*[@id=\"btn-create-form-type-cancel\"]") WebElementFacade btnCancel;
    @FindBy(xpath = "//*[@id=\"form-type-code\"]") WebElementFacade code;
    @FindBy(xpath = "//*[@id=\"form-type-title\"]") WebElementFacade title;
    @FindBy(xpath = "//*[@id=\"form-type-description\"]") WebElementFacade description;
    @FindBy(xpath = "//*[@id=\"aem-form-path\"]") WebElementFacade aemForm;

    public void clickFormsConfigurationLink(){
        if(FORMSCONFIGLINK.isDisplayed()){
            FORMSCONFIGLINK.click();
        }
    }
    public boolean createNewFormType(String code, String title, String distributionType,String frequency, String action){
        Select select = null;
        wait = new WebDriverWait(this.getDriver(), 30);
        act = new Actions(this.getDriver());
        LOGGER.info("Creating new form type: "+code);

        WebElement distributionBox;
        WebElement frequencyBox;

        btnAddNewFormType.click();
        if(action.equalsIgnoreCase("created")){

            //create new formType
            this.code.type(code);
            this.title.type(title);
            description.type(title);
            aemForm.type(title);

            distributionBox = this.getDriver().findElement(By.xpath("//*[@id=\"distribution-type\"]"));
            select = new Select(distributionBox);
            select.selectByValue(distributionType);


            frequencyBox = this.getDriver().findElement(By.xpath("//*[@id=\"release-frequency\"]"));
            select = new Select(frequencyBox);
            select.selectByValue(frequency);

            if(btnCreate.isDisplayed()) {
                btnCreate.click();
            }else {
                act.click(wait.until(ExpectedConditions.elementToBeClickable(btnCreate)));
            }
            if(btnOk.isDisplayed()){
                btnOk.click();
                return true;
            }else{
                act.click(wait.until(ExpectedConditions.elementToBeClickable(btnOk))).build().perform();
                return true;
            }
        }else if(action.equalsIgnoreCase("not_created")){
            //cancel the process of creating a formType
            if(btnCancel.isDisplayed()) {
                btnCancel.click();
                return true;
            }
        }
        return false;
    }
    //

    public boolean getFormTypeReleaseCount(String formType, String amount){
        int index = 0;
        List<WebElement> formTypeList = getFormTypeList();
        LOGGER.info("Found this amount of form types in the list "+formTypeList.size());
        for(WebElement item:formTypeList){
            index++;
            if(item.getText().contains(formType)){
               String releaseCount = this.getDriver().findElement(By.xpath("/html/body/div/div[2]/div/div[1]/div/div/table/tbody/tr["+index+"]/td[5]")).getText();
               LOGGER.info("Found Release Count "+releaseCount+" for "+formType);
               if(Integer.parseInt(releaseCount)==Integer.parseInt(amount)){
                   return true;
               }else if(amount.equalsIgnoreCase("more_than_one")&&Integer.parseInt(releaseCount)>1){
                   return true;
               }
            }
        }
        return false;
    }

    //check for form type in forms configuration
    public boolean checkFormType(String formType){
        wait = new WebDriverWait(this.getDriver(), 30);
        act = new Actions(this.getDriver());

        LOGGER.info("Going through table to see if "+formType+" has been created.");

        List<WebElement> formTypeList = getFormTypeList();
        if(formType.isEmpty()) {
            if (formTypeList.isEmpty()) {
                return true;
            }
        }else {
            for (WebElement formTypes : formTypeList) {
                if (formTypes.getText().contains(formType)) {
                    LOGGER.info("Found new created form Type: " + formTypes.getText());
                    return true;
                }
            }
        }
        return formTypeList.isEmpty();
    }

    public boolean checkFormTypeByPropertyType(String formType, String propertyType, String propertyValue){
        List<WebElement> propertyList = new ArrayList<>();
        if(propertyType.contains("distribution")){
            propertyList = this.getDriver().findElements(By.xpath("/html/body/div/div[2]/div/div[1]/div/div/table/tbody/tr/td[2]"));
        }else if(propertyType.contains("frequency")){
            propertyList = this.getDriver().findElements(By.xpath("/html/body/div/div[2]/div/div[1]/div/div/table/tbody/tr/td[3]"));
        }
        for(WebElement item:propertyList){
            if(item.getText().equals(propertyValue.toUpperCase())&&this.checkFormType(formType)){
                return true;
            }
        }
         return false;
    }

    public boolean clickOnReleaseDetail(String periodValue, String statusValue){
        for(WebElement period:periodList){
            index++;
            for(WebElement status:statusList){
                if(period.getText().equals(periodValue)&&status.getText().equals(statusValue)){
                    LOGGER.info("Attempting to click on Release detail for release "+periodValue);
                    WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                    Actions act = new Actions(this.getDriver());
                    act.click(wait.until(ExpectedConditions.elementToBeClickable(this.getDriver().findElement(By.xpath("/html/body/div/div[2]/div/div[2]/table/tbody/tr["+index+"]/td[5]/a"))))).build().perform();
                    return true;
                }
            }
        }
        LOGGER.info("Attempting to click on Release detail for release "+periodValue+" but something is wrong by retrieving the index.");
        return false;
    }

    private List<WebElement> getPeriodList(){
        WebElement element = this.getDriver().findElement(By.xpath("/html/body/div/div[3]"));
        act = new Actions(this.getDriver());
        wait = new WebDriverWait(this.getDriver(),30);
        act.moveToElement(wait.until(ExpectedConditions.visibilityOf(element)),element.getLocation().getX(),element.getLocation().getY()).build().perform();
       return this.getDriver().findElements(By.xpath("/html/body/div/div[2]/div/div[2]/table/tbody/tr/td[1]"));
    }
    private List<WebElement> getStatusList() {
       WebElement element = this.getDriver().findElement(By.xpath("/html/body/div/div[3]"));
       act = new Actions(this.getDriver());
       wait = new WebDriverWait(this.getDriver(),30);
       act.moveToElement(wait.until(ExpectedConditions.visibilityOf(element)),element.getLocation().getX(),element.getLocation().getY()).build().perform();
       return this.getDriver().findElements(By.xpath("/html/body/div/div[2]/div/div[2]/table/tbody/tr/td[2]"));
   }
    public List<WebElement> getFormTypeList(){
        return this.getDriver().findElements(By.xpath("/html/body/div/div[2]/div/div[1]/div/div/table/tbody/tr/td[1]"));
   }

   public void clickReleaseDetail(int index){
       act.click(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[1]/div/div/table/tbody/tr["+index+"]/td[6]/a[1]")))).build().perform();
   }

    public boolean checkNewCreatedRelease(String periodValue, String statusValue){
        periodList = getPeriodList();
        statusList = getStatusList();
        for(WebElement period:periodList){
            for(WebElement status:statusList){
                if(period.getText().equals(periodValue)&&status.getText().equals(statusValue)){
                    return true;
                }
            }
        }
        return false;
    }


}
