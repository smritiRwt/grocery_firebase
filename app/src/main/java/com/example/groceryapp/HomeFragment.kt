package com.example.groceryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.groceryapp.adapter.CategoryAdapter
import com.example.groceryapp.models.Category
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    lateinit var adapter: CategoryAdapter
    private var catList = mutableListOf<Category>()
    lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    fun setUpViews() {
        setUpFireStore()
        setUpRecyclerView()
    }




    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("grocery")
        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(context, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            catList.clear()
            catList.addAll(value.toObjects(Category::class.java))
            adapter.notifyDataSetChanged()
        }
    }


    private fun setUpRecyclerView() {
        adapter = CategoryAdapter(requireContext(), catList)
        rcyclerview.layoutManager =   StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        rcyclerview.adapter = adapter
    }

   }