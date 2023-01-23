package design.propia.noteapp_pl_coding.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import design.propia.noteapp_pl_coding.feature_note.data.data_source.NoteDatabase
import design.propia.noteapp_pl_coding.feature_note.data.repository.NoteRepositoryImpl
import design.propia.noteapp_pl_coding.feature_note.domain.repository.NoteRepository
import design.propia.noteapp_pl_coding.feature_note.domain.use_cases.AddNote
import design.propia.noteapp_pl_coding.feature_note.domain.use_cases.DeleteNote
import design.propia.noteapp_pl_coding.feature_note.domain.use_cases.GetNotes
import design.propia.noteapp_pl_coding.feature_note.domain.use_cases.NoteUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)

    }
    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getNotes= GetNotes(repository),
            deleteNote= DeleteNote(repository),
            addNote= AddNote(repository)
        )
    }
}