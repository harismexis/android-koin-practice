package com.harismexis.koinpractice.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

}