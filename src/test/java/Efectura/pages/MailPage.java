package Efectura.pages;

import Efectura.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailPage extends BasePage{

    @FindBy(xpath = "//span[contains(text(),'Efectura-QA')]")
    private WebElement efecturaMailGroup;

    @FindBy(xpath = "//span[contains(text(),'Deneme')]")
    private WebElement denemeMailGroup;

    @FindBy(xpath = "//span[contains(text(),'E-posta gönder')]")
    private WebElement sendMailLink;






}
