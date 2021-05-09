package lab;
import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        File input = new File("test.txt");
        writeLineToFile(input);
        readThreeSymbolsFromFile(input);
        writeStringAndIntsToFile();
        int[] frequencies = countLettersFromFile();
        //writeFrequenciesToFile(frequencies);
        writeHistogramToFile(frequencies);

    }

    private static void writeHistogramToFile(int[] frequencies) throws IOException {
        File frequencyFile = new File("histogram.txt");
        frequencyFile.createNewFile();
        PrintWriter frequencyWriter = new PrintWriter(frequencyFile);
        for(int i = 0; i < frequencies.length; i++){
            char symbol = (char)('A' + i); // convert index to letter
            frequencyWriter.print(symbol + " : ");
            // write as many stars as frequency of the letter
            for(int j = 0; j < frequencies[i]; j++){
                frequencyWriter.print("*");
            }
            frequencyWriter.println();
        }
        frequencyWriter.flush();
        frequencyWriter.close();
    }

    private static void writeFrequenciesToFile(int[] frequencies) throws IOException {
        File frequencyFile = new File("frequency.txt");
        frequencyFile.createNewFile();
        PrintWriter frequencyWriter = new PrintWriter(frequencyFile);
        for(int i = 0; i < frequencies.length; i++){
            char symbol = (char)('A' + i); // convert index to letter
            frequencyWriter.println(symbol + " = " + frequencies[i]);
        }
        frequencyWriter.flush();
        frequencyWriter.close();
    }

    private static int[] countLettersFromFile() throws IOException {
        File inputFile = new File("input.txt");
        inputFile.createNewFile();
        BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
        Character inputSymbol = readSymbol(inputReader);
        // Create an array to store letters frequencies
        int[] frequencies = new int[26];
        while(inputSymbol != null){
            char c = Character.toUpperCase(inputSymbol);
            if(c >= 'A' && c <= 'Z'){
                frequencies[c - 'A']++;
            }
            // Read next symbol
            inputSymbol = readSymbol(inputReader);
        }
        System.out.println(Arrays.toString(frequencies));
        return frequencies;
    }

    // Create a file and write a String, an Integer and an int to a file.
    private static void writeStringAndIntsToFile() throws IOException {
        File file = new File("Lab.txt");
        file.createNewFile();
        String line = "Nice day";
        Integer number1 = 9;
        int number2 = 6;
        PrintWriter writer = new PrintWriter(file);
        writer.println(line);
        writer.println(number1);
        writer.println(number2);
        writer.flush();
        writer.close();
    }

    private static void readThreeSymbolsFromFile(File input) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        System.out.println(readSymbol(reader));
        System.out.println(readSymbol(reader));
        System.out.println(readSymbol(reader));
    }

    private static void writeLineToFile(File input) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(input);
        printWriter.print("d\nhfghgdjg\rgg");
        printWriter.flush();
        printWriter.close();
    }

    // Read a symbol from the file
    public static Character readSymbol(BufferedReader reader) throws IOException {
        int code = reader.read();
        if(code == -1){
            return null;
        }
        Character symbol = (char) code;
        if (symbol == '\r' || symbol == '\n'){
            symbol = readSymbol(reader); // use a recursion to read next symbol
        }
        return symbol;
    }
}
