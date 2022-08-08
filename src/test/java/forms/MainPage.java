package forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private ILink link = getElementFactory().getLink(By.xpath("//div[@class='view__row']//a[@class='start__link']"), "start link");

    public MainPage() {
        super(By.xpath("//button[@type='button']"), "start button");
    }

    public void clickStartLink() {
        link.clickAndWait();
    }
}