package za.co.resbank.pages.Internal;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lean
 * Date: 5/29/18
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */

@DefaultUrl("")
public class InternalFormsPortal extends PageObject {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private int index=0;
    private Actions act;
    private WebDriverWait wait;

     //Retrieve all data subjects inside the table and see if one exists////
    public List<WebElement> getFormReleases()
    {
       return this.getDriver().findElements(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/table/tbody/tr/td[1]"));
    }
    /////////////////
    //Gets the index of all the specific dataSubject in the internal landing page table and clicks on the specific one
     public void viewFormRelease(String dataSubject) throws Exception {
         List<WebElement> elements = this.getDriver().findElements(By.xpath("/html/body/div/div[2]/div/div/div/div/table/tbody/tr/td[1]"));
         for(WebElement item:elements){
             index++;
             System.out.println(item.getText()+" : " +index);
          if(item.getText().equalsIgnoreCase(dataSubject)){
              wait = new WebDriverWait(this.getDriver(), 30);
              act = new Actions(this.getDriver());
              act.moveToElement(item).perform();
              act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div/div/div/table/tbody/tr["+index+"]/td[8]/a")))).build().perform();
         }
         }
     }
    //






    //All buttons inside the forms release form/////
    @FindBy(xpath = "//*[@id=\"release-detail\"]")
    WebElementFacade edit;

    @FindBy(xpath = "//*[@id=\"openEnabled\"]")
    WebElementFacade open;

    @FindBy(xpath = "//*[@id=\"release-detail-open\"]/form/div/div[16]/button[3]")
    WebElementFacade close;

    @FindBy(xpath = "//*[@id=\"send-reminder\"]")
    WebElementFacade setReminder;

    @FindBy(xpath = "//*[@id=\"release-detail-open\"]/form/div/div[16]/button[4]")
    WebElementFacade delete;




     public void viewFormInstance()
     {
         WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"statusCanvas\"]"));
         WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);

         Actions act = new Actions(this.getDriver());
         act.moveToElement(element).perform();
         act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"formInstanceLinkID\"]")))).build().perform();
     }

    //This buttons are on the forms release page, this will only function inside of the form when someone wants to delete, remind, open and close or edit.
    public void clickOption(String option)
    {
         if(option.equalsIgnoreCase("edit")){edit.click();}
        if(option.equalsIgnoreCase("open")){open.click();}
        if(option.equalsIgnoreCase("close")){close.click();}
        if(option.equalsIgnoreCase("setReminder")){setReminder.click();}
        if(option.equalsIgnoreCase("delete")){delete.click();}
    }
    //End

    //Selecting Statuses in the View Form Instance section
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/ul/li[1]/a")
    WebElementFacade created_status;

    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/ul/li[2]")
    WebElementFacade ready_status;

    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/ul/li[3]")
    WebElementFacade in_progress_status;

    @FindBy(xpath = "/html/body/div/div[2]/div[1]/div/div/ul/li[4]")
    WebElementFacade submit_status;

    public void goToCreated(){created_status.click();}

    public void goToReady(){ready_status.click();}

    public void goToProgressStatus(){in_progress_status.click();}

    public void goToSubmittedStatus(){submit_status.click();}
    //End

    ///END


    //Checking statuses for instances on the forms table

    public List<WebElement> getCreatedFormInstances() {

        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("#createdTab > table > tbody > tr:nth-child(2) > td:nth-child(3)"))));

        return this.getDriver().findElements(By.cssSelector("#createdTab > table > tbody > tr > td:nth-child(3)"));
    }

    public List<WebElement> getReadyFormInstances(){
        return this.getDriver().findElements(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr/td[3]"));
    }

    public List<WebElement> getInProgressFormInstances(){
        return this.getDriver().findElements(By.xpath(""));
    }

    public List<WebElement> getSubmittedFormInstances(){
        return this.getDriver().findElements(By.xpath(""));
    }


    //END

}
