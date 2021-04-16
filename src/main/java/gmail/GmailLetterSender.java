package gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Nikolai Shilenko
 */
public class GmailLetterSender {

    private WebDriver driver;

    public GmailLetterSender(WebDriver driver) {
        this.driver = driver;
    }

    private By newLetterFormButton = By.xpath("//*[text()= 'Написать']");
    private By letterRecipientField = By.xpath("//textarea[contains(@aria-label, 'Кому')]");
    private By letterSubjectField = By.name("subjectbox");
    private By letterTextField = By.xpath("//*[@role=\"textbox\"]");
    private By sendButton = By.xpath("//*[@class= 'T-I J-J5-Ji aoO v7 T-I-atl L3']");

    public GmailLetterSender clickNewLetterFormButton() {
            driver.findElement(newLetterFormButton).click();
            return this;
    }

    public GmailLetterSender typeLetterRecipientField(String address) {
        driver.findElement(letterRecipientField).sendKeys(address);
        return this;
    }

    public GmailLetterSender typeLetterSubjectField(String subject) {
        driver.findElement(letterSubjectField).sendKeys(subject);
        return this;
    }

    public GmailLetterSender typeLetterTextField(String text) {
        driver.findElement(letterTextField).sendKeys(text);
        return this;
    }

    public void clickSendButton() {
        driver.findElement(sendButton).click();
    }

}
