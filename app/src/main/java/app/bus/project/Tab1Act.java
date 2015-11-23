package app.bus.project;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Tab1Act  extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);

        LinearLayout routeA = (LinearLayout)findViewById(R.id.routeA);
        routeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView stop = (TextView)findViewById(R.id.busStops1);
                String ro = stop.getText().toString();
                String[] address = ro.split(" -> ");

                Front.current = address[0];
                Front.destination = address[1];

                TextView rn = (TextView)findViewById(R.id.busRouteNum1);
                Front.routeNum = rn.getText().toString();

                TextView com = (TextView)findViewById(R.id.busRouteCom1);
                Front.routeCom = com.getText().toString();
            }
        });

        LinearLayout routeB = (LinearLayout)findViewById(R.id.routeB);
        routeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView stop = (TextView)findViewById(R.id.busStops2);
                String ro = stop.getText().toString();
                String[] address = ro.split(" -> ");

                Front.current = address[0];
                Front.destination = address[1];

                TextView rn = (TextView)findViewById(R.id.busRouteNum2);
                Front.routeNum = rn.getText().toString();

                TextView com = (TextView)findViewById(R.id.busRouteCom2);
                Front.routeCom = com.getText().toString();
            }
        });
    }
}