package com.example.josegarcia.todaymeal.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.josegarcia.todaymeal.R;
import com.example.josegarcia.todaymeal.model.Ingredient;

import java.util.ArrayList;

public class ListViewWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this, intent);
    }

    class ListRemoteViewsFactory implements RemoteViewsFactory {
        private ArrayList<Ingredient> items;
        private Context context;

        ListRemoteViewsFactory(Context context, Intent intent) {
            if (MealAppWidget.widgetIngredients != null) {
                items = MealAppWidget.widgetIngredients;
            }
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {
            items.clear();
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public RemoteViews getViewAt(int i) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
            rv.setTextViewText(R.id.name, items.get(i).getName());
            rv.setTextViewText(R.id.quantity, Float.toString(items.get(i).getQuantity()));
            rv.setTextViewText(R.id.measure, items.get(i).getMeasure());

            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
