Feature: Assign chapters to self or others

  Background: preconditions
    Given : Navigate to Fresh stage
    And : Search for the book in the stockList
    Then : Navigate to assign chapters page of that book


  Scenario: Click ASSIGN button without selecting any chapter and Verify the error message

    Given : click the Assign button while not selecting any chapter
    Then : Verify the CHECKBOX ERROR Toaster message is displayed


  Scenario: Try to assign chapter while MS pages is ZERO and verify the error message

    Given : Search for the chapter with empty MSPages and retrieve data
    Then : Validate the MS pages count is Zero and try to assign chapter
    And : verify the MS PAGE ERROR Toaster message is displayed
    Then : Finally still if checkbox is selected, then deselect it.


  Scenario:  Try to assign chapter while there is a query to be checked.
              If query is not checked, Chapter should not be assignable.
              Subsequently, If query is checked successfully, Then chapter should be assignable.

    Given : Search for the query added chapter and retrieve data
    Then : Make sure the MS pages count greater than ZERO
    When : Try to select and verify the PENDING QUERIES error message is displayed
    But : Navigate to the corresponding query icon And check the query
    Then : Select the checkbox and assign to self and verify the assign page is popUP.
    And : After navigating to Assign page navigate backwards


  Scenario: Assign the valid chapter to SELF and verify the status is changed.
            also verify the chapter is added newly in ASSIGNED section
    Given : Get the CURRENT USER name
    Then : Search for the chapter to assign and retrieve data
    And : Ensure it does not have any pending queries and MS pages greater than zero
    When : Assign the chapter to self
    Then : Validate the USER is updated to CURRENT USER
    And : Navigate to ASSIGNED page
    Then : verify the newly assigned chapter is added in the bucket
