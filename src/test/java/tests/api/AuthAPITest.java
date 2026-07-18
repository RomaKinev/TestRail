package tests.api;

import api_adapters.*;
import io.qameta.allure.*;
import listeners.RetryAnalyzer;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Test(testName = "API authentication functionality tests")
public class AuthAPITest extends BaseAPITest {

    private static final String INVALID_EMAIL = "invalid_user@example.com";
    private static final String INVALID_PASSWORD = "wrong_password";

    @Owner("Roma")
    @Feature("API Authentication")
    @Description("Verify API authentication succeeds with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(
            testName = "Verify API authentication succeeds with valid credentials",
            description = "Verify API authentication succeeds with valid credentials",
            groups = "api",
            priority = 1,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void checkValidCredentialsAuthentication() {
        int statusCode = ProjectAdapter.getProjectsStatusCode(
                BaseAdapter.CONFIG.email(), BaseAdapter.CONFIG.password());

        assertEquals(statusCode, 200, "Valid credentials were not accepted by the API.");
    }

    @Owner("Roma")
    @Feature("API Authentication")
    @Description("Verify API authentication fails with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(
            testName = "Verify API authentication fails with invalid credentials",
            description = "Verify API authentication fails with invalid credentials",
            groups = "api",
            priority = 2,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void checkInvalidCredentialsAuthentication() {
        int statusCode = ProjectAdapter.getProjectsStatusCode(INVALID_EMAIL, INVALID_PASSWORD);

        assertEquals(statusCode, 401, "Invalid credentials did not produce an authentication error.");
    }
}
