package master.clean.architecture_kodein

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import master.clean.architecture_kodein.databinding.ActivityMainBinding
import master.clean.architecture_kodein.ui.SystemViewModel
import master.clean.architecture_kodein.ui.SystemViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()

    // binding
    private lateinit var binding: ActivityMainBinding

    // viewModel
    private val systemViewModelFactory: SystemViewModelFactory by instance()
    lateinit var systemViewModel: SystemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        systemViewModel =
            ViewModelProviders.of(this, systemViewModelFactory)
                .get(SystemViewModel::class.java)



    }
}