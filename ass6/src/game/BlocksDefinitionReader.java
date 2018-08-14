package game;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import blocks.BlockCreator;
import blocks.BlockMaker;
import blocks.BlocksFromSymbolsFactory;

/**
 * BlocksDefinitionReader class.
 */
public class BlocksDefinitionReader {

    /**
     * this method is responsible for the reader reading and the creation of the
     * BlocksFromSymbolsFactory.
     * @param reader - as a BlocksFromSymbolsFactory
     * @return Blc
     */
    public static BlocksFromSymbolsFactory fromReader(Reader reader) {
        Map<String, String> spacers = new TreeMap<>();
        Map<String, BlockCreator> blockCreateors = new TreeMap<>();
        Map<String, String> defaultBlockInformation = new TreeMap<>();

        BufferedReader buffer = new BufferedReader(reader);
        String lineBuffer;
        String end = null;
        // read all of the lines
        try {
            lineBuffer = buffer.readLine();
            while (lineBuffer != null) {
                if (!lineBuffer.contains("#")) {
                    end += "\\\\" + lineBuffer;
                }
                lineBuffer = buffer.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // getting the reader and split by "\\\\"
        String[] lineSplited = end.split("\\\\");
        // going over all of the lines in the splitted lines
        for (String line : lineSplited) {
            // treating the default arguments
            if (line.contains("default")) {
                defaultBlockInformation = creatAMap(line);
                break;
            }
        }
        // going over the lines
        for (String defLine : lineSplited) {
            // if the key starts with bdef
            if (defLine.contains("bdef")) {
                BlockCreator blockCreator = lineToBlockCreator(defLine, defaultBlockInformation);
                String symbol = String.valueOf(defLine.charAt(defLine.indexOf(':') + 1));
                blockCreateors.put(symbol, blockCreator);
                // handeling spacers
            } else if (defLine.contains("sdef")) {
                Map<String, String> stringSpacers = creatAMap(defLine);
                spacers.put(stringSpacers.get("symbol"), stringSpacers.get("width"));
            }
        }
        return new BlocksFromSymbolsFactory(spacers, blockCreateors);
    }

    /**
     * this method gets a string and makes a map from it.
     * @param toSplit - the string needed to be splitted
     * @return map - of the block values
     */
    public static Map<String, String> creatAMap(String toSplit) {
        Map<String, String> mapS = new TreeMap<>();
        // split by space
        String[] splitedLine = toSplit.split(" ");
        // go over all of the splited args
        for (String section : splitedLine) {
            if (section.contains(":")) {
                String[] secondSplit = section.split(":");
                // put key and value
                mapS.put(secondSplit[0], secondSplit[1]);
            }
        }
        return mapS;
    }

    /**
     * this method create BlockCreator by finding all of the nessesary feilds.
     * @param line - as a line that represent a kind of block
     * @param defaultMap - the map of values
     * @return Block creator
     */
    private static BlockCreator lineToBlockCreator(String line, Map<String, String> defaultMap) {
        Map<String, String> mergedMap = mergeMap(defaultMap, line);
        TreeMap<Integer, Color> colors = new TreeMap<>();
        TreeMap<Integer, Image> images = new TreeMap<>();
        ColorsParser cP = new ColorsParser();
        int hitPoints = Integer.valueOf(mergedMap.get("hit_points"));
        int height = Integer.valueOf(mergedMap.get("height"));
        int width = Integer.valueOf(mergedMap.get("width"));
        Color stroke = null;
        // if the key is "stroke" put the color on stroke
        if (mergedMap.containsKey("stroke")) {
            stroke = cP.colorFromString(mergedMap.get("stroke"));
        }
        // handle a "forAll" default for fill
        if (mergedMap.containsKey("fill")) {
            mergedMap.put("fill-0", mergedMap.get("fill"));
            mergedMap.remove("fill");
        }
        // going over all of the possible fill- options
        for (int i = 0; i <= Integer.valueOf(mergedMap.get("hit_points")); i++) {
            if (mergedMap.containsKey("fill-" + i)) {
                String fillString = mergedMap.get("fill-" + i);
                // handel with fill of image
                if (fillString.startsWith("image(")) {
                    fillString = fillString.substring(6);
                    fillString = fillString.replace(")", "");
                    InputStream imageIS = ClassLoader.getSystemClassLoader().getResourceAsStream(fillString);
                    Image image1 = null;
                    try {
                        image1 = ImageIO.read(imageIS);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // put the image in the map
                    if (image1 != null) {
                        images.put(i, image1);
                    }
                    // close the stream
                    try {
                        imageIS.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // handel fill of color
                } else if (fillString.startsWith("color(")) {
                    Color color = cP.colorFromString(fillString);
                    colors.put(i, color);
                } else {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new BlockMaker(width, height, hitPoints, colors, images, stroke);
    }

    /**
     * this method merge between the default map and the bdef map.
     * @param dafaultInf - as the default map
     * @param line - as the line
     * @return a Map for the block
     */
    private static Map<String, String> mergeMap(Map<String, String> dafaultInf, String line) {
        Map<String, String> finalMap = new TreeMap<>();
        Map<String, String> splittedMap = creatAMap(line);
        finalMap.putAll(splittedMap);
        // going over all of the sets
        for (Map.Entry<String, String> entry : dafaultInf.entrySet()) {
            // if there is a need in the args from the default
            if (!splittedMap.containsKey(entry.getKey())) {
                finalMap.put(entry.getKey(), entry.getValue());
            }
        }
        return finalMap;
    }

}