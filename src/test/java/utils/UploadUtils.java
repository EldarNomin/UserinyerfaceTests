package utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.datatransfer.Clipboard;
import java.util.concurrent.TimeUnit;

public class UploadUtils {
    public static void uploadFile(File file) {
        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        int milliSeconds = 2000;

        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_META);

        robot.delay(milliSeconds);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(milliSeconds);
    }
}