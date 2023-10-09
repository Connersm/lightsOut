package com.example.lightsout;

import static com.example.lightsout.MainActivity.GRID;
import static com.example.lightsout.MainActivity.buttons;

import android.widget.Button;
import android.graphics.Color;


/**
 * The SwitchView class provides a static method 'switchColor' to toggle the color of the
 * associated Button between white and black, and also updates the colors of adjacent Buttons
 * in the grid accordingly.
 */
public class SwitchView {


    //The Button associated with this SwitchView.

    public Button button;

    /**
     * Constructs a new SwitchView with the given Button.
     *
     * @param button The Button to be associated with this SwitchView.
     */
    public SwitchView(Button button) {
        this.button = button;
    }

    /**
     * Static method to switch the color of a Button and its adjacent Buttons in the grid.
     * If the Button is white, it will be changed to black, and its adjacent Buttons will also
     * be set to black. If the Button is black, it will be changed to gray, and its adjacent
     * Buttons will be set to gray as well.
     *
     * @param button The Button whose color needs to be switched.
     */
    public static void switchColor(Button button) {
        // Get the index of the Button in the list of Buttons
        int idx = buttons.indexOf(button);

        if (button.getId() == MainActivity.WHITE) {
            // If the Button is white, switch it to black
            button.setBackgroundColor(Color.BLACK);
            button.setId(MainActivity.BLACK);

            // Set the colors and IDs of adjacent Buttons to black
            buttons.get(idx - GRID + 1).setBackgroundColor(Color.BLACK);
            buttons.get(idx - GRID - 1).setBackgroundColor(Color.BLACK);
            buttons.get(idx - GRID + 1).setId(MainActivity.BLACK);
            buttons.get(idx - GRID - 1).setId(MainActivity.BLACK);
        } else if (button.getId() == MainActivity.BLACK) {
            // If the Button is black, switch it to gray
            button.setBackgroundColor(Color.GRAY);
            button.setId(MainActivity.WHITE);

            // Set the colors and IDs of adjacent Buttons to gray
            buttons.get(idx - GRID + 1).setBackgroundColor(Color.GRAY);
            buttons.get(idx - GRID - 1).setBackgroundColor(Color.GRAY);
            buttons.get(idx - GRID + 1).setId(MainActivity.WHITE);
            buttons.get(idx - GRID - 1).setId(MainActivity.WHITE);
        }
    }
}
