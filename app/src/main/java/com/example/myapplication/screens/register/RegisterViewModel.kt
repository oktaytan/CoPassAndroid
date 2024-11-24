package com.example.myapplication.screens.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Event
import com.example.myapplication.R

class RegisterViewModel: ViewModel() {

    val username = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val masterPassword = MutableLiveData<String>()
    val reMasterPassword = MutableLiveData<String>()

    public var errors = MutableLiveData<Event<ArrayList<RegisterError>>>()
    private var errorList: ArrayList<RegisterError> = ArrayList()

    public fun validate(): Boolean {
        errorList = ArrayList()

        username.value?.let {
            if (it.length < 4) {
                errorList.add(RegisterError.INVALID_USERNAME)
            }
        } ?: errorList.add(RegisterError.BLANK_USERNAME)

        email.value?.let {
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
            if (!it.matches(emailRegex)) {
                errorList.add(RegisterError.INVALID_EMAIL)
            }
        } ?: errorList.add(RegisterError.BLANK_EMAIL)

        masterPassword.value?.let { masterPassword -> String
            if (masterPassword.length < 4) {
                errorList.add(RegisterError.INVALID_EMAIL)
            } else {
                reMasterPassword.value?.let {
                    if (it != masterPassword) {
                        errorList.add(RegisterError.UNMATCHED_PASSWORD)
                    }
                } ?: errorList.add(RegisterError.BLANK_RE_PASSWORD)
            }
        } ?: errorList.add(RegisterError.BLANK_PASSWORD)

        reMasterPassword.value?.let {

        } ?: errorList.add(RegisterError.BLANK_RE_PASSWORD)

        errors.value = Event(errorList)

        return errorList.isEmpty()
    }

}

enum class RegisterError(private val message: Int) {
    BLANK_USERNAME(R.string.blank_field_warning),
    BLANK_EMAIL(R.string.blank_field_warning),
    BLANK_PASSWORD(R.string.blank_field_warning),
    BLANK_RE_PASSWORD(R.string.blank_field_warning),
    INVALID_USERNAME(R.string.invalid_username_warning),
    INVALID_EMAIL(R.string.invalid_email_warning),
    INVALID_PASSWORD(R.string.invalid_password_warning),
    UNMATCHED_PASSWORD(R.string.unMatch_password_warning);

    fun getMessage(): Int {
        return message
    }
}