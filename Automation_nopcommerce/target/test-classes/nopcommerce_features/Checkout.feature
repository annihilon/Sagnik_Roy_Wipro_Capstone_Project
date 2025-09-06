Feature: Check the complete checkout flow process for nopcommerce
Scenario: Search and add to cart and then go to checkout and complete the process
Given home should be open in default browser and search and add to cart is working

When log in to the website using valid <useremail> and <password> 
And in search box enter the name of <item> and click search
And after the item displayed click on the add to cart option
And navigate to cart, click the terms and condition checkbox and the checkout button for checkout
And click on billing address continue
And select ground shipping method and continue
And select check/money payment then continue
And payment information verify and continue
And check the order details and continue
Then checkout successful

Examples:
|useremail|password|item|
|johndoe@gmail.com|john798|lenovo|
