package com.example.seafood.ui.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.seafood.R
import com.example.seafood.ui.viewmodel.MainActivityViewModel

class ShowAccountDialog : DialogFragment() {

    private var account: Int = 0
    private var rest: Int = 0
    private lateinit var etAccount: EditText
    private lateinit var etCash: EditText
    private lateinit var etRest: EditText
    val viewModel: MainActivityViewModel by activityViewModels()

    companion object {
        const val TAG = "CED_TAG"
        const val INPUT = "CED_TAG_INPUT"

        fun newInstance(account: Int): ShowAccountDialog {
            val f = ShowAccountDialog()
            val args = Bundle()
            args.putInt(INPUT, account)
            f.arguments = args
            return f
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            account = requireArguments().getInt(INPUT)
            val builder = AlertDialog.Builder(it)
            val view = requireActivity().layoutInflater.inflate(R.layout.dialog_show_account, null)
            etAccount = view.findViewById(R.id.row_et_amount)
            etCash = view.findViewById(R.id.row_et_cash)
            etRest = view.findViewById(R.id.row_et_rest)
            etAccount.setText(account.toString())
            etAccount.isEnabled = false
            builder.setView(view)
                .setPositiveButton(R.string.app_accept) { _, _ ->
                    viewModel.getFoodInfo()
                }
                .setNegativeButton(R.string.app_cancel, null)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}