@LoginFlow
Feature: Check Login flow

  Scenario Outline: Check Invalid Password
    Given User is on landing page
    Then Close promotion popup if exist
    Then Click on Login link
    Then User provides Login details by entering "<email>" and "<password>"
    Then Check for invalid login

  
