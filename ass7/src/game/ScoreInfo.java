package game;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class ScoreInfo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

    /**
     * this is the constructor of ScoreInfo.
     * @param name - as a name
     * @param score - as a score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * this method returns the user name.
     * @return name (string)
     */
    public String getName() {
        return this.name;
    }

    /**
     * this method returns the user score.
     * @return score (int)
     */
    public int getScore() {
        return this.score;
    }
}