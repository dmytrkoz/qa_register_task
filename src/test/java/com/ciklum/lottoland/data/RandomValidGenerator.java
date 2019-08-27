package com.ciklum.lottoland.data;

import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomValidGenerator extends AbstractDataGenerator {

    @Override
    public String generateAddress() {
        return faker.address().fullAddress();
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
    public int generateGender() {
        return oneOrZero();
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
    public int generateSkills() {
        return getRandomInt( 78);
    }


    @Override
    public int generateCountries() {
        return getRandomInt( 251);
    }

    @Override
    public int generateCountry() {
        return getRandomInt( 11);
    }

    @Override
    public int[] generateDateOfBirth() {
        return new int[] {getRandomInt( 1916,2016),getRandomInt(1, 13),getRandomInt(1, 32)};
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
            logger.info("Password that was generated, doesn't match needed pattern "+ password + " will regenerate");
        }
        setPassword(password);
        return password;
    }

    @Override
    public String generatePasswordConfirmation() {
        return getPassword();
    }

}
