package com.example.exercicio_aula_23

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.fragment_user_infos.*

class UserInfosFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_infos, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Código para salvar os dados neste fragment
        activity?.let {
            viewModel = ViewModelProvider(it).get(MainViewModel::class.java)

            viewModel.updatedPartner.observe(this) {
                activity?.tvResultInfos?.text =
                    "Nome do Sócio: ${it.name}\n\nE-mail do Sócio: ${it.email}\n\nTelefone do Sócio: ${it.phone}"
            }
        }
    }
}
