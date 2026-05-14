package com.example.instagram.ui.screen.story

import android.R.attr.progress
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import com.example.instagram.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun StoryScreen(
    navController: NavController
){
    var isLiked by remember{ mutableStateOf(false) }

    //進行度を管理する変数　0f=0% 1f=100%
    //Animatable 0~1の値を時間をかけて変化させる
    val progress = remember{ Animatable(0f) }

    //画面が表示されたら5秒後にホーム画面に戻る
    /* LaunchedEffect(Unit)
       ＝画面が「最初に表示されたとき」に1回だけ実行される
     */
    LaunchedEffect(Unit){
        progress.animateTo(
            targetValue = 1f, //最後まで進む
            animationSpec = tween(
                durationMillis = 5000
            )
        )
        navController.popBackStack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.story_post),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
            //画面いっぱいにトリミング表示
        )

        //上部の進行バー
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter) //上中央に配置
                .fillMaxWidth()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .height(4.dp) //バーの細さ
                .background(Color.White.copy(alpha = 0.3f))
                //alpha 透明度　0.3＝かなり薄い
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    //fillMaxWidth(progress.value) 幅を割合で指定できる
                    .fillMaxWidth(progress.value) //超重要
                    .background(Color.White)
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.TopStart) //左上に表示
                .padding(16.dp) //端からの余白
        ){
            Box(
                modifier = Modifier
                    .size(40.dp) //アイコンのサイズ
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(8.dp)) //アイコンと名前の感覚

            Text(
                text = "user0",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter) //下中央
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            Box(
                modifier = Modifier
                    .weight(1f) //横幅いっぱい使う
                    .height(40.dp)
                    .background(
                        Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.CenterStart
            ){
                Text(
                    text = "メッセージを送信",
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                imageVector =
                    if(isLiked)
                        Icons.Default.Favorite //押した後→塗りつぶし
                    else
                        Icons.Default.FavoriteBorder, //押す前→枠だけ
                contentDescription = null,
                tint = if(isLiked) Color.Red else Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        isLiked = !isLiked //押すたびに反転
                    }
              )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

    }
}