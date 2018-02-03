package IntergationTests;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.image.Kernel;
import java.net.URL;



public class TestClass2 {

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

    @Test(description = "Проверка из класса 2")
    public void test2222()
    {
        driver.get("https://google.ru");

        driver.findElement(By.cssSelector("#lst-ib")).sendKeys("Привет!" + Keys.ENTER);

        report.log("Работает...class2");
    }


}
