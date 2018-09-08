package com.rock.commondialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rock.commondialog.dialog.CommonDialogOne;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CommonDialogOne.CommonDialogOneListener {



    private boolean cancel = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn:
                CommonDialogOne.getIntence(this,cancel,"这是文本的内容",this,this);
                break;
        }
    }

    @Override
    public void onCommonDialogOneClick(CommonDialogOne dialogOne, View view) {

        switch (view.getId()){
            case R.id.dialogone_cancel:
                Toast.makeText(this,"取消",Toast.LENGTH_SHORT).show();
                dialogOne.dismiss();
                break;
            case R.id.dialogone_ensure:
                Toast.makeText(this,"确定",Toast.LENGTH_SHORT).show();
                dialogOne.dismiss();
                break;
        }
    }
}
