
/**
 * @author Gabriel Smith
 * Created Dec 14, 2010
 *
 * Stores vertex coordinates and a label 'num' corresponding
 * to the vertex number. Contains accessors for all fields.
 */
public class Vertex {

    private int num, x, y;

    /**
     * Constructor
     *
     * @param num   numeric label for current vertex
     * @param x     x-coordinate
     * @param y     y-coordinate
     */
    public Vertex( int num, int x, int y ) {
        this.num = num;
        this.x = x;
        this.y = y;
    }

    /**
     * Accessors
     */
    public int getNum() { return num; }
    public int getX() { return x; }
    public int getY() { return y; }

}
