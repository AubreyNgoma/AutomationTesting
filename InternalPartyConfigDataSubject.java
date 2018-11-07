package za.co.resbank.pages.Internal;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DefaultUrl("")
public class InternalPartyConfigDataSubject extends PageObject {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    public static @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul/li[3]/div/a") WebElementFacade PARTYCONFIGLINK;

    public void clickPartyConfigLink(){
        if(PARTYCONFIGLINK.isDisplayed()){
            PARTYCONFIGLINK.click();
        }
    }

}
