package personal.udacity.damc.com.cooperativeexpenses.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import personal.udacity.damc.com.cooperativeexpenses.R
import personal.udacity.damc.com.cooperativeexpenses.expenses.MainPageExpenses


class LoginFragment : Fragment() {

    private val loginViewModel by viewModels<LoginViewModel>()

    lateinit var btnLogin : Button
    lateinit var loginEmail: EditText
    lateinit var loginPassword: EditText
    lateinit var txtFirstAccessClickHere: TextView
    lateinit var motionLayout: MotionLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        btnLogin = view.findViewById(R.id.btn_login)
        loginEmail = view.findViewById(R.id.edt_login_email)
        loginPassword = view.findViewById(R.id.edt_login_password)
        txtFirstAccessClickHere = view.findViewById(R.id.txt_first_access_click_here)
        motionLayout = view.findViewById(R.id.login_motion_layout)


        setClickListeners()

        return view

//        setupBinding(inflater, container)
        setClickListeners()


//        return binding.root
    }

    private fun setClickListeners() {
        setLoginClickListener()
        setNewUserClickListener()
    }



//    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
//        binding =
//            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
//
//        binding.viewModel = loginViewModel
//        binding.lifecycleOwner = this
//    }

    override fun onStart() {
        super.onStart()
        observeAuthenticationState()
    }


    private fun observeAuthenticationState() {
        loginViewModel.authenticationState.observe(this) { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    Toast.makeText(
                        context,
                        "Login with email " + FirebaseAuth.getInstance().currentUser!!.email,
                        Toast.LENGTH_SHORT
                    ).show()
                    moveToMainActivity()
                }
                else -> {
                    Toast.makeText(context, "FAIL LOGIN", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun moveToMainActivity() {
        this.let{
            val intent = Intent (requireContext(), MainPageExpenses::class.java)
            it.startActivity(intent)
            // so can't click back button to previous login screen after logging in successfully
            it.requireActivity().finish()
        }
    }

    private fun setLoginClickListener() {
        btnLogin.setOnClickListener {
            if (loginEmail.text.isNullOrBlank() || loginPassword.text.isNullOrBlank()) {
                Toast.makeText(context, "Please, insert email and password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                loginWithFirebase(
                    loginEmail.text.toString(),
                    loginPassword.text.toString()
                )
            }
        }
    }

    private fun setNewUserClickListener() {
        txtFirstAccessClickHere.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_newUserFragment)
        }
    }

    private fun loginWithFirebase(email: String, password: String) {
        motionLayout.transitionToEnd()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
//                Toast.makeText(
//                    context,
//                    "Login with email " + FirebaseAuth.getInstance().currentUser!!.email,
//                    Toast.LENGTH_SHORT
//                ).show()
            }
            .addOnFailureListener {
                motionLayout.transitionToStart()
                Toast.makeText(context, "FAIL LOGIN", Toast.LENGTH_SHORT).show()
            }
    }


}