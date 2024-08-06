package com.siddharthchordia.myrepoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.siddharthchordia.myrepoapp.core.ui.theme.MyRepoAppTheme
import com.siddharthchordia.myrepoapp.ui.MyRepoApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MyRepoAppTheme {
                MyRepoApp(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                )
            }
        }
    }
}
