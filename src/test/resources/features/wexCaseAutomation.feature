#language: en
Feature: Buy some stuff on Amazon ecommerce under certain conditions

  Scenario: Test Setup
    Given I set up the navigation to Amazon WebSite

  @ignore
  Scenario: 80% Of Shown Products Should Be Exclusively The Searched Product
    Given Search For "Iphone" Using The Search Bar
    And Count The Total List Of Found Products
    And Count The Total Of "Iphone" Items Found
    Then Sure At Least 80% Of Items Found are "Iphone"

  @ignore
  Scenario: The Higher Price In The First Page Can't Be Greater Than U$2000
    Given Search For "Iphone" Using The Search Bar
    And Find The More Expensive "Iphone" In Page
    When Convert Its Value To USD
    Then Make Sure The Converted Value Is Not Greater Than "2.000" USD

  Scenario: Products Different Than The Searched Product Should Be Cheaper Than The Searched Product
    Given Search For "Iphone" Using The Search Bar
    When Find Products Which Are Not "Iphone"
    Then Make Sure All Found Products Are Cheaper Than The Cheapest "Iphone"
