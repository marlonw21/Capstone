package com.mwdevs.capstone.coins.di

import com.mwdevs.capstone.coins.utils.CapstoneSchedulers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideCapstoneScheduler(): CapstoneSchedulers = object : CapstoneSchedulers {
        override val main: Scheduler
            get() = AndroidSchedulers.mainThread()
        override val io: Scheduler
            get() = Schedulers.io()
    }
}
