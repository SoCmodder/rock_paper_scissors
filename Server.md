# Rock-Paper-Scissors

## Goal
Create a web-based application that allows a user to play rock-paper-scissors. 

## Estimated Time
30 minutes

## Requirements
- The server should generate a random throw
- The server should respond with a page indicating whether the user won, lost or tied based on the server's sign

## Rules
- Rock beats scissors
- Scissors beats paper
- Paper beats rock
- Identical throws tie (rock == rock, etc.)

## Routes

| Verb | Path   | Params |
|------|--------|--------|
| POST | /throw | :sign  |