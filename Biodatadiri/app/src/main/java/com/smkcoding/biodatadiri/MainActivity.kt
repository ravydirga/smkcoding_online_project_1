package com.smkcoding.biodatadiri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //inisialisasi variabel global untuk menampung data inputan user
    private var namaInput : String = ""
    private var emailInput : String = ""
    private var telpInput : String = ""
    private var alamatInput : String = ""
    private var genderInput : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       setDataSpinnerGender()
        //memberi click listener ke tombol Save.         //jika tombol save ditekan, maka akan menjalankan method validasiInput()
        btnSave.setOnClickListener { validasiInput() }     }

        //fungsi untuk set data pada spinner gender dengan string-array jenis kelamin
    private fun setDataSpinnerGender(){
    val adapter = ArrayAdapter.createFromResource(this, R.array.jenis_kelamin, android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGender.adapter = adapter     }
        //fungsi untuk melakukan validasi input
    private fun validasiInput(){         //mendapatkan value/data dari tiap EditText dan menyimpannya ke dalam variabel yang telah dibuat
             namaInput = edtName.text.toString()
            emailInput = edtEmail.text.toString()
            telpInput = edtTelp.text.toString()
            alamatInput = edtAddress.text.toString()
            genderInput = spinnerGender.selectedItem.toString()
        when{             //cek di tiap inputan apakah kosong atau tidak, jika kosong maka tampilkan error
            namaInput.isEmpty() -> edtName.error = "Nama tidak boleh kosong"
            genderInput.equals("Pilih Jenis Kelamin", ignoreCase = true) -> tampilToast("Jenis Kelamin harus dipilih")
            emailInput.isEmpty() -> edtEmail.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> edtTelp.error = "Telp tidak boleh kosong"
            alamatInput.isEmpty() -> edtAddress.error = "Alamat tidak boleh kosong"
            else -> {                 //jika semua inputan telah diisi, maka jalankan method goToProfilActivity
                tampilToast("Navigasi ke halaman profil")
                goToProfilActivity()
            }
        }
        }
                //fungsi untuk menampilkan toast
                private fun tampilToast(message: String) {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()     }
                //fungsi untuk berpindah ke ProfilActivity, sekaligus mengirimkan data hasil input user
                private fun goToProfilActivity() {
                    val intent = Intent(this, ProfilActivity::class.java)
                val bundle = Bundle()
                    bundle.putString("nama", namaInput)
                    bundle.putString("gender", genderInput)
                    bundle.putString("email", emailInput)
                    bundle.putString("telp", telpInput)
                    bundle.putString("alamat", alamatInput)
                intent.putExtras(bundle)
                startActivity(intent)     }
}

