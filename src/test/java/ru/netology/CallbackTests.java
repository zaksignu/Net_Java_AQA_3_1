package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.jupiter.api.Assertions.*;

public class CallbackTests {
    private WebDriver driver;

    @BeforeAll
    public static void firstSetUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUP() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
    @Order(1)
    @Test
    public void shouldWorkWithHappyPath() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.name("phone")).sendKeys("+79137451456");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.className("paragraph")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actual);
    }
    @Order(2)
    @Test
    public void shouldNotWorkWithEnglishName() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys("Ruuk Keept");
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_type_text .input__sub")).getText().trim();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expected, actual);
    }
    @Order(3)
    @Test
    public void shouldNotWorkWithNoSpacesRu() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys("Руук");
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_type_text .input__sub")).getText().trim();
        String expected = "Укажите точно как в паспорте";
        assertEquals(expected, actual);
    }
    @Order(4)
    @Test
    public void shouldNotWorkWithNoSpacesEng() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys("Ruuk");
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_type_text .input__sub")).getText().trim();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expected, actual);
    }
    @Order(5)
    @Test
    public void shouldNotWorkWithBlankInput() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys("");
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_type_text .input__sub")).getText().trim();
        String expected = "Поле обязательно для заполнения";
        assertEquals(expected, actual);
    }
    @Order(6)
    @Test
    public void shouldNotWorkWithSpaces() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys(" ");
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_type_text .input__sub")).getText().trim();
        String expected = "Поле обязательно для заполнения";
        assertEquals(expected, actual);
    }
    @Order(7)
    @Test
    public void shouldNotWorkWithNoPlus() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.name("phone")).sendKeys("89171723121");
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_type_tel  .input__sub")).getText().trim();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expected, actual);
    }
    @Order(8)
    @Test
    public void shouldNotWorkWithLessNumbers() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.name("phone")).sendKeys("+791717231");
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_type_tel  .input__sub")).getText().trim();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expected, actual);
    }
    @Order(9)
    @Test
    public void shouldNotWorkWithMoreNumbers() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.name("phone")).sendKeys("+791717231123123");
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_type_tel  .input__sub")).getText().trim();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expected, actual);
    }
    @Order(10)
    @Test
    public void shouldNotWorkCheckbox() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.name("name")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.name("phone")).sendKeys("+79137451456");
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.className("checkbox__text")).getCssValue("color");
        String expected = "rgba(255, 92, 92, 1)";
        assertEquals(expected, actual);
    }

    @AfterEach
    public void finishOne() {
        driver.quit();
        driver = null;

    }

}
