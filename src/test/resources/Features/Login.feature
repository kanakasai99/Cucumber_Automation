Feature: Login page feature for adactin page
  Scenario: Login page title
    Given user is on login page
    When user gets the title of the page
    Then page title should be "Adactin.com - Hotel Reservation System"

  Scenario: Forgot password link
    Given user is on login page
    Then forgot password link should be displayed

  Scenario: Login with valid credentials
    Given user is on login page
    When user enter the username "kanakasai.mereddy03@gmail.com"
    And user enter the password "Kanak@0321"
    And clicks on login button
    Then user gets the title of the page
    And page title should be "Adactin.com - Search Hotel"