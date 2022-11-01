package org.chiachat.app

import com.soywiz.korio.file.std.resourcesVfs
import org.chiachat.app.db.DbService
import org.chiachat.app.toast.ToastService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object SharedAppModules {
  val sharedModule = module {
    singleOf(::ToastService)
    singleOf(::DbService)
    resourcesVfs
  }
}
