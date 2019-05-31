package com.example.josegarcia.todaymeal.views

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.adapter.RecipeListAdapter
import com.example.josegarcia.todaymeal.view_model.RecipeListFragmentVM
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.no_connection as noConnectionMessage
import kotlinx.android.synthetic.main.fragment_recipe_list.progress_bar as progressBar
import kotlinx.android.synthetic.main.fragment_recipe_list.recipe_list as recyclerView

@SuppressLint("ShowToast")
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListFragmentVM by lazy {
        ViewModelProviders.of(this).get(RecipeListFragmentVM::class.java)
    }

    private val noConnectionToast: Toast by lazy {
        val message = requireContext().resources.getString(R.string.no_connection)
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_recipe_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        progressBar.visibility = View.VISIBLE
        recyclerView.adapter = RecipeListAdapter()
        observeData()
        verifyConnection()
    }

    private fun observeData() {
        viewModel.recipesObservable
            .observe(this, Observer { recipes ->
                (recyclerView.adapter as RecipeListAdapter).submitList(recipes)
                progressBar.visibility = View.GONE
                noConnectionMessage.visibility = View.GONE
            })
        viewModel.getRecipes()
    }

    private fun verifyConnection() {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val isConnected =
            connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
        if (!isConnected) {
            progressBar.visibility = View.GONE
            if (recyclerView != null || recyclerView.adapter?.itemCount == 0) {
                noConnectionMessage.visibility = View.VISIBLE
            } else {
                noConnectionToast.cancel()
                noConnectionToast.show()
            }
        }
    }
}
