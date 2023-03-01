package com.mwdevs.capstone.coins.utils

import io.reactivex.Scheduler

interface CapstoneSchedulers {
    val main: Scheduler
    val io : Scheduler
}