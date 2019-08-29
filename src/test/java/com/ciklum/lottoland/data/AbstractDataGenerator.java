package com.ciklum.lottoland.data;

import com.ciklum.lottoland.steps.serenity.VerificationSteps;
import com.github.javafaker.Faker;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class with common methods needed for data generation
 */
public abstract class AbstractDataGenerator implements DataGenerator {

    protected final Logger logger = LoggerFactory.getLogger(VerificationSteps.class);

    private EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();


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

    /**
     *Scans the folder with photos if there are some - returns random one, if the folder doesn't
     * exist
     * Folder path is set up in serenity.conf file
     *
     * @return
     */
    @Override
    public String generatePhoto(){
        List<String> results = new ArrayList<>();
        final String photosFolder = variables.getProperty("test.photos.folder");
        final String noFile = variables.getProperty("test.photos.noFile");
        Optional<File[]> files = Optional.ofNullable(new File(photosFolder).listFiles());

        if(!files.isPresent() || !(files.get().length>0)){
            logger.error("There are no photo files in folder or folder doesn't exist");
            return noFile;
        } else {
            for (File file : files.get()) {
                if (file.isFile() && isImage(file)) {
                    results.add(file.getAbsolutePath());
                }
            }
            if (results.isEmpty()){
                return noFile;
            }
            //random noFile because it's optional value
            return new Random().nextBoolean() ? results.get(getRandomInt(results.size())) : noFile;
        }
    }


    /**
     * Check if the file is an image
     * @param file
     * @return
     */
    private boolean isImage(File file){
        boolean validImage= true;
        try {
            Image image = ImageIO.read(file);
            if (image == null) {
                validImage = false;
                logger.error("The file {} could not be used, it is not an image",file);
            }
        } catch(IOException ex) {
            validImage = false;
            logger.error("Error occurred during the file {} check",file);
        }
        return validImage;
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
        List<?> randomList = new ArrayList<>(list);
        Collections.shuffle(randomList); // cannot do directly Collections.shuffle(List)
        return randomList.subList(startFrom, getRandomInt(randomList.size()+1));
    }

    protected <E> E selectRandomElementFromList(List<E> values, int startFrom){
        return values.get(getRandomInt(startFrom,values.size()));
    }

    protected <E> E selectRandomElementFromList(List<E> values){
        return selectRandomElementFromList(values,0);
    }

}
