Feature:Check Add Employee feature functionality
  As a Admin user
  I can add employee
  So that I can see added employee in Employeelist
  @smoke
  Scenario: Admin can add employee with valid data to employee list

    Given Admin is on login page
    When Admin enters username as"Admin" and password as "aediMNjU"
    And Andmin select login
    Then Admin should login successfully
    And admin  is on add employee page
    When admin add  new employee details with login details
      | firstname | lastname | password  | confirmpassword | status   | location              |
      | Vikas     | Patil    | password1 | password1       | Disabled | New York Sales Office |
    Then admin  can see added employee in  employeelist


  Scenario: Admin cannot  add employee with invalid data to employee list
    Given Admin is on login page
    When Admin enters username as"Admin" and password as "aediMNjU"
    And Andmin select login
    Then Admin should login successfully
    And admin  is on add employee page
    When admin add  new employee details with login details
      | firstname | lastname | password  | confirmpassword | status   | location              |
      | Veedant    | patil    | password1 |                 | Disabled | New York Sales Office |



