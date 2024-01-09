/*
 * Names: Kidus Tensay
 * netID: ktensay
 * G#: 01366237
 * Lecture section: 004
 * Lab section: 208
 */

public abstract class Player {

    /**
     * this abstract method to choose a move
     * @param miniCheckers
     * @return the possible moves
     */
    public abstract MiniCheckers chooseMove(MiniCheckers miniCheckers);

    /**
     * this method determines the value of the board
     * @param miniCheckers
     * @return a double value representation of how good the board is for the player
     */
    public double boardValue(MiniCheckers miniCheckers){
        if(miniCheckers.checkWin(this)){
            return 1.0;
        } else if (miniCheckers.checkLose(this)) {
            return -1.0;
        }
        return 0.0;
    }
}
