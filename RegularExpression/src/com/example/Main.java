package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	    String string = "I am a string, yes I am.";
        System.out.println(string);
        String yourString = string.replaceAll("I", "You");
        System.out.println(yourString);

        String alphanumeric = "abcDeeeF12Ghhiiiiijkl99z";
        System.out.println(alphanumeric.replaceAll(".", "Y"));

        System.out.println(alphanumeric.replaceAll("^abcDeee", "YYY"));

        String secondString = "abcDeeeF12GhhabcDeeeiiiiijkl99z";
        System.out.println(secondString.replaceAll("^abcDeee", "YYY"));

        System.out.println(alphanumeric.matches("^hello"));
        System.out.println(alphanumeric.matches("^abcDeee"));
        System.out.println(alphanumeric.matches("^abcDeeeF12Ghhiiiiijkl99z$"));

        System.out.println(alphanumeric.replaceAll("ijkl99z$", "THE END"));

        System.out.println(alphanumeric.replaceAll("[aei]", "X"));
        System.out.println(alphanumeric.replaceAll("[aei]", "I replaced a letter here"));
        System.out.println(alphanumeric.replaceAll("[aei][Fj]", "X"));

        System.out.println("harry".replaceAll("[Hh]arry", "Harry"));

        System.out.println(alphanumeric.replaceAll("[^ej]", "Z"));

        System.out.println(alphanumeric.replaceAll("[a-fA-F3-8]", "Z"));
        System.out.println(alphanumeric.replaceAll("(?i)[a-f3-8]", "Z"));
        System.out.println(alphanumeric.replaceAll("[0-9]", "Z"));
        System.out.println(alphanumeric.replaceAll("\\d", "Z"));
        System.out.println(alphanumeric.replaceAll("\\D", "Z"));

        String hasWhitespace = "I have blanks\ta tab, and also a newline\n";
        System.out.println(hasWhitespace);
        System.out.println(hasWhitespace.replaceAll("\\s", ""));
        System.out.println(hasWhitespace.replaceAll("\\S", ""));
        System.out.println(hasWhitespace.replaceAll("\\t", ""));

        System.out.println(alphanumeric.replaceAll("\\w", "Z"));

        System.out.println(hasWhitespace.replaceAll("\\b", "Z"));
        System.out.println(hasWhitespace.replaceAll("\\B", "Z"));

        alphanumeric = "abcDeeeF12Ghhiiiiijkl99z";
        System.out.println(alphanumeric.replaceAll("^abcDe{3}", "YYY"));
        System.out.println(alphanumeric.replaceAll("^abcDe+", "YYY"));
        System.out.println(alphanumeric.replaceAll("^abcDe*", "YYY"));
        System.out.println(alphanumeric.replaceAll("^abcDe{2,5}", "YYY"));
        System.out.println(alphanumeric.replaceAll("h+i*j", "Z"));

        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>")
                .append("<H2>Sub-heading</H2>")
                .append("<p>This is a paragraph about something</p>")
                .append("<p>This is another paragraph about something else</p>")
                .append("<h2>Summary</h2>")
                .append("<p>Here is the summary.</p>");

        String h2Pattern = ".*<h2>.*";
        Pattern pattern = Pattern.compile(h2Pattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        matcher.reset();
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + matcher.start() + " to " + matcher.end());
        }

        String h2GroupPattern = "(<h2>.+?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while (groupMatcher.find()) {
            System.out.println("Occurrences: " + groupMatcher.group(1));
        }

        String h2TextGroups = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2TextGroups, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);

        while (h2TextMatcher.find()) {
            System.out.println("Occurrence: " + h2TextMatcher.group(2));
        }

        System.out.println("harry".replaceAll("[Hh]arry", "Larry"));
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));

        String tvTest = "tstvtkt";
