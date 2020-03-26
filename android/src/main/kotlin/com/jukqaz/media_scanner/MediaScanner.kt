package com.jukqaz.media_scanner

import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import android.util.Log

class MediaScanner(
    private val context: Context
) {
    companion object {
        @JvmStatic
        fun newInstance(context: Context) = MediaScanner(context)
    }

    private var path: String? = null
    private var mediaScanner: MediaScannerConnection? = null
    private var mediaScannerClient: MediaScannerConnection.MediaScannerConnectionClient? = null

    fun mediaScanning(path: String) {
        if (mediaScanner == null) {
            mediaScannerClient = object : MediaScannerConnection.MediaScannerConnectionClient {
                override fun onMediaScannerConnected() {
                    mediaScanner?.scanFile(this@MediaScanner.path, null)
                }

                override fun onScanCompleted(path: String?, uri: Uri?) {
                    Log.d(MediaScanner::class.simpleName, "onScanCompleted")
                }
            }
            mediaScanner = MediaScannerConnection(context, mediaScannerClient)
        }
        this.path = path
        mediaScanner?.connect()
    }
}
