package listeners;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // tests recovered by RetryAnalyzer (failed then passed) must not fail the build
    @Override
    public void onFinish(ITestContext context) {
        List<ITestResult> recovered = new ArrayList<>();
        for (ITestResult failed : context.getFailedTests().getAllResults()) {
            boolean passedOnRetry = context.getPassedTests().getAllResults().stream()
                    .anyMatch(passed -> passed.getMethod().getMethodName().equals(failed.getMethod().getMethodName())
                            && Arrays.equals(passed.getParameters(), failed.getParameters()));
            if (passedOnRetry) {
                recovered.add(failed);
                log.warn("⚑ Flaky (passed on retry): {}", failed.getName());
            }
        }
        recovered.forEach(result -> context.getFailedTests().removeResult(result));
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    private byte[] attachScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}