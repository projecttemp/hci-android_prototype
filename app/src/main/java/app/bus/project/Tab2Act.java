package app.bus.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

public class Tab2Act  extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);

        EditText addr = (EditText)findViewById(R.id.mapAddEnter);
        addr.setText(Front.current);
    }
}