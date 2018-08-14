package game;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import blocks.Block;
import blocks.Enemy;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprite.BackroundB;
import sprite.Sprite;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class SpaceInvadersLevel implements LevelInformation {
    private BackroundB backround;
    private List<Enemy> enemies;
    private List<Block> shields;
    private Image image;

    /**
     * this is the constructor of spaceInvadersLevel. it only initializes the
     * memebers.
     */
    public SpaceInvadersLevel() {
        this.image = getImage();
        backround = new BackroundB(image);
        this.enemies = new ArrayList<>();
        this.shields = new ArrayList<>();
    }

    /**
     * this method load the image of the backround and returns it.
     * @return Image - as an image
     * @throws IOException
     */
    public Image getImage() {
        InputStream imageIS = ClassLoader.getSystemClassLoader().getResourceAsStream("backround.png");
        Image image1 = null;
        if (imageIS == null) {
            return image1;
        }
        // try to load the image
        if (imageIS != null) {
            try {
                image1 = ImageIO.read(imageIS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            imageIS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image1;
    }

    @Override
    public int paddleSpeed() {
        return 600;
    }

    @Override
    public int paddleWidth() {
        return 50;
    }

    @Override
    public Sprite getBackground() {
        return this.backround;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 50;
    }

    @Override
    public String levelName() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        // creating the sheild.
        int counter = 0;
        for (int i = 460; i <= 475; i += 5) {
            for (int j = 135; j <= 665; j += 5) {
                Block block = new Block((new Rectangle((new Point(j, i)), 5, 5)), Color.BLACK, 1);
                shields.add(block);
                counter++;
                if (counter == 25) {
                    j += 75;
                    counter = 0;
                }
            }
        }
        return this.shields;
    }

    @Override
    public List<Enemy> enemies() {
        List<Enemy> before = new ArrayList<>();
        int xPos = 0;
        int yPos = 50;
        // load the image once
        InputStream imageIS = ClassLoader.getSystemClassLoader().getResourceAsStream("enemy.png");
        if (imageIS == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No image for enemy was found!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        Image image1 = null;
        // try to load the image
        try {
            image1 = ImageIO.read(imageIS);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            imageIS.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Enemy enemy1;
        if (image1 != null) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    // create an enemy.
                    enemy1 = new Enemy(xPos, yPos, image1);
                    before.add(enemy1);
                    xPos += 50;
                }
                yPos += 40;
                xPos = 0;
            } // using the copyEnemy in order to run over a copy and not
              // changing the real objects.
            for (Enemy e : before) {
                this.enemies.add(e.copyEnemy());
            }
            return this.enemies;
        }
        return null;
    }

}
