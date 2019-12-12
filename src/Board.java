// Written by Mayer379

public class Board {
    int column;
    int row;
    Boat[] boats;
    String[][] board;
    String[][] guessBoard;

    public Board(int y, int x){ // constructor
        this.column = y;
        this.row = x;
        this.guessBoard = new String[y][x];
        for (int i=0; i<column; i++){
            for (int j=0; j<row; j++){
                guessBoard[i][j] = "-";
            }
        }
        this.board = new String[y][x]; // create 2D array
        for (int i = 0; i < y; i++){
            for(int j = 0; j < x; j++){
                board[i][j] = "~";
            }
        }
        // will use if statements to determine number of boats
        // starting with biggest option because board size will be determined by smaller of two dimensions
        if (Math.min(x,y) > 10 && Math.min(x,y) <= 12){
            this.boats = new Boat[6];
            System.out.println("There are 6 boats.");
        }
        if (Math.min(x,y) > 8 && Math.min(x,y) <= 10) {
            this.boats = new Boat[4];
            System.out.println("There are 4 boats.");
        }
        if (Math.min(x,y) > 6 && Math.min(x,y) <= 8) {
            this.boats = new Boat[3];
            System.out.println("There are 3 boats.");
        }
        if (Math.min(x,y) > 3 && Math.min(x,y) <= 6){
            this.boats = new Boat[2];
            System.out.println("There are 2 boats.");
        }
        if (Math.min(x,y) == 3){ // determining number of boats based on board size
            this.boats = new Boat[1];
            System.out.println("There is 1 boat.");
        }
        if (x <=0 || x > 12 || y <= 0 || y > 12){
            System.out.println("Invalid board size, please try again");
        }
        //System.out.println(boats);
        boatPlacer(boats, board, x, y);
    }
    public void boatPlacer(Boat[] boats, String[][] board, int x, int y){
        //System.out.println("boatPlacer test");
        for (int i=0; i<boats.length; i++){
            int yPos = posGen(y);
            int xPos = posGen(x);
            //System.out.println("here");
            double orientation = Math.random(); // orientation > 0.5 will position boat vertically, <= 0.5 horizontally
            if (orientation > 0.5) {
                if ((board[yPos][xPos].equals("~")) && (board[yPos+1][xPos].equals("~")) &&
                        (board[yPos+2][xPos].equals("~"))){
                    board[yPos][xPos] = "B";
                    board[yPos+1][xPos] = "B";
                    board[yPos+2][xPos] = "B";
                    boats[i] = new Boat(yPos,yPos+1,yPos+2,xPos,xPos,xPos);
                }
                else
                    i--;
            }
            if (orientation <= 0.5) {
                if ((board[yPos][xPos].equals("~")) && (board[yPos][xPos+1].equals("~")) &&
                        (board[yPos][xPos + 2].equals("~"))) {
                    board[yPos][xPos] = "B";
                    board[yPos][xPos+1] = "B";
                    board[yPos][xPos+2] = "B";
                    boats[i] = new Boat(yPos,yPos,yPos,xPos,xPos+1,xPos+2);
                } else
                    i--;
            }
        }
        //System.out.println(boats[1]);
    }
    public int getX(){
        return row;
    }
    public int getY(){
        return column;
    }
    public boolean drone(int x, int y){
        if (x > row || x <= 0 || y > column || y <= 0){
            return false;
        }
        else {
            String drone = "";
            for (int i=0; i<board.length; i++){
                for (int j=0; j<board[i].length; j++){
                    if ((i == y-1) || (j == x-1)) {
                        drone = drone + board[i][j] + " ";
                        guessBoard[i][j] = board[i][j];
                    }
                    else{
                        drone = drone + "  ";
                    }
                }
                drone = drone + "\n";
            }
            System.out.print(drone);
            return true;
        }
    }
    // posGen takes a board dimension as argument and returns a random position to place the boat on
    public int posGen(int n){
        double randPos = Math.random() * (n-2);
        //randPos uses n-2 as the double will be converted to an int whose max value will be
        //the length of the dimension - 3 (if  dimension is 10, max int value desired is 7
        int position = (int) randPos;
        //System.out.println(position);
        return position;
    }
    // shoot() will return an int, 1 = hit, 2 = miss, 3 = repeat guess, 4 = out of bounds
    public int shoot(int x, int y){
        if (x <= 0 || x > row || y <= 0 || y > column){
            System.out.println("Out of bounds.");
            return 4;
        }
        else if (guessBoard[y-1][x-1].equals("O") || guessBoard[y-1][x-1].equals("X")){
            System.out.println("Penalty! Repeat guess.");
            return 3;
        }
        else if (board[y-1][x-1].equals("B")){
            System.out.println("Hit!");
            board[y-1][x-1] = "X";
            guessBoard[y-1][x-1] = "X";
            for (int i=0; i<boats.length; i++){
                if (boats[i].checkLoc(x,y)){
                    if(boats[i].sunk(board)){
                        System.out.println("Sunk!");
                    }
                }
            }
            return 1;
        }
        else {
            System.out.println("Miss!");
            board[y-1][x-1] = "O";
            guessBoard[y-1][x-1] = "O";
            return 2;
        }
    }
    public boolean checkComplete(){
        for (int i=0; i<board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("B")){
                    return false;
                }
            }
        }
        return true;
    }
    public void getGuesses(){
        String guesses = "";
        for (int i=0; i<guessBoard.length; i++){
            for (int j=0; j<guessBoard.length; j++){
                guesses = guesses + guessBoard[i][j] + " ";
            }
            guesses = guesses + "\n";
        }
        System.out.print(guesses);
    }
    public String toString(){
        String printBoard = "";
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                printBoard = printBoard + board[i][j] + " ";
            }
            printBoard = printBoard + "\n";
        }
        return printBoard;
    }
    public static void main(String[] args){
        Board goBoatsGo = new Board(12,12);
        System.out.println(goBoatsGo);
        //goBoatsGo.drone(1,1);
//        goBoatsGo.shoot(1,1);
//        goBoatsGo.shoot(2,2);
//        goBoatsGo.shoot(3,3);
//        goBoatsGo.shoot(4,4);
//        goBoatsGo.shoot(6,5);
//        goBoatsGo.drone(2,2);
//        goBoatsGo.getGuesses();
//        System.out.print(goBoatsGo);
    }
}
