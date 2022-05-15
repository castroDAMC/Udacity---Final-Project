package personal.udacity.damc.com.cooperativeexpenses.expenses.view

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import personal.udacity.damc.com.cooperativeexpenses.R
import personal.udacity.damc.com.cooperativeexpenses.databinding.FragmentMainPageExpensesBinding
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.Expense
import personal.udacity.damc.com.cooperativeexpenses.expenses.model.GroupExpense
import personal.udacity.damc.com.cooperativeexpenses.expenses.view.adapters.MainExpensesAdapter
import personal.udacity.damc.com.cooperativeexpenses.expenses.viewmodel.ExpensesViewModel


class FragmentMainPageExpenses : Fragment() {

    private lateinit var binding: FragmentMainPageExpensesBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[ExpensesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupBinding(inflater, container)
        setRecyclerViewConfiguration()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setRecyclerViewConfiguration() {
        binding.recyclerView.adapter = MainExpensesAdapter(
            MutableLiveData(viewModel._listOfGroups.value),
            {setClickItemFunction(it)}
        )
    }

    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_page_expenses, container, false)


        binding.lifecycleOwner = this
    }

    private fun setClickItemFunction(expense: GroupExpense) {
        Toast.makeText(requireContext(), "Despesa = " + expense.groupName, Toast.LENGTH_SHORT).show()
        findNavController().navigate(FragmentMainPageExpensesDirections.actionMainExpansesToFragmentInsideGroupExpense(expense))
//        Navigation.findNavController(binding.root).navigate(R.id.action_main_expanses_to_fragmentInsideGroupExpense, expense.group)
    }
}