package com.bugathonframework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.Map;


public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Setup Chrome driver
        WebDriverManager.chromedriver().setup();

        // Configure ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // ⭐ Run in incognito (reduces password breach alerts)
        options.addArguments("--incognito");

        // ⭐ Use a clean automation profile (VERY IMPORTANT)
        options.addArguments("user-data-dir=C:/HyreNetAutomationProfile");

        // Disable password manager UI
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("prefs", Map.of(
            "credentials_enable_service", false,
            "profile.password_manager_enabled", false
        ));

        // Launch Chrome with options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Open HyreNet login page
        driver.get("https://app.hyrenet.in/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
