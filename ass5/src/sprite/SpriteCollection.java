package sprite;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class SpriteCollection {

    private List<Sprite> listSprite;

    /**
     * constructor for SpriteCollection.
     */
    public SpriteCollection() {
        this.listSprite = new ArrayList<Sprite>();
    }

    /**
     * @return a list of sprite
     */
    public List<Sprite> getListSprite() {
        return listSprite;
    }

    /**
     * this method sets listSprite.
     * @param aListOfSprite -
     *            as a listOfSprite
     */
    public void setListSprite(List<Sprite> aListOfSprite) {
        this.listSprite = aListOfSprite;
    }

    /**
     * this method add a sprite to the listOfSprite.
     * @param s
     *            - as a sprite
     */
    public void addSprite(Sprite s) {
        listSprite.add(s);
    }

    /**
     * this method remove a sprite from a list of sprites.
     * @param s - as a sprite
     */
    public void removeSprite(Sprite s) {
        listSprite.remove(s);
    }

    /**
     * this method calls timePassed to all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> listOfSprite = new ArrayList<Sprite>(this.listSprite);
        // Notify all listeners about a hit event:
        for (Sprite s : listOfSprite) {
            s.timePassed();
        }
    }

    /**
     * this method calls drawOn on all sprites.
     * @param d
     *            - as DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : listSprite) {
            sprite.drawOn(d);
        }
    }
}