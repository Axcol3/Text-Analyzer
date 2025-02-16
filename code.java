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
        boolean exit = true; // Flag to control the loop
        int choice = 0; // Variable to store the user's choice
        while (exit) { // Continue running as long as exit is true
            // Reading file
            String str1 = readFile("/Users/alina/Desktop/coding/java/filename.txt"); // Reads the file
            // Check if file damaged or not exist
            if (str1 == null) { // If the file can't be read
                System.out.println("Error! Can't read file ");
                return; // Stop the program if the file can't be read
            }
            // Split the content of the file into individual words or lines
            String[] lines = str1.split("\\s+"); 
        
            ArrayList<String> optionsList = new ArrayList<>(); // List to store options from the file

            // Check each line for an "OPTION:" keyword and add it to the list
            for (String line : lines) {
                if (line.startsWith("OPTION:")) { // Find lines starting with "OPTION:"
                    optionsList.add(line.substring(7).trim()); // Extract the option after "OPTION:"
                }
            }
            
            // Show the menu of available options
            printMenu();
            Scanner input = new Scanner(System.in); // To read user input
            // Clean string if user input not valid option
           
            // Start function again if string is empty or there are no numbers
            // Transform String to integer (option)
            int option = Integer.parseInt(optionsList.get(choice)); // Get the user's option
            int nextoption = 0; // Variable to store the next option
            if (Integer.parseInt(optionsList.get(choice)) != 10) { // If option is not 10 (Exit)
                nextoption = Integer.parseInt(optionsList.get(choice + 1)); // Get next option
            }
            choice++; // Move to the next option
            // Consume the remaining newline
            // Divides words with space
            String[] arrStr = str1.replaceAll("[^a-zA-Zа-яА-Я\\s-]", "").split("\\s+"); // Clean and split the text into words
            // Return false if user input 'exit'
            // And start again function if it is not false 
            exit = chooseOption(option, nextoption, str1.trim(), exit, arrStr); // Process the user's choice
        }
    }
    
    // Function for reading file 
    static String readFile(String filename) {
        StringBuilder str = new StringBuilder(); // StringBuilder to store file content
        try {
            File file = new File(filename); // Create a file object with the given filename
            System.out.println("\nTrying to open file: " + file.getAbsolutePath());  // Print the absolute path of the file
            Scanner scanner = new Scanner(file);  // Open the file using Scanner

            while (scanner.hasNextLine()) { // Read line by line
                str.append(scanner.nextLine()).append("\n"); // Append each line to the StringBuilder
            }
        } catch (FileNotFoundException e) { // If the file is not found
            System.out.println("File not found: " + filename);
            e.printStackTrace(); // Print error details
            return null; // Return null if the file is not found
        }
        return str.toString(); // Return the file content as a string
    }
    
    // Method to write to file
    static void writeToFile(String outputFile, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
            writer.write(content); // Write the content to the file
            writer.newLine();  // Add a new line after writing
        } catch (IOException e) { // If an error occurs while writing
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    // Menu of choices for the user
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

    // Function to handle user's choice and perform corresponding action
    static boolean chooseOption(int option, int nextoption, String str1, boolean exit, String[] arrStr) {
        StringBuilder output = new StringBuilder(); // StringBuilder to accumulate the output
        
        switch(option) { // Check user's choice and execute the corresponding action
            case 1:
                output.append(String.format("Total count word : %d", countWord(arrStr))); // Count words
                break;
            case 2:
                output.append(String.format("Unique word count : %d", countUniqueWord(arrStr))); // Count unique words
                break;
            case 3:
                output.append(String.format("Average word length : %.2f", averageLength(arrStr))); // Calculate average word length
                break;
            case 4:
                output.append(String.format("Sentence count : %d", sentCount(str1))); // Count sentences
                break;
            case 5:
                output.append("Most frequent words : ").append(FrequentWord(arrStr)); // Find frequent words
                break;
            case 6:
                output.append("Longest word : ").append(longWord(arrStr)); // Find longest word
                break;
            case 7:
                output.append("Shortest word : ").append(shortWord(arrStr)); // Find shortest word
                break;
            case 8:
                output.append("Text without word :\n").append(deleteWord(str1, option, nextoption)); // Delete word or sentence
                break;
            case 9:
                output.append(extText(str1, option, nextoption)); // Extract sentences with number
                break;
            case 10:
                output.append("Exiting..."); // Exit the program
                exit = false; // Stop the loop
                break;
            default:
                output.append("Please enter correct option!"); // Invalid option
                break;
        }

        // Write the output to a file
        writeToFile("/Users/alina/Desktop/coding/java/output.txt", output.toString());

        // Print the output to the console
        System.out.println(output.toString());
        
        return exit; // Return the exit flag (whether to continue or stop)
    }

    // Function for counting total number of words
    static int countWord(String[] arrStr) {
        int count = 0; // Counter for words
        for(String element : arrStr) { // Loop through each word in the array
            count++; // Increment the counter for each word
        }
        return count; // Return the total word count
    }

    // Function for counting unique words
    static int countUniqueWord(String[] arrStr) {
        StringBuilder str = new StringBuilder(); // StringBuilder to store unique words
        int count = 0; // Counter for unique words
        for (String element : arrStr) { // Loop through each word in the array
            // If the word is not already in the StringBuilder, add it
            if (str.isEmpty()) {
                str.append(element.toLowerCase());
                count++;
            } else if (str.indexOf(element.toLowerCase()) == -1) { // If word is not already added
                str.append(element.toLowerCase());
                count++;
            }
        }
        return count; // Return the unique word count
    }

    // Function for calculating the average word length
    static double averageLength(String[] arrStr) {
        double len = 0; // To accumulate the total length of words
        int count = 0; // To count the number of words
        for (String element : arrStr) { // Loop through each word in the array
            count++; // Increment word counter
            len += element.length(); // Add the length of the word to the total
        }
        return len / count; // Return the average length
    }

    // Function to count the number of sentences in the text
    static int sentCount(String str1) {
        int count = 0; // To count sentences
        String end = "?.!"; // Possible sentence-ending characters
        for (int i = 0; i < str1.length(); i++) { // Loop through the text character by character
            if (end.indexOf(str1.charAt(i)) != -1) { // If a sentence-ending character is found
                // Check if it is the end of a sentence
                if (i + 1 == str1.length() || end.indexOf(str1.charAt(i + 1)) == -1) {
                    count++;  // Increment sentence count
                }   
            }
        }
        return count; // Return the sentence count
    }

    // Function to find the most frequent words in the text
    static StringBuilder FrequentWord(String[] arrStr) {
        StringBuilder res = new StringBuilder(); // StringBuilder to store results
        StringBuilder res1 = new StringBuilder(); // StringBuilder to track already processed words
        for (String element : arrStr) { // Loop through each word in the array
            int count = 0; // Counter for word frequency
            for (String el : arrStr) { // Loop through the array again to count occurrences
                if (element.equals(el) && res1.indexOf(el) == -1) { // If the word is found and not already processed
                    count++;
                }
            }
            res1.append(element); // Mark the word as processed
            if (count != 0) { // If the word appeared at least once
                res.append(element).append(" : ").append("\t").append(count).append("\n"); // Add to result
            }
        }
        return res; // Return the result
    }

    // Function to find the longest word
    static StringBuilder longWord(String[] arrStr) {
        StringBuilder res = new StringBuilder(); // StringBuilder to store the longest word(s)
        Arrays.sort(arrStr, (str3, str4) -> Integer.compare(str3.length(), str4.length())); // Sort words by length
        int max = arrStr[arrStr.length - 1].length(); // Get the length of the longest word
        res.append(arrStr[arrStr.length - 1].toUpperCase()); // Add the longest word to the result
        for (String elem : arrStr) { // Loop through the words again to find all longest words
            if (max == elem.length()) { // If the word has the same length as the longest word
                if (res.indexOf(elem.toUpperCase()) == -1) { // If it's not already in the result
                    if (!res.isEmpty()) {
                        res.append(", "); // Separate with a comma
                    }
                    res.append(elem.toUpperCase()); // Add the word
                }
            }
        }
        return res; // Return the result
    }

    // Function to find the shortest word
    static StringBuilder shortWord(String[] arrStr) {
        StringBuilder res = new StringBuilder(); // StringBuilder to store the shortest word(s)
        Arrays.sort(arrStr, (str3, str4) -> Integer.compare(str3.length(), str4.length())); // Sort words by length
        int min = arrStr[0].length(); // Get the length of the shortest word
        res.append(arrStr[0].toUpperCase()); // Add the shortest word to the result
        for (String elem : arrStr) { // Loop through the words again to find all shortest words
            if (min == elem.length()) { // If the word has the same length as the shortest word
                if (res.indexOf(elem.toUpperCase()) == -1) { // If it's not already in the result
                    if (!res.isEmpty()) {
                        res.append(", "); // Separate with a comma
                    }
                    res.append(elem.toUpperCase()); // Add the word
                }
            }
        }
        return res; // Return the result
    }

    // Function to delete word or sentence from text
    static String deleteWord(String str1, int choice, int nextoption) {
        System.out.println("\nEnter word or sentence which you want delete from text :");
        String ss = "OPTION:" + Integer.toString(choice); // Extract the option from the string
        String ss1 = "OPTION:" + Integer.toString(nextoption); // Extract the next option
        String s = str1.substring(str1.indexOf(ss) + ss.length(), str1.indexOf(ss1)).trim(); // Get the part to delete
        String res = str1.replaceAll("\\b" + Pattern.quote(s) + "\\b", "").trim(); // Delete the word or sentence
        return res.trim(); // Return the updated text
    }

    // Function to extract sentences with a specific number
    static StringBuilder extText(String str1, int choice, int nextoption) {
        String ss = "OPTION:" + Integer.toString(choice); // Extract the option from the string
        String ss1 = "OPTION:" + Integer.toString(nextoption); // Extract the next option
        String[] arrSen = str1.split("[!.?]"); // Split text into sentences
        StringBuilder res = new StringBuilder(); // StringBuilder to store results
        System.out.println("Input number which you want find : ");
        String s = str1.substring(str1.indexOf(ss) + ss.length(), str1.indexOf(ss1)).trim(); // Get the number to search for
        int num = Integer.parseInt(s); // Parse the number
        
        for (String elem : arrSen) { // Loop through sentences
            if (elem.matches(".*\\b" + num + "\\b.*")) { // Check if the sentence contains the number
                res.append(elem).append("\n"); // Add the sentence to the result
            }
        }
        if (res.length() == 0) { // If no sentences are found
            System.out.println("\nThere are no sentences with this number!");
        } else {
            System.out.println("\nSentences with number : ");
        }
        return res; // Return the result
    }
}
