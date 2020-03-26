import 'dart:async';

import 'package:flutter/services.dart';

class MediaScanner {
  static const MethodChannel _channel = MethodChannel('media_scanner');

  static Future<void> scan(String path) async =>
      await _channel.invokeMethod('scan', path);
}
