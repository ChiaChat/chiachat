package org.chiachat.app

import co.touchlab.kermit.Logger
import org.chiachat.app.db.DbService
import org.chiachat.app.toast.ToastService
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

object SharedAppModules {
  val sharedModule = module {
    single { ToastService(ioScope = get(named("ioScope")), logger = Logger) }
    singleOf(::DbService)
  }
}
