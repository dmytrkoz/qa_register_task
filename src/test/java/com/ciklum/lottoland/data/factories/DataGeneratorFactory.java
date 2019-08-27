package com.ciklum.lottoland.data.factories;

import com.ciklum.lottoland.data.DataGenerator;
import com.ciklum.lottoland.data.RandomValidGenerator;
//import com.ciklum.lottoland.data.StaticGenerator;


/**
 * Creates the instances of Data Generators
 * Needed to select the data generator dynamically from test story parameters
 */
public abstract class DataGeneratorFactory {
    /**
     * Create the instance of Data Generator based on requested generator type
     * @param generator - possible values: static, random
     * @return the DataGenerator object of needed type
     * @throws IllegalArgumentException if provided String generator name doesn't exist
     */
    public static DataGenerator getInstance(String generator){
        switch (generator){
//            case "static": return new StaticGenerator();
            case "random": return new RandomValidGenerator();
            default:
                throw new IllegalArgumentException("Provided generator with name "+ generator+ " doesn't exist");
        }
    }
}
