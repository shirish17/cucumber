@NewAccountCreationFlow
Feature: This is to create new account

  Scenario Outline: Create new account
    Given User is on landing page
    Then Close promotion popup if exist
    Then Click on Login link
    Then Click on Create Account
    Then User creates a new account with "<rFName>", "<rLName>", "<Day>","<Month>","<year>","<rAddress>","<rPword>" and "<rcPword>"
    Then User logout

    Examples: 
      | rFName   | rLName   | Day | Month | year | rAddress              | rPword    | rcPword   |
      | shirish  | wanjari  |  17 | Jun   | 1981 | automation3@gmail.com | shirish17 | shirish17 |
      | shirish1 | wanjari1 |  17 | Jun   | 1981 | automation4@gmail.com | shirish17 | shirish17 |
