package com.example.instagram.ui.navigation

import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.example.instagram.ui.screen.home.HomeScreen
import com.example.instagram.ui.screen.profile.ProfileScreen
import com.example.instagram.ui.navigation.Screen


@Composable
fun AppNavigation(){

    //NavController:画面遷移を管理する司令塔
    val navController = rememberNavController()

    //Scaffold:画面の骨組み（下にナビ、上に中身など）
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ){padding->
        //NavHost:どの画面にいけるかを定義
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ){
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
        }

    }
}

@Composable
fun BottomBar(navController: NavController){
    NavigationBar{
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            }
        )
    }
}