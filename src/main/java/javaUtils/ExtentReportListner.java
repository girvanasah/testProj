package javaUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportListner implements ITestListener {

	private static final String fileSeperator = System.getProperty("file.separator");
	private static final String reportFileName = "API-Test-Report" + ".html";
	private static final String reportFilepath = System.getProperty("user.dir") + fileSeperator;
	private static final String dtFolder = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ms").format(new Date());
	private static final String reportFileLocation = reportFilepath + fileSeperator + "test-output" + fileSeperator
			+ "reports" + fileSeperator + dtFolder+ reportFileName;
	protected static ExtentReports reports;
	protected static ExtentTest test;

	public void onTestStart(ITestResult result) {

		test = reports.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, "Starting Method :: " +result.getMethod().getMethodName());
		System.out.println(result.getTestClass().getTestName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, "Test is pass: " +result.getMethod().getMethodName());

	}

	public void onTestFailure(ITestResult result) {
		test.log(LogStatus.FAIL, "Test is fail: " +result.getMethod().getMethodName());

	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "Test is skipped: " +result.getMethod().getMethodName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		System.out.println("ReportLocation: " + reportFileLocation);
		reports = new ExtentReports(reportFileLocation);
		test = reports.startTest("");

	}

	public void onFinish(ITestContext context) {
		reports.endTest(test);
		reports.flush();

	}
	
	public static void logInfo(String message) {
		test.log(LogStatus.INFO, message);
	}

}
