package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.viewmodel.AllGuestsViewModel
import com.example.convidados.databinding.FragmentAllGuestsBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.OnGuestListener


class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestsViewModel
    private lateinit var adapterGuest: GuestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this)[AllGuestsViewModel::class.java]
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        this.observer()
        return binding.root
    }

    private fun adtadorRecicly(itens: List<GuestModel>) {
        val reyclerAllGuest = binding.reyclerAllGuest
        reyclerAllGuest.layoutManager = LinearLayoutManager(context)
        reyclerAllGuest.setHasFixedSize(true)
        adapterGuest = GuestAdapter(itens)
        reyclerAllGuest.adapter = adapterGuest
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {

                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("Guestid",id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }
        }
        adapterGuest.attachListener(listener)

    }


    private fun observer() {
        viewModel.getAll()
        viewModel.guests.observe(viewLifecycleOwner) {
            this.adtadorRecicly(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }
}