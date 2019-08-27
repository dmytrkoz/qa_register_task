package com.ciklum.lottoland.data;

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
    public int[] generateHobbies() {

        int[] hobbies = new int[3];

        return fillArrayWithOneOrZero(hobbies);
    }

    @Override
    public int[] generateLanguages() {
        int [] array = new int[41];
        return fillArrayWithOneOrZero(array);
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

    @Override
    public String generatePassword() {
        String password = faker.internet()
                .password(10,12,true,true, true);
        setPassword(password);
        return password;
    }

    @Override
    public String generatePasswordConfirmation() {
        return getPassword();
    }

}
