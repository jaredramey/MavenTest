package selenium.DataDrivenFramework.Excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

import java.io.*;

import static org.junit.Assert.fail;

public class Data_Driven_Framework_Example1
{
    private static String DRIVERPATH_GECKO = "C:/Users/Jared/Desktop/Drivers/geckodriver.exe";
    private static String DRIVERPATH_CHROME = "C:/Users/Jared/Desktop/Drivers/chromedriver.exe";
    private static String DRIVERPATH_IE = "C:/Users/Jared/Desktop/Drivers/IEDriverServer.exe";
    private static String DATASHEETPATH_MERCURY = "C:/Users/Jared/IdeaProjects/MavenTest/MercuryTours_Data.xlsx";
    private static String WEB_URL = "http://newtours.demoaut.com/";
    private static String USER = "Hup";
    private static String PASS = "Hup";

    @Test
    public void Login_Test_FF() throws InterruptedException, IOException, InvalidFormatException {
        //Create data file object
        File dataFile = new File(DATASHEETPATH_MERCURY);

        //Create file input stream object
        FileInputStream inputStream = new FileInputStream(dataFile);

        //Create buffered input stream object
        BufferedInputStream buffer = new BufferedInputStream(inputStream);

        //Create an object for the workbook
        Workbook workBook = WorkbookFactory.create(buffer);

        //Create an object for the sheet
        Sheet sh1 = workBook.getSheet("Sheet1");

        //Count number of rows in data
        int firstRow = sh1.getFirstRowNum();
        int lastRow = sh1.getLastRowNum();
        System.out.println(String.format("%d tests being run off of %d rows in sheet.", lastRow, lastRow));

        //For loop to automate all test case scenarios
        for(int i = 1; i <= lastRow; i++)
        {
            //Cell var value
            int j = 0;
            //Create variables
            String userName = sh1.getRow(i).getCell(j).getStringCellValue();
            String password = sh1.getRow(i).getCell(j+1).getStringCellValue();
            String tripType = sh1.getRow(i).getCell(j+2).getStringCellValue();
            int numOfPassengers = (int) sh1.getRow(i).getCell(j+3).getNumericCellValue();
            String departCity = sh1.getRow(i).getCell(j+4).getStringCellValue();
            String departMonth = sh1.getRow(i).getCell(j+5).getStringCellValue();
            int departDay = (int) sh1.getRow(i).getCell(j+6).getNumericCellValue();
            String arrivalCity = sh1.getRow(i).getCell(j+7).getStringCellValue();
            String returnMonth = sh1.getRow(i).getCell(j+8).getStringCellValue();
            int returnDay = (int) sh1.getRow(i).getCell(j+9).getNumericCellValue();
            String serviceClass = sh1.getRow(i).getCell(j+10).getStringCellValue();
            String airline = sh1.getRow(i).getCell(j+11).getStringCellValue();

            //Declare path to gecko driver
            System.setProperty("webdriver.gecko.driver", DRIVERPATH_GECKO);

            //Initialize selenium webdriver class and create object
            WebDriver driver = new FirefoxDriver();

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
        }//End forloop
    }
}
