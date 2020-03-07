package Code;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;

class Board extends GridPane {
    //Creates an 8 by 8 chess board
    private Spaces[][] board = new Spaces[8][8];
    private Spaces selectedSpace = null;
    private String whoseTurn = "white";
    private int moveCount = 0;
    private ArrayList<Piece> attackList = new ArrayList<>();
    private ArrayList<Piece> movedList = new ArrayList<>();

    //The boolean value isWhite is for the user to choose if they want to be white or black
    Board(boolean isWhite) {
        super();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                boolean light = ((x + y) % 2 != 0); // checkerboard space colors
                board[x][y] = new Spaces(light, x, y);

                //If the user selects white it will build the board with white at the bottom
                //if the user selects black it will build the board with black at the bottom
                if (isWhite) {
                    this.add(board[x][y], x, 7 - y);
                } else {
                    this.add(board[x][y], 7 - x, y);
                }

                int FX = x;
                int FY = y;
                //runs things that happen when a space is clicked
                board[x][y].setOnAction(e -> onClickedSpace(FX, FY));
            }
        }
        this.setUpStartingPositions();
    }

    //The method that handles what happens when a space is clicked
    private void onClickedSpace(int x, int y) {
        // Gets the selected space based on the passed values for x and y
        Spaces spaceSelected = board[x][y];

        //This check is to make sure that any space selected is
        if (selectedSpace != null && !selectedSpace.getColor().equals(whoseTurn)) {
            this.setSelectedSpace(null);
        }
        //Preforms some checks to make sure that an incorrect space isn't selected.
        if (selectedSpace != null && selectedSpace.getPiece() != null
                && !spaceSelected.getColor().equals(selectedSpace.getColor())
                && selectedSpace.getColor().equals(whoseTurn)) {
            Position position;
            position = new Position(selectedSpace.getX(), selectedSpace.getY(), x, y);

            //creates a varriable for if a move is completed or not.
            boolean moveCompleted;
            moveCompleted = Move(position);
            if (moveCompleted) {
                moveCount++;
            }
            //This is the turn counter. Since white always goes first it is preset to white. Once white moves it changes
            //whoseTurn to black and then it just goes back and forth as the players move their pieces.
            if (moveCount == 2 && whoseTurn.equals("white")) {
                whoseTurn = "black";
                moveCount = 0;
            } else if (moveCount == 2 && whoseTurn.equals("black")) {
                whoseTurn = "white";
                moveCount = 0;
            }
            this.setSelectedSpace(null);


        } else if (board[x][y].getPiece() != null)
            // If the player selects a piece that is the same color the selected piece changes to the new piece
            // If they select a colored piece that is not their own it deselects the piece.
            if (spaceSelected.getColor().equals(whoseTurn))
                this.setSelectedSpace(board[x][y]);
            else
                this.setSelectedSpace(null);
    }

    // This method just sets the board up with the starting positions.
    private void setUpStartingPositions() {
        //These will be for white(true)
        this.board[0][0].setPiece(new Rook(true));
        this.board[1][0].setPiece(new Knight(true));
        this.board[2][0].setPiece(new Bishop(true));
        this.board[3][0].setPiece(new Queen(true));
        this.board[4][0].setPiece(new King(true));
        this.board[5][0].setPiece(new Bishop(true));
        this.board[6][0].setPiece(new Knight(true));
        this.board[7][0].setPiece(new Rook(true));

        for (int i = 0; i < 8; i++)
            this.board[i][1].setPiece(new Pawn(true));

        //This will be for black
        for (int i = 0; i < 8; i++)
            this.board[i][6].setPiece(new Pawn(false));

        this.board[0][7].setPiece(new Rook(false));
        this.board[1][7].setPiece(new Knight(false));
        this.board[2][7].setPiece(new Bishop(false));
        this.board[3][7].setPiece(new Queen(false));
        this.board[4][7].setPiece(new King(false));
        this.board[5][7].setPiece(new Bishop(false));
        this.board[6][7].setPiece(new Knight(false));
        this.board[7][7].setPiece(new Rook(false));


    }

    //This method handles assigning the red square for selection
    private void setSelectedSpace(Spaces selected) {
        if (this.selectedSpace != null)
            this.selectedSpace.getStyleClass().removeAll("board-selected");

        this.selectedSpace = selected;

        if (this.selectedSpace != null)
            this.selectedSpace.getStyleClass().add("board-selected");
    }

    // Method that registers the movement, and passes the movement to a method to test the validitiy of the move.
    private boolean Move(Position position) {
        if (isMovePossible(position)) {
            Spaces originalSpace = board[position.getOriginalX()][position.getOriginalY()];
            Spaces targetedSpace = board[position.getNewX()][position.getNewY()];

            targetedSpace.setPiece(originalSpace.pieceSelected());
            return true;
        }
        //returns false if the move is not possible.
        else
            return false;

    }

    //This method will validate the movement of the pieces and return true if the move is valid.
    private boolean isMovePossible(Position position) {
        Spaces originalSpace;
        Spaces targetSpace;
        Piece piece;
        Piece targetPiece;
        PossibleMoves[] moves;

        if (moveCount == 0){
            movedList.clear();
        }

        if (position == null)
            return false;

        // Check if original space selected is within the board
        try {
            originalSpace = board[position.getOriginalX()][position.getOriginalY()];
        } catch (NullPointerException e) {
            return false;
        }

        // Check if the targeted space is within the board
        try {
            targetSpace = board[position.getNewX()][position.getNewY()];
        } catch (NullPointerException e) {
            return false;
        }

        // Checks to see if the original space selected is empty
        if (!originalSpace.isOccupied()) {
            return false;
        }

        // This prevents pieces from attacking other pieces that aren't more then one square from them.
        if (targetSpace.getPiece() != null) {
            if (position.getGapX() > 1 || position.getGapX() < -1 || position.getGapY() > 1 || position.getGapY() < -1) {
                return false;
            }

        }

        //Assigns the original space's piece and the possible moves for that piece.
        piece = originalSpace.getPiece();
        targetPiece = targetSpace.getPiece();
        moves = piece.getMoves();

        if (targetSpace.getPiece() != null) {
            if (!(attack(piece, targetPiece))) {
                return false;
            }
        }

        if (targetSpace.getPiece() == null){
            if(movedList.contains(piece)){
                return false;
            }
        }

        boolean correctMoves = false;
        int moveCount;
        int nextMoveX;
        int nextMoveY;

        /*System.out.println("This is Y: " + originalSpace.getX());
        System.out.println("This is X: " + originalSpace.getY());

        System.out.println("This is target x: " + targetSpace.getX());
        System.out.println("This is target y: " + targetSpace.getY());

        System.out.println("This is X original - target" + (originalSpace.getX() - targetSpace.getX()));
        System.out.println("This is Y original - target" + (originalSpace.getY() - targetSpace.getY()));
        int differentX = originalSpace.getX() - targetSpace.getX();
        int differentY = originalSpace.getY() - targetSpace.getY();
        if(differentX == 0 && differentY != 0){
            if (differentY > 0){
                System.out.println("You moved up");
            }
            else
                System.out.println("You moved down");
        }
        else if(differentX != 0 && differentY != 0){
            if (differentX > 0){

                //FINISH THE OPTIONS FOR IF THEY JUST MOVE UP AND DOWN
                //AND TO ALSO INCLUDE THE OPTION FOR IF THEY MOVE DIAGNOLY.
                //CHECK EACH POSITIONS WITHIN THE SPACES BETWEEN TARGETED AND ORIGINAL TO SEE IF THEY ARE EMPTY
                //IF THEY ARE NOT EMPTY THEN RETURN FALSE SO THAT WAY THE MOVE DOESN'T HAPPEN.

                //INSTEAD OF DOING ABOVE ATTEMPT THIS. IF PIECE SELECTED IS A BISHOP OR A ROOK THEN USING THE DIFFERENCE
                //OF ORGINAL - TARGET. YOU'RE ABLE TO KNOW IF IT(BISHOP/ROOK) WENT IN A DIRRECTION IT CAN ONLY MOVE ONCE
                // IF IT MOVES IN A DIRECTION IT CAN ONLY MOVE ONCE TO MUCH RETURN FALSE. THIS SHOULD PULL IT OUT
                // OF THE METHOD AND CANCEL THE MOVE ALL TOGETHER.
            }
        }*/


        //Add the extra movement to the bishop class
        // and add the if statements to check to see whether they are moving outside the range of 1square with different
        // movement then normal.

        // Assigns a name to the loop so its possible to break out.
        MovementLoop:
        for (PossibleMoves move : moves) {
            moveCount = 1;
            if (!piece.usesSingleMove()) {
                moveCount = 4;
            }

            boolean hasCollided = false;


           /* // This handles the fuzzy logic for the Bishop. Essentially it will take the change in y axis and the x axis
            //and make sure the bishop can't move more then one square up,down,left,right.
            if(originalSpace.getPiece().getName().equals("Bishop")) {
                if ((position.getGapX()) == 0 ) {
                    if ((position.getGapY() > 1) || (position.getGapY() < -1)) {
                        return false;
                    }
                }
                if((position.getGapY())== 0){
                    if ((position.getGapX() > 1)|| (position.getGapX() < -1)){
                        return false;
                    }
                }
            }

            //This handles the fuzzy logic for Rook movement in the same way the Bishop is handled. If the change in
            //axis is greater then 1 for a diagnoal movement it will return false saying the movement isn't allowed.
            if(originalSpace.getPiece().getName().equals("Rook")){
                if((position.getGapX()) != 0 && (position.getGapY()) != 0){
                    if((position.getGapY() > 1) || (position.getGapY() < -1)
                            ||(position.getGapX() > 1) || (position.getGapX() < -1)){
                        return false;
                    }
                }
            }*/

            for (int i = 1; i <= moveCount; i++) {
                //If the movement leads to another piece it will break out of the loop and cancel the movement.
                if (hasCollided) {
                    break;
                }

                //Gets the next move in the movement loop and sets it to a temp variable that will check
                //if there are any pieces in the space.
                nextMoveX = move.getX() * i;
                nextMoveY = move.getY() * i;
                Spaces nextSpace;

                //Makes sure that future positions are not out of bounds.
                try {
                    nextSpace = board[position.getOriginalX() + nextMoveX]
                            [position.getOriginalY() + nextMoveY];
                } catch (Exception e) {
                    break;
                }

                //Checks to see if the next space is empty and if it isn't prevents the movement from
                //occuring and breaks out of the movement of the piece so that the player actually selects the
                // new piece instead.
                if (nextSpace.isOccupied()) {
                    hasCollided = true;
                    boolean piecesSameColor = nextSpace.getPiece().getColor()
                            .equals(originalSpace.getPiece().getColor());
                    //stops checking if the pieces in the movement are the same color.
                    if (piecesSameColor) {
                        break;
                    }
                }

                //checks to see if the actual moves matched the nextMoves which will essentially validate the move and
                //will break out of the loop and return true.
                if (position.getGapX() == nextMoveX && position.getGapY() == nextMoveY) {
                    correctMoves = true;

                    //Sets the piece to has moved. This prevents the pawns from being able to move extra movement
                    piece.setHasMoved();
                    //breaks out of MoveLoop (both loops)
                    break MovementLoop;
                }
            }
        }
        if (correctMoves) {
            if (piece.getName().equals("Pawn") || piece.getName().equals("King"))
                movedList.add(piece);
        }
        return correctMoves;

    }

    //This method handles the dice roll for the fuzzy logic
    private boolean attack(Piece attacker, Piece defender) {
        if(moveCount == 0){
            attackList.clear();
        }
        if (attackList.contains(attacker)) {
            System.out.println("This piece has already attacked");
            return false;
        }

        boolean win = false;
        int roll;
        Random dice = new Random();
        roll = dice.nextInt((6 - 1) + 1) + 1;
        switch (attacker.getName()) {
            case "Pawn":
                switch (defender.getName()) {
                    case "Pawn":
                        if (roll > 3)
                            win = true;
                        break;
                    case "Knight":
                        if (roll > 4)
                            win = true;
                        break;
                    case "Bishop":
                    case "King":
                    case "Queen":
                    case "Rook":
                        if (roll > 5)
                            win = true;
                        break;
                }
                break;
            case "Knight":
                switch (defender.getName()) {
                    case "Pawn":
                        if (roll > 2)
                            win = true;
                        break;
                    case "Knight":
                        if (roll > 3)
                            win = true;
                        break;
                    case "Bishop":
                    case "Rook":
                        if (roll > 4)
                            win = true;
                        break;
                    case "Queen":
                    case "King":
                        if (roll > 5)
                            win = true;
                        break;
                }
                break;
            case "Rook":
            case "Bishop":
                switch (defender.getName()) {
                    case "Pawn":
                        if (roll > 1)
                            win = true;
                        break;
                    case "Knight":
                        if (roll > 2)
                            win = true;
                        break;
                    case "Rook":
                    case "Bishop":
                        if (roll > 3)
                            win = true;
                        break;
                    case "Queen":
                    case "King":
                        if (roll > 4)
                            win = true;
                        break;
                }
                break;
            case "Queen":
            case "King":
                switch (defender.getName()) {
                    case "Pawn":
                        if (roll > 0)
                            win = true;
                        break;
                    case "Knight":
                        if (roll > 1)
                            win = true;
                        break;
                    case "Rook":
                    case "Bishop":
                        if (roll > 2)
                            win = true;
                        break;
                    case "Queen":
                    case "King":
                        if (roll > 3)
                            win = true;
                        break;
                }
                break;
        }
        if (!attacker.getName().equals("Knight")) {
            System.out.println(roll);
            attackList.add(attacker);
            System.out.println(attacker);
        }


        if (!win) {
            moveCount++;
            System.out.println("Attack Failed");

        }

        return win;
    }

}
// To implement the correct attacking set up. Each piece is going to have a have attacked boolean value that will be changed
// upon the success or failure of an attack.