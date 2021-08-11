package master.clean.architecture_kodein.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import master.clean.architecture_kodein.repository.SystemRepository

class SystemViewModelFactory(
    private val systemRepository: SystemRepository,

    ) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SystemViewModel(
            systemRepository
        ) as T
    }
}