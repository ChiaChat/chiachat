package org.chiachat.app

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import net.folivo.trixnity.client.MatrixClient
import net.folivo.trixnity.client.fromStore
import net.folivo.trixnity.client.store.repository.createInMemoryRepositoriesModule
import org.chiachat.app.db.DbService
import org.chiachat.app.toast.ToastService
import org.koin.core.qualifier.named
import org.koin.dsl.module

class SharedAppModules(driver: SqlDriver) {
//  val repositoriesModule = createInMemoryRepositoriesModule()
//  val matrixClient = MatrixClient.fromStore(
//  )

  val services = module {
    single { DbService(driver) }
    single { ToastService(ioScope = get(named("ioScope")), logger = Logger) }
  }

  val all = services
}
