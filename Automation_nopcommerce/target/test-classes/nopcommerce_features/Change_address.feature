Feature: Verify changing address in the account section
Scenario: Navigate to my accounts section and change the shipping address
Given homepage should be loaded and the myaccounts hyperlink is clearly visible
When the user logs in nopcommerce using valid <useremail> and <password> 
And then navigate to my account section
And click on address section and existing address edit
And Enter Country, State, <City>, <Address1>, ZipCode, Phone
And click on the Save button
Then Address Updation successful

Examples:
|useremail			|password	|City		|Address1	|
|johndoe@gmail.com	|john798	|Hollywood	|MidWilshire|
