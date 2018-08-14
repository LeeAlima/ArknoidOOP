package levels;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class ArrangeLevelsSets {

    /**
     * this function creates a new reader based on the one it gets and creates a
     * list of levels based on it.
     * @param reader - as a Reader object
     * @return a list of LevelInformation
     */
    public List<LevelInformation> fromReader(Reader reader) {
        // creating a LineNumberReader to run the reader by lines
        LineNumberReader numberReader = new LineNumberReader(reader);
        // creating an empty list of Level Information
        List<LevelInformation> levelsList = new ArrayList<>();
        // trying to create a list of levels
        try {
            // creating a LevelSpecificationReader object
            LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
            // try to read the information and put it in the list of levels
            levelsList = levelSpecificationReader.fromReader((InputStreamReader) reader);
        } finally {
            // try to close the reader
            try {
                numberReader.close();
            } catch (IOException e) {
                // print an error message to the user
                System.err.println("Couldn't close LineNumberReader");
                e.printStackTrace();
            }
        }
        // return the list
        return levelsList;
    }
}