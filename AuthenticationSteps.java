/**
 *
 */
package za.co.resbank.cucumbersteps;

import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import za.co.resbank.forms.testing.data.PersonaDataProvider;
import za.co.resbank.forms.testing.model.Persona;
import za.co.resbank.forms.testing.model.PersonaDetail;
import za.co.resbank.forms.testing.personas.CurrentPersona;
import za.co.resbank.forms.testing.steps.serenity.Pattern_AemFormsAuthorSteps;
import za.co.resbank.serenitysteps.*;
import za.co.resbank.utils.LoginMenu;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * This Step Definition file contains Cucumber step definitions for all steps related
 * to authentication and authorisation on all UIs in the solution
 *
 * @author lean
 *
 */
public class AuthenticationSteps {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Steps
    InternalFormsProcessingEndUserSteps InternaltestUser;
    @Steps
    FormsPortalEndUserSteps ExternaltestUser;
    @Steps
    Pattern_AemFormsAuthorSteps author;

    private Persona persona;
    private PersonaDetail personaDetail;




    public void beforeOpen()
    {
        ExternaltestUser.goMax();
    }



    @Given("^that \"(.*)\" is logged into the \"(.*)\"$")
    public void givenTheUserIsLoggedIntoSystem(String personaName, String userInterface) throws ParserConfigurationException, SAXException, IOException {
        LoginMenu menu = null;
        if(userInterface.equalsIgnoreCase("Internal Interface")){
            menu = LoginMenu.valueOf("InternalInterface");
        }else if(userInterface.equalsIgnoreCase("External Interface")){
            menu = LoginMenu.valueOf("ExternalInterface");
        }else if(userInterface.equalsIgnoreCase("AEM Authoring Interface")){
            menu = LoginMenu.valueOf("AEM");
        }

        switch (menu) {
            case InternalInterface:
                beforeOpen();

                persona = Persona.valueOf(personaName.toUpperCase());
                LOGGER.info("Persona Identified as: "+persona.name());

                personaDetail =  InternaltestUser.getPersona(persona);
                LOGGER.info("Persona Details loaded: "+personaDetail.toString());

                InternaltestUser.is_on_the_internal_login_page();
                InternaltestUser.enterUsername(personaDetail.getUserName());
                InternaltestUser.enterPassword(personaDetail.getValidPassword());
                InternaltestUser.submit();
                LOGGER.info(personaDetail.getUserName() +"Just Logged into the Internal Forms portal as "+personaDetail.getUserName());
                break;
            case ExternalInterface:
                beforeOpen();
                ExternaltestUser.is_on_external_login_page();
                PersonaDetail userData = ExternaltestUser.getPersona(Persona.valueOf(personaName.toUpperCase()));
                ExternaltestUser.enterEmail(userData.getUserName());
                ExternaltestUser.enterPassword(userData.getValidPassword());
                ExternaltestUser.submit();
                LOGGER.info(userData.getUserName() +"Just Logged into the External Forms portal as "+userData.getUserName());
                break;
            case AEM:
                persona = Persona.valueOf(personaName.toUpperCase());
                LOGGER.info("Persona Identified as: "+persona.name());
                // Load persona details
                personaDetail = author.getPersona(persona);
                LOGGER.info("Persona Details loaded: "+personaDetail.toString());
                beforeOpen();
                author.is_on_aem_login_page();
                author.enters_username(personaDetail.getUserName());
                author.enters_password(personaDetail.getValidPassword());
                author.hits_sign_in();
                LOGGER.info(personaDetail.getUserName() +"Just Logged into the AEM Forms portal as an Author");
                break;
            default:
                LOGGER.error("Something went wrong, a feature file may contain a broken scenario step!");
                break;
        }

    }


}
