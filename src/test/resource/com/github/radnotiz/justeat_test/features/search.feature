Feature: Use the website to find restaurants
	So that I can order food
	As a hungry customer
	I want to be able to find restaurants in my area

	Scenario: Search for restaurants in an area
		Given I want food in "AR51"
		When I search for restaurants
		Then I should see some restaurants in "AR51"

    Scenario: Don't proceed with invalid postcode
        Given I want food in "invalid location"
        When I search for restaurants
        Then I should see a message "Please enter a valid postcode" below the postcode just entered

    Scenario: Show restaurants only serving selected cuisine
      Given I want food in "AR51"
      And I select cuisine "Italian"
      When I search for restaurants
      Then I should see only restaurants serving "Italian" cuisine
