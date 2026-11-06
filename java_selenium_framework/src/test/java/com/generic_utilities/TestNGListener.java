package com.generic_utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

	/**
	 * Reports the outcome of each TestNG test method to the execution console.
	 * Failure screenshots are captured by BaseClass during its teardown, before
	 * the WebDriver session is closed.
	 *
	 * @author Reshab Patel
	 */
	public class TestNGListener implements ITestListener {

	    /** Logs the start of a test method. */
	    @Override
	    public void onTestStart(ITestResult result) {
	        System.out.println("[TEST STARTED] " + getTestName(result));
	    }

	    /** Logs a successfully completed test method. */
	    @Override
	    public void onTestSuccess(ITestResult result) {
	        System.out.println("[TEST PASSED] " + getTestName(result));
	    }

	    /** Logs a failed test method and its failure cause. */
	    @Override
	    public void onTestFailure(ITestResult result) {
	        System.err.println("[TEST FAILED] " + getTestName(result));
	        if (result.getThrowable() != null) {
	            System.err.println("Failure reason: " + result.getThrowable());
	        }
	        Object screenshotPath = result.getAttribute("screenshotPath");
	        if (screenshotPath != null) {
	            System.err.println("Screenshot: " + screenshotPath);
	        }
	    }

	    /** Logs a skipped test method and its cause, when available. */
	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("[TEST SKIPPED] " + getTestName(result));
	        if (result.getThrowable() != null) {
	            System.out.println("Skip reason: " + result.getThrowable());
	        }
	    }

	    /** Logs the start of a TestNG &lt;test&gt; section. */
	    @Override
	    public void onStart(org.testng.ITestContext context) {
	        System.out.println("[TEST CONTEXT STARTED] " + context.getName());
	    }

	    /** Logs the completion of a TestNG &lt;test&gt; section. */
	    @Override
	    public void onFinish(org.testng.ITestContext context) {
	        System.out.println("[TEST CONTEXT FINISHED] " + context.getName());
	        System.out.println("Passed: " + context.getPassedTests().size()
	                + " | Failed: " + context.getFailedTests().size()
	                + " | Skipped: " + context.getSkippedTests().size());
	    }

	    private String getTestName(ITestResult result) {
	        return result.getTestClass().getName() + "." + result.getMethod().getMethodName();
	    }
	}


