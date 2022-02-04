package com.protium.protiumretrofitkeycloak.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.protium.protiumretrofitkeycloak.R
import com.protium.protiumretrofitkeycloak.keycloak.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {
    private var btnLogout: Button? = null
    private var btnUserData: Button? = null
    private var tvUserData: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daashboard)
        btnLogout = findViewById(R.id.button_logout)
        btnUserData = findViewById(R.id.button_get_user)
        tvUserData = findViewById(R.id.textView)

        btnLogout?.setOnClickListener(View.OnClickListener { //todo: code here
            logout()
        })

        btnUserData?.setOnClickListener(View.OnClickListener { //todo: code here
            getUser()
        })
    }

    private fun logout() {
        val service: GetDataService = RetrofitClientInstance.getRetrofitInstance().create(

            GetDataService::class.java

        )

        val call: Call<ResponseBody> = service.logout(Constants.CLIENT_ID, Constants.REFRESH_TOKEN, Constants.CLIENT_SECRET);

        call.enqueue(object : Callback<ResponseBody?>{
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful){
                    gotoLogin()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@DashboardActivity,"Logout Failed",Toast.LENGTH_LONG).show()
            }

        })
    }
    fun getUser() {
        val service: GetDataService = RetrofitClientInstance.getRetrofitInstance().create(
            GetDataService::class.java
        )

        val call: Call<Users> = service.getUser("Bearer "+Constants.ACCESS_TOKEN)
        call.enqueue(object : Callback<Users?> {
            override fun onResponse(call: Call<Users?>, response: Response<Users?>) {

                if (response.isSuccessful()) {
                    Toast.makeText(
                        this@DashboardActivity,
                        response.body().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    tvUserData?.text = "id : " + response.body()?.get(0)!!.id + "\nusername : "+response.body()?.get(0)!!.username
                    Log.e("onres1", response.body()?.get(0)!!.username)

                } else {
                    Toast.makeText(
                        this@DashboardActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("onres2", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<Users?>, t: Throwable) {
                Toast.makeText(this@DashboardActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.e("onf", t.message.toString())
            }
        })
    }

    private fun gotoLogin() {
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }
}