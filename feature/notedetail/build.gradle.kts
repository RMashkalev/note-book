plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.google.devtools.ksp)
	alias(libs.plugins.compose.compiler)
}

android {
	namespace = "com.example.notedetail"
	compileSdk = 34

	defaultConfig {
		minSdk = 26

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
}

dependencies {
	implementation(project(":component:ui"))
	implementation(project(":shared:notedatabase"))

	implementation(libs.koin.core)
	implementation(libs.koin.android)
	implementation(libs.koin.android.compose)
	implementation(libs.koin.annotations)
	ksp(libs.koin.ksp.compiler)

	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.material3)
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.ui.tooling.preview.android)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}