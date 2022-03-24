package ru.netology;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CallbackTests {
    private WebDriver driver;
    @BeforeAll
    public static void firstSetUp() {
        System.setProperty("webdriver.chrome.driver","./driver/win/chromedriver.exe");
    }

    @BeforeEach
    public void setUP() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void finishOne(){

    }

}
