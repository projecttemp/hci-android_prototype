package app.bus.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
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
    }
}
