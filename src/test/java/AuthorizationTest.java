import gmail.GmailLoginPage;
import gmail.GmailMailBoxPage;
import gmail.GmailPasswordPage;
import gmail.GmailSendLetter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;


/**
 * @author Nikolai Shilenko
 */
public class AuthorizationTest {
    private WebDriver driver;
    private GmailLoginPage gmailLoginPage;
    private GmailPasswordPage gmailPasswordPage;
    private GmailMailBoxPage gmailMailBoxPage;
    private GmailSendLetter gmailSendLetter;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1#identifier");
        gmailLoginPage = new GmailLoginPage(driver);
    }
    @Test
    public void authorize() {
        gmailLoginPage.typeUserEmail("sdettask@gmail.com");
        gmailPasswordPage = gmailLoginPage.clickNext();
        gmailPasswordPage.typeUserPassword("qwerty12345$");
        gmailMailBoxPage = gmailPasswordPage.clickNext();
        String header = gmailMailBoxPage.getHeader();
        Assert.assertEquals(header, "Meet");
    }
    @Test
    public void getCountLettersBySubject() {
        gmailLoginPage.typeUserEmail("sdettask@gmail.com");
        gmailPasswordPage = gmailLoginPage.clickNext();
        gmailPasswordPage.typeUserPassword("qwerty12345$");
        gmailMailBoxPage = gmailPasswordPage.clickNext();
        int count = gmailMailBoxPage.getCountLettersBySubject("Simbirsoft Тестовое задание");
        Assert.assertTrue(count == 6);
    }

    @Test
    public void sendLetter() {
        gmailLoginPage.typeUserEmail("sdettask@gmail.com");
        gmailPasswordPage = gmailLoginPage.clickNext();
        gmailPasswordPage.typeUserPassword("qwerty12345$");
        gmailMailBoxPage = gmailPasswordPage.clickNext();
        int count = gmailMailBoxPage.getCountLettersBySubject("Simbirsoft Тестовое задание");
        int before = gmailMailBoxPage.getCountLettersBySubject("Simbirsoft Тестовое задание. Шиленко");
        gmailSendLetter = new GmailSendLetter(driver);
        gmailSendLetter.clickNewLetterFormButton();
        gmailSendLetter.typeLetterRecipientField("sdettask@gmail.com");
        gmailSendLetter.typeLetterSubjectField("Simbirsoft Тестовое задание. Шиленко");
        gmailSendLetter.typeLetterTextField(String.valueOf(count));
        gmailSendLetter.clickSendButton();
        Assert.assertTrue(before < gmailMailBoxPage.getCountLettersBySubject("Simbirsoft Тестовое задание. Шиленко"));
    }
}
