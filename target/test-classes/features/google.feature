Feature: Search functionality

  I want to be able to search for stuff


  Scenario: Simple Search
    Given I am using the Google website
    When I search for "Malta"
    Then The title should be "Malta - Google Search"

  Scenario: Another Simple Search
    Given I am using the Google website
    When I search for "Lebanon"
    Then The title should be "Lebanon - Google Search"