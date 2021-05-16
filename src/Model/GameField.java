/*William Lilja och Elisabeth Nyström*/
/*Aj0367 och Ai8798*/
/*Medieproduktion och processdesign*/
package Model;

public class GameField {

    private ShipType[][] gameLayout;
    private Uboat uboat;
    private Torpedoboat torpedoboat;
    private Hunter hunter;
    private Kisser kisser;
    private FightingShip fightingShip;
    private HighscorePerson[] highscoreList = new HighscorePerson[10];
    private String[] highscoreStrings = new String[10];
    private int totalHit = 0; //Antal träffar
    private int highscoreHits = 0; //Antal skott
    private GameManager gameManager;
    private int numbrOfHighscores = 0;

    //Konstruktor som tar in användarens val (int) av spelplan och gamemanagern som hanterar spelet
    public GameField(int option, GameManager gameManager){
        this.gameManager = gameManager;
        createGameField(option);
    }

    public ShipType[][] getGameLayout() {
        return gameLayout;
    }

    //Metod som skapar gameField beroende på knappvalet, option: 1 eller 2.
    //Här skapas även båtarna
    //Varje ruta på spelplanen innehåller en shipType, inte ett objekt av ship.
    public void createGameField(int option){
        gameLayout = new ShipType[10][10];
        uboat = new Uboat();
        torpedoboat = new Torpedoboat();
        hunter = new Hunter();
        kisser = new Kisser();
        fightingShip = new FightingShip();

        switch(option){
            case 1:
                //Lagrar infromation om vilken shipType som finns i spelplanens ruta.
                //Loopen lagrar informationen på lika många platser som skeppet storlek är.
                for(int i=0; i<fightingShip.getSize(); i++) {
                    gameLayout[i][9] = ShipType.fightingShip;
                }
                //kisser.getSize() + 2 för att annars slutar skeppet läggas in på kollumn 6
                //eftersom den redan börjar läggas in två kollumner in
                for(int i=2; i<kisser.getSize()+2; i++) {
                    gameLayout[8][i] = ShipType.kisser;
                }
                for(int i=2; i<hunter.getSize()+2; i++) {
                    gameLayout[6][i] = ShipType.hunter;
                }
                for(int i=0; i<torpedoboat.getSize(); i++) {
                    gameLayout[i][6] = ShipType.torpedoboat;
                }
                gameLayout[3][2] = ShipType.uboat;
                break;
            case 2:
                //Lagrar infromation om vilken shipType som finns i spelplanens ruta.
                //Loopen lagrar informationen på lika många platser som skeppet storlek är.
                for(int i=0; i<fightingShip.getSize(); i++) {
                    gameLayout[i][1] = ShipType.fightingShip;
                }
                //kisser.getSize() + 2 för att annars slutar skeppet läggas in på kollumn 6
                //eftersom den redan börjar läggas in två kollumner in
                for(int i=2; i<kisser.getSize()+2; i++) {
                    gameLayout[5][i] = ShipType.kisser;
                }
                for(int i=2; i<hunter.getSize()+2; i++) {
                    gameLayout[8][i] = ShipType.hunter;
                }
                for(int i=0; i<torpedoboat.getSize()+2; i++) {
                    gameLayout[i][9] = ShipType.torpedoboat;
                }
                gameLayout[4][2] = ShipType.uboat;
                break;


        }
    }

    //Metod som kollar klickad ruta med hjälp av koordinater (int)
    //Returnerar feedback och avslutar eventuellt spelet
    public String checkPosition(int posY, int posX){

        //variabel som håller reda på resultatet av skottet (varför låter detta så brutalt)
        String result = null;
        try {
            //Skjuter rätt skepp
            ShipType thePositionsShipType = gameLayout[posY][posX];
                switch(thePositionsShipType){
                    case fightingShip:
                        result = fightingShip.shoot();
                        totalHit++;
                        break;
                    case hunter:
                        result = hunter.shoot();
                        totalHit++;
                        break;
                    case kisser:
                        result = kisser.shoot();
                        totalHit++;
                        break;
                    case torpedoboat:
                        result = torpedoboat.shoot();
                        totalHit++;
                        break;
                    case uboat:
                        result = uboat.shoot();
                        totalHit++;
                        break;
                }
                highscoreHits++;
                if(totalHit==15){
                    endGame();
                    return "vinnare";
            }else{
                }
            return result;
        }
        catch(NullPointerException e) {
            result = "Du missade";
            highscoreHits++;
        }

        return result;
    }

    //Avslutar spelet och hanterar highscorelistan.
    public void endGame(){
        int whatIndex = 10;
        String name = "anonym";

        for(int i=0; i<highscoreList.length; i++){
            if(highscoreList[i]== null || highscoreHits<highscoreList[i].getHighscoreHits()){
                if(whatIndex>i){
                    whatIndex = i;
                }
            }
        }

        for(int i = highscoreList.length; i>whatIndex; i--){
            try{
                highscoreList[i-1] = highscoreList[i-2];
            }catch(ArrayIndexOutOfBoundsException e){

            }
        }

        name = gameManager.askName();
        highscoreList[whatIndex] = new HighscorePerson(name, highscoreHits);

        for(int i=0; i<highscoreList.length; i++){
            if(highscoreList[i]==null){
                highscoreStrings[i] = "-";
            }else{
                highscoreStrings[i] = highscoreList[i].toString();
            }
        }

        highscoreHits=0;
        gameManager.showHighscore(highscoreStrings);
        gameManager.reset();

    }

    //Startar om spelplanen och användarens poäng
    public void reset() {
        fightingShip.reset();
        hunter.reset();
        kisser.reset();
        torpedoboat.reset();
        uboat.reset();
        totalHit = 0;
        highscoreHits = 0;
    }
}
