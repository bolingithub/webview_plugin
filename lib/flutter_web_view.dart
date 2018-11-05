import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

typedef void WebViewCreatedCallback(WebController controller);

class FlutterWebView extends StatefulWidget {
  final WebViewCreatedCallback onWebCreated;

  FlutterWebView({this.onWebCreated});

  @override
  _FlutterWebViewState createState() => _FlutterWebViewState();
}

class _FlutterWebViewState extends State<FlutterWebView> {
  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
        viewType: 'pers.hbolin.webviewplugin/webview',
        onPlatformViewCreated: onPlatformCreated,
//        creationParamsCodec: const StandardMessageCodec(),
//        gestureRecognizers: [new VerticalDragGestureRecognizer(), new HorizontalDragGestureRecognizer(), new TapGestureRecognizer()],
      );
    }
    return new Text(
        '$defaultTargetPlatform is not yet supported by the maps plugin');
  }

  Future<void> onPlatformCreated(id) async {
    if (widget.onWebCreated == null) {
      return;
    }
    widget.onWebCreated(new WebController.init(id));
  }
}

class WebController {
  MethodChannel _channel;

  WebController.init(int id) {
    _channel = new MethodChannel('ponnamkarthik/flutterwebview_$id');
  }

  Future<void> loadUrl(String url) async {
    assert(url != null);
    return _channel.invokeMethod('loadUrl', url);
  }
}
