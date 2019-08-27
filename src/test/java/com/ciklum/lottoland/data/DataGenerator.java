package com.ciklum.lottoland.data;

/**
 * Describes what methods are required for the test data generator
 */
public interface DataGenerator {

     String generateFullName();

     String generateAddress();

     String generateEmail();

     String generatePhone();

     int generateGender();

     int[] generateHobbies();

     int[] generateLanguages();

     int generateSkills();

     int generateCountries();

     int generateCountry();

     int[] generateDateOfBirth();

     String generatePassword();

     String generatePasswordConfirmation();

     int generatePhoto();
}
