package app.bus;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends ActivityGroup {

    public static String current, destination, startInt, endInt, note;
    public static String[] routeNum, routeCom, startTime, endTime;

    public static TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        current = "Current Address";
        destination = "Destination";

        routeCom = new String[] {"DRT", "YRT", "TTC", "GO"};
        routeNum = new String[] {"401", "402", "403", "404", "405", "406", "407", "408", "409"};

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this.getLocalActivityManager());

        TabHost.TabSpec fave = tabHost.newTabSpec("Favourites");
        TabHost.TabSpec maps = tabHost.newTabSpec("Maps");
        TabHost.TabSpec sche = tabHost.newTabSpec("Schedule");

        fave.setIndicator("Favorite");
        maps.setIndicator("Map");
        sche.setIndicator("Schedule");

        fave.setContent(new Intent(this, Favourites.class));
        maps.setContent(new Intent(this, Maps.class));
        sche.setContent(new Intent(this, Schedule.class));

        tabHost.addTab(fave);
        tabHost.addTab(maps);
        tabHost.addTab(sche);
    }

    public static void switchTab(int tab) {
        tabHost.setCurrentTab(tab);
    }


}
