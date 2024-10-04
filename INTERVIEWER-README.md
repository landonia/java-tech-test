# Java Tech Test - Interviewer Notes

These are the notes to help the interview guide the candidate.

**Only the code under _com.postman_ can be modified by the candidate - the other packages are simulating library code**


## Setup

I recommend using a service like replit.com for the interview as this will make collaboration much easier.

In replit, you can create a new project using the Github project. You just have to login and select this repository.
The `.replit` file is already present, so just accept the settings when it asks you for the run command etc.

### Branching

Create a new branch from master for the candidate using the branch naming `candidate/{name}` and at the end of the interview
make sure that their code is committed.

### PART 1

* How long is it taking to run the billing cycle?

  * We are looking for them to add a basic timer. They can do this by creating a new Billing service and wrapping the __PostmanBillingService__. The _bill()_ method should print the amount of time it takes to execute.

* What is the maximum time you would expect the test to run for?

  * The maximum amount of time it should take is 12 SECONDS
    * The batch service can simultaneously process 3 payments at a time (with the max time for a payment being 3 seconds)
    * So, 10 (payments) / 3 (batch) = 4 simultaneous blocks * 3 (maximum time for payment) = 12 seconds

  * There is a bug in the code - _**EZPayPaymentProvider**_ has a synchronous block on the payment meaning that each payment will be synchronous

  * YOU SHOULD TELL THE CANDIDATE THAT THE EZPAY EXTERNAL PAYMENT SERVICE CAN TAKE UP-TO 3 SECONDS (this is simulated with a timeout)
  * The candidate needs to look through the code and try to determine the maximum execution it should be
    * THEY SHOULD RECOGNIZE THE BATCH AND BE ABLE TO CALCULATE THE 12 SECONDS

* Why is it taking longer than we expect?
  * Now they have identified how long it should take, ask them why it is taking longer.
  * They should spot the synchronize block - if not, guide them that way and ask them what that logic is actually doing.
    * This will identify if they understand synchronize blocks and how they work.

* How would you fix this?
  * They should recognize that removing the synchronize block will fix the issue

***

### PART 2
* We want an audit log when a customer is billed. This should print out the customers name, credit card number and the amount. 
  * We can *only modify* the code under com.postman.*
  
  * In order to do this, they essentially need to use composition.
    * They need to create a new PaymentProvider that accepts the actual PaymentProvider as a dependency. 
    * They should implement the pay() method that will print the payment details before handing off to the actual provider
    * They should then wire this into _**PostmanBillingService**_

***

### PART 3
This follows on from PART 1 when they identified the synchronize block. Explain to the candidate that EZPay were trying to ensure that only one unique credit card can be billed at the same time but instead only allowed one credit card to be billed at the same time. 

  * Create a new provider that will ensure that makes sure that only the same credit card cannot be billed multiple times at the same time.
    * They need to create a new EZPay provider that will fix this issue.
    * They need to understand how synchronize works and what it actually holds the lock on
    * We need a new Object for each credit card that will provide a unique lock. This is a simple implementation that will use a map - the credit card being the key and the object for the lock as the value
      * They need to retrieve the object (if it exists) or create a new object if not (storing it in the map)
      * They should synchronize over this block
      * They should be able to show that the same credit card will not execute at the same time using the debugging they created in PART 2

***

### PART 4
So if they breezed through the first parts, this is an extension to fill the time. A bit more advanced.

* We want to be able to use different payment providers based on the card. Create a payment provider that can determine what actual provider to use based on the card number.

  * Once again, they will need to use composition.
  * They should create a new multiplex provider that will take a list of actual payment providers
  * I would expect them to create a new interface that extends the current _**PaymentProvider**_ interface adding a new method - something like _boolean accepts(String creditCardNo)_
  * The implementation will call _accepts()_ on each payment provider and then hand off the payment to that provider if it returns true
    * They will need to wrap the existing payment providers in a new class that can implement the _accepts()_ method
  * If they ask just ask them to recognize the first 4 numbers to determine the payment provider
    * One impl will handle cards starting with 1234
    * The other impl will handle any cards not starting with 1234 (so essentially it can just return true but should be last in the list)

  * They can test this by using their audit payment provider from PART 2