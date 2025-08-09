Feature: Contact Us Feature

  Scenario Outline: Contact us feature with different sets of data
    Given user navigates to contact us page
    When user fills the form from excel "<SheetName>" and row <RowNumber>
    And user clicks on send button
    Then it shows a success message "Your message has been successfully sent to our team."

    Examples:
      | SheetName | RowNumber |
      | ContactUs | 0         |
      | ContactUs | 1         |
