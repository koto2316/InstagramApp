package com.example.instagram.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(){

    /* LazyVerticalGrid
        縦方向にスクロールできるグリッドを作るComposable
      */
    LazyVerticalGrid(
        //固定横3列表示
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 300.dp), //最低限の高さを指定
        contentPadding = PaddingValues(2.dp)
    ){
        item(span ={ GridItemSpan(3)}){
            ProfileHeader()
        }

        //index = 投稿番号（0, 1, 2, …）
        items(30){ index ->
            Box(
                modifier = Modifier
                    //幅と高さの比を1:1にする＝正方形
                    .aspectRatio(1f)
                    .padding(2.dp)
                    .background(Color.LightGray),
                //中央揃え
                contentAlignment = Alignment.Center
            ){
                //マスの中に表示する内容
                Text("Post $index")
            }
        }
    }
}

@Composable
fun ProfileHeader(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Text("こと", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            /* Arrangement = 並び方
            　　Alignment = 揃え位置
             */
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround //周りに均等な余白
        ){
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray, shape = CircleShape)
            )
            ProfileStat(label = "投稿", count = "12")
            ProfileStat(label = "フォロワー", count = "34")
            ProfileStat(label = "フォロー", count = "56")

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