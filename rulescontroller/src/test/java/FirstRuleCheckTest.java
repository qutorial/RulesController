import static org.junit.Assert.fail;

import org.fortiss.smg.rulescontroller.ruleinterfaces.IRule;
import org.fortiss.smg.rulescontroller.smgenvironment.ResultWaiterBase;
import org.fortiss.smg.rulescontroller.smgenvironment.SmgEnvironment;
import org.fortiss.smg.rulescontroller.smgenvironment.exceptions.AddRuleException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.org.apache.regexp.internal.REUtil;


public class FirstRuleCheckTest {

	public static SmgEnvironment Environment;

	@BeforeClass
	public static void testSetup() {
		Environment = new SmgEnvironment();
	}

	@AfterClass
	public static void testCleanup() {
		Environment = null;
	}
	
	@Test
	public void test() {
		
		ResultWaiterBase resultWaiter = new ResultWaiterBase();
		
		IRule rule = null;
		
		try {
			Environment.requestAddingRule(rule, resultWaiter);
		} catch (AddRuleException e) {
			fail(e.getMessage());
		}
		
		int TimeOutSec = 15;
		
		for(int i = 0; i < TimeOutSec + 1; ++i)
		{
			if(resultWaiter.check())
			{
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				fail("Main thread interrupted!");
			}
		}

		boolean r = resultWaiter.getResult();
		if(!r)
		{
			Exception e = resultWaiter.getException();
			if(e == null)
			{
				fail("No exception set when check fails");
			}
		}
		

	}
	
	public static void main(String[] args) {
		testSetup();
		FirstRuleCheckTest t = new FirstRuleCheckTest();
		t.test();
		testCleanup();	
	}

}
