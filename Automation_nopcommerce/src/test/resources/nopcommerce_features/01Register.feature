Feature: validate register page of nopcommerce 
Scenario: Enter valid credentials for registration
Given registration page should be open in default browser

When in the fields enter <gender>, <fname>, <lname>, <email>, <cname>, <newsletter>, <pwd>, <cpwd>
And get registration result and get error messages if any
And click the continue button
Then registration successful and open home page
Examples:
|gender|fname|lname|email|cname|newsletter|pwd|cpwd|
|male|John|Doe|johndoe7@gmail.com|wipro|true|john798|john798|

