package com.sandstorm.notes.create

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sandstorm.notes.R
import com.sandstorm.notes.foundations.ApplicationScope
import com.sandstorm.notes.foundations.NullFieldChecker
import com.sandstorm.notes.models.Note
import com.sandstorm.notes.notes.INoteModel
import com.sandstorm.notes.notes.NoteLocalModel
import kotlinx.android.synthetic.main.fragment_create_note.*
import toothpick.Toothpick
import javax.inject.Inject


class CreateNoteFragment : Fragment(), NullFieldChecker {
    @Inject
    lateinit var model: INoteModel

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this,ApplicationScope.scope)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun saveNote(callback: (Boolean)->Unit) {
        createnote()?.let{
            model.addNote(it){
                callback.invoke(true)
            }
        }?: callback.invoke(false)
    }

    private fun createnote(): Note? = if (!hasNullField()) {
        Note(noteEditText.editableText.toString())
    } else {
        null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction()
    }

    override fun hasNullField(): Boolean = noteEditText.editableText.isNullOrEmpty()

    companion object {
        fun newInstance() = CreateNoteFragment()
    }
}
