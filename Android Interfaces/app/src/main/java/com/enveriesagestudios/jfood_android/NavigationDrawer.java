package com.enveriesagestudios.jfood_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enveriesagestudios.jfood_android.activity.LoginActivity;
import com.enveriesagestudios.jfood_android.activity.SelesaiPesananActivity;
import com.enveriesagestudios.jfood_android.ui.gallery.GalleryFragment;
import com.enveriesagestudios.jfood_android.ui.gallery.GalleryViewModel;
import com.enveriesagestudios.jfood_android.utility.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Navigation Drawer Class
 * Description : This Class is used to display Navigation drawer or being used as Main Activity / Home Activity in this Project
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class NavigationDrawer extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    private int currentInvoiceID;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Replace Default Fragment
        setContentView(R.layout.activity_navigation_drawer);
        //Getting the customer Data
        Intent currentUserandInvoiceIntent = getIntent();
        //Session Manager
        session = new SessionManager(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);

        //Drawing Navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Plauing with Navigation
        updateNavHeader();

        //Getting the button
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showSignoutDialog();
             }
         });

            //navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //Navigation Header View Update
    //Updating Navbar Header MVVM
    public void updateNavHeader() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View nav_header = navigationView.getHeaderView(0);
        TextView navUsername =  nav_header.findViewById(R.id.nav_userName);
        TextView navUserEmail =  nav_header.findViewById(R.id.nav_userEmail);
        ImageView navUserPhoto = nav_header.findViewById(R.id.nav_userPhoto);

        navUserEmail.setText(session.getUserEmail());
        navUsername.setText(session.getUserName());

        //Glide to Photo Profile
        //if(sessiongetPhotoUrl()!= null) {
        //    Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);
        //}
    }

    //Sigout Popup
    private void showSignoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?");
        LinearLayout linearLayout = new LinearLayout(this);
        final TextView confirmationSignout =new TextView(this);
        linearLayout.addView(confirmationSignout);
        builder.setView(linearLayout);

        //Recover Button
        builder.setPositiveButton("Signout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                session.logoutUser();
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivity);
                finish();
            }
        });

        //Cancel Button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
