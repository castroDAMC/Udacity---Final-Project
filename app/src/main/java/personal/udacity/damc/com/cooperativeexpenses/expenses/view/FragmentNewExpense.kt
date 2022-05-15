package personal.udacity.damc.com.cooperativeexpenses.expenses.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import personal.udacity.damc.com.cooperativeexpenses.R
import personal.udacity.damc.com.cooperativeexpenses.databinding.NewExpenseInsertCustomLayoutBinding
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.GroupExpense
import personal.udacity.damc.com.cooperativeexpenses.expenses.viewmodel.ExpensesViewModel


class FragmentNewExpense : Fragment() {

    private lateinit var expenseGroup: GroupExpense
    private lateinit var binding: NewExpenseInsertCustomLayoutBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[ExpensesViewModel::class.java]
    }


    /** The system calls this to get the DialogFragment's layout, regardless
    of whether it's being displayed as a dialog or an embedded fragment. */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        expenseGroup = FragmentInsideGroupExpenseArgs.fromBundle(requireArguments()).expenseGroup
        // Inflate the layout to use as dialog or embedded fragment
        setupBinding(layoutInflater, container)
        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        binding.btnSaveNew.setOnClickListener {
            saveNewExpense()
            moveToListOfExpansesPage()
        }

        binding.btnCancelNew.setOnClickListener {
            if (
                !binding.edtNewExpenseDetails.text.isNullOrBlank() ||
                !binding.edtNewExpenseName.text.isNullOrBlank() ||
                !binding.edtNewExpenseValue.text.isNullOrBlank()
            ){
                AlertDialog.Builder(context)
                    .setTitle("Are you sure?")
                    .setMessage("Do you really wanna cancel this new expense addition?")
                    .setPositiveButton(
                        "OF COURSE"
                    ) { _, _ ->
                        moveToListOfExpansesPage()
                    }
                    .setNegativeButton("MY MISTAKE") { _, _ ->
                    }
                    .create()
                    .show()
            } else {
                moveToListOfExpansesPage()
            }

        }
    }

    private fun moveToListOfExpansesPage() {
        findNavController().navigate(
            FragmentNewExpenseDirections.actionFragmentNewExpenseToFragmentInsideGroupExpense(
                expenseGroup
            )
        )
    }

    private fun saveNewExpense() {

        viewModel.newExpense.value!!.group = expenseGroup.groupName
        viewModel.newExpense.value!!.name = binding.edtNewExpenseName.text.toString()
        viewModel.newExpense.value!!.value = binding.edtNewExpenseValue.text.toString()
        viewModel.newExpense.value!!.explanation = binding.edtNewExpenseDetails.text.toString()
        Toast.makeText(context, "IMPLEMENT SAVE", Toast.LENGTH_SHORT).show()

        viewModel.addNewExpense()
    }


    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.new_expense_insert_custom_layout,
                container,
                false
            )

        binding.lifecycleOwner = this
    }


}