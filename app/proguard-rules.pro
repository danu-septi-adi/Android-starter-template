# Project-specific ProGuard rules
# Keep Retrofit interfaces
-keep,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Keep Kotlin Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.template.app.**$$serializer { *; }
-keepclassmembers class com.template.app.** {
    *** Companion;
}
-keepclasseswithmembers class com.template.app.** {
    kotlinx.serialization.KSerializer serializer(...);
}
