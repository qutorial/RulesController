import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LiteralAndExpressionsTypeSystemTest.class,
		ParametersTypeSystemTest.class, PrimitiveTypeSystemTest.class,
		SmgEnvironmentParametersTest.class, SmgEnvironmentDevicesTest.class, YicesExpressionPrinterTest.class })
public class AllTests {

}
