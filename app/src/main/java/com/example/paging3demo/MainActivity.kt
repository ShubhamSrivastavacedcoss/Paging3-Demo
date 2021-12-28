package com.example.paging3demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3demo.Adapter.RecyclerviewAdapter
import com.example.paging3demo.databinding.ActivityMainBinding
import com.example.paging3demo.viewmodels.MovieViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    // lateinit var quoteViewModel: QuoteViewModel
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var recyclerViewAdapter: RecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView()
        getInitViewModel()

        /*     activityMainBinding.rv.setLayoutManager(LinearLayoutManager(this))
             activityMainBinding.rv.setHasFixedSize(true)

             recyclerViewAdapter = RecyclerviewAdapter()
             recyclerViewAdapter

             val bookAdapter = RecyclerviewAdapter()
             //set adapter here
             activityMainBinding.rv.adapter = bookAdapter
 */

        /*  val quotesApi = RetrofitHelper.getInstance().create(QuoteApi::class.java)

          val quoteRepository = QuoteRepository(quotesApi)

          quoteViewModel = ViewModelProvider(this, QuoteViewModelFactory(quoteRepository))
              .get(QuoteViewModel::class.java)

          quoteViewModel.quotes.observe(this, androidx.lifecycle.Observer {
              // Log.i("HIT", " " + it.results.toString())

              bookAdapter.setdataList(it.Search)


              //   activityMainBinding.dataTv.text = it.per_page.toString()

          })*/

    }

    fun initRecyclerView() {
        activityMainBinding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            recyclerViewAdapter = RecyclerviewAdapter()
            adapter = recyclerViewAdapter
        }
    }

    fun getInitViewModel() {
        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
}