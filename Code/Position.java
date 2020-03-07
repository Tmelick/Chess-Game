package Code;

class Position {
    private int originalX;
    private int originalY;
    private int newX;
    private int newY;

    Position(int originalX, int originalY, int newX, int newY){
        this.originalX = originalX;
        this.originalY = originalY;
        this.newX = newX;
        this.newY = newY;
    }

    int getOriginalX() {
        return originalX;
    }

    int getOriginalY() {
        return originalY;
    }

    int getNewX() {
        return newX;
    }

    int getNewY() {
        return newY;
    }

    int getGapX(){return this.newX - this.originalX;}

    int getGapY(){return this.newY - this.originalY;}
}
