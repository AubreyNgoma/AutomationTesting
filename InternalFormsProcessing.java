package za.co.resbank.pages.Internal;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@DefaultUrl("")
public class InternalFormsProcessing extends PageObject {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private WebDriverWait wait;
    private Actions act;
    public static @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul/li[1]/a") WebElementFacade FORMSPROCESSINGLINK;

    public void clickFormsProcessingLink(){
        if (FORMSPROCESSINGLINK.isDisplayed()) {
            FORMSPROCESSINGLINK.click();
        }
    }
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

    public List<WebElement> getFormTypeList(){
        return this.getDriver().findElements(By.xpath("/html/body/div/div[2]/div/div/div/div/table/tbody/tr/td[1]"));
    }

    public void clickReleaseDetail(int index){
        act.click(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[1]/div/div/table/tbody/tr["+index+"]/td[6]/a[2]")))).build().perform();
    }

}
