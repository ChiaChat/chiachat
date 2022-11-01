import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

class Test {

    val test = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
}