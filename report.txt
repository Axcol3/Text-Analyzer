# Java Text Processor Project

## Documentation

### Project Description:
This project is a text processor written in Java. The program reads a text file, processes the text, and then outputs the results based on the user's choice. The user can choose to count words, find unique words, calculate the average word length, and perform other operations on the text.

### Design Choices:
- **Input Handling**: The program reads text files using `Scanner` and processes each line.
- **Output Handling**: Results are written to a file using `BufferedWriter`.
- **Modular Functions**: The program is divided into functions to perform different tasks like counting words, finding frequent words, and more. This makes the code easier to maintain and extend.

### Challenges:
- Extracting data from the input files was challenging, especially when handling special characters.
- Implementing efficient algorithms to count words, sentences, and find frequent words required careful design.

### Algorithms and Data Structures:
- **Arrays**: Used to store the split text and individual words.
- **ArrayList**: Used to store the options read from the text file.
- **StringBuilder**: Used to build the output text and handle string manipulations efficiently.
- **Regular Expressions**: Used to search and process specific patterns in the text.

### Improvements:
- Initially, there were no checks for invalid input. I added error handling to ensure that the user enters valid options.
- I optimized some functions for better performance when working with larger text files.

### Input and Output Files:
- **Input Files**: The input files (`input1.txt`, `input2.txt`, etc.) contain text data that the program reads and processes.
- **Output File**: The results of the processing are saved to an output file (`output.txt`).

### Example Output:
Here is an example of what the output looks like when the program runs:

