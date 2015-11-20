package app.bus.project;

import android.os.Bundle;
import android.content.Intent;
import android.app.ActivityGroup;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost.*;
import android.widget.TabHost;


public class Front extends ActivityGroup {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
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
}