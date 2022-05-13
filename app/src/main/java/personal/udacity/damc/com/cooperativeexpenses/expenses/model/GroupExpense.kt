package personal.udacity.damc.com.cooperativeexpenses.expenses.model

data class GroupExpense(
    val id: String,
    val groupName: String,
    val lastUpdate: String,
    val target: String,
    val percentage: String,
)