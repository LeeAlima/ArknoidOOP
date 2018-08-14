package blocks;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import game.ColorsParser;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class BlocksDefinitionReader {

    /**
     * this method return a BlocksFromSymbolsFactory object - by looking for all
     * of the data needed to create it.
     * @param reader - as java.io.Reader
     * @return BlocksFromSymbolsFactory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        int width = 0;
        int height = 0;
        java.awt.Color stroke = null;
        int hitPoints = 0;
        TreeMap<String, BlockCreator> blocksMap = new TreeMap<>();
        TreeMap<String, String> spacersMap = new TreeMap<>();
        BufferedReader buffer = new BufferedReader(reader);
        // creating a BufferedReader object
        String information;
        List<String> dataForBlocks = new ArrayList<>();
        List<String> dataForSpacers = new ArrayList<>();
        try {
            while ((information = buffer.readLine()) != null) {
                // as long the line is not null
                information = information.trim();
                if (!(information.isEmpty())) {
                    if (!(information.contains("#"))) { // if the line doesn't
                                                        // contain "#"
                        // if the line starts with "bdef symbol" or "default" -
                        // add it to dataForBlocks
                        if (information.startsWith("bdef symbol") || information.startsWith("default")) {
                            dataForBlocks.add(information);
                        } // if the line starts with "sdef symbol"
                        if (information.startsWith("sdef symbol")) {
                            dataForSpacers.add(information);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { // try to close the buffer
                buffer.close();
            } catch (IOException e) {
                System.err.println("Couldn't close reader");
                e.printStackTrace();
            }
        }
        List<String> duplicationOfBlocks = dataForBlocks;
        List<String> duplicationOfSpacers = dataForSpacers;
        for (String data : duplicationOfSpacers) { // going over a copy of
                                                   // dataForSpacers
            List<String> symbols = addSpacerToMap(data);
            spacersMap.put(symbols.get(0), symbols.get(1));
        }
        for (String data : duplicationOfBlocks) { // going over a copy of
                                                  // dataForBlocks
            ColorsParser cP = new ColorsParser();
            TreeMap<Integer, Image> imageFillMap = new TreeMap<>();
            TreeMap<Integer, Color> colorsFillMap = new TreeMap<>();
            String[] allInformation = data.split(" "); // split by space
            int size = allInformation.length;
            // go over all the information of allInformation
            for (int i = 0; i < size; i++) {
                if (allInformation[i].contains(":")) {
                    String[] oneItem = allInformation[i].split(":");
                    if (oneItem[0].contains("width")) { // check for
                                                        // width
                        width = Integer.parseInt(oneItem[1]);
                    } // check for height
                    if (oneItem[0].equals("height")) {
                        height = Integer.parseInt(oneItem[1]);
                    } // check for hit_points
                    if (oneItem[0].equals("hit_points")) {
                        hitPoints = Integer.parseInt(oneItem[1]);
                    } // check for stroke
                    if (oneItem[0].equals("stroke")) {
                        stroke = cP.colorFromString(oneItem[1]);
                    } // check for fill
                    if (oneItem[0].startsWith("fill")) {
                        if (oneItem[0].equals("fill")) { // hangeling
                                                         // color
                            if (oneItem[1].startsWith("color")) {
                                colorsFillMap.put(0, cP.colorFromString(oneItem[1]));
                            } // hangeling image
                            if (oneItem[1].startsWith("image")) {
                                oneItem[1] = oneItem[1].replaceAll("[()]", "");
                                oneItem[1] = oneItem[1].substring(5);
                                InputStream imageIS = ClassLoader.getSystemClassLoader()
                                        .getResourceAsStream(oneItem[1]);
                                Image image = null;
                                try { // trying reading the image
                                    image = ImageIO.read(imageIS);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } // put the image in the map of images
                                if (image != null) {
                                    imageFillMap.put(0, image);
                                }
                                try {
                                    imageIS.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } // hangeling un - defaulf fills
                        if (oneItem[0].startsWith("fill-")) {
                            if (oneItem[1].startsWith("color")) { // for
                                                                  // colors
                                colorsFillMap.put(Integer.parseInt(oneItem[0].substring(5)),
                                        cP.colorFromString(oneItem[1]));
                            }
                            if (oneItem[1].startsWith("image")) { // for
                                                                  // images
                                oneItem[1] = oneItem[1].replaceAll("[()]", "");
                                oneItem[1] = oneItem[1].substring(5);
                                InputStream imageIS = ClassLoader.getSystemClassLoader()
                                        .getResourceAsStream(oneItem[1]);
                                Image image = null;
                                try { // trying reading the image
                                    image = ImageIO.read(imageIS);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (image != null) {
                                    imageFillMap.put(Integer.parseInt(oneItem[0].substring(5)), image);
                                }
                                try {
                                    imageIS.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } // create a blockCreator object
            BlockCreator b = new BlockMaker(width, height, hitPoints, colorsFillMap, imageFillMap, stroke);
            // find the block symbol
            String[] key = data.split(" ");
            String[] find = key[1].split(":");
            // add the pair (symbol and blockCreator) to the map
            blocksMap.put(find[1], b);
        } // return the map
        return new BlocksFromSymbolsFactory(spacersMap, blocksMap);
    }

    /**
     * this method add spacer and it's value to a list in order to add it to a
     * map.
     * @param data - as a string
     * @return list of string (of spacers and their values)
     */
    public static List<String> addSpacerToMap(String data) {
        // split the data by spaces
        String[] arrays = data.split(" ");
        // split the data by ":"
        String[] findSymbol = arrays[1].split(":");
        // create a key
        String key = findSymbol[1];
        // split arrays[2] by ":"
        String[] findWidth = arrays[2].split(":");
        // find the value
        String value = findWidth[1];
        List<String> keyAndSymbol = new ArrayList<>();
        // add to keyAndSymbol the keyAndSymbol
        keyAndSymbol.add(key);
        keyAndSymbol.add(value);
        // return the list
        return keyAndSymbol;
    }

    /**
     * this method returns a list of symbols.
     * @param blocks - as a list of string
     * @return list of symbols (string)
     */
    public static List<String> blockSymbolsList(List<String> blocks) {
        // creating a list of symbols
        List<String> symbols = new ArrayList<>();
        String[] splitWithSpace;
        String[] splitWithBracket;
        // go over the size of blocks
        for (int i = 0; i < blocks.size(); i++) {
            // if the string doesn't start with "default"
            if (!(blocks.get(i).startsWith("default"))) {
                // split it by spaces
                splitWithSpace = blocks.get(i).split(" ");
                // split it by ":"
                splitWithBracket = splitWithSpace[1].split(":");
                // add the symbol to the list of symbols
                symbols.add(splitWithBracket[1]);
            }
        }
        return symbols;
    }
}
