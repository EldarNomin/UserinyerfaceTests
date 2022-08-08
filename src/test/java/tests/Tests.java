package tests;

import aquality.selenium.core.logging.Logger;
import forms.InterestsCard;
import forms.CardsPage;
import forms.MainPage;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.steps.AuthorizationCardSteps;
import utils.RandomUtils;

public class Tests extends BaseTest {
    private static final ISettingsFile TEST_DATA = new JsonSettingsFile("TestData.json");
    private final MainPage mainPage = new MainPage();
    private final CardsPage cardsPage = new CardsPage();
    private final InterestsCard interestsCard = new InterestsCard();
    private final AuthorizationCardSteps authorizationCardSteps = new AuthorizationCardSteps();
    private final RandomUtils randomUtils = new RandomUtils();
    private final String email = TEST_DATA.getValue("/email").toString();
    private final Logger log = AqualityServices.getLogger();

    @Test
    public void testCase1() {
        Assert.assertTrue(mainPage.state().isDisplayed(), "Error, welcome page is not displayed");
        log.info("Click the link (in text 'Please click HERE to GO to the next page') to navigate the next page.");
        mainPage.clickStartLink();
        Assert.assertEquals(cardsPage.getNumberPage(), TEST_DATA.getValue("/numberCard1").toString(), "Wrong page number!");
        log.info("Choose 3 random interest, upload image, click Next button.");
        authorizationCardSteps.setPasswordAndEmail(email, randomUtils.generatePassword(email));
        Assert.assertEquals(cardsPage.getNumberPage(), TEST_DATA.getValue("/numberCard2").toString(), "Wrong page number!");
        interestsCard.unselectDifferentInterests(3);
        interestsCard.uploadImage();
        interestsCard.goToNextCard();
        Assert.assertEquals(cardsPage.getNumberPage(), TEST_DATA.getValue("/numberCard3").toString(), "Wrong page number!");
    }

    @Test
    public void testCase2() {
        Assert.assertTrue(mainPage.state().isDisplayed(), "Error, welcome page is not displayed");
        log.info("Click HERE to GO to the next page)");
        mainPage.clickStartLink();
        Assert.assertEquals(cardsPage.getNumberPage(), TEST_DATA.getValue("/numberCard1").toString(), "Wrong page number!");
        log.info("Hide Help form");
        cardsPage.hideHelpWindowForm();
        Assert.assertTrue(cardsPage.isHelpFormHidden());
    }

    @Test
    public void testCase3() {
        Assert.assertTrue(mainPage.state().isDisplayed(), "Error, welcome page is not displayed");
        log.info("Click HERE to GO to the next page)");
        mainPage.clickStartLink();
        Assert.assertEquals(cardsPage.getNumberPage(), TEST_DATA.getValue("/numberCard1").toString(), "Wrong page number!");
        log.info("Accept cookie");
        cardsPage.waitCookiesBanner();
        cardsPage.acceptCookies();
        Assert.assertFalse(cardsPage.isCookiesAccepted(), "Cookies not accepted yet");
    }

    @Test
    public void testCase4() {
        Assert.assertTrue(mainPage.state().isDisplayed(), "Error, welcome page is not displayed");
        log.info("Click HERE to GO to the next page)");
        mainPage.clickStartLink();
        Assert.assertEquals(cardsPage.getNumberPage(), TEST_DATA.getValue("/numberCard1").toString(), "Wrong page number!");
        log.info("Validate that timer starts from 00");
        Assert.assertEquals(cardsPage.getTimerValue(), TEST_DATA.getValue("/timerText").toString(), "Timer not starts at 00!");
    }
}