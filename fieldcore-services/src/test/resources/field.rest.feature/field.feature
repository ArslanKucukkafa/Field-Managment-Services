Feature: Name transformation

  Background:
    * url 'http://localhost:8080'

  Scenario: Transform multiple names
    Given path '/field'
    When method get
    Then status 200
    And print response

  Scenario: Deneme
    Given path '/field/deneme'
    When method get
    Then status 200
    And print response