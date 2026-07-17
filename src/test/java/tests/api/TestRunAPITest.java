package tests.api;

import api.models.attachments.*;
import api.models.results.*;
import api.models.runs.RunsRs;
import api_adapters.*;
import io.qameta.allure.*;
import listeners.RetryAnalyzer;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class TestRunAPITest extends BaseAPITest {

    private static final String PROJECT_CODE = "3";
    private static final String SECTION_NAME = "API run tests section";
    private static final String SECTION_DESCRIPTION = "Section for test run API tests";
    private static final String CASE_TITLE = "API run test case";
    private static final String RUN_NAME = "API test run";
    private static final String RUN_DESCRIPTION = "Test run created by API test";
    private static final String RESULT_COMMENT = "Result added by API test";
    private static final Integer PASSED_STATUS_ID = 1;
    private TestCaseRs testCase;
    private TestRunRs testRun;

    @Owner("Roma")
    @Feature("Test Runs API")
    @Description("Verify project test runs can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify project test runs can be retrieved",
            description = "Verify project test runs can be retrieved",
            groups = "api",
            priority = 1,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void getTestRuns() {
        testCase = TestCaseAdapter.createTestCaseWithSection(PROJECT_CODE, SECTION_NAME,
                SECTION_DESCRIPTION, CASE_TITLE);
        testRun = TestRunAdapter.createTestRunForCase(PROJECT_CODE, RUN_NAME, RUN_DESCRIPTION, testCase);
        RunsRs runsRs = TestRunAdapter.getRuns(PROJECT_CODE);

        assertTrue(runsRs.getRuns().stream().anyMatch(r -> r.getId().equals(testRun.getId())),
                "The created test run was not found in the project runs list.");
    }

    @Owner("Roma")
    @Feature("Test Runs API")
    @Description("Verify a result can be added to a test run")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify a result can be added to a test run",
            description = "Verify a result can be added to a test run",
            groups = "api",
            priority = 2,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void addResultForCase() {
        assertNotNull(testRun, "Test run must be created before adding a result.");
        ResultRq resultRq = ResultRq.builder()
                .status_id(PASSED_STATUS_ID)
                .comment(RESULT_COMMENT)
                .build();
        ResultRs resultRs = ResultAdapter.addResultForCase(testRun.getId(), testCase.getId(), resultRq);

        assertNotNull(resultRs.getId(), "The created result ID was not returned.");
        assertEquals(resultRs.getStatusId(), PASSED_STATUS_ID, "The result status does not match.");
    }

    @Owner("Roma")
    @Feature("Test Runs API")
    @Description("Verify test run results can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify test run results can be retrieved",
            description = "Verify test run results can be retrieved",
            groups = "api",
            priority = 3,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void getResultsForRun() {
        assertNotNull(testRun, "Test run must be created before retrieving results.");
        ResultsRs resultsRs = ResultAdapter.getResultsForRun(testRun.getId());

        assertFalse(resultsRs.getResults().isEmpty(), "The test run results list is empty.");
        assertEquals(resultsRs.getResults().get(0).getStatusId(), PASSED_STATUS_ID,
                "The retrieved result status does not match.");
    }

    @Owner("Roma")
    @Feature("Test Runs API")
    @Description("Verify created test data can be deleted")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify created test data can be deleted",
            description = "Verify created test data can be deleted",
            groups = "api",
            priority = 4,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void deleteTestRunData() {
        if (testRun != null) {
            TestRunAdapter.deleteRunIfCreated(testRun.getId());
        }
        if (testCase != null) {
            SectionAdapter.deleteSectionIfCreated(testCase.getSectionId());
        }
    }
}
