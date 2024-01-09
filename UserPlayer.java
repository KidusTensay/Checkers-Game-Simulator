/*
 * Names: Kidus Tensay
 * netID: ktensay
 * G#: 01366237
 * Lecture section: 004
 * Lab section: 208
 */

import java.util.ArrayList;
import java.util.Scanner;

public class UserPlayer extends Player{

    private String name;
    private Scanner input;

    /**
     * constructor of the class which sets name and input
     * @param input
     * @param name
     */
    public UserPlayer(Scanner input, String name) {
        this.name = name;
        this.input = input;
    }

    /**
     * this method is a to string representation of the name
     * @return the name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * this method allows the player to choose their next move
     * @param miniCheckers
     * @return the chosen move
     */
    @Override
    public MiniCheckers chooseMove(MiniCheckers miniCheckers) {

        System.out.println(miniCheckers.toString());

        ArrayList<MiniCheckers> possibleMoves = miniCheckers.possibleMoves(this);

        for(int i = 0; i < possibleMoves.size(); i++){
            System.out.println("Move number " + i + ": \n" + possibleMoves.get(i).toString());
        }

        System.out.println("Choose a move: ");
        int move = input.nextInt();
        MiniCheckers moveChose = possibleMoves.get(move);

        return moveChose;
    }
}
