package com.sumesh_pandit.testdf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus


class MainActivity : AppCompatActivity() {
    private val manager: SplitInstallManager by lazy { SplitInstallManagerFactory.create(this) }
    private val request = SplitInstallRequest.newBuilder()
        .addModule(DYNAMIC_MODULE_NAME)
        .build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        val buttonClick= findViewById<Button>(R.id.but)
        buttonClick.setOnClickListener {

            if(manager.installedModules.contains(DYNAMIC_MODULE_NAME)) {
                val intent = Intent()
                intent.setClassName(
                    BuildConfig.APPLICATION_ID,
                    "com.sumesh_pandit2.dynamicfeature.MainActivity2"
                )
                startActivity(intent)
            }
        }
    }

    private fun initComponents() {

        if(manager.installedModules.contains(DYNAMIC_MODULE_NAME)){
            Toast.makeText(this, "installed return", Toast.LENGTH_SHORT).show()
            return
        }

        manager.startInstall(request).addOnSuccessListener {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()

        }
        .addOnFailureListener {
                Toast.makeText(this, "Failure......", Toast.LENGTH_SHORT).show()
            }

    }

    companion object {
        private const val DYNAMIC_MODULE_NAME = "dynamicfeature"
    }
}