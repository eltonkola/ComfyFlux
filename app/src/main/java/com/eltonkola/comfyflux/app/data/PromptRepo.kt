package com.eltonkola.comfyflux.app.data

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState

class PromptRepo(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun getPagingSource(query: String): PagingSource<Int, String> {
        return object : PagingSource<Int, String>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
                val position = params.key ?: 0
                val db = dbHelper.readableDatabase
                val cursor = db.rawQuery("SELECT * FROM prompts_table WHERE Prompt LIKE ? LIMIT ? OFFSET ?",
                    arrayOf("%$query%", params.loadSize.toString(), position.toString()))
                val lines = mutableListOf<String>()
                if (cursor.moveToFirst()) {
                    do {
                        val line = cursor.getString(cursor.getColumnIndexOrThrow("Prompt"))
                        lines.add(line)
                    } while (cursor.moveToNext())
                }
                cursor.close()
                return LoadResult.Page(
                    data = lines,
                    prevKey = if (position == 0) null else position - params.loadSize,
                    nextKey = if (lines.isEmpty()) null else position + params.loadSize
                )
            }

            override fun getRefreshKey(state: PagingState<Int, String>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
                }
            }
        }
    }
}

