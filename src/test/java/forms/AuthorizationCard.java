package forms;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;
import static aquality.selenium.elements.ElementType.TEXTBOX;
import static utils.RandomUtils.*;

public class AuthorizationCard extends Form {
    public AuthorizationCard() {
        super(By.xpath("//form//div[@class='page-indicator']"), "page indicator");
    }

    private final ITextBox textBoxPassword = getElementFactory().getTextBox(By.xpath("//div[@class='login-form__field-row']/input"),
            "password");
    private final ITextBox textBoxEmail =
            getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"),
                    "email");
    private final ITextBox textBoxDomain =
            getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"),
                    "domain");
    private final ITextBox comboBoxDomainCountry =
            getElementFactory().getTextBox(By.className("dropdown__field"), "dropdown opener");
    private final ICheckBox checkBoxAccept =
            getElementFactory().getCheckBox(By.className("checkbox"), "checkbox accept Terms & Conditions");
    private final ILink linkNext =
            getElementFactory().getLink(By.className("button--secondary"), "button next");
    private final String locatorOfDomainEmail = "//div[@class='dropdown__list-item'][%s]";

    public void enterPassword(String password) {
        textBoxPassword.clearAndType(password);
    }

    public void enterEmail(String email) {
        textBoxEmail.clearAndType(getEmail(email));
    }

    public void enterDomain(String email) {
        textBoxDomain.clearAndType(getDomain(email));
    }

    public void clickDropDownOpener() {
        comboBoxDomainCountry.click();
    }

    public ITextBox getDomainOfEmail(String email) {
        List<ITextBox> domains =
                getElementFactory().findElements(By.className("dropdown__list-item"), "Domains", TEXTBOX);
        for (int i = 0; i < domains.size(); i++) {
            if (domains.get(i).getText().equals(getDropDownOpener(email))) {
                return getElementFactory().getTextBox(By.xpath
                        (String.format(locatorOfDomainEmail, i + 1)), "DomainEmail");
            }
        }
        Logger.getInstance().error("Incorrect Email");
        return null;
    }

    public void acceptTermsConditions() {
        checkBoxAccept.check();
    }

    public void goToNextCard() {
        linkNext.clickAndWait();
    }
}