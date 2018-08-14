package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class LevelSpecificationReader {
    private ArrayList<ArrayList<String>> listOfLevels = new ArrayList<ArrayList<String>>();
    private List<LevelInformation> levels;

    /**
     * this is the constructor for LevelSpecificationReader.
     */
    public LevelSpecificationReader() {
        listOfLevels = new ArrayList<>();
        levels = new ArrayList<>();
    }

    /**
     * this method creates levels with the reader, adds them to a list and
     * returns the list.
     * @param reader - as a java.io.Reader object
     * @return a list of LevelInformation
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        // calling addAListToLevelList with the reader
        addAListToLevelList(reader);
        for (int i = 0; i < listOfLevels.size(); i++) {
            // building a level and add the level to the list of levels.
            this.levels.add(new BuildALevel(listOfLevels.get(i)).seperateLeveData());
        }
        return this.levels;
    }

    /**
     * this method add a list to the level list.
     * @param reader - as a java.io.Reader object
     */
    public void addAListToLevelList(java.io.Reader reader) {
        String data;
        ArrayList<String> oneLevel = new ArrayList<String>();
        // creating a buffer
        BufferedReader buffer = new BufferedReader(reader);
        try {
            // as long as there is a line to read
            while ((data = buffer.readLine()) != null) {
                // if it's an empty line - continue
                if (data.equals("")) {
                    continue;
                }
                // if the line doesn't start with START_LEVEL or END_LEVEL
                if (!(data.matches("END_LEVEL")) && !data.equals("START_LEVEL")) {
                    // if the line is not a comment and it's nor empty
                    if (!data.startsWith("#") && !data.isEmpty()) {
                        // add the data
                        oneLevel.add(data);
                    }
                } else if (data.matches("END_LEVEL")) {
                    // add a level to the listOfLevels
                    this.listOfLevels.add(new ArrayList<String>(oneLevel));
                    oneLevel.clear();
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read file");
            e.printStackTrace();
        } finally {
            try {
                // try to close the buffer
                buffer.close();
            } catch (IOException e) {
                System.err.println("Could not close reader");
                e.printStackTrace();
            }
        }
    }
}