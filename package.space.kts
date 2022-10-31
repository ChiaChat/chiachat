job("Check Pull Request") {
  container(displayName = "Check Pull Request", image = "gradle:jdk17-jammy") {
    sequential {
        kotlinScript { api ->
            api.gradle("build")
        }
    }
  }
}
