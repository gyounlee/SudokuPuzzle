package com.novemberstory.sudokupuzzle;

import android.content.Context;

import java.util.Arrays;

public class SudokuGame {
    private static SudokuGame instance;

    private SudokuBoard board = null;

    private int selectedPosX = -1, selectedPosY = -1;
    private int[][] sudokuPuzzle = {
            {9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0},
    };


    private SudokuGame(){}

    public static SudokuGame getInstance(){
        if( instance == null ){
            instance = new SudokuGame();
        }
        return instance;
    }

    public void createBoard( Context context ) {
        board = new SudokuBoard(context);
        board.loadBoard(sudokuPuzzle);
    }

    public void loadBoard(Context context, int[][] newPuzzle ) {
        board.clearBoard();
        sudokuPuzzle = newPuzzle;
        board.loadBoard(sudokuPuzzle);
    }

    public void resetBoard( Context context ) {
        board.resetBoard();
    }

    public SudokuBoard getBoard(){
        return board;
    }

    public void solvePuzzle() {
        if (solve()) {
            board.updateBoard(sudokuPuzzle);
        }
    }

    private boolean solve() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuPuzzle[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (isValid(k, i, j) == true) {
                            sudokuPuzzle[i][j] = k;

                            if (solve()) { // we start backtracking recursively
                                return true;
                            } else { // if not a solution, we empty the cell and we continue
                                sudokuPuzzle[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int value, int col, int row) {
        for (int i = 0; i < 9; i++) {
            if (sudokuPuzzle[col][i] == value) {
                return false;
            }
            if (sudokuPuzzle[i][row] == value) {
                return false;
            }
        }

        int posX = col / 3 * 3;
        int posY = row / 3 * 3;

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudokuPuzzle[posX+i][posY+j] == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
