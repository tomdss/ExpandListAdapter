package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.EventRecruit;
import com.example.myapplication.ultil.ConstantManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zerones on 04-Oct-17.
 */

public class MyCategoriesExpandableListAdapter2 extends BaseExpandableListAdapter {

    public static HashMap<String, List<EventRecruit>> childItems;
    public static List<String> parentItems;
    //    private final ArrayList<HashMap<String, String>> childItems;
    private LayoutInflater inflater;
    private Activity activity;
    private EventRecruit child;
    private int count = 0;
    private boolean isFromMyCategoriesFragment;

    public MyCategoriesExpandableListAdapter2(Activity activity, List<String> parentItems,
                                              HashMap<String, List<EventRecruit>> childItems, boolean isFromMyCategoriesFragment) {

        this.parentItems = parentItems;
        this.childItems = childItems;
        this.activity = activity;
        this.isFromMyCategoriesFragment = isFromMyCategoriesFragment;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getGroupCount() {
        return parentItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return (childItems.get(parentItems.get(groupPosition))).size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean b, View convertView, ViewGroup viewGroup) {
         final ViewHolderParent viewHolderParent;
        if (convertView == null) {

            if(isFromMyCategoriesFragment) {
                convertView = inflater.inflate(R.layout.group_list_layout_choose_categories2, null);
            }else {
                convertView = inflater.inflate(R.layout.group_list_layout_choose_categories2, null);
            }
            viewHolderParent = new ViewHolderParent();

            viewHolderParent.tvMainCategoryName = convertView.findViewById(R.id.tvMainCategoryName);
            viewHolderParent.ivCategory = convertView.findViewById(R.id.ivCategory);
            convertView.setTag(viewHolderParent);
        } else {
            viewHolderParent = (ViewHolderParent) convertView.getTag();
        }

//        ConstantManager.childItems = childItems;
//        ConstantManager.parentItems = parentItems;

        viewHolderParent.tvMainCategoryName.setText(parentItems.get(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean b, View convertView, ViewGroup viewGroup) {

        final ViewHolderChild viewHolderChild;
        child = childItems.get(parentItems.get(groupPosition)).get(childPosition);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_list_layout_choose_category, null);
            viewHolderChild = new ViewHolderChild();

            viewHolderChild.tvSubCategoryName = convertView.findViewById(R.id.tvSubCategoryName);
            viewHolderChild.cbSubCategory = convertView.findViewById(R.id.cbSubCategory);
            viewHolderChild.viewDivider = convertView.findViewById(R.id.viewDivider);
            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }

        if (childItems.get(parentItems.get(groupPosition)).get(childPosition).isCheck()) {
            viewHolderChild.cbSubCategory.setChecked(true);
            notifyDataSetChanged();
        } else {
            viewHolderChild.cbSubCategory.setChecked(false);
            notifyDataSetChanged();
        }

        viewHolderChild.tvSubCategoryName.setText(child.getResult());
        viewHolderChild.cbSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolderChild.cbSubCategory.isChecked()) {
                    count = 0;
                    childItems.get(parentItems.get(groupPosition)).get(childPosition).setCheck(true);
                    notifyDataSetChanged();
                } else {
                    count = 0;
                    childItems.get(parentItems.get(groupPosition)).get(childPosition).setCheck(false);
                    notifyDataSetChanged();
                }

                for (int i = 0; i < childItems.get(parentItems.get(groupPosition)).size(); i++) {
                    if (childItems.get(parentItems.get(groupPosition)).get(i).isCheck()) {
                        count++;
                    }
                }


//                ConstantManager.childItems = childItems;
//                ConstantManager.parentItems = parentItems;
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    private class ViewHolderParent {

        TextView tvMainCategoryName;
        ImageView ivCategory;
    }

    private class ViewHolderChild {

        TextView tvSubCategoryName;
        CheckBox cbSubCategory;
        View viewDivider;
    }


}