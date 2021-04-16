package gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Nikolai Shilenko
 */
public class GmailPasswordPage {

    private WebDriver driver;

    public GmailPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private By userPasswordField = By.name("password");
    private By nextButton = By.id("passwordNext");

    public GmailPasswordPage typeUserPassword(String password) {
        driver.findElement(userPasswordField).sendKeys(password);
        return this;
    }

    public GmailMailBoxPage clickNext() {
        driver.findElement(nextButton).click();
        return new GmailMailBoxPage(driver);
    }

}
