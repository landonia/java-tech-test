# Java Tech Test

Simple pojo based java test that will cover various aspects of pure java

To Solve:

PART 1

* How long is it taking to run the billing cycle?
* What is the maximum time you would expect the test to run for?
* Why is it taking longer than we expect?
  * How can we fix this?

PART 2
* We want an audit log when a customer is billed. This should print out the credit card number and the amount before the payment and then confirm when the payment has been taken including the time taken.
    * We can *only modify* the code under com.postman.*

PART 3
* There is an issue with the current EZPayProvider is incorrect. They were trying to ensure that only one unique credit card can be billed at the same time. 
  * Create a new provider that will ensure that makes sure that only the same credit card cannot be billed multiple times at the same time.

PART 4
* We want to be able to use different payment providers based on the card. Create a payment provider that can determine what actual provider to use based on the card number.
