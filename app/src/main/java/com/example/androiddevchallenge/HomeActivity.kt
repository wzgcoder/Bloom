package com.example.androiddevchallenge

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
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

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                NavigationView()
            }
        }
    }

    @Composable
    fun NavigationView() {
        val items = listOf(
            Screen.Home,
            Screen.Favorites,
            Screen.ProFile,
            Screen.Cart,
        )
        val navController = rememberNavController()

        Surface(Modifier.background(color = MaterialTheme.colors.background)) {
            Column(verticalArrangement = Arrangement.Bottom) {
                Column(
                    modifier = Modifier.weight(1f)
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
                                Image(
                                    painter = painterResource(id = screen.imageResId),
                                    contentDescription = null
                                )
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
fun Home(viewModel: HomeViewModel = HomeViewModel()) {
    val viewModel = HomeViewModel()
    LazyColumn(
        content = {
            item { Search(viewModel) }
            item { Title() }
            item { RowList(viewModel) }
            item { TitleAndFilter() }
            items(viewModel.imageListDatas) { item ->
                ImageListItem(item)
            }
        })


}


@Composable
fun RowList(viewModel: HomeViewModel = HomeViewModel()) {
    //横向列表
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            item {
                Text(
                    text = "",
                    Modifier
                        .height(136.dp)
                        .width(8.dp)
                        .background(color = Color.Transparent)
                )
            }
            items(viewModel.browseThemes) { item ->
                BrowseThemeItem(item)
            }
        })
}

@Composable
fun Search(viewModel: HomeViewModel) {
    //搜索
    OutlinedTextField(value = viewModel.searchText,
        onValueChange = { viewModel.searchText = it },
        modifier = Modifier
            .padding(16.dp, 40.dp, 16.dp, 0.dp)
            .height(56.dp)
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = "Search",
                style = body1TextStyle,
                color = MaterialTheme.colors.onPrimary
            )
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                Modifier.size(18.dp)
            )
        }
    )
}

@Composable
fun Title() {
    Text(
        text = "Browse themes",
        Modifier
            .paddingFromBaseline(32.dp, 16.dp)
            .padding(16.dp, 0.dp),
        style = h1TextStyle,
        color = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun TitleAndFilter() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp, 0.dp)
            .wrapContentHeight()
    ) {
        Text(
            text = "Design your home garden",
            Modifier
                .weight(1f)
                .paddingFromBaseline(40.dp, 16.dp),
            style = h1TextStyle,
            color = MaterialTheme.colors.onPrimary
        )
        Image(
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = null,
            Modifier
                .padding(top = 20.dp)
                .size(24.dp)
        )
    }
}

@Composable
fun ImageListItem(item: BrowseTheme) {
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NetImage(
            url = item.image,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.small)
        )
        Column(Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                ) {
                    Text(
                        text = item.name,
                        style = h2TextStyle,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .wrapContentSize()
                            .paddingFromBaseline(24.dp)
                    )
                    Text(
                        text = item.description,
                        style = body1TextStyle,
                        color = MaterialTheme.colors.onPrimary,
                    )
                }

                var checkedState by remember { mutableStateOf(false) }
                Checkbox(checked = checkedState,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colors.secondary,
                        checkmarkColor = MaterialTheme.colors.background
                    ),
                    onCheckedChange = {
                        checkedState = it
                    }
                )
            }

            Text(text = "",
            Modifier.height(1.dp)
                .padding(start = 8.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.onPrimary)
            )
        }
    }
}

@Composable
fun BrowseThemeItem(theme: BrowseTheme) {
    Card(
        Modifier.size(136.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Column(verticalArrangement = Arrangement.Bottom) {
            NetImage(
                theme.image,
                Modifier.weight(1f)
            )
            Text(
                text = theme.name,
                style = h2TextStyle,
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                color = MaterialTheme.colors.onPrimary
            )
        }

    }
}

private const val TAG = "HomeActivity"

@Composable
fun NetImage(url: String, modifier: Modifier) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    Glide.with(LocalContext.current).asBitmap()
        .load("https://t7.baidu.com/it/u=2340400811,4174965252&fm=193&f=GIF")
//        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })

    if (bitmap != null) {
        Image(
            bitmap!!.asImageBitmap(), "null",
            modifier,
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun Favorites() {
    PlaceHolder(
        text = "Favorites"
    )
}

@Composable
fun ProFile() {
    PlaceHolder(text = "ProFile")
}

@Composable
fun Cart() {
    PlaceHolder(text = "Cart")
}

@Composable
fun PlaceHolder(text: String) {
    Text(
        text = text,
        Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onPrimary
    )
}

