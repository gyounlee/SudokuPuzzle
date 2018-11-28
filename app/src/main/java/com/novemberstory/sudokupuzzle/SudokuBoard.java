package com.novemberstory.sudokupuzzle;

import android.content.Context;
import android.graphics.Color;

public class SudokuBoard {
    private SudokuCell[][] sudokuCells = new SudokuCell[9][9];

    private Context context;

    public SudokuBoard( Context context ){
        this.context = context;
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++){
                sudokuCells[x][y] = new SudokuCell(context);
            }
        }
    }

    public void loadBoard( int[][] board ){
        boolean colFlag = false;
        boolean rowFlag = false;
        for( int x = 0 ; x < 9 ; x++ ){
            if (x > 2 && x < 6) {
                colFlag = true;
            } else {
                colFlag = false;
            }
            for( int y = 0 ; y < 9 ; y++){
                if (y > 2 && y < 6) {
                    rowFlag = true;
                } else {
                    rowFlag = false;
                }
                sudokuCells[x][y].setInitValue(board[x][y]);
                if (colFlag ^ rowFlag) {
                    sudokuCells[x][y].setBackgroundColor(Color.LTGRAY);
                }
            }
        }
    }

    public void updateBoard( int[][] board ){
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++){
                if (sudokuCells[x][y].isInitValue() == false) {
                    sudokuCells[x][y].setTextColor(Color.BLUE);
                }

                sudokuCells[x][y].setValue(board[x][y]);
            }
        }
    }

    public void resetBoard(){
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++){
                if (sudokuCells[x][y].isInitValue() == false) {
                    sudokuCells[x][y].setValue(0);
                }
            }
        }
    }

    public void clearBoard(){
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++){
                sudokuCells[x][y].setInitValue(0);
            }
        }
    }

    public SudokuCell getItem( int position ){
        int x = position % 9;
        int y = position / 9;

        return sudokuCells[x][y];
    }
}
