package com.weishu.binder_hook.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;

/**
 * @author weishu
 * @date 16/2/15
 */
public class MainActivity extends Activity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            BinderHookHelper.hookClipboardService();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData data =ClipData.newPlainText("","CatHcool068409");
        clipboardManager.setPrimaryClip(data);

        EditText editText = new EditText(this);
        setContentView(editText);
    }
}
