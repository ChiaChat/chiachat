package org.chiachat.app.desktop

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

class DesktopModules {
  val scopes = module {
    factory(named("ioScope")) { CoroutineScope(Dispatchers.IO) }
    factory(named("vmScope")) { CoroutineScope(Dispatchers.Default) }
  }

  val all = scopes
}
