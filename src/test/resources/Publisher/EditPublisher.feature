Feature: Edit Publisher

  Background: Preconditions to navigate to Edit publisher page
    Given navigate to List of Publishers page
    Then go the Add publisher page and add new publisher

  @abc
  Scenario: Edit any new publisher with valid name and ensure other details are intact
    Given edit the publisher name
    Then verify the bookcount is still "0"
    And verify the presence of Edit and Delete button

  @ab
  Scenario: Edit any old publisher with valid name and ensure other details are intact and confirm from any other section
    Given add new book to the publisher
      | Sl. No | Received Date | Publisher | Book name | No of chapters | ISBN Number | Type of book | Complexity level | Book color | Priority | CE Received Date | Client Due Date | PDMR Plan Date | Stages |
      | 1      | 20-06-23      | ex        | DBook     | 5              | ex          | Math         | Medium           | 2c         | High     | 21-09-2023       | 22-09-2023      | 23-09-2023     | Fresh  |
    But edit the current publisher name
    Then verify the absence of Delete button
    And go to the recently added book page and confirm the publisher name is modified

  Scenario: Abort the editing the publisher name midway and verify publisher is not modified
    Given proceed to edit the publisher but press cancel button
    Then verify the publisher name is not modified

  @abc
  Scenario: Edit existing publisher name with already existing publisher name
    Given retrieve one older publisher name
    Then create new publisher and edit with retrieved name
    And verify the error message is displayed

  @abc
  Scenario: Edit valid publisher with invalid name and verify the error message is displayed
    Given edit the publisher with invalid text
    Then verify the error message be displayed for invalid characters