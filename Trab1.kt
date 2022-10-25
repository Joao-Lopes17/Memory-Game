
val SYMBOLS = "#%XO&"

fun main() {
    val pairs = (SYMBOLS+SYMBOLS).toList().shuffled()
    //println(pairs)
    println("Foram gerados ${SYMBOLS.length} pares aleatórios de símbolos.")
    println("Vamos jogar!")
    var places: List<Char> = allHiddenPairs(pairs)
    var trys = 0
    do {
        println("$trys: $places")
        val first = readPosition("primeira")
        val second = readPosition("segunda")
        if ( isValidPositions(first, second, places) ) {
            places = places.play(first, second, pairs)
            trys++
        } else println("Posições inválidas")
    } while( ! isAllPairsShowed(places) )
    println("$trys: $places")
    println("Terminou o jogo usando $trys tentativas")
}

fun List<Char>.play(p1: Int, p2: Int, pairs: List<Char>): List<Char> {
    val turned = turnPlaces(p1, p2, pairs)
    if (turned[p1] != turned[p2]) {
        print(turned)
        repeat(4) { print('.'); Thread.sleep(1000) }
        print("\r                                                       \r")
        return this
    }
    return turned
}

fun allHiddenPairs(pairs: List<Char> ): List<Char>{
    return "_".repeat(pairs.size).toList()
}
//val listchar : List<Char> =listOf(repeat(pairs.size){'_'})  return listchar

fun readPosition(name:String):Int{
    println("Indique a $name posição a virar ? ")
    val pos=readln().toInt()
    return pos
}

fun isValidPositions(p1:Int, p2:Int, places:List<Char>):Boolean{ 
    val emp='_'  
    return if(p1==p2 || p1<0 || p1>=(places.size-1) || p2<0 || p2>(places.size-1) || places[p1]!=emp || places[p2]!=emp) false else  true
}

fun isAllPairsShowed(places:List<Char>):Boolean{
    val toFind='_'
    return (if(toFind in places) false else true)
}

fun List<Char>.turnPlaces(p1:Int, p2:Int, pairs:List<Char>):List<Char>{
    val lTurned=this.mapIndexed{i, value->
        if (this[p1] == '_' && i==p1) pairs[p1]
        else if (this[p1] !='_' && i==p1) '_'
        else if (this[p2] == '_' && i==p2) pairs[p2]
        else if (this[p2] !='_' && i==p2) '_'
        else value
    }
    return lTurned
}
