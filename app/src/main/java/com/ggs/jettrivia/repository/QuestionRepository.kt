package com.ggs.jettrivia.repository

import android.util.Log
import com.ggs.jettrivia.data.DataOrException
import com.ggs.jettrivia.model.QuestionItem
import com.ggs.jettrivia.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api:QuestionApi) {
    private val dataOrException
    = DataOrException<ArrayList<QuestionItem>,
            Boolean,
            Exception>()

    suspend fun getAllQuestions():DataOrException<ArrayList<QuestionItem>,
            Boolean,Exception>{

        try{
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty())
                dataOrException.loading = false
        }catch(exception:Exception){
            dataOrException.e = exception
            Log.d("Exc: ", "getAllQuestion:${dataOrException.e!!.localizedMessage} ")

        }
        return dataOrException
    }

}