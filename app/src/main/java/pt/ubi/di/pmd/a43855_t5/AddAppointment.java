package pt.ubi.di.pmd.a43855_t5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;

public class AddAppointment extends Activity {

    String id;

    ImageButton add_settings_button;
    ImageButton add_list_button;
    ImageButton add_home_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Irá inicializar a view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment);
        Intent iCameToAdd = getIntent();
        id = iCameToAdd.getStringExtra("SNS");

        //Setup the menu buttons
        add_settings_button = (ImageButton) findViewById(R.id.add_settingsButton);
        add_home_button = (ImageButton) findViewById(R.id.add_homeButton);
        add_list_button = (ImageButton) findViewById(R.id.add_appointmentsButton);

        add_list_button.setOnClickListener(
                oView -> {
                    Intent AddToList = new Intent(this, AppointmentList.class);
                    AddToList.putExtra("SNS", id);
                    finish();
                    this.overridePendingTransition(0, 0);
                    startActivity(AddToList);
                }
        );
        add_settings_button.setOnClickListener(
                oView -> {
                    Intent AddToSettings = new Intent(this, SettingsPage.class);
                    AddToSettings.putExtra("SNS", id);
                    finish();
                    this.overridePendingTransition(0, 0);
                    startActivity(AddToSettings);
                }
        );
        add_home_button.setOnClickListener(
                oView -> {
                    Intent AddToHome = new Intent(this, InitialPage.class);
                    AddToHome.putExtra("SNS", id);
                    finish();
                    this.overridePendingTransition(0, 0);
                    startActivity(AddToHome);
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
