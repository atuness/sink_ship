/*William Lilja och Elisabeth Nyström*/
/*Aj0367 och Ai8798*/
/*Medieproduktion och processdesign*/
package Model;

public class HighscorePerson {
    private String name;
    private int highscoreHits;

    //Konstruktor som lagrar namn och antal poäng användaren fick
    public HighscorePerson(String name, int highscoreHits){
        this.name = name;
        this.highscoreHits = highscoreHits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighscoreHits() {
        return highscoreHits;
    }

    public void setHighscoreHits(int highscoreHits) {
        this.highscoreHits = highscoreHits;
    }

    public String toString(){
        return String.format("%s %d",name, highscoreHits);
    }
}
