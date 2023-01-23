package design.propia.noteapp_pl_coding.feature_note.presentation.notes

import design.propia.noteapp_pl_coding.feature_note.domain.models.Note
import design.propia.noteapp_pl_coding.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()

    data class RestoreNote : NotesEvent()
    data class ToggleOrderSection : NotesEvent()
}
