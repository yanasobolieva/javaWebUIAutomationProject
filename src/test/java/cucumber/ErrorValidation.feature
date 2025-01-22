@tag
Feature: Error validation on user login

  @ErrorValidation
  Scenario Outline: User logins with incorrect credentials
    Given User landed on Landing page
    When User is logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed on landing page

    Examples:
      | username          | password        |  |  |
      | yibiv@chosenx.com | cg6G@bficYJi6U4 |  |  |