package com.example.instagram.screen.editprofile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
/* 上の2行のimportがないと　
   var name by remember のbyが使えない
 */
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun EditProfileScreen(
    //名前：型 = 値
    navController : NavController,
    name : String, //String 文字列
    username : String,
    onSave: (String, String) -> Unit
    ) {

    //editName editUsername 編集後の名前・ユーザーネーム
    var editName by remember { mutableStateOf(name) }
    var editUsername by remember{ mutableStateOf(username) }

    //focusManagerを使うための宣言
    val focusManager = LocalFocusManager.current

    var year by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    val genderOptions = listOf("男性", "女性", "その他")
    var gender by remember { mutableStateOf("") }

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("プロフィール編集") },

                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "戻る"
                        )
                    }
                },

                windowInsets = WindowInsets(0)
            )
        }
    ){ padding ->


        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
            //各パーツの間を16.dpあける
        ) {

            //----------名前入力欄----------
            OutlinedTextField(
                value = editName,  //今入っている値
                onValueChange = { editName = it },
                //onValueChange 入力が終わったらnameに保存
                label = { Text("名前") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true, //改行禁止
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    //キーボードの右下を「次へ」にする　エンターで次の項目へ
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                        //focusManager.moveFocus() 次の入力欄へ移動
                        //Next　次の入力欄へ移動　Down　物理的に下の入力欄へ移動
                    }
                )
            )

            //ユーザーネーム入力
            OutlinedTextField(
                value = editUsername,
                onValueChange = { editUsername = it },
                label = { Text("ユーザーネーム") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )
            )

            //生年月日入力
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                //年
                OutlinedTextField(
                    value = year,
                    onValueChange = { year = it },
                    label = { Text("年") },
                    modifier = Modifier.weight(1f), //横幅を均等に分ける
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, //数字キーボード
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    )
                )

                //月
                OutlinedTextField(
                    value = month,
                    onValueChange = { month = it },
                    label = { Text("月") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    )
                )

                //日
                OutlinedTextField(
                    value = day,
                    onValueChange = { day = it },
                    label = { Text("日") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                        //Doneだと入力終了となり最初の項目に戻ることがあること
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Next) //キーボード閉じる
                        }
                    )
                )
            }

            //性別選択
            Text("性別")

            Column {

                //genderOprionsの中身を1つずつ取り出す
                genderOptions.forEach { option ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = gender == option,
                            //今のgenderと同じなら選択状態になる
                            onClick = {
                                gender = option
                                //押されたらgenderに保存
                            }
                        )

                        Text(text = option)
                    }
                }
            }

            Button(
                onClick = {
                    //親に更新をお願いする（AppNavigation）
                    onSave(editName, editUsername)

                    //前の画面に戻る
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("保存")


            }
        }
    }
}