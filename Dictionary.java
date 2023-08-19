import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class representing a dictionary that stores words and their definitions.
 * It provides methods for adding, removing, and retrieving words and their definitions.
 * The class uses a HashMap to store words and their definitions as an ArrayList of Strings.
 * The key of the HashMap is the word and the value is the ArrayList of its definitions.
 * The class also provides methods for loading and saving dictionary data to a file.
 * 
 * @author Joseph Liu
 * author email: tianlinl@student.unimelb.edu.au
 * author student number: 1101805
 */

public class Dictionary {
	// A HashMap to store the words and their definitions.
	private HashMap<String, ArrayList<String>> words = new HashMap<>();
	// The file path of the dictionary.
	private String dictFile;

	/**
	 * Constructor to create a new dictionary object with the specified file path.
	 * 
	 * @param dictFile - the file path of the dictionary file.
	 */
	public Dictionary(String dictFile) {
		this.dictFile = dictFile;
	}
	
	/**
	 * Returns the file path of the dictionary
	 * @return - the file path of the dictionary
	 */
	public String getDictFile() {
		return dictFile;
	}
	
	/**
	 * Adds a new word to the dictionary.
	 * @param newWord - the word to be added
	*/
	public synchronized void addWord(String newWord) {
		ArrayList<String> defArrayList = new ArrayList<>();
		words.put(newWord, defArrayList);
	}

	/**
	 * Adds a new definition to an existing word in the dictionary.
	 * @param newWord - the word to which the definition will be added
	 * @param definition - the definition to be added
	 */
	public synchronized void addDef(String newWord, String definition) {
		words.get(newWord).add(definition);
	}

	/**
	 * Returns the definitions of a given word.
	 * @param word - the word whose definitions are to be retrieved
	 * @return - an ArrayList of Strings representing the definitions of the word
	 */
	public ArrayList<String> getDefinition(String word) {
		return words.get(word);
	}

	/**
	 * Checks if a word exists in the dictionary.
	 * @param word - the word to be checked
	 * @return - true if the word exists in the dictionary, false otherwise
	 */
	public Boolean checkWord(String word) {
		return words.containsKey(word);
	}

	/**
	 * Removes a word from the dictionary.
	 * @param word - the word to be removed
	 */
	public synchronized void removeWord(String word) {
		words.remove(word);
	}
	
	/**
	 * Removes all the definitions of a given word.
	 * @param word - the word whose definitions are to be removed
	 */
	public synchronized void clearDef(String word) {
		words.get(word).clear();
	}
	
	/**
	 * Saves the contents of the dictionary to a file.
	 * Each line in the file represents a single word and its corresponding definitions.
	 * The format of each line is "word,definition1,definition2,definition3,..."
	 * @param filename - the file path to which the dictionary data is to be saved
	 */
	public void save(String filename) {
        try {
        	// Create a FileWriter object to write to the file.
            FileWriter writer = new FileWriter(filename);
            // Loop through each entry in the HashMap.
            for (Map.Entry<String, ArrayList<String>> entry : words.entrySet()) {
            	// Get the word.
                String word = entry.getKey();
                // Get the definitions.
                ArrayList<String> defs = entry.getValue();
                // Create a comma-separated string of definitions.
                String defString = "";
                // Create a line in the file with the format "word,definition1,definition2,definition3,..."
                for (String def: defs) {
                	defString = defString + ", " + def;
                }
                String line = word + String.join(",", defString);
                // Write the line to the file.
                writer.write(line + "\n");
            }
            // Close the FileWriter object.
            writer.close();
        	
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data to file: " + e.getMessage());
        }
    }
    
	/**
	 * This method loads data from a file and populates the dictionary object
	 * @param filename the name of the file to load the data from
	 */
    public void load(String filename) {
    	try {
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            // Read each line of the file and split into word and definitions
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                String word = parts[0];
                String[] definitions = parts[1].split(",");
                ArrayList<String> defArrayList = new ArrayList<>();
                // Add each definition to the ArrayList of definitions
                for (String def : definitions) {
                    defArrayList.add(def);
                }
                // Add the word and its definitions to the dictionary
                words.put(word, defArrayList);
            }
            bufferedReader.close();
            reader.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("Dictionary file not found! New Dictionary file \"" + dictFile + "\" would be created after clients adding a new word.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading the data from file: " + e.getMessage());
        }
    }
}