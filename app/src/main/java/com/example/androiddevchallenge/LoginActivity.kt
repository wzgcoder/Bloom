package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MyTheme{
                LoginView()
            }
        }
    }


    @Composable
    fun LoginView(loginViewModel: LoginViewModel = LoginViewModel()){
        var context  = LocalContext.current
        Surface(Modifier.background(color = MaterialTheme.colors.background)) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                    .padding(16.dp,0.dp)
                    ){
                val textColor = MaterialTheme.colors.onPrimary
                Text(text = "Log in with email",
                    Modifier.paddingFromBaseline(184.dp),
                    color = textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)

                val editTextColors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = textColor,
                    placeholderColor = textColor)
                OutlinedTextField(value = loginViewModel.email,
                    onValueChange = { loginViewModel.onEmailChange(it) },
                    textStyle = TextStyle(
                        color = textColor,
                        fontSize = 14.sp
                    ),
                    placeholder = {
                        Text(text = "Email address",
                            fontSize = 14.sp,
                            color = textColor,
                            fontWeight = FontWeight.Light)
                    },
                    modifier = Modifier.height(56.dp)
                        .fillMaxWidth(),
                    colors =editTextColors )
                OutlinedTextField(value = loginViewModel.password,
                    onValueChange = { loginViewModel.onPasswordChange(it) },
                    textStyle = TextStyle(
                        color = textColor,
                        fontSize = 14.sp
                    ),
                    placeholder = {
                        Text(text = "Password(8+characters)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light)
                    },
                    modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)
                        .height(56.dp)
                        .fillMaxWidth(),
                    colors = editTextColors)
                val annotatedText  = buildAnnotatedString {
                    withStyle(style = ParagraphStyle(TextAlign.Center)) {
                        append("By clicking below, you agree to our ")
                        pushStringAnnotation(tag = "Terms of Use",
                        annotation = "https://developer.android.com/jetpack/compose/text")
                        withStyle(style = SpanStyle(
                            textDecoration = TextDecoration.Underline
                        )) {
                            append("Terms of Use")
                        }
                        pop()
                        append(" and consent to our ")

                        pushStringAnnotation(tag = "Privacy Policy",
                            annotation = "https://developer.android.com/jetpack/compose/graphics")
                        withStyle(style = SpanStyle(
                            textDecoration = TextDecoration.Underline
                        )) {
                            append("Privacy Policy")
                        }
                        pop()
                    }
                }
                ClickableText(text = annotatedText,
                modifier = Modifier.paddingFromBaseline(24.dp,16.dp),
                style = TextStyle(
                    color = textColor
                )){
                    annotatedText.getStringAnnotations(tag = "Terms of Use", start = it,
                        end = it)
                        .firstOrNull()?.let { annotation ->
                            Toast.makeText(context,"Terms of Use",Toast.LENGTH_SHORT).show()
                        }
                    annotatedText.getStringAnnotations(tag = "Privacy Policy", start = it,
                        end = it)
                        .firstOrNull()?.let { annotation ->
                            Toast.makeText(context,"Privacy Policy",Toast.LENGTH_SHORT).show()
                        }
                }

                Button(onClick = {
                      startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                },
                    Modifier.height(48.dp)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                    )) {
                    Text(text = "Log in",
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.background,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }


}



