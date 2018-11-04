package pers.hbolin.webviewplugin;

import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * WebviewPlugin
 */
public class WebviewPlugin {
    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        // 新增返回
        registrar.platformViewRegistry().registerViewFactory("pers.hbolin.webviewplugin/webview", new FlutterWebViewFactory(registrar));
    }
}
