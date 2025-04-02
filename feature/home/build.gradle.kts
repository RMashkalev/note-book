plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.google.devtools.ksp)
	alias(libs.plugins.compose.compiler)
}

android {
	namespace = "com.example.home"
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
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
	ksp {
		arg("KOIN_USE_COMPOSE_VIEWMODEL","true")
		arg("KOIN_CONFIG_CHECK", "true")
	}
}

dependencies {

	implementation(project(":component:ui"))
	implementation(project(":shared:notedatabase"))

	implementation(libs.koin.core)
	implementation(libs.koin.android)
	implementation(libs.koin.android.compose)
	implementation(libs.koin.annotations)
	implementation(libs.androidx.ui.tooling.preview.android)
	ksp(libs.koin.ksp.compiler)

	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.material3)
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}