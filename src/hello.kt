import java.util.*    // required import


fun randomDay() : String {
    val week = arrayOf ("Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday")
    return week[Random().nextInt(week.size)]
}

fun fishFood (day : String) : String {
    val food : String
    //var food = ""
    when (day) {
        "Monday" -> food = "flakes"
        "Tuesday" -> food = "pellets"
        "Wednesday" -> food = "redworms"
        "Friday" -> food = "mosquitoes"
        "Saturday" -> food = "lettuce"
        else -> food = "nothing"
    }
    return food
}

fun feedTheFish() {
    val day = randomDay()
    val food = fishFood(day)
    println ("Today is $day and the fish eat $food")
    println("Change water: ${shouldChangeWater(day)}")
}

fun swim(speed: String = "fast") {
    println("swimming $speed")
}

fun isTooHot(temperature: Int) = temperature > 30

fun isDirty(dirty: Int) = dirty > 30

fun isSunday(day: String) = day == "Sunday"

fun shouldChangeWater(day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    return when {
        isTooHot(temperature) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else -> false
    }
}

fun updateDirty(dirty: Int, operation: (Int) -> Int): Int {
    return operation(dirty)
}

fun increaseDirty( start: Int ) = start + 1

fun main(args: Array<String>) {
    println("Hello, ${args[0]}")
    // Will assign kotlin.Unit
    val isUnit = println("This is an expression")
    println(isUnit)
    val temperature = 10
    val isHot = if (temperature > 50) true else false
    println(isHot)
    val message = "The water temperature is ${ if (temperature > 50) "too warm" else "OK" }."
    println(message)
    feedTheFish()
    swim()   // uses default speed
    swim("slow")   // positional argument
    swim(speed="turtle-like")   // named parameter
    val decorations = listOf ("rock", "pagoda", "plastic plant", "alligator", "flowerpot")
    println( decorations.filter {it[0] == 'p'})
    println( decorations.filter {it[1] == 'l'})
    // eager, creates a new list
    val eager = decorations.filter { it [0] == 'p' }
    println("eager: $eager")
    // lazy, will wait until asked to evaluate
    val filtered = decorations.asSequence().filter { it[0] == 'p' }
    println("filtered: $filtered")
    // force evaluation of the lazy list
    val newList = filtered.toList()
    println("new list: $newList")
    val lazyMap = decorations.asSequence().map {
        println("access: $it")
        it
    }
    println("lazy: $lazyMap")
    println("-----")
    println("first: ${lazyMap.first()}")
    println("-----")
    println("all: ${lazyMap.toList()}")
    val lazyMap2 = decorations.asSequence().filter {it[0] == 'p'}.map {
        println("access: $it")
        it
    }
    println("-----")
    println("filtered: ${lazyMap2.toList()}")
    var dirtyLevel = 20
    var waterFilter = { dirty : Int -> dirty / 2}
    println(waterFilter(dirtyLevel))
    var waterFilter1 = { x : Int -> x / 2}
    println(waterFilter1(dirtyLevel))
    val waterFilter3: (Int) -> Int = { dirty -> dirty / 2 }
    println(waterFilter3(dirtyLevel))
    println(updateDirty(30, waterFilter))
    println(updateDirty(15, ::increaseDirty))
    var dirtyLevel1 = 19;
    dirtyLevel1 = updateDirty(dirtyLevel1) { dirtyLevel1 -> dirtyLevel1 + 23}
    println(dirtyLevel1)
}
