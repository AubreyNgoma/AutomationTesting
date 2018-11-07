package za.co.resbank.pages.External;

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
import za.co.resbank.cucumbersteps.FormsPortalExternal;
import za.co.resbank.utils.ClickModes;
import za.co.resbank.utils.TabStatuses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lean
 * Date: 5/25/18
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
@DefaultUrl("http://localhost:4502/content/sarb-forms-web-interface/forms-portal.html")
public class ExternalFormsPortal extends PageObject {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private List<WebElement> dataSubjectList = new ArrayList<>();
    private List<WebElement> periodlist = new ArrayList<>();
    private  int index = 1;
    private boolean check = false;
    private TabStatuses tabStatuses;

    //Tab click methods and components//
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/ul/li[1]/a") WebElementFacade READYTAB;
    @FindBy(css = "body > div.page__par > div.forms-portal > div > div > div > ul > li:nth-child(2) > a") WebElementFacade PROGRESSTAB;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/ul/li[3]/a") WebElementFacade SUBMITTAB;
    @FindBy(xpath= "/html/body/div/div[2]/div/div/div/p/b") WebElementFacade viewReadyPage;
    @FindBy(xpath = "//*[@id=\"guideContainer-rootPanel-panel-webaddress1525876727___widget\"]") WebElementFacade first_field;
    @FindBy(xpath = "//*[@id=\"sarb_submit\"]") WebElementFacade submitButton;
    @FindBy(xpath = "//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_1306459901-panel-panel-table-Row1527253821370-tableItem1527253821372__\"]/div[3]") WebElementFacade T1C1R1;


    public void readTabClick(){
        READYTAB.click();
    }
    public void progressTabClick(){
        PROGRESSTAB.click();
    }
    public void submittedTabClick(){
        SUBMITTAB.click();
    }

    //Get the form instances instances inside of the in progress tab//
    public List<WebElement> getProgressTableList(){
        return this.getDriver().findElements(By.cssSelector("#menu1 > table > tbody > tr"));
    }

    public boolean readyFormDisplayed() {
        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div/div/p/b"))).isDisplayed();
    }

    public void changeField(Object value){
        first_field.type(value.toString());
    }

    public void clickSubmit(){submitButton.click();}

