/**
* @author shaked hazan shaked1246@gmail.com
* @version 1.0
* @since 2026-01-30
* This activity displays the credits of the application.
*/
package com.example.ex11061;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreditsPage extends AppCompatActivity {

    /**
    * Called when the activity is first created.
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
        setContentView(R.layout.activity_credits_page);
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
        if (id != R.id.credits) {
            Intent home = new Intent(this, MainActivity.class);
            startActivity(home);
        }
        return super.onOptionsItemSelected(item);
    }
}