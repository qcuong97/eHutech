package com.example.cpd.ehutech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SV5TActivity extends AppCompatActivity {

    TextView txtChinhsua, txtLuu;
    ExpandableListView expandableListView;
    List<String> listdataHeader;
    HashMap<String, List<String>> listdataChild;
    CustomExpandableListView_SV5T customExpandableListView_sv5t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sv5_t);

        Anhxa();
        Addcontrol();

        customExpandableListView_sv5t = new CustomExpandableListView_SV5T(SV5TActivity.this, listdataHeader, listdataChild);
        expandableListView.setAdapter(customExpandableListView_sv5t);

    }

    public void txtChinhsuaAction(View v)
    {
        txtChinhsua.setVisibility(View.GONE);
        txtLuu.setVisibility(View.VISIBLE);
    }

    public void txtLuuAction(View v)
    {
        //Code lưu chỉnh sửa - truyền dkiệu về sql
        txtChinhsua.setVisibility(View.VISIBLE);
        txtLuu.setVisibility(View.GONE);
    }

    private void Anhxa()
    {
        txtChinhsua = (TextView) findViewById(R.id.txtChinhsua);
        txtLuu = (TextView) findViewById(R.id.txtLuu);
    }

    private void Addcontrol()
    {
        expandableListView = (ExpandableListView) findViewById(R.id.TCSV5T);
        listdataHeader = new ArrayList<>();
        listdataChild = new HashMap<String, List<String>>();

        listdataHeader.add("Tiêu chí ĐẠO ĐỨC");
        listdataHeader.add("Tiêu chí HỌC TẬP");
        listdataHeader.add("Tiêu chí THỂ LỰC");
        listdataHeader.add("Tiêu chí TÌNH NGUYỆN");
        listdataHeader.add("Tiêu chí HỘI NHẬP");
        listdataHeader.add("Tiêu chí ƯU TIÊN");

        List<String> TCDD = new ArrayList<String>();
        TCDD.add("•\tĐiểm Rèn luyện HK1: ………");
        TCDD.add("HK2: …………");
        TCDD.add("TB cả năm: …………");
        TCDD.add("•\tKết quả phân tích chất lượng Đoàn viên: …………………………………….");
        TCDD.add("•\tThành tích khác:            ");
        TCDD.add("Là “Thanh niên tiên tiến làm theo lời Bác.”");
        TCDD.add("Tham gia Hội thi về tư tưởng Hồ Chí Minh.");

        List<String> TCHT = new ArrayList<String>();
        TCHT.add("•\tĐiểm Học tập HK1: .........");
        TCHT.add("Điểm HK2: ........... ");
        TCHT.add("Điểm TB năm học: ...............");
        TCHT.add("•\tTham gia phong trào NCKH: ");
        TCHT.add("Bài viết, Tham luận khoa học.");
        TCHT.add("Thực hiện Đề tài NCKH.");
        TCHT.add("Đạt giải Cuộc thi học thuật: …………………………………………………..");

        List<String> TCTL = new ArrayList<String>();
        TCTL.add("Đạt danh hiệu “Sinh viên khỏe.”");
        TCTL.add("Thành viên các Đội tuyển TDTT.");
        TCTL.add("Đạt Giải trong Hội thao các cấp: ……………………………………………");

        List<String> TCTN = new ArrayList<String>();
        TCTN.add("Tham gia, nhận Giấy chứng nhận: Xuân Tình nguyện, Mùa Hè xanh.");
        TCTN.add("Hoàn thành 05 hoạt động tình nguyện/năm.");

        List<String> TCHN = new ArrayList<String>();
        TCHN.add("Đạt trình độ Anh văn B1 hoặc tương đương.");
        TCHN.add("Hoạt động Giao lưu quốc tế.");
        TCHN.add("Đạt Giải trong cuộc thi về Ngoại ngữ.");
        TCHN.add("Hoàn thành 01 Khóa học kỹ năng.");
        TCHN.add("Hoàn thành 03 buổi Hội thảo kỹ năng.");
        TCHN.add("Tham gia hoạt động về Hội nhập.");

        List<String> TCUT = new ArrayList<String>();
        TCUT.add("Là Đoàn viên ưu tú được Kết nạp Đảng.");
        TCUT.add("Tham gia Hiến máu tình nguyện.");
        TCUT.add("Được biểu dương, khen thưởng.");

        listdataChild.put(listdataHeader.get(0), TCDD);
        listdataChild.put(listdataHeader.get(1), TCHT);
        listdataChild.put(listdataHeader.get(2), TCTL);
        listdataChild.put(listdataHeader.get(3), TCTN);
        listdataChild.put(listdataHeader.get(4), TCHN);
        listdataChild.put(listdataHeader.get(5), TCUT);
    }
}
