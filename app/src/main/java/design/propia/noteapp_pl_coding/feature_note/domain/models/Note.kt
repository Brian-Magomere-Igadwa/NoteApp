package design.propia.noteapp_pl_coding.feature_note.domain.models

import androidx.compose.ui.graphics.Color.Companion.Cyan

import androidx.compose.ui.graphics.Color.Companion.DarkGray

import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: String,
    val color: Int,
    @PrimaryKey val id:Int?=null
){
    companion object{
        val noteColors=listOf(Magenta,Green, Yellow,DarkGray,Cyan)
    }
}
