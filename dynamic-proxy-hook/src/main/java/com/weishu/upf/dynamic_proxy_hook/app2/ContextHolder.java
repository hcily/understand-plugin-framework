package com.weishu.upf.dynamic_proxy_hook.app2;

/**
 * Created by cathcool on 2017/12/1.
 */
import android.content.Context;

public class ContextHolder
{
    private static Context context = null;

    public static void setContext(Context context)
    {
        System.out.println("com.weishu.upf.dynamic_proxy_hook.app2.ContextHolder setContext");

        ContextHolder.context = context;
    }

    public static Context getContext()
    {
        System.out.println("com.weishu.upf.dynamic_proxy_hook.app2.ContextHolder getContext() context="
                + context);

        return context;
    }
}