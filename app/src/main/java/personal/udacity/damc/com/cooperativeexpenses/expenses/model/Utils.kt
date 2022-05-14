package personal.udacity.damc.com.cooperativeexpenses.expenses.model

fun getLastExpenseUpdateByGroup(list: ArrayList<Expense>, groupName: String) {

}

fun getGroupExpenseSum(list: ArrayList<Expense>, groupName: String): String {
    return list
        .filter { it.group == groupName }
        .sumOf { it.value.toLong() }
        .toString()
}

fun getExpenseUsedByPercentage(expense_value: String, finalTarget: String): String {
    val percentage = 100*expense_value.toFloat()/finalTarget.toFloat()
    return String.format("%.2f", percentage)
}