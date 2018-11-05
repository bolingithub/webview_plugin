import 'package:flutter/material.dart';
import 'package:webview_plugin/webview_plugin.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: FlutterWebView(onWebCreated: (controller){
          controller.loadUrl("http://storybank.one:3001/");
        },),
      ),
    );
  }
}
