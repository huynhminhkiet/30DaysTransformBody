package com.bigcake.a30daystransformbody.flow.weightmanager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.interfaces.UpdateWeightDialogCallback;

/**
 * Created by kiethuynh on 10/05/2017
 */

public class UpdateWeightDialog extends Dialog {
    private UpdateWeightDialogCallback mCallback;
    private float lastWeightData;
    private NumberPicker leftNumberPicker, rightNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update_weight);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initViews();

        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weight = Float.parseFloat(leftNumberPicker.getValue() + "." + rightNumberPicker.getValue());
                mCallback.onWeightSubmitted(weight);
                dismiss();
            }
        });

    }


    private void initViews() {
        leftNumberPicker = (NumberPicker) findViewById(R.id.number_picker_left);
        leftNumberPicker.setMinValue(1);
        leftNumberPicker.setMaxValue(300);
        leftNumberPicker.setWrapSelectorWheel(false);
        leftNumberPicker.setValue((int) lastWeightData);
        rightNumberPicker = (NumberPicker) findViewById(R.id.number_picker_right);
        rightNumberPicker.setMinValue(0);
        rightNumberPicker.setMaxValue(9);
        rightNumberPicker.setWrapSelectorWheel(true);
        rightNumberPicker.setValue(((int)(lastWeightData * 10)) % 10);
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
        if (lastWeight == null || lastWeight.isEmpty())
            lastWeightData = 50;
        else
            lastWeightData = Float.parseFloat(lastWeight);
    }
}
