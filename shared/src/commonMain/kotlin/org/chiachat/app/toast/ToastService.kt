package org.chiachat.app.toast

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

const val TOAST_TIMER_MS = 5000L

class ToastService(
    val ioScope: CoroutineScope,
    val toastTimer: Long = TOAST_TIMER_MS,
    val logger: Logger? = null
) {

  val currentToast = MutableStateFlow<Toast?>(null)

  fun toast(toast: Toast) {
    currentToast.value = toast
    ioScope.launch {
      delay(toastTimer)
      if (currentToast.value == toast) {
        currentToast.value = null
      }
    }
    logger?.let(toast::log)
  }

  fun error(message: String) = toast(Toast(ToastType.ERROR, message))

  fun success(message: String) = toast(Toast(ToastType.SUCCESS, message))

  fun debug(message: String) = toast(Toast(ToastType.DEBUG, message))

  fun info(message: String) = toast(Toast(ToastType.INFO, message))

  fun warning(message: String) = toast(Toast(ToastType.WARNING, message))
}
