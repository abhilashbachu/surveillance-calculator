package com.provigil.calculator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.provigil.enums.Location;
import com.provigil.enums.PlanType;


/**
 * Unit test for SurveillanceCostCal App.
 */
public class SurveillanceCostCalTest {

	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void testApp() {
		CostCalculator calculator  = new CostCalculator("src/main/resources/cost.properties");
		float testcase1  = calculator.calculateCost(PlanType.MONTHLY.getPlanType(), 2500, Location.INDOOR.getLocation());
		float testcase2 = calculator.calculateCost(PlanType.YEARLY.getPlanType(), 2500, Location.INDOOR.getLocation());
		float testcase3 = calculator.calculateCost(PlanType.MONTHLY.getPlanType(), 4000, Location.OUTDOOR.getLocation());
		float testcase4 = calculator.calculateCost(PlanType.YEARLY.getPlanType(), 4000, Location.OUTDOOR.getLocation());
		float testcase5 = calculator.calculateCost(PlanType.MONTHLY.getPlanType(), 25000, Location.INDOOR.getLocation());
		float testcase6 = calculator.calculateCost(PlanType.YEARLY.getPlanType(), 25000, Location.INDOOR.getLocation());
		System.out.println("value for testcase1 is:" + testcase1);
		System.out.println("value for testcase2 is:" + testcase2);
		System.out.println("value for testcase3 is:" + testcase3);
		System.out.println("value for testcase4 is:" + testcase4);
		System.out.println("value for testcase5 is:" + testcase5);
		System.out.println("value for testcase6 is:" + testcase6);
		assertTrue("value for testcase1 is:" + testcase1 , testcase1 == 5000.0);
		assertTrue("value for testcase2 is:" + testcase2 , testcase2 == 3750.0);
		assertTrue("value for testcase3 is:" + testcase3 , testcase3 == 8500.0);
		assertTrue("value for testcase4 is:" + testcase4 , testcase4 == 6500.0);
		assertTrue("value for testcase5 is:" + testcase5 , testcase5 == 28750.0);
		assertTrue("value for testcase6 is:" + testcase6 , testcase6 == 18250.0);
	}
}
