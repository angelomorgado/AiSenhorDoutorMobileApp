package pt.ubi.di.pmd.a43855_t5;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class InitialPage extends Activity {

        String id,pass;

        TextView idText;
        TextView passText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            //Irá inicializar a view
            super.onCreate(savedInstanceState);
            setContentView(R.layout.initial_page);
            Intent iCameFromMain = getIntent();
            id = iCameFromMain.getStringExtra("SNS");
            pass = iCameFromMain.getStringExtra("Password");

            idText = (TextView) findViewById(R.id.nSNS);
            passText = (TextView) findViewById(R.id.pass);

            idText.setText(id);
            passText.setText(pass);

        }
}
