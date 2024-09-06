dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Guajolotas"
include(":app")
include(":app:guajolota_models")
include(":domain:guajolota_models")
