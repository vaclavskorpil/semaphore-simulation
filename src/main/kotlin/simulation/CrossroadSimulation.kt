package simulation

import CrossroadSimulationEventCreator
import base.Simulation
import data.CrossroadSimulationState
import data.SimulationResult
import simulation.CrossroadSimulationEvent.CarArriveOnSemaphore
import simulation.CrossroadSimulationEvent.CarLeavesCrossRoad
import simulation.CrossroadSimulationEvent.CarStartLeavingCrossroad
import simulation.CrossroadSimulationEvent.SemaphoreChange
import simulation.CrossroadSimulationEvent.SimulationEnd

/**
 * Crossroad simulation, state machine that creates new stated based on incoming events from [eventCreator] until SimulationEvent [SimulationEnd] is received.
 *
 *  @param eventCreator creator that generates events for simulation
 * */
class CrossroadSimulation(
    private val eventCreator: CrossroadSimulationEventCreator,
) : Simulation<CrossroadSimulationState, CrossroadSimulationEvent> {

    /**
     * Starts simulation.
     * collects simulation states that create new events which maps to new states until [SimulationEnd] event is received.
     * */
    override fun startSimulation(
        initialState: CrossroadSimulationState,
        simulationEnd: Int,
    ): SimulationResult<CrossroadSimulationState, CrossroadSimulationEvent> {
        var currentState = initialState
        val simulationStateTimeline = mutableListOf(currentState)
        val simulationEventTimeline = mutableListOf<CrossroadSimulationEvent>()

        do {
            val currentEvent = eventCreator.nextEvent(currentState, simulationEnd)
            currentState = mapEventToState(currentEvent, currentState)
            simulationStateTimeline.add(currentState)
            simulationEventTimeline.add(currentEvent)
        } while (currentEvent !is SimulationEnd)

        return SimulationResult(simulationEventTimeline, simulationStateTimeline)
    }

    /**
     * Creates new state based on the incoming event
     * */
    private fun mapEventToState(
        event: CrossroadSimulationEvent,
        currentState: CrossroadSimulationState,
    ): CrossroadSimulationState {
        return when (event) {
            is CarArriveOnSemaphore -> handleCarrArrivedOnSemaphore(event, currentState)
            is CarStartLeavingCrossroad -> handleCarLeavesCrossRoad(event, currentState)
            is CarLeavesCrossRoad -> TODO("Implement in similar fashion")
            is SemaphoreChange -> TODO("Implement in similar fashion")
            is SimulationEnd -> TODO("Implement in similar fashion")
        }

    }

    /**
     * Creates new state based on from CarArriveOnSemaphore event. Changes the time of simulation and adds new car to the queue.
     * */
    private fun handleCarrArrivedOnSemaphore(
        event: CarArriveOnSemaphore,
        currentState: CrossroadSimulationState,
    ): CrossroadSimulationState = currentState.copy(currentTime = event.eventTime).apply {
        crossroad.addCarToQueue(event.car, event.direction)
    }

    /**
     * Creates new state based on from CarStartLeavingCrossroad event. Changes the time of simulation and moves car from waiting queue to laving cars queue.
     * */
    private fun handleCarLeavesCrossRoad(
        event: CarStartLeavingCrossroad,
        currentState: CrossroadSimulationState,
    ): CrossroadSimulationState = currentState.copy(currentTime = event.eventTime).apply {
        crossroad.moveCarToLeavingQueue(event.direction, event.timeWhenCarLeaves)
    }
}
