package tests;

import driver.DriverProvider;
import org.junit.Before;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import utils.DriverUtils;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public abstract class BaseTest {

    static final Logger logger = Logger.getLogger(TestHerokuappCom.class.getName());

    @Before
    public static void setUp() {
        DriverProvider.getDriver();
        try {
            LogManager.getLogManager().readConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void clearCookies() {
        DriverUtils.deleteCookies();
    }

    @AfterClass
    public void tearDown() {
        DriverUtils.close();
    }
}
