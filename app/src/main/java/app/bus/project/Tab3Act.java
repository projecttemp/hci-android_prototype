package app.bus.project;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Tab3Act extends Activity
{
    public static boolean updated;

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

        ScrollView timeListScroll = (ScrollView) findViewById(R.id.schTimeList);
        ScrollView.LayoutParams scrollParam = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout timeList = new LinearLayout(this);
        timeList.setLayoutParams(scrollParam);
        timeList.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams listParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);

        timeList.addView(getView("8:00 AM", "8:15 AM", listParam));
        timeList.addView(getView("8:05 AM", "8:20 AM", listParam));
        timeList.addView(getView("8:10 AM", "8:25 AM", listParam));
        timeList.addView(getView("8:15 AM", "8:30 AM", listParam));
        timeList.addView(getView("8:20 AM", "8:35 AM", listParam));
        timeList.addView(getView("8:25 AM", "8:40 AM", listParam));
        timeList.addView(getView("8:30 AM", "8:45 AM", listParam));
        timeList.addView(getView("8:35 AM", "8:50 AM", listParam));
        timeList.addView(getView("8:40 AM", "8:55 AM", listParam));
        timeList.addView(getView("8:45 AM", "9:00 AM", listParam));
        timeList.addView(getView("8:50 AM", "9:05 AM", listParam));
        timeList.addView(getView("8:55 AM", "9:10 AM", listParam));

        timeList.addView(getView("9:00 AM", "9:15 AM", listParam));
        timeList.addView(getView("9:10 AM", "9:25 AM", listParam));
        timeList.addView(getView("9:20 AM", "9:35 AM", listParam));
        timeList.addView(getView("9:30 AM", "9:45 AM", listParam));
        timeList.addView(getView("9:40 AM", "9:55 AM", listParam));
        timeList.addView(getView("9:50 AM", "10:05 AM", listParam));

        timeList.addView(getView("10:00 AM", "10:15 AM", listParam));
        timeList.addView(getView("10:20 AM", "10:35 AM", listParam));
        timeList.addView(getView("10:40 AM", "10:55 AM", listParam));

        timeList.addView(getView("11:00 AM", "11:15 AM", listParam));
        timeList.addView(getView("11:20 AM", "11:35 AM", listParam));
        timeList.addView(getView("11:40 AM", "11:55 AM", listParam));

        timeList.addView(getView("12:00 PM", "12:15 PM", listParam));

        timeList.addView(getView("Frequent", "Service", listParam));

        timeList.addView(getView("2:30 PM", "2:45 PM", listParam));

        timeList.addView(getView("3:00 PM", "3:15 PM", listParam));
        timeList.addView(getView("3:30 PM", "3:45 PM", listParam));

        timeList.addView(getView("4:00 PM", "4:15 PM", listParam));
        timeList.addView(getView("4:30 PM", "4:45 PM", listParam));

        timeList.addView(getView("5:00 PM", "5:15 PM", listParam));
        timeList.addView(getView("5:10 PM", "5:25 PM", listParam));
        timeList.addView(getView("5:20 PM", "5:35 PM", listParam));
        timeList.addView(getView("5:30 PM", "5:45 PM", listParam));
        timeList.addView(getView("5:40 PM", "5:55 PM", listParam));
        timeList.addView(getView("5:50 PM", "6:05 PM", listParam));

        timeList.addView(getView("6:00 PM", "6:15 PM", listParam));

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
            EditText from = (EditText)findViewById(R.id.schFromEnter);
            from.setText(Front.current);

            EditText to = (EditText)findViewById(R.id.schToEnter);
            to.setText(Front.destination);
        }
    }
}
