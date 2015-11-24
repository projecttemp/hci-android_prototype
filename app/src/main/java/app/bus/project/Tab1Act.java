package app.bus.project;

import android.app.Activity;
import android.app.ActivityGroup;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class Tab1Act extends Activity
{
    private static LinearLayout routeB;
    private static View.OnClickListener eve;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);

        LinearLayout routeA = (LinearLayout)findViewById(R.id.routeA);
        routeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView stop = (TextView) findViewById(R.id.busStops1);
                String ro = stop.getText().toString();
                String[] address = ro.split(" -> ");

                Front.current = address[0];
                Front.destination = address[1];
                Front.startTime = getResources().getStringArray(R.array.A_start);
                Front.endTime = getResources().getStringArray(R.array.A_end);

                Front.startInt = getResources().getString(R.string.startInt);
                Front.endInt = getResources().getString(R.string.endInt_A);
                Front.note = getResources().getString(R.string.notifA);

                Tab2Act.updated = true;
                Tab3Act.updated = true;

                Front.switchTab(2);
            }
        });

        routeB = (LinearLayout)findViewById(R.id.routeB);
        eve = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView stop = (TextView) findViewById(R.id.busStops2);
                String ro = stop.getText().toString();
                String[] address = ro.split(" -> ");

                Front.current = address[0];
                Front.destination = address[1];
                Front.startTime = getResources().getStringArray(R.array.B_start);
                Front.endTime = getResources().getStringArray(R.array.B_end);

                Front.startInt = getResources().getString(R.string.startInt);
                Front.endInt = getResources().getString(R.string.endInt_B);
                Front.note = getResources().getString(R.string.notifB);

                Tab2Act.updated = true;
                Tab3Act.updated = true;

                Front.switchTab(2);
            }
        };
        disableB();

        Button favRemove = (Button) findViewById(R.id.favBtnRemove2);
        View.OnClickListener btnEve = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableB();
            }
        };
        favRemove.setOnClickListener(btnEve);
    }

    public static void disableB() {
        routeB.setVisibility(View.INVISIBLE);
        routeB.setOnClickListener(null);
    }

    public static void enableB() {
        routeB.setVisibility(View.VISIBLE);
        routeB.setOnClickListener(eve);
    }
}