package com.petrlr14.mvvm.service.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.petrlr14.mvvm.database.entities.GitHubRepo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.String as String1

const val GITHUB_BASE_URL = "https://api.github.com/"

interface GitHubService {

    @GET("/users/{user}/repos")
    fun getRepos (@Path("user") user: kotlin.String):Deferred<Response<List<GitHubRepo>>>

    companion object{

        var INSTANCE : GitHubService? = null

        fun getGitHubService():GitHubService{
            return if(INSTANCE != null){
                return INSTANCE!!
            }else{
                Retrofit
                    .Builder()
                    .baseUrl(GITHUB_BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                    .create(GitHubService::class.java)
            }

            }
    }
}