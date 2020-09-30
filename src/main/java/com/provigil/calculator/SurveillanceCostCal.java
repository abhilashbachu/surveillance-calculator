package com.provigil.calculator;

/**
 * Main Class for starting the app
 * 
 */
public class SurveillanceCostCal {

	public static void main(String[] args) {
		SurveillanceCostXMLInputProcessor xmlprocesser = new SurveillanceCostXMLInputProcessor();
		xmlprocesser.processInput("src/main/resources/sample01.xml");
	}
}
