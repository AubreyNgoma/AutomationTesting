package za.co.resbank.pages.AEM;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

/**
 * The wizard page when creating AEM forms. Selecting the template 
 * 
 * @author gregf
 *
 */
@DefaultUrl("http://10.96.87.132:4502/aem/createaf.html")
public class AemAuthorCreateWizard extends PageObject {
  
  // Sarb Blank Form Template
  @FindBy(xpath = "//coral-card[/div/coral-card-content/coral-card-title/coral-card-title-label=\"sarb-blank-form\"]")
  WebElementFacade sarbBlankFormCard;
  
  public void clickSarbBlankFormCard() {
    sarbBlankFormCard.click();
  }


}