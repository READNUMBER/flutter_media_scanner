package com.jukqaz.media_scanner

import android.content.Context
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

public class MediaScannerPlugin(
    private val context: Context
) : MethodCallHandler {
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "media_scanner")
            channel.setMethodCallHandler(MediaScannerPlugin(registrar.activity()))
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "scan") {
            val ms = MediaScanner.newInstance(context)
            ms.mediaScanning(call.arguments as String)
            result.success(null)
        } else {
            result.notImplemented()
        }
    }
}
