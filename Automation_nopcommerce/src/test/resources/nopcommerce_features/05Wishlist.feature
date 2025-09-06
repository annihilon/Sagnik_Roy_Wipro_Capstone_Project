Feature: validate if the  wishlist functionality working properly
Scenario: Search and add products in wishlist and then move it to add to cart
Given home should be open and the wishlist tab should be visible properly

When click on the searcch tab and enter the product <itemname> and click on the search button
And go to the product details and click on the wishlist tab
And verify if the wishlist added message is displayed
And go to the wishlist
And click on the items checkbox to be added to cart then add to cart button
Then item successfully added to cart from wishlist and add to cart is displayed

Examples:
|itemname|
|Apple Macbook Pro|