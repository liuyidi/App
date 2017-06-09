package com.product.is.app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.product.is.app.db.DbOpenHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UpdateActivity extends AppCompatActivity {

    @BindView(R.id.addText1)
    EditText mUpdateName;
    @BindView(R.id.addText2)
    EditText mUpdateClassName;
    @BindView(R.id.addText3)
    EditText mUpdateStudentNo;
    @BindView(R.id.addText4)
    EditText mUpdateChinese;
    @BindView(R.id.addText5)
    EditText mUpdateMath;
    @BindView(R.id.addText6)
    EditText mUpdateEnglish;
    @BindView(R.id.backList)
    ImageButton backList;
    @BindView(R.id.submitBtn2)
    Button submitBtn2;

    private DbOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mUpdateName.setText(intent.getStringExtra("name"));
        mUpdateClassName.setText(intent.getStringExtra("className"));
        mUpdateStudentNo.setText(intent.getStringExtra("studentNo"));
        mUpdateChinese.setText(String.valueOf(intent.getIntExtra("chinese", 0)));
        mUpdateMath.setText(String.valueOf(intent.getIntExtra("math", 0)));
        mUpdateEnglish.setText(String.valueOf(intent.getIntExtra("english", 0)));

        //回到列表页
        backList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UpdateActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //保存数据
        mHelper = new DbOpenHelper(this,"Student.db",null,1);
        saveData();
    }

    /**
     * 保存数据
     */
    public void saveData() {


        submitBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String name = mUpdateName.getText().toString();
                final String className = mUpdateClassName.getText().toString();
                final String studentNo = mUpdateStudentNo.getText().toString();
                final String english = mUpdateEnglish.getText().toString();
                final String chinese = mUpdateChinese.getText().toString();
                final String math = mUpdateMath.getText().toString();

                SQLiteDatabase db = mHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("className",className);
                values.put("english",english);
                values.put("chinese",chinese);
                values.put("math",math);
                String[] args = {String.valueOf(studentNo)};
                db.update("Student",values,"studentNo = ?",args);
                values.clear();

                Intent intent = new Intent(UpdateActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
