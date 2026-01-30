/**
* @author shaked hazan shaked1246@gmail.com
* @version 1.0
* @since 2026-01-30
* This activity displays a numerical series and information about it.
*/
package com.example.ex11061;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SeriesViewPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView tV;
    Spinner spin;
    String [] series;
    boolean type;
    double diffQuo;

    /**
    * Called when the activity is first created. Initializes the UI and the series data.
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
        setContentView(R.layout.activity_series_view_page);
        tV = findViewById(R.id.tV);
        spin = findViewById(R.id.spin);
        Intent info = getIntent();
        type = info.getBooleanExtra("type", true);
        double firstNumber = info.getDoubleExtra("firstNumber", 0);
        diffQuo = info.getDoubleExtra("diffQuo", 0);
        series = new String[21];
        series[0] = "choose item";
        series[1] = formatDecimal(firstNumber);
        fillSeries(series);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, series);
        spin.setAdapter(adp);
    }

    /**
    * Fills the series array with calculated values based on the series type.
    * <p>
    *
    * @param series The array to be filled with series numbers.
    */
    private void fillSeries(String[] series) {
        for(int i = 2; i < series.length; i++) {
            if (type) {
                series[i] = formatDecimal(Double.parseDouble(series[1]) * Math.pow(diffQuo, (i-1)));
            } else {
                series[i] = formatDecimal(Double.parseDouble(series[1]) + (i-1) * diffQuo);
            }
        }
    }

    /**
    * Callback method to be invoked when an item in this view has been selected.
    * <p>
    *
    * @param adapterView The AdapterView where the selection happened.
    * @param view The view within the AdapterView that was clicked.
    * @param pos The position of the view in the adapter.
    * @param rowid The row id of the item that is selected.
    */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long rowid) {
        if(pos != 0){
            tV.setText("The place of this item is -> " + pos);
            tV.append("\nThe value of the next item is -> " + nextNum(pos));
            tV.append("\nThe sum of the items until this item is -> " + sum(pos));
        }
    }

    /**
    * Calculates the sum of the series up to a given position.
    * <p>
    *
    * @param pos The position up to which the sum should be calculated.
    * @return String The formatted sum of the series.
    */
    private String sum(int pos) {
        if(type)
        {
            return formatDecimal(Double.parseDouble(series[1]) * (Math.pow(diffQuo, pos) - 1) / (diffQuo - 1));
        }
        else
        {
            return formatDecimal((pos) * (Double.parseDouble(series[1]) + Double.parseDouble(series[pos])) / 2);
        }
    }

    /**
    * Calculates the next number in the series after a given position.
    * <p>
    *
    * @param pos The position of the current number.
    * @return String The formatted value of the next number in the series.
    */
    private String nextNum(int pos) {
        if (type) {
            double num = Double.parseDouble(series[1]) * Math.pow(diffQuo, pos);
             return formatDecimal(Double.parseDouble(series[1]) * Math.pow(diffQuo, pos));
        } else {
            return formatDecimal(Double.parseDouble(series[1]) + pos * diffQuo);
        }
    }

    /**
    * Callback method to be invoked when the selection disappears from this view.
    * <p>
    *
    * @param adapterView The AdapterView that now contains no selected item.
    */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        tV.setText("Nothing selected");
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
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}