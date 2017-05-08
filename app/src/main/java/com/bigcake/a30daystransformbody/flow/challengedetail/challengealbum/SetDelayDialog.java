package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.interfaces.SetDelayDialogCallback;

/**
 * Created by Big Cake on 5/7/2017
 */

public class SetDelayDialog extends Dialog {
    private EditText edtDelay;
    private Button btnOk;
    private SetDelayDialogCallback callback;

    private SetDelayDialog(@NonNull Context context) {
        super(context);
    }

    public static SetDelayDialog create(Context context, SetDelayDialogCallback callback) {
        SetDelayDialog dialog = new SetDelayDialog(context);
        dialog.callback = callback;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_set_delay);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initViews();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate(edtDelay.getText().toString())) {
                    callback.onDelaySetted(Integer.parseInt(edtDelay.getText().toString()));
                    dismiss();
                }
            }
        });
    }

    private boolean validate(String delay) {
        if (delay.isEmpty())
            return false;
        int delayValue = Integer.parseInt(delay);
        if (delayValue < 100 || delayValue > 1000) {
            edtDelay.setError(getContext().getString(R.string.error_delay));
            edtDelay.setFocusable(true);
            edtDelay.requestFocus();
            return false;
        }
        return true;
}

    private void initViews() {
        edtDelay = (EditText) findViewById(R.id.edt_delay);
        edtDelay.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {
                edtDelay.setError(null);
            }
        });
        btnOk = (Button) findViewById(R.id.btn_ok);
    }
}
