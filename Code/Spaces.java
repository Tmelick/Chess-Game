package Code;


import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Spaces extends Button {
    private int x;
    private int y;

    private Piece piece;

    Spaces(boolean color, int x, int y){
        this.x = x;
        this.y = y;
        this.piece = null;
        this.getStyleClass().add("board");

        if (color)
            this.getStyleClass().add("board-white");
        else
            this.getStyleClass().add("board-black");

    }
    void setPiece(Piece piece){
        this.piece = piece;

        if(this.piece != null)
            this.setGraphic(new ImageView(piece.getPicture()));
        else
            this.setGraphic(new ImageView());
    }
    Piece getPiece(){
        return this.piece;
    }
    String getColor(){
        if(getPiece() != null)
            return getPiece().getColor();
        else
            return "";
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }
    Piece pieceSelected(){
        Piece temp = this.piece;
        setPiece(null);
        return temp;
    }
    boolean isOccupied(){
        return(this.piece != null);
    }
}
