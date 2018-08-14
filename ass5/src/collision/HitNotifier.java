package collision;

/**
 * @author Lee alima <leealima28@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public interface HitNotifier {

    /**
     * this method adds an HitListener object as a listener to hit event.
     * @param hl - as the HitListener object
     */
    void addHitListener(HitListener hl);

    /**
     * this method removes an HitListener object from the list of listeners to hit events.
     * @param hl - as the HitListener object
     */
    void removeHitListener(HitListener hl);
}