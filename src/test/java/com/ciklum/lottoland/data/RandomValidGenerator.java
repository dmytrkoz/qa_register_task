package com.ciklum.lottoland.data;

import java.util.List;
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
    public String generateGender(List<?> values) {
        return values.get(oneOrZero()).toString();
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
        return selectRandomElementFromList(values).toString();
    }

    @Override
    public String generateCountry(List<?> values) {
        return selectRandomElementFromList(values).toString();
    }

    @Override
    public String generateYear(List<?> values) {
        return selectRandomElementFromList(values,1).toString();
    }

    @Override
    public String generateMonth(List<?> values) {
        return selectRandomElementFromList(values,1).toString();
    }

    @Override
    public String generateDay(List<?> values) {
        return selectRandomElementFromList(values,1).toString();
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
