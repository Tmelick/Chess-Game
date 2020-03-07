package Code;

public class Knight extends Piece{
    Knight(boolean pieceColor){
        super(pieceColor);

    }

    @Override
    public String getName() {
        return "Knight";
    }

    @Override
    public PossibleMoves[] getMoves()  {
        return new PossibleMoves[]{
                PossibleMoves.KNIGHT_LEFT_UP,
                PossibleMoves.KNIGHT_UP_LEFT,
                PossibleMoves.KNIGHT_UP_RIGHT,
                PossibleMoves.KNIGHT_RIGHT_UP,
                PossibleMoves.KNIGHT_RIGHT_DOWN,
                PossibleMoves.KNIGHT_DOWN_RIGHT,
                PossibleMoves.KNIGHT_DOWN_LEFT,
                PossibleMoves.KNIGHT_LEFT_DOWN,
                PossibleMoves.UP,
                PossibleMoves.DOWN,
                PossibleMoves.LEFT,
                PossibleMoves.RIGHT,
                PossibleMoves.UP_RIGHT,
                PossibleMoves.UP_LEFT,
                PossibleMoves.DOWN_LEFT,
                PossibleMoves.DOWN_RIGHT,
        };
    }


        @Override
    public boolean usesSingleMove() {
        return true;
    }
}
