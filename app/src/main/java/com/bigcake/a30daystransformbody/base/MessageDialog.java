package com.bigcake.a30daystransformbody.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bigcake.a30daystransformbody.R;

/**
 * Created by kiethuynh on 17/05/2017
 */

public class MessageDialog extends Dialog {
    private Button btnRight, btnLeft;
    private TextView tvTitle, tvMessage;
    private String title, message, btnRightText, btnLeftText;
    private ClickListener leftButtonClickListener, rightButtonClickListener;

    public MessageDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initViews();
    }

    private void initViews() {
        btnRight = (Button) findViewById(R.id.btn_right);
        btnLeft = (Button) findViewById(R.id.btn_left);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMessage = (TextView) findViewById(R.id.tv_message);
    }

    public static MessageDialog create(Context context) {
        return new MessageDialog(context);
    }

    public MessageDialog setTitleText(@StringRes int title) {
        this.setTitleText(getContext().getString(title));
        return this;
    }

    public MessageDialog setTitleText(String title) {
        this.title = title;
        return this;
    }

    public MessageDialog setMessage(@StringRes int message) {
        this.setMessage(getContext().getString(message));
        return this;
    }

    public MessageDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public MessageDialog setLeftButton(@StringRes int label, ClickListener clickListener) {
        return setLeftButton(getContext().getString(label), clickListener);
    }

    public MessageDialog setLeftButton(String label, ClickListener clickListener) {
        this.btnLeftText = label;
        this.leftButtonClickListener = clickListener;
        return this;
    }

    public MessageDialog setRightButton(@StringRes int label, ClickListener clickListener) {
        return setRightButton(getContext().getString(label), clickListener);
    }

    public MessageDialog setRightButton(String label, ClickListener clickListener) {
        this.btnRightText = label;
        this.rightButtonClickListener = clickListener;
        return this;
    }

    @Override
    public void show() {
        super.show();
        setupDialog();
    }

    private void setupDialog() {
        if (title != null && !title.isEmpty()) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(View.GONE);
        }

        if (message != null && !message.isEmpty()) {
            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText(message);
        } else {
            tvMessage.setVisibility(View.GONE);
        }

        if (btnLeftText != null && !btnLeftText.isEmpty()) {
            btnLeft.setVisibility(View.VISIBLE);
            btnLeft.setText(btnLeftText);
            btnLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    leftButtonClickListener.onClick();
                }
            });
        } else {
            btnLeft.setVisibility(View.GONE);
        }

        if (btnRightText != null && !btnRightText.isEmpty()) {
            btnRight.setVisibility(View.VISIBLE);
            btnRight.setText(btnRightText);
            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rightButtonClickListener.onClick();
                    dismiss();
                }
            });
        } else {
            btnRight.setVisibility(View.GONE);
        }
    }

    public interface ClickListener {
        void onClick();
    }
}
