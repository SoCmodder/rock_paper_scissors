# Rock-Paper-Scissors

## Goal
Create a native iOS or Android application that allows a user to play rock-paper-scissors with a remote server. 

## Estimated Time
60 minutes

## Requirements
- Player gives Rock Paper or Scissors
- App makes a request on behalf of the user to an app like the one above, except it responds with text rather than html.
- App shows the result to the player
- Player plays on.

## Request format

Similar to:

    curl -v -H 'Accept: text/plain' http://roshambo.herokuapp.com/throws/scissors

## Response format

    Content-type: text/plain
    
    Computer: Paper
    Player: Rock
    
    You lost! The computer threw paper.

## Extra Credit

- (60 min) Store previous games, and display them in a list
- (60 min) Provide a creative way for the player to select their choice of Rock Paper or Scissors