    //Done
    public int getTableRowButtons(String status, String dataSubject){
        int sumOfButtons = 0;
        dataSubjectList = getDataSubjects(status);
        periodlist = getPeriodList(status);
        tabStatuses = TabStatuses.valueOf(status);
        if(status.equalsIgnoreCase("READY")^status.equalsIgnoreCase("IN_PROGRESS")^status.equalsIgnoreCase("SUBMITTED")){
            tabStatuses = TabStatuses.valueOf(status.toUpperCase());
        }
        switch (tabStatuses){
            case READY:
                index=0;
                for(int i = 0; i < dataSubjectList.size(); i++){
                        dataSubject = dataSubject.replaceAll("^\"|\"$","");
                        index++;
                        if(dataSubjectList.get(i).getText().equalsIgnoreCase(dataSubject)&&periodlist.get(i).getText().equalsIgnoreCase(FormsPortalExternal.PERIOD)){
                            WebElement btnStart = this.getDriver().findElement(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr["+index+"]/td[7]/a"));
                          if(btnStart.isDisplayed()){sumOfButtons+=1; return sumOfButtons;}
                            return sumOfButtons;
                        }
                }
                return 12;
            case IN_PROGRESS:
                index=0;
                for(int i = 0; i < dataSubjectList.size(); i++){
                        dataSubject = dataSubject.replaceAll("^\"|\"$","").trim();
                        index++;
                        if(dataSubjectList.get(i).getText().equalsIgnoreCase(dataSubject)&&periodlist.get(i).getText().equalsIgnoreCase(FormsPortalExternal.PERIOD)){
                           if(index==2){
                               WebElement btnEdit = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[11]/a[1]"));
                               WebElement btnView = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[11]/a[2]"));
                               WebElement btnDetail = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[11]/a[3]"));
                               if(btnEdit.isDisplayed()){sumOfButtons+=1;}
                               if(btnView.isDisplayed()){sumOfButtons+=1;}
                               if(btnDetail.isDisplayed()){sumOfButtons+=1;}
                           }else {
                               WebElement btnEdit = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr[" + index + "]/td[11]/a[1]"));
                               WebElement btnView = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr[" + index + "]/td[11]/a[2]"));
                               WebElement btnDetail = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr[" + index + "]/td[11]/a[3]"));
                               if (btnEdit.isDisplayed()) { sumOfButtons += 1; }
                               if (btnView.isDisplayed()) { sumOfButtons += 1; }
                               if (btnDetail.isDisplayed()) { sumOfButtons += 1; }
                           }
                            return sumOfButtons;
                        }
                }
                return sumOfButtons;
            case SUBMITTED:
                for(int i = 0; i < dataSubjectList.size(); i++){
                        dataSubject = dataSubject.replaceAll("^\"|\"$","").trim();
                        index++;
                        if(dataSubjectList.get(i).getText().equalsIgnoreCase(dataSubject)&&periodlist.get(i).getText().equalsIgnoreCase(FormsPortalExternal.PERIOD)){
                            if(index==2){
                               WebElement btnView = this.getDriver().findElement(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr/td[10]/a[1]"));
                               WebElement btnDetail = this.getDriver().findElement(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr/td[10]/a[2]"));
                               if (btnView.isDisplayed()) {
                                   sumOfButtons += 1;
                               }
                               if (btnDetail.isDisplayed()) {
                                   sumOfButtons += 1;
                               }
                           }else {
                               WebElement btnView = this.getDriver().findElement(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr[" + index + "]/td[10]/a[1]"));
                               WebElement btnDetail = this.getDriver().findElement(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr[" + index + "]/td[10]/a[2]"));
                               if (btnView.isDisplayed()) {
                                   sumOfButtons += 1;
                               }
                               if (btnDetail.isDisplayed()) {
                                   sumOfButtons += 1;
                               }
                           }
                            return sumOfButtons;
                        }
                }
                return sumOfButtons;
        }
        return sumOfButtons;
    }

    //Done
    private List<WebElement> getDataSubjects(String status){
        tabStatuses = TabStatuses.valueOf(status.toUpperCase());
        if(status.equalsIgnoreCase("READY")^status.equalsIgnoreCase("IN_PROGRESS")^status.equalsIgnoreCase("SUBMITTED")){
            tabStatuses = TabStatuses.valueOf(status.toUpperCase());
        }
        switch (tabStatuses){
            case READY:
                dataSubjectList =  this.getDriver().findElements(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr/td[3]"));
                return dataSubjectList;
            case IN_PROGRESS:
                return dataSubjectList = this.getDriver().findElements(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[3]"));
            case SUBMITTED:
                return dataSubjectList =  this.getDriver().findElements(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr/td[3]"));
        }
        return dataSubjectList;
    }

    //Done
    private List<WebElement> getPeriodList(String status){
        tabStatuses = TabStatuses.valueOf(status.toUpperCase());
        if(status.equalsIgnoreCase("READY")^status.equalsIgnoreCase("IN_PROGRESS")^status.equalsIgnoreCase("SUBMITTED")){
            tabStatuses = TabStatuses.valueOf(status.toUpperCase());
        }
        switch (tabStatuses){
            case READY:
                periodlist =  this.getDriver().findElements(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr/td[5]"));
                return periodlist;
            case IN_PROGRESS:
                return  periodlist = this.getDriver().findElements(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[5]"));
            case SUBMITTED:
                return  periodlist =  this.getDriver().findElements(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr/td[5]"));
        }
        return  periodlist;
    }

    //Done
    public boolean clickExternalFormButton(String mode, String dataSubject, String status, String period){
        index = 0;
        tabStatuses = TabStatuses.valueOf(status.toUpperCase());
        ClickModes modes = ClickModes.valueOf(mode.toUpperCase());
        dataSubjectList = getDataSubjects(status);
        periodlist = getPeriodList(status);
        switch (tabStatuses){
            case READY:
                switch (modes){
                    case START:
                      for(int i = 0; i < dataSubjectList.size(); i++){
                          index++;
                          dataSubject = dataSubject.replaceAll("^\"|\"$","").trim();
                          if(dataSubjectList.get(i).getText().equalsIgnoreCase(dataSubject)&&periodlist.get(i).getText().equalsIgnoreCase(FormsPortalExternal.PERIOD)){
                              WebElement btnStart = this.getDriver().findElement(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr["+index+"]/td[7]/a"));
                              WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                              Actions act = new Actions(this.getDriver());
                              act.moveToElement(btnStart).perform();
                              act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr["+index+"]/td[7]/a")))).build().perform();
                              return true;
                          }
                      }
                }
                return check;
            case IN_PROGRESS:
                switch (modes){
                     case VIEW:
                         for(int i = 0; i < dataSubjectList.size(); i++){
                                 dataSubject = dataSubject.replaceAll("^\"|\"$","").trim();
                                 index++;
                                 if(dataSubjectList.get(i).getText().equalsIgnoreCase(dataSubject)&&periodlist.get(i).getText().equalsIgnoreCase(FormsPortalExternal.PERIOD)){
                                    if(index==1){
                                        WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[11]/a[2]"));
                                        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                                        Actions act = new Actions(this.getDriver());
                                        act.moveToElement(element).perform();
                                        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[11]/a[2]")))).build().perform();

                                    }else {
                                        WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr[" + index + "]/td[11]/a[2]"));
                                        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                                        Actions act = new Actions(this.getDriver());
                                        act.moveToElement(element).perform();
                                        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr[" + index + "]/td[11]/a[2]")))).build().perform();
                                    }
                                     return true;
                                 }
                         }
                         return check;
                     case EDIT:
                         for(int i = 0; i < dataSubjectList.size(); i++){
                                 dataSubject = dataSubject.replaceAll("^\"|\"$","").trim();
                                 index++;
                                 if(dataSubjectList.get(i).getText().equalsIgnoreCase(dataSubject)&&periodlist.get(i).getText().equalsIgnoreCase(FormsPortalExternal.PERIOD)){
                                    if(index==1){
                                        WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[11]/a[1]"));
                                        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                                        Actions act = new Actions(this.getDriver());
                                        act.moveToElement(element).perform();
                                        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[11]/a[1]")))).build().perform();

                                    }else {
                                        WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr[" + index + "]/td[11]/a[1]"));
                                        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                                        Actions act = new Actions(this.getDriver());
                                        act.moveToElement(element).perform();
                                        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr[" + index + "]/td[11]/a[1]")))).build().perform();
                                    }
                                     return true;
                                 }
                         }
                         return check;
                     case DETAIL:
                         for(int i = 0; i < dataSubjectList.size(); i++){
                                 dataSubject = dataSubject.replaceAll("^\"|\"$","").trim();
                                 index++;
                                 if(dataSubjectList.get(i).getText().equalsIgnoreCase(dataSubject)&&periodlist.get(i).getText().equalsIgnoreCase(FormsPortalExternal.PERIOD)){
                                    if(index==1){
                                        WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[11]/a[3]"));
                                        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                                        Actions act = new Actions(this.getDriver());

                                        act.moveToElement(element).perform();
                                        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr/td[11]/a[3]")))).build().perform();

                                    }else {
                                        WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr[" + index + "]/td[11]/a[3]"));
                                        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                                        Actions act = new Actions(this.getDriver());

                                        act.moveToElement(element).perform();
                                        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inProgressTab\"]/table/tbody/tr[" + index + "]/td[11]/a[3]")))).build().perform();
                                    }
                                     return true;
                                 }
                         }
                         break;
                }
                return check;
            case SUBMITTED:
                switch (modes){
                   case VIEW:
                       for(int i = 0; i < dataSubjectList.size(); i++){
                               dataSubject = dataSubject.replaceAll("^\"|\"$","").trim();
                               index++;
                               if(dataSubjectList.get(i).getText().equalsIgnoreCase(dataSubject)&&periodlist.get(i).getText().equalsIgnoreCase(period)){
                                   WebElement element;
                                   LOGGER.info("************ "+index);
                                   if(index==1){
                                       element = this.getDriver().findElement(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr/td[10]/a[1]"));
                                       WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                                       Actions act = new Actions(this.getDriver());

                                       act.moveToElement(element).perform();
                                       act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr/td[10]/a[1]")))).build().perform();

                                   }else {
                                       element = this.getDriver().findElement(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr[" + index + "]/td[10]/a[1]"));
                                       WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                                       Actions act = new Actions(this.getDriver());

                                       act.moveToElement(element).perform();
                                       act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr[" + index + "]/td[10]/a[1]")))).build().perform();
                                   }
                                       return true;
                               }
                       }
                       return check;
                   case DETAIL:
                       for(int i = 0; i < dataSubjectList.size(); i++){
                               dataSubject = dataSubject.replaceAll("^\"|\"$","").trim();
                               index++;
                               if(dataSubjectList.get(i).getText().equalsIgnoreCase(dataSubject)&&periodlist.get(i).getText().equalsIgnoreCase(period)){
                                  if(index==1){
                                      WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr/td[10]/a[2]"));
                                      WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                                      Actions act = new Actions(this.getDriver());

                                      act.moveToElement(element).perform();
                                      act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr/td[10]/a[2]")))).build().perform();

                                  }else {
                                      WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr[" + index + "]/td[10]/a[2]"));
                                      WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
                                      Actions act = new Actions(this.getDriver());

                                      act.moveToElement(element).perform();
                                      act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submittedTab\"]/table/tbody/tr[" + index + "]/td[10]/a[2]")))).build().perform();
                                  }
                                   return true;
                               }
                       }
                       break;
                }
                return check;
            default:
                return check;
        }
    }

    //This method is SUPER slow
    public boolean compareDatesInProgress(String period, String dataSubject, String action) throws ParseException {

      List<WebElement> dates = this.getDriver().findElements(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr/td[6]"));
      List<WebElement> periods = this.getDriver().findElements(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr/td[5]"));
      List<WebElement> dataSubjects = this.getDriver().findElements(By.xpath("//*[@id=\"readyTab\"]/table/tbody/tr/td[3]"));
      Date dueDate;
      Date submissionDate = new Date();
       for(WebElement dataSubjectItems:dataSubjects){
           for(WebElement periodItems:periods){
               for(WebElement dateItems:dates){
                   dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateItems.getText());
                   if(dataSubjectItems.getText().equalsIgnoreCase(dataSubject)&&periodItems.getText().equalsIgnoreCase(period)&&submissionDate.before(dueDate)&&action.equalsIgnoreCase("Before")){
                       return submissionDate.before(dueDate);
                   }
                   if(dataSubjectItems.getText().equalsIgnoreCase(dataSubject)&&periodItems.getText().equalsIgnoreCase(period)&&submissionDate.after(dueDate)&&action.equalsIgnoreCase("After")){
                       return submissionDate.after(dueDate);
                   }
               }
           }
       }
        return false;
    }

    public boolean formError(){
       return T1C1R1.isDisplayed();
    }
}