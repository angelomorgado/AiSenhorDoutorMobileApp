package pt.ubi.di.pmd.a43855_t5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;

public class AppointmentList extends Activity {

    String id;

    ImageButton list_settings_button;
    ImageButton list_add_button;
    ImageButton list_home_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Irá inicializar a view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_list);
        Intent iCameToList = getIntent();
        id = iCameToList.getStringExtra("SNS");

        //Setup the menu buttons
        list_settings_button = (ImageButton) findViewById(R.id.list_settingsButton);
        list_home_button = (ImageButton) findViewById(R.id.list_homeButton);
        list_add_button = (ImageButton) findViewById(R.id.list_addButton);

        list_add_button.setOnClickListener(
                oView -> {
                    Intent ListToAdd = new Intent(this, AddAppointment.class);
                    ListToAdd.putExtra("SNS", id);
                    finish();
                    this.overridePendingTransition(0, 0);
                    startActivity(ListToAdd);
                }
        );
        list_settings_button.setOnClickListener(
                oView -> {
                    Intent ListToSettings = new Intent(this, SettingsPage.class);
                    ListToSettings.putExtra("SNS", id);
                    finish();
                    this.overridePendingTransition(0, 0);
                    startActivity(ListToSettings);
                }
        );
        list_home_button.setOnClickListener(
                oView -> {
                    Intent ListToHome = new Intent(this, InitialPage.class);
                    ListToHome.putExtra("SNS", id);
                    finish();
                    this.overridePendingTransition(0, 0);
                    startActivity(ListToHome);
                }
        );
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Log-out");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", (dialog, which) -> {
            // Do nothing but close the dialog
            finish();
            System.exit(0);
            dialog.dismiss();
        });
        builder.setNegativeButton("NO", (dialog, which) -> {

            // Do nothing
            dialog.dismiss();
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}