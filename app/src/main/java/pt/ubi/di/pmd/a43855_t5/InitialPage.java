package pt.ubi.di.pmd.a43855_t5;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

    public class InitialPage extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            //Irá inicializar a view
            super.onCreate(savedInstanceState);
            setContentView(R.layout.initial_page);
            Intent iCameFromMain = getIntent();
        }
}
