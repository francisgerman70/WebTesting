import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UVicTest {

    WebDriver browser;

    @BeforeEach
    public void setUp() {
        // Chrome
        //System.setProperty("webdriver.chrome.driver", "*****LOCATION OF YOUR WEBDRIVER*****");
        //browser = new ChromeDriver();

        // Firefox
        // System.setProperty("webdriver.gecko.driver", "*****LOCATION OF YOUR WEBDRIVER*****");
        // browser = new FirefoxDriver();

        // Safari
        //browser = new SafariDriver();
        System.setProperty("webdriver.chrome.driver", "/Users/ownerone/Documents/chromedriver-3");
        browser = new ChromeDriver();

        browser.manage().window().maximize();
    }

    @AfterEach
    public void cleanUp() {
        browser.quit();
    }


    // Your tests go here
    @Test
    public void UVICPageLoads() {
        browser.get("https://www.uvic.ca");
        assertEquals("Home - University of Victoria", browser.getTitle());
    }

    @Test
    public void UVICContainsSearchButton() {
        browser.get("https://www.uvic.ca");
        WebElement inputButton = browser.findElement(By.xpath("//*[@id=\"search-btn\"]/i"));                    // by name - this works
        // WebElement inputBox = browser.findElement(By.className("gLFyf gsfi"));   // by className - this fails
        // WebElement inputBox = browser.findElement(By.cssSelector(".gLFyf"));     // by cssSelector (aka style) - this works
        // WebElement inputBox = browser.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input"));
        // by xpath - this works
        assertTrue(inputButton.isEnabled());
    }
    @Test
    public void UVICSearchBoxAppears() {
        browser.get("https://www.uvic.ca");
        //WebElement searchButton = browser.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[3]/center/input[1]"));
        WebElement inputButton = browser.findElement(By.xpath("//*[@id=\"search-btn\"]/i"));

        WebElement inputBox = browser.findElement(By.xpath("//*[@id=\"searchUVic\"]"));
        inputButton.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.visibilityOf(inputBox));
        assertTrue(inputBox.isDisplayed());
    }
    @Test
    public void uvicSearchBoxFilled() {
        browser.get("https://www.uvic.ca");
        WebElement inputButton = browser.findElement(By.xpath("//*[@id=\"search-btn\"]"));
        WebElement inputBox = browser.findElement(By.xpath("//*[@id=\"searchUVic\"]"));
        inputButton.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.visibilityOf(inputBox));
        inputBox.sendKeys("csc");
        assertEquals("csc", inputBox.getAttribute("value"));
    }
    @Test
    public void uvicSearchResultsAppear() {
        browser.get("https://www.uvic.ca");
        WebElement inputButton = browser.findElement(By.xpath("//*[@id=\"search-btn\"]"));
        WebElement inputBox = browser.findElement(By.xpath("//*[@id=\"searchUVic\"]"));
        WebElement SearchButton = browser.findElement(By.xpath("//*[@id=\"searchMain\"]/div/div/form/div/button"));
        inputButton.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.visibilityOf(inputBox));
        inputBox.sendKeys("csc");
        SearchButton.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.titleIs("Search - University of Victoria"));
        assertEquals("Search - University of Victoria", browser.getTitle());
    }

    @Test
    public void UVICPhoneNumber() {
        browser.get("https://www.uvic.ca");
        assertEquals("Home - University of Victoria", browser.getTitle());
        WebElement phoneNum = browser.findElement(By.xpath("/html/body/footer/div/div[3]/div/div/div[2]/div/div[1]/ul/li[1]/a"));
        assertTrue(phoneNum.isDisplayed());
    }


}
