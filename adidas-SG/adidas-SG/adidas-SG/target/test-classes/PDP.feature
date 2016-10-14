@PDP
Feature: Testing feature

  Scenario Outline: Product details page validation
    Given User is on landing page
    Then Close promotion popup if exist
    Then Click on Login link
    Then User provides Login details by entering "<email>" and "<password>"
    Then Check customer "<name>"
    When Customer selects main menu "<mainmenu>"
    Then Customer checks for the products
    Then Customer selects a "<product>" from PLP page
    And Check PDP page for "<product>"
    Then User logout

  Scenario Outline: Registered user Add to bag from PDP
    Given User is on landing page
    Then Close promotion popup if exist
    Then Click on Login link
    Then User provides Login details by entering "<email>" and "<password>"
    Then Check customer "<name>"
    When Customer selects main menu "<mainmenu>"
    Then Customer checks for the products
    Then Customer selects a "<product>" from PLP page
    And Customer add "<qty>" to cart and validate minicart
    Then User logout

    Examples: 
      | email                | password  | name            | mainmenu | product          |
      | shirish@singpost.com | shirish17 | shirish wanjari | Women    | Stan Smith Shoes |
