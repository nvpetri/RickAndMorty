package br.senai.sp.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.senai.sp.rickandmorty.ui.theme.RickAndMortyTheme
import br.senai.sp.rickandmorty.screens.CharacterDetails
import br.senai.sp.rickandmorty.screens.CharacterList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                CharacterList()
            }
        }
    }
}
