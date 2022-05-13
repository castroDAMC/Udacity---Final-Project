package personal.udacity.damc.com.cooperativeexpenses.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import personal.udacity.damc.com.cooperativeexpenses.R
import personal.udacity.damc.com.cooperativeexpenses.databinding.FragmentLoginBinding
import personal.udacity.damc.com.cooperativeexpenses.expenses.MainPageExpenses

class LoginFragment : Fragment() {

    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setupBinding(inflater, container)
        setClickListeners()


        return binding.root
    }

    private fun setClickListeners() {
        setLoginClickListener()
        setNewUserClickListener()
    }



    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
    }

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
        binding.btnLogin.setOnClickListener {
            if (binding.edtLoginEmail.text.isNullOrBlank() || binding.edtLoginPassword.text.isNullOrBlank()) {
                Toast.makeText(context, "Please, insert email and password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                loginWithFirebase(
                    binding.edtLoginEmail.text.toString(),
                    binding.edtLoginPassword.text.toString()
                )
            }
        }
    }

    private fun setNewUserClickListener() {
        binding.txtFirstAccessClickHere.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_newUserFragment)
        }
    }

    private fun loginWithFirebase(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
//                Toast.makeText(
//                    context,
//                    "Login with email " + FirebaseAuth.getInstance().currentUser!!.email,
//                    Toast.LENGTH_SHORT
//                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "FAIL LOGIN", Toast.LENGTH_SHORT).show()
            }
    }


}