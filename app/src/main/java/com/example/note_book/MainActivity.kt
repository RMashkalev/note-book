package com.example.note_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.ui.compose.Screen

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			Screen {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					Text(
						text = "Hello user!",
						modifier = Modifier.padding(paddingValues = innerPadding)
					)
				}
			}
		}
	}
}