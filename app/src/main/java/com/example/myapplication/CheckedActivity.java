package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapter.MyCategoriesExpandableListAdapter2;
import com.example.myapplication.ultil.ConstantManager;

public class CheckedActivity extends AppCompatActivity {

    private TextView tvParent, tvChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked);

        tvParent = findViewById(R.id.parent);
        tvChild = findViewById(R.id.child);

        /*for (int i = 0; i < MyCategoriesExpandableListAdapter2.parentItems.size(); i++ ){

            String isChecked = MyCategoriesExpandableListAdapter2.parentItems.get(i).get(ConstantManager.Parameter.IS_CHECKED);

            if (isChecked.equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE))
            {
                tvParent.setText(tvParent.getText() + MyCategoriesExpandableListAdapter2.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME));
            }

            for (int j = 0; j < MyCategoriesExpandableListAdapter2.childItems.get(i).size(); j++ ){

                String isChildChecked = MyCategoriesExpandableListAdapter2.childItems.get(i).get(j).get(ConstantManager.Parameter.IS_CHECKED);

                if (isChildChecked.equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE))
                {
                    tvChild.setText(tvChild.getText() +" , " + MyCategoriesExpandableListAdapter2.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME) + " "+(j+1));
                }

            }

        }*/
    }
}