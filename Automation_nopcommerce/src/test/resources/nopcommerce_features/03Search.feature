Feature: check the response for multiple search
Scenario Outline: Enter data to search
Given home page should be open in default browser and search option visible

When in the searchbox enter the <item> and click on the search option
Then search successful and redirected to home page

Examples:
|item|
|laptop|
|Apple|
|camera|