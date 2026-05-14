package com.example.instagram.ui.screen.home
//Packageに書くのはフォルダまで
//クラス名や関数名は書いてはいけない

import com.example.instagram.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Repeat
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.example.instagram.ui.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController
){
    /*Column + verticalScroll 学習用・小規模用
    要素が少ないときに使う、わかりやすい
    投稿100件などは重くなる
     */
    /*LazyColumn 画面に見えている分だけ描画する
    →高速、メモリ効率がよい、無限スクロールが可能
     */
    LazyColumn(
        /* modifier
           書く順番によって結果が変わる
           ・背景
           ・padding
           ・height/width/size
           ・形のクリップ　など
         */
        modifier = Modifier.fillMaxSize()
    ){
        //上部タイトル
        item{
            /* 中央揃え
               Boxの横幅をいっぱいにしてから中央に配置
             */
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Instagram",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        //ストーリー（横スクロール）
        /*item
        1つの静的なコンテンツ（テキスト、画像、広告など）をリストに追加
        単一の大きなセクションに利用

        最初の固定の見出しや最後の固定のフッターを入れる場合
         */
        item{
            LazyRow(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            ){
                items(10){ index ->
                    StoryItem(
                        name = "user$index",
                        index = index,
                        navController = navController)
                }
            }
        }

        //投稿一覧
        /*items
        大量のデータでもパフォーマンスを維持しながらスクロール可能なリストを作成

        繰り返しの多いデータ（商品リストやメッセージなど）を表示する場合
        パフォーマンス最適化を行う
         */
        items(5){index ->
            PostItem(userName = "user$index")
        }
    }
}

@Composable
fun StoryItem(
    name: String,
    index: Int,
    navController: NavController
){
    //ストーリー1つ分のUI
    Column(
        //中央揃え
        horizontalAlignment = Alignment.CenterHorizontally,
        //ストーリー同士の間隔
        modifier = Modifier
            .padding(8.dp)
            .then(
                if(index < 3){
                    Modifier.clickable{
                        navController.navigate(Screen.Story.route)
                    }
                }else{
                    Modifier
                }
            )
    ){
        //ストーリーの丸いアイコン部分
        /* Box
        ・要素を別の要素の上に配置する
        ・重ねて子レイアウトを表示できる
         */

        /* Image
           実際の画像をアイコンに使うとき
         */

        Image(
            painter = painterResource(id = R.drawable.gelato_icon),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape), //〇アイコン
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = name,
            fontSize = 12.sp
        )
    }
}


@Composable
fun PostItem(userName: String){
    //投稿1件分のUI全体

    var liked by remember { mutableStateOf(false) }
    var likeCount by remember { mutableStateOf(123)}
    var saved by remember { mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxWidth()
            //次の投稿との余白
            .padding(bottom = 16.dp)
    ){

        //アイコン＋ユーザーネーム
       Row(
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier.padding(8.dp)
       ){
           Image(
               painter = painterResource(id = R.drawable.gelato_icon),
               contentDescription = null,
               modifier = Modifier
                   .size(32.dp)
                   .clip(CircleShape),
               contentScale = ContentScale.Crop
           )

           Spacer(modifier = Modifier.width(8.dp))

           Text(
               text =userName,
               fontWeight = FontWeight.Bold
           )
        }
        //投稿
        Image(
            painter = painterResource(id = R.drawable.home_post_compressed),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            //いいね
            /* Icon + clickableよりもIconButtonのほうが主流
               IconButtonはMaterialデザインの標準ボタン
             */
            Icon(
                imageVector =
                    if(liked) Icons.Filled.Favorite
                    else Icons.Outlined.FavoriteBorder,

                contentDescription = "Like",

                modifier = Modifier
                    .size(24.dp)
                    .clickable{
                        liked = !liked

                        if(liked){
                            likeCount += 1
                        }else{
                            likeCount -= 1
                        }
                    },

                tint =
                    if(liked) Color.Red
                    else MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text("$likeCount")

            Spacer(modifier = Modifier.width(16.dp))

            //コメント
            Icon(
                imageVector = Icons.Outlined.ChatBubbleOutline,
                contentDescription = "Comment",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text("10")

            Spacer(modifier = Modifier.width(16.dp))

            //共有
            Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = "Share",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text("24")

            Spacer(modifier = Modifier.width(16.dp))

            //リポスト
            Icon(
                imageVector = Icons.Outlined.Repeat,
                contentDescription = "Repost",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text("5")

            //右端に押し出す
            Spacer(modifier = Modifier.weight(1f))

            //保存アイコン
            Icon(
                imageVector =
                    if(saved) Icons.Filled.Bookmark
                    else Icons.Outlined.BookmarkBorder,
                contentDescription = "Save",
                modifier = Modifier
                    .size(24.dp)
                    .clickable{
                        saved = !saved
                    },
                tint =
                if(saved) Color.Red
                else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
