# QuickBoard

This is a small program I made in college to let you type with only half your keyboard. It isn't pretty and needs a lot of love, but I wanted to test the viability of the concept, and it works.

It is similar to [T9](http://en.wikipedia.org/wiki/T9_(predictive_text)) in that each key is modified to represent two symbols instead of one. Since "I" is both "I" and "E" (from the other side of the keyboard), typing "hiy" will print "hey", since "hey" is a word and "hiy" is not.

--

1. `javac QuickBoard.java`
2. `java QuickBoard`
