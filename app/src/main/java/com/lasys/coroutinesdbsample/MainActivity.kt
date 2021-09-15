package com.lasys.coroutinesdbsample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.lasys.coroutinesdbsample.adapter.SubscribersAdapter
import com.lasys.coroutinesdbsample.databinding.ActivityMainBinding
import com.lasys.coroutinesdbsample.db.entity.Subscriber
import com.lasys.coroutinesdbsample.viewmodel.SubscriberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val subscriberViewModel: SubscriberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /*binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)*/

        //subscriberViewModel = ViewModelProvider.AndroidViewModelFactory()
        //val dao: SubscriberDAO = AppDatabase.getDatabase(applicationContext).getSubscriberDAO()
        //val repository = SubscriberRepository(dao)
        //val factory = SubscriberViewModelFactory(repository)
        // subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        displaySubscribersList()

        subscriberViewModel.message.observe(this, {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displaySubscribersList() {

        subscriberViewModel.subscribers.observe(this, {
            prepareList(it)
        })

        /*subscriberViewModel.getSaveSubscribers().observe(this, Observer {
            prepareList(it)

        })*/
    }

    private fun prepareList(it: List<Subscriber>?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = it?.let { it1 ->
            SubscribersAdapter(
                it1,
                { selectedItem: Subscriber -> listItemClicked(selectedItem) })
        }

    }

    fun listItemClicked(subscriber: Subscriber) {
        //Toast.makeText(this,"selectedItem = ${subscriber.name}", Toast.LENGTH_LONG).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}