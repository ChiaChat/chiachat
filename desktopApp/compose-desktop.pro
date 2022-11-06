-optimizationpasses 3
-overloadaggressively
-repackageclasses ''
-allowaccessmodification

-keep class app.cash.sqldelight.driver.jdbc.sqlite.** { *; }
-keep class org.sqlite.** { *; }