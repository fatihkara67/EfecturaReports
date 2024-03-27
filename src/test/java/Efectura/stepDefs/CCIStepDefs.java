package Efectura.stepDefs;


import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import Efectura.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CCIStepDefs extends BaseStep{
    @Given("The user login CCI elastic service")
    public void theUserLoginCCIElasticService() {
        pages.cciPage().cciLoginElastic();
    }

    @Given("The user login CCI elastic page")
    public void theUserLoginCCIElasticPage() {
        List<String> cciservices = new ArrayList<>(Arrays.asList("cciFletumItemService", "cciFletumApi", "cciFletumDBConnector", "cciFletumCDP", "cciFletumCEP", "cciFletumOtp", "cciFletumValidation", "cciFletumWeb" ));
        //pages.cciPage().cciElasticFletumServices();
        for (String service : cciservices) {
            BrowserUtils.wait(5);
            Driver.getDriver().get(ConfigurationReader.getProperty(service));
            BrowserUtils.wait(5);
            pages.cciPage().verify(service);
        }
    }

    @When("user enters mail credentials for cci")
    public void userEntersMailCredentialsForCci() {
        pages.cciPage().enterEmailCredentials();
    }
}
