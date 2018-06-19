package selenium.TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class LoginTest_TestNG_Parameterized
{
    private static String DRIVERPATH_GECKO = "C:/Users/Jared/Desktop/Drivers/geckodriver.exe";
    private static String DRIVERPATH_CHROME = "C:/Users/Jared/Desktop/Drivers/chromedriver.exe";
    private static String DRIVERPATH_IE = "C:/Users/Jared/Desktop/Drivers/IEDriverServer.exe";
    private static String WEB_URL = "http://newtours.demoaut.com/";
    private static String USER = "Hoop";
    private static String PASS = "Hoop";

    WebDriver driver = null;

    @BeforeSuite(description = "Runs before your tests", groups = {"Smoke Test", "Integration Test"})
    public void beforeSuite()
    {
        //Declare path to gecko driver
        System.setProperty("webdriver.gecko.driver", DRIVERPATH_GECKO);

        //Initialize selenium webdriver class and create object
        driver = new FirefoxDriver();

        //Go to the website
        driver.get(WEB_URL);

        //Maximize window
        driver.manage().window().maximize();
    }

    @Test(description = "Login user", groups = {"Smoke Test", "Integration Test"})
    @Parameters({"username", "password"})
    public void Login_Test_FF(String username, String password) throws InterruptedException {

        //Fill out username
        driver.findElement(By.name("userName")).sendKeys(username);

        //Fill out password
        driver.findElement(By.name("password")).sendKeys(password);

        //Click on sign in button
        driver.findElement(By.name("login")).click();

        //Create and set Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 20);

        //Wait until element is present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("findFlights")));
    }

    @Test(description = "Validate page title", groups = {"Integration Test"})
    public void ValidateTitle()
    {
        //Print out title
        System.out.println(driver.getTitle());

        //Validate title
        if(driver.getTitle().equals("Find a Flight: Mercury Tours:"))
        {
            System.out.println("Login test passed");
        }
        else
        {
            System.out.println("Login test failed");
            fail("Login test failed - Page title not verified.");
        }
    }

    @AfterSuite(description = "Close browser", groups = {"Smoke Test", "Integration Test"})
    public void afterSuite()
    {
        //Close window and destroy driver instance
        driver.quit();
    }
}
