package main.java.uk.sky;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        Main main = new Main();
        long l = 0;
        System.out.println("long" + l);

    }

    private Collection readFile(String s) {
        BufferedReader br = null;
        List<String> list = null;

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(s));
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains("US")) {
                    list = Arrays.asList(sCurrentLine);
                    for (String str : list) {
                        System.out.println(str);
                        System.out.println(str.substring(str.lastIndexOf(",")+1));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
}
