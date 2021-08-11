package master.clean.architecture_kodein.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import master.clean.architecture_kodein.MainActivity
import master.clean.architecture_kodein.databinding.FragmentLoginBinding
import master.clean.architecture_kodein.ui.SystemViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

class LoginFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    // binding
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // viewModel
    private lateinit var systemViewModel: SystemViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        // setUp ViewModel
        systemViewModel = (activity as MainActivity).systemViewModel
        binding.systemViewModel = systemViewModel
        binding.lifecycleOwner = this



        systemViewModel.message.observe(viewLifecycleOwner, Observer { it ->
            if (it == null) return@Observer

            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })


    }

}
