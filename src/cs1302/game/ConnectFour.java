package cs1302.game;

import cs1302.gameutil.GamePhase;
import cs1302.gameutil.Token;
import cs1302.gameutil.TokenGrid;

/**
 * {@code ConnectFour} represents a two-player connection game involving a two-dimensional grid of
 * {@linkplain cs1302.gameutil.Token tokens}. When a {@code ConnectFour} game object is
 * constructed, several instance variables representing the game's state are initialized and
 * subsequently accessible, either directly or indirectly, via "getter" methods. Over time, the
 * values assigned to these instance variables should change so that they always reflect the
 * latest information about the state of the game. Most of these changes are described in the
 * project's <a href="https://github.com/cs1302uga/cs1302-c4-alpha#functional-requirements">
 * functional requirements</a>.
 */
public class ConnectFour {

    //----------------------------------------------------------------------------------------------
    // INSTANCE VARIABLES: You should NOT modify the instance variable declarations below.
    // You should also NOT add any additional instance variables. Static variables should
    // also NOT be added.
    //----------------------------------------------------------------------------------------------

    private int rows;        // number of grid rows
    private int cols;        // number of grid columns
    private Token[][] grid;  // 2D array of tokens in the grid
    private Token[] player;  // 1D array of player tokens (length 2)
    private int numDropped;  // number of tokens dropped so far
    private int lastDropRow; // row index of the most recent drop
    private int lastDropCol; // column index of the most recent drop
    private GamePhase phase; // current game phase

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------------------------

    /**
     * Constructs a {@link cs1302.game.ConnectFour} game with a grid that has {@code rows}-many
     * rows and {@code cols}-many columns. All of the game's instance variables are expected to
     * be initialized by this constructor as described in the project's
     * <a href="https://github.com/cs1302uga/cs1302-c4-alpha#functional-requirements">functional
     * requirements</a>.
     *
     * @param rows the number of grid rows
     * @param cols the number of grid columns
     * @throws IllegalArgumentException if the value supplied for {@code rows} or {@code cols} is
     *     not supported. The following values are supported: {@code 6 <= rows <= 9} and
     *     {@code 7 <= cols <= 9}.
     */
    public ConnectFour(int rows, int cols)  {
        //
        // replace the entire contents of this constructor with your implementation
        //
        //throw new UnsupportedOperationException("constructor: not yet implemented.");
        if ((rows < 6 || rows > 9) || (cols < 7 || cols > 9)) {
            throw new IllegalArgumentException();
        } else {
            this.grid = new Token[rows][cols];
            this.numDropped = 0;
            this.lastDropRow = -1;
            this.lastDropCol = -1;
            this.rows = rows;
            this.cols = cols;
            this.phase = GamePhase.NEW;
        } // if
    } // ConnectFour

    //----------------------------------------------------------------------------------------------
    // INSTANCE METHODS
    //----------------------------------------------------------------------------------------------

    /**
     * Return the number of rows in the game's grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        //
        // replace the entire contents of this method with your implementation
        //
        // throw new UnsupportedOperationException("getRows: not yet implemented.");
        return rows;
    } // getRows

    /**
     * Return the number of columns in the game's grid.
     *
     * @return the number of columns
     */
    public int getCols() {
        //
        // replace the entire contents of this method with your implementation
        //
        // throw new UnsupportedOperationException("getCols: not yet implemented.");
        return cols;
    } // getCols

    /**
     * Return whether {@code row} and {@code col} specify a location inside this game's grid.
     *
     * @param row the position's row index
     * @param col the positions's column index
     * @return {@code true} if {@code row} and {@code col} specify a location inside this game's
     *     grid and {@code false} otherwise
     */
    public boolean isInBounds(int row, int col) {
        //
        // replace the entire contents of this method with your implementation
        //
        // throw new UnsupportedOperationException("isInBounds: not yet implemented.");
        if ((0 >= row && (row < 6 || row < 9)) && (0 >= col && (col < 7 || col < 9))) {
            return true;
        } else {
            return false;
        } // if
    } // isInBounds

