package blocks;

import java.util.Map;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class BlocksFromSymbolsFactory {
    private Map<String, String> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * this is the consructor of BlocksFromSymbolsFactory.
     * @param spacerWidths - as a map of spacers
     * @param blockCreators - as a map of BlockCreator
     */
    public BlocksFromSymbolsFactory(Map<String, String> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * this method return true if 's' is a valid space symbol and false
     * otherwise.
     * @param s - as a string
     * @return true or false
     */
    public boolean isSpaceSymbol(String s) {
        // checking if 's' is a key in the spacers map
        if (this.spacerWidths.containsKey(s)) {
            return true;
        }
        return false;
    }

    /**
     * this method return true if 's' is a valid block symbol and false
     * otherwise.
     * @param s - as a string
     * @return true or false
     */
    public boolean isBlockSymbol(String s) {
        // checking if 's' is a key in the blockCreator map
        if (this.blockCreators.containsKey(s)) {
            return true;
        }
        return false;
    }

    /**
     * this method return a block according to definitions. this block will be
     * located at posiotion(xpos,ypos).
     * @param s - as a string
     * @param xpos - as the x position
     * @param ypos - as the y posiotion
     * @return a block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * this method returns the space width in pixels.
     * @param s - as the spacer symbol
     * @return the spacer width
     */
    public int getSpaceWidth(String s) {
        return Integer.parseInt(this.spacerWidths.get(s));
    }

    /**
     * this method adds a spacer to the spacers map.
     * @param data - as the symbol
     * @param spacerWidth - as its width
     */
    public void addSpacer(String data, String spacerWidth) {
        this.spacerWidths.put(data, spacerWidth);
    }

    /**
     * this method adds a blockCreator object to the list of blockCreator.
     * @param s1 - as the block symbol
     * @param blockC - as the block creator
     */
    public void addBlock(String s1, BlockCreator blockC) {
        this.blockCreators.put(s1, blockC);
    }
}