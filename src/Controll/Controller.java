/*William Lilja och Elisabeth Nyström*/
/*Aj0367 och Ai8798*/
/*Medieproduktion och processdesign*/
package Controll;


import Model.GameManager;
import Model.HighscorePerson;
import Model.ShipType;
import View.EastPanel;
import View.MainFrame;

public class Controller {
    MainFrame view;
    GameManager model;

    //Konstruktor
    public Controller(){
       view = new MainFrame(this);
    }

    //Skapar spelfältet beroende på vilket val anvädaren gör (option) och om det är första gången eller ej (boolean)
    public void createGameField(int option, boolean firstTime){
        if(firstTime){
            model = new GameManager(option, this);
        }else if(firstTime==false){
            System.out.println(" ");
        }
    }

    //Kollar om en båt ligger på platsen, x och y. Returnerar en sträng med feedback
    public void checkPosition(int posY, int posX){
        String test = model.checkPosition(posY,posX);
            view.AddElement(test);
    }

    //Frågor view om namn och returnerar en sträng
    public String askName(){
        return view.askName();
    }

    //Ber view visa highscore med hjälp av en array av strängar
    public void showHighscore(String[] highscore) {
        view.showHighscore(highscore);
    }
}
