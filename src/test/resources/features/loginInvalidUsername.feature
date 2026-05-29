@Login
Feature: Login with invalid username

  @login_with_invalid_username
  Scenario: User cannot login with invalid username
    Given User navigated to login page
    When User enters username "invalid_user"
    And User enters password "secret_sauce"
    And User clicks login
    Then User should see login error message "Epic sadface: Username and password do not match any user in this service"
