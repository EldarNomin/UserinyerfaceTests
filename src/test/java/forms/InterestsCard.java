package forms;

import utils.PropertiesRead;
import utils.UploadUtils;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class InterestsCard extends Form {
    private final UploadUtils uploadUtils = new UploadUtils();
    private final IButton lUploadAvatar = getElementFactory().getButton(By.xpath("//p//a[@class='avatar-and-interests__upload-button']"), "link for upload avatar");
    private final IButton buttonNext = getElementFactory().getButton(By.xpath("//div[@class='avatar-and-interests-page__buttons-row']//button[contains(@class,'button--white')]"), "button next");
    private final String xpathToCheckBoxesInterests = "//div[@class='avatar-and-interests__interests-list']//label";
    private final static String FILTER_UNSELECT_ALL = "interest_unselectall";

    public InterestsCard() {
        super(By.xpath("//div[@class='page-indicator']"), "page indicator");
    }

    private static File file = new File(PropertiesRead.readFromDataConfig("avatarFile"));

    private List<ICheckBox> getCheckBoxesInterests() {
        return getElementFactory().findElements(By.xpath(xpathToCheckBoxesInterests), ElementType.CHECKBOX);
    }

    private ICheckBox getCheckBoxUnselectAll() {
        List<ICheckBox> checkBoxesInterests = getCheckBoxesInterests();
        return checkBoxesInterests.stream().filter(checkBox -> FILTER_UNSELECT_ALL.equals(checkBox.getAttribute("for"))).findFirst().orElseThrow();
    }

    private List<ICheckBox> checkUnselectInterest() {
        List<ICheckBox> checkBoxesInterests = getCheckBoxesInterests();
        checkBoxesInterests.removeIf(checkBox -> Objects.equals(checkBox.getAttribute("for"), FILTER_UNSELECT_ALL));
        return checkBoxesInterests;
    }

    private void unselectAll() {
        getCheckBoxUnselectAll().clickAndWait();
    }

    public void unselectDifferentInterests(int number) {
        Random random = new Random();
        List<ICheckBox> checkBoxesInterests = checkUnselectInterest();
        unselectAll();

        for (int i = 0; i < number; i++) {
            checkBoxesInterests.remove(random.nextInt(checkBoxesInterests.size())).clickAndWait();
        }
    }

    public void uploadImage() {
        lUploadAvatar.clickAndWait();
        uploadUtils.uploadFile(file);
    }

    public void goToNextCard() {
        buttonNext.clickAndWait();
    }
}