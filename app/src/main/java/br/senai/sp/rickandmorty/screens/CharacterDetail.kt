package br.senai.sp.rickandmorty.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.senai.sp.rickandmorty.model.Character
import br.senai.sp.rickandmorty.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CharacterDetails(modifier: Modifier = Modifier){
    var id by remember {
        mutableStateOf("")
    }

    var character by remember {
        mutableStateOf(Character())
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .padding(16.dp)
        ){
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth(),
                value = id,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    id = it
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            val callCharacter = RetrofitFactory()
                                .getCharacterService()
                                .getCharacterById(id.toInt())

                            callCharacter.enqueue(object : Callback<Character> {
                                override fun onResponse(
                                    call: Call<Character>,
                                    response: Response<Character>
                                ) {
                                    character = response.body()!!
                                }

                                override fun onFailure(
                                    call: Call<Character>,
                                    response: Throwable
                                ) {
                                    TODO("Not yet implemented")
                                }
                            } )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier
                    .size(100.dp)
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = "",
                )
            }
            Text(text = "Nome: ${character.name}")
            Text(text = "Espécie: ${character.species}")
            Text(text = "Origem: ${character.origin.name}")
        }
    }
}