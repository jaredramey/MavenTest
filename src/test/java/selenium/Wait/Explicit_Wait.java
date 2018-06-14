package selenium.Wait;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Explicit_Wait
{
    private static String DRIVERPATH_GECKO = "C:/Users/Jared/Desktop/Drivers/geckodriver.exe";
    private static String DRIVERPATH_CHROME = "C:/Users/Jared/Desktop/Drivers/chromedriver.exe";
    private static String DRIVERPATH_IE = "C:/Users/Jared/Desktop/Drivers/IEDriverServer.exe";
    private static String WEB_URL = "http://newtours.demoaut.com/";
    private static String USER = "Hoop"; //User/Pass needs to be updated each day
    private static String PASS = "Hoop";

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
        driver.findElement(By.name("userName")).sendKeys(USER);

        //Fill out password
        driver.findElement(By.name("password")).sendKeys(PASS);

        //Click on sign in button
        driver.findElement(By.name("login")).click();

        //Create and set Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 20);

        //Wait until element is present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("findFlights")));

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

        //Validate logo
        WebElement mercuryToursLogo = driver.findElement(By.xpath("//img[@src='/images/nav/logo.gif'][@alt='Mercury Tours']"));

        if(mercuryToursLogo.isDisplayed())
        {
            System.out.println("Logo is present and displayed.");
        }
        else
        {
            fail("Logo is not present");
        }

        //Validate rent a car image
        WebElement rentACarImg = driver.findElement(By.xpath("//img[@src='/images/nav/boxad1.gif']"));

        if(rentACarImg.isDisplayed())
        {
            System.out.println("Rent a car ad is present and displayed.");
        }
        else
        {
            fail("Rent a car ad is not present");
        }

        //Find the one way radio button
        WebElement tripType = driver.findElement(By.xpath("//input[@name='tripType'][@value='oneway']"));
        tripType.click();

        //Find the drop down
        WebElement passengerDropDown = driver.findElement(By.xpath("//select[@name='passCount']"));
        //Create select object
        Select selectPassengerDrop = new Select(passengerDropDown);
        //Select by visible text
        selectPassengerDrop.selectByValue("4");

        //Find the drop down
        WebElement fromDropDown = driver.findElement(By.xpath("//select[@name='fromPort']"));
        //Create select object
        Select fromDrop = new Select(fromDropDown);
        //Select by visible text
        fromDrop.selectByVisibleText("London");

        //Close window and destroy driver instance
        driver.quit();
    }
}
