package com.android.sekarya_mobile_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.sekarya_mobile_app.MainActivity
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.custom_view.CustomDialog
import com.android.sekarya_mobile_app.databinding.ActivityFormPortofolioBinding
import com.google.android.material.chip.Chip

class FormPortofolioActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFormPortofolioBinding
    private lateinit var customDialog: CustomDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormPortofolioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Daftar tag yang tersedia
        val tagOptions = arrayOf("Tag1", "Tag2", "Tag3", "Tag4")

        // Buat adapter untuk AutoCompleteTextView
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tagOptions)
        binding.autoCompleteTextView.setAdapter(adapter)

        // Tambahkan listener untuk AutoCompleteTextView
        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->

            val selectedTag = adapter.getItem(position).toString()

            if (binding.chipGroup.childCount < 3){
                addChip(selectedTag)
                binding.autoCompleteTextView.setText("") // Bersihkan teks setelah menambahkan chip
            } else {
                Toast.makeText(this, "melebihi batas maksimum tag", Toast.LENGTH_SHORT).show()
            }


        }

//        cancel handler
        goToHome()
//        save handler
        save()


    }

    private fun addChip(tag: String) {
        val chip = Chip(this)
        chip.text = tag
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener {
            binding.chipGroup.removeView(chip)
        }
        binding.chipGroup.addView(chip)
    }

//  save handler

    private fun save(){
        binding.btnSave.setOnClickListener{
            val customDialog = CustomDialog(this)
            customDialog.show()
            customDialog.setImageResource(R.drawable.ic_detect_art)
            customDialog.setTitle("Popup Title")
            customDialog.setDescription("Popup Description")
            customDialog.setLoadingIndicatorVisible(true)

//            customDialog.dismiss() -> popup dialog gone

        }
    }

    private fun goToHome(){
        binding.btnCencel.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }



}