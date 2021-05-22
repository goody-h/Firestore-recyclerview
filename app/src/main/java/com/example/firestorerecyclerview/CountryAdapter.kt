package com.example.firestorerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CountryAdapter(private val countries: ArrayList<String>) :
        RecyclerView.Adapter<CountryAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View, val isLast: Boolean) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.country)
        val progress: View = view.findViewById(R.id.progress)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    override fun getItemViewType(position: Int): Int {

        return if (position == itemCount -1) 1 else 0
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.country_item, viewGroup, false)

        return ViewHolder(view, viewType == 1)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = countries[position]
        if (viewHolder.isLast) {
            viewHolder.progress.visibility = View.VISIBLE
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = countries.size

}
