package com.ciklum.lottoland.data;

import java.util.List;

/**
 * Describes what methods are required for the test data generator
 */
public interface DataGenerator {

     String generateFullName();

     String generateAddress();

     String generateEmail();

     String generatePhone();

     int generateGender();

     List<?> generateHobbies(List<?> values);

     List<?> generateLanguages(List<?> values);

     int generateSkills();

     int generateCountries();

     int generateCountry();

     String generateYear(List<?>values);

     String generateMonth(List<?>values);

     String generateDay(List<?>values);

     String generatePassword();

     String generatePasswordConfirmation();

     int generatePhoto();
}
