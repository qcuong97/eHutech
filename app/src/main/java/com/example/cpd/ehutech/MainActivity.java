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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.cpd.ehutech.model.SV5T.GetTTinTChiSV5T;
import com.example.cpd.ehutech.model.SV5T.Row;
import com.example.cpd.ehutech.remote.ApiUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

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
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        ActionViewFilpper();
        KiemTraChuKy();
    }

    private void ActionViewFilpper() {
        ArrayList<String> mangtintuc = new ArrayList<>();
        mangtintuc.add("https://www.hutech.edu.vn/img/slider/mhxv2_1000x365-1527662826.png");
        mangtintuc.add("https://www.hutech.edu.vn/img/slider/cover_doan_1000x365-1523948481.png");
        mangtintuc.add("https://www.hutech.edu.vn/img/slider/08_998x280_pc-1517879915.png");
        mangtintuc.add("https://www.hutech.edu.vn/img/slider/09_998x280_pc-1517880235.png");
        mangtintuc.add("https://www.hutech.edu.vn/img/slider/10_998x280_pc-1517879955.png");
        mangtintuc.add("https://www.hutech.edu.vn/img/slider/11_998x280_pc-1517879973.png");
        mangtintuc.add("https://www.hutech.edu.vn/img/slider/12_998x280_pc-1517879993.png");
        for (int i=0; i< mangtintuc.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
                    Picasso.with(getApplicationContext()).load(mangtintuc.get(i)).into(imageView);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(6000);
        viewFlipper.setAutoStart(true);
        Animation anim_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation anim_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out);
        viewFlipper.setInAnimation(anim_slide_in);
        viewFlipper.setOutAnimation(anim_slide_out);
    }
    private void Anhxa(){
        intent = getIntent();
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper_main);
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
                    if(response.body().getCode() == 500) {
                        Toast.makeText(MainActivity.this, "Đã Xảy Ra Lỗi!!Hãy Thử Đăng Nhập Lại", Toast.LENGTH_SHORT).show();
                        cancel_sharedPre();
                    }
                    Gson gson = new Gson();
                    String sendOject = gson.toJson(row);
                    intent = new Intent(MainActivity.this, SV5TActivity.class);
                    intent.putExtra("InfoSV5T",sendOject);
                    startActivity(intent);
                } else {
                    if(response.body().getCode() == 500) {
                        Toast.makeText(MainActivity.this, "Đã Xảy Ra Lỗi!!Hãy Thử Đăng Nhập Lại", Toast.LENGTH_SHORT).show();
                        cancel_sharedPre();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTTinTChiSV5T> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Đã Xảy Ra Lỗi!!Hãy Thử Đăng Nhập Lại", Toast.LENGTH_SHORT).show();
                cancel_sharedPre();
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
    void KiemTraChuKy(){
        a.sharedPreferences = getSharedPreferences(a.MyPREFERENCES, Context.MODE_PRIVATE);
        String chuky = a.sharedPreferences.getString(a.ChuKy,"");
        if (chuky == ""){
            thongbao_chuky();
        }
    }
    void thongbao_chuky(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intent = new Intent(MainActivity.this,DrawSignatureActivity.class);
                        startActivity(intent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.cancel();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn Chưa Có Chữ Ký!");
        builder.setMessage("Hãy Thêm Chữ Ký").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
    class SV5T extends LoginActivity{
    }
}
