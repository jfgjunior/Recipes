package com.example.josegarcia.todaymeal.views

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.adapter.RecipeListAdapter
import com.example.josegarcia.todaymeal.model.Recipe
import com.example.josegarcia.todaymeal.view_model.RecipeListFragmentVM
import kotlinx.android.synthetic.main.fragment_recipe_list.no_connection as noConnectionMessage
import kotlinx.android.synthetic.main.fragment_recipe_list.progress_bar as progressBar
import kotlinx.android.synthetic.main.fragment_recipe_list.recipe_list as recyclerView

@SuppressLint("ShowToast")
class RecipeListFragment : Fragment(), SelectRecipe {
    private val viewModel: RecipeListFragmentVM by lazy {
        ViewModelProviders.of(this).get(RecipeListFragmentVM::class.java)
    }
    
    private val noConnectionToast: Toast by lazy {
        val message = requireContext().resources.getString(R.string.no_connection)
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }

    companion object {
        const val RECIPE_KEY = "recipe_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_recipe_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    override fun onSelectRecipeListener(recipe: Recipe) {
        (activity as HomeActivity).onSelectRecipeListener(recipe)
    }

    private fun setUpRecyclerView() {
        progressBar.visibility = View.VISIBLE
        val adapter = RecipeListAdapter(this)
        recyclerView.adapter = adapter
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
