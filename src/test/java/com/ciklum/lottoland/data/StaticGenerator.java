//package com.ciklum.lottoland.data;
//
//import java.util.Arrays;
//
//public class StaticGenerator extends AbstractDataGenerator {
//
//    @Override
//    public String generateFullName() {
//        return usersList.get(0);
//    }
//
//    @Override
//    public String generateAddress() {
//        return "calle Marie Curie 1, ofic. 15, 29590, Malaga, Spain";
//    }
//
//    @Override
//    public String generateEmail() {
//        return "elisa1984@yahoo.com";
//    }
//
//    @Override
//    public String generatePhone() {
//        return "1234567890";
//    }
//
//    @Override
//    public int generateGender() {
//        return 1;
//    }
//
//    @Override
//    public List<> generateHobbies() {
//        return new int [] {0,0,1};
//    }
//
//    @Override
//    public int[] generateLanguages() {
//        int [] myarray = new int[41];
//        Arrays.fill(myarray, 0);
//        myarray[5]=1;
//        myarray[40]=1;
//        return myarray;
//    }
//
//    @Override
//    public int generateSkills() {
//        return 6;
//    }
//
//
//    @Override
//    public int generateCountries() {
//        return 9;
//    }
//
//    @Override
//    public int generateCountry() {
//        return 8;
//    }
//
//    @Override
//    public int[] generateDateOfBirth() {
//        return new int[] {1990,1,16};
//    }
//
//    @Override
//    public String generatePassword() {
//        return "!Mypassword123";
//    }
//
//    @Override
//    public String generatePasswordConfirmation() {
//        return generatePassword();
//    }
//}