    /**
     * Return the grid {@linkplain cs1302.gameutil.Token token} located at the specified position
     * or {@code null} if no token has been dropped into that position.
     *
     * @param row the token's row index
     * @param col the token's column index
     * @return the grid token located in row {@code row} and column {@code col}, if it exists;
     *     otherwise, the value {@code null}
     * @throws IndexOutOfBoundsException if {@code row} and {@code col} specify a position that is
     *     not inside this game's grid.
     */
    public Token getTokenAt(int row, int col) {
        //
        // replace the entire contents of this method with your implementation
        //
        //throw new UnsupportedOperationException("getTokenAt: not yet implemented.");
        if (this.isInBounds(row, col) == false) {
            return grid[row][col];
        } else {
            throw new IndexOutOfBoundsException("Position of token is not inside the game's grid");
        } // if
    } // getTokenAt

    /**
     * Set the first player token and second player token to {@code token0} and {@code token1},
     * respectively. If the current game phase is {@link cs1302.gameutil.GamePhase#NEW}, then
     * this method changes the game phase to {@link cs1302.gameutil.GamePhase#READY}, but only
     * if no exceptions are thrown.
     *.
     * @param token0 token for first player
     * @param token1 token for second player
     * @throws NullPointerException if {@code token0} or {@code token1} is {@code null}.
     * @throws IllegalArgumentException if {@code token0 == token1}.
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#PLAYABLE} or {@link cs1302.gameutil.GamePhase#OVER}.
     */
    public void setPlayerTokens(Token token0, Token token1) {
        //
        // replace the entire contents of this method with your implementation
        //
        //throw new UnsupportedOperationException("setPlayerTokens: not yet implemented.");
        if (token0 == null || token1 == null) {
            throw new NullPointerException();
        } else if (token0 == token1) {
            throw new IllegalArgumentException();
        } else if (getPhase() == GamePhase.PLAYABLE || getPhase() == GamePhase.OVER) {
            throw new IllegalStateException();
        } else {
            player = new Token[]{token0, token1};
            this.phase = GamePhase.READY;
        } // if
    } // setPlayerTokens

    /**
     * Return a player's token.
     *
     * @param player the player ({@code 0} for first player and {@code 1} for second player)
     * @return the token for the specified player
     * @throws IllegalArgumentException if {@code player} is neither {@code 0} nor {@code 1}
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW}.
     */
    public Token getPlayerToken(int player) {
        //
        // replace the entire contents of this method with your implementation
        //
        //throw new UnsupportedOperationException("getPlayerToken: not yet implemented.");
        if (!(player == 1 || player == 0)) {
            throw new IllegalArgumentException();
        } else if (getPhase() == GamePhase.NEW) {
            throw new IllegalStateException();
        } else {
            return this.player[player];
        } // if
    } // getPlayerToken

    /**
     * Return the number of tokens that have been dropped into this game's grid so far.
     *
     * @return the number of dropped tokens
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getNumDropped() {
        //
        // replace the entire contents of this method with your implementation
        //
        //throw new UnsupportedOperationException("getNumDropped: not yet implemented.");
        if (getPhase() == GamePhase.NEW || getPhase() == GamePhase.READY) {
            throw new IllegalStateException();
        } else {
            return numDropped;
        } // if
    } // getNumDropped

    /**
     * Return the row index of the last (i.e., the most recent) token dropped into this
     * game's grid.
     *
     * @return the row index of the last drop
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getLastDropRow() {
        //
        // replace the entire contents of this method with your implementation
        //
        //throw new UnsupportedOperationException("getLastDropRow: not yet implemented.");
        if (getPhase() == GamePhase.NEW || getPhase() == GamePhase.READY) {
            throw new IllegalStateException();
        } else {
            return lastDropRow;
        } // if
    } // getLastDropRow

    /**
     * Return the col index of the last (i.e., the most recent) token dropped into this
     * game's grid.
     *
     * @return the column index of the last drop
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getLastDropCol() {
        //
        // replace the entire contents of this method with your implementation
        //
        //throw new UnsupportedOperationException("getLastDropCol: not yet implemented.");
        if (getPhase() == GamePhase.NEW || getPhase() == GamePhase.READY) {
            throw new IllegalStateException();
        } else {
            return lastDropCol;
        } // if
    } // getLastDropCol

    /**
     * Return the current game phase.
     *
     * @return current game phase
     */
    public GamePhase getPhase() {
        //
        // replace the entire contents of this method with your implementation
        //
        //throw new UnsupportedOperationException("getPhase: not yet implemented.");
        return phase;
    } // getPhase

