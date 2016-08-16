# kandrextensions
Useful android extensions, for kotlin based projects

## AsyncTask:
    fun <T> Any.runAsyncTask(backgroundTask: () -> T?,
                        uiCallback: (T?) -> Unit = {},
                        errorHandler: (Throwable) -> Unit = { throw it}) {

    helper method, which can be used for background work, and replace android asynctasks
        This helper takes three callbacks and uses AsyncTask for beckground work, so, it must be called from the UI thread:

        backgroundTask - will be executed in background thread, returns nullable result

        uiCallback - will be executed in UI thread. Nullable result from background task will be passed to this callback

        errorHandler - any caught error(exception) will be passed into this callback. In case of error, uiCallback will not be called. Raised exception will be passed as an argument


## Cursor: 
    accessor functions, which return types, by column name
        fun Cursor.getBoolean(name: String): Boolean {

          fun Cursor.getString(name: String): String 
          fun Cursor.getNullableString(name: String): String?
          fun Cursor.getInt(name: String): Int 
          fun Cursor.getNullableInt(name: String): Int?
          fun Cursor.getLong(name: String): Long
          fun Cursor.getNullableLong(name: String): Long?
          fun Cursor.getDouble(name: String): Double
          fun Cursor.getNullableDouble(name: String): Double?
          fun Cursor.getFloat(name: String): Float
          fun Cursor.getNullableFloat(name: String): Float?
          fun Cursor.getBoolean(name: String): Boolean
          fun Cursor.getNullableBoolean(name: String): Boolean?
          
    fun <T> Cursor.asList(mapper: (c: Cursor) -> T): CloseableList<T> - wraps cursror with closeable list instance, to access cursor as list
          
          example:
          
          val c = MatrixCursor("column")
          val list = List<SomeDataObject> = c.asList{ SomeDataObject(it.getString("column")) }
          
  
##UI extensions:
    fun View.setGone(gone: Boolean)
    fun View.setVisible(visible: Boolean)
    
    fun FragmentManager.isFragmentAdded(tag: String) : Boolean