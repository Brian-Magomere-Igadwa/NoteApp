package design.propia.noteapp_pl_coding.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import design.propia.noteapp_pl_coding.feature_note.domain.models.Note
import design.propia.noteapp_pl_coding.feature_note.domain.use_cases.NoteUseCases
import design.propia.noteapp_pl_coding.feature_note.domain.util.NoteOrder
import design.propia.noteapp_pl_coding.feature_note.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {


    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeletedNote: Note? = null
    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(notes = notes, noteOrder = noteOrder)
            }
            .launchIt(viewModelScope)
    }
}
