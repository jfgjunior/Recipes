package com.example.josegarcia.todaymeal.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.SimpleIdlingResource;
import com.example.josegarcia.todaymeal.adapter.RecipeListAdapter;
import com.example.josegarcia.todaymeal.model.Recipe;
import com.example.josegarcia.todaymeal.view_model.RecipeListFragmentVM;

public class RecipeListFragment extends Fragment implements SelectRecipe {
    public static final String RECIPE_KEY = "recipe_key";
    private RecipeListFragmentVM viewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView noConnectionMessage;
    private Toast noConnectionToast;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        viewModel = ViewModelProviders.of(this).get(RecipeListFragmentVM.class);
        recyclerView = view.findViewById(R.id.recipe_list);
        progressBar = view.findViewById(R.id.progress_bar);
        noConnectionMessage = view.findViewById(R.id.no_connection);
        setUpToast();
        setUpRecyclerView();
        return view;
    }

    private void setUpToast() {
        String message = getContext().getResources().getString(R.string.no_connection);
        noConnectionToast = new Toast(getContext()).makeText(getContext(), message, Toast.LENGTH_SHORT);
    }

    @Override
    public void onSelectRecipeListener(Recipe recipe) {
        ((HomeActivity) getActivity()).onSelectRecipeListener(recipe);
    }

    private void setUpRecyclerView() {
        progressBar.setVisibility(View.VISIBLE);
        RecipeListAdapter adapter = new RecipeListAdapter(this);
        recyclerView.setAdapter(adapter);
        observeData();
        verifyConnection();
    }

    private void observeData() {
        viewModel.getRecipesObservable()
                .observe(this, recipes -> {
                    ((RecipeListAdapter) recyclerView.getAdapter()).submitList(recipes);
                    progressBar.setVisibility(View.GONE);
                    noConnectionMessage.setVisibility(View.GONE);
                    SimpleIdlingResource.getInstance().setIdleState(true);
                });
        viewModel.getRecipes();
    }

    private void verifyConnection() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE));
        boolean isConnected = connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        if (!isConnected) {
            progressBar.setVisibility(View.GONE);
            if (recyclerView != null || recyclerView.getAdapter().getItemCount() == 0) {
                noConnectionMessage.setVisibility(View.VISIBLE);
            } else {
                noConnectionToast.cancel();
                noConnectionToast.show();
            }
        }
    }
}
