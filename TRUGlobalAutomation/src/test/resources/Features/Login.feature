Feature: Verify Login

  Scenario: Verify user is able to Login to Salesforce CRM application
    Given launch the browser
    When The Aplication is loaded
    And Enter the user login credentials
    And Click on App launcher and search for PROJECTS in the items list
    And Click on the PROJECTS item
    Then Verify Project name on home page
    Then testcase is completed
