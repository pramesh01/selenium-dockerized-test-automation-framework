package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.constants.Env;
import utilities.PropertyUtils;


public class MyRetryAnalyzer implements IRetryAnalyzer {

	private static final int MAX_NO_ATTEMPTS = Integer.parseInt(PropertyUtils.propertyFileReader(Env.QA, "MAX_RETRY_ATTEMPTS"));
	private static int current_attempts = 1;

	@Override
	public boolean retry(ITestResult result) { // ITestResults gives u information about failed Tests / otherwise @Test
												// informations
		if (current_attempts <= MAX_NO_ATTEMPTS) {
			current_attempts++;
			return true; // return true means that retry attempts still alive, hence go and re-run script
							// again.
		}
		return false; // all the chances are executed and test still fails. Then Test script is Failed
						// here.
	}

}
