package com.wnc_21.kandroidextensions.async

import android.os.Looper
import android.support.test.rule.UiThreadTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class TestAsyncTask {

    @get:Rule var uiThread = UiThreadTestRule()

    @Test
    fun runAsyncTask_Should_run_background_task_in_background_thread() {
        val countDownLatch = CountDownLatch(2)

        uiThread.runOnUiThread {
            countDownLatch.countDown()

            assertEquals(Looper.myLooper(), Looper.getMainLooper())

            runAsyncTask({
                assertNotEquals(Looper.myLooper(), Looper.getMainLooper())
                countDownLatch.countDown()
            })
        }

        assertTrue(countDownLatch.await(1, TimeUnit.SECONDS))
    }

    @Test
    fun runAsyncTask_should_return_result_into_main_thread() {
        val countDownLatch = CountDownLatch(3)

        uiThread.runOnUiThread {
            countDownLatch.countDown()
            assertEquals(Looper.myLooper(), Looper.getMainLooper())

            var result: Any? = null

            runAsyncTask(
                    {
                        assertNotEquals(Looper.myLooper(), Looper.getMainLooper())
                        result = Any()

                        countDownLatch.countDown()
                        result
                    }, {

                assertEquals(result, it)
                assertEquals(Looper.myLooper(), Looper.getMainLooper())
                countDownLatch.countDown()
            })
        }

        assertTrue(countDownLatch.await(1, TimeUnit.SECONDS))
    }

    @Test
    fun runAsyncTask_Should_report_error_into_UI_thread() {
        val countDownLatch = CountDownLatch(3)

        uiThread.runOnUiThread {
            countDownLatch.countDown()

            runAsyncTask(backgroundTask = {
                countDownLatch.countDown()
                throw IOException("Should be caught in error block")
            }, errorHandler = { error ->
                assertEquals(Looper.getMainLooper(), Looper.myLooper())

                assertTrue(error is IOException)
                countDownLatch.countDown()
            }, uiCallback = {fail("Must not be called")})
        }

        assertTrue(countDownLatch.await(1, TimeUnit.SECONDS))
    }
}
