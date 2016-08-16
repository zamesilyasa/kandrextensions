package com.wnc_21.kandroidextensions.async

import android.os.AsyncTask

fun <T> Any.runAsyncTask(backgroundTask: () -> T?,
                    uiCallback: (T?) -> Unit = {},
                    errorHandler: (Throwable) -> Unit = { throw it}) {

    object : AsyncTask<Void, Void, T?>() {
        var result: T? = null
        var error :Throwable? = null

        override fun doInBackground(vararg p0: Void?): T? {
            try {
                result = backgroundTask()
            } catch (e: Throwable) {
                error = e
            }

            return result
        }

        override fun onPostExecute(result: T?) {
            if (error != null) {
                errorHandler(error!!)
            } else {
                uiCallback(result)
            }
        }

    }.execute()
}
