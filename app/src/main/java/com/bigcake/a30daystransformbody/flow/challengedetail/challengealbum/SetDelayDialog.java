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
import android.widget.SeekBar;
import android.widget.TextView;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.interfaces.SetDelayDialogCallback;

/**
 * Created by Big Cake on 5/7/2017
 */

public class SetDelayDialog extends Dialog {
    private Button btnOk;
    private TextView tvDelay;
    private SetDelayDialogCallback callback;
    private SeekBar seekBar;
    private int delay;

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
                callback.onDelaySetted(delay);
                dismiss();
            }
        });

        seekBar.setProgress(400);
        seekBar.incrementProgressBy(50);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                delay = progress + 100;
                tvDelay.setText(String.format(getContext().getString(R.string.gen_delay_value), delay));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initViews() {
        tvDelay = (TextView) findViewById(R.id.tv_delay);
        btnOk = (Button) findViewById(R.id.btn_ok);
        seekBar = (SeekBar) findViewById(R.id.seekbar_delay);
    }
}
