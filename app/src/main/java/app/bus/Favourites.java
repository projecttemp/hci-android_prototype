package app.bus;

import android.app.Activity;
import android.app.ActivityGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;

public class Favourites extends Activity {

    private static LinearLayout routeB;
    private static View.OnClickListener eve;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        LinearLayout routeA = (LinearLayout)findViewById(R.id.routeA);
        routeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView stop = (TextView) findViewById(R.id.busStops1);
                String ro = stop.getText().toString();
                String[] address = ro.split(" -> ");

                MainActivity.current = address[0];
                MainActivity.destination = address[1];
                MainActivity.startTime = getResources().getStringArray(R.array.A_start);
                MainActivity.endTime = getResources().getStringArray(R.array.A_end);

                MainActivity.startInt = getResources().getString(R.string.startInt);
                MainActivity.endInt = getResources().getString(R.string.endInt_A);
                MainActivity.note = getResources().getString(R.string.notifA);

                Maps.updated = true;
                Schedule.updated = true;

                MainActivity.switchTab(2);
            }
        });

        routeB = (LinearLayout)findViewById(R.id.routeB);
        eve = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView stop = (TextView) findViewById(R.id.busStops2);
                String ro = stop.getText().toString();
                String[] address = ro.split(" -> ");

                MainActivity.current = address[0];
                MainActivity.destination = address[1];
                MainActivity.startTime = getResources().getStringArray(R.array.B_start);
                MainActivity.endTime = getResources().getStringArray(R.array.B_end);

                MainActivity.startInt = getResources().getString(R.string.startInt);
                MainActivity.endInt = getResources().getString(R.string.endInt_B);
                MainActivity.note = getResources().getString(R.string.notifB);

                Maps.updated = true;
                Schedule.updated = true;

                MainActivity.switchTab(2);
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
