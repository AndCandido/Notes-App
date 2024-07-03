package io.github.AndCandido.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.AndCandido.R
import io.github.AndCandido.databinding.ActivityUpdateNoteBinding
import io.github.AndCandido.models.Note
import io.github.AndCandido.models.NotesDatabaseHelper

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id", -1)

        if(noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.inputUpdateNoteTitle.setText(note.title)
        binding.inputUpdateNoteContent.setText(note.content)

        binding.updateNoteButton.setOnClickListener {
            val title = binding.inputUpdateNoteTitle.text.toString()
            val content = binding.inputUpdateNoteContent.text.toString()

            val updatedNote = Note(noteId, title, content)
            db.updateNote(updatedNote)

            finish()
            Toast.makeText(this, "Nota atualizada", Toast.LENGTH_LONG)
                .show()
        }

        viewCompatSetOnApplyWindowInsetsListener()
    }

    private fun viewCompatSetOnApplyWindowInsetsListener() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}