package selenium.Wait;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.fail;

public class Header_Automation
{
    private static String DRIVERPATH_GECKO = "C:/Users/Jared/Desktop/Drivers/geckodriver.exe";
    private static String DRIVERPATH_CHROME = "C:/Users/Jared/Desktop/Drivers/chromedriver.exe";
    private static String DRIVERPATH_IE = "C:/Users/Jared/Desktop/Drivers/IEDriverServer.exe";
    private static String WEB_URL = "https://www.virginia.gov/";

    @Test
    public void HomePage_Validation_FF() throws InterruptedException {
        //Declare path to gecko driver
        System.setProperty("webdriver.gecko.driver", DRIVERPATH_GECKO);

        //Initialize selenium webdriver class and create object
        WebDriver driver = new FirefoxDriver();

        //Go to the website
        driver.get(WEB_URL);

        //Maximize window
        driver.manage().window().maximize();

        //Validate H2 header
        WebElement header = driver.findElement(By.xpath("//h2[contains(text(), 'How Can Virginia Government Help You?')]"));

        if(header.isDisplayed())
        {
            System.out.println("Header is present and displayed");
        }
        else
        {
            fail("Header is not present");
        }

        //Validate text body
        WebElement textBody = driver.findElement(By.xpath("//p[contains(text(), 'Virginia State Bar provides information about attorneys licensed to practice law in the Commonwealth of Virginia.')]"));

        if(textBody.isDisplayed())
        {
            System.out.println("Text is displayed properly");
        }
        else
        {
            fail("Text is not displayed.");
        }

        //Close window and destroy driver instance
        driver.quit();
    }
}
