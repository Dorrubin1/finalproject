package dor.rubin.dorproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import dor.rubin.dorproject.firebase.RealtimeDatabase
import dor.rubin.dorproject.models.Cart


import dor.rubin.dorproject.models.Payment

class PaymentRepository {
    // open a path and if there is a path with the same name then it approaches it
    private val ref: DatabaseReference = RealtimeDatabase.getRoot().child("users").
    child(FirebaseAuth.getInstance().uid ?: "undefined").child("payment")
    private val paymentCall: MutableLiveData<List<Payment>> = MutableLiveData()
    val paymentLiveData: LiveData<List<Payment>> get() = paymentCall
       //Listener of changes
    private val listener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val payment = snapshot.children
                .mapNotNull {
                    it.getValue(Payment::class.java)
                }

            paymentCall.postValue(payment)
        }


        override fun onCancelled(error: DatabaseError) {
            println(error.message)
        }
    }
       // delete from fire base by fullName
        fun deleteCartFromDataBase(fullName: String) {
            ref.get()
                .addOnSuccessListener {
                    val foundCart = it.children
                        .find { child ->
                            child.child("fullName").value!! == fullName
                        }
                    foundCart?.ref?.removeValue()

                }
        }
    // add to firebase
    fun addCart(payment: Payment){
        ref.push()
            .setValue(payment)
    }

    init {
        ref.addValueEventListener(listener)
    }




    }
