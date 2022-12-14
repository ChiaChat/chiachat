package org.chiachat.app.web

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

class WebModules {
  val scopes = module {
    factory(named("ioScope")) { CoroutineScope(Dispatchers.Default) }
    factory(named("vmScope")) { CoroutineScope(Dispatchers.Default) }
  }

  val all = scopes
}
