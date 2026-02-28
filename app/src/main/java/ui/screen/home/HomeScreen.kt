package com.example.instagram.ui.screen.home
//Packageに書くのはフォルダまで
//クラス名や関数名は書いてはいけない

import com.example.instagram.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale

@Composable
fun HomeScreen(){
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
            Text(
                text = "Instagram",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
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
                    StoryItem(name = "user$index")
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
fun StoryItem(name: String){
    //ストーリー1つ分のUI
    Column(
        //中央揃え
        horizontalAlignment = Alignment.CenterHorizontally,
        //ストーリー同士の間隔
        modifier = Modifier.padding(8.dp)
    ){
        //ストーリーの丸いアイコン部分
        /* Box
        ・要素を別の要素の上に配置する
        ・重ねて子レイアウトを表示できる
         */

        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //次の投稿との余白
            .padding(bottom = 16.dp)
    ){
        //投稿者名
        Text(
            text = userName,
            //Instagramっぽい感じで太字で表示する
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.home_post_compressed),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "いいね！123件",
            modifier = Modifier.padding(8.dp)
        )

    }
}
