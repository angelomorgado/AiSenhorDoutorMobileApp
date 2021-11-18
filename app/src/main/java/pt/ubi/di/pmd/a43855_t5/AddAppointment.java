package pt.ubi.di.pmd.a43855_t5;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;

import java.util.Calendar;


public class AddAppointment extends Activity {

    String id;

    Button confirm;
    ImageButton add_settings_button;
    ImageButton add_list_button;
    ImageButton add_home_button;
    Button hourButton;
    Spinner apType;
    Spinner doctorName;
    EditText email;
    EditText apNote;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private int year,month,day, hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Irá inicializar a view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment);
        Intent iCameToAdd = getIntent();
        id = iCameToAdd.getStringExtra("SNS");

        //Initialize the components
        confirm = (Button) findViewById(R.id.confirm);
        apType = (Spinner) findViewById(R.id.add_typeSpinner);
        doctorName = (Spinner) findViewById(R.id.add_doctorSpinner);
        email = (EditText) findViewById(R.id.contactEmail);
        apNote = (EditText) findViewById(R.id.apNotes);
        hourButton = (Button) findViewById(R.id.hourButton);
        dateButton = (Button) findViewById(R.id.dateButton);
        add_settings_button = (ImageButton) findViewById(R.id.add_settingsButton);
        add_home_button = (ImageButton) findViewById(R.id.add_homeButton);
        add_list_button = (ImageButton) findViewById(R.id.add_appointmentsButton);


        //Setup the doctor name spinner + type spinner
        //Create the types
        String[] typesList={"General consultation","COVID-19 test","Blood exam","Eye exam","Ear exam (Otoscopy)","Cardiac exam","Prostate Exam","Endoscopy"};
        String[] generalDoctors = {"Dr.Ângelo Morgado","Dr.Rita Quelhas"};
        String[] bloodDoctors = {"Dr.Rita Quelhas","Dr.Artur Canário","Dr.André Garcia"};
        String[] eyeDoctors = {"Dr.Diogo Correia","Dr.Manuel Morais"};
        String[] earDoctors = {"Dr.Gonçalo Simões","Dr.Beatriz Nave"};
        String[] cardiacDoctors = {"Dr.Luís Sena","Dr. Inês Carrilho"};
        String[] prostateDoctors = {"Dr.Johnny Sins","Dr.Quim Sirenes"};
        String[] endoscopyDoctors = {"Dr.Joana Morais","Dr.Ângelo Morgado"};

        //check the type spinner
        apType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] selectedList;
                switch (position)
                {
                    case 0:
                    case 1:
                        selectedList = generalDoctors;
                        break;
                    case 2:
                        selectedList = bloodDoctors;
                        break;
                    case 3:
                        selectedList = eyeDoctors;
                        break;
                    case 4:
                        selectedList = earDoctors;
                        break;
                    case 5:
                        selectedList = cardiacDoctors;
                        break;
                    case 6:
                        selectedList = prostateDoctors;
                        break;
                    case 7:
                        selectedList = endoscopyDoctors;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }

                ArrayAdapter<String> doctorAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, selectedList);
                doctorName.setAdapter(doctorAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });





        //Setup the hour
        hourButton.setOnClickListener(
                oView -> showHour()
        );

        //Setup the date
        initDatePicker();
        dateButton.setText(getTodaysDate());

        dateButton.setOnClickListener(
                oView -> datePickerDialog.show()
        );


        //Confirmation Buttons
        confirm.setOnClickListener(
                oView -> {
                    year = datePickerDialog.getDatePicker().getYear();
                    month = datePickerDialog.getDatePicker().getMonth() + 1;
                    day = datePickerDialog.getDatePicker().getDayOfMonth();
                    hour = Integer.parseInt((String) hourButton.getText());
                    System.out.println("Year: " + year + " | Month: " + month +" | Day: " + day + " | Hour: " + hour);
                }
        );



        //Setup the menu buttons
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

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month++;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month, year);
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

    //===================Date Methods==================
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this,dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    //============================== Hour Methods ===========================================
    public void showHour()
    {
        //Creates the dialog and shows it
        final Dialog d = new Dialog(AddAppointment.this);
        d.setTitle("Pick an hour:");
        d.setContentView(R.layout.hour_dialog);

        Button setHour = (Button) d.findViewById(R.id.setHour);
        Button cancelHour = (Button) d.findViewById(R.id.cancelHour);

        //gets the number picker and changes it
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(19);
        np.setMinValue(8);
        np.setWrapSelectorWheel(false);

        //Changes the value of the button on confirm
        setHour.setOnClickListener(v -> {
            hourButton.setText(String.valueOf(np.getValue()));
            d.dismiss();
        });

        //Simply closes the app
        cancelHour.setOnClickListener(v -> d.dismiss());

        //Shows the dialog
        d.show();
    }
}