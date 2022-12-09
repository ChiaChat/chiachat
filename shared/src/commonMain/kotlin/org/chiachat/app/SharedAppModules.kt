package org.chiachat.app

//import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
//import org.chiachat.app.db.DbService
import org.chiachat.app.toast.ToastService
import org.chiachat.app.util.MpSettings
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class SharedAppModules() {
//    val repositoriesModule = createInMemoryRepositoriesModule()
//    val matrixClient = MatrixClient.fromStore(
//    )

  val services = module {
//    single { DbService(driver) }
    single { ToastService(ioScope = get(named("ioScope")), logger = Logger) }
    singleOf(::MpSettings)
  }

  val all = services
}