package org.chiachat.app.compose.components

import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

abstract class ViewModel : KoinComponent {
  val vmScope: CoroutineScope by inject(named("vmScope"))
  val ioScope: CoroutineScope by inject(named("ioScope"))
}
