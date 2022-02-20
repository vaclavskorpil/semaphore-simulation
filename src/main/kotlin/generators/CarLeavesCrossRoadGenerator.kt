package generators

import base.SimulationEventCreator
import data.CrossroadSimulationState
import simulation.CrossroadSimulationEvent

/**
 * Generates CarLeavesCrossRoad events based on current state
 * */
class CarLeavesCrossRoadGenerator :
    SimulationEventCreator<CrossroadSimulationState, CrossroadSimulationEvent.CarLeavesCrossRoad> {

    override fun nextEvent(
        simulationState: CrossroadSimulationState,
        simulationEndTime: Int,
    ): CrossroadSimulationEvent.CarLeavesCrossRoad {
        TODO()
    }

    override fun nextEventAt(simulationState: CrossroadSimulationState,simulationEndTime: Int,): Int? {
        return simulationState.crossroad.carsCrossing.minOfOrNull { it.value.timeToLave }
    }

}