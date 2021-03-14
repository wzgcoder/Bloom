package com.example.androiddevchallenge

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.androiddevchallenge.ui.theme.MyTheme

class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       
        setContent {
            MyTheme {
                NavigationView()
            }
        }
    }

    @Composable
    fun NavigationView(){
        val items = listOf(
            Screen.Home,
            Screen.Favorites,
            Screen.ProFile,
            Screen.Cart,
        )
        val navController = rememberNavController()

        Surface(Modifier.background(color = MaterialTheme.colors.background)) {
            Column(verticalArrangement = Arrangement.Bottom ) {

                Column(modifier = Modifier.weight(1f)
                ) {
                    NavHost(navController, startDestination = Screen.Home.route) {
                        composable(Screen.Home.route) { Home() }
                        composable(Screen.Favorites.route) { Favorites() }
                        composable(Screen.ProFile.route) { ProFile() }
                        composable(Screen.Cart.route) { Cart() }
                    }
                }

                BottomNavigation(
                    modifier = Modifier.height(56.dp)
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                    items.forEach { screen ->
                        BottomNavigationItem(
                            selected = currentRoute == screen.route,
                            icon = {
                                Image(painter = painterResource(id = screen.imageResId), contentDescription = null)
                            },
                            label = {
                                Text(text = stringResource(id = screen.titleResId))
                            },
                            onClick = {
                                navController.navigate(screen.route)
                            }
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun Home(viewModel: HomeViewModel = HomeViewModel()){
    val textColor = MaterialTheme.colors.onPrimary
    val padding = 16.dp
   Column (
       Modifier
           .fillMaxSize()
           .verticalScroll(rememberScrollState())){
        //搜索
       OutlinedTextField(value = viewModel.searchText,
           onValueChange = { viewModel.searchText= it },
           modifier = Modifier
               .padding(padding, 40.dp, padding, 0.dp)
               .height(56.dp)
               .fillMaxWidth(),
           placeholder = {
               Text(text = "Search",
               style = body1TextStyle,
               color = textColor)
           },
           leadingIcon = {
               Image(painter = painterResource(id = R.drawable.ic_search),
                   contentDescription =null,
                   Modifier.size(18.dp)
               )
           }

       )

       Text(text = "Browse themes",
           Modifier
               .paddingFromBaseline(32.dp, 16.dp)
               .padding(padding, 0.dp),
       style = h1TextStyle,
           color = textColor)

       LazyRow(
           horizontalArrangement = Arrangement.spacedBy(8.dp),
           content = {
           items(viewModel.browseThemes){ item ->
                BrowseThemeItem(item)
           }
       })

        Row(
            verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(padding, 0.dp)
        ) {
            Text(text = "Design your home garden",
                Modifier.weight(1f)
                    .paddingFromBaseline(40.dp, 16.dp),
                style = h1TextStyle,
                color = textColor)
            Image(painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                Modifier.size(24.dp)
            )

        }


   }
}

@Composable
fun BrowseThemeItem(theme : BrowseTheme){
    Card(
        Modifier
            .clip(MaterialTheme.shapes.small)
            .size(136.dp)) {
        Column(verticalArrangement = Arrangement.Bottom) {
            NetImage(theme.image, Modifier.weight(1f))
            Text(text = theme.name,
                modifier = Modifier.height(40.dp).fillMaxWidth(),
                style  = h2TextStyle,
                textAlign = TextAlign.Center,
                color =  MaterialTheme.colors.onPrimary)
        }

    }
}
private const val TAG = "HomeActivity"
@Composable
fun NetImage(url : String,modifier :Modifier){
    var bitmap by remember { mutableStateOf<Bitmap?>(null)}
    Glide.with(LocalContext.current).asBitmap()
        .load("https://t7.baidu.com/it/u=2340400811,4174965252&fm=193&f=GIF")
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap = resource
                Log.e(TAG, "onLoadCleared: 图片下载成功" )
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                Log.e(TAG, "onLoadCleared: 图片下载失败" )
            }
        })

    if (bitmap !=null){
        Image(bitmap!!.asImageBitmap(),"null",modifier)
    }
}


@Composable
fun Favorites(){
    PlaceHolder(text = "Favorites"
    )
}

@Composable
fun ProFile(){
    PlaceHolder(text = "ProFile")
}

@Composable
fun Cart(){
    PlaceHolder(text = "Cart")
}

@Composable
fun PlaceHolder(text : String){
    Text(text = text,
        Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onPrimary)
}

