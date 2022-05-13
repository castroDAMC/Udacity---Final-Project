package personal.udacity.damc.com.cooperativeexpenses.login

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
import personal.udacity.damc.com.cooperativeexpenses.databinding.FragmentLoginNewUserBinding

class LoginNewUserFragment : Fragment() {


    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var binding: FragmentLoginNewUserBinding


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
        setNewAccountClickListener()
    }

    private fun setNewAccountClickListener() {
        binding.btnNewAccount.setOnClickListener {
            createNewUserWithFirebase(binding.editTextTextEmailAddress.text.toString(), binding.editTextTextPassword.text.toString())
        }
    }

    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login_new_user, container, false)

        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
    }

    private fun createNewUserWithFirebase(email: String, password: String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "New User with " + FirebaseAuth.getInstance().currentUser!!.email,
                    Toast.LENGTH_SHORT
                ).show()
                loginViewModel.authenticationState
                Navigation.findNavController(binding.root).navigate(R.id.action_newUserFragment_to_loginFragment)
            }
            .addOnFailureListener {
                Toast.makeText(context, "FAIL CREATE LOGIN", Toast.LENGTH_SHORT).show()
            }
    }
}