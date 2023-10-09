package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Conner Santa Monica
 * @version October 2023
 *
 * This is the main activity class for the LightsOut game application.
 *
 * The MainActivity class extends AppCompatActivity and implements the View.OnClickListener
 * interface to handle user interactions with the game grid buttons. It dynamically creates a grid
 * of buttons with random initial colors (black or gray) and allows users to toggle the color of
 * individual buttons by clicking on them.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //An ArrayList of Buttons representing the grid of the LightsOut game.
    static ArrayList<Button> buttons;

    //Constant representing a white button color.
    static final int WHITE = 0;


    //Constant representing a black button color.
    static final int BLACK = 1;


    //The size of the grid (number of rows and columns).
    static final int GRID = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the layout and buttons ArrayList
        LinearLayout layout = findViewById(R.id.row1);
        buttons = new ArrayList<>();

        // Initialize a random number generator
        Random rand = new Random();

        int y = 1;
        while (y <= GRID) {
            for (int i = 0; i < GRID; i++) {
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
                buttons.add(button);
            }

            y++;

            // Switch to the next row in the layout based on the value of 'y'
            switch (y) {
                case 2: { layout = findViewById(R.id.row2); break; }
                case 3: { layout = findViewById(R.id.row3); break; }
                case 4: { layout = findViewById(R.id.row4); break; }
                case 5: { layout = findViewById(R.id.row5); break; }
            }
        }
    }

    /**
     * Handles the button click event by calling the 'switchColor' method of the 'SwitchView'
     * class to toggle the color of the clicked button and its adjacent buttons.
     *
     * @param v The View that was clicked (a Button in this case).
     */
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        SwitchView.switchColor(clickedButton);
    }
}
