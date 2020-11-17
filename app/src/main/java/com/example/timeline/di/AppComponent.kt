package com.example.timeline.di

import android.app.Application
import com.example.timeline.MainApplication
import com.kotlin.project.data.di.NetworkModule
import com.kotlin.project.data.di.RepositoryModule
import com.kotlin.project.domain.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        // app
        AppModule::class,
        ViewModelModule::class,
        FragmentModule::class,
        ActivityModule::class,
        DelegateModule::class,
        // domain
        UseCaseModule::class,
        RepositoryModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: MainApplication)
}
