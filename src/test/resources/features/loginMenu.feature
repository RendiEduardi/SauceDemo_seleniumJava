@Menu
  Feature: Menu login

    @login_with_valid_data
    Scenario: User click menu login
      Given User navigated to login page
      When User enters username "standard_user"
      And User enters password "secret_sauce"
      And User clicks login
      Then User in homepage menu

    #@select_cart
    #Scenario: User click menu cart
      #Given User in homepage menu
      #When User clicks menu cart
      #Then User in cart page