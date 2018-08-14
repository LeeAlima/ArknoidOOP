package game;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
class HighScoresTable implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private List<ScoreInfo> listOfscores;
    private int size;

    /**
     * this is the constructor for HighScoresTable.
     * @param size - as the size fore the list of scores
     */
    public HighScoresTable(int size) {
        this.listOfscores = new ArrayList<ScoreInfo>(size);
        this.size = size;
    }

    /**
     * this method adds a high score to the list.
     * @param score - as a score.
     */
    public void add(ScoreInfo score) {
        int indexPlace;
        // if the score should enter the list
        if (getRank(score.getScore()) <= this.size) {
            indexPlace = (getRank(score.getScore()) - 1);
            listOfscores.add(indexPlace, score);
        }
        // otherwise
        if (listOfscores.size() > this.size) {
            listOfscores.remove(listOfscores.size() - 1);
        }
    }

    /**
     * this method returns the size of the list of scores.
     * @return the size of the list
     */
    public int size() {
        int sizeParameter = 0;
        for (ScoreInfo score : listOfscores) {
            if (score != null) {
                sizeParameter++;
            }
        }
        return sizeParameter;
    }

    /**
     * this method returns the current high scores.
     * @return a list of ScoreInfo
     */
    public List<ScoreInfo> getHighScores() {
        return returnSortedList(this.listOfscores);
    }

    /**
     * this method returns a sorted list of ScoreInfo.
     * @param list - a list of ScoreInfo
     * @return a sorted list
     */
    public List<ScoreInfo> returnSortedList(List<ScoreInfo> list) {
        List<ScoreInfo> scoresList = new ArrayList<ScoreInfo>(list);
        int n = scoresList.size();
        ScoreInfo temp = null;
        // bubble sort
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (scoresList.get(j - 1).getScore() < scoresList.get(j).getScore()) {
                    // swap the elements
                    temp = scoresList.get(j - 1);
                    scoresList.set(j - 1, scoresList.get(j));
                    scoresList.set(j, temp);
                }
            }
        }
        return scoresList;
    }

    /**
     * this method returns the rank of the score.
     * @param score - as an int
     * @return the place to put it in the list
     */
    public int getRank(int score) {
        int number = 1;
        for (ScoreInfo scores : this.listOfscores) {
            if (score <= scores.getScore()) {
                number++;
            }
        }
        return number;
    }

    /**
     * this method clears the listOfScores.
     */
    public void clear() {
        this.listOfscores.clear();
    }

    /**
     * this methood load table data from file. the current table data is
     * cleared.
     * @param filename - as the File name
     * @throws IOException - an exception
     * @throws ClassNotFoundException - an exception
     */
    public void load(File filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = null;
        // trying to reas an ObjectInputStream.
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            objectInputStream.readObject();
            // Can't find file to open
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename);
            return;
            // Some other problem
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    // close it
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * this method saves table data to the specified file.
     * @param filename - as the File name
     * @throws IOException - as an exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        // trying to write to ObjectOutputStream
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } finally {
            try {
                if (objectOutputStream != null) {
                    // close it
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * this method reads a table from file and returns it. if the file doesn't
     * exist, or there is a problem with reading it , an empty table is
     * returned.
     * @param filename - as the File name
     * @return HighScoresTable
     * @throws IOException - as an exception.
     */
    public static HighScoresTable loadFromFile(File filename) throws IOException {
            ObjectInputStream objectIS = null;
            // tring to read the object
            try {
                objectIS = new ObjectInputStream(new FileInputStream(filename));
                return (HighScoresTable) objectIS.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("something went wrong", e);
            } catch (FileNotFoundException e) {
                return new HighScoresTable(5);
            } finally {
                if (objectIS != null) {
                    // closing it
                    objectIS.close();
                }
            }
    }
}