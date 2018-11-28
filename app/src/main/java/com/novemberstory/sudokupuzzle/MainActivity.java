package com.novemberstory.sudokupuzzle;

import android.app.AlertDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
                          implements CreatePuzzleDialog.DialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Solve button
        final Button button = findViewById(R.id.btnSolve);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SudokuGame.getInstance().solvePuzzle();
            }
        });

        SudokuGame.getInstance().createBoard(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.restart:
                restartPuzzle();
                return true;
            case R.id.load:
                loadPuzzle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogPositiveClick(String text) {
        // User touched the dialog's positive button
        //EditText editText = findViewById(R.id.editText);
        //String text = editText.getText().toString();

        String[] token = text.split("\\s+");
        int[][] newPuzzle = new int[9][9];
        int index = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                newPuzzle[j][i] = Integer.parseInt(token[index++]);
            }
        }
        SudokuGame.getInstance().loadBoard(this, newPuzzle);
    }

    public void restartPuzzle() {
        SudokuGame.getInstance().resetBoard(this);
    }

    public void loadPuzzle() {
        DialogFragment newFragment = new CreatePuzzleDialog();
        newFragment.show(getSupportFragmentManager(), "puzzle");

    }
}
