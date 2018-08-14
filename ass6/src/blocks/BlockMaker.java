package blocks;

import java.awt.Color;
import java.awt.Image;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import geometry.Point;
import geometry.Rectangle;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class BlockMaker implements BlockCreator {

    private int width;
    private int height;
    private int hitPoints;
    private java.awt.Color stroke;
    private TreeMap<Integer, Image> imageFillMap = new TreeMap<>();
    private TreeMap<Integer, Color> colorsFillMap = new TreeMap<>();

    /**
     * this is the constructor of the blockMaker.
     * @param width - as the block width
     * @param height - as the block height
     * @param hitPoints - as the number of hits
     * @param colorsFillMap - as a map of colors
     * @param imageFillMap - as a map of images
     * @param stroke - as the color of the stroke
     */
    public BlockMaker(int width, int height, int hitPoints, TreeMap<Integer, Color> colorsFillMap,
            TreeMap<Integer, Image> imageFillMap,
            java.awt.Color stroke) {
        this.hitPoints = hitPoints;
        this.width = width;
        this.height = height;
        this.colorsFillMap = colorsFillMap;
        this.imageFillMap = imageFillMap;
        this.stroke = stroke;

    }

    @Override
    public Block create(int xpos, int ypos) {
        if (width == 0 || height == 0 || hitPoints == 0
                || (this.colorsFillMap.isEmpty() && this.imageFillMap.isEmpty())) {
            messageToUser();
        }
        // creating a rectangle based on coordinates
        Rectangle rec = new Rectangle(new Point(xpos, ypos), width, height);
        // returning a new block
        return new Block(rec, imageFillMap, colorsFillMap, hitPoints, this.stroke);
    }

    /**
     * this message opens an eror message to the user and close the project.
     */
    public void messageToUser() {
        // show a message dialog - the message is an error message.
        JOptionPane.showMessageDialog(new JFrame(),
                "You are missing some parameters in the block definitions, Please check it", "Error!",
                JOptionPane.ERROR_MESSAGE);
        // exit the program
        System.exit(0);
    }

}