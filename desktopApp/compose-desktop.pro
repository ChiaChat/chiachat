-optimizationpasses 3
-overloadaggressively
-repackageclasses ''
-allowaccessmodification
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses,EnclosingMethod

-keep class app.cash.sqldelight.driver.jdbc.sqlite.** { *; }
-keep class org.sqlite.** { *; }
-keep class org.jetbrains.skia.** { *; }

-dontnote **
