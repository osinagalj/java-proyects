Feature: Test cases for phrases

  Scenario: translate a simple phrase to morse code
    Given A new string
    When Translate "this is an example" from plain text
    Then The result in morse code should be "- .... .. ... / .. ... / .- -. / . -..- .- -- .--. .-.. ."

    Given A new string
    When Translate "messi is number 1" from plain text
    Then The result in morse code should be "-- . ... ... .. / .. ... / -. ..- -- -... . .-. / .----"

  Scenario: translate a simple phrase from morse code to plain text
    Given A new string
    When Translate "- .... .. ... / .. ... / .- -. / . -..- .- -- .--. .-.. ." from morse code
    Then The result in plain text should be "this is an example"
