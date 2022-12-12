/*
 * Copyright 2020-2021 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

import android.content.Context
import androidx.compose.runtime.Composable
import com.soywiz.korio.android.AndroidCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.chiachat.app.SharedAppModules
import org.chiachat.app.ui.ComposeAppModules
import org.chiachat.app.ui.ComposeRoot
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module


class AndroidRoot(androidContext: Context) {
    private val root = ComposeRoot()
    private val androidModule = module {
        factory(named("ioScope")) {
            CoroutineScope(Dispatchers.IO + AndroidCoroutineContext(androidContext))
        }
        factory(named("vmScope")) {
            CoroutineScope(Dispatchers.Default + AndroidCoroutineContext(androidContext))
        }
    }

    init {
        startKoin {
            val composeModules = ComposeAppModules()
            val sharedModules = SharedAppModules()
            modules(androidModule + composeModules.all + sharedModules.all)
            allowOverride(false)
        }
    }

    @Composable
    fun View() {
        root.View()
    }

}