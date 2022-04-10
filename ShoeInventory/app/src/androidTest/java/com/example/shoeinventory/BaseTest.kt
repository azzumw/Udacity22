package com.example.shoeinventory

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Before

open class BaseTest {
    companion object{
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        private val instrumentation = InstrumentationRegistry.getInstrumentation()

        private const val shellCommandAnimatorOff =
            "settings put global animator_duration_scale 0.0"

        private const val shellCommandTransitionOff =
            "settings put global transition_animation_scale 0.0"

        private const val shellCommandWindowOff =
            "settings put global window_animation_scale 0.0"

        val uiDevice: UiDevice = UiDevice.getInstance(instrumentation)
    }

    @Before
    open fun setUp() {
        uiDevice.executeShellCommand(shellCommandAnimatorOff)
        uiDevice.executeShellCommand(shellCommandTransitionOff)
        uiDevice.executeShellCommand(shellCommandWindowOff)
    }

}