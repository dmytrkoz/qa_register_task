# qa_register_task
Test task for lottoland project

<b>To run you need to have:</b><br>
    java 8 or higher<br>
    maven version 3.6.0 or higher (tested with 3.6.0)<br>


to run the test please execute:<br> `mvn clean verify`

After execution test report should be available at {project base dir}/target/site/serenity/index.html


<b>Issues found: </b>  
1. Each month 31 day is available, and for example February 31 can be submitted
2. Expected format of phone number is not provided (should be 10 digits)
3. Country 2 times(maybe just to check different dropdown types)
4. Photo field jumps on the page different parts depending on browser window size change
and when it's in list with others, still is after submit button which is not good
5. Female option of gender is written as FeMale
6. Date Of Birth, Password, ConfirmPassword is not marked with asterisks, but are mandatory(in tests I put it, to make 
registration successful, but can be easily changed)
7. year - starts with small letter, but Month and Day starts with capital.
8. Email address format check is present but doesn't check the top level domain, for example <b>volzokamid@gmail</b>
counted as valid 