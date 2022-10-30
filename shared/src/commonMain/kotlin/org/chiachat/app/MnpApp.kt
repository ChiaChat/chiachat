package org.chiachat.app

import co.touchlab.kermit.Logger
import com.apollographql.apollo3.ApolloClient
import com.liftric.cognito.idp.IdentityProviderClient
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.chiachat.app.toast.ToastService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object MnpApp {

  private val region: String = "us-east-2"
  private val clientId: String = "6kcamhgfhilq4ue31jq02h51s5"
  private val cognitoClient = IdentityProviderClient(region, clientId)
  private val apolloClient: ApolloClient =
      ApolloClient.Builder().serverUrl("http://10.0.2.2:3000/graphql/").build()

  init {

    CoroutineScope(Dispatchers.Default).launch {
      Logger.i { "shared" }
      resourcesVfs["."].list().collect { Logger.i(it.path) }
      Logger.i {resourcesVfs["MR/base/colors.xml"].exists().toString() }
      Logger.i {resourcesVfs["fonts/OFL.txt"].exists().toString() }
    }
  }

  val appModule = module {
    single { cognitoClient }
    single { apolloClient }
    singleOf(::ToastService)
  }
}
