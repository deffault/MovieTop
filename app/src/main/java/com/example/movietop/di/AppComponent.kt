package com.example.movietop.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DispatchersModule::class])
interface AppComponent