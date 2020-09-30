package com.provigil.calculator;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/** This class processes the input xml.
 * @author abhilash.bachu
 *
 */
public class SurveillanceCostXMLInputProcessor {

    public void processInput(String inputFileName) {
        // Here input can be replaced from any other input/response from a webservice
        File xmlFile = new File(inputFileName);
        JAXBContext jaxbContext;
        try {
            //creating the jaxbContext with the input xml
            jaxbContext = JAXBContext.newInstance(com.provigil.generated.Subscriptions.class);
            //creating the unmarshaller
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            // creating the subscriptions as it is main node in the input xml
            com.provigil.generated.Subscriptions subscriptions = (com.provigil.generated.Subscriptions) jaxbUnmarshaller
                    .unmarshal(xmlFile);
            //creating the calculator object
            //this takes the property file where we have costs
            //this can be replaced by source where we can have cost per area
            CostCalculator calculator = new CostCalculator("src/main/resources/cost.properties");
            
            //Forming the object for output result
            com.provigil.generated.Result result = new com.provigil.generated.Result();
            
            //iterating the input to get each subscription
            for (com.provigil.generated.Subscriptions.Subscription sub : subscriptions.getSubscriptions()) {
                
                //getting the cost for particular subcription
                float cost = calculator.calculateCost(sub.getPlan(), sub.getArea(), sub.getLocation().toLowerCase());
                //forming the result
                com.provigil.generated.Result.Subscription resultSub = new com.provigil.generated.Result.Subscription();
                resultSub.setId(sub.getId());
                resultSub.setCost(cost);
                result.getSubscriptions().add(resultSub);
            }
            //converting result to XML
            convertObjectToXML(result);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /** Converts POJO Class to XML
     * @param result
     */
    public void convertObjectToXML(com.provigil.generated.Result result) {
        JAXBContext contextObj;
        try {
            contextObj = JAXBContext.newInstance(com.provigil.generated.Result.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //displaying the output to the console
            marshallerObj.marshal(result, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
