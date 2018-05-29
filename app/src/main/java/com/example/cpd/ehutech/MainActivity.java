package com.example.cpd.ehutech;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cpd.ehutech.model.SV5T.GetTTinTChiSV5T;
import com.example.cpd.ehutech.model.SV5T.Row;
import com.example.cpd.ehutech.remote.ApiUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Intent intent;
    SV5T a = new SV5T();
    Row row = new Row();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();

    }
    private void Anhxa(){
        intent = getIntent();
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        a.sharedPreferences = getSharedPreferences(a.MyPREFERENCES, Context.MODE_PRIVATE);
        TextView txt_ten = (TextView) headerView.findViewById(R.id.txt_ten);
        txt_ten.setText(a.sharedPreferences.getString(a.Ten,""));
        TextView txt_lop = (TextView) headerView.findViewById(R.id.txt_lop);
        txt_lop.setText(a.sharedPreferences.getString(a.Lop,""));
        TextView txt_khoa = (TextView) headerView.findViewById(R.id.txt_khoa);
        txt_khoa.setText(a.sharedPreferences.getString(a.Khoa,""));
        /*-----------Toolbar cai tren đầu-------------*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hutech - University");
        /*-------------Floatting Action------*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DrawSignatureActivity.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_sv5t) {
            doGetInfoTChiSV5T();
        } else if (id == R.id.nav_lhtt) {

        } else if (id == R.id.nav_nckh) {

        } else  if (id == R.id.nav_help) {
        } else  if (id == R.id.nav_logout){
            onClicked_logout();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void doGetInfoTChiSV5T() {
        a.sharedPreferences = getSharedPreferences(a.MyPREFERENCES, Context.MODE_PRIVATE);
        String token = "Bearer " + a.sharedPreferences.getString(a.Token, "");
        String mssv = a.sharedPreferences.getString(a.MSSV, "");
        a.apiService = ApiUtils.getUserService();
        Call<GetTTinTChiSV5T> call = a.apiService.getTTinTChiSV5T(mssv, token);
        call.enqueue(new Callback<GetTTinTChiSV5T>() {
            @Override
            public void onResponse(Call<GetTTinTChiSV5T> call, Response<GetTTinTChiSV5T> response) {
                if (response.isSuccessful()) {
                    row = response.body().getResults().getObject().getRows().get(0);
                    Gson gson = new Gson();
                    String sendOject = gson.toJson(row);
                    intent = new Intent(MainActivity.this, SV5TActivity.class);
                    intent.putExtra("InfoSV5T",sendOject);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Đã Xảy Ra Lỗi!!Hãy Thử Lại!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetTTinTChiSV5T> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ket Noi Loi", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClicked_logout(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        cancel_sharedPre();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.cancel();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn Có Thật Sự Muốn Đăng Xuất?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
    public  void cancel_sharedPre(){
        a.sharedPreferences = getSharedPreferences(a.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = a.sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent;
        intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    class SV5T extends LoginActivity{
    }
}
