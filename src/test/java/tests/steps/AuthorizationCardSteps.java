package tests.steps;

import forms.AuthorizationCard;

public class AuthorizationCardSteps {
    private final AuthorizationCard authorizationCard = new AuthorizationCard();

    public void setPasswordAndEmail(String email, String password) {
        authorizationCard.enterPassword(password);
        authorizationCard.enterEmail(email);
        authorizationCard.enterDomain(email);
        authorizationCard.clickDropDownOpener();
        authorizationCard.getDomainOfEmail(email).click();
        authorizationCard.acceptTermsConditions();
        authorizationCard.goToNextCard();
    }
}