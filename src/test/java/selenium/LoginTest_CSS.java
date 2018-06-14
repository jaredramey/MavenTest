package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.fail;

public class LoginTest_CSS
{
    private static String DRIVERPATH_GECKO = "C:/Users/Jared/Desktop/Drivers/geckodriver.exe";
    private static String DRIVERPATH_CHROME = "C:/Users/Jared/Desktop/Drivers/chromedriver.exe";
    private static String DRIVERPATH_IE = "C:/Users/Jared/Desktop/Drivers/IEDriverServer.exe";
    private static String WEB_URL = "http://newtours.demoaut.com/";
    private static String USER = "Hup";
    private static String PASS = "Hup";

    @Test
    public void Login_Test_FF() throws InterruptedException {
        //Declare path to gecko driver
        System.setProperty("webdriver.gecko.driver", DRIVERPATH_GECKO);

        //Initialize selenium webdriver class and create object
        WebDriver driver = new FirefoxDriver();

        //Go to the website
        driver.get(WEB_URL);

        //Maximize window
        driver.manage().window().maximize();

        //Fill out username
        driver.findElement(By.cssSelector("input[name='userName']")).sendKeys(USER);

        //Fill out password
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(PASS);

        //Click on sign in button
        driver.findElement(By.cssSelector("input[Value='Login']")).click();

        //Wait 5 seconds for load time
        Thread.sleep(5000);

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

        //Close window and destroy driver instance
        driver.quit();
    }
}
