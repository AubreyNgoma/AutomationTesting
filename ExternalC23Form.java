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
import za.co.resbank.utils.TableTypes;

@DefaultUrl("http://localhost:4502/editor.html/content/forms/af/sarb-forms-web-interface/c23.html")
public class ExternalC23Form extends PageObject {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    //Retrieve URL to compare vs previous version
    public String getUrl()
    {
        return this.getDriver().getCurrentUrl();
    }
    ///////////////////////

    private TableTypes tableTypes;

    //this method retrieve a class name from the dataSetProvider class, as well as the data that is linked to that specific class name.
    public void enterFieldsForC3Form(String className, String classValue){
        WebElement field = this.getDriver().findElement(By.className(className));
        Actions actions = new Actions(this.getDriver());
        actions.moveToElement(field);
        actions.click();
        actions.sendKeys(classValue);
        actions.build().perform();
    }

    WebDriverWait wait;
    WebElement field;
    Actions actions;
    public void goto_table1()
    {
        wait = new WebDriverWait(this.getDriver(), 30);
        field = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_1306459901___guide-item-nav\"]/a"));
        actions = new Actions(this.getDriver());
        actions.moveToElement(field);
        actions.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_1306459901___guide-item-nav\"]/a")))).build().perform();
    }
    public void goto_table2()
    {
        field = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_280151870___guide-item-nav\"]/a"));
        actions = new Actions(this.getDriver());
        actions.moveToElement(field);
        actions.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_280151870___guide-item-nav\"]/a")))).build().perform();
    }
    public void goto_table3()
    {
        field = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527___guide-item-nav\"]/a"));
        actions = new Actions(this.getDriver());
        actions.moveToElement(field);
        actions.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527___guide-item-nav\"]/a")))).build().perform();
    }
    public void goto_table4()
    {
        field = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626___guide-item-nav\"]/a"));
        actions = new Actions(this.getDriver());
        actions.moveToElement(field);
        actions.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626___guide-item-nav\"]/a")))).build().perform();
    }
    public void goto_table5()
    {
        field = this.getDriver().findElement(By.xpath("//*[@id='guideContainer-rootPanel-panel_100111925-panel_201441536___guide-item-nav']/a"));
        actions = new Actions(this.getDriver());
        actions.moveToElement(field);
        actions.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='guideContainer-rootPanel-panel_100111925-panel_201441536___guide-item-nav']/a")))).build().perform();
    }
    //

    private void clickOnFieldC23Form(String className){
        field = this.getDriver().findElement(By.className(className));
        actions = new Actions(this.getDriver());
        actions.moveToElement(field);
        actions.click();
    }

    //Extention field in C23 form//

    public void changeC23FormToFail()
    {
        this.goto_table1();
        WebElement element = this.getDriver().findElement(By.className("c_01_002_0001"));

        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
        Actions act = new Actions(this.getDriver());

        act.moveToElement(element).perform();
        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("c_01_002_0001")))).sendKeys("1").build().perform();
    }
    /////////////End/////////////////

    //C23 form editing Elements and methods//
    public void clickSave()
    {
        WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"sarb_save\"]"));
        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
        Actions act = new Actions(this.getDriver());

        act.moveToElement(element).perform();
        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sarb_save\"]")))).build().perform();
    }

    public void clickClose()
    {
        WebElement element = this.getDriver().findElement(By.xpath("//*[@id=\"sarb_close\"]"));
        WebDriverWait wait = new WebDriverWait(this.getDriver(), 30);
        Actions act = new Actions(this.getDriver());

        act.moveToElement(element).perform();
        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sarb_close\"]")))).build().perform();
    }

    @FindBy(xpath = "//*[@id=\"sarb_validate\"]")
    WebElementFacade btnValidate;

    public void clickValidate()
    {
        btnValidate.click();
    }


     @FindBy(xpath = "//*[@id=\"sarb_submit\"]")
    WebElementFacade btnSubmit;

    public void clickSubmit(){
        btnSubmit.click();
    }

    @FindBy(xpath = "//*[@id=\"submit\"]")
    WebElementFacade submitAccept;

    public void clickConfirmSubmission()
    {
        submitAccept.click();
    }

    @FindBy(xpath = "//*[@id=\"confirm-submit\"]/div/div/div[3]/button")
    WebElementFacade submitCancel;

    public void clickCancelSubmission()
    {
        submitCancel.click();
    }

    @FindBy(xpath = "//*[@id=\"submitted\"]/div/div/div[3]/button")
    WebElementFacade submittedOkay;
    public void clickOkAfterSubmission()
    {
      submittedOkay.click();
    }
}
