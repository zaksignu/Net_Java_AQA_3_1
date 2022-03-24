package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.util.Asserts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTests {
    private WebDriver driver;
    @BeforeAll
    public static void firstSetUp() {
       // System.setProperty("webdriver.chrome.driver","./driver/win/chromedriver.exe");

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUP() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
 //       driver = new ChromeDriver();

    }
    @Test
    public void shouldWork(){
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.name("phone")).sendKeys("+79167451456");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actual =  driver.findElement(By.className("paragraph")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected,actual);
      //  System.out.println("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.");
    }

//    @Test
//    public void shouldWork_1(){
//        driver.get("http://localhost:9999/");
//        System.out.println("");
//    }

    @AfterEach
    public void finishOne(){
        driver.quit();
        driver = null;

    }

}
