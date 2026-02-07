package com.example.instagram.ui.navigation

//sealed class:決められた画面だけを使えるようにする仕組み
sealed class Screen(val route:String){
    //各画面の移動用の名前
    object Home: Screen("home")
    object Notes: Screen("notes")
    object Search: Screen("search")
    object Reels: Screen("reels")
    object Profile: Screen("profile")
}