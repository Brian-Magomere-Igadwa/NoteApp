package design.propia.noteapp_pl_coding.feature_note.domain.use_cases

import design.propia.noteapp_pl_coding.feature_note.domain.models.Note
import design.propia.noteapp_pl_coding.feature_note.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
    ) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote()
    }
}