---
title: MP02 - Fun with Fractions
author: Drew Fargo
date: 8 October, 2024
---

# MP02: Fun with Fractions

A project for CSC-207 2024Fa.

The assignment specification for this project may be found at [this webpage.](https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/mps/mp02.html)
My submission code may be found at [this repository.](https://github.com/andrewfargo/csc207-mp2-fractions)

## Overview

In effect, I extend our `BigFraction` class from lab into a calculator.
The assignment requires two new utility classes: `BFCalculator`, which acts as a calculator
with registers, operations, and state, and `BFRegisterSet`, an object which can store 
fractions into 26 alphabetical registers.

In addition to these classes, the assignment also specifies two main classes:
`InteractiveCalculator` and `QuickCalculator`, which take input from Standard Input
and an argument list, respectively.

To complete the functionality of these classes, I introduced several
new utility classes:

- `Lexer`, a class which sequentially provides tokens of user input, known as lexemes;
- `Lexeme`, an interface which can alter the BFCalculator when it is read;
- `Command implements Lexeme`, an enumeration of commands `QUIT` and `STORE`;
- `Register implements Lexeme`, an individual regsiter object;
- `Reducer extends Lexeme`, an extension of the lexeme implementation that defines
  how an operator would reduce the program state;
- `Operator implements Reducer`, an enumeration of valid operators and their functions;
- `SyntaxException`, specifying when a read line doesn't match the grammar.

Note: I also implemented `Lexeme` in the `BigFraction` class.

## Implementation

One realization led me to my solution: the grammar of the program is regular.
That is, not only is validating user input as easy as checking against a regular expression,
executing each lexeme left to right is sufficient to calculate the result.
So, I did exactly that.

The entry point of the application is either `QuickCalculator` or `InteractiveCalculator`,
but they only differ in input source and a flag that changes output (`isQuiet`).
From there, a `Parser` instance is made, 
itself making associated `BFCalculator` and `Lexer` objects.
The former may be thought of as the "context" of a finite state machine, and is sometimes
referred to as such in the code.

When the parser is run, it begins collecting input from its lexer until its context,
the calculator, indicates that it has received a QUIT directive.
The lexer will return each token in a line until the line is empty, at which point
it will validate and lexicalize a new line.

Since the "line" starts empty, the lexer first prints any output it needs to, 
then requests a new line from the scanner it uses, at which point the user will 
type in the line if its interactive.

In any case, when the line is received, the lexer compares the line to a regular expression
that matches exactly when the input is valid.
If the regex doesn't match, a syntax error is returned.
If the regex does match, the line is split by whitespace and each individual string
of input is compared against separate regexes to identify them. Note that
so long as the grammar and lexeme regexes are correct, an unidentified lexeme should
never occur. The line's lexemes are added to the list, and the lexer will return
the lexeme the parser originally asked for.

Once the lexeme is received by the parser, it runs the lexeme's `alterContext` method
on the parser's calculator object. Once the program is directed to stop, the parser
stops collecting input. If the session was not interactive, then the parser
will request of the lexer a history of the calculations, which it will print.

## Citations

Instructions, written by Samuel Rebelsky, were consulted and may be found here: (https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/mps/mp02.html)

While I'd known the idea of left-regular grammars, I went to Wikipedia for more
specifics before starting programming. (https://en.wikipedia.org/wiki/Regular_grammar)

I learned that enumerations can extend interfaces from a StackOverflow article on the topic.
(https://stackoverflow.com/questions/2709593/why-would-an-enum-implement-an-interface)

Originally, I had tried to use a State design pattern, which my current
design is loosely based off of. I learned about State design patterns from the
"Refactoring Guru" website.
(https://refactoring.guru/design-patterns/state)

Various java language features, such as Scanners, Patterns, Matchers, BigIntegers, 
and ArrayLists, I learned about from the Java 17 Oracle documentation.
(https://docs.oracle.com/en/java/javase/17/docs/api/)

I first learned the general behavior of Parser-Lexer machines from an O'Reilly book
on the `lex` and `yacc` UNIX programs, 2nd edition.
(https://www.oreilly.com/library/view/lex-yacc/9781565920002/)
I recognize that my "Parser" isn't actually doing the job of parsing, which is
really implemented via regex in my Lexer class. I named it parser since it's the general
"thing" the class is doing: parsing user input.

My initial `pom.xml` and stylecheck files come from the CSC-207 website.
The unit tests for this program were provided by SamR.

My .gitignore file comes from a couple official GitHub templates, namely Java and Emacs.
(https://github.com/github/gitignore/tree/main)

Some files, I'm not sure the exact amount, were generated automatically by Maven via
`mvn archetype:generate`. If you are inclined to check this, I encourage you run this
on an empty directory and see.

All other code is my own. If I have any say in this post submission, I do not consent
for this code to be used as training for generative large language models or
their successors.
