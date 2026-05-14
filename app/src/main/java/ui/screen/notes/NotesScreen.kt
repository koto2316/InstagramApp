package com.example.instagram.ui.screen.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
//import com.example.アプリ名.R//
import com.example.instagram.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//byを使うためのimport
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle


@Composable
fun NotesScreen() {
    Box {
        var showEditor by remember { mutableStateOf(false) }
        //全体を縦スクロールできるようにLazyColumnの中に入れる
        /*  isEditing 入力中かどうか　false 普通表示
            noteText 入力内容
         */
        var isEditing by remember { mutableStateOf(false) }
        var noteText by remember { mutableStateOf("ひとこと") }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            //ユーザーネーム
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "koto_123",
                        fontSize = 20.sp, //sp フォントサイズ
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            /*  padding 要素の中の余白調整
            height 要素と要素の間を空けたい
         */
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {

                /*  remember 画面の状態を覚える仕組み
                Composableの中でしか使えない
             */
                var text by remember { mutableStateOf("") }
                //ユーザが文字列を入力・編集するためのUIコンポーネント
                TextField(
                    value = text, //今入力されてる文字
                    onValueChange = { newText ->
                        text = newText
                    }, //文字が変わったときに呼ばれる処理
                    placeholder = { Text("検索") },
                    //placeholder 入力されていないときに出る薄い文字
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    //改行なし
                    singleLine = true
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                LazyRow(
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    items(10) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {

                            Box(
                                contentAlignment = Alignment.TopCenter
                            ) {
                                //アイコン
                                Image(
                                    painter = painterResource(id = R.drawable.gelato_icon),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )

                                //自分のところのみ入力可能
                                if (it == 0) {

                                    //吹き出し
                                    Box(
                                        modifier = Modifier
                                            //上にずらす
                                            .offset(y = (-20).dp)
                                            .background(
                                                Color.DarkGray, shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                            .clickable {
                                                showEditor = true
                                                //true 入力中
                                            }
                                    ) {

                                        if (isEditing) {
                                            TextField(
                                                //今の文字を表示
                                                value = noteText,
                                                //入力されたら保存
                                                onValueChange = { noteText = it },
                                                textStyle = TextStyle(
                                                    color = Color.White,
                                                    fontSize = 12.sp
                                                ),
                                                singleLine = true,
                                                colors = TextFieldDefaults.colors(
                                                    focusedContainerColor = Color.Transparent,
                                                    unfocusedContainerColor = Color.Transparent
                                                )
                                            )
                                        } else {
                                            Text(
                                                text = noteText,
                                                color = Color.White,
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                } else {

                                    //他の人（普通表示）
                                    Box(
                                        modifier = Modifier
                                            .offset(y = (-20.dp))
                                            .background(
                                                Color.DarkGray,
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = "ひとこと",
                                            color = Color.White,
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "user$it",
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            //DMタイトル
            item {
                Text(
                    text = "メッセージ",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            //DM一覧
            items(10) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //アイコン
                    Image(
                        painter = painterResource(id = R.drawable.gelato_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "user$it",
                            fontWeight = FontWeight.Bold //太字
                        )

                        //最後のメッセージ
                        Text(
                            text = "既読",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                    }
                }
            }
        }

        if (showEditor) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.9f)),
                //背景透ける感じ
                contentAlignment = Alignment.Center//中央に配置
            ) {
                //Boxの中で何も指定しないと左上に表示される
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "閉じる",
                    modifier = Modifier
                        .align(Alignment.TopStart)//左上固定
                        .padding(16.dp)
                        .size(28.dp)
                        .clickable {
                            showEditor = false
                        }
                )

                Column(
                    //中央に配置
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.height(200.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        //アイコン
                        Image(
                            painter = painterResource(id = R.drawable.gelato_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                        )

                        Box(
                            modifier = Modifier
                                .offset(y = (-80).dp) //上にずらす（超重要！）
                                .background(
                                    Color.DarkGray,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            //入力欄
                            TextField(
                                value = noteText, //今の文字
                                onValueChange = { noteText = it }, //入力されたら更新

                                textStyle = TextStyle(
                                    color = Color.White,
                                    fontSize = 14.sp
                                ),

                                singleLine = true,

                                //背景を透明にする（これ重要）
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp), //間隔
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.MusicNote,
                        contentDescription = "音楽",
                        modifier = Modifier.size(28.dp)
                    )

                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "位置情報",
                        modifier = Modifier.size(28.dp)
                    )

                    Text(
                        text = "GIF",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}