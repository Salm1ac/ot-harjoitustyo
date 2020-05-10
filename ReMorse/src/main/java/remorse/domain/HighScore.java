package remorse.domain;

/**
 * Luokka kuvaa huipputuloksia. Tulokseen sisältyy pistemäärä ja saavutusaika.
 */
public class HighScore {
    
    private final String points;
    private final String time;

    public HighScore(String points, String time) {
        this.points = points;
        this.time = time;
    }

    public String getPoints() {
        return points;
    }

    public String getTime() {
        return time;
    }
           
}
