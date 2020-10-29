package com.example.exercicio_aula_23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(RegisterFragment())

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Código para mudar de tela ao clicar no botão
        viewModel.onDataSaved.observe(this, {
            if(it == true) {
                tlBar.getTabAt(1)?.select()
            }
        })

        tlBar.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        loadFragment(RegisterFragment())
                    }

                    1 -> {
                        loadFragment(UserInfosFragment())
                    }
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {/* Não usa */ }
            override fun onTabUnselected(tab: TabLayout.Tab?) { /* Não usa */}
        })
    }

    fun loadFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()
    }
}
