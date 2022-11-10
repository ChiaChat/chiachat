package org.chiachat.app.android

import android.content.Context
import com.soywiz.korio.android.AndroidCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

class AndroidModules(androidContext: Context) {
  val ioContext = Dispatchers.IO + AndroidCoroutineContext(androidContext)
  val vmContext = Dispatchers.Default + AndroidCoroutineContext(androidContext)

  val context = module { single { androidContext } }

  val scopes = module {
    factory(named("ioScope")) { CoroutineScope(ioContext) }
    factory(named("vmScope")) { CoroutineScope(vmContext) }
  }

  val all = context + scopes
}
