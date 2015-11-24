package app.bus.project;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Tab3Act extends Activity
{
    public static boolean updated;
    private LinearLayout timeList;
    private LinearLayout.LayoutParams listParam;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);

        this.onResume();

        Spinner spinCompany = (Spinner) findViewById(R.id.schCompanySel);
        ArrayAdapter<String> adpCompany = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Front.routeCom);
        spinCompany.setAdapter(adpCompany);
        spinCompany.setSelection(0);

        Spinner spinRoute = (Spinner) findViewById(R.id.schRouteSel);
        ArrayAdapter<String> adpRoute = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Front.routeNum);
        spinRoute.setAdapter(adpRoute);
        spinRoute.setSelection(0);

        Button favButton = (Button) findViewById(R.id.schBtnFav);
        View.OnClickListener favEve = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tab1Act.enableB();
                Front.switchTab(0);
            }
        };
        favButton.setOnClickListener(favEve);

        Button timeButton = (Button) findViewById(R.id.schBtnTime);
        View.OnClickListener timeEve = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker;
                datePicker = new DatePickerDialog(Tab3Act.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth++;
                        System.out.println("Date: " + selectedday + ", " + selectedmonth + "/" + selectedyear);
                    }
                }, year, month, day);
                datePicker.setTitle("Select the Date");
                datePicker.show();
            }
        };
        timeButton.setOnClickListener(timeEve);

        ScrollView timeListScroll = (ScrollView) findViewById(R.id.schTimeList);
        ScrollView.LayoutParams scrollParam = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        timeList = new LinearLayout(this);
        timeList.setLayoutParams(scrollParam);
        timeList.setOrientation(LinearLayout.VERTICAL);
        listParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);

        timeListScroll.addView(timeList);
    }

    private LinearLayout getView(String left, String right, LinearLayout.LayoutParams param)
    {
        LinearLayout lay = new LinearLayout(this);
        lay.setOrientation(LinearLayout.HORIZONTAL);
        lay.setLayoutParams(param);

        LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0.5f);

        TextView le = new TextView(this);
        TextView ri = new TextView(this);
        le.setText(left);
        ri.setText(right);
        le.setLayoutParams(textParam);
        ri.setLayoutParams(textParam);
        le.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ri.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        le.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        ri.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        lay.addView(le);
        lay.addView(ri);

        return lay;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if(this.updated) {
            this.updated = false;
            timeList.removeAllViews();
            EditText from = (EditText)findViewById(R.id.schFromEnter);
            from.setText(Front.current);

            EditText to = (EditText)findViewById(R.id.schToEnter);
            to.setText(Front.destination);

            for(int t = 0; t < Front.startTime.length; t++){
                timeList.addView(getView(Front.startTime[t], Front.endTime[t], listParam));
            }

            TextView intF = (TextView)findViewById(R.id.schTextFrom);
            intF.setText(Front.startInt);

            TextView intT = (TextView)findViewById(R.id.schTextTo);
            intT.setText(Front.endInt);

            TextView alert = (TextView)findViewById(R.id.textView3);
            alert.setText(Front.note);
        }
    }
}
