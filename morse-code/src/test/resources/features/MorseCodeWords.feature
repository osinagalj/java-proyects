Feature: Test cases for simple words

  Scenario: translate common words to morse
    Given A new string
    When Translate "abcd" from plain text
    Then The result in morse code should be ".- -... -.-. -.."

    Given A new string
    When Translate "abcdefghijklmnopqrstuvwxyz" from plain text
    Then The result in morse code should be ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --.."

    When Translate the following list
      | code |
      | type |
    Then The result should be
      | -.-. --- -.. . |
      | - -.-- .--. . |

  Scenario: translate morse to common words
    Given A new string
    When Translate ".- -... -.-. -.." from morse code
    Then The result in plain text should be "abcd"

    Given A new string
    When Translate ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --.." from morse code
    Then The result in plain text should be "abcdefghijklmnopqrstuvwxyz"


