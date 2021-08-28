package com.lasys.coroutinesdbsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lasys.coroutinesdbsample.databinding.SubscriberItemBinding
import com.lasys.coroutinesdbsample.db.entity.Subscriber
import com.lasys.coroutinesdbsample.generated.callback.OnClickListener

class SubscribersAdapter(val subscriberList: List<Subscriber>,
                         private val clickListener:(Subscriber)->Unit) :
    RecyclerView.Adapter<SubscribersAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SubscriberItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val subscriber = subscriberList.get(position)
        holder.bind(subscriber,clickListener)
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }


    class MyViewHolder(val layoutItemBinding: SubscriberItemBinding) :
        RecyclerView.ViewHolder(layoutItemBinding.root) {

        fun bind(item: Subscriber, clickListener:(Subscriber)->Unit) {
            layoutItemBinding.subscriber = item
            layoutItemBinding.executePendingBindings()
            layoutItemBinding.mainView.setOnClickListener {
               clickListener(item)
            }
        }

    }
}