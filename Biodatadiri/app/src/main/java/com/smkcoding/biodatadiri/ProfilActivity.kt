package com.smkcoding.biodatadiri

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        ambilData()
        btnEditname.setOnClickListener({ navigasiKeEditProfil() })
        btnCall.setOnClickListener { dialPhoneNumber(txtTelp.text.toString()) }
        btnAboutmi.setOnClickListener({keAboutmeActivity()})
    }

    companion object {
        val REQUEST_CODE = 100
    }


    private fun ambilData() {
        val bundle = intent.extras
        val nama = bundle?.getString("nama")
        val gender = bundle?.getString("gender")
        val umur = bundle?.getString("umur")
        val email = bundle?.getString("email")
        val telp = bundle?.getString("telp")
        val alamat = bundle?.getString("alamat")
        txtName.text = nama
        txtGender.text = gender
        txtUmur.text = umur
        txtEmail.text = email
        txtTelp.text = telp
        txtAddress.text = alamat
    }

    private fun navigasiKeEditProfil() {
        val intent = Intent(this, EditProfilActivity::class.java)
        // mengirimkan data dengan keyName "nama"
        val namaUser = txtName.text.toString()
        intent.putExtra("nama", namaUser)
        startActivityForResult(intent, REQUEST_CODE)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {                 //jika sukses maka data hasil edit kita tampikan ke TextView txtName
                val result = data?.getStringExtra("nama")
                txtName.text = result
            } else {                 //jika gagal maka tampilkan toast
                Toast.makeText(this, "Edit failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //fungsi untuk melakukan dial
    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun keAboutmeActivity(){
        val intent = Intent (this, AboutmeActivity::class.java)
        startActivity(intent)
    }

}
