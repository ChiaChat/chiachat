package com.dialexa.mnp

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
class Greeting {
    fun greeting(): String {
        return "Hello, chiachat!"
    }
}
