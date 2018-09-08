package com.rock.commondialog.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.rock.commondialog.R;

/**
 * Created by Rock on 2018/8/10.
 *
 * 本demo弹出框共分为3种
 * 1、普通弹出窗，点击框外不消失
 * 2、提示弹出框，点击框外消失
 * 3、弹出框的数据交换弹框
 */

public class CommonDialogOne  extends Dialog implements View.OnClickListener {

    private WindowManager windowManager;

    private boolean cancal = false;//用来判断是否可点击弹框外消失弹框

    private CommonDialogOneListener leftListener ,rightListener;
    private TextView contentView;
    private String contentStr = "";

    public CommonDialogOne(@NonNull Context context) {
        this(context,0);
    }

    public CommonDialogOne(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        windowManager = ((Activity)context).getWindowManager();
        getWindow().setWindowAnimations(R.style.dialogstyle_vertical);
    }


    public static CommonDialogOne getIntence(Context context,boolean isclick,String content,CommonDialogOneListener leftListener,CommonDialogOneListener rightListener){

        CommonDialogOne dialogOne = new CommonDialogOne(context);
        dialogOne.setWeatherClick(isclick);
        dialogOne.setContent(content);
        dialogOne.setOnLeftClickListener(leftListener);
        dialogOne.setOnRightClickListener(rightListener);
        dialogOne.show();
        return dialogOne;
    }

    private void setContent(String content) {
        this.contentStr = content;
    }

    private void setWeatherClick(boolean isclick) {
        this.cancal = isclick;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除屏幕的title

        setContentView(R.layout.diaolog_one_type);//设置diaolog的样式布局

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);//设置dialog背景为透明色

        getWindow().setGravity(Gravity.CENTER);//设置dialog的位置

        Point point = new Point();

        windowManager.getDefaultDisplay().getSize(point);//获取屏幕的宽和高

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();//获取弹框当前的参数

        layoutParams.width = (int) (point.x * 0.8);

        setCanceledOnTouchOutside(cancal);//设置点击屏幕dialog不消失

        onWindowAttributesChanged(layoutParams);  //改变dialog窗口的位置

        getWindow().setAttributes(layoutParams);

        initView();//初始化view
    }





    private void initView() {
        findViewById(R.id.dialogone_cancel).setOnClickListener(this);
        findViewById(R.id.dialogone_ensure).setOnClickListener(this);
        contentView = findViewById(R.id.dialogone_content);

        contentView.setText(contentStr);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.dialogone_cancel:
                if (leftListener != null){
                    leftListener.onCommonDialogOneClick(this,view);
                }

                break;

            case R.id.dialogone_ensure:
                if (rightListener != null){
                    rightListener.onCommonDialogOneClick(this,view);
                }
                break;
        }
    }


    public interface CommonDialogOneListener{
        void onCommonDialogOneClick(CommonDialogOne dialogOne, View view);
    }

    private void setOnLeftClickListener(CommonDialogOneListener listener){
        this.leftListener = listener;
    }
    private void setOnRightClickListener(CommonDialogOneListener listener){
        this.rightListener = listener;
    }
}
