package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporter {

    private static ExtentReports extentReport;

    public static ExtentReports generateExtentReport() {
        if (extentReport == null) {
            extentReport = createExtentReport();
        }
        return extentReport;
    }

    private static ExtentReports createExtentReport() {
        ExtentReports extentReport = new ExtentReports();
        PropsLoader propsLoader = PropsLoader.getInstance("/src/main/java/config.properties");

        // Generate a timestamp for the report file
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());

        // Define the file path with the timestamp
        String reportFilePath = System.getProperty("user.dir") + "/src/main/reports/extentReport_" + timestamp + ".html";
        File extentReportFile = new File(reportFilePath);

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Gillette Test Report");
        sparkReporter.config().setDocumentTitle("Gillette Automation Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReport.attachReporter(sparkReporter);

        extentReport.setSystemInfo("Application URL", propsLoader.getProperty("url"));
        extentReport.setSystemInfo("Browser Name", propsLoader.getProperty("browserName"));
        extentReport.setSystemInfo("Email", propsLoader.getProperty("validEmail"));
        extentReport.setSystemInfo("Password", propsLoader.getProperty("validPassword"));
        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReport;
    }
}
