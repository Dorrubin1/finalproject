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

class CartRepository {
    // open a path and if there is a path with the same name then it approaches it
    private val ref: DatabaseReference = RealtimeDatabase.getRoot().child("users")
        .child(FirebaseAuth.getInstance().uid ?: "undefined").child("cart")
    private val cartCall: MutableLiveData<List<Cart>> = MutableLiveData()
    val cartLiveData: LiveData<List<Cart>> get() = cartCall
       //Listener of changes
    private val listener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val cart = snapshot.children
                .mapNotNull {
                    it.getValue(Cart::class.java)
                }

            cartCall.postValue(cart)
        }


        override fun onCancelled(error: DatabaseError) {
            println(error.message)
        }
    }
       // delete from fire base by name
        fun deleteCartFromDataBase(cName: String) {
            ref.get()
                .addOnSuccessListener {
                    val foundCart = it.children
                        .find { child ->
                            child.child("name").value!! == cName
                        }
                    foundCart?.ref?.removeValue()
                    if (foundCart == null) {
                        println("no cart found with name $cName")
                    }


                }
        }

    fun cleanTheCart(){
        ref.removeValue()
    }
    // add to firebase
    fun addCart(cart: Cart){
        ref.push()
            .setValue(cart)
    }

    init {
        ref.addValueEventListener(listener)
    }




    }
