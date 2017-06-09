package com.product.is.app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.product.is.app.adapter.StudentAdapter;
import com.product.is.app.db.DbOpenHelper;
import com.product.is.app.model.StudentInfo;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    //定义数据
    private List<StudentInfo> mData;

    private DbOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //返回操作
        returnIndex();

        //操作list列表
        final ListView listView = (ListView) findViewById(R.id.list);
        LayoutInflater inflater = getLayoutInflater();

        //获取数据
        mHelper = new DbOpenHelper(this,"Student.db",null,1);
        getStudentInfoList();

        //实例化ListAdapter
        StudentAdapter adapter = new StudentAdapter(this, inflater, mData);
        listView.setAdapter(adapter);
    }

    /**
     * 返回首页
     */
    private void returnIndex() {
        //回到列表页
        ImageButton btn = (ImageButton) findViewById(R.id.backList);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ListActivity.this,
//                        "信息还未保存，确认返回吗？",
//                        Toast.LENGTH_SHORT
//                ).show();
                Intent intent = new Intent();
                intent.setClass(ListActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 获得数据库中的数据
     */
    private List<StudentInfo> getStudentInfoList() {
        mData = new ArrayList<StudentInfo>();
        SQLiteDatabase sqLiteDatabase = mHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("Student", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String studentNo = cursor.getString(cursor.getColumnIndex("studentNo"));
                String className = cursor.getString(cursor.getColumnIndex("className"));
                Integer chinese = cursor.getInt(cursor.getColumnIndex("chinese"));
                Integer math = cursor.getInt(cursor.getColumnIndex("math"));
                Integer english = cursor.getInt(cursor.getColumnIndex("english"));
                Integer total = chinese+math+english;
                mData.add(new StudentInfo(name, studentNo, className, chinese, math, english, total));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return mData;
    }
}
