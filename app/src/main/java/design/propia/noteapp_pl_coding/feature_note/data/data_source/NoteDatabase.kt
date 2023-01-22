package design.propia.noteapp_pl_coding.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import design.propia.noteapp_pl_coding.feature_note.domain.models.Note

@Database(
    entities=[Note::class],
    version=1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao:NoteDao

}