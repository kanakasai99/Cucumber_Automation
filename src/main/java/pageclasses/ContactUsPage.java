package pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {
    private static final Logger log = Logger.getLogger(ContactUsPage.class);
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Contact us')]")
    private WebElement contactUsButton;

    @FindBy(id = "id_contact")
    private WebElement subjectHeading;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "id_order")
    private WebElement orderReferenceInput;

    @FindBy(id = "message")
    private WebElement messageTextarea;

    @FindBy(id = "submitMessage")
    private WebElement sendButton;

    @FindBy(css = ".alert.alert-success")
    private WebElement successMessage;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("ContactUsPage initialized");
    }

    public void clickContactUs() {
        contactUsButton.click();
        log.info("Clicked on Contact Us button");
    }

    public void selectSubjectHeading(String subject) {
        Select select = new Select(subjectHeading);
        select.selectByVisibleText(subject);
        log.info("Selected subject heading: " + subject);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        log.info("Entered email: " + email);
    }

    public void enterOrderReference(String orderRef) {
        orderReferenceInput.clear();
        orderReferenceInput.sendKeys(orderRef);
        log.info("Entered order reference: " + orderRef);
    }

    public void enterMessage(String message) {
        messageTextarea.clear();
        messageTextarea.sendKeys(message);
        log.info("Entered message: " + message);
    }

    public void clickSend() {
        sendButton.click();
        log.info("Clicked Send button");
    }

    public void fillContactForm(String subject, String email, String orderRef, String message) {
        selectSubjectHeading(subject);
        enterEmail(email);
        enterOrderReference(orderRef);
        enterMessage(message);
        log.info("Filled contact form with provided data");
    }

    public String getSuccessMessage() {
        String msg = successMessage.getText();
        log.info("Success message retrieved: " + msg);
        return msg;
    }
}
