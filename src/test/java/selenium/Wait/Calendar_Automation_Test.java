package selenium.Wait;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.fail;

public class Calendar_Automation_Test
{
    private static String DRIVERPATH_GECKO = "C:/Users/Jared/Desktop/Drivers/geckodriver.exe";
    private static String WEB_URL = "https://www.orbitz.com/";

    @Test
    public void HomePage_Validation_FF() throws InterruptedException {
        //Declare path to gecko driver
        System.setProperty("webdriver.gecko.driver", DRIVERPATH_GECKO);

        //Initialize selenium webdriver class and create object
        WebDriver driver = new FirefoxDriver();

        //Go to the website
        driver.get(WEB_URL);

        Thread.sleep(15000);

        //Maximize window
        driver.manage().window().maximize();

        //Click to bring up calendar
        WebElement departTextBox = driver.findElement(By.xpath("//input[@id='package-departing-hp-package']"));
        departTextBox.click();

        //Wait until calendar obj is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='datepicker-cal-weeks']")));

        //Create a calendar obj
        WebElement calendarObject = driver.findElement(By.xpath("//table[@class='datepicker-cal-weeks']"));

        //Create a list for the webElement
        List<WebElement> departList = calendarObject.findElements(By.tagName("td"));

        String departDate = "15";
        //Loop through days
        for(WebElement cell : departList)
        {
            if(cell.getText().equals(departDate))
            {
                System.out.println("Cell found");
                cell.click();
                break;
            }
        }



        //Close window and destroy driver instance
        driver.quit();
    }
}
