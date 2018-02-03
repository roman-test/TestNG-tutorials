package IntergationTests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.URL;



public class TestClass1 {

    Reporter report;
    WebDriver driver;

    @BeforeTest
    public void init() throws Exception
    {
        report = new Reporter();
        driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),
                DesiredCapabilities.firefox());

    }

    @AfterTest
    public void end()
    {
        driver.quit();
    }

    @Test(description = "Проверка из класса 1")
    public void test1()
    {

        driver.get("https://yandex.ru");

        driver.findElement(By.cssSelector("#text")).sendKeys("Хочу!" + Keys.ENTER);

        report.log("Работает...class1)");
    }


}
