package remorse.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import remorse.domain.HighScore;

/**
 * Luokka käsittelee tietokantaa.
 */
public class DatabaseHandler {
    
    private final String dbAddress;

    public DatabaseHandler(String dbAddress) {
        this.dbAddress = dbAddress;
    }
        
    /**
     * Metodi tyhjentää kirjainpelin pistetaulukon tietokannasta.
     * @return Palauttaa false jos tuli poikkeus, muuten true.
     */
    public boolean clearLetterScores() {
        try(Connection connection = DriverManager.getConnection(dbAddress)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM LetterScores");
            statement.close();
            connection.close();
            return true;                      
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Metodi tyhjentää kirjainpelin pistetaulukon tietokannasta.
     * @return Palauttaa false jos tuli poikkeus, muuten true.
     */
    public boolean clearWordScores() {
        try(Connection connection = DriverManager.getConnection(dbAddress)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM WordScores");
            statement.close();
            connection.close();
            return true;                      
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Metodi hakee tietokannasta satunnaisen sanan. 
     * Haun pitäisi olla tehokas suurissakin tietokannoissa.
     * @return Palauttaa haetun sanan tai "virhe", jos tuli ongelma.
     */
    public String nextWord() {
        try(Connection connection = DriverManager.getConnection(dbAddress)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT word_string "
                    + "FROM Words LIMIT 1 OFFSET ABS(RANDOM()) "
                    + "% MAX((SELECT COUNT(*) FROM Words), 1)");
            if (!resultSet.next()) {
                return "virhe";
            }
            String next = resultSet.getString("word_string");
            resultSet.close();
            statement.close();
            connection.close();
            return next;                      
        } catch (Exception e) {
            return "virhe";
        }
    }
    
    /**
     * Metodi hakee listan kirjainpelin huipputuloksista.
     * @return Palauttaa listan, jossa on viisi parasta tulosta aikoineen.
     */
    public ArrayList<HighScore> letterHighScores() {
        ArrayList<HighScore> scoreList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbAddress)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT points, time "
                    + "FROM LetterScores ORDER BY points DESC LIMIT 5");            
            while(resultSet.next()) {
                String points = String.valueOf(resultSet.getInt("points"));
                String time = resultSet.getString("time");
                HighScore score = new HighScore(points, time);
                scoreList.add(score);
            }            
            resultSet.close();
            statement.close();
            connection.close();
            return scoreList;
        } catch (Exception e) {
            return scoreList;
        }
    }
    
    /**
     * Metodi hakee listan sanapelin huipputuloksista.
     * @return Palauttaa listan, jossa on viisi parasta tulosta aikoineen.
     */
    public ArrayList<HighScore> wordHighScores() {
        ArrayList<HighScore> scoreList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbAddress)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT points, time "
                    + "FROM WordScores ORDER BY points DESC LIMIT 5");
            while (resultSet.next()) {
                String points = String.valueOf(resultSet.getInt("points"));
                String time = resultSet.getString("time");
                HighScore score = new HighScore(points, time);
                scoreList.add(score);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return scoreList;
        } catch (Exception e) {
            return scoreList;
        }
    }
    
    /**
     * Metodi tallettaa pistemäärän tietokantaan.
     * @param points Käyttäjän saama pistemäärä
     * @param type Tallettavan pelin tyyppi
     * @return Palauttaa false, jos tuli poikkeus, muuten true.
     */
    public boolean saveScore(int points, String type) {
        try (Connection connection = DriverManager.getConnection(dbAddress)) {
            if (type.equals("letter")) {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO LetterScores (points) VALUES (?)");
                ps.setInt(1, points);
                ps.execute();
                ps.close();
            } else if (type.equals("word")) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                        + "WordScores (points) VALUES (?)");
                ps.setInt(1, points);
                ps.execute();
                ps.close();
            }
            connection.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
