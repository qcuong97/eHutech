package com.example.cpd.ehutech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListView_SV5T extends BaseExpandableListAdapter{

    Context context;
    List<String> listHeader;
    HashMap<String, List<String>> listChild;

    public CustomExpandableListView_SV5T(Context context, List<String> listHeader, HashMap<String, List<String>> listChild) {
        this.context = context;
        this.listHeader = listHeader;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listChild.get(listHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(listHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View convertView, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listview_header_sv5t, null);
        TextView txtHeader = (TextView) convertView.findViewById(R.id.textviewHeader);
        txtHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        String item = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listview_item_sv5t, null);
        TextView txtChild = (TextView) convertView.findViewById(R.id.checkboxChild);
        txtChild.setText(item);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
