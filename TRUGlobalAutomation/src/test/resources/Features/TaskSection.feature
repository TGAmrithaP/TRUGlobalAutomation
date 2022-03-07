Feature: Verify Task Section

  Scenario: Verify Recent contacts section
    Given launch the browser
    When The Aplication is loaded
    And Enter the user login credentials
    And Click on App launcher and search for PROJECTS in the items list
    And Click on the PROJECTS item
    And User navigate to Recent contacts section
    Then Verify Recent contact list
    When User navigate to Today's Task Section.
    When User verify the Closed , Open, Goals details of Quarterly Performance
    Then testcase is completed
