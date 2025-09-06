Feature: validate if the add to cart and delete from cart is working properly
Scenario: Search and add to cart and then delete from cart
Given home should be open in default browser and search bar clearly visible to search

When log in using valid <useremail> and <password> and navigate to the home page
And in search box enter <item1> and hit search
And click on the add to cart option for the item displayed and item added in cart
And navigate to cart and hit the delete button after the item
Then item successfully deleted from cart and navigate to homepage

Examples:
|useremail|password|item1|
|johndoe@gmail.com|john798|lenovo|