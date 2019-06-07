package com.example.josegarcia.todaymeal.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.adapter.RecipeListAdapter
import com.example.josegarcia.todaymeal.databinding.FragmentRecipeListBinding
import com.example.josegarcia.todaymeal.view_model.RecipeListViewModel
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.no_connection as noConnectionMessage
import kotlinx.android.synthetic.main.fragment_recipe_list.progress_bar as progressBar
import kotlinx.android.synthetic.main.fragment_recipe_list.recipe_list as recyclerView

class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by lazy {
        ViewModelProviders.of(this).get(RecipeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentRecipeListBinding>(
            inflater,
            R.layout.fragment_recipe_list,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
        recyclerView.adapter = RecipeListAdapter()
        viewModel.recipes
            .observe(this, Observer { recipes ->
                (recyclerView.adapter as RecipeListAdapter).submitList(recipes)
            })
    }
}
