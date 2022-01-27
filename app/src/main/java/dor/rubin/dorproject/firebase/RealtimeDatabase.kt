package dor.rubin.dorproject.firebase

import com.google.firebase.database.FirebaseDatabase

class RealtimeDatabase {
    companion object{
        fun getRoot() = FirebaseDatabase.getInstance().reference
    }
}