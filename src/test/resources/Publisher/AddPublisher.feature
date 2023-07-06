Feature: Add publisher

  Background: Preconditions to navigate to Add publisher page
    Given Go to List of Publishers page
    And Navigate furthur to Add Publisher page

  @Test2
  Scenario: Try to add new publisher and verify its presence and validate Book count and presence of Delete button
    Given create new publisher
    When search and verify the presence of created publisher
    Then validate the bookcount is zero
    And validate the presence of Edit and Delete button

    @Test2
  Scenario: Add book to the new publisher and verify the delete button vanishes and validate the incremented bookcounts
    Given Add new Publisher and press Enter
    Then add new book "1" to the publisher
    And validate the bookcount changes to "1"
    But also verify the delete icon vanishes
    Then add another book "2" to the publisher
    And validate the bookcount updated to "2"

  Scenario Outline: Try to add new publisher with Valid and Invalid names and validate
    Given Try to enter new publisher "<name>" and click save
    Then verify the availablity of getting SUCCESSFUL message and validate with "<ExpectedResult>"
    Examples:
      | name | ExpectedResult |
      | DemoABC | true        |
      | Dem_ABC | true      |
      | DPub   | true        |
      | Dpub 2 | true        |
      | Dpub3  | true        |
      | 121    | false       |
      | @@@    | false       |
      | Dpub@# | true        |
      | @#pub  | false       |
      | 12pub  | false       |
      |        | false       |

  @Test1
  Scenario: Abort the Publisher addition by pressing CANCEL and verify it doesn't added up
    Given Try to enter new publisher name and click CANCEL
    Then Verify the publisher name doesn't added up

  @Test2
  Scenario: Enter existing publisher name and verify error message is displayed.
    Given try to provide already existing publisher name while adding new publisher
    Then press save and validate the error message is displayed

