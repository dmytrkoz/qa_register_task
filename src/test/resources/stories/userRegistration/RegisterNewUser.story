Register new user
Narrative:
In order to register a new user
I want to be able to fill the fields and submit registration request

Scenario: Register new user with random data
Given the user is on the Registration page
When the user enters data generated by $generator
Then the new user should be registered $registered

Examples:
|generator|registered|
|random         |true|
|random         |true|
|random         |true|
|random         |true|
|random         |true|