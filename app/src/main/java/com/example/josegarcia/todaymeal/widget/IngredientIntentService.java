package com.example.josegarcia.todaymeal.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.josegarcia.todaymeal.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientIntentService extends IntentService {
    private static final String NAME = "IngredientIntentService";
    public static final String ACTION_UPDATE_WIDGET = "update_widget_action";
    public static final String ACTION_SETUP_ADAPTER = "update_widget_action";
    public static final String INGREDIENTS = "ingredients_value";
    public static final String INGREDIENT = "ingredient";

    public IngredientIntentService() {
        super(NAME);
    }

    public static void startUpdateWidgetService(Context context, List<Ingredient> ingredients) {
        Intent intent = new Intent(context, IngredientIntentService.class);
        intent.setAction(ACTION_UPDATE_WIDGET);
        ArrayList<Ingredient> aux = new ArrayList<>();
        aux.addAll(ingredients);
        intent.putParcelableArrayListExtra(INGREDIENTS, aux);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action.equals(ACTION_UPDATE_WIDGET)) {
                ArrayList<Ingredient> ingredients = intent.getParcelableArrayListExtra(INGREDIENTS);
                handleUpdateWidget(ingredients);
            }
        }
    }

    private void handleUpdateWidget(ArrayList<Ingredient> ingredients) {
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        int[] ids = manager.getAppWidgetIds(new ComponentName(this, MealAppWidget.class));
        MealAppWidget.updateIngredients(this, manager, ids, ingredients);
    }
}
