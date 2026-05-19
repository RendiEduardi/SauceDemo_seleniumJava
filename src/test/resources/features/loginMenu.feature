@Menu
  Feature: Menu login

    @login_with_valid_data
    Scenario: User click menu login
      Given User navigated to login page
      When User enters username "standard_user"
      And User enters password "secret_sauce"
      And User clicks login
      Then User in homepage menu

    @add_cart
    Scenario: User add product to cart
      Given User navigated to login page
      When User enters username "standard_user"
      And User enters password "secret_sauce"
      And User clicks login
      Then User in homepage menu
      When User adds product "Sauce Labs Backpack" to cart
      Then Cart badge should show "1"
      When User clicks menu cart
      Then User should see product "Sauce Labs Backpack" in cart page
