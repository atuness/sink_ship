/*William Lilja och Elisabeth Nyström*/
/*Aj0367 och Ai8798*/
/*Medieproduktion och processdesign*/
package Model;

public class Hunter extends Ship{
    private int size = 3;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    //Ökar först antalet träffar.
    //Kollar sedan ifall antalet träffar är större än skeppets storlek
    //Returnerar en sträng med feedback beroende på utfallet
    @Override
    public String shoot() {
        setHits(getHits()+1);
        int hits = 0;
        hits = getHits();
        if(hits>=size){
            setSunk(true);
            return String.format("Du sänkte en %s", ShipType.hunter.name());
        }else if(hits<size){
            return String.format("Du träffade en %s", ShipType.hunter.name());
        }
        return String.format("Något gick snett");
    }
}
