@Skip
Feature: Add to Cart Promotions
  As a customer
  I want to add multiple products to my cart
  So that if I add more than 3 different products, I receive a free LEGO product.

  Scenario: Add 3 different products and get free LEGO
    Given I am on the Home Page
    When I add the following products to my cart:
      | product1 | Harry Potter Book |
      | product2 | The Great Gatsby |
      | product3 | Lego Architecture Set |
    Then my cart should contain 4 items
    And I should see a free LEGO product added
