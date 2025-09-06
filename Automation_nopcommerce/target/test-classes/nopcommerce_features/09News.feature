Feature: Verify if the newsletter sectionn is functioning properly
Scenario: From the homepaeg navigate to news section by scrolling down and click on a news
Given home page is opened and thw news section is clickable

When First log-in with my <useremail> and <password> and navigate back to home screen
And scroll down to find the news section and click on a news details
And at the Leave comment section add a <title> and below add <comments>
And click on the new comment/add comment section
Then comment displayed and task completed

Examples:
|useremail|password|title|comments|
|johndoe@gmail.com|john798|Creating a sample comment|Validating the comments feature|
