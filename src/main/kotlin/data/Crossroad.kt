package data

import java.util.*

class Crossroad(
    /**
     * Directions where the semaphore lights are red
     * */
    val stoppedDirections: Set<Direction> = setOf(Direction.NORTH, Direction.SOUTH),
) {

    /**
     * Map of Cars that are currently crossing the crossroad, only one car can cross crossroad at a time
     * */
    private val _carsCrossing: MutableMap<Direction, CarLeaving> = mutableMapOf()
    val carsCrossing: Map<Direction, CarLeaving> = _carsCrossing

    /**
     * Map of direction and  queue of cars that are waiting here
     * */
    private val routeQueue: Map<Direction, Queue<Car>> = mapOf(
        Direction.NORTH to LinkedList(),
        Direction.SOUTH to LinkedList(),
        Direction.EAST to LinkedList(),
        Direction.WEST to LinkedList()
    )

    /**
     * Adds a car to a queue of cars waiting to cross the crossroad in the given [direction].
     * */
    fun addCarToQueue(car: Car, direction: Direction) = getDirectionQueue(direction).add(car)

    /**
     * Removes first car in specified [direction]
     * */
    fun moveCarToLeavingQueue(direction: Direction, timeWhenCarGone: Int): Car? =
        getDirectionQueue(direction).poll().also { _carsCrossing[direction] = CarLeaving(it, timeWhenCarGone) }

    /**
     * Checks whether is there any car in this direction
     * */
    fun isCarWaiting(direction: Direction): Boolean = getDirectionQueue(direction).isNotEmpty()

    /**
     *  Returns queue for specified [direction] non null because
     *  !! bang operator can be used here because routeQueue has all directions initialized and is immutable
     * */
    private fun getDirectionQueue(direction: Direction) = routeQueue[direction]!!

    data class CarLeaving(val car: Car, val timeToLave: Int)
}