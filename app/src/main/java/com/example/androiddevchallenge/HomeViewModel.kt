package com.example.androiddevchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class BrowseTheme(var name : String,var image : String)
class HomeViewModel : ViewModel(){
    var searchText by mutableStateOf("")
    var browseThemes = mutableListOf<BrowseTheme>().apply {
        add(BrowseTheme("Desert chic","https://www.pexels.com/photo/assorted-color-flowers-2132227/"))
        add(BrowseTheme("Tiny terrariums","https://www.pexels.com/photo/clear-glass-terrarium-jar-with-mossy-plants-1400375/"))
        add(BrowseTheme("Jungle vibes","https://www.pexels.com/photo/big-green-leaves-of-monstera-5699665/"))
        add(BrowseTheme("Easy care","https://www.pexels.com/photo/green-plant-with-long-leaves-in-pot-at-home-6208086/"))
        add(BrowseTheme("Monstera","https://www.pexels.com/photo/green-swiss-cheese-plant-3097770/"))
        add(BrowseTheme("Aglaonema","https://www.pexels.com/photo/green-leaf-plant-on-white-stones-4751978/"))
        add(BrowseTheme("Peace lily","https://www.pexels.com/photo/delicate-spathiphyllum-cochlearispathum-flowers-with-fresh-green-leaves-in-garden-4425201/"))
        add(BrowseTheme("Fiddle leaf","https://www.pexels.com/photo/ficus-lyrata-with-lush-green-leaves-in-house-6208087/"))
        add(BrowseTheme("Snake plant","https://www.pexels.com/photo/photo-of-green-snake-house-plant-2123482/"))
        add(BrowseTheme("Pothos","https://www.pexels.com/photo/green-leaf-plant-1084199/"))
    }
}
