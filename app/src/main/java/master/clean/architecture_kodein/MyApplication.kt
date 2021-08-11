package master.clean.architecture_kodein

import android.app.Application
import master.clean.architecture_kodein.network.connectivity.ConnectivityInterceptor
import master.clean.architecture_kodein.network.connectivity.ConnectivityInterceptorImpl
import master.clean.architecture_kodein.network.datasource.SystemDataSource
import master.clean.architecture_kodein.network.datasource.SystemDataSourceImpl
import master.clean.architecture_kodein.network.response.ApiSystemService
import master.clean.architecture_kodein.repository.SystemRepository
import master.clean.architecture_kodein.repository.SystemRepositoryImpl
import master.clean.architecture_kodein.ui.SystemViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))


        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }

        // system DataSource
        bind() from singleton { ApiSystemService(instance()) }
        bind<SystemDataSource>() with singleton {
            SystemDataSourceImpl(instance())
        }
        bind<SystemRepository>() with singleton {
            SystemRepositoryImpl(instance())
        }
        bind() from provider { SystemViewModelFactory(instance()) }

    }
}