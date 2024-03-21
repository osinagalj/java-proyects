Feature: Test cases for simple numbers

  Scenario: translate morse to basic numbers
    Given A new string
    When Translate ".---- ..--- ...--" from morse code
    Then The result in plain text should be "123"

    Given A new string
    When Translate "----- .---- ..--- ...-- ....- ..... -.... --... ---.. ----." from morse code
    Then The result in plain text should be "0123456789"

  Scenario: translate numbers to morse
    Given A new string
    When Translate "123" from plain text
    Then The result in morse code should be ".---- ..--- ...--"

    Given A new string
    When Translate "0123456789" from plain text
    Then The result in morse code should be "----- .---- ..--- ...-- ....- ..... -.... --... ---.. ----."