package com.provigil.calculator;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/** This class holds the business logic for calculating the cost.
 * @author abhilash.bachu
 *
 */
public class CostCalculator {

    Properties prop;

    public CostCalculator(String filename) {
        FileReader reader;
        try {
            reader = new FileReader(filename);
            this.prop = new Properties();
            this.prop.load(reader);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /** 
     * @param planType
     * @param area
     * @param location
     * @return the total cost of the given area
     */
    public float calculateCost(String planType, int area, String location) {
        int remainingArea = area;
        float totalCost = 0;
        int maxSlabValue = getMaxSlab(area);
        for (int slab = 1; slab <= maxSlabValue; slab++) {
            float cost = getCost(slab, planType.toLowerCase(), location.toLowerCase());
            int areaToCalculate = getAreaToCalulate(slab, remainingArea);
            totalCost = totalCost + (cost * areaToCalculate);
            remainingArea = remainingArea - areaToCalculate;
        }
        return totalCost;
    }

    /** 
     * @param slabValue
     * @param planType
     * @param location
     * @return Gets the clost of the slab
     */
    public float getCost(int slabValue, String planType, String location) {
        String propetyName = planType + "_" + location + "_slab" + slabValue;
        return Float.parseFloat(this.prop.getProperty(propetyName));
    }

    /**
     * @param slabValue
     * @param area
     * @return the area to calculate based on the slab
     */
    public int getAreaToCalulate(int slabValue, int area) {
        int maxRange = Integer.parseInt(this.prop.getProperty("slab" + slabValue + "_max_range"));
        if (slabValue != 4 && area > maxRange) {
            return maxRange;
        } else {
            return area;
        }
    }

    /** Gets the maximum slab the given area can go
     * @param area
     * @return
     */
    public int getMaxSlab(int area) {
        int slabValue = 0;
        int slab1 = Integer.parseInt(this.prop.getProperty("slab1_max_range"));
        int slab2 = Integer.parseInt(this.prop.getProperty("slab2_max_range"));
        int slab3 = Integer.parseInt(this.prop.getProperty("slab3_max_range"));

        if (area <= slab1) {
            slabValue = 1;
        } else if (area > slab1 && area <= slab2) {
            slabValue = 2;
        } else if (area > slab2 && area <= slab3) {
            slabValue = 3;
        } else if (area > slab3) {
            slabValue = 4;
        }
        return slabValue;
    }
}
