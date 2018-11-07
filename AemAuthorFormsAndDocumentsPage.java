package za.co.resbank.pages.AEM;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The home page for AEM Forms and Documents. From here you can 
 * drill into forms folders, create forms, etc.
 * 
 * @author gregf
 *
 */
@DefaultUrl("http://10.96.87.132:4502/aem/forms.html/content/dam/formsanddocuments")
public class AemAuthorFormsAndDocumentsPage extends PageObject {

  private WebDriverWait wait;
  private WebElement field;
  private Actions actions;
  // Create Button
  @FindBy(xpath = "//*[@id=\"aem-forms-create\"]")
  WebElementFacade createButton;
  
  // Adaptive Form element in Create dropdown
  @FindBy(xpath = "//*[@id=\"0500c406-6c00-46f7-97f1-70132e6645a5\"]/div[3]/coral-popover-content/coral-anchorlist/a[2]/div/div/coral-list-item-content")
  WebElementFacade adaptiveListItem;  

  public void clickCreateAdaptiveForm()
  {
    createButton.click();
  }

  public String getBtnText()
  {
    wait = new WebDriverWait(this.getDriver(), 30);
    field = this.getDriver().findElement(By.xpath("//*[@id=\"globalnav-start-home-collection\"]/coral-masonry-item[1]/div/div"));
    actions = new Actions(this.getDriver());
    actions.moveToElement(field);
    return field.getText();
  }
}