package gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

/**
 * @author Nikolai Shilenko
 */
public class GmailMailBoxPage {
    private WebDriver driver;
    public GmailMailBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    private By header = By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]");

    public int getCountLettersBySubject(String subject) {
        List<WebElement> list = driver.findElements(By.xpath("//tbody//span/*[text()= '" + subject + "']"));
        return list.size();
    }
    public String getHeader() {
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
        return driver.findElement(header).getText();
    }
}
