Feature: Search page feature on adactin hotel website
  Background:
    Given user is already logged in website
    |username|password|
    |kanakasaimereddy99@gmail.com|Kanak@0321|

  Scenario: Search page title
    Given user is on search page
    When user gets the title of the search page
    Then search page title should be "Adactin.com - Search Hotel"

  Scenario: Search page contents validation
    Given user is on search page
    Then user gets search page content
    |Location|
    |Room Type|
    |Adults per Room|


