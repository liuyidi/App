package com.product.is.app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.product.is.app.db.DbOpenHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.backList)
    ImageButton backList;
    @BindView(R.id.addText1)
    EditText addText1;
    @BindView(R.id.addText2)
    EditText addText2;
    @BindView(R.id.addText3)
    EditText addText3;
    @BindView(R.id.addText4)
    EditText addText4;
    @BindView(R.id.addText5)
    EditText addText5;
    @BindView(R.id.addText6)
    EditText addText6;
    @BindView(R.id.submitBtn)
    Button submitBtn;

    private DbOpenHelper mHelper;

    //TODO
    public static void startAct(Context context) {
        Intent intent = new Intent(context, AddActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        mHelper = new DbOpenHelper(this, "Student.db", null, 1);
    }

    @OnClick({R.id.backList, R.id.submitBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backList:
//                Toast.makeText(AddActivity.this,
//                        "信息还未保存，确认返回吗？",
//                        Toast.LENGTH_SHORT
//                ).show();
                Intent intent = new Intent();
                intent.setClass(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.submitBtn:

                final String name = addText1.getText().toString();
                final String className  = addText2.getText().toString();
                final String studentNo  = addText3.getText().toString();
                final String chinese  = addText4.getText().toString();
                final String math  = addText5.getText().toString();
                final String english = addText6.getText().toString();

                SQLiteDatabase db = mHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("className",className);
                values.put("studentNo",studentNo);
                values.put("chinese",chinese);
                values.put("math",math);
                values.put("english",english);
                db.insert("Student", null, values);
                values.clear();

                Intent intent2 = new Intent(AddActivity.this, ListActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}
