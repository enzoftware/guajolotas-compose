package com.enzoftware.guajolotas.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Surface
import com.enzoftware.guajolotas.ui.navigation.Navigation
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuajolotasTheme {
                Surface {
                    Navigation()
                }
            }
        }
    }
}

