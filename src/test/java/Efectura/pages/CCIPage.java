package Efectura.pages;

import Efectura.utilities.BrowserUtils;
import Efectura.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static Efectura.utilities.BrowserUtils.isElementDisplayed;


public class CCIPage extends BasePage {

    @FindBy(xpath = "//p[text()='Log in with Elastic Cloud']")
    private WebElement elasticOptions;

    @FindBy(xpath = "//div[text()='Microsoft']")
    private WebElement elasticMicrosoft;
    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameInputBox;

    @FindBy(xpath = "//h2[text()='No results match your search criteria']")
    private WebElement noMatchingInfo;

    @FindBy(xpath = "//div/div/strong")
    private WebElement numberOfFilteredData;

    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement recipientsInputBox;

    @FindBy(xpath = "//input[@placeholder='Subject']")
    private WebElement subjectInputBox;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private WebElement messageBodyInputBox;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInputBoxInCciElastic;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInputBoxInCciElastic;

    @FindBy(xpath = "//span[text()='Log in']")
    private WebElement loginButtonInCciElastic;

    public void cciLoginElastic() {
        Driver.getDriver().get("https://commercial-elk-prod.kb.europe-west1.gcp.cloud.es.io:9243/login");
        elasticOptions.click();
        BrowserUtils.wait(5);
        elasticMicrosoft.click();
        BrowserUtils.wait(10);
    }

    public void cciElasticFletumServices() {
        Driver.getDriver().get("https://commercial-elk-prod.kb.europe-west1.gcp.cloud.es.io:9243/app/discover#/?_g=(filters:!(),refreshInterval:(pause:!t,value:60000),time:(from:now-24h%2Fh,to:now))&_a=(columns:!(),filters:!(),index:a97a0c4b-b5a1-4768-8c21-759e117c4885,interval:auto,query:(language:kuery,query:'level:%20%22error%22'),sort:!(!(timeStamp,desc)))");

    }

    List<String> results = new ArrayList<>();
    public void verify(String service) {
        BrowserUtils.wait(2);
        if (!isElementDisplayed(noMatchingInfo)) {
            results.add(service + ": Error Number: " + numberOfFilteredData.getText());
        } else {
            results.add(service + " : SUCCESS");
            Assert.assertTrue(noMatchingInfo.isDisplayed());
        }
    }

    public String getEmailMessageBody() {
        StringBuilder emailBody = new StringBuilder();
        for (String result : results) {
            emailBody.append(result).append("\n");
        }
        System.out.println(emailBody);
        return emailBody.toString();
    }

    public void enterEmailCredentials() {
        BrowserUtils.wait(3);
        recipientsInputBox.sendKeys("fatih.kara@efectura.com, emre.kurt@efectura.com, cem@efectura.com");
        subjectInputBox.sendKeys("Environment Elastic, Flows And Tedarik Screen Control");
        System.out.println("Results: ");
        System.out.println(getEmailMessageBody());
        //System.out.println(getEmailMessageBodyForFlow());
        messageBodyInputBox.sendKeys(getEmailMessageBody());
        //messageBodyInputBox.sendKeys(getEmailMessageBodyForFlow());
    }
}
