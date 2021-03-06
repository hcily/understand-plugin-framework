package com.weishu.upf.dynamic_proxy_hook.app2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.weishu.upf.dynamic_proxy_hook.app2.hook.HookHelper;

/**
 * @author weishu
 * @date 16/1/28
 */
public class MainActivity extends Activity {

    static final String ACTION = "com.weishu.upf.demo.app2.PLUGIN_ACTION";
    CustomerReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: 16/1/28 支持Activity直接跳转请在这里Hook
        // 家庭作业,留给读者完成.
        try {
            HookHelper.attachActivity(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button tv = new Button(this);
        //tv.setTextColor(Color.WHITE);
        tv.setText("测试界面");

        setContentView(tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("http://www.baidu.com"));

                // 注意这里使用的ApplicationContext 启动的Activity
                // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
                // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
                // 比较简单, 直接替换这个Activity的此字段即可.
                //startActivity(intent);
                //getApplicationContext().startActivity(intent);

                ContextHolder.getContext().sendBroadcast(new Intent(ACTION));
            }
        });


        int myProcessID = android.os.Process.myPid();
        System.out.println("====>>>2" + myProcessID);

        mReceiver = new CustomerReceiver();
        ContextHolder.getContext().registerReceiver(mReceiver, new IntentFilter(ACTION));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            // 在这里进行Hook
            HookHelper.attachContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class CustomerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(ContextHolder.getContext(), "收到广播啦！", Toast.LENGTH_SHORT).show();
        }
    }
}
