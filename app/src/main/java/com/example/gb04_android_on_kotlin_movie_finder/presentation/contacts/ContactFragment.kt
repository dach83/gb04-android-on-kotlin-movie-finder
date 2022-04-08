package com.example.gb04_android_on_kotlin_movie_finder.presentation.contacts

import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.gb04_android_on_kotlin_movie_finder.R
import com.example.gb04_android_on_kotlin_movie_finder.databinding.FragmentContactBinding


class ContactFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentContactBinding? = null
    private val binding: FragmentContactBinding get() = _binding as FragmentContactBinding

    private var cursorAdapter: SimpleCursorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoaderManager.getInstance(this).initLoader(0, null, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCursorAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupCursorAdapter() {
        context?.let {
            cursorAdapter = SimpleCursorAdapter(
                it,
                R.layout.item_contact,
                null,
                FROM_COLUMNS,
                TO_IDS,
                0
            )

            binding.contactListView.adapter = cursorAdapter
        }

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        context?.let {
            return CursorLoader(
                it,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION,
                null,
                null,
                SORT_ORDER
            )
        } ?: throw IllegalStateException()

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        cursorAdapter?.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        cursorAdapter?.swapCursor(null)
    }

    companion object {

        private val FROM_COLUMNS: Array<String> = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        private val TO_IDS: IntArray = intArrayOf(
            R.id.name_text_view,
            R.id.phone_text_view
        )

        private val PROJECTION = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        private const val SORT_ORDER = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
    }
}