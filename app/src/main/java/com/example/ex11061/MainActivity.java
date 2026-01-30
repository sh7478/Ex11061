/**
* @author shaked hazan shaked1246@gmail.com
* @version 1.0
* @since 2026-01-30
* This is the main activity of the application. It allows the user to define a numerical series
* by providing the first number, the difference or quotient, and the type of series (arithmetic or geometric).
*/
package com.example.ex11061;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    EditText firstNumberInput, diffQuoInput;
    Switch type;
    AlertDialog.Builder adb;

    /**
    * Called when the activity is first created. Initializes the UI components.
    * <p>
    *
    * @param savedInstanceState If the activity is being re-initialized after
    *     previously being shut down then this Bundle contains the data it most
    *     recently supplied in onSaveInstanceState(Bundle).
    *     Note: Otherwise it is null.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        type = findViewById(R.id.type);
        firstNumberInput = findViewById(R.id.firstNumberInput);
        diffQuoInput = findViewById(R.id.diffQuoInput);
    }

    /**
     * This method is called when the confirm button is clicked. It validates the user input and starts the SeriesViewPage activity.
     * <p>
     *
     * @param view The view that was clicked.
     */
    public void confirm(View view) {
        adb = new AlertDialog.Builder(this);
        if(tryParseDecimal(firstNumberInput.getText().toString()) == null || tryParseDecimal(diffQuoInput.getText().toString()) == null)
        {
            adb.setTitle("Error");
            adb.setMessage("You have to put number in each input section");
            AlertDialog ad = adb.create();
            ad.show();
        }
        else if(tryParseDecimal(diffQuoInput.getText().toString()) == 0)
        {
            adb.setTitle("Error");
            adb.setMessage("The number of the difference/quotient can't be 0");
            AlertDialog ad = adb.create();
            ad.show();
        }
        else if(tryParseDecimal(diffQuoInput.getText().toString()) == 1 && type.isChecked())
        {
            adb.setTitle("Error");
            AlertDialog ad = adb.create();
            ad.show();
        }
        else if(tryParseDecimal(firstNumberInput.getText().toString()) == 0)
        {
            adb.setTitle("Error");
            adb.setMessage("The first number of a series can't be 0");
            AlertDialog ad = adb.create();
            ad.show();
        }
        else
        {
            Intent info = new Intent(this, SeriesViewPage.class);
            info.putExtra("type", type.isChecked());
            info.putExtra("firstNumber", tryParseDecimal(firstNumberInput.getText().toString()));
            info.putExtra("diffQuo", tryParseDecimal(diffQuoInput.getText().toString()));
            startActivity(info);
        }
    }
    /**
     * Attempts to parse a string into a Double.
     * <p>
     *
     * @param str The string to be parsed.
     * @return Double Returns the parsed Double value if successful, or null if the string is not a valid number.
     */
    public static Double tryParseDecimal(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }


        if (str.matches("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)")) {
            return Double.parseDouble(str);
        }
        return null;
    }

    /**
     * Formats a double number into a specific string representation. It uses scientific notation for large numbers.
     * <p>
     *
     * @param number The number to be formatted.
     * @return String The formatted number as a string.
     */
    public static String formatDecimal(double number) {
        DecimalFormat simpleFormat = new DecimalFormat("0.####");
        String formattedSimple = simpleFormat.format(number).replaceAll("[^0-9]", "");
        if (formattedSimple.length() <= 5) {
            NumberFormat standardFormat = NumberFormat.getInstance();
            standardFormat.setMaximumFractionDigits(4);
            standardFormat.setGroupingUsed(false);
            return standardFormat.format(number);
        } else {
            DecimalFormat scientificFormat = new DecimalFormat("0.00E0");
            String result = scientificFormat.format(number);
            return result.replace("E", "e");
        }
    }

    /**
     * This method initializes the contents of the Activity's standard options menu.
     * <p>
     *
     * @param menu The options menu in which you place your items.
     * @return boolean You must return true for the menu to be displayed; if you return false it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * <p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to proceed, true to consume it here.
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.credits) {
            Intent cred = new Intent(this, CreditsPage.class);
            startActivity(cred);
        }
        else
        {
            firstNumberInput.setText("");
            diffQuoInput.setText("");
            type.setChecked(false);
        }
        return super.onOptionsItemSelected(item);
    }
}