package test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import yicestests.TestToolRunning;

import expressions.tests.LiteralAndExpressionsTypeSystemTest;
import expressions.tests.ParametersTypeSystemTest;
import expressions.tests.PrimitiveTypeSystemTest;
import expressions.tests.SmgEnvironmentDevicesTest;
import expressions.tests.SmgEnvironmentParametersTest;
import expressions.tests.YicesExpressionPrinterTest;

@RunWith(Suite.class)
@SuiteClasses({ LiteralAndExpressionsTypeSystemTest.class,
		ParametersTypeSystemTest.class, PrimitiveTypeSystemTest.class,
		SmgEnvironmentParametersTest.class, SmgEnvironmentDevicesTest.class,
		YicesExpressionPrinterTest.class, TestToolRunning.class })
public class AllTests {

}
