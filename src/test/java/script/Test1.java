package script;

import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;

public class Test1 extends BaseTest{
	@Test
	public void test1()
	{
		Reporter.log("test1....",true);
	}
	
}
