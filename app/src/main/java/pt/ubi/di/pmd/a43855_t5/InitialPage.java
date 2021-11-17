package pt.ubi.di.pmd.a43855_t5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class InitialPage extends Activity {

        String id;
        Client c;
        DataBase db;
        List<Appointment> list;

        //XML objects
        TextView welcomeMessage;
        ImageButton home_list_button;
        ImageButton home_add_button;
        ImageButton home_settings_button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            //Irá inicializar a view
            super.onCreate(savedInstanceState);
            setContentView(R.layout.initial_page);
            Intent iCameFromMain = getIntent();
            id = iCameFromMain.getStringExtra("SNS");

            //Setup the database
            db = DataBase.getInstance(getApplicationContext());
            c = db.myDao().getClientBySNS(Integer.parseInt(id));

            //Setup the appointments list
            list = db.myDao().getAppointmentsBySNS(c.getSNS());

            //Update the title
            welcomeMessage = (TextView) findViewById(R.id.welcomeText);
            String str = getResources().getString(R.string.home_welcome_en, c.getName());
            welcomeMessage.setText(str);

            //Setup the menu buttons
            home_list_button = (ImageButton) findViewById(R.id.home_appointmentsButton);
            home_settings_button = (ImageButton) findViewById(R.id.home_settingsButton);
            home_add_button = (ImageButton) findViewById(R.id.home_addButton);

            home_add_button.setOnClickListener(
                    oView -> {
                        Intent HomeToAdd = new Intent(this, AddAppointment.class);
                        HomeToAdd.putExtra("SNS", id);
                        finish();
                        this.overridePendingTransition(0, 0);
                        startActivity(HomeToAdd);
                    }
            );
            home_list_button.setOnClickListener(
                    oView -> {
                        Intent HomeToList = new Intent(this, AppointmentList.class);
                        HomeToList.putExtra("SNS", id);
                        finish();
                        this.overridePendingTransition(0, 0);
                        startActivity(HomeToList);
                    }
            );
            home_settings_button.setOnClickListener(
                    oView -> {
                        Intent HomeToSettings = new Intent(this, SettingsPage.class);
                        HomeToSettings.putExtra("SNS", id);
                        finish();
                        this.overridePendingTransition(0, 0);
                        startActivity(HomeToSettings);
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
