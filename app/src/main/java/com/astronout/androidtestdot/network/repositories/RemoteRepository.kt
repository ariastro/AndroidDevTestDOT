package com.astronout.androidtestdot.network.repositories

import com.astronout.androidtestdot.network.Network
import com.astronout.androidtestdot.network.ServiceFactory
import kotlinx.coroutines.CoroutineScope

class RemoteRepository(coroutineScope: CoroutineScope) {

    private val network = Network(coroutineScope)
    private val restApi = ServiceFactory.create()

//    fun postLogin(username: String, password: String, context: Context,
//                  onSuccess: (LoginResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postLogin(username, password)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }

}