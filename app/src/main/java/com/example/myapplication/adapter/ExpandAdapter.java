package com.example.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.custom.NonScrollExpandableListView;
import com.example.myapplication.model.EventRecruit;
import com.example.myapplication.model.GroupEvent;
import com.example.myapplication.ultil.ConstantManager;

import java.util.HashMap;
import java.util.List;


import android.widget.CompoundButton;
import android.widget.ImageView;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<HashMap<String, String>> listGroup;
    List<List<HashMap<String, String>>> listItem;
    HashMap<String, String> ItemEvent;
    LayoutInflater inflater;

    public ExpandAdapter(Context context, List<HashMap<String, String>> listGroup, List<List<HashMap<String, String>>> listItem) {
        this.context = context;
        this.listGroup = listGroup;
        this.listItem = listItem;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listItem.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listItem.get(groupPosition).get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ViewHolderExpandGroup viewHolderExpandGroup;

//        GroupEvent group = (GroupEvent) getGroup(groupPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_expand_list, null);
            viewHolderExpandGroup = new ViewHolderExpandGroup();
            viewHolderExpandGroup.ivGroup = convertView.findViewById(R.id.iv_down);
            viewHolderExpandGroup.tvGroupName = convertView.findViewById(R.id.expand_list_parent);
            convertView.setTag(viewHolderExpandGroup);
        } else {
            viewHolderExpandGroup = (ViewHolderExpandGroup) convertView.getTag();
        }


        viewHolderExpandGroup.tvGroupName.setText(listGroup.get(groupPosition).get(ConstantManager.Parameter.CATEGORY_NAME));
        //Set the arrow programatically, so we can control it
        int imageResourceId = isExpanded ? android.R.drawable.arrow_up_float : android.R.drawable.arrow_down_float;
        viewHolderExpandGroup.ivGroup.setImageResource(imageResourceId);
//        listGroup.get(groupPosition).setExpand(isExpanded);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolderExpandChild viewHolderExpandChild;
        ItemEvent = listItem.get(groupPosition).get(childPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_check_event_recruit, null);
            viewHolderExpandChild = new ViewHolderExpandChild();
            viewHolderExpandChild.tvChildName = (TextView) convertView.findViewById(R.id.tv_item_event_time);
            viewHolderExpandChild.cbChild = (CheckBox) convertView.findViewById(R.id.ckb_item_event);
            convertView.setTag(viewHolderExpandChild);
        } else {
            viewHolderExpandChild = (ViewHolderExpandChild) convertView.getTag();
        }

        viewHolderExpandChild.tvChildName.setText(ItemEvent.get(ConstantManager.Parameter.SUB_CATEGORY_NAME));

        if (listItem.get(groupPosition).get(childPosition).get(ConstantManager.Parameter.IS_CHECKED).equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {
            viewHolderExpandChild.cbChild.setChecked(true);
            notifyDataSetChanged();
        } else {
            viewHolderExpandChild.cbChild.setChecked(false);
            notifyDataSetChanged();
        }

        viewHolderExpandChild.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolderExpandChild.cbChild.isChecked()) {
                    listItem.get(groupPosition).get(childPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);
                } else {
                    listItem.get(groupPosition).get(childPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_FALSE);
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ViewHolderExpandGroup {
        TextView tvGroupName;
        ImageView ivGroup;
    }

    private class ViewHolderExpandChild {
        TextView tvChildName;
        CheckBox cbChild;
    }

}