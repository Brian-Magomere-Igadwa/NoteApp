package design.propia.noteapp_pl_coding.feature_note.domain.use_cases

import design.propia.noteapp_pl_coding.feature_note.domain.models.Note
import design.propia.noteapp_pl_coding.feature_note.domain.repository.NoteRepository
import design.propia.noteapp_pl_coding.feature_note.domain.util.NoteOrder
import design.propia.noteapp_pl_coding.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when(noteOrder){
                        is NoteOrder.Title->notes.sortedBy{it.title.lowercase()}
                        is NoteOrder.Date->notes.sortedBy{it.timestamp}
                        is NoteOrder.Color->notes.sortedBy{it.color}
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Title->notes.sortedByDescending{it.title.lowercase()}
                        is NoteOrder.Date->notes.sortedByDescending{it.timestamp}
                        is NoteOrder.Color->notes.sortedByDescending{it.color}
                    }
                }
            }
        }
    }

}