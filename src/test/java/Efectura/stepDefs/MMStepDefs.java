package Efectura.stepDefs;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import Efectura.utilities.Driver;
import io.cucumber.java.en.Given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MMStepDefs extends BaseStep {

    @Given("The user look elastic service for mm")
    public void theUserLookElasticServiceForMm() {

        if (pages.diaPages().getCouldNotLoginText().isDisplayed()) {
            pages.diaPages().setMessage();
        }else {
            BrowserUtils.wait(7);
            List<String> services = new ArrayList<>(Arrays.asList(
                    "mmFletumApi", "mmFletumWeb", "mmSchedulerService", "mmValidationService",
                    "mmItemService", "mmIdentityService", "mmOtpService",
                    "mmBpmService", "mmMMService","mmDbConnector"
            ));
            for (String service : services) {
                BrowserUtils.wait(2);
                Driver.getDriver().get(ConfigurationReader.getProperty(service));
                BrowserUtils.wait(5);
                pages.diaPages().verify(service);
            }
        }
    }

    @Given("The user sends email for mm")
    public void theUserSendsEmailForMm() {
        pages.diaPages().sendMailForMM();
    }

    @Given("The user sends group email for mm")
    public void theUserSendsGroupEmailForMm() {
        pages.diaPages().sendGroupEmailForMM();
    }

    @Given("The user sends telegram sms for mm")
    public void theUserSendsTelegramSmsForMm() {
        pages.diaPages().sendTelgramSmsForMm();
    }
}
