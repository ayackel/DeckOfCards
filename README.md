DeckOfCards
===========

Deck of Cards Problem Design Considerations

For the method dealOneCard(), I choose to throw a RuntimeException if the user tries to deal more cards than are in the deck.  There were four basic choices: return null, return a Null Object, throw a checked exception, and throw a runtime exception.  A Null Object would likely be confusing to a user.  Checked exceptions are rarely the way to go.  They often just add unnecessary clutter and anoyance for the API user.  With a choice between returning null or a runtime exception, I choose to add a hasCards() method for the user and throw a runtime exception.  This allows the user to use the API correctly and not have to continuously check for null.  A hasCards() method is easier to read and understand its meaning than a null check.

For the shuffle, I found an efficient shuffling algorithm and implemented a chi-squared test to confirm an even distribution.  This pointed to bias in the shuffling algorithm caused from shuffling an ordered deck.  A second shuffle of the deck removed this bias.

Note:  The methods in the Deck class that are package access are that way for unit testing.

The code is in an Eclipse project and a gradle build file has been added. 

![githalytics.com alpha](https://cruel-carlota.pagodabox.com/733a0b3e6c796789981fd14298565737 "githalytics.com")
