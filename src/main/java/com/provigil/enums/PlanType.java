/**
 * 
 */
package com.provigil.enums;

/** Plan Type Enums
 * @author abhilash.bachu
 *
 */
public enum PlanType {
    MONTHLY("MONTHLY"), YEARLY("YEARLY");
	private String plantype;
	PlanType(String plantype) {
		this.plantype = plantype;
	}
	
	public String getPlanType() {
		return this.plantype;
	}
}