    /**
     * Drop a player's token into a specific column in the grid. This method should not enforce turn
     * order -- that is the players' responsibility should they desire an polite and honest game.
     *
     * @param player the player ({@code 0} for first player and {@code 1} for second player)
     * @param col the grid column where the token will be dropped
     * @throws IndexOutOfBoundsException if {@code col} is not a valid column index
     * @throws IllegalArgumentException if {@code player} is neither {@code 0} nor {@code 1}
     * @throws IllegalStateException if {@link #getPhase getPhase()} does not return
     *    {@link cs1302.gameutil.GamePhase#READY} or {@link cs1302.gameutil.GamePhase#PLAYABLE}
     * @throws IllegalStateException if the specified column in the grid is full
     */
    public void dropToken(int player, int col) {
        //
        // replace the entire contents of this method with your implementation
        //
        //throw new UnsupportedOperationException("dropToken: not yet implemented.");
        if (col < 0 || (col > 7 && col > 9)) {
            throw new IndexOutOfBoundsException();
        } else if (!(player == 0 || player == 1)) {
            throw new IllegalArgumentException();
        } else if (!(getPhase() == GamePhase.READY || getPhase() == GamePhase.PLAYABLE)) {
            throw new IllegalStateException();
        } else if (grid[0][col] != null) {
            throw new IllegalStateException();
        } else {
            int rowNum;
            if (rows == 6) {
                rowNum = dropToken6(col);
            } else if (rows == 7) {
                rowNum = dropToken7(col);
            } else if (rows == 8) {
                rowNum = dropToken8(col);
            } else {
                rowNum = dropToken9(col);
            } // if
            this.phase = GamePhase.PLAYABLE;
            this.lastDropCol = col;
            this.lastDropRow = rowNum;
            this.numDropped++;
            this.grid[rowNum][col] = this.player[player];
        } // if
    } // dropToken

