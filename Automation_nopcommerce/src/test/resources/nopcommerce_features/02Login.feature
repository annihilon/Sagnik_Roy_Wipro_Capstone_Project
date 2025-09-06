Feature: validate login page of nopcommerce website 
Scenario: Enter valid credentials for login
Given login page should be open in default browser and internet connected

When in the textbox enter <username> and <password>
And Click on the <remember> option
And Click on the submit/login button
Then login successful and you are redirected to the home page

Examples:
|username|password|remember|
|johndoe@gmail.com|john798|true|