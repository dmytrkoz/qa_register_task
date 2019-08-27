package com.ciklum.lottoland.data;

import com.github.javafaker.Faker;

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
    public int generatePhoto(){
            return getRandomInt(5);
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

}
