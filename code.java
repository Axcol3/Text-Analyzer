import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class code { 
    public static void main(String[] args) { 
        // Creation bool variable to control when function should stop 
        boolean exit = true;
        int choice = 0;
        while (exit) {
            // Reading file
            String str1 = readFile("/Users/alina/Desktop/coding/java/filename.txt"); // Читаем файл
            // Check if file damaged or not exist
            if (str1 == null) {
                System.out.println("Error! Can't read file ");
                return;
            }
            String[] lines = str1.split("\\s+");
        
            ArrayList<String> optionsList = new ArrayList<>();

            // Перебираем все строки и ищем, где встречается "OPTION:"
            for (String line : lines) {
                if (line.startsWith("OPTION:")) {
                    // Добавляем строку в список
                    optionsList.add(line.substring(7).trim());
                }
            }
            
            // Show menu
            printMenu();
            Scanner input = new Scanner(System.in);
            // Clean string if user input not valid option
           
            // Start function again if string is empty or there are no numbers
            // Transform String to integer (option)
            int option = Integer.parseInt(optionsList.get(choice));
            int nextoption = 0;
            if (Integer.parseInt(optionsList.get(choice)) != 10) {
                nextoption = Integer.parseInt(optionsList.get(choice + 1));
            }
            choice++;
            // Consume the remaining newline
            // Divides words with space
            String[] arrStr = str1.replaceAll("[^a-zA-Zа-яА-Я\\s-]", "").split("\\s+");
            // Return false if user input 'exit'
            // And start again function if it is not false 
            exit = chooseOption(option, nextoption, str1.trim(), exit, arrStr);
        }
    }
    
    // Function for reading file 
    static String readFile(String filename) {
        StringBuilder str = new StringBuilder();
        try {
            File file = new File(filename);
            System.out.println("\nTrying to open file: " + file.getAbsolutePath());  // Печатаем абсолютный путь
            Scanner scanner = new Scanner(file);  // Открываем файл с помощью Scanner

            while (scanner.hasNextLine()) {
                str.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            e.printStackTrace();
            return null;
        }
        return str.toString();
    }
    
    // Method to write to file
    static void writeToFile(String outputFile, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
            writer.write(content);
            writer.newLine();  // Add new line
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    // Menu of choices
    static void printMenu() {
        System.out.println("\t\t\t\t ---------MENU---------\n");
        System.out.println("1 : Total count word");
        System.out.println("2 : Unique word count");
        System.out.println("3 : Average word length");
        System.out.println("4 : Sentence count");
        System.out.println("5 : Most frequent words");
        System.out.println("6 : Longest word");
        System.out.println("7 : Shortest word");
        System.out.println("8 : Delete word or sentence ignoring Case");
        System.out.println("9 : Extract all strings with number");
        System.out.println("10 : Exit");
        System.out.print("\n\nChoose one option : ");
    }

    // Function to control if user input number more than 10
    static boolean chooseOption(int option, int nextoption, String str1, boolean exit, String[] arrStr) {
        StringBuilder output = new StringBuilder(); // String for accumulating the output
        
        switch(option) {
            case 1:
                output.append(String.format("Total count word : %d", countWord(arrStr)));
                break;
            case 2:
                output.append(String.format("Unique word count : %d", countUniqueWord(arrStr)));
                break;
            case 3:
                output.append(String.format("Average word length : %.2f", averageLength(arrStr)));
                break;
            case 4:
                output.append(String.format("Sentence count : %d", sentCount(str1)));
                break;
            case 5:
                output.append("Most frequent words : ").append(FrequentWord(arrStr));
                break;
            case 6:
                output.append("Longest word : ").append(longWord(arrStr));
                break;
            case 7:
                output.append("Shortest word : ").append(shortWord(arrStr));
                break;
            case 8:
                output.append("Text without word :\n").append(deleteWord(str1, option, nextoption));
                break;
            case 9:
                output.append(extText(str1, option, nextoption));
                break;
            case 10:
                output.append("Exiting...");
                exit = false;
                break;
            default:
                output.append("Please enter correct option!");
                break;
        }

        // Write the output to a file
        writeToFile("/Users/alina/Desktop/coding/java/output.txt", output.toString());

        // Also print to console if needed
        System.out.println(output.toString());
        
        return exit; 
    }

    // Function for counting words
    static int countWord(String[] arrStr) {
        // Create parameter to count words
        int count = 0;
        // Scans all elements of an array
        for(String element : arrStr) {
            count++;
        }
        // Return number of words
        return count;
    }

    // Function for counting unique words
    static int countUniqueWord(String[] arrStr) {
        // Create StringBuilder for adding unique words
        StringBuilder str = new StringBuilder();
        // Create parameter to count words
        int count = 0;
        for (String element : arrStr) {
            // If StringBuilder is empty, add the first unique word
            if (str.isEmpty()) {
                str.append(element.toLowerCase());
                count++;
            }
            // If it didn't find word in str, then function adds word in str and the counter is incremented
            else if (str.indexOf(element.toLowerCase()) == -1) {
                str.append(element.toLowerCase());
                count++;
            }
        }
        // Return number of unique words in str
        return count;
    }

    // Function for finding average length of all words
    static double averageLength(String[] arrStr) {
        double len = 0;
        int count = 0;
        for (String element : arrStr) {
            count++;
            len += element.length();
        }
        return len / count;
    }

    // Function to count number of sentences
    static int sentCount(String str1) {
        int count = 0;
        String end = "?.!"; 
        for (int i = 0; i < str1.length(); i++) {
            if (end.indexOf(str1.charAt(i)) != -1) {
                if (i + 1 == str1.length() || end.indexOf(str1.charAt(i + 1)) == -1) {
                    count++;  
                }   
            }
        }
        return count;
    }

    // Function to count all appearances of each word
    static StringBuilder FrequentWord(String[] arrStr) {
        StringBuilder res = new StringBuilder();
        StringBuilder res1 = new StringBuilder();
        for (String element : arrStr) {
            int count = 0;
            for (String el : arrStr) {
                if (element.equals(el) && res1.indexOf(el) == -1) {
                    count++;
                }
            }
            res1.append(element);
            if (count != 0) {
                res.append(element).append(" : ").append("\t").append(count).append("\n");
            }
        }
        return res;
    }

    // Function to find the longest word
    static StringBuilder longWord(String[] arrStr) {
        StringBuilder res = new StringBuilder();
        Arrays.sort(arrStr, (str3, str4) -> Integer.compare(str3.length(), str4.length()));
        int max = arrStr[arrStr.length - 1].length();
        res.append(arrStr[arrStr.length - 1].toUpperCase());
        for (String elem : arrStr) {
            if (max == elem.length()) {
                if (res.indexOf(elem.toUpperCase()) == -1) {
                    if (!res.isEmpty()) {
                        res.append(", ");
                    }
                    res.append(elem.toUpperCase());
                }
            }
        }
        return res;
    }

    // Function for finding the shortest word
    static StringBuilder shortWord(String[] arrStr) {
        StringBuilder res = new StringBuilder();
        Arrays.sort(arrStr, (str3, str4) -> Integer.compare(str3.length(), str4.length()));
        int min = arrStr[0].length();
        res.append(arrStr[0].toUpperCase());
        for (String elem : arrStr) {
            if (min == elem.length()) {
                if (res.indexOf(elem.toUpperCase()) == -1) {
                    if (!res.isEmpty()) {
                        res.append(", ");
                    }
                    res.append(elem.toUpperCase());
                }
            }
        }
        return res;
    }

    // Function to delete word or sentence
    static String deleteWord(String str1, int choice, int nextoption) {
        System.out.println("\nEnter word or sentence which you want delete from text :");
        String ss = "OPTION:" + Integer.toString(choice);
        String ss1 = "OPTION:" + Integer.toString(nextoption);
        String s = str1.substring(str1.indexOf(ss) + ss.length(), str1.indexOf(ss1)).trim();
        String res = str1.replaceAll("\\b" + Pattern.quote(s) + "\\b", "").trim();
        return res.trim();
    }

    // Function to extract sentences with number
    static StringBuilder extText(String str1, int choice, int nextoption) {
        String ss = "OPTION:" + Integer.toString(choice);
        String ss1 = "OPTION:" + Integer.toString(nextoption);
        String[] arrSen = str1.split("[!.?]");
        StringBuilder res = new StringBuilder();
        System.out.println("Input number which you want find : ");
        String s = str1.substring(str1.indexOf(ss) + ss.length(), str1.indexOf(ss1)).trim();
        int num = Integer.parseInt(s);
        for (String elem : arrSen) {
            if (elem.matches(".*\\b" + num + "\\b.*")) {
                res.append(elem).append("\n");
            }
        }
        if (res.length() == 0) {
            System.out.println("\nThere are no sentences with this number!");
        } else {
            System.out.println("\nSentences with number : ");
        }
        return res;
    }
}
