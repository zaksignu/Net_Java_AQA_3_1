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
        driver.get("http://localhost:9999/");
    }

    @Test
    public void shouldWorkWithHappyPath() {
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__inner .input__control")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__inner .input__control")).sendKeys("+79137451456");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success'] ")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotWorkWithEnglishName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__inner .input__control")).sendKeys("Ruuk Kert");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__inner .input__control")).sendKeys("+79137451456");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText().trim();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotWorkWithNoSpacesEng() {
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__inner .input__control")).sendKeys("Ruuk");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__inner .input__control")).sendKeys("+79137451456");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText().trim();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotWorkWithBlankInput() {
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__inner .input__control")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__inner .input__control")).sendKeys("+79137451456");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText().trim();
        String expected = "Поле обязательно для заполнения";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotWorkWithSpaces() {
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__inner .input__control")).sendKeys(" ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__inner .input__control")).sendKeys("+79137451456");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText().trim();
        String expected = "Поле обязательно для заполнения";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotWorkWithNoPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__inner .input__control")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__inner .input__control")).sendKeys("89171723121");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText().trim();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotWorkWithLessNumbers() {
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__inner .input__control")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__inner .input__control")).sendKeys("+791717231");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText().trim();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotWorkWithMoreNumbers() {
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__inner .input__control")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__inner .input__control")).sendKeys("+791717231123123");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".input_invalid .input__sub")).getText().trim();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotWorkCheckbox() {
        driver.findElement(By.cssSelector("[data-test-id='name'] .input__inner .input__control")).sendKeys("Рууукк Кеерт");
        driver.findElement(By.cssSelector("[data-test-id='phone'] .input__inner .input__control")).sendKeys("+79137451456");
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector(".form-field .input_invalid .checkbox__text")).getText().trim();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        assertEquals(expected, actual);
    }

    @AfterEach
    public void finishOne() {
        driver.quit();
        driver = null;

    }

}
