package com.example.androiddevchallenge

import androidx.annotation.StringRes

sealed class Screen(val route : String ,@StringRes val titleResId: Int, val imageResId : Int) {
    object Home : Screen("Home", R.string.home,R.drawable.ic_home_selected)
    object Favorites : Screen("Favorites",R.string.home, R.drawable.ic_favorites_unselect)
    object ProFile : Screen("ProFile",R.string.profile, R.drawable.ic_account)
    object Cart : Screen("Cart",R.string.cart, R.drawable.ic_shopping)
}
