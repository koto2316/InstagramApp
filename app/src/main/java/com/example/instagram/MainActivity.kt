package com.example.instagram
//責務：起動だけ（UIは書かない）

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.instagram.ui.navigation.AppNavigation
import com.example.instagram.ui.navigation.Screen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AppNavigation()
        }
    }
}

@Composable
fun BottomBar(navController: NavController){
    //下に並べる画面
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Reels,
        Screen.Notes,
        Screen.Profile
    )

    NavigationBar{
        val currentRoute =
            navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach{ screen->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = screen.route
                    )
                }
            )
        }
    }
}