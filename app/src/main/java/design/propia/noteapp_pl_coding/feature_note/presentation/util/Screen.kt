package design.propia.noteapp_pl_coding.feature_note.presentation.util

sealed class Screen(val route:String){
    object NotesScreen:Screen("notes_screen")
}