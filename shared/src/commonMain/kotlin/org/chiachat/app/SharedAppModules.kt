package org.chiachat.app

import org.chiachat.app.toast.ToastService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object SharedAppModules {

  val sharedModule = module {
    singleOf(::ToastService)
  }
}
