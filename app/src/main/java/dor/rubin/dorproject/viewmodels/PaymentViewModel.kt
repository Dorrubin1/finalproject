package dor.rubin.dorproject.viewmodels

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import dor.rubin.dorproject.models.Cart
import dor.rubin.dorproject.models.Payment
import dor.rubin.dorproject.repository.CartRepository
import dor.rubin.dorproject.repository.PaymentRepository

class PaymentViewModel : ViewModel() {
     // live data:
    private val paymentRepository: PaymentRepository = PaymentRepository()
    val paymentLiveData: LiveData<List<Payment>> = paymentRepository.paymentLiveData
   // delete payment
    fun deletePaymentByFullName(fullName:String){
        paymentRepository.deleteCartFromDataBase(fullName)
    }
    // add payment
    fun addPayment(payment: Payment){
       paymentRepository.addCart(payment)
    }

}