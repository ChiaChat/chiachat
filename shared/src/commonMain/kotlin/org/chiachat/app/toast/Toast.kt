package org.chiachat.app.toast

import co.touchlab.kermit.Logger

data class Toast(
    val type: ToastType,
    val message: String,
) {
  fun log(logger: Logger) {
    when (type) {
      ToastType.ERROR -> logger.e(message)
      ToastType.WARNING -> logger.w(message)
      ToastType.INFO -> logger.i(message)
      ToastType.SUCCESS -> logger.i(message)
      ToastType.DEBUG -> logger.d(message)
    }
  }
}
