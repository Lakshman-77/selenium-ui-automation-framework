package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    private static final ExtentReports extent = createInstance();
    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    private static ExtentReports createInstance() {

        ExtentSparkReporter spark =
                new ExtentSparkReporter("test-output/extent-report.html");

        spark.config().setReportName("UI Automation - SauceDemo");
        spark.config().setDocumentTitle("UI Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(spark);

        extentReports.setSystemInfo("Application", "SauceDemo");
        extentReports.setSystemInfo("Environment", "https://www.saucedemo.com");
        extentReports.setSystemInfo("Browser", "Chrome");

        return extentReports;
    }

    @Override
    public void onStart(ITestContext context) {
        // Report initialized only once.
    }

    @Override
    public void onTestStart(ITestResult result) {

        String description = result.getMethod().getDescription();

        ExtentTest test = extent.createTest(
                result.getMethod().getMethodName(),
                description);

        currentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        currentTest.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        currentTest.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}