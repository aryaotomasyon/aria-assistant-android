
plugins { id("com.android.application"); id("org.jetbrains.kotlin.android") }
android {
  namespace = "com.arya.assistant"
  compileSdk = 34
  defaultConfig { applicationId = "com.arya.assistant"; minSdk = 24; targetSdk = 34; versionCode = 1; versionName = "1.0" }
  buildTypes { getByName("release") { isMinifyEnabled = false } }
  buildFeatures { compose = true }
  composeOptions { kotlinCompilerExtensionVersion = "1.5.14" }
}
dependencies {
  val bom = platform("androidx.compose:compose-bom:2024.06.00")
  implementation(bom); androidTestImplementation(bom)
  implementation("androidx.core:core-ktx:1.13.1")
  implementation("androidx.activity:activity-compose:1.9.1")
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3:1.2.1")
  implementation("com.opencsv:opencsv:5.9")
  debugImplementation("androidx.compose.ui:ui-tooling")
}
