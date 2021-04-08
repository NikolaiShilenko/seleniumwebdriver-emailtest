import gmail.GmailLoginPage;
import gmail.GmailMailBoxPage;
import gmail.GmailPasswordPage;
import gmail.GmailSendLetter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Nikolai Shilenko
 */
public class MainClass {
    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1#identifier");
        GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
        gmailLoginPage.typeUserEmail("sdettask@gmail.com");
        GmailPasswordPage gmailPasswordPage = gmailLoginPage.clickNext();
        gmailPasswordPage.typeUserPassword("qwerty12345$");
        GmailMailBoxPage gmailMailBoxPage = gmailPasswordPage.clickNext();
        int lettersCounter = gmailMailBoxPage.getCountLettersBySubject("Simbirsoft Тестовое задание");
        GmailSendLetter gmailSendLetter = new GmailSendLetter(driver);
        gmailSendLetter.clickNewLetterFormButton();
        gmailSendLetter.typeLetterRecipientField("sdettask@gmail.com");
        gmailSendLetter.typeLetterSubjectField("Simbirsoft Тестовое задание. Шиленко");
        gmailSendLetter.typeLetterTextField(String.valueOf(lettersCounter));
        gmailSendLetter.clickSendButton();
    }
}
