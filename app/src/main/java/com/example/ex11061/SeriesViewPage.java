package com.example.ex11061;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
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

    private void fillSeries(String[] series) {
        for(int i = 2; i < series.length; i++) {
            if (type) {
                series[i] = formatDecimal(Double.parseDouble(series[1]) * Math.pow(diffQuo, (i-1)));
            } else {
                series[i] = formatDecimal(Double.parseDouble(series[1]) + (i-1) * diffQuo);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long rowid) {
        if(pos != 0){
            tV.setText("The place of this item is -> " + pos);
            tV.append("\nThe value of the next item is -> " + nextNum(pos));
            tV.append("\nThe sum of the items until this item is -> " + sum(pos));
        }
    }

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

    private String nextNum(int pos) {
        if (type) {
            double num = Double.parseDouble(series[1]) * Math.pow(diffQuo, pos);
             return formatDecimal(Double.parseDouble(series[1]) * Math.pow(diffQuo, pos));
        } else {
            return formatDecimal(Double.parseDouble(series[1]) + pos * diffQuo);
        }
    }

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
}