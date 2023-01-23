package design.propia.noteapp_pl_coding.feature_note.domain.use_cases

data class NoteUseCases(
    val getNotes:GetNotes,
    val getNote:GetNote,
    val deleteNote:DeleteNote,
    val addNote: AddNote,
)
