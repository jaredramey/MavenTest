package selenium.DockerSwarm;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.fail;

public class Swarm_Test_FireFox3
{
    private static String USER = "Hoop";
    private static String PASS = "Hoop";

    WebDriver driver = null;

    @BeforeSuite(description = "Runs before your tests", groups = {"Smoke Test", "Integration Test"})
    public void beforeSuite() throws MalformedURLException {
        //Configure the Hub URL
        String hubURL = "http://192.168.99.102:4444/wd/hub";

        //Create a desired capabilities object
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();

        //Set platform
        capabilities.setPlatform(Platform.ANY);

        //Set the name
        capabilities.setBrowserName("firefox");

        //Accept certs from http
        capabilities.acceptInsecureCerts();

        //Create a webdriver object
        driver = new RemoteWebDriver(new URL(hubURL), capabilities);

        //Go to the website
        driver.get("https://newtours.herokuapp.com/");

        //Maximize window
        driver.manage().window().maximize();
    }

    @Test(description = "Login user", groups = {"Smoke Test", "Integration Test"})
    public void Login_Test_FF() throws InterruptedException {

        //Fill out username
        driver.findElement(By.name("userName")).sendKeys(USER);

        //Fill out password
        driver.findElement(By.name("password")).sendKeys(PASS);

        //Click on sign in button
        driver.findElement(By.name("login")).click();

        //Create and set Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 60);

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
