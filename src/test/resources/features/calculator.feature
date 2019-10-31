Feature: Basic calculator functionality

  These scenarios test basic calculator functionality.

    Scenario Outline: Basic addition
      Given I am using my calculator
      When I add <num1> and <num2>
      Then The result should be <result>

      Examples:
      |num1|num2|result|
      |5   |2   |7     |
      |0   |1   |1     |
      |-2  |2   |0     |

    Scenario Outline: Basic subtraction
      Given I am using my calculator
      When I subtract <num1> from <num2>
      Then The result should be <result>

      Examples:
      |num1|num2|result|
      |5   |2   |3     |
      |7   |1   |6     |