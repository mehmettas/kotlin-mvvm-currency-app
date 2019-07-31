package com.mehmettas.cent.utils.customscreens;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import com.mehmettas.cent.R;


public class LoadingDialog extends AlertDialog {

    public static class Builder {
        private Context context;
        private String message;
        private int messageId;
        private int themeId;
        private boolean cancelable = true; // default dialog behaviour
        private OnCancelListener cancelListener;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder setTheme(@StyleRes int themeId) {
            this.themeId = themeId;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCancelListener(OnCancelListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        public AlertDialog build() {
            return new LoadingDialog(
                    context,
                    messageId != 0 ? context.getString(messageId) : message,
                    themeId != 0 ? themeId : R.style.LoadingDialogDefault,
                    cancelable,
                    cancelListener
            );
        }
    }

    private static final int DELAY = 150;
    private static final int DURATION = 1500;

    private int size;
    private CharSequence message;

    private LoadingDialog(Context context, String message, int theme, boolean cancelable, OnCancelListener cancelListener) {
        super(context, theme);
        this.message = message;

        setCancelable(cancelable);
        if (cancelListener != null) setOnCancelListener(cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.loading_dialog_layout);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void setMessage(CharSequence message) {
        this.message = message;

    }

}

