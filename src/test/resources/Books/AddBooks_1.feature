Feature: Features after added the books


  Scenario: Add new book and ensure the details are added as it is and also verify the presence of edit delete and eye icons
    Given navigate to Add books page
    Then add the valid book datas and wait
      | Sl. No | Received Date | Publisher | Book name | No of chapters | ISBN Number | Type of book | Complexity level | Book color | Priority | CE Received Date | Client Due Date | PDMR Plan Date | Stages |
      | 1      | 0             | 3         | AB1_      | 5              | -ex-        | Math         | Medium           | 2c         | High     | 0                | 10              | 8              | Fresh  |
    Given add and submit the book without uploading the file
    Then verify by matching the datas of the book are correct with provided details
    And validate the presence of mandatory icons
    But download icon should not present


  Scenario: Add new book with uploading the file. Then verify the name of uploaded file by downloading it
    Given add new book with the details
      | Sl. No | Received Date | Publisher | Book name | No of chapters | ISBN Number | Type of book | Complexity level | Book color | Priority | CE Received Date | Client Due Date | PDMR Plan Date | Stages |
      | 1      | 0             | 3         | AB2_      | 5              | -ex-        | Math         | Medium           | 2c         | High     | 0                | 10              | 8              | Fresh  |
    Given add book with valid file from location and submit
      | C:\Users\banbuganesan\IdeaProjects\FMS-Books-Automation-1\src\test\resources\Books_test files\ | anbu File.pdf |
    Then verify the download icon and download the file
    And Ensure the downloaded file is as same as upload


  @Test
  Scenario Outline: Add book and verify it gets correctly reflected on all the stages
    Given add new book with stage "<Stages>" details and submit
      | Sl. No | Received Date | Publisher | Book name | No of chapters | ISBN Number | Type of book | Complexity level | Book color | Priority | CE Received Date | Client Due Date | PDMR Plan Date |
      | 1      | 0             | 3         | AB3_      | 3              | -ex-        | Math         | Medium           | 2c         | High     | 0                | 10              | 8              |
    And verify the presence in Fresh "<Fresh>" AuthorRevisions "<AuthorRevisions>" printWeb "<PrintWeb>" and wordConversion "<WordConversion>"
    Examples:
      | Stages          | Fresh | AuthorRevisions | PrintWeb | WordConversion |
      | Fresh           | true  | false           | false    | false          |
      | Word Conversion | false | false           | false    | true           |