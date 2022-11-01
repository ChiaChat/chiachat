/*
 * Copyright 2020-2022 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

// Use `xcodegen` first, then `open ./ComposeMinesweeper.xcodeproj` and then Run button in XCode.
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import kotlinx.cinterop.*
import org.chiachat.app.compose.ComposeRoot
import platform.Foundation.*
import platform.UIKit.*

fun main() {
  val args = emptyArray<String>()
  memScoped {
    val argc = args.size + 1
    val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
    autoreleasepool { UIApplicationMain(argc, argv, null, NSStringFromClass(SkikoAppDelegate)) }
  }
}

class SkikoAppDelegate : UIResponder, UIApplicationDelegateProtocol {
  companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

  @ObjCObjectBase.OverrideInit constructor() : super()

  private var _window: UIWindow? = null
  override fun window() = _window
  override fun setWindow(window: UIWindow?) {
    _window = window
  }

  override fun application(
      application: UIApplication,
      didFinishLaunchingWithOptions: Map<Any?, *>?
  ): Boolean {
    window = UIWindow(frame = UIScreen.mainScreen.bounds)
    val app = ComposeRoot()
    val mainComponent = ComposeRoot()
    window!!.rootViewController =
        Application("chiachat") {
          Column {
            Spacer(modifier = Modifier.size(48.dp))
            mainComponent.view()
          }
        }
    window!!.makeKeyAndVisible()
    return true
  }
}
