package main.java.uk.sky;

import java.io.*;
import java.util.*;

public class DataFilterer {

    private static BufferedReader getBufferedReader(Reader source) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(source);
        readHeader(bufferedReader);
        return bufferedReader;
    }

    private static void readHeader(BufferedReader bufferedReader) throws IOException {
        bufferedReader.readLine();
    }

    private static List getLineByCountry(BufferedReader bufferedReader, String country) throws IOException {
        String currentLine;
        List linesWithMatchingCountry = new ArrayList<>();
        while ((currentLine
                = bufferedReader.readLine()) != null) {
            if (currentLine.contains(country)) {
                linesWithMatchingCountry.add(currentLine);
            }
        }
        return linesWithMatchingCountry;
    }

    private static List getLineByCountryAndAboveResponseTime(BufferedReader bufferedReader, String country, long limit) throws IOException {
        String currentLine;
        List linesWithMatchingCountry = new ArrayList<>();
        while ((currentLine = bufferedReader.readLine()) != null) {
            if (currentLine.contains(country) && isAboveResponseTime(currentLine, limit)) {
                linesWithMatchingCountry.add(currentLine);
            }
        }
        return linesWithMatchingCountry;
    }

    private static boolean isAboveResponseTime(String sCurrentLine, long limit) {
        if (Long.parseLong(sCurrentLine.substring(sCurrentLine.lastIndexOf(",") + 1)) > limit) {
            return true;
        }
        return false;
    }


    public static Collection<?> filterByCountry(Reader source, String country) throws IOException {
        BufferedReader bufferedReader = getBufferedReader(source);
        List result =  getLineByCountry(bufferedReader, country);
        return result;
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) throws IOException {

        BufferedReader bufferedReader = getBufferedReader(source);
        List result =  getLineByCountryAndAboveResponseTime(bufferedReader, country, limit);
        return result;
    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
        return Collections.emptyList();
    }

}

