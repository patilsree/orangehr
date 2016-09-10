Feature: Add Leave Entitlement feature functionality
  As a Admin
  I want to add leave entitlement to employee
  so that I can see employee with given leave entitlement

  Scenario:Check Add Entitlement feature

    Given Admin is on login page
    When Admin enters username as"Admin" and password as "aediMNjU"
    And Andmin select login
    When Admin created new employee with following details
      | firstname | lastname | password  | confirmpassword | status   | location  |
      | Vedita1   | Patil    | password1 | password1       | Disabled | Texas R&D |
    And admin add 10 days leave entitlement for new employee
    Then he should see employee with given leave  days

