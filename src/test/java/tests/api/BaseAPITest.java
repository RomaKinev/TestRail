package tests.api;


import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseAPITest {
}