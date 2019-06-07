package com.example.josegarcia.todaymeal.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.josegarcia.todaymeal.R
import com.example.josegarcia.todaymeal.adapter.DescriptionAdapter
import com.example.josegarcia.todaymeal.databinding.FragmentRecipeDescriptionBinding
import com.example.josegarcia.todaymeal.view_model.RecipeDescriptionViewModel
import kotlinx.android.synthetic.main.fragment_recipe_description.toolbar
import kotlinx.android.synthetic.main.fragment_recipe_description.appbar as appBar
import kotlinx.android.synthetic.main.fragment_recipe_description.collapsing_toolbar_layout as collapsingToolbarLayout
import kotlinx.android.synthetic.main.fragment_recipe_description.recipe_description as recipeDescription
import kotlinx.android.synthetic.main.fragment_recipe_description.recipe_image as recipeImage

class RecipeDescriptionFragment : Fragment() {

    private val viewModel: RecipeDescriptionViewModel by lazy {
        ViewModelProviders.of(this).get(RecipeDescriptionViewModel::class.java).apply {
            arguments?.let {
                this.recipe = RecipeDescriptionFragmentArgs.fromBundle(it).recipe
                requireActivity().windowManager.defaultDisplay.getSize(this.activitySize)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentRecipeDescriptionBinding>(
            inflater,
            R.layout.fragment_recipe_description,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collapsingToolbarLayout.setupWithNavController(toolbar, findNavController())
        work_arround_while_no_solution_is_provided()
        populateLayout()
    }

    private fun populateLayout() {
        val adapter = DescriptionAdapter()
        adapter.submit(viewModel.ingredients, viewModel.steps)
        recipeDescription.adapter = adapter
    }

    /* TODO
    * Discussion link: https://issuetracker.google.com/issues/121078028
    * remove it when solution comes up
    */
    private fun work_arround_while_no_solution_is_provided() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
    }
}
