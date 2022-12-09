/*
 * Copyright 2020-2021 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.chiachat.app.SharedAppModules
import org.chiachat.app.ui.ComposeAppModules
import org.chiachat.app.ui.ComposeRoot
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module


class DesktopRoot() {
    private val root = ComposeRoot()
    private val desktopModule = module {
        factory(named("ioScope")) {
            CoroutineScope(Dispatchers.IO)
        }
        factory(named("vmScope")) {
            CoroutineScope(Dispatchers.Default)
        }
    }

    init {
        startKoin {
            val composeModules = ComposeAppModules()
            val sharedModules = SharedAppModules()
            modules(desktopModule + composeModules.all + sharedModules.all)
            allowOverride(false)
        }
    }

    @Composable
    fun View() {
        root.View()
    }

}