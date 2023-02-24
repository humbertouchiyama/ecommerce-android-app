package com.renarosantos.ecommerceapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

class AppApplication {

    @HiltAndroidApp
    class AppAplication  : Application()
}