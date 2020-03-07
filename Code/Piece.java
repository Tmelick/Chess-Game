package Code;

import javafx.scene.image.Image;

public abstract class Piece {

    private Image picture;
    private boolean pieceColor;
     boolean hasMoved;

    Piece(boolean pieceColor){
        this.pieceColor = pieceColor;

       String resources = "Resources/" + this.getColor() + this.getName() + ".png";

        this.picture = new Image(resources);
    }

    String getColor(){
        if(this.pieceColor)
            return "white";
        else
            return "black";

    }

    Image getPicture() {
        return picture;
    }

    protected abstract String getName();

    public abstract PossibleMoves[] getMoves();

    public abstract boolean usesSingleMove();

    void setHasMoved() {
        this.hasMoved = true;
    }
}
