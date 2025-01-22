@tag
  Feature: Purchase the order from Ecommerce Website

    Background:
      Given User landed on Landing page

    @Regression
    Scenario Outline: Positive test for submitting the order
      Given User is logged in with username <username> and password <password>
      When User adds product <productName> to the Cart
      And User checkout <productName>
      And User selects <country> for delivery
      And User submits the order
      Then "Thankyou for the order." message is displayed on Confirmation Page

      Examples:
        | username                | password        | productName   | country |
        | yibiv465523@chosenx.com | cg6G@bficYJi6U4 | QWERTY        | india   |
        | y2318188@chosenx.com    | c$YH7awJs!P6H5k | BANARSI SAREE | ukraine |
