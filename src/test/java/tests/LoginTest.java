package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;


    /*
    1.открыть страницу https://www.demoblaze.com/
    2. Нажать на кнопку "Log in"
    3. Ввести в поле Username "Arcady"
    4. Ввести в поле Password "Arcady"
    5. Нажать кнопку "Log in" В окне
    6. Проверить, что мы видим плашку "Welcome Arcady"
     */


public class LoginTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);


        driver.get("https://www.demoblaze.com/");

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        homePage.clickLoginLink();
        loginPage.enterUsername("Arcady");
        loginPage.enterPassword("Arcady");
        loginPage.clickLoginButton();

        String welcomeMessage = homePage.getWelcomeMessage();
        Assert.assertTrue(welcomeMessage.contains("Welcome Arcady"), "Login was not successful");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}