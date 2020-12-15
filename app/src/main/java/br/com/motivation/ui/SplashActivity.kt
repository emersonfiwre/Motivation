package br.com.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.motivation.R
import br.com.motivation.infra.MotivationConstants
import br.com.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var mSecurityPreferences: SecurityPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)
        if(supportActionBar != null){
            supportActionBar!!.hide()
        }
        buttonSave.setOnClickListener(this)
        verifyName()
    }


    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id == R.id.buttonSave){
            handleSave()
        }
    }

    private fun verifyName() {
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if(name.isNotEmpty()){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleSave() {
        val name = editName.text.toString()
        if(name.isNotEmpty()){
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME,name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }else{
            Toast.makeText(this,"Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }
}