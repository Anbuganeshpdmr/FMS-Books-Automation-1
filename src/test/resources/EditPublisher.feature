Feature: Add publisher

  Background: Preconditions to navigate to Add publisher page
    Given Go to List of Publishers page
    And Navigate furthur to Add Publisher page

  Scenario: Try to add existing publisher and verify error message is displayed
    Given Try to enter already existing publisher
    Then Error message be displayed
    And Navigate back by pressing CLOSE button

  Scenario: Try to add new publisher and verify added publisher is available
    Given Try to enter new publisher name and click save
    Then Search the same name in search bar
    And The searched name should be available

  Scenario: Press cancel button while adding new publisher and verify PUBLISHER not added
    Given Try to enter new publisher name and click save
    Then Search that same name in search bar
    And The searched name should not be available