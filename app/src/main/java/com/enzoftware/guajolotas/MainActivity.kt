package com.enzoftware.guajolotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.enzoftware.guajolotas.ui.navigation.Navigation
import com.enzoftware.guajolotas.ui.theme.GuajolotasTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
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

