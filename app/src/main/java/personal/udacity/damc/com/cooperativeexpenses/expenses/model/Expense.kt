package personal.udacity.damc.com.cooperativeexpenses.expenses.model

data class Expense(
    val id: String,
    val group: String,
    val name: String,
    val value: String,
    val date: String,
    val user: String
)
