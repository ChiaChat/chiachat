package org.chiachat.app.toast

import co.touchlab.kermit.Logger

data class Toast(
    val type: ToastType,
    val message: String,
) {
  fun log() {
    when (type) {
      ToastType.ERROR -> Logger.e(message)
      ToastType.WARNING -> Logger.w(message)
      ToastType.INFO -> Logger.i(message)
      ToastType.SUCCESS -> Logger.i(message)
      ToastType.DEBUG -> Logger.d(message)
    }
  }
}
