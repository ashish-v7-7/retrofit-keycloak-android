package com.protium.protiumretrofitkeycloak.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.protium.protiumretrofitkeycloak.keycloak.AccessToken
import com.protium.protiumretrofitkeycloak.keycloak.GetDataService
import com.protium.protiumretrofitkeycloak.R
import com.protium.protiumretrofitkeycloak.keycloak.Constants
import com.protium.protiumretrofitkeycloak.keycloak.RetrofitClientInstance

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private var username: EditText? = null
    private var password: EditText? = null
    private var btnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btnLogin = findViewById(R.id.button_login)

        btnLogin?.setOnClickListener(View.OnClickListener { //todo: code here
            getAccessToken()
        })
    }


    fun getAccessToken() {
        val service: GetDataService = RetrofitClientInstance.getRetrofitInstance().create(
            GetDataService::class.java
        )
        val user_name = username!!.text.toString()
        val user_password = password!!.text.toString()
        val call: Call<AccessToken> = service.getAccessToken(
            Constants.CLIENT_ID,
            Constants.GRANT_TYPE,
            Constants.CLIENT_SECRET,
            Constants.SCOPE,
            user_name,
            user_password)
        call.enqueue(object : Callback<AccessToken?> {
            override fun onResponse(call: Call<AccessToken?>, response: Response<AccessToken?>) {
                if (response.isSuccessful()) {
                    Constants.REFRESH_TOKEN = response.body()?.getRefreshToken()
                    Constants.ACCESS_TOKEN = response.body()?.getAccessToken()
                    gotoDashboard()
                    Log.e("onres1", response.message())

                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("onres2", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<AccessToken?>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.e("onf", t.message.toString())
            }
        })
    }

    private fun gotoDashboard() {
        val i = Intent(this, DashboardActivity::class.java)
        startActivity(i)
    }

}