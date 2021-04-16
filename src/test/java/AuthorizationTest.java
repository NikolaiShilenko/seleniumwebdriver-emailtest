import gmail.GmailLoginPage;
import gmail.GmailMailBoxPage;
import gmail.GmailPasswordPage;
import gmail.GmailLetterSender;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * @author Nikolai Shilenko
 */
public class AuthorizationTest {
    private WebDriver driver;
    private GmailLoginPage gmailLoginPage;
    private GmailPasswordPage gmailPasswordPage;
    private GmailMailBoxPage gmailMailBoxPage;
    private GmailLetterSender gmailLetterSender;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        ChromeOptions capabilities = new ChromeOptions();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1#identifier");
        gmailLoginPage = new GmailLoginPage(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void authorize() {
        gmailLoginPage.typeUserEmail("sdettask@gmail.com");
        gmailPasswordPage = gmailLoginPage.clickNext();
        gmailPasswordPage.typeUserPassword("qwerty12345$");
        gmailMailBoxPage = gmailPasswordPage.clickNext();
        String header = gmailMailBoxPage.getHeader();
        Assertions.assertEquals(header, "Meet");
    }

    @Test
    public void getCountLettersBySubject() {
        gmailLoginPage.typeUserEmail("sdettask@gmail.com");
        gmailPasswordPage = gmailLoginPage.clickNext();
        gmailPasswordPage.typeUserPassword("qwerty12345$");
        gmailMailBoxPage = gmailPasswordPage.clickNext();
        int count = gmailMailBoxPage.getCountLettersBySubject("Simbirsoft Тестовое задание");
        Assertions.assertTrue(count == 6);
    }

    @Test
    public void sendLetter() {
        gmailLoginPage.typeUserEmail("sdettask@gmail.com");
        gmailPasswordPage = gmailLoginPage.clickNext();
        gmailPasswordPage.typeUserPassword("qwerty12345$");
        gmailMailBoxPage = gmailPasswordPage.clickNext();
        int count = gmailMailBoxPage.getCountLettersBySubject("Simbirsoft Тестовое задание");
        int beforeSending = gmailMailBoxPage.getCountLettersBySubject("Simbirsoft Тестовое задание. Шиленко");
        gmailLetterSender = new GmailLetterSender(driver);
        gmailLetterSender.clickNewLetterFormButton();
        gmailLetterSender.typeLetterRecipientField("sdettask@gmail.com");
        gmailLetterSender.typeLetterSubjectField("Simbirsoft Тестовое задание. Шиленко");
        gmailLetterSender.typeLetterTextField(String.valueOf(count));
        gmailLetterSender.clickSendButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()= 'Письмо отправлено.']")));
        int afterSending = gmailMailBoxPage.getCountLettersBySubject("Simbirsoft Тестовое задание. Шиленко");
        Assertions.assertTrue(beforeSending < afterSending);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
