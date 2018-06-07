package com.example.cpd.ehutech;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cpd.ehutech.model.ChuKy.UploadChuKy;
import com.example.cpd.ehutech.remote.ApiUtils;
import com.example.cpd.ehutech.service.APIService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawSignatureActivity extends AppCompatActivity implements MediaStore.Images.ImageColumns{
    Button buttonnew;
    Button buttonsave;
    DrawingView drawView;
    Drawing extent = new Drawing();
    String mssv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_signature);
        Anhxa();
        KyLai();
        LuuChyKy();
    }
void Anhxa(){
    buttonnew = findViewById(R.id.btn_new);
    buttonsave = findViewById(R.id.btn_save);
    drawView = findViewById(R.id.drawing);
}
void KyLai(){
    buttonnew.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //new button
            AlertDialog.Builder newDialog = new AlertDialog.Builder(DrawSignatureActivity.this);
            newDialog.setTitle("Ký Tên");
            newDialog.setMessage("Bạn Muốn Ký Lại Tên?");
            newDialog.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            newDialog.show();
        }
    });
}
void LuuChyKy(){
    buttonsave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final String url = "/storage/emulated/0/Pictures/";
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(DrawSignatureActivity.this);
            saveDialog.setTitle("Ký Tên");
            saveDialog.setMessage("Lưu Chữ Ký?"+ "\n" + "Vui Lòng Giữ Chữ Ký Để Đối Chiếu Nếu Cần Thiết");
            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    //save drawing
                    if(saveImage()){
                        upload_chuky();
                    }
                    else{
                        Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                "Oops! Lưu Thất Bại.", Toast.LENGTH_SHORT);
                        unsavedToast.show();
                    }
                    drawView.startNew();
                }
            });
            saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            saveDialog.show();
        }
    });
}
public Boolean saveImage(){
    drawView.setDrawingCacheEnabled(true);
    extent.sharedPreferences = getSharedPreferences(extent.MyPREFERENCES, Context.MODE_PRIVATE);
    mssv = extent.sharedPreferences.getString(extent.MSSV,"");
    Bitmap b = drawView.getDrawingCache();
    FileOutputStream fos = null;
    try {
        fos = new FileOutputStream("/storage/emulated/0/Pictures/"+mssv+".png");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        return false;
    }
    b.compress(Bitmap.CompressFormat.PNG, 95, fos);
    return true;
}
private void upload_chuky(){
    File file = new File("/storage/emulated/0/Pictures/"+mssv+".png");
    RequestBody requestFile =
            RequestBody.create(MediaType.parse("image/*"), file);
// MultipartBody.Part is used to send also the actual file name
    MultipartBody.Part body = MultipartBody.Part.createFormData("chuky",file.getName(),requestFile);
    ///upload
    extent.apiService = ApiUtils.getUserService();
    extent.sharedPreferences = getSharedPreferences(extent.MyPREFERENCES, Context.MODE_PRIVATE);
    String token  = "Bearer " + extent.sharedPreferences.getString(extent.Token,"");
    String id = extent.sharedPreferences.getString(extent.ID,"");
    Call<UploadChuKy> call = extent.apiService.upload_chuky(id,token,body);
    call.enqueue(new Callback<UploadChuKy>() {
        @Override
        public void onResponse(Call<UploadChuKy> call, Response<UploadChuKy> response) {
            if(response.isSuccessful()) {
                SharedPreferences.Editor editor = extent.sharedPreferences.edit();
                editor.putString(extent.ChuKy, response.body().getResults().getObject().getChuky());
                editor.apply();
                Intent intent = new Intent(DrawSignatureActivity.this,DangKySV5TActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(DrawSignatureActivity.this, "Đã Xảy Ra Lỗi!!Hãy Thử Đăng Nhập Lại", Toast.LENGTH_SHORT).show();
                MainActivity a = new MainActivity();
                a.cancel_sharedPre();
            }
        }
        @Override
        public void onFailure(Call<UploadChuKy> call, Throwable t) {
            upload_chuky();
        }
    });
}
class Drawing extends LoginActivity{
}
}
