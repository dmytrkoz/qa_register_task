package com.ciklum.lottoland.data;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Generates the valid inputs for successful user registration
 */
public class RandomValidGenerator extends AbstractDataGenerator {

    @Override
    public String generateAddress() {
        return new Random().nextBoolean() ? faker.address().fullAddress() : ""; //random empty because it's optional
    }

    @Override
    public String generateEmail() {
        return faker.internet().emailAddress();
    }

    @Override
    public String generatePhone() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    @Override
    public String generateGender(List<?> values) {
        return selectRandomElementFromList(values).toString();
    }

    @Override
    public List<?> generateHobbies(List<?> values) {
       return selectRandomElementsFromList(values);
    }

    @Override
    public List<?> generateLanguages(List<?> values) {
        return selectRandomElementsFromList(values);
    }

    @Override
    public String generateSkills(List<?> values) {
        return selectRandomElementFromList(values).toString();
    }

    @Override
    public String generateCountries(List<?> values) {
        return selectRandomElementFromList(values,1).toString(); // start from 1 because it's mandatory
    }

    @Override
    public String generateCountry(List<?> values) {
        return selectRandomElementFromList(values).toString();
    }

    @Override
    public String generateYear(List<?> values) {
        return selectRandomElementFromList(values,1).toString(); // start from 1 because it's mandatory
    }

    @Override
    public String generateMonth(List<?> values) {
        return selectRandomElementFromList(values,1).toString(); // start from 1 because it's mandatory
    }

    @Override
    public String generateDay(List<?> values) {
        return selectRandomElementFromList(values,1).toString(); // start from 1 because it's mandatory
    }

    /**
     * Generate the password, if password doesn't match needed format, regenerate it
     * @return
     */
    @Override
    public String generatePassword() {
        String password;
        final Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W).+$");
        while (!passwordPattern.matcher(password = faker.internet().password(10,12,true,true, true)).matches()){
            logger.warn("Password that was generated: {}, doesn't match needed pattern will regenerate",password);
        }
        setPassword(password);
        return password; // password is mandatory thought it's not marked with asterisks
    }

    @Override
    public String generatePasswordConfirmation() {
        return getPassword(); //  confirm password is mandatory thought it's not marked with asterisks
    }

}
