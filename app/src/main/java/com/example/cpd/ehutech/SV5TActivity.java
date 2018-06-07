package com.example.cpd.ehutech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.cpd.ehutech.model.SV5T.Row;
import com.google.gson.Gson;


public class SV5TActivity extends AppCompatActivity {

    TextView txt_TCDD, txt_TCHT, txt_TCTL, txt_TCTN, txt_TCHN, txt_TCUT;
    EditText edt_drlhk1, edt_drlhk2, edt_dhthk1, edt_dhthk2, edt_dhttb;
    CheckBox cb_rl_cluongDV, cb_rl_tnttltlBac, cb_rl_hthiTTHCM, cb_ht_thdtNCKH, cb_ht_bvNCKH, cb_ht_dgCTHT,
            cb_tl_sbKhoe, cb_tl_tvDTuyenTDTT, cb_tl_giaiHThao, cb_tn_MXHXTN, cb_tn_5hoatdong,
            cb_hn_AVanB1, cb_hn_GluuQuocTe, cb_hn_GiaiNgoaiNgu, cb_hn_1KhoahocKyNang,
            cb_hn_3HoiThaoKiNang, cb_hn_hoatdongHN, cb_ut_utuDang, cb_ut_hienmau, cb_ut_khenthuong;
    RadioButton radi_khoa,radi_truong,radi_thanh,radi_tw;
    Row row = new Row();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sv5t_main);
        Anhxa();
        lockChange();
        getInfoSV5T();
        getData();
    }

    public void Anhxa()
    {
        {
            radi_khoa = findViewById(R.id.cap_khoa);
            radi_truong = findViewById(R.id.cap_truong);
            radi_thanh = findViewById(R.id.cap_thanh);
            radi_tw = findViewById(R.id.cap_TW);
            txt_TCDD = findViewById(R.id.TCDD);
            txt_TCHT = findViewById(R.id.TCHT);
            txt_TCTL = findViewById(R.id.TCTL);
            txt_TCTN = findViewById(R.id.TCTN);
            txt_TCHN = findViewById(R.id.TCHN);
            txt_TCUT = findViewById(R.id.TCUT);
            edt_drlhk1 = findViewById(R.id.edt_rl_drlhk1);
            edt_drlhk2 = findViewById(R.id.edt_rl_drlhk2);
            edt_dhthk1 = findViewById(R.id.edt_ht_dhthk1);
            edt_dhthk2 = findViewById(R.id.edt_ht_dhthk2);
            edt_dhttb = findViewById(R.id.edt_ht_dhttb);
            cb_rl_cluongDV = findViewById(R.id.cb_rl_cluongDV);
            cb_rl_tnttltlBac = findViewById(R.id.cb_rl_tnttltlBac);
            cb_rl_hthiTTHCM = findViewById(R.id.cb_rl_hthiTTHCM);
            cb_ht_thdtNCKH = findViewById(R.id.cb_ht_thdtNCKH);
            cb_ht_bvNCKH = findViewById(R.id.cb_ht_bvNCKH);
            cb_ht_dgCTHT = findViewById(R.id.cb_ht_dgCTHT);
            cb_tl_sbKhoe = findViewById(R.id.cb_tl_sbKhoe);
            cb_tl_tvDTuyenTDTT = findViewById(R.id.cb_tl_tvDTuyenTDTT);
            cb_tl_giaiHThao = findViewById(R.id.cb_tl_giaiHThao);
            cb_tn_MXHXTN = findViewById(R.id.cb_tn_MXHXTN);
            cb_tn_5hoatdong = findViewById(R.id.cb_tn_5hoatdong);
            cb_hn_AVanB1 = findViewById(R.id.cb_hn_AVanB1);
            cb_hn_GluuQuocTe = findViewById(R.id.cb_hn_GluuQuocTe);
            cb_hn_GiaiNgoaiNgu = findViewById(R.id.cb_hn_GiaiNgoaiNgu);
            cb_hn_1KhoahocKyNang = findViewById(R.id.cb_hn_1KhoahocKyNang);
            cb_hn_3HoiThaoKiNang = findViewById(R.id.cb_hn_3HoiThaoKiNang);
            cb_hn_hoatdongHN = findViewById(R.id.cb_hn_hoatdongHN);
            cb_ut_utuDang = findViewById(R.id.cb_ut_utuDang);
            cb_ut_hienmau = findViewById(R.id.cb_ut_hienmau);
            cb_ut_khenthuong = findViewById(R.id.cb_ut_khenthuong);
        }

    }

    private void lockEditText(EditText a)
    {
        a.setClickable(false);
        a.setCursorVisible(false);
        a.setFocusable(false);
        a.setFocusableInTouchMode(false);
    }

    private void unlockEditText(EditText a)
    {
        a.setClickable(true);
        a.setCursorVisible(true);
        a.setFocusable(true);
        a.setFocusableInTouchMode(true);
    }

    private void lockChange() {
        radi_khoa.setClickable(false);
        radi_thanh.setClickable(false);
        radi_truong.setClickable(false);
        radi_tw.setClickable(false);
        lockEditText(edt_drlhk1);
        lockEditText(edt_drlhk2);
        lockEditText(edt_dhthk1);
        lockEditText(edt_dhthk2);
        lockEditText(edt_dhttb);
        cb_rl_tnttltlBac.setClickable(false);
        cb_rl_hthiTTHCM.setClickable(false);
        cb_rl_cluongDV.setClickable(false);
        cb_ht_thdtNCKH.setClickable(false);
        cb_ht_dgCTHT.setClickable(false);
        cb_ht_bvNCKH.setClickable(false);
        cb_tl_tvDTuyenTDTT.setClickable(false);
        cb_tl_sbKhoe.setClickable(false);
        cb_tl_giaiHThao.setClickable(false);
        cb_tn_MXHXTN.setClickable(false);
        cb_tn_5hoatdong.setClickable(false);
        cb_hn_hoatdongHN.setClickable(false);
        cb_hn_GluuQuocTe.setClickable(false);
        cb_hn_AVanB1.setClickable(false);
        cb_hn_3HoiThaoKiNang.setClickable(false);
        cb_hn_1KhoahocKyNang.setClickable(false);
        cb_hn_GiaiNgoaiNgu.setClickable(false);
        cb_ut_utuDang.setClickable(false);
        cb_ut_khenthuong.setClickable(false);
        cb_ut_hienmau.setClickable(false);
    }

    private void unlockChange() {
        radi_khoa.setClickable(true);
        radi_thanh.setClickable(true);
        radi_truong.setClickable(true);
        radi_tw.setClickable(true);
        unlockEditText(edt_drlhk1);
        unlockEditText(edt_drlhk2);
        unlockEditText(edt_dhthk1);
        unlockEditText(edt_dhthk2);
        unlockEditText(edt_dhttb);
        cb_rl_tnttltlBac.setClickable(true);
        cb_rl_hthiTTHCM.setClickable(true);
        cb_rl_cluongDV.setClickable(true);
        cb_ht_thdtNCKH.setClickable(true);
        cb_ht_dgCTHT.setClickable(true);
        cb_ht_bvNCKH.setClickable(true);
        cb_tl_tvDTuyenTDTT.setClickable(true);
        cb_tl_sbKhoe.setClickable(true);
        cb_tl_giaiHThao.setClickable(true);
        cb_tn_MXHXTN.setClickable(true);
        cb_tn_5hoatdong.setClickable(true);
        cb_hn_hoatdongHN.setClickable(true);
        cb_hn_GluuQuocTe.setClickable(true);
        cb_hn_AVanB1.setClickable(true);
        cb_hn_3HoiThaoKiNang.setClickable(true);
        cb_hn_1KhoahocKyNang.setClickable(true);
        cb_hn_GiaiNgoaiNgu.setClickable(true);
        cb_ut_utuDang.setClickable(true);
        cb_ut_khenthuong.setClickable(true);
        cb_ut_hienmau.setClickable(true);
    }

    private void getData() {
        if(row.getDanhhieu().equals("Khoa")){
            radi_khoa.setChecked(true);
        }
        if(row.getDanhhieu().equals("Trường")){
            radi_truong.setChecked(true);
        }
        if(row.getDanhhieu().equals("Thành")){
            radi_thanh.setChecked(true);
        }
        if(row.getDanhhieu().equals("Trung Ương")){
            radi_tw.setChecked(true);
        }
        edt_drlhk1.setText(row.getDd_drlhk1().toString());
        edt_drlhk2.setText(row.getDd_drlhk2().toString());
        edt_dhthk1.setText(row.getHt_diemhk1().toString());
        edt_dhthk2.setText(row.getHt_diemhk2().toString());
        edt_dhttb.setText(row.getHt_diemtbn().toString());
        cb_rl_cluongDV.setChecked(row.getDd_loaidoanvien());
        cb_rl_hthiTTHCM.setChecked(row.getDd_hoithitutuonghcm());
        cb_rl_tnttltlBac.setChecked(row.getDd_thanhnientientien());
        cb_ht_bvNCKH.setChecked(row.getHt_baivietnckh());
        cb_ht_dgCTHT.setChecked(row.getHt_giaithuongnckh());
        cb_ht_thdtNCKH.setChecked(row.getHt_thuchiennckh());
        cb_tl_giaiHThao.setChecked(row.getTl_giaithuongthethao());
        cb_tl_sbKhoe.setChecked(row.getTl_sinhvienkhoe());
        cb_tl_tvDTuyenTDTT.setChecked(row.getTl_doituyentdtt());
        cb_tn_5hoatdong.setChecked(row.getTn_5tn_nam());
        cb_tn_MXHXTN.setChecked(row.getTn_mhx_xtn());
        cb_hn_GiaiNgoaiNgu.setChecked(row.getHnhap_giaithuongthingoaingu());
        cb_hn_1KhoahocKyNang.setChecked(row.getHnhap_1khoakynang());
        cb_hn_3HoiThaoKiNang.setChecked(row.getHnhap_3hoithaokynang());
        cb_hn_AVanB1.setChecked(row.getHnhap_avanB1());
        cb_hn_GluuQuocTe.setChecked(row.getHnhap_giaoluuquocte());
        cb_hn_hoatdongHN.setChecked(row.getHnhap_thamgiahoatdonghoinhap());
        cb_ut_hienmau.setChecked(row.getUutien_hienmau());
        cb_ut_khenthuong.setChecked(row.getUutien_bieuduongkhenthuong());
        cb_ut_utuDang.setChecked(row.getUutien_duocketnapdang());
    }

    public void txt_TCDD_Action(View v)
    {
        LinearLayout layout_TCDD = findViewById(R.id.TTin_TCDD);
        if(layout_TCDD.getVisibility() == View.GONE)
        {
            layout_TCDD.setVisibility(View.VISIBLE);
        }else {
            layout_TCDD.setVisibility(View.GONE);
        }
    }

    public void txt_TCHT_Action(View v)
    {
        LinearLayout layout_TCHT = findViewById(R.id.TTin_TCHT);
        if(layout_TCHT.getVisibility() == View.GONE)
        {
            layout_TCHT.setVisibility(View.VISIBLE);
        }else {
            layout_TCHT.setVisibility(View.GONE);
        }
    }

    public void txt_TCTL_Action(View v)
    {
        LinearLayout layout_TCTL = findViewById(R.id.TTin_TCTL);
        if(layout_TCTL.getVisibility() == View.GONE)
        {
            layout_TCTL.setVisibility(View.VISIBLE);
        }else {
            layout_TCTL.setVisibility(View.GONE);
        }
    }

    public void txt_TCTN_Action(View v)
    {
        LinearLayout layout_TCTN = findViewById(R.id.TTin_TCTN);
        if(layout_TCTN.getVisibility() == View.GONE)
        {
            layout_TCTN.setVisibility(View.VISIBLE);
        }else {
            layout_TCTN.setVisibility(View.GONE);
        }
    }

    public void txt_TCHN_Action(View v)
    {
        LinearLayout layout_TCHN = findViewById(R.id.TTin_TCHN);
        if(layout_TCHN.getVisibility() == View.GONE)
        {
            layout_TCHN.setVisibility(View.VISIBLE);
        }else {
            layout_TCHN.setVisibility(View.GONE);
        }
    }

    public void txt_TCUT_Action(View v)
    {
        LinearLayout layout_TCUT = findViewById(R.id.TTin_TCUT);
        if(layout_TCUT.getVisibility() == View.GONE)
        {
            layout_TCUT.setVisibility(View.VISIBLE);

        }else {
            layout_TCUT.setVisibility(View.GONE);
        }
    }

    void getInfoSV5T(){
        Gson gson = new Gson();
        String getInfSV5T = getIntent().getStringExtra("InfoSV5T");
        row = gson.fromJson(getInfSV5T,Row.class);
    }
}
