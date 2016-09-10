Feature: Login feature for OrangeHRM site
  As a Admin
  I want to see login page
  So that I can login successfully

  Scenario Outline: Admin can error message after entering false details
    Given Admin is on login page
    When Admin enters  invalid "<username>" and invalid "<password>"
    And Admin select login
    Then Admin should navigate to retry login page

    Examples:    | username | password |
    | admin 44 |          |
    | admin1   | asdfvde  |
    | admin mn | aediMNjp |

 @smoke1
  Scenario:Admin can able to login successfully with valid data
    Given Admin is on login page
    When Admin enters username as"Admin" and password as "aediMNjU"
    And Andmin select login
    Then Admin should login successfully
    And admin should see welcome message as "Welcome Admin"

  Scenario: Admin can able to login successfully with valid credentials
    Given Admin is on login page
    When Admin login with below credentials
      | Admin | aediMNjU |
    And Admin selects login
    Then Admin should login successfully
    And Admin should see welcome message as "Welcome Admin"