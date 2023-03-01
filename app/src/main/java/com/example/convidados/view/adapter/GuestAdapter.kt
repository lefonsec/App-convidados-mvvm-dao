package com.example.convidados.view.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.listener.OnGuestListener

class GuestAdapter(var itens: List<GuestModel>) :
    RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item, listener)
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(itens[position])
    }

    fun attachListener(guestListener: OnGuestListener) {
        listener = guestListener
    }

    class GuestViewHolder(var binding: RowGuestBinding, var listener: OnGuestListener) :
        ViewHolder(binding.root) {
        val nome = binding.textName

        fun bind(guests: GuestModel) {
            nome.text = guests.name

            binding.textName.setOnClickListener {
                listener.onClick(guests.id)
            }

            binding.textName.setOnLongClickListener {
                AlertDialog.Builder(itemView.context)
                    .setTitle("Remoção de convidado")
                    .setMessage("Tem certeza que deseja remover ?")
                    .setPositiveButton("Sim") { dialog, which ->
                        listener.onDelete(guests.id)
                    }
                    .setNegativeButton("Não",null)
                    .create()
                    .show()

                true
            }
        }

    }
}