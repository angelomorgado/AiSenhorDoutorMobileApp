package pt.ubi.di.pmd.a43855_t5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class AppointmentDetails extends Activity {

    String apID;
    DataBase db;
    Appointment currentAp;

    //Components
    TextView typeText;
    TextView doctorText;
    TextView dateText;
    TextView hourText;
    TextView emailText;
    TextView notesText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Irá inicializar a view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        Intent iCameToApdetails = getIntent();
        apID = iCameToApdetails.getStringExtra("SNS");

        db = DataBase.getInstance(getApplicationContext());
        currentAp = db.myDao().getAppointmentByID(Integer.parseInt(apID));

        //Initialize the compontents
        typeText = (TextView) findViewById(R.id.def_typeText);
        doctorText = (TextView) findViewById(R.id.def_doctorText);
        dateText = (TextView) findViewById(R.id.def_dateText);
        hourText = (TextView) findViewById(R.id.def_hourText);
        emailText = (TextView) findViewById(R.id.def_emailText);
        notesText = (TextView) findViewById(R.id.def_notesText);

        //Set the components text
        typeText.setText(currentAp.getType());
        doctorText.setText(currentAp.getMedicResponsable());
        dateText.setText(currentAp.getDay() + "/" + currentAp.getMonth() + "/" + currentAp.getYear());
        hourText.setText(currentAp.getHour());
        emailText.setText(currentAp.getEmail());
        notesText.setText(currentAp.getNotes());

        
    }

    @Override
    public void onBackPressed() {
        Intent DetailstoList = new Intent(this, AppointmentList.class);
        DetailstoList.putExtra("SNS", String.valueOf(apID));
        finish();
        this.overridePendingTransition(0, 0);
        startActivity(DetailstoList);
    }
}
