package com.tc.domain

class Calculator {

    // a -> MAx, b = 0
    fun add(a:Int, b : Int) : Int {
        return a +b
    }

    fun bs(list : List<Int>, key  : Int) : Boolean{
        var l = 0
        var r  = list.size -1
        var mid = 0
        while (l < r){
//            var mid = (l+r)/2
            mid = l +   (r - l) / 2    // vs l+r/2
            if(list[mid] == key){
                return true
            }
            if(list[mid] < key ){
                l = mid +1
            }
            else {
                r = mid - 1
            }

        }

        return false
    }

}