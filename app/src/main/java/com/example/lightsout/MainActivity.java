package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * @author Conner Santa Monica
 * @version 2023 October
 * <p>
 * The main activity class for the LightsOut game application.
 * <p>
 * This class extends AppCompatActivity and implements the View.OnClickListener interface
 * to handle user interactions with the game grid buttons. It dynamically creates a grid
 * of buttons with random initial colors (black or gray) and allows users to toggle the color of
 * individual buttons by clicking on them.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // An array of Buttons representing the grid of the LightsOut game.
    static Button[][] buttons = new Button[5][5];

    // Constants representing button colors.
    static final int WHITE = 0;
    static final int BLACK = 1;

    // The size of the grid (number of rows and columns).
    static final int GRID = 5;
    static int row;
    static int col;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the layout and buttons array
        LinearLayout layout = findViewById(R.id.row1);

        // Initialize a random number generator
        Random rand = new Random();

        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {
                Button button = new Button(this);

                // Randomly set the button's initial color (black or gray)
                if (rand.nextInt(2) == 1) {
                    button.setBackgroundColor(Color.BLACK);
                    button.setId(BLACK);
                } else {
                    button.setBackgroundColor(Color.GRAY);
                    button.setId(WHITE);
                }

                // Add the button to the layout and set its click listener
                layout.addView(button);
                button.setOnClickListener(this);
                buttons[r][c] = button;
            }

            // Switch to the next row in the layout based on the value of 'r'
            switch (r) {
                case 0: { layout = findViewById(R.id.row2); break; }
                case 1: { layout = findViewById(R.id.row3); break; }
                case 2: { layout = findViewById(R.id.row4); break; }
                case 3: { layout = findViewById(R.id.row5); break; }
            }
        }

        Button resetButton = new Button(this);
        resetButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        resetButton.setText("Reset");
        layout.addView(resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLights();
            }
        });
    }

    /**
     * Handles the button click event by calling the 'colorSwap' method to toggle the color
     * of the clicked button and its adjacent buttons.
     *
     * @param v The View that was clicked (a Button in this case).
     */
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        colorSwap(clickedButton);
        if (checkWin()) { winSeq(); }
    }

    /**
     * Toggles the color of the clicked button and its adjacent buttons.
     *
     * @param button The button that was clicked.
     */
    public void colorSwap(Button button) {
        indexOf(button);
        if (buttons[row][col].getId() != WHITE) {
            setWhite();
        } else {
            setBlack();
        }
        for (int x = 0; x < 5; x++) {
            if (buttons[row][col].getId() != WHITE && buttons[row][col] != button) {
                setWhite();
            } else if (buttons[row][col] != button) {
                setBlack();
            }
            indexOf(button);
            switch (x) {
                case 0: if (row != 0) { row -= 1; } break;
                case 1: if (col != 0) { col -= 1; } break;
                case 2: if (col != 4) { col++; } break;
                case 3: if (row != 4) { row++; } break;
            }
        }
    }

    /**
     * Finds the row and column index of a given button.
     *
     * @param button The button to find the index for.
     */
    public void indexOf(Button button) {
        for (int i = 0; i < buttons.length; i++) {
            for (int y = 0; y < buttons[i].length; y++) {
                if (button == buttons[i][y]) {
                    row = i;
                    col = y;
                }
            }
        }
    }

    /**
     * Changes the color of all buttons to green when the game is won.
     */
    public void winSeq() {
        for (Button[] button : buttons) {
            for (Button value : button) {
                value.setBackgroundColor(Color.GREEN);
            }
        }
    }

    /**
     * Checks if all buttons are black, indicating a win.
     *
     * @return True if all buttons are black, false otherwise.
     */
    public boolean checkWin() {
        for (Button[] button : buttons) {
            for (Button value : button) {
                if (value.getId() != BLACK) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sets a button's color to white (gray) and its ID to WHITE.
     */
    public void setWhite() {
        buttons[row][col].setBackgroundColor(Color.GRAY);
        buttons[row][col].setId(WHITE);
    }

    /**
     * Sets a button's color to black and its ID to BLACK.
     */
    public void setBlack() {
        buttons[row][col].setBackgroundColor(Color.BLACK);
        buttons[row][col].setId(BLACK);
    }

    /**
     * Resets the game by randomizing the button colors.
     */
    public void resetLights() {
        LinearLayout layout = findViewById(R.id.row1);
        Random rand = new Random();
        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {
                if (rand.nextInt(2) == 1) {
                    buttons[r][c].setBackgroundColor(Color.BLACK);
                    buttons[r][c].setId(BLACK);
                } else {
                    buttons[r][c].setBackgroundColor(Color.GRAY);
                    buttons[r][c].setId(WHITE);
                }
            }
            switch (r) {
                case 0: { layout = findViewById(R.id.row2); break; }
                case 1: { layout = findViewById(R.id.row3); break; }
                case 2: { layout = findViewById(R.id.row4); break; }
                case 3: { layout = findViewById(R.id.row5); break; }
            }
        }
    }
}
