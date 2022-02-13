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

class CrossroadSimulation(
    override val simulationEnd: Int,
    private val eventCreator: CrossroadSimulationEventCreator,
    initialState: CrossroadSimulationState,
) : Simulation {

    private val scope: CoroutineScope = CoroutineScope(Job())

    private val _simulationState = MutableStateFlow(initialState)
    override val simulationState: StateFlow<CrossroadSimulationState> = _simulationState

    override fun startSimulation() {
        scope.launch {
            simulationState.collect { currentState ->
                val event = eventCreator.nextEvent(currentState)
                val newState = mapEventToState(event, currentState)
                if (event is SimulationEnd) cancel()
                _simulationState.emit(newState)
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
     * Creates new state based on from CarArriveOnSemaphore event
     * */
    private fun handleCarrArrivedOnSemaphore(
        event: CarArriveOnSemaphore,
        currentState: CrossroadSimulationState,
    ): CrossroadSimulationState = currentState.copy(currentTime = event.eventTime).apply {
        crossroad.addCarToQueue(event.car, event.direction)
    }

    /**
     * Creates new state based on from CarStartLeavingCrossroad event
     * */
    private fun handleCarLeavesCrossRoad(
        event: CarStartLeavingCrossroad,
        currentState: CrossroadSimulationState,
    ): CrossroadSimulationState = currentState.copy(currentTime = event.eventTime).apply {
        crossroad.moveCarToLeavingQueue(event.direction, event.timeWhenCarLeaves)
    }
}
