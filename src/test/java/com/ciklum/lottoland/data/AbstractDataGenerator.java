package com.ciklum.lottoland.data;

import com.ciklum.lottoland.steps.serenity.VerificationSteps;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Class with common methods needed for data generation
 */
public abstract class AbstractDataGenerator implements DataGenerator {

    protected final Logger logger = LoggerFactory.getLogger(VerificationSteps.class);

    //immutable list of available users
    protected static final List<String> usersList = Collections.unmodifiableList(Arrays.asList("Jan van Dam", "Chack Norris", "Klark n Kent",
            "John Daw", "Bat Man", "Tim Los", "Dave o Core", "Pay Pal", "Lazy Cat", "Jack & Johnes"));

    private static List<String> usedUsers = new ArrayList<>(); // track already registered users

    protected static final Faker faker = new Faker(); // fake data content generator

    private String password;

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    /**
     * Takes unique unused user from list and marks it as used
     * @return
     */
    @Override
    public String generateFullName(){
        String user = getUniqueUser();
        markUserAsUsed(user);
        return user;
    }

    @Override
    public String generatePhoto(){
        List<String> results = new ArrayList<>();
        File[] files = new File("src/test/resources/photos/").listFiles();
//If this pathname does not denote a directory, then listFiles() returns null.

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getAbsolutePath());
            }
        }

        return (oneOrZero()==0) ? results.get(getRandomInt(results.size())) : "without file";
    }

    /**
     *
     * @return all users that were not used yet in tests
     */
    public static List<String> getAvailableUsers(){
        return usersList.stream().filter(user -> !usedUsers.contains(user)).collect(Collectors.toList());
    }

    /**
     * Put user to usedUsers list
     * @param user user to put
     */
    private void markUserAsUsed(String user){
        usedUsers.add(user);
    }

    /**
     * Check if user was already used during other test
     * @param user - user for check
     * @return true if user was already used
     */
    private boolean checkIfUserUsed(String user){
        return usedUsers.contains(user);
    }


    /**
     * Takes the random user from list, then checks if user already was used, if yes, runs itself in recursion.
     * If there is no more unused users, throws exception
     * @return unique user from list of users
     * @throws IllegalArgumentException if there is no more unused users left
     */
    private String getUniqueUser(){
        if (usedUsers.size() == usersList.size()){
            throw new IllegalArgumentException("There is no more unique users left, please extend the list or allow " +
                    "repeat of the users");
        }
        String userCandidate = usersList.get(getRandomInt(usersList.size()));
        return !checkIfUserUsed(userCandidate) ? userCandidate : getUniqueUser();
    }

    /**
     * Randomly returns 1 or 0
     * @return 0 or 1
     */
    protected int oneOrZero(){
        return getRandomInt(2);
    }

    /**
     * Fills array randomly with 0 or 1 elements
     *
     * @param array that you need to fill with values
     * @return filled array
     */
    protected int[] fillArrayWithOneOrZero(int[] array){
        for (int i =0; i< array.length; i++){
            array[i] = oneOrZero();
        }
        return array;
    }

    /**
     * @param max exclusive
     * @return a random number from 0 to max
     */
    protected int getRandomInt(int max){
        return getRandomInt(0,max);
    }

    /**
     *
     * @param min inclusive
     * @param max exclusive
     * @return a random number from min to max
     */
    protected int getRandomInt(int min, int max){
        return faker.number().numberBetween(min,max);
    }

    protected List<?> selectRandomElementsFromList(List<?> list){
        return selectRandomElementsFromList(list,0);
    }

    protected List<?> selectRandomElementsFromList(List<?> list, int startFrom){
        List<?> randomList = new ArrayList<>(list.subList(1, list.size()));
        Collections.shuffle(randomList); // cannot do directly Collections.shuffle(List)
        return randomList.subList(0, getRandomInt(randomList.size()+1));
    }

    protected <E> E selectRandomElementFromList(List<E> values, int startFrom){
        return values.get(getRandomInt(startFrom,values.size()));
    }

    protected <E> E selectRandomElementFromList(List<E> values){
        return selectRandomElementFromList(values,0);
    }

}
