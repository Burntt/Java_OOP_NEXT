package space.harbour.hw2;

import java.util.Scanner;
import java.util.StringTokenizer;


public class Slang {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the String:");
        String inputStr = scan.nextLine();

        //String inputStr = "FYI my dog is very :(. ¯\\_(ツ)_/¯";
        //String inputStr = "Hey, Johnny, GTFO! I’m too tired :)";

        String words = inputStr.replaceAll("[{},.!?<>%]", "");

        System.out.println(words);
        StringTokenizer defaultTokenizer = new StringTokenizer(words);


        StringBuilder decodedString = new StringBuilder();
        while (defaultTokenizer.hasMoreTokens()) {
            String tokenized = defaultTokenizer.nextToken();
            String decodedAbbrev = fixAbbrevations(tokenized);
            String decodedSmiley = slang(decodedAbbrev);
            decodedString.append(" " + decodedSmiley);
        }
        System.out.println(decodedString);

    }

    public static String fixAbbrevations(String anyWord) {

        if (anyWord.contains("LOL")) {
            return "please";
        } else if (anyWord.contains("FYI")) {
            return "for your information";
        } else if (anyWord.contains("GTFO")) {
            return "please, leave me alone";
        } else if (anyWord.contains("ASAP")) {
            return "as soon as possible";
        } else {
            return anyWord;
        }
    }

    public static String slang(String anyWord) {

        if (anyWord.contains(":)")) {
            return "[smiling]";
        } else if (anyWord.contains(":(")) {
            return "[sad]";
        } else if (anyWord.contains("¯\\_(ツ)_/¯")) {
            return "[such is life]";
        } else {
            return anyWord;
        }

    }
}

