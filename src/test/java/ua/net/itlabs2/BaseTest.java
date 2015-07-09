package ua.net.itlabs2;

import com.codeborne.selenide.Configuration;

public class BaseTest {
    {
        Configuration.browser = System.getProperty("driver.browser");
    }
}
