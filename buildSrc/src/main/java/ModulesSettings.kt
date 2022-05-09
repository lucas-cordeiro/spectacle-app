import org.gradle.api.JavaVersion

const val APP_PREFIX = ":app"

object Modules {
    private const val CORE_PREFIX = "$APP_PREFIX:core"

    const val domain = "$CORE_PREFIX:domain"
    const val data = "$CORE_PREFIX:data"
    const val ds = "$CORE_PREFIX:ds"

    val javaVersion: JavaVersion = JavaVersion.VERSION_1_8
    const val useIR = true
    const val compose = true
    const val dataBinding = true
    const val viewBinding = true

    const val packagingOptions = "/META-INF/{AL2.0,LGPL2.1}"
}

object Features {
    private const val FEATURE_PREFIX = "$APP_PREFIX:feature"

    const val movies = "$FEATURE_PREFIX:movies"
    const val login = "$FEATURE_PREFIX:login"
}
