package app.bus.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Tab2Act extends Activity
{
    public static boolean updated;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);

        this.onResume();

        Spinner mapComp = (Spinner)findViewById(R.id.mapCompanySel);
        ArrayAdapter<String> adaptMapCom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Front.routeCom);
        mapComp.setAdapter(adaptMapCom);

        Spinner mapRo = (Spinner)findViewById(R.id.mapRouteSel);
        ArrayAdapter<String> adaptMapRo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Front.routeNum);
        mapRo.setAdapter(adaptMapRo);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if(this.updated) {
            this.updated = false;

            EditText addr = (EditText)findViewById(R.id.mapAddEnter);
            addr.setText(Front.destination);
        }
    }
}