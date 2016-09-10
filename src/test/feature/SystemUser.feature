@smoke1
Feature: Admin User management functionality

  As a Admin user
  I want to access user management menu functionality
  So that I can add and update employee role and information

  Scenario: Search System User
    Given admin user is on Welcome page
    When Admin create user with given data
      | firstname | lastname | password  | confirmpassword | status   | location  |
      | Ved       | kadam    | password1 | password1       | Disabled | Texas R&D |
    And Admin  click on User Management menu
    Then Admin can see employee details in user list


  Scenario: Edit System User
    Given admin user is on Welcome page
    When Admin create user with given data
      | firstname | lastname | password  | confirmpassword | status  | location  |
      | Minaal    | pawar    | password1 | password1       | Enabled | Texas R&D |
    And Admin  click on User Management menu
    Then Admin can see user  details in user list
    When Admin edit user details with following data
      | Admin Role | status   | username | EmployeeName | Password    | RePassword  |
      | HR Manager | Disabled | nikam    |              | password124 | password124 |
    Then Admin can see edited user details in user list

  @smoke1
  Scenario:  Edit user wih invalid details
    Given Admin is on Welcome  page
    When Admin create user with given data
      | firstname | lastname | password  | confirmpassword | status  | location  |
      | Minaal    | pawar    | password1 | password1       | Enabled | Texas R&D |
    And Admin can see user deatils in user list
    When Admin edit user details with invalid data
      | Admin Role | status   | username | EmployeeName | Password    | RePassword  |
      | HR Manager | Disabled | nikam    |              | password124 | password124 |
    Then admin get error message "invalid data"

