package com.example.josegarcia.todaymeal.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.model.Ingredient;
import com.example.josegarcia.todaymeal.widget.ListViewWidgetService;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class MealAppWidget extends AppWidgetProvider {
    public static ArrayList<Ingredient> widgetIngredients;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, ArrayList<Ingredient> ingredients) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.meal_app_widget);
        Intent intent = new Intent(context, ListViewWidgetService.class);
        widgetIngredients = ingredients;
        views.setRemoteAdapter(R.id.ingredients_list, intent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateIngredients(Context context, AppWidgetManager manager,
                                         int[] ids, ArrayList<Ingredient> ingredients) {
        for(int id: ids) {
            updateAppWidget(context, manager, id, ingredients);
        }
    }
}

