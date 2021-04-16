package gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Nikolai Shilenko
 */
public class GmailLoginPage {

    private WebDriver driver;

    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By userEmailField = By.id("identifierId");
    private By nextButton = By.id("identifierNext");

    public GmailLoginPage typeUserEmail(String email) {
        driver.findElement(userEmailField).sendKeys(email);
        return this;
    }

    public GmailPasswordPage clickNext() {
        driver.findElement(nextButton).click();
        return new GmailPasswordPage(driver);
    }

}
