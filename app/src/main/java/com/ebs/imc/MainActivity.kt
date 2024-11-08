package com.ebs.imc

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.ebs.imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        binding.btCalcular.setOnClickListener{
            //
            val editPeso = binding.editPeso.text.toString()
            val editAltura = binding.editAltura.text.toString()
            //
            if (editPeso.isEmpty()){
                binding.mensagem.setText("Informe o seu peso.")
            }else if (editAltura.isEmpty()){
                binding.mensagem.setText("Informe a sua altura.")
            }else{
                CalcularIMC()
            }
        }
        //
    }
    //
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    private fun CalcularIMC(){
        // captura novamente os valores dos campos e salva nas constantes
        val pesoID = binding.editPeso
        val alturaID = binding.editAltura
        //
        val peso = java.lang.Float.parseFloat(pesoID.text.toString())
        val altura = java.lang.Float.parseFloat(alturaID.text.toString())
        //
        val resultado = binding.mensagem
        val imc = peso / (altura * altura)
        //
        val Mensagem = when{
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau 1)"
            imc <= 39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }
        //
        imc.toString()
        resultado.setText(String.format("IMC:  %.2f  \n$Mensagem",imc))
        if (imc <= 18.5){
            resultado.setTextColor(getColor(R.color.yellow))
        }else if (imc <= 24.9){
            resultado.setTextColor(getColor(R.color.green))
        }else if (imc <= 29.9){
            resultado.setTextColor(getColor(R.color.orange))
        }else{
            resultado.setTextColor(getColor(R.color.red))
        }
    }
    //
    // Implementar o menu na tela com o icone
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal,menu)
        //
        return true
    }
    //
    // Imlementar a ação do icone do menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //
        when (item.itemId){
            R.id.reset ->{
                val limparEditPeso = binding.editPeso
                val limparEditAltura = binding.editAltura
                val limparMensagem = binding.mensagem
                //
                limparEditPeso.setText("")
                limparEditAltura.setText("")
                limparMensagem.setText("")
            }
        }
        //
        return super.onOptionsItemSelected(item)
    }
}