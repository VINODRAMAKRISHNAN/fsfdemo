

Feature: Title of your feature
  I want to test the rules in the scenario outline
  Background: 
    Given that I loaded the Marketing Email Rule Order JSON file
    And that I loaded the Rules JSON file
    And there are no orphan Rules
    Then I connect to the Data File Under Test
    And there are data available
    And all Rules are correct
  
  @REGRESSION
  Scenario Outline: Validate Policy Rules for both 1008 and 1002
		When I enter the Rule number <ruleno>
    Examples: 
      | ruleno | 
      | 1008 | 
      | 1002 |
