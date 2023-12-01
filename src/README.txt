I complete this homework myself.

Link to your GitHub repo:

Cohesion Analysis:
    EvilHangman Class:
    High Cohesion:

defineValidInput(): Creates a set of valid lowercase characters, having functional cohesion.
getSameLengthWords(): Retrieves words with the same length from the word list, having functional cohesion.
getProceedWords(): Retrieves words based on a pattern from a map, having functional cohesion.
getMaxFamily(): Determines the pattern with the maximum number of words, having functional cohesion.
promptForGuess(): Accepts user input for guessing a letter, which fits the functional cohesion.
buildFamily(): Builds word families based on the user's guessed letter and all possible correct answers, having functional cohesion.


    Solution Class:
    High Cohesion:
isSolved(): Checks if the word is solved, having functional cohesion.
printProgress(): Displays the current progress of the word, having functional cohesion.
addGuess(): Updates the partial solution based on the guessed letter, having functional cohesion.
getTarget(): Retrieves the target word, having functional cohesion.

Coupling Analysis:
Loose Coupling:
EvilHangman and Solution classes are loosely coupled as the EvilHangman class interacts with the Solution class primarily through method calls. There's minimal dependency on the internal workings of each other.

Overall Analysis:
The EvilHangman class demonstrates moderate to high cohesion by grouping related methods together.
The Solution class exhibits high cohesion by encapsulating related functionalities.
The coupling between these classes is low, providing a degree of independence between them.

Description to run: simply run the "EvilHangmanRunner", the program will begin to prompt for user input and print out results accordingly.