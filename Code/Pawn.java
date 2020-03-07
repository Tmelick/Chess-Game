package Code;

import javafx.geometry.Pos;

import java.util.ArrayList;

public class Pawn extends Piece{
        Pawn(boolean pieceColor){
            super(pieceColor);
        }

    @Override
    public String getName() {
        return "Pawn";
    }

    @Override
    public PossibleMoves[] getMoves() {
            PossibleMoves[] moves = {};
            if(!hasMoved){
                ArrayList<PossibleMoves> firstMove = new ArrayList<>();

                firstMove.add(PossibleMoves.DOWN);
                firstMove.add(PossibleMoves.UP);
                firstMove.add(PossibleMoves.UP_RIGHT);
                firstMove.add(PossibleMoves.UP_LEFT);
                firstMove.add(PossibleMoves.DOWN_LEFT);
                firstMove.add(PossibleMoves.DOWN_RIGHT);
                firstMove.add(PossibleMoves.LEFT);
                firstMove.add(PossibleMoves.RIGHT);
                firstMove.add(PossibleMoves.DOUBLE_UP);
                firstMove.add(PossibleMoves.DOUBLE_DOWN);
                moves = firstMove.toArray(moves);
            }
            else{
                ArrayList<PossibleMoves> afterFirst = new ArrayList<>();
                afterFirst.add(PossibleMoves.DOWN);
                afterFirst.add(PossibleMoves.UP);
                afterFirst.add(PossibleMoves.UP_RIGHT);
                afterFirst.add(PossibleMoves.UP_LEFT);
                afterFirst.add(PossibleMoves.DOWN_LEFT);
                afterFirst.add(PossibleMoves.DOWN_RIGHT);
                afterFirst.add(PossibleMoves.LEFT);
                afterFirst.add(PossibleMoves.RIGHT);
                moves = afterFirst.toArray(moves);
            }
            return moves;
    }

    @Override
    public boolean usesSingleMove() {
        return true;
    }
}
