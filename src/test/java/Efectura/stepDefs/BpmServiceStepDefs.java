package Efectura.stepDefs;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.ConfigurationReader;
import Efectura.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BpmServiceStepDefs extends BaseStep {

    @Given("The user login elastic")
    public void theUserLoginElastic() {
        pages.elastic().loginElastic();
    }

    @Given("The user navigates to {string}")
    public void theUserNavigatesToBpm(String service) {
        Driver.getDriver().get(ConfigurationReader.getProperty(service));
    }

//    @Given("Verify service")
//    public void verify() {
//        pages.elastic().verify();
//    }

    @Given("The user login elastic service")
    public void theUserLoginElasticService() {
        List<String> services = new ArrayList<>(Arrays.asList("bpm", "itemService", "otpService", "dbConnector", "fletumApi"));
        for (String service : services) {
            BrowserUtils.wait(3);
            Driver.getDriver().get(ConfigurationReader.getProperty(service));
            BrowserUtils.wait(2);
            pages.elastic().verify(service);
        }
    }

    @Given("user go to mail")
    public void userGoToMail() {
        Driver.getDriver().get("https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F2%2F&emr=1&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F2%2F&ifkv=ARZ0qKIMpKLfpGCGrAv2NAxvajiUQ_v0gy2yvMZaXqJyYB-h9r-CPOUradhi4bR2TygEvJI_b8Ka&osid=1&passive=1209600&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S-1978881952%3A1711122851010429&theme=mn&ddm=0#inbox");
    }

    @When("user login email")
    public void userLoginEmail() {
        pages.elastic().loginEmail();
    }

    @When("user click compose button")
    public void userClickComposeButton() {
        pages.elastic().clickComposeButton();
    }

    @When("user enters mail credentials")
    public void userEntersMailCredentials() {
        pages.elastic().enterEmailCredentials();
    }

    @Then("user clicks send button")
    public void userClicksSendButton() {
        pages.elastic().clickSendButton();
    }
}
