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

     String generateGender(List<?> values);

     List<?> generateHobbies(List<?> values);

     List<?> generateLanguages(List<?> values);

     String generateSkills(List<?>values);

     String generateCountries(List<?>values);

     String generateCountry(List<?>values);

     String generateYear(List<?>values);

     String generateMonth(List<?>values);

     String generateDay(List<?>values);

     String generatePassword();

     String generatePasswordConfirmation();

     String generatePhoto();
}
