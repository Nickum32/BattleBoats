// Written by Mayer 379

public class Boat {
    int xPos;
    int yPos;
    int y1;
    int y2;
    int y3;
    int x1;
    int x2;
    int x3;

    public Boat(int y1, int y2, int y3, int x1, int x2, int x3){
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;

    }
    public boolean sunk(String[][] board){
        if (board[y1][x1].equals("X") && board[y2][x2].equals("X") && board[y3][x3].equals("X"))
            return true;
        else
            return false;
    }
    public boolean checkLoc(int x, int y){
        if (x-1 == x1 && y-1 == y1){
            return true;
        }
        else if (x-1 == x2 && y-1 == y2){
            return true;
        }
        else if (x-1 == x3 && y-1 == y3){
            return true;
        }
        else
            return false;
    }
    public String toString(){
        return "("+x1+","+y1+") ("+x2+","+y2+") ("+x3+","+y3+")";
    }
}
