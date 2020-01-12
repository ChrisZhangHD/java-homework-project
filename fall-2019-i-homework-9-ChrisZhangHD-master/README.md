## Homework 9 - Week 9

### Motivation
* Demonstrate your ability to program using Java semaphores.

### Instructions
* You have one task to complete:
* Create an orderly ElectionNight! This is a version of the producer / consumer problem.
    - Create an implementation of the [Voter](src/main/java/edu/nyu/cs9053/homework9/Voter.java)
    - Create an implementation of the [VoteCounter](src/main/java/edu/nyu/cs9053/homework9/VoteCounter.java)
    - Complete the factory which instantiates your implementations [Factory](src/main/java/edu/nyu/cs9053/homework9/Factory.java)
    - You should only modify `Factory`, create your `Voter` and `VoteCounter` implementations and create some implementations of [Ballet](src/main/java/edu/nyu/cs9053/homework9/Ballet.java) (have fun with these). No other class should be modified.
    - To ensure this portion of the assignment works you need to invoke the main class which is [ElectionNight](src/main/java/edu/nyu/cs9053/homework9/ElectionNight.java)
        - It should run forever

### Implementation
* Ensure your code is correct by compiling and testing it
* A portion of your grade will be based upon readability and organization of your code.
    - Follow the naming guidelines of lecture
    - Break large functions into multiple functions based on logical organizations
* You should not need to create any Thread objects, call sleep or join for this assignment. If you find yourself doing this you have done something wrong.    
    

    