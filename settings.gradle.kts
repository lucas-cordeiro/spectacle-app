rootProject.name = "Spectacle"

include(
    ":app",
    ":app:core:domain",
    ":app:core:data",
    ":app:core:ds"
)
include(":app:feature:movies")
include(":app:feature:login")
include(":app:feature:musics")
