package com.product.is.app.adapter;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.product.is.app.R;
import com.product.is.app.UpdateActivity;
import com.product.is.app.db.DbOpenHelper;
import com.product.is.app.model.StudentInfo;

import java.util.List;

public class StudentAdapter extends BaseAdapter{

    //定义数据
    private List<StudentInfo> mData;
    //定义Inflater,加载我们自定义的布局
    private LayoutInflater mInflater;
    //定义上下文
    private Context mcon;

    private DbOpenHelper mHelper;

    //定义构造器
    public StudentAdapter(Context context, LayoutInflater inflater, List<StudentInfo> data) {
        mcon = context;
        mInflater = inflater;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View viewStudent = mInflater.inflate(R.layout.list_item_1,null);
        StudentInfo student = mData.get(position);

        final TextView studentNo = (TextView)viewStudent.findViewById(R.id.studentNo);
        final TextView name = (TextView)viewStudent.findViewById(R.id.name);

        mHelper = new DbOpenHelper(mcon, "Student.db", null, 1);

        //操作&删除
        Button deleteBtn = (Button)viewStudent.findViewById(R.id.deleteBtn);
        Button updateBtn = (Button)viewStudent.findViewById(R.id.updateBtn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Index = position+1;
                Toast.makeText(v.getContext(), "删除第"+Index+"个", Toast.LENGTH_SHORT).show();
                String studentNoValue = studentNo.getText().toString();
                SQLiteDatabase dbDelete = mHelper.getWritableDatabase();
                dbDelete.delete("Student", "studentNo = ?", new String[]{studentNoValue});
                mData.remove(position);
                notifyDataSetChanged();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Index = position+1;
                Toast.makeText(v.getContext(), "修改第"+Index+"个", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcon, UpdateActivity.class);

                intent.putExtra("position",position);
                intent.putExtra("name",mData.get(position).getName());
                intent.putExtra("className",mData.get(position).getClassName());
                intent.putExtra("studentNo",mData.get(position).getStudentNo());
                intent.putExtra("english",mData.get(position).getEnglish());
                intent.putExtra("chinese",mData.get(position).getChinese());
                intent.putExtra("math",mData.get(position).getMath());
                mcon.startActivity(intent);
                notifyDataSetChanged();
            }
        });

        //获得自定义布局中每一个控件的对象
        TextView className = (TextView) viewStudent.findViewById(R.id.className);
        TextView english = (TextView) viewStudent.findViewById(R.id.english);
        TextView chinese = (TextView) viewStudent.findViewById(R.id.chinese);
        TextView math = (TextView) viewStudent.findViewById(R.id.math);
        TextView total = (TextView) viewStudent.findViewById(R.id.total);

        //将数据添加到自定义的布局中去
        name.setText(student.getName());
        className.setText(student.getClassName());
        studentNo.setText(student.getStudentNo());
        english.setText(String.valueOf(student.getEnglish()));
        chinese.setText(String.valueOf(student.getChinese()));
        math.setText(String.valueOf(student.getMath()));
        total.setText(String.valueOf(student.getTotal()));

        return viewStudent;
    }
}
