/*
 * Names: Kidus Tensay
 * netID: ktensay
 * G#: 01366237
 * Lecture section: 004
 * Lab section: 208
 */

import java.util.ArrayList;

public class AIPlayer extends Player{

    private String name;
    private Player opponent;

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * this method is a to string representation of the ai player
     * @return the name of the ai player
     */
    @Override
    public String toString() {
        return name + " (AI)";
    }

    /**
     * constructor for class which sets the name and opponent
     * @param name
     * @param opponent
     */
    public AIPlayer(String name, Player opponent) {
        this.name = name;
        this.opponent = opponent;
    }

    /**
     * this method uses mutual recursion to find the min value
     * @param game
     * @param depth
     * @return the minimum value
     */
    public double minValue(MiniCheckers game, int depth){
        int rTotal = 0;
        int RTotal = 0;
        int bTotal = 0;
        int BTotal = 0;

        if(game.checkWin(this)){
            return 10.0;
        }else if(game.checkLose(this)){
            return -10.0;
        } else if (depth < 1) {
            for(int i = 0; i < game.getBoard().length; i++){
                for(int j = 0; j < game.getBoard()[i].length; j++){
                    if(game.getBoard()[i][j] == 'r'){
                        rTotal++;
                    } else if (game.getBoard()[i][j] == 'R') {
                        RTotal++;
                    } else if (game.getBoard()[i][j] == 'b') {
                        bTotal++;
                    } else if (game.getBoard()[i][j] == 'B') {
                        BTotal++;
                    }
                }
            }
            if(opponent == game.getRed()){
                return (bTotal + (3 * BTotal)) - (rTotal + (3 * RTotal));
            }
            return (rTotal + (3 * RTotal)) - (bTotal + (3 * BTotal));
        }

        double value = Double.MAX_VALUE;

        ArrayList<MiniCheckers> possibleMoves = game.possibleMoves(this);
        if(possibleMoves.size() == 0){
            return 0.0;
        }

        for(int i = 0; i < possibleMoves.size(); i++){
            double val = maxValue(possibleMoves.get(i), depth-1);

            if(val < value){
                value = val;
            }

        }
        return value;
    }

    /**
     * this method uses mutual recursion to find the max value
     * @param game
     * @param depth
     * @return the maximum value
     */
    public double maxValue(MiniCheckers game, int depth){
        int rTotal = 0;
        int RTotal = 0;
        int bTotal = 0;
        int BTotal = 0;

        if(game.checkWin(this)){
            return 10.0;
        }else if(game.checkLose(this)){
            return -10.0;
        } else if (depth < 1) {
            for(int i = 0; i < game.getBoard().length; i++){
                for(int j = 0; j < game.getBoard()[i].length; j++){
                    if(game.getBoard()[i][j] == 'r'){
                        rTotal++;
                    } else if (game.getBoard()[i][j] == 'R') {
                        RTotal++;
                    } else if (game.getBoard()[i][j] == 'b') {
                        bTotal++;
                    } else if (game.getBoard()[i][j] == 'B') {
                        BTotal++;
                    }
                }
            }
            if(opponent == game.getRed()){
                return (bTotal + (3 * BTotal)) - (rTotal + (3 * RTotal));
            }
            return (rTotal + (3 * RTotal)) - (bTotal + (3 * BTotal));
        }

        double value = -9999;

        ArrayList<MiniCheckers> possibleMoves = game.possibleMoves(this);
        if(possibleMoves.size() == 0){
            return 0.0;
        }
        for(int i = 0; i < possibleMoves.size(); i++){
            double val = minValue(possibleMoves.get(i), depth-1);

            if(val > value){
                value = val;
            }

        }
        return value;
    }

    /**
     * this method returns the move with the highest value using the minValue method
     * @param miniCheckers
     * @return the move
     */
    @Override
    public MiniCheckers chooseMove(MiniCheckers miniCheckers) {
        double highest = -9999.0;
        MiniCheckers move = null;
        ArrayList<MiniCheckers> possibleMoves = miniCheckers.possibleMoves(this);

        for(int i = 0; i < possibleMoves.size(); i++){
            double val = minValue(possibleMoves.get(i), 10);
            if(val > highest){
                highest = val;
                move = possibleMoves.get(i);
            }

        }
        return move;
    }

    /**
     * this method overides the boardeValue method to use the maxValue method
     * @param miniCheckers
     * @return the value
     */
    @Override
    public double boardValue(MiniCheckers miniCheckers){
        return maxValue(miniCheckers, 10);
    }
}
