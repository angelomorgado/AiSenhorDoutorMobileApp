package pt.ubi.di.pmd.a43855_t5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class AppointmentDetails extends Activity {

    String apID;
    DataBase db;
    Appointment currentAp;
    Client c;

    //Components
    TextView welcomeText;
    TextView typeText;
    TextView doctorText;
    TextView patientText;
    TextView dateText;
    TextView hourText;
    TextView emailText;
    TextView notesText;
    Button cancelButton;
    Button reportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Irá inicializar a view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_details);
        Intent iCameToApdetails = getIntent();
        apID = iCameToApdetails.getStringExtra("SNS");

        db = DataBase.getInstance(getApplicationContext());
        currentAp = db.myDao().getAppointmentByID(Integer.parseInt(apID));
        c = db.myDao().getClientBySNS(currentAp.getSNS());


        //Initialize the compontents
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        typeText = (TextView) findViewById(R.id.def_typeText);
        doctorText = (TextView) findViewById(R.id.def_doctorText);
        patientText = (TextView) findViewById(R.id.def_patientText);
        dateText = (TextView) findViewById(R.id.def_dateText);
        hourText = (TextView) findViewById(R.id.def_hourText);
        emailText = (TextView) findViewById(R.id.def_emailText);
        notesText = (TextView) findViewById(R.id.def_notesText);
        reportButton = (Button) findViewById(R.id.det_reportButton);
        cancelButton = (Button) findViewById(R.id.det_cancelButton);

        //Set the welcome text
        String str = getResources().getString(R.string.appointment_welcome_en, currentAp.getIDappointment());
        welcomeText.setText(str);

        //Set the components text
        typeText.setText(currentAp.getType());
        doctorText.setText(currentAp.getMedicResponsable());
        patientText.setText(c.getName());
        dateText.setText(currentAp.getDay() + "/" + currentAp.getMonth() + "/" + currentAp.getYear());
        hourText.setText(currentAp.getHour() + "");
        if(!currentAp.getEmail().isEmpty())
            emailText.setText(currentAp.getEmail());
        else
            emailText.setText(c.getEmail());
        notesText.setText(currentAp.getNotes());


        //Button listeners
        cancelButton.setOnClickListener(
                oView ->{
                    Intent DetailstoList = new Intent(this, AppointmentList.class);
                    DetailstoList.putExtra("SNS", String.valueOf(c.getSNS()));
                    finish();
                    this.overridePendingTransition(0, 0);
                    startActivity(DetailstoList);
                }
        );
    }

    @Override
    public void onBackPressed() {
        Intent DetailstoList = new Intent(this, AppointmentList.class);
        DetailstoList.putExtra("SNS", String.valueOf(c.getSNS()));
        finish();
        this.overridePendingTransition(0, 0);
        startActivity(DetailstoList);
    }
}
