package pers.hbolin.webviewplugin;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.platform.PlatformView;

public class FlutterWebView implements PlatformView, MethodChannel.MethodCallHandler {
    private static final String TAG = "FlutterWebView";

    private Context mContext;
    private Registrar mRegistrar;
    private WebView mWebView;
    MethodChannel mMethodChannel;

    FlutterWebView(Context context, Registrar registrar, int id) {
        mContext = context;
        mRegistrar = registrar;
        // 创建WebView
        mWebView = createWebView(mRegistrar);
        // 接管方法管理
        mMethodChannel = new MethodChannel(registrar.messenger(), "ponnamkarthik/flutterwebview_" + id);
        mMethodChannel.setMethodCallHandler(this);
    }


    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (methodCall.method.equals("loadUrl")) {
            String url = methodCall.arguments.toString();
            Log.d(TAG, "onMethodCall: 参数：" + url);
            mWebView.loadUrl(url);
        }
    }

    @Override
    public View getView() {
        //mWebView.loadUrl("https://www.baidu.com/");
        Log.d(TAG, "getView: " + "返回web view ！！！");
        return mWebView;
        //TextView textView = new TextView(mContext);
        //textView.setText("HELLO ANDROID VIEW");
        //return textView;
    }

    @Override
    public void dispose() {

    }

    private WebView createWebView(Registrar registrar) {
        WebView webView = new WebView(registrar.context());
        initWebViewSetting(webView);
        //webView.setWebViewClient(new CustomWebViewClient());
        //webView.getSettings().setJavaScriptEnabled(true);
        return webView;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSetting(WebView webView) {
        // webView.setListener(this, this);
        WebSettings mSettings = webView.getSettings();
        // 支持获取手势焦点
        webView.requestFocusFromTouch();
        webView.setHorizontalFadingEdgeEnabled(true);
        webView.setVerticalFadingEdgeEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        // 支持JS
        mSettings.setJavaScriptEnabled(true);
        mSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mSettings.setBuiltInZoomControls(true);
        mSettings.setDisplayZoomControls(true);
        mSettings.setLoadWithOverviewMode(true);
        // 支持插件
        mSettings.setPluginState(WebSettings.PluginState.ON);
        mSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 自适应屏幕
        mSettings.setUseWideViewPort(true);
        mSettings.setLoadWithOverviewMode(true);
        // 支持缩放
        mSettings.setSupportZoom(false);//就是这个属性把我搞惨了，
        // 隐藏原声缩放控件
        mSettings.setDisplayZoomControls(false);
        // 支持内容重新布局
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mSettings.supportMultipleWindows();
        mSettings.setSupportMultipleWindows(true);
        // 设置缓存模式
        mSettings.setDomStorageEnabled(true);
        mSettings.setDatabaseEnabled(true);
        mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mSettings.setAppCacheEnabled(true);
        mSettings.setAppCachePath(webView.getContext().getCacheDir().getAbsolutePath());
        // 设置可访问文件
        mSettings.setAllowFileAccess(true);
        mSettings.setNeedInitialFocus(true);
        // 支持自定加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            mSettings.setLoadsImagesAutomatically(true);
        } else {
            mSettings.setLoadsImagesAutomatically(false);
        }
        mSettings.setNeedInitialFocus(true);
        // 设定编码格式
        mSettings.setDefaultTextEncodingName("UTF-8");
    }


}
