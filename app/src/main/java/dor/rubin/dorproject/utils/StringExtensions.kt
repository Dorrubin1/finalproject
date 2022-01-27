package dor.rubin.dorproject.utils

import android.util.Patterns

fun CharSequence.isEmailValid(): Boolean {
    return this.length >= 6 && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}