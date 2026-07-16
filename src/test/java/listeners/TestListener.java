package listeners;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@Log4j2
public class TestListener implements ITestListener, IAnnotationTransformer {

    // attaches RetryAnalyzer to all @Test automatically
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("▶ Test started: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("✅ Passed: {}", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("⏭ Skipped: {}", result.getName());
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    private byte[] attachScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}