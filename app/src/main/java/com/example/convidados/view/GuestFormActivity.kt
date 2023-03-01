package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewmodel.GuestFormViewModel
import com.example.convidados.R
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var viewModel: GuestFormViewModel
    private lateinit var binding: ActivityGuestFormBinding
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]
        binding.btnSalvar.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        this.observer()
        loadData()

    }

    private fun observer() {
        viewModel.guest.observe(this, Observer{
          binding.editNome.setText(it.name)
            if (it.precense){
                binding.radioPresent.isChecked = true
            }else{
                binding.radioAusente.isChecked = true
            }
        })

        viewModel.guestSave.observe(this, Observer {
            if (it != "") {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null){
            val guestID = bundle.getInt("Guestid")
            viewModel.get(guestID)
        }
    }

    override fun onClick(view: View) {
       if(view.id == R.id.btn_salvar){
           val name = binding.editNome.text.toString()
           val presente = binding.radioPresent.isChecked
           val model = GuestModel(guestId,name,presente)
            viewModel.save(model)
       }
    }

}