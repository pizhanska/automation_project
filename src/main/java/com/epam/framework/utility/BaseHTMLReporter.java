package com.epam.framework.utility;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.uncommons.reportng.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Uliana Pizhanska on 13/03/2017.
 */
public class BaseHTMLReporter  extends HTMLReporter implements ITestListener {
    private final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

    @Override
    public void onTestFailure(final ITestResult result)  {
        if (!result.isSuccess()) {
            try {
                String failureImageFileName = result.getName()+"." + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new Date()) + ".png";
                File scrFile = ((TakesScreenshot) Driver.instance).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("test-output/"+failureImageFileName ));
                System.setProperty(ESCAPE_PROPERTY, "false");
                Reporter.setCurrentTestResult(result);
                String fileName = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + failureImageFileName;
                Reporter.log("<a href=\""+fileName+"\"> Click here to take a look at screenshot </a>");

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSuccess(final ITestResult result) {
    }

    @Override
    public void onTestStart(final ITestResult result) {
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
    }

    @Override
    public void onStart(final ITestContext context) {
    }

    @Override
    public void onFinish(final ITestContext context) {
    }
}