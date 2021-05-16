/*William Lilja och Elisabeth Nyström*/
/*Aj0367 och Ai8798*/
/*Medieproduktion och processdesign*/
package Model;

import Controll.Controller;

public class GameManager {

    private Controller controller;
    private GameField gameField;

    //Konstruktor som skapar en spelplan beroende på användarens val
    public GameManager(int option, Controller controller){
        this.controller = controller;
        gameField = new GameField(option,this);
    }

    //Frågar controller om användarens namn
    //Returnerar en sträng till gamefield som frågar efter det
    public String askName(){
        return controller.askName();
    }

    //Vidarebefodrar gamefields feedback till användaren
    public String checkPosition(int posY, int posX){
        return gameField.checkPosition(posY, posX);
    }

    //Vidarebefodrar arrayen med highscorelistans strängar
    public void showHighscore(String[] highscoreList) {
        controller.showHighscore(highscoreList);
    }

    //Startar om gamefield
    public void reset(){
        gameField.reset();
    }

}

