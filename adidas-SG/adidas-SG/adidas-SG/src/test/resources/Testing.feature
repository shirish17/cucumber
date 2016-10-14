@Test
Feature: Testing feature

  Scenario Outline: Registered user Add to bag from PLP
    Given User is on landing page
    Then Check for the common components on the page
    #Then Delete step Customer selects a "<product>" from PLP
    When Customer selects main menu "<mainmenu>"
    Then Customer checks for the products count
    Then Check PLP "<product>" has subcategory name
    Then Check PLP "<product>" has price with currency symbol
    Then Customer adds a "<product>" of "<size>" from PLP page

    Then Customer checks product is added to cart
    #Then Close promotion popup if exist
    #Then Close cookie poup if exist
    #And Check for search option
    #And Check for minibag
    #And Check for free delivery text
    #And Check for customer service text
    #And Check for return text
    #And User search by providing "<searchstring>"
    #And Click order tracker link
    #And User enters newsletter details by entering "<email>", "<dob>" and "<gender>"
    #Then Click on Login link
    #Then Click on adidas logo to navigate homepage
    #Then User provides Login details by entering "<email>" and "<password>"
    #Then Check customer "<name>"
    #When Customer selects main menu "<mainmenu>"
    #Then Customer checks for the products
    #Then Customer adds a "<product>" of "<size>" from PLP page
    #And Customer add "<qty>" to cart and validate minicart
    #Then User logout
    #And Click a "<product>" from PLP page to open PDP
    Examples: 
      | email                | password  | name            | mainmenu | product                    | qty | size | dob         | gender | searchstring   |
      | shirish@singpost.com | shirish17 | shirish wanjari | Women    | Supernova Sequence 9 Shoes |   1 |   S| 17/jun/1981 | male   | Climachill Tee |
