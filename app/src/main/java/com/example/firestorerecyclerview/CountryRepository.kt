package com.example.firestorerecyclerview

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class CountryRepository(var onUpdate: (Int, Int)-> Unit) {
    private val db = FirebaseFirestore.getInstance()

    var lastFetched: DocumentSnapshot? = null
    val countries: ArrayList<String> = ArrayList()
    var isLoading: Boolean = false

    fun fetchData() {
        if (!isLoading) {
            isLoading = true
            var nextCountries = db.collection("countries")
                .orderBy("name")
                .limit(14)
            if (lastFetched != null) nextCountries = nextCountries.startAfter(lastFetched!!)

            nextCountries.get().addOnCompleteListener { task ->
                if (task.result?.documents?.isNotEmpty() == true) {
                    val names = task.result!!.documents.map { d -> d.data?.get("name") as String }
                    countries.addAll(names)
                    runBlocking {
                        if (lastFetched != null) delay(2000)
                        lastFetched = task.result?.documents?.last()
                        onUpdate(countries.size - names.size - 1 , names.size)
                        isLoading = false
                    }
                } else {
                    isLoading = false
                }
            }
        }
    }
}