    /**
     * Return {@code true} if the last token dropped via {@link #dropToken} created a
     * <em>connect four</em>. A <em>connect four</em> is a sequence of four equal tokens (i.e., they
     * have the same color) -- this sequence can occur horizontally, vertically, or diagonally.
     * If the grid is full or the last drop created a <em>connect four</em>, then this method
     * changes the game's phase to {@link cs1302.gameutil.GamePhase#OVER}.
     *
     * <p>
     * <strong>NOTE:</strong> The only instance variable that this method might change, if
     * applicable, is ``phase``.
     *
     * <p>
     * <strong>NOTE:</strong> If you want to use this method to determin a winner, then you must
     * call it after each call to {@link #dropToken}.
     *
     * @return {@code true} if the last token dropped created a <em>connect four</em>, else
     *     {@code false}
     */
    public boolean isLastDropConnectFour() {
        //
        // replace the entire contents of this method with your implementation
        //
        //throw new UnsupportedOperationException("isWinner: not yet implemented.");
        int emptySpotCounter = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Token player = grid[r][c];
                if (player == null) {
                    emptySpotCounter++;
                    continue;
                } // if
                if (c + 3 < cols &&
                    player == grid[r][c + 1] &&
                    player == grid[r][c + 2] &&
                    player == grid[r][c + 3]) {
                    phase = GamePhase.OVER;
                    return true;
                } // if
                if (r + 3 < rows) {
                    if (player == grid[r + 1][c] &&
                        player == grid[r + 2][c] &&
                        player == grid[r + 3][c]) {
                        phase = GamePhase.OVER;
                        return true;
                    } // if
                    if (c + 3 < cols &&
                        player == grid[r + 1][c + 1] &&
                        player == grid[r + 2][c + 2] &&
                        player == grid[r + 3][c + 3]) {
                        phase = GamePhase.OVER;
                        return true;
                    } // if
                    if (c - 3 >= 0 &&
                        player == grid[r + 1][c - 1] &&
                        player == grid[r + 2][c - 2] &&
                        player == grid[r + 3][c - 3]) {
                        phase = GamePhase.OVER;
                        return true;
                    } // if
                } // if
            } // for
        } // for
        if (emptySpotCounter == 0) {
            phase = GamePhase.OVER;
        } // if
        return false;
    } // isLastDropConnectFour

    //----------------------------------------------------------------------------------------------
    // ADDITIONAL METHODS: If you create any additional methods, then they should be placed in the
    // space provided below.
    //----------------------------------------------------------------------------------------------

    /**
     * Return {@code rowNum} for a board of 6 rows. Called for dropToken method.
     *
     * @param col the grid column where the token will be dropped
     * @return rowNum the grid row number where the token will be dropped
     */
    public int dropToken6(int col) {
        int rowNum;
        if (grid[5][col] == null) {
            rowNum = 5;
        } else if (grid[4][col] == null) {
            rowNum = 4;
        } else if (grid[3][col] == null) {
            rowNum = 3;
        } else if (grid[2][col] == null) {
            rowNum = 2;
        } else if (grid[1][col] == null) {
            rowNum = 1;
        } else {
            rowNum = 0;
        } // if
        return rowNum;
    } // dropToken6

    /**
     * Return {@code rowNum} for a board of 7 rows. Called for dropToken method.
     *
     * @param col the grid column where the token will be dropped
     * @return rowNum the grid row number where the token will be dropped
     */
    public int dropToken7(int col) {
        int rowNum;
        if (grid[6][col] == null) {
            rowNum = 6;
        } else if (grid[5][col] == null) {
            rowNum = 5;
        } else if (grid[4][col] == null) {
            rowNum = 4;
        } else if (grid[3][col] == null) {
            rowNum = 3;
        } else if (grid[2][col] == null) {
            rowNum = 2;
        } else if (grid[1][col] == null) {
            rowNum = 1;
        } else {
            rowNum = 0;
        } // if
        return rowNum;
    } // dropToken7

    /**
     * Return {@code rowNum} for a board of 8 rows. Called for dropToken method.
     *
     * @param col the grid column where the token will be dropped
     * @return rowNum the grid row number where the token will be dropped
     */
    public int dropToken8(int col) {
        int rowNum;
        if (grid[7][col] == null) {
            rowNum = 7;
        } else if (grid[6][col] == null) {
            rowNum = 6;
        } else if (grid[5][col] == null) {
            rowNum = 5;
        } else if (grid[4][col] == null) {
            rowNum = 4;
        } else if (grid[3][col] == null) {
            rowNum = 3;
        } else if (grid[2][col] == null) {
            rowNum = 2;
        } else if (grid[1][col] == null) {
            rowNum = 1;
        } else {
            rowNum = 0;
        } // if
        return rowNum;
    } //dropToken8

    /**
     * Return {@code rowNum} for a board of 9 rows. Called for dropToken method.
     *
     * @param col the grid column where the token will be dropped
     * @return rowNum the grid row number where the token will be dropped
     */
    public int dropToken9(int col) {
        int rowNum;
        if (grid[8][col] == null) {
            rowNum = 8;
        } else if (grid[7][col] == null) {
            rowNum = 7;
        } else if (grid[6][col] == null) {
            rowNum = 6;
        } else if (grid[5][col] == null) {
            rowNum = 5;
        } else if (grid[4][col] == null) {
            rowNum = 4;
        } else if (grid[3][col] == null) {
            rowNum = 3;
        } else if (grid[2][col] == null) {
            rowNum = 2;
        } else if (grid[1][col] == null) {
            rowNum = 1;
        } else {
            rowNum = 0;
        } // if
        return rowNum;
    } //dropToken9

    //----------------------------------------------------------------------------------------------
    // DO NOT MODIFY THE METHODS BELOW!
    //----------------------------------------------------------------------------------------------

    /**
     * <strong>DO NOT MODIFY:</strong>
     * Print the game grid to standard output. This method assumes that the constructor
     * is implemented correctly.
     *
     * <p>
     * <strong>NOTE:</strong> This method should not be modified!
     */
    public void printGrid() {
        TokenGrid.println(this.grid);
    } // printGrid

} // ConnectFour
