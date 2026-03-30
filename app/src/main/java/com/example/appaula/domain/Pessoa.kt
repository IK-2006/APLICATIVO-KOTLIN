package com.example.appaula.domain

data class Pessoa(var id:Int?, var nome:String, var idade:Int){

    fun getDadosPessoa(): String {
        var ret:String = "$nome tem $idade anos. Viveu aproximadamente "+
                (idade*365)+" dias."
        return ret
    }

}
