package com.example.josegarcia.todaymeal.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.model.Ingredient;

public class IngredientView extends CardView {

    public IngredientView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public IngredientView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public IngredientView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setIngredient(Ingredient ingredient) {
        initializeView(ingredient);
    }

    private void init(AttributeSet set) {
        LayoutInflater.from(getContext())
                .inflate(R.layout.view_ingredient, this, true);
        if (set == null) {
            return;
        }
    }

    private void initializeView(Ingredient ingredient) {
        TextView quantity = findViewById(R.id.quantity);
        ImageView measure = findViewById(R.id.measure_image);
        TextView ingredientName = findViewById(R.id.item_name);
        quantity.setText(Float.toString(ingredient.getQuantity()));
        measure.setImageResource(getMeasureImage(ingredient.getMeasure()));
        ingredientName.setText(ingredient.getName());
    }

    private int getMeasureImage(String measure) {
        switch (measure) {
            //TODO: Return proper image
        }
        return R.drawable.ic_noun_one_tablespoon_111987;
    }
}
