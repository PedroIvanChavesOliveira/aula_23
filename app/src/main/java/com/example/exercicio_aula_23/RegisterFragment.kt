package com.example.exercicio_aula_23

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var isNameOk = false
    private var isEmailOk = false
    private var isPhoneOk = false
    private var isPasswordOk = false
    private var isConfirmPasswordOk = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            viewModel = ViewModelProvider(it).get(MainViewModel::class.java)
        }

        initComponents()
    }

    fun initComponents() {
        tietPhone.addTextChangedListener(MaskWatcher(tietPhone, getString(R.string.phoneMask)))

        textChangedDefault(tietName, tilName, R.string.textInputName)
        textChangedDefault(tietEmail, tilEmail, R.string.textInputEmail)
        textChangedDefault(tietPhone, tilPhone, R.string.textInputPhone)
        textChangedDefault(tietPassword, tilPassword, R.string.textInputPassword)
        confirmPassword(tietConfirmPassword, tietPassword)

        btRegisterUser.setOnClickListener {
            if(isActivated()) {
                viewModel.setNewPartner(Partner(
                    name = tilName.editText?.text.toString(),
                    email = tilEmail.editText?.text.toString(),
                    phone = tilPhone.editText?.text.toString()))

                viewModel.changeToFragmentUserInfos(true)
            }
        }
    }

    private fun textChangedDefault(editText: EditText, textInput: TextInputLayout, errorString: Int) {
        editText.doOnTextChanged { text, _, _, _ ->
            if(text?.isBlank() == true) {
                textInput.error = getString(R.string.errorMessage, getString(errorString))
                setIsOkByTag(editText.tag as String ?: "", false)
            }else {
                textInput.isErrorEnabled = false
                setIsOkByTag(editText.tag as? String ?: "", true)
            }
            isActivated()
        }
//        editText.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun afterTextChanged(p0: Editable?) {}
//
//            override fun onTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
//                if(text.toString().isBlank()) {
//                    textInput.error = getString(R.string.errorMessage, getString(errorString))
//                    setIsOkByTag(editText.tag as String ?: "", false)
//                }else {
//                    textInput.isErrorEnabled = false
//                    setIsOkByTag(editText.tag as? String ?: "", true)
//                }
//                isActivated()
//            }
//        })
    }

    private fun confirmPassword(editText1: EditText, editText2: EditText) {
        editText1.doOnTextChanged { text, _, _, _ ->
            val password = editText2.text.toString()
            if(text.toString() == password) {
                setIsOkByTag(editText1.tag as String ?: "", true)
            }else {
                setIsOkByTag(editText1.tag as String ?: "", false)
            }

            isActivated()
        }

//        editText1.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun afterTextChanged(p0: Editable?) {}
//
//            override fun onTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
//                val password = editText2.text.toString()
//                if(text.toString() == password) {
//                    setIsOkByTag(editText2.tag as String ?: "", true)
//                }else {
//                    setIsOkByTag(editText2.tag as String ?: "", false)
//                }
//                isActivated()
//            }
//        })
    }

    private fun setIsOkByTag(tag: String, isOk: Boolean) {
        when (tag) {
            "name" -> isNameOk = isOk
            "email" -> isEmailOk = isOk
            "phone" -> isPhoneOk = isOk
            "password" -> isPasswordOk = isOk
            "confirmPassword" -> isConfirmPasswordOk = isOk
        }
    }

    private fun isActivated(): Boolean {
        val isOk: Boolean
        if(isNameOk && isEmailOk && isPhoneOk && isPasswordOk && isConfirmPasswordOk) {
            btRegisterUser.isEnabled = true
            isOk = true
        }else {
            btRegisterUser.isEnabled = false
            isOk = false
        }
        return isOk
    }
}