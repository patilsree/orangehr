

Feature: Configuration functionality check for PIM

  As a Admin user
  I want to ckeck fucionality of configurations
  So that I can make suitable changes in employee records

  Scenario: Check add reporting feature

  Given  Admin is on home page
    When Admin  add  "SubDirect" reporting  method in configuration from PIM
  Then user can create employee profile with new reporting method Subdirect in add supervisor under Report to section

    | firstname | lastname | password  | confirmpassword | status  | location              |
    | Kiran3   | Patil    | password1 | password1       | Disabled | New York Sales Office |

