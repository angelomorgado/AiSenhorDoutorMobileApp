package pt.ubi.di.pmd.a43855_t5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class InitialPage extends Activity {

        String id,pass;
        int doubleClick;

        Client c;
        DataBase db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            //Irá inicializar a view
            super.onCreate(savedInstanceState);
            setContentView(R.layout.initial_page);
            Intent iCameFromMain = getIntent();
            id = iCameFromMain.getStringExtra("SNS");

            db = DataBase.getInstance(getApplicationContext());
            c = db.myDao().getClientBySNS(Integer.parseInt(id));


        }

        @Override
        public void onBackPressed() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Exit");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                    finish();
                    System.exit(0);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
}
