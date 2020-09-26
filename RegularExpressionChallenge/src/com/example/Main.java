package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	    String challenge1 = "I want a bike.";
//        System.out.println(challenge1.matches("I want a bike."));

        String challenge2 = "I want a ball.";
        String regex = "I want a \\w+.";
//        System.out.println(challenge1.matches(regex));
//        System.out.println(challenge2.matches(regex));

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(challenge1);
        System.out.println(matcher.matches());

        matcher = pattern.matcher(challenge2);
        System.out.println(matcher.matches());

        String challenge4 = "Replace all blanks with underscores.";
        System.out.println(challenge4.replaceAll("\\s", "_"));

        String challenge5 = "aaabccccccccdddefffg";
        System.out.println(challenge5.matches("^a+bc+d+ef+g$"));
        System.out.println(challenge5.matches("^a{3}bc{8}d{3}ef{3}g$"));
        System.out.println(challenge5.matches("[a-g]+"));

        String challenge6 = "abcd.135";
        System.out.println(challenge6.matches("^[a-zA-Z]+[\\.]\\d+$"));
        System.out.println(challenge6.replaceAll("^[a-zA-Z]+[\\.]\\d+$", "REPLACED"));

        String challenge7 = "abcd.135uvqz.7tzik.999";
        Pattern pattern1 = Pattern.compile("([a-zA-Z]+[\\.](\\d+))");
        Matcher matcher1 = pattern1.matcher(challenge7);

        int count = 0;
        while (matcher1.find()) {
            count++;
            System.out.println((count < 2 ? "Occurrence " : "Occurrences ") + count + " : " + matcher1.group(2));
        }

        System.out.println("-----------------------------");
        String challenge8 = "abcd.135\tuvqz.7\ttzik.999\n";
        Pattern pattern2 = Pattern.compile("([a-zA-Z]+[\\.](\\d+)\\s)");
        Matcher matcher2 = pattern2.matcher(challenge8);

        count = 0;
        while (matcher2.find()) {
            count++;
            System.out.println((count < 2 ? "Occurrence " : "Occurrences ") + count + " : " + matcher2.group(2));
            System.out.println("Start: " + matcher2.start(2) + " to " + (matcher2.end(2) - 1) + "\n");
        }

        String challenge9 = "{0, 2}, {0, 5}, {1, 3}, {2, 4}, {x, y}, {5, 5}";
        Pattern pattern3 = Pattern.compile("\\{(\\w+, \\w+)\\}");
//        Pattern pattern3 = Pattern.compile("\\{(.+?)\\}");
        Matcher matcher3 = pattern3.matcher(challenge9);

        count = 0;
        while (matcher3.find()) {
            count++;
            System.out.println((count < 2 ? "Occurrence " : "Occurrences ") + count + " : " + matcher3.group(1));
        }

        String challenge10 = "11111";
        Pattern pattern4 = Pattern.compile("^\\d{5}$");
        Matcher matcher4 = pattern4.matcher(challenge10);
        System.out.println(matcher4.matches());

        String challenge11 = "11111-1111";
        System.out.println(challenge11.matches("^\\d{5}-\\d{4}$"));

        String regex2 = "^\\d{5}(-\\d{4})?$";
        System.out.println(challenge10.matches(regex2));
        System.out.println(challenge11.matches(regex2));
    }
}
