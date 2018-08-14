package blocks;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public interface BlockCreator {

    /**
     * this method create a block at the specified location.
     * @param xpos - as the x coordinate
     * @param ypos - as the y coordinate
     * @return a new block
     */
    Block create(int xpos, int ypos);
}
