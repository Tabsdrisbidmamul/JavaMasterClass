package com.company;

public class Main {

    private static final String INVALID_VALUE_MSG = "Invalid value";

    public static void main(String[] args) {
        System.out.println(getDurationString(90, 45));
        System.out.println(getDurationString(61, 0));
        System.out.println(getDurationString(90));
        System.out.println(getDurationString(349));
        System.out.println(getDurationString(-1000));
        System.out.println(getDurationString(65, 9));
    }

    private static String getDurationString(int minutes, int seconds) {
        if ( (minutes < 0) || (seconds < 0 || seconds > 59) ) {
            return INVALID_VALUE_MSG;
        } else {
            int hours = minutes / 60;
            int minutesRemaining = minutes % 60;

            return formatter(hours, "hours") +
                    formatter(minutesRemaining, "minutes" ) +
                    formatter(seconds, "seconds");
        }
    }

    private static String getDurationString(int seconds) {
        if ( seconds < 0 ) {
            return INVALID_VALUE_MSG;
        } else {
            int minutes = seconds / 60;
            int secondsRemaining = seconds % 60;

            return getDurationString(minutes, secondsRemaining);
        }
    }

    private static String formatter(int timeValue, String timeValueDesc) {
        if (timeValue < 10 && timeValueDesc.equals("hours")) {
            return "0" + timeValue + "h ";
        } else if (timeValue < 10 && timeValueDesc.equals("minutes")) {
            return "0" + timeValue + "m ";
        } else if (timeValue < 10 && timeValueDesc.equals("seconds")) {
            return "0" + timeValue + "s";
        } else if (timeValue > 10 && timeValueDesc.equals("hours")) {
            return timeValue + "h ";
        } else if (timeValue > 10 && timeValueDesc.equals("minutes")) {
            return timeValue + "m ";
        } else if (timeValue > 10 && timeValueDesc.equals("seconds")) {
            return timeValue + "s";
        }
        return INVALID_VALUE_MSG;
    }
}


/*
* Constants
* We use the keyword 'final' to indicate to Java that this object is a constant - a value which once instantiated
* cannot change.
*
* The example above mentions static before it - this is to tell Java that we do not need to create an object -
* instantiate the object for it be created and used - its already there when the program starts.
* */