package app.bus.project;

import android.os.Bundle;
import android.content.Intent;
import android.app.ActivityGroup;
import android.widget.TabHost.*;
import android.widget.TabHost;


public class Front extends ActivityGroup {
    public static String current, destination;
    public static String[] routeNum, routeCom;

    private static TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        current = "Current Address";
        destination = "Destination";

        routeCom = new String[] {"DRT", "YRT", "TTC", "GO"};
        routeNum = new String[] {"401", "402", "403", "404", "405", "406", "407", "408", "409"};

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        //tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.setup(this.getLocalActivityManager());

        TabSpec tab1 = tabHost.newTabSpec("Tab One");
        TabSpec tab2 = tabHost.newTabSpec("Tab Two");
        TabSpec tab3 = tabHost.newTabSpec("Tab Three");

        tab1.setIndicator("Favorite");
        tab2.setIndicator("Map");
        tab3.setIndicator("Schedule");

        tab1.setContent(new Intent(this, Tab1Act.class));
        tab2.setContent(new Intent(this, Tab2Act.class));
        tab3.setContent(new Intent(this, Tab3Act.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
    }

    public static void switchTab(int tab) {
        tabHost.setCurrentTab(tab);
    }
}
