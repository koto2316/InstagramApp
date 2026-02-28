package com.example.instagram.ui.screen.profile

import com.example.instagram.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instagram.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController : NavController,
    name: String,
    username: String) {

    //navController 画面遷移管理

    /* Composeは親から子へしかデータを渡せない
       AppNavigation→ProfileScreen→ProfileHeader
       すべてにNaviController
     */

    //Scaffold 画面の骨組み
    /* Scaffold(
            topBar = {}, 上のバー
            bottomBar = {},　下のバー
            floatingActionButton = {},
       ){ content }
     */
    Scaffold(

        topBar = {
            /* CenterAlignedTopAppBar
               =中央揃えのTopAppBar
             */
            CenterAlignedTopAppBar(
                title = { Text(text = username) },
                windowInsets = WindowInsets(0)
                //ステータスバーの余白いらない
            )
        }
    ) { padding ->
        //paddingを必ず適用（重要）

        /* LazyVerticalGrid
        縦方向にスクロールできるグリッドを作るComposable
      */
        LazyVerticalGrid(
            //固定横3列表示
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(padding)
                //*** ↑Scaffold使うときは必須！ ***

                .fillMaxWidth() ,
            contentPadding = PaddingValues(2.dp)
        ) {
            item(span = { GridItemSpan(3) }) {
                ProfileHeader(
                    navController, username, name
                )
            }

            //index = 投稿番号（0, 1, 2, …）
            items(30) { index ->
               Image(
                   painter = painterResource(id = R.drawable.profile_post_square_adjusted_compressed),
                   contentDescription = null,
                   modifier = Modifier
                       .aspectRatio(1f)
                       .padding(2.dp),
                   contentScale = ContentScale.Crop
               )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileHeader(
    navController : NavController,
    username: String, //上中央に表示するユーザーネーム
    name: String  //投稿数の上に表示する名前
) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                /* Arrangement = 並び方
            　　Alignment = 揃え位置
             */
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround //周りに均等な余白
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Gray, shape = CircleShape)
                )
                ProfileStat(label = "投稿", count = "12")
                ProfileStat(label = "フォロワー", count = "34")
                ProfileStat(label = "フォロー", count = "56")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.EditProfile.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("プロフィールを編集")

        }
    }
}


@Composable
fun ProfileStat(label: String, count: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(count, style = MaterialTheme.typography.titleMedium)
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}