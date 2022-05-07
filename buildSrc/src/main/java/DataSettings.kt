private object DataVersions {
    const val ktor = "1.5.3"
    const val datastore = "1.0.0"
    const val protobuf = "3.11.0"
    const val room = "2.4.0"
}

object DataLibs {
    const val ktor = "io.ktor:ktor-client-okhttp:${DataVersions.ktor}"
    const val ktorGson =  "io.ktor:ktor-client-gson:${DataVersions.ktor}"
    const val ktorLog = "io.ktor:ktor-client-logging-jvm:${DataVersions.ktor}"
    const val ktorAuth = "io.ktor:ktor-client-auth:${DataVersions.ktor}"

    const val roomRuntime = "androidx.room:room-runtime:${DataVersions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${DataVersions.room}"
    const val roomKtx = "androidx.room:room-ktx:${DataVersions.room}"
    
    const val datastore = "androidx.datastore:datastore:${DataVersions.datastore}"
    const val datastorePreferences = "androidx.datastore:datastore-preferences:${DataVersions.datastore}"
    const val protobufJavaLite = "com.google.protobuf:protobuf-javalite:${DataVersions.protobuf}"
    const val protobufProto = "com.google.protobuf:protoc:${DataVersions.protobuf}"
}