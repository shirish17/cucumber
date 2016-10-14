Feature: PDP page features

  Scenario Outline: Search functionality
    Given User is on landing page
    When User logins with "<email>" and "<password>"
    Then Verify logged in user name is "<username>"
    Then provide a "<search>" criteria    
    Then validate the result
    Then get the products on the page
    Then user logout

    Examples: 
      | email                | password  | page   | search | username |
      | shirish@singpost.com | shirish17 | adidas | shoes  | shirish  |
