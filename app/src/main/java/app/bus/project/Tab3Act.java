package app.bus.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Tab3Act  extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);

        EditText from = (EditText)findViewById(R.id.schFromEnter);
        from.setText(Front.current);

        EditText to = (EditText)findViewById(R.id.schToEnter);
        to.setText(Front.destination);

        Spinner schComp = (Spinner)findViewById(R.id.schCompanySel);
        ArrayAdapter<String> adaptSchCom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Front.routeCom);
        schComp.setAdapter(adaptSchCom);

        Spinner schRo = (Spinner)findViewById(R.id.schRouteSel);
        ArrayAdapter<String> adaptSchRo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Front.routeNum);
        schRo.setAdapter(adaptSchRo);
    }
}
