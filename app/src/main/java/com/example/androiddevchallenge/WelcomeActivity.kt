/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }

    // Start building your app here!
    @Composable
    fun MyApp() {
        Surface(color = MaterialTheme.colors.primary) {
            Box(modifier = Modifier.fillMaxSize()){
                Image(painter = painterResource(id = R.drawable.welcome_bg), contentDescription =null)
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id = R.drawable.welcome_illos),
                        contentDescription = null,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(88.dp, 72.dp, 0.dp, 48.dp),)
                    Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
                    Text(text = "Beautiful home garden solutions",
                        Modifier.paddingFromBaseline(32.dp,40.dp),
                        fontSize = 16.sp)
                    Button(onClick = { /*TODO*/ },
                        Modifier
                            .padding(16.dp, 0.dp, 16.dp, 8.dp)
                            .height(48.dp)
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.secondary,
                        )) {
                        Text(text = "Create account",
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.background,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    LoginButton()

                }
            }
        }
    }

    @Composable
    fun LoginButton(){
        Text(text = "Log in",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.wrapContentSize()
                .clickable {
                    this@WelcomeActivity.startActivity(Intent(this@WelcomeActivity,LoginActivity::class.java))
                })
    }
}




@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
    }
}





