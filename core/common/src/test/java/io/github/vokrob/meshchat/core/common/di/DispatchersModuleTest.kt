package io.github.vokrob.meshchat.core.common.di

import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertSame
import org.junit.Test

class DispatchersModuleTest {
    @Test
    fun providesIoDispatcher() {
        assertSame(Dispatchers.IO, DispatchersModule.providesIoDispatcher())
    }

    @Test
    fun providesDefaultDispatcher() {
        assertSame(Dispatchers.Default, DispatchersModule.providesDefaultDispatcher())
    }
}
