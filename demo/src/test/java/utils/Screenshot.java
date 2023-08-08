package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Screenshot {
    public String captureScreenshot(WebDriver driver, String testName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
        
        // Define the screenshots directory path
        String screenshotsDirectory = System.getProperty("user.dir") + "/src/main/screenshots/";
        
        // Create the screenshots directory if it doesn't exist
        File directory = new File(screenshotsDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotPath = screenshotsDirectory + testName + "_" + timestamp + ".png";

        try {
            FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destinationScreenshotPath;
    }
}
