package com.ciklum.lottoland.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaticGenerator extends AbstractDataGenerator {

    @Override
    public String generateFullName() {
        return usersList.get(0);
    }

    @Override
    public String generateAddress() {
        return "calle Marie Curie 1, ofic. 15, 29590, Malaga, Spain";
    }

    @Override
    public String generateEmail() {
        return "elisa1984@yahoo.com";
    }

    @Override
    public String generatePhone() {
        return "1234567890";
    }

    @Override
    public int generateGender() {
        return 1;
    }

    @Override
    public List<?> generateHobbies(List<?> values) {
        return new ArrayList<>(Arrays.asList("Movies"));
    }

    @Override
    public List<?> generateLanguages(List<?> values) {
        return new ArrayList<>(Arrays.asList("Arabic","Catalan"));
    }

    @Override
    public String generateYear(List<?> values) {
        return "2000";
    }

    @Override
    public String generateMonth(List<?> values) {
        return "September";
    }

    @Override
    public String generateDay(List<?> values) {
        return "30";
    }

    @Override
    public int generateSkills() {
        return 6;
    }


    @Override
    public int generateCountries() {
        return 9;
    }

    @Override
    public int generateCountry() {
        return 8;
    }

    @Override
    public String generatePassword() {
        return "!Mypassword123";
    }

    @Override
    public String generatePasswordConfirmation() {
        return generatePassword();
    }
}
