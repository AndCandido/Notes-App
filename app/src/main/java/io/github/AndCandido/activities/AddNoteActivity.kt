package io.github.AndCandido.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.AndCandido.R
import io.github.AndCandido.databinding.ActivityAddNoteBinding
import io.github.AndCandido.models.Note
import io.github.AndCandido.models.NotesDatabaseHelper

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.inputNoteTitle.text.toString()
            val content = binding.inputNoteContent.text.toString()

            val note = Note(0, title, content)
            db.saveNote(note)

            finish()
            Toast.makeText(this, "Nota salva", Toast.LENGTH_LONG)
                .show()
        }

        setOnApplyWindowInsetsListenerOnViewCompat()
    }

    private fun setOnApplyWindowInsetsListenerOnViewCompat() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}