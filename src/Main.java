// Written by Mayer379

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        boolean debug = false;
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to BattleBoats! Please enter desired grid height:");
        int height = s.nextInt();
        System.out.println("Please enter desired grid width:");
        int width = s.nextInt();
        System.out.println("Would you like to play in debug mode? Y/N:");
        String enterDebug = s.next();
        if (enterDebug.equals("Y")){
            debug = true;
        }

        Board gameBoard = new Board(height, width);
        int turns = 0;
        int shots = 0;
        while (true){
            if (debug){
                System.out.print(gameBoard);
            }
            System.out.println("Enter 'c' to fire cannon, or 'd' to send a drone:");
            String nextAction = s.next();
            System.out.print("X: ");
            int guessX = s.nextInt();
            System.out.print("Y: ");
            int guessY = s.nextInt();
            if (nextAction.equals("c")) {
                int shot = gameBoard.shoot(guessX, guessY);
                shots ++;
                turns ++;
                if (shot == 4){
                    turns ++;
                }
                if (shot == 3){
                    turns = turns + 4;
                }
                if (gameBoard.checkComplete()){
                    System.out.println("You completed the game in " + turns + " turns.");
                    break;
                }
            }
            else if (nextAction.equals("d")){
                if (!gameBoard.drone(guessX, guessY)){
                    turns ++;
                }
                turns = turns + 4;
            }
            System.out.println("");
            if (!debug){
                gameBoard.getGuesses();
            }
        }
    }
}
