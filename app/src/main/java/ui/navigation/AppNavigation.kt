package com.example.instagram.ui.navigation

import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.platform.LocalFocusManager
import com.example.instagram.screen.editprofile.EditProfileScreen
import com.example.instagram.ui.screen.home.HomeScreen
import com.example.instagram.ui.screen.profile.ProfileScreen
import com.example.instagram.ui.screen.notes.NotesScreen
import com.example.instagram.ui.screen.reels.ReelsScreen
import com.example.instagram.ui.screen.search.SearchScreen


@Composable
fun AppNavigation(){

    //NavController:画面遷移を管理する司令塔
    val navController = rememberNavController()

    //remember 画面が再描写されても値を保持する
    //mutableStateOf 値が変わると画面も更新される
    var name by remember { mutableStateOf("こと") }
    var username by remember { mutableStateOf("koto_123") }

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

            composable(Screen.Reels.route){
                ReelsScreen()
            }

            composable(Screen.Notes.route){
                NotesScreen()
            }

            composable(Screen.Search.route){
                SearchScreen()
            }

            composable(Screen.Profile.route){
                ProfileScreen(
                    navController = navController,
                    name = name,
                    username = username
                )
            }

            composable(Screen.EditProfile.route){
                EditProfileScreen(
                    navController = navController,
                    name = name,
                    username = username,
                    onSave = { newName, newUsername ->
                        name = newName
                        username = newUsername
                    }
                )
            }
        }

    }
}

@Composable
fun BottomBar(navController: NavController){

    /* currentRoute 今どの画面が表示されているかを取得
    　　NavController　
    　　・どの画面にいるか
    　　・次にどこに行くか　を管理
     */
    val currentRoute = navController
        .currentBackStackEntryAsState()
        //画面遷移の履歴(BackStack)をStateとして監視
        .value
        //今表示されている画面の情報
        ?.destination
        //その画面のroute(home, profile)
        ?.route

    /* NavigationBar
       画面下に表示されるナビゲーションバー(下の5つのアイコン)
       コードの順で並び順も決まる（左から）
     */
    NavigationBar{
        //NavigationBarItem ナビゲーションバーのなかの1つのボタン

        //1　Homeボタン
        NavigationBarItem(
            /*　selected
            　　今表示されている画面がHomeならtrue
　　　　　　　　  →アイコンが選択中の見た目になる
             */
            selected =currentRoute == Screen.Home.route,
            /* onclick
            　　ボタンを押したときに何をするか
             */
            onClick = {
                navController.navigate(Screen.Home.route){
                    /* LaunchSingleTop（※重要）
                       同じ画面を何回も重ねて開かないようにする
                     */
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    /* Icons.Default.でCtrl + Space
                    　　→一覧が表示される
                    　　
                    　　「Material Icons」で検索
                     */
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            }
        )

        //2　Reelsボタン
        NavigationBarItem(
            selected = currentRoute == Screen.Reels.route,
            onClick = {
                navController.navigate(Screen.Reels.route){
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Reels"
                )
            }
        )

        //3　Notesボタン
        NavigationBarItem(
            selected = currentRoute == Screen.Notes.route,

            onClick = {
                navController.navigate(Screen.Notes.route){
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Notes"
                )
            }
        )

        //4　Searchボタン
        NavigationBarItem(
            selected = currentRoute == Screen.Search.route,

            onClick = {
                navController.navigate(Screen.Search.route){
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        )

        //5　Profileボタン
        NavigationBarItem(
            selected = currentRoute == Screen.Profile.route,

            onClick = {
                navController.navigate(Screen.Profile.route){
                    launchSingleTop = true
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile"
                )
            }
        )
    }
}