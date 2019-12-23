package com.geektech.taskapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.geektech.taskapp.onboard.OnBoardActivity;
import com.geektech.taskapp.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import java.util.prefs.Preferences;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    TextView name;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isShown = preferences.getBoolean("isShown", false);

        if (!isShown) {
            startActivity(new Intent(this, OnBoardActivity.class));
            finish();
            return;

        }

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, PhoneActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent, 1);

            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        SharedPreferences sharedPreferences = getSharedPreferences("nav_settings", MODE_PRIVATE);
        name = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);

        sharedPreferences.getString("name", "");
        sharedPreferences.getString("email", "");

        View header = navigationView.getHeaderView(0);




        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //        initFile();
    }


//    private void startCheckAnimation(){
//        ValueAnimator animator = ValueAnimator.ofFloat()
//    }


    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//
//        return true;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort:
                Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                assert navHostFragment != null;
                ((HomeFragment) navHostFragment.getChildFragmentManager().getFragments().get(0)).sortList();
                return true;
            case R.id.signOut:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Выход из аккаунта");
                builder.setMessage("Вы хотите выйти из аккаунта?");


                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        finish();
                    }
                });
                builder.setNegativeButton("Нет", null);


                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        return true;
//    }


   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        fragment.getChildFragmentManager().getFragments().get(0).onActivityResult(requestCode, resultCode, data);

    }*/

//    @AfterPermissionGranted(101)
//    private void initFile() {
//
//        String[] permissions;
//        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//
//        File folder = new File(Environment.getExternalStorageDirectory(),"TaskApp");
//        folder.mkdirs();
//
//
//        File file = new File(folder, "note.txt");
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            File imageFile = new File(folder, "image.png");
//            downloadFile(imageFile);
//
//
//        }else {
//        EasyPermissions.requestPermissions(this, "Разрешить?", 101,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//    }
//
//
//}
//
//private void downloadFile(final File imageFile){
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                    try {
//                    URL url = new URL("https://square.github.io/picasso/static/sample.png");
//                    FileUtils.copyURLToFile(url, imageFile);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//        thread.start();
//}
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        // Forward results to EasyPermissions
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
}


