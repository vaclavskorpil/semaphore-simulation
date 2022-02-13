package simulation

import CrossroadSimulationEventCreator
import base.Simulation
import data.CrossroadSimulationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import simulation.CrossroadSimulationEvent.CarArriveOnSemaphore
import simulation.CrossroadSimulationEvent.CarLeavesCrossRoad
import simulation.CrossroadSimulationEvent.CarStartLeavingCrossroad
import simulation.CrossroadSimulationEvent.SemaphoreChange
import simulation.CrossroadSimulationEvent.SimulationEnd

/**
 * Crossroad simulation, state machine that creates new stated based on incoming events from [eventGenerator] until SimulationEvent [SimulationEnd] is received.
 * Simulation state is exposed trough StateFlow fol clients to observe.
 *
 *  @param simulationEnd time when simulation should end
 *  @param eventCreator creator that generates events for simulation
 *  @param initialState initial simulation state
 * */
class CrossroadSimulation(
    override val simulationEnd: Int,
    private val eventCreator: CrossroadSimulationEventCreator,
    initialState: CrossroadSimulationState,
) : Simulation {

    private val scope: CoroutineScope = CoroutineScope(Job())

    private val _simulationState = MutableStateFlow(initialState)
    override val simulationState: StateFlow<CrossroadSimulationState> = _simulationState

    /**
     * Starts simulation.
     * collects simulation states that create new events which maps to new states until [SimulationEnd] event is received.
     * */
    override fun startSimulation() {
        scope.launch {
            simulationState.collect { currentState ->
                val event = eventCreator.nextEvent(currentState)
                val newState = mapEventToState(event, currentState)
                _simulationState.emit(newState)
                if (event is SimulationEnd) cancel()
            }
        }
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
