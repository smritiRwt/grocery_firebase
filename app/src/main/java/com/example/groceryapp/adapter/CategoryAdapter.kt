package com.example.groceryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.groceryapp.R
import com.example.groceryapp.models.Category
import com.example.groceryapp.utils.IconPicker

class CategoryAdapter(val context: Context, val category: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_item_home, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.iconView.setImageResource(IconPicker.getIcon())
        Glide.with(holder.itemView.context).load(category.get(position).image).
        placeholder(R.drawable.login).centerCrop().into(holder.iconView)

        //   postImageView.setImageResource(BASE_URL+postItem.image)



        holder.itemView.setOnClickListener {
           Toast.makeText(context, category[position].name, Toast.LENGTH_SHORT).show()
          /*  val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra("DATE", quizzes[position].title)
            context.startActivity(intent)
        */}
    }

    override fun getItemCount(): Int {
        return category.size
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iconView: ImageView = itemView.findViewById(R.id.imagePost)
    }
}