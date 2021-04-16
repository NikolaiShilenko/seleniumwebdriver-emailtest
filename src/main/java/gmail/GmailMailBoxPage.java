package gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author Nikolai Shilenko
 */
public class GmailMailBoxPage {

    private WebDriver driver;

    public GmailMailBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    private By header = By.className("YV");

    public int getCountLettersBySubject(String subject) {
        List<WebElement> list = driver.findElements(By.xpath("//tbody//span/*[text()= '" + subject + "']"));
        return list.size();
    }

    public String getHeader() {
        return driver.findElement(header).getText();
    }

}
