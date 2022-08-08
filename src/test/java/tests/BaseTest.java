package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected Logger log = AqualityServices.getLogger();
    protected static final ISettingsFile TEST_DATA = new JsonSettingsFile("TestData.json");
    protected static final String url = TEST_DATA.getValue("/mainPage").toString();

    @BeforeMethod
    protected void beforeMethod() {
        log.info(String.format("Go to the %s", url));
        getBrowser().goTo(TEST_DATA.getValue("/URL").toString());
        getBrowser().maximize();
        getBrowser().waitForPageToLoad();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            getBrowser().quit();
        }
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}