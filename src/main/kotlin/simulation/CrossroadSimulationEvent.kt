package simulation

import base.SimulationEvent
import data.Car
import data.Direction

/**
 * Simulation event
 * */

sealed interface CrossroadSimulationEvent : SimulationEvent {

    /**
     * Event that signalizing that semaphore light switched. Sempahores that are red are going to be green
     * and otherwise
     * */
    class SemaphoreChange(override val eventTime: Int) : CrossroadSimulationEvent

    /**
     * Event signalizing car arrived on crossroad
     * */
    data class CarArriveOnSemaphore(
        override val eventTime: Int,
        val direction: Direction,
        val car: Car,
    ) : CrossroadSimulationEvent

    /**
     * Event signalizing light on this direction is green and car is starting to leave the crossroad
     * */
    data class CarStartLeavingCrossroad(
        override val eventTime: Int,
        val direction: Direction,
        val car: Car,
        val timeWhenCarLeaves: Int,
    ) : CrossroadSimulationEvent

    /**
     * Event signalizing carr crossed the crossroad and left
     * */
    data class CarLeavesCrossRoad(
        override val eventTime: Int,
        val direction: Direction,
        val car: Car,
    ) : CrossroadSimulationEvent

    /**
     * Event signalizes end of simulation
     * */
    class SimulationEnd(override val eventTime: Int) : CrossroadSimulationEvent

}


