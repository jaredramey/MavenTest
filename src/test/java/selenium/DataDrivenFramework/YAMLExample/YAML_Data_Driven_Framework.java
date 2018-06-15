package selenium.DataDrivenFramework.YAMLExample;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

import static org.junit.Assert.fail;

public class YAML_Data_Driven_Framework
{
    private static String DRIVERPATH_GECKO = "C:/Users/Jared/Desktop/Drivers/geckodriver.exe";
    private static String DRIVERPATH_CHROME = "C:/Users/Jared/Desktop/Drivers/chromedriver.exe";
    private static String DRIVERPATH_IE = "C:/Users/Jared/Desktop/Drivers/IEDriverServer.exe";
    private static String WEB_URL = "http://newtours.demoaut.com/";

    @Test
    public void Login_Test() throws InterruptedException, IOException, InvalidFormatException {
        //Create YAML object
        Yaml yaml = new Yaml();

        //Create file object
        File file = new File("src/Data.yaml");
        File configFile = new File("src/config.yaml");

        //Create input string
        InputStream inputStream = new FileInputStream(file);
        InputStream configInputStream = new FileInputStream(configFile);

        //Parse YAML file and create a series of map of lists
        Map<String, Map<String, Object>> data = (Map<String, Map<String, Object>>) yaml.load(inputStream);
        Map<String, Object> config = (Map<String, Object>) yaml.load(configInputStream);
        System.out.println(data);
        System.out.println(config);

        //Create variables for config
        String browserType = config.get("browser").toString();
        String[] recordList = config.get("record").toString().split(",");
        System.out.println(recordList);

        System.out.println(recordList.length);

        for(int i = 0; i < recordList.length; i++) {
            WebDriver driver = null;

            String userName = data.get(recordList[i]).get("username").toString();
            String password = data.get(recordList[i]).get("password").toString();
            String tripType = data.get(recordList[i]).get("TripType").toString();
            int numOfPassengers = Integer.parseInt(data.get(recordList[i]).get("NumofPass").toString());
            String departCity = data.get(recordList[i]).get("From").toString();
            String departMonth = data.get(recordList[i]).get("DepartMonth").toString();
            int departDay = Integer.parseInt(data.get(recordList[i]).get("DepartDate").toString());
            String arrivalCity = data.get(recordList[i]).get("ArrivalCity").toString();
            String returnMonth = data.get(recordList[i]).get("ReturnMonth").toString();
            int returnDay = Integer.parseInt(data.get(recordList[i]).get("ReturnDate").toString());
            String serviceClass = data.get(recordList[i]).get("ServiceClass").toString();
            String airline = data.get(recordList[i]).get("Airline").toString();

            if(browserType.equalsIgnoreCase("firefox")) {
                //Declare path to gecko driver
                System.setProperty("webdriver.gecko.driver", DRIVERPATH_GECKO);
                //Initialize selenium webdriver class and create object
                driver = new FirefoxDriver();
            }else if(browserType.equalsIgnoreCase("chrome")) {
                //Declare path to chrome driver
                System.setProperty("webdriver.chrome.driver", DRIVERPATH_CHROME);
                //Initialize selenium webdriver class and create object
                driver = new ChromeDriver();
            }else{
                //Declare path to gecko driver
                System.setProperty("webdriver.ie.driver", DRIVERPATH_IE);
                //Initialize selenium webdriver class and create object
                driver = new InternetExplorerDriver();
            }

            //Go to the website
            driver.get(WEB_URL);

            //Maximize window
            driver.manage().window().maximize();

            //Fill out username
            driver.findElement(By.name("userName")).sendKeys(userName);

            //Fill out password
            driver.findElement(By.name("password")).sendKeys(password);

            //Click on sign in button
            driver.findElement(By.name("login")).click();

            //Create and set Explicit wait
            WebDriverWait wait = new WebDriverWait(driver, 20);

            //Wait until element is present
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("findFlights")));

            //Print out title
            System.out.println(driver.getTitle());

            //Validate title
            if (driver.getTitle().equals("Find a Flight: Mercury Tours:")) {
                System.out.println("Login test passed");
            } else {
              System.out.println("Login test failed");
              fail("Login test failed - Page title not verified.");
            }

            //Select the trip type
            WebElement trip_Type = driver.findElement(By.xpath("//input[@name='tripType'][@value=\'" + tripType + "\']"));
            trip_Type.click();

            //Select passenger dropdown
            WebElement passengerDropDown = driver.findElement(By.xpath("//select[@name='passCount']"));
            //Create an object for the select drop down
            Select passengerSelectDropDown = new Select(passengerDropDown);
            //Selecting number of passengers
            passengerSelectDropDown.selectByVisibleText(String.valueOf(numOfPassengers));

            //Select depart city dropdown
            WebElement departFromDropDown = driver.findElement(By.xpath("//select[@name='fromPort']"));
            //Create an object for the select drop down
            Select departFromSelectDropDown = new Select(departFromDropDown);
            //Selecting departing city
            departFromSelectDropDown.selectByVisibleText(departCity);

            //Select depart month
            WebElement departMonthDropDown = driver.findElement(By.xpath("//select[@name='fromMonth']"));
            //Create an object for the select drop down
            Select departMonthSelectDropDown = new Select(departMonthDropDown);
            //Selecting number of passengers
            departMonthSelectDropDown.selectByVisibleText(departMonth);

            //Select depart day
            WebElement departDayDropDown = driver.findElement(By.xpath("//select[@name='fromDay']"));
            //Create an object for the select drop down
            Select departDaySelectDropDown = new Select(departDayDropDown);
            //Selecting departure day
            departDaySelectDropDown.selectByVisibleText(String.valueOf(departDay));

            //Select arrival city dropdown
            WebElement arrivalDropDown = driver.findElement(By.xpath("//select[@name='toPort']"));
            //Create an object for the select drop down
            Select arrivalSelectDropDown = new Select(arrivalDropDown);
            //Selecting arrival city
            arrivalSelectDropDown.selectByVisibleText(arrivalCity);

            //Select return month drop down
            WebElement returnMonthDropDown = driver.findElement(By.xpath("//select[@name='toMonth']"));
            //Create an object for the select drop down
            Select returnMonthSelectDropDown = new Select(returnMonthDropDown);
            //Selecting return month
            returnMonthSelectDropDown.selectByVisibleText(returnMonth);

            //Select return day dropdown
            WebElement returnDayDropDown = driver.findElement(By.xpath("//select[@name='toDay']"));
            //Create an object for the select drop down
            Select returnDaySelectDropDown = new Select(returnDayDropDown);
            //Selecting return day
            returnDaySelectDropDown.selectByVisibleText(String.valueOf(returnDay));

            //Selecting service type
            WebElement service = driver.findElement(By.xpath("//input[@name='servClass'][@value=\'" + serviceClass + "\']"));
            service.click();

            //Select airline dropdown
            WebElement airlineDropDown = driver.findElement(By.xpath("//select[@name='airline']"));
            //Create an object for the select drop down
            Select airlineSelectDropDown = new Select(airlineDropDown);
            //Selecting airline preference
            airlineSelectDropDown.selectByVisibleText(airline);

            //Close window and destroy driver instance
            driver.quit();
        }
    }
}
