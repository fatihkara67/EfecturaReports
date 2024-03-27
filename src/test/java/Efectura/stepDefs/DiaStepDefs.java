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


public class DiaStepDefs extends BaseStep {

    @Given("The user login elastic")
    public void theUserLoginElastic() {
        pages.diaPages().loginElastic();
    }

    @Given("The user login elastic service")
    public void theUserLoginElasticService() {
        List<String> services = new ArrayList<>(Arrays.asList("bpm", "itemService", "otpService", "dbConnector", "fletumApi", "fletumWeb", "diaService"));
        for (String service : services) {
            BrowserUtils.wait(3);
            Driver.getDriver().get(ConfigurationReader.getProperty(service));
            BrowserUtils.wait(2);
            pages.diaPages().verify(service);
        }
    }

    @Given("user go to mail")
    public void userGoToMail() {
        Driver.getDriver().get("https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F2%2F&emr=1&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F2%2F&ifkv=ARZ0qKIMpKLfpGCGrAv2NAxvajiUQ_v0gy2yvMZaXqJyYB-h9r-CPOUradhi4bR2TygEvJI_b8Ka&osid=1&passive=1209600&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S-1978881952%3A1711122851010429&theme=mn&ddm=0#inbox");
    }

    @When("user login email")
    public void userLoginEmail() {
        pages.diaPages().loginEmail();
    }

    @When("user click compose button")
    public void userClickComposeButton() {
        pages.diaPages().clickComposeButton();
    }

    @When("user enters mail credentials")
    public void userEntersMailCredentials() {
        pages.diaPages().enterEmailCredentials();
    }

    @Then("user clicks send button")
    public void userClicksSendButton() {
        pages.diaPages().clickSendButton();
    }

    @Given("The user login Fletum")
    public void theUserLoginFletum() {
        Driver.getDriver().get("https://diageo.efectura.com/Account/Login");
        pages.diaPages().loginFletum();
        BrowserUtils.wait(10);
    }

    @Given("The user open BPM page")
    public void theUserOpenBPMPage() {
        Driver.getDriver().get("https://diageo.efectura.com/Task/TaskList");
        BrowserUtils.wait(10);
    }

    @Given("The user write {string} in search all filter")
    public void theUserWriteInSearchAllFilter(String searchFlow) {
        pages.diaPages().searchFor(searchFlow);
    }

    @Given("The user take the modul count")
    public void theUserTakeTheModulCount() {
        BrowserUtils.wait(10);
        pages.diaPages().takeModulCount();
    }

    @Given("The user take the menu count")
    public void theUserTakeTheMenuCount() {
        BrowserUtils.wait(10);
        pages.diaPages().takeMenuCount();

    }

    @When("user enters mail credentials for flow")
    public void userEntersMailCredentialsForFlow() {
        pages.diaPages().enterEmailCredentialsForFlow();
    }

    @Given("The user login tedarikci screen")
    public void theUserLoginTedarikciScreen() {
        pages.diaPages().tedarikciLogin();
    }


    @Given("Wait tedarik table")
    public void waitTedarikTable() {
        pages.diaPages().waitTedarikciTable();
    }
}
