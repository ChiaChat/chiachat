package org.chiachat.app.toast

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

const val TOAST_TIMER_MS = 5000L

class ToastService {

  private val toastQueue = MutableStateFlow<List<Toast>>(listOf())

  private val toastFlow = flow {
    while (true) {
      // Get the first toast added to the queue, or null if empty
      val toast = toastQueue.value.firstOrNull()
      emit(toast)
      // Remove it from queue if not empty
      toast?.let {
        toastQueue.value -= it
        delay(TOAST_TIMER_MS)
      }
    }
  }

  val currentToast = MutableStateFlow<Toast?>(null)

  init {
    CoroutineScope(Dispatchers.Default).launch {
      toastFlow.collectLatest { currentToast.value = it }
    }
  }

  fun toast(toast: Toast) {
    toastQueue.value += toast
    toast.log()
  }

  fun error(message: String) = toast(Toast(ToastType.ERROR, message))

  fun success(message: String) = toast(Toast(ToastType.SUCCESS, message))

  fun debug(message: String) = toast(Toast(ToastType.DEBUG, message))

  fun info(message: String) = toast(Toast(ToastType.INFO, message))

  fun warning(message: String) = toast(Toast(ToastType.WARNING, message))
}
