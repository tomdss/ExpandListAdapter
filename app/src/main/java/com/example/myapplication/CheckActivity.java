package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapter.ExpandAdapter;
import com.example.myapplication.adapter.MyCategoriesExpandableListAdapter2;
import com.example.myapplication.model.DataItem;
import com.example.myapplication.model.EventRecruit;
import com.example.myapplication.model.GroupEvent;
import com.example.myapplication.model.SubCategoryItem;
import com.example.myapplication.ultil.ConstantManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CheckActivity extends AppCompatActivity {

    private Button btn;
    private ExpandableListView lvCategory;

    private ArrayList<DataItem> arCategory;
    private ArrayList<SubCategoryItem> arSubCategory;
    private ArrayList<ArrayList<SubCategoryItem>> arSubCategoryFinal;

    private ArrayList<HashMap<String, String>> parentItems;
    private ArrayList<ArrayList<HashMap<String, String>>> childItems;

    private List<String> listGroup;
    private HashMap<String, List<EventRecruit>> listItem;

    private MyCategoriesExpandableListAdapter2 myCategoriesExpandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckActivity.this, CheckedActivity.class);
                startActivity(intent);
            }
        });

        setupReferences();
    }

    private void setupReferences() {

        lvCategory = findViewById(R.id.lvCategory);


        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
        listGroup.add(getString(R.string.event_group_1));
        listGroup.add(getString(R.string.event_group_2));
        listGroup.add(getString(R.string.event_group_3));
        listGroup.add(getString(R.string.event_group_4));

        List<EventRecruit> array = new ArrayList<>();
        EventRecruit eventRecruit1 = new EventRecruit();
        eventRecruit1.setTime("000");
        eventRecruit1.setCheck(false);
        array.add(eventRecruit1);
        EventRecruit eventRecruit2 = new EventRecruit();
        eventRecruit2.setTime("111");
        eventRecruit2.setCheck(false);
        array.add(eventRecruit2);
        EventRecruit eventRecruit3 = new EventRecruit();
        eventRecruit3.setTime("222");
        eventRecruit3.setCheck(false);
        array.add(eventRecruit3);

        List<EventRecruit> list1 = new ArrayList<>();
        for (EventRecruit item : array) {
            list1.add(item);
        }

        List<EventRecruit> list2 = new ArrayList<>();
        for (EventRecruit item : array) {
            list2.add(item);
        }

        List<EventRecruit> list3 = new ArrayList<>();
        for (EventRecruit item : array) {
            list3.add(item);
        }

        List<EventRecruit> list4 = new ArrayList<>();
        for (EventRecruit item : array) {
            list4.add(item);
        }

        listItem.put(listGroup.get(0), list1);
        listItem.put(listGroup.get(1), list2);
        listItem.put(listGroup.get(2), list3);
        listItem.put(listGroup.get(3), list4);
        /*arCategory = new ArrayList<>();
        arSubCategory = new ArrayList<>();
        parentItems = new ArrayList<>();
        childItems = new ArrayList<>();

        DataItem dataItem = new DataItem();
        dataItem.setCategoryId("1");
        dataItem.setCategoryName("Adventure");

        arSubCategory = new ArrayList<>();
        for(int i = 1; i < 6; i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Adventure: "+i);
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("2");
        dataItem.setCategoryName("Art");
        arSubCategory = new ArrayList<>();
        for(int j = 1; j < 6; j++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(j));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Art: "+j);
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("3");
        dataItem.setCategoryName("Cooking");
        arSubCategory = new ArrayList<>();
        for(int k = 1; k < 6; k++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(k));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Cooking: "+k);
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        Log.d("TAG", "setupReferences: "+arCategory.size());

        for(DataItem data : arCategory){
//                        Log.i("Item id",item.id);
            ArrayList<HashMap<String, String>> childArrayList =new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mapParent = new HashMap<String, String>();

            mapParent.put(ConstantManager.Parameter.CATEGORY_ID,data.getCategoryId());
            mapParent.put(ConstantManager.Parameter.CATEGORY_NAME,data.getCategoryName());

            int countIsChecked = 0;
            for(SubCategoryItem subCategoryItem : data.getSubCategory()) {

                HashMap<String, String> mapChild = new HashMap<String, String>();
                mapChild.put(ConstantManager.Parameter.SUB_ID,subCategoryItem.getSubId());
                mapChild.put(ConstantManager.Parameter.SUB_CATEGORY_NAME,subCategoryItem.getSubCategoryName());
                mapChild.put(ConstantManager.Parameter.CATEGORY_ID,subCategoryItem.getCategoryId());
                mapChild.put(ConstantManager.Parameter.IS_CHECKED,subCategoryItem.getIsChecked());

                if(subCategoryItem.getIsChecked().equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {

                    countIsChecked++;
                }
                childArrayList.add(mapChild);
            }

            if(countIsChecked == data.getSubCategory().size()) {

                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_TRUE);
            }else {
                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            }

            mapParent.put(ConstantManager.Parameter.IS_CHECKED,data.getIsChecked());
            childItems.add(childArrayList);
            parentItems.add(mapParent);

        }*/

//        ConstantManager.parentItems = parentItems;
//        ConstantManager.childItems = childItems;

        myCategoriesExpandableListAdapter = new MyCategoriesExpandableListAdapter2(this,listGroup,listItem,false);
        lvCategory.setAdapter(myCategoriesExpandableListAdapter);
    }
}
