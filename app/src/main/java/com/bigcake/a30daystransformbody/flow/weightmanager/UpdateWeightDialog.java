package com.bigcake.a30daystransformbody.flow.weightmanager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.interfaces.UpdateWeightDialogCallback;

/**
 * Created by kiethuynh on 10/05/2017
 */

public class UpdateWeightDialog extends Dialog {
    private UpdateWeightDialogCallback mCallback;
    private EditText edtWeight;
    private String lastWeightData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update_weight);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initViews();

        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(edtWeight.getText().toString())) {
                    mCallback.onWeightSubmitted(Float.parseFloat(edtWeight.getText().toString()));
                    dismiss();
                }
            }
        });
    }

    private boolean validate(String s) {
        return (s != null && !s.isEmpty() && !"0".equals(s));
    }

    private void initViews() {
        edtWeight = (EditText) findViewById(R.id.edt_weight);
        edtWeight.setText(lastWeightData);
    }

    private UpdateWeightDialog(@NonNull Context context) {
        super(context);
    }

    public static UpdateWeightDialog create(Context context, UpdateWeightDialogCallback callback) {
        UpdateWeightDialog dialog = new UpdateWeightDialog(context);
        dialog.mCallback = callback;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void setLastWeight(String lastWeight) {
        lastWeightData = lastWeight;
    }
}