//        String tvRegex = "t[^v]";
        String tvRegex = "t(?!v)";
        Pattern tvPattern = Pattern.compile(tvRegex);
        Matcher tvMatcher = tvPattern.matcher(tvTest);

        int count1 = 0;
        while (tvMatcher.find()) {
            count1++;
            System.out.println("Occurrences " + count1 + " : " + tvMatcher.start() + " to " + tvMatcher.end());
        }
        // ^([\(]{1}[0-9]{3}[\)]{1}[ ]{1}[0-9]{3}[\-]{1}[0-9]{4})$ Regex for a U.S phone number
        String phone1 = "1234567890"; // Not a match
        String phone2 = "(123) 456-7890"; // A match
        String phone3 = "123 456-7890"; // Not a match
        String phone4 = "(123)456-7890"; // Not a match

        System.out.println("phone1: " + phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone2: " + phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone3: " + phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone4: " + phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));

        // ^4[0-9]{12}([0-9]{3})?$
        String visa1 = "4444444444444"; // A match
        String visa2 = "5444444444444"; // Not a match
        String visa3 = "4444444444444444"; // A match
        String visa4 = "4444"; // Not a match

        System.out.println("----------------------------------------------");
        System.out.println("Visa 1: " + visa1.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("Visa 2: " + visa2.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("Visa 3: " + visa3.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("Visa 4: " + visa4.matches("^4[0-9]{12}([0-9]{3})?$"));
    }
}

/*
* Regex
* Regular Expression is a way to describe a String or a pattern expressions
*
* The simplest form of regex is a String literal - which is a literal String pattern and nothing alike the regex
* patterns
*
* String.replaceAll()
* This takes 2 params
* 1st param: The regex, the String pattern you want to target
* 2nd param: The new String you want in its place
*
* Character Classes & Boundary Matches
* Now this form of regex is very simple, but regex is a lot more than that
* There are "Character Classes" and "Boundary Matches"
*
* Character Classes: These are like a wildcards
* Boundary Matches: These looks at it as a set or classes - which look for boundaries such as the beginning of
* characters, and a boundary matcher at the end of a String or a word
*
* The Dot (.) Character
* The Dot character in regex will match any character, so the code below will output a long literal of "Y"
*   String alphanumeric = "abcDeeeF12Ghhiiiiijkl99z";
*   System.out.println(alphanumeric.replaceAll(".", "Y"));
*
* The Caret (^) Character
* The Caret character in regex means to start from the beginning of the String, and patterns after that will try to
* match the characters with the String
*
* This will output: YYYF12Ghhiiiiijkl99z
*   System.out.println(alphanumeric.replaceAll("^abcDeee", "YYY"));
*
* NOTE: The length of the regex does not have to be the same length of the replacement String
*
* The Dollar ($) character
* The Dollar character in regex means to match from the end of the a String
*
* This will output: abcDeeeF12GhhiiiiTHE END
*   System.out.println(alphanumeric.replaceAll("ijkl99z$", "THE END"));
*
* String.matches()
* This method takes a regex, and if there is a match within the whole String it will return true otherwise false
* From the alphanumeric sample - this will return false since the string literal "hello" is not in the alphanumeric
* String
*   System.out.println(alphanumeric.matches("^hello"));
*
* Now we know "^abcDee" worked when we did replaceAll(), but when we run this bit of code we get false as the output
* This is because the matches() method requires that the whole String must match the regex not a part of it
* System.out.println(alphanumeric.matches("^abcDeee"));
*
* Square brackets []
* The square brackets will match any range of character(s) within the square brackets
*
* This will replace all occurrences of "a", "e" and "i" with the letter "X": XbcDXXXF12GhhXXXXXjkl99z
*   System.out.println(alphanumeric.replaceAll("[aei]", "X"));
*
* If we wanted to be more specific with our replacement we can use 2 or more square brackets like so
* This will output: abcDeeX12GhhiiiiXkl99z
*   System.out.println(alphanumeric.replaceAll("[aei][Fj]", "X"));
*
* See we have less replacements in this case, this is because the regex is not only looking for the characters
* "a", "e" and "i" but also if they are followed by an "F" or a "j", then it will be replaced with the letter "X"
*
* Another case is this, if we wanted to replace an occurrence where the a name has been lowercase(d) and we want to
* titleise it we can do this
*    System.out.println("harry".replaceAll("[Hh]arry", "Harry"));
*
* Caret (^) inside Square Brackets
* With a Caret (^) inside a square bracket, it actually has a different set of meanings - instead of it being a
* Boundary Matcher (matches boundaries) it becomes a Character Class (wildcard)
*
* This will actually replace all occurrences of a String where the letters are not "e" or "j" so we get something
* like this
* Output: ZZZZeeeZZZZZZZZZZZjZZZZZ
*    System.out.println(alphanumeric.replaceAll("[^ej]", "Z"));
*
*
* Dash (-) inside Square Brackets
* This will cover a range of letters and numbers within the dashes
* This example will cover letters within lowercase a to f, uppercase A to F and integers from 3 to 8 (all inclusive)
* Output: ZZZZZZZZ12Ghhiiiiijkl99z
*   System.out.println(alphanumeric.replaceAll("[a-fA-F3-8]", "Z"));
*
* Special construct to remove case sensitivity from the regex (?i) followed by the range or letters - this works for
* ASCII Strings, if it were Unicode characters we would have to put (?iu) instead
* This will output exactly the same as the above code
* System.out.println(alphanumeric.replaceAll("(?i)[a-f3-8]", "Z"));
*
* Digits (\\d) & (\\D)
* The double backslash d and D represent digits from 0-9 (where \\d is filters all digits and \\D filters non-digits)
*   System.out.println(alphanumeric.replaceAll("[0-9]", "Z"));
*   System.out.println(alphanumeric.replaceAll("\\d", "Z"));
*   System.out.println(alphanumeric.replaceAll("\\D", "Z"));
*
*
* Whitespace (\t, \n etc.)
* The character class \\s will target all white space characters
* The character class \\S will target non-white space characters - basically meaning all if not all the text is gone
*
*   System.out.println(hasWhitespace.replaceAll("\\s", ""));
*   System.out.println(hasWhitespace.replaceAll("\\S", ""));
*   System.out.println(hasWhitespace.replaceAll("\\t", ""));
*
* \\w and \\W
* \\w covers a-z A-Z 0-9 and all whitespace characters so when used it mainly will replace everything
*   System.out.println(alphanumeric.replaceAll("\\w", "Z"));
*
* \\W will do cover the opposite of \\w
*
* \\b
* \\b will actually wrap around text or words, this code will output this
* ZIZ ZhaveZ ZblanksZ	ZaZ ZtabZ, ZandZ ZalsoZ ZaZ ZnewlineZ
*
* Each word is wrapped around with the letter "Z", this can be useful with XHTML if we want to wrap round words very
* quickly
*
*    System.out.println(hasWhitespace.replaceAll("\\b", "Z"));
*
* \\B will be in-between letters within words
* output: I hZaZvZe bZlZaZnZkZs	a tZaZb,Z aZnZd aZlZsZo a nZeZwZlZiZnZeZ
* System.out.println(hasWhitespace.replaceAll("\\B", "Z"));
*
* Quantifiers
* https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
* These are used in regex to a finite number of characters
*
* character{n} - n being a number, and character being an ASCII or Unicode char, the number represents how many of
* the characters there should be for a match
*
* {n,}: at least n times
*
* {n,m} (no spaces between them) this will match between bounds n being the lower bound and m being the upper bound,
* anything between the 2 will match, and anything on the extreme bounds will match as well
*
* +: represents 1 or more characters
*
* *: represents 0 or more characters
*
* ?: once or not all
*
*
* Pattern and Matcher Class
* These 2 classes are used in-conjuction with one another - as they both achieve regex matches
* and just like the String.matches() method - the matches() method here also wants to match the entire String too.
*
* The way to construct these 2 goes like this:
* either create a String pattern variable or write the String pattern as a param to Patter.compile
*
* Pattern.compile()
* This will accept a String which is going to be the regex that its going to look for
*
* Pattern.matcher()
* This will create an Matcher instance and this will accept the String we want to checking against
*
* We then use the matcher instance and its method .matches() which returns a boolean value depending whether or not
* the regex worked
*
*
* In this example, we have to place .* before and after the <h2> tag. This is because the matches() method wants to
* match the entire String as a whole and not part of it, so what we are saying here is . (any character) followed by
* a * (meaning o or more occurrences) and the same goes for after the <h2> tag
*       String h2Pattern = ".*<[hH]2>.*";
        Pattern pattern = Pattern.compile(h2Pattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());
*
*
* Matcher.find()
* Returns a boolean value, true if it finds the match within the String and false otherwise
*
* Mather.start()
* Returns an int value of the index of where the match occurred
*
* Matcher.end()
* Returns an int value of the index of where the match ends
*
*
* If we have used matches() method before these bits of code, then it will not work, this is because the Matcher
* objects internal state has been updated in such a way that pointer to the String is no longer able to be used
* the rest() method makes sure that pointer points to the beginning of the String
*
* When using start() and end() methods, we have to make sure that our pattern for regex is not broad but specific
*
* so a patten like .*<h2>.* is too broad and will result in indices 0 to (length - 1)
* but a pattern like <h2> will result in more precise indices
*       matcher.reset();
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + matcher.start() + " to " + matcher.end());
        }
*
* Group
* By wrapping parenthesis around the String pattern, we are not saying to include the parenthesises - but instead
* make it into a group, a group can be used to extract further information say within the tags that we have defined here
* We do everything the same likewise with the Pattern and Matcher
*
* but when it comes down to the println, we use the group() method and we pass the value 1
*
* Grouping numbers
* Capturing groups are numbered by counting their opening parentheses from left to right. In the expression
* ((A)(B(C))), for example, there are four such groups:
*   1 ((A)(B(C)))
*   2 (A)
*   3 (B(C))
*   4 (C)
* Group zero always stands for the entire expression. We can say 0 and 1 are the same
*
*       String h2GroupPattern = "(<h2>.+?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while (groupMatcher.find()) {
            System.out.println("Occurrences: " + groupMatcher.group(1));
        }
*
* https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html
*
* Logical Operators
* AND (XY) X followed by Y
* We have so far been using the AND operator
* "abc" is actually "a" AND "b" AND "c" - its actually implicit within the regex engine
*
* OR (X|Y) Either X or Y
* To use OR we use a single pipe character within the regex
* We so a case of OR, a bit of a crude method is to place it within a square bracket in the first example - but
* sometimes that will not always work, so by using a the pipe character (|) we can say either H or h needs to match
*   System.out.println("harry".replaceAll("[Hh]arry", "Larry"));
*   System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
*
* NOT (?!char) Negative look-ahead approach
* We used a crude method to simulate NOT by using a caret character (^) within square brackets to signify we do not
* want those character to pop for us
*
* To use the NOT operator, we use what is called a "look-ahead" approach which means we have to wrap the sequence of
* character(s) within a (?!). This is in turn is better than the [^], simply because the caret method says that any
* character sequence except the ones in the brackets - meaning that it considers itself a character
*
* Caret Method
* Here we are saying match any characters of t that are not followed by a v
* So in this case there are 3 occurrences
* 1. ts
* 2. tk
* 3. t
*
* But when using the caret method, we only get 2 occurrences and not 3, this is because the t[^v], the [^v] tells the
* compiler that any character except v, meaning it excepts another character there, which in case of occurrence 3, it
*  is followed by whitespace and not an ASCII char which is not going to work
*       String tvTest = "tstvtkt";
        String tvRegex = "t[^v]";

        Pattern tvPattern = Pattern.compile(tvRegex);
        Matcher tvMatcher = tvPattern.matcher(tvTest);

        int count1 = 0;
        while (tvMatcher.find()) {
            count1++;
            System.out.println("Occurrences " + count1 + " : " + tvMatcher.start() + " to " + tvMatcher.end());
        }
*
*
* (?!) method
* This method instead does not consume a character and we get the 3 occurrences as we would expect
*
*       String tvTest = "tstvtkt";
        String tvRegex = "t(?!v)";
        Pattern tvPattern = Pattern.compile(tvRegex);
        Matcher tvMatcher = tvPattern.matcher(tvTest);

        int count1 = 0;
        while (tvMatcher.find()) {
            count1++;
            System.out.println("Occurrences " + count1 + " : " + tvMatcher.start() + " to " + tvMatcher.end());
        }
*
* (?=) Positive look-ahead approach
* This is the opposite to the negative look-ahead approach (?!), where we said all cases of t not followed by v (but
* don't include the v of course)
*
* The positive look ahead approach simply means to include the v in the search
* so t(?=v) means match any cases of t followed by v, but don't include v within the final output etc.
*
* NOTE: To include character that are part of the regex engine, we use \\ or \ to escape it and tell the JVM we want
* to include it in our search
* NOTE: to include parenthesis we use the escape character (\\) backslash to indicate to JVM we want to include that
* in our search
*
*
* */
