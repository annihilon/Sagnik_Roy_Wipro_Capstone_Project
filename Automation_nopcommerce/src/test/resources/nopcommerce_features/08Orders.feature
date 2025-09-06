Feature: Check the ordered list is correctly visibl ein order section
Scenario: Click on the orders tab in the accounts section to view my orders
Given home page should be opened in browser and the account section is clickable

When log-in to the website using my <useremail> and <password> and navigate to home page
And click on the my account section at the top
And click on orders tab in the left
And click on view details of the first order
Then order details displayed and testcases successful

Examples:
|useremail|password|
|johndoe@gmail.com|john798|
