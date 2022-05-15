package personal.udacity.damc.com.cooperativeexpenses.expenses.model

data class Expense(
    var id: String,
    var group: String,
    var name: String,
    var value: String,
    var date: String,
    var user: String,
    var explanation: String
)

// TODO 1 - Save New Expense using viewModel
// TODO 2 - Integrate with Firebase repository
// TODO 3 - Share Expenses Board with someone
// TODO 4 - Receive Notification when new expense is added by other user
// TODO 5 - Open this notification in "inside_group_layout"
// TODO 6 - Use MotionLayout
// TODO 7 - Improve Layout


