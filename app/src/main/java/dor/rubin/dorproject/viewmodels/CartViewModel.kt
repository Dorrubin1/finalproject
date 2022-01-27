package dor.rubin.dorproject.viewmodels

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import dor.rubin.dorproject.models.Cart
import dor.rubin.dorproject.repository.CartRepository

class CartViewModel : ViewModel() {
     // live data:
    private val cartRepository: CartRepository = CartRepository()
    val cartLiveData: LiveData<List<Cart>> = cartRepository.cartLiveData

    fun deleteCartByName(cName:String){
        cartRepository.deleteCartFromDataBase(cName)
    }

    fun addCart(cart: Cart){
       cartRepository.addCart(cart)
    }

    fun cleanTheCart(){
        cartRepository.cleanTheCart()
    }

}