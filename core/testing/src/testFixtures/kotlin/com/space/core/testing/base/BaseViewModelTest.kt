package com.space.core.testing.base

import com.space.core.testing.rules.MainDispatcherRule
import io.mockk.clearAllMocks
import org.junit.Before
import org.junit.Rule

abstract class BaseViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    open fun setUp() {
        clearAllMocks()
    }